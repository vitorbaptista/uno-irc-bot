/* =================================================== *
 *                                                     *
 *                        UNO                          *
 *        http://projetos.vitorbaptista.com/uno        *
 *               Licenciado sob a GPLv3                *
 *                                                     *
 *    Feito em 2007 por Daniel Pires, Felipe Lacet,    *
 *   Nathália Sobral, Pedro Dantas e Vítor Baptista.   *
 *                                                     *
 *      UFPB - Linguagem de Programação 2 - 2007.2     *
 *                                                     *
 * =================================================== */


package jogodecartas.uno;

import jogodecartas.*;
import java.util.Iterator;
import org.jibble.pircbot.Colors;

public class UnoBot extends Bot {
    //Este constructor está aqui mas está estranho. Não deveria precisar dele.
    //Afinal, há um constructor com esta assinatura na superclasse.
    public UnoBot(String nick, String server) {
        super(nick, server);
    }

    protected void processaComando(String channel, String sender, String comando) {
        //super.processaComando(channel, sender, comando);

        String[] c = comando.split("\\s");

        if (c[0].equalsIgnoreCase("uno")) {
            if (!jogos.containsKey(channel)) {
                sendMessage(channel, "Criando jogo...");
                novoJogo(channel);
            }

            ((Uno) jogos.get(channel)).adicionaJogador(new Jogador(
                    sender));
        }
        else {

        Uno uno = (Uno) jogos.get(channel);
        if (uno == null) sendMessage(channel, "Não há jogo neste canal");
        Jogador[] jogadores = uno.getJogadores();


            Jogador j = null;
            for (int i = 0; i < jogadores.length; i++)
                if (jogadores[i].getNome().equalsIgnoreCase(sender))
                    j = jogadores[i];

            boolean estaNaVez = (j == uno.getProximoJogador());

            if (jogos.containsKey(channel) && c[0].equalsIgnoreCase("inicia") && uno.getEstado() == Estado.FIM) {
                sendMessage(channel, "Iniciando jogo...");
                uno.inicia();
            }
            if (jogos.containsKey(channel) && j != null && uno.getEstado() != Estado.FIM) {
                if (c[0].equalsIgnoreCase("exibe"))
                        exibe(channel);
                if (c[0].equalsIgnoreCase("sai"))
                    uno.retiraJogador(j);
                if (estaNaVez) {
                    System.out.println("Esta na vez");
                    if (c[0].equalsIgnoreCase("puxa"))
                        uno.puxa();
                    else if (c[0].equalsIgnoreCase("passa")) {
                            uno.passa();
                            exibe(channel);
                    }
                    else if (c[0].equalsIgnoreCase("joga")) {
                            sendMessage(channel, sender + " jogando...");
                            if (c.length == 2) 
                                uno.joga(c[1]);
                            else if (c.length == 3) 
                                uno.joga(c[1] + " " + c[2]);

                            checaFimJogo(channel);
                            }
                    else if (c.length == 2 && c[0].equalsIgnoreCase("cor")) {
                        if (c[1].equalsIgnoreCase("AMARELO")) uno.setCorMorto(Cor.AMARELO);
                        else if (c[1].equalsIgnoreCase("AZUL")) uno.setCorMorto(Cor.AZUL);
                        else if (c[1].equalsIgnoreCase("VERDE")) uno.setCorMorto(Cor.VERDE);
                        else if (c[1].equalsIgnoreCase("VERMELHO")) uno.setCorMorto(Cor.VERMELHO);
                    }
                }
            }
        }
    }

    private void checaFimJogo(String channel) {
        Uno uno = (Uno) jogos.get(channel);

        System.out.println("Checando fim de jogo");

        if (uno.getEstado() == Estado.FIM) {
           Jogador vencedor = uno.getVencedor();
           System.out.println(vencedor.getNome() + " venceu!");
           sendMessage(channel, "O jogador " + Colors.BOLD + vencedor.getNome() + Colors.NORMAL + " venceu!");
            jogos.remove(channel);
        }
    }

    private void novoJogo(String channel) {
        Uno uno = new Uno();
        jogos.put(channel, uno);
        System.out.println(((Uno) jogos.get(channel)).getEstado().toString());
        if (jogos.containsKey(channel))
            sendMessage(channel, "Jogo adicionado com sucesso!");
        else
            sendMessage(channel, "Falha ao adicionar o jogo.");
    }

    protected void onMessage(String channel, String sender, String login,
        String hostname, String message) {
        if (message.charAt(0) == '!') { 
            processaComando(channel, sender, message.substring(1));
        }
    }

    private String formataCarta(Carta c) {
        String resultado = "";

        Cor cor = (Cor) c.getCaracteristica("COR");
        if (cor == Cor.AMARELO)
            resultado = resultado + Colors.YELLOW;
        else if (cor == Cor.AZUL)
                resultado = resultado + Colors.BLUE;
        else if (cor == Cor.VERDE)
                resultado = resultado + Colors.GREEN;
        else if (cor == Cor.VERMELHO)
                resultado = resultado + Colors.RED;

        resultado = resultado + ((c.getCaracteristica("TIPO") == "NORMAL") ? (Integer) c.getCaracteristica("NUMERO") : (String) c.getCaracteristica("TIPO"));

        resultado = resultado + Colors.NORMAL;

        return resultado;

    }

    private void exibe(String channel) {
        Uno uno = (Uno) jogos.get(channel);

        sendMessage(channel, uno.estado.toString());
        if (uno.corMorto != null)
            sendMessage(channel, uno.corMorto.toString());


        Jogador js[] = uno.getJogadores();
        String jogadoresString = "";
        if (js.length > 0)
            jogadoresString = Colors.BOLD + js[0].getNome() + Colors.NORMAL + " ";
        for (int i = 1; i < js.length; i++)
            jogadoresString = jogadoresString + " " + js[i].getNome(); 
        
        sendMessage(channel, jogadoresString + " + " + uno.getNumCartasPuxar());

        Jogador[] jogadores = uno.getJogadores();

        for (int i = 0; i < jogadores.length; i++) {
            String aux = "";
            aux = aux + jogadores[i].getNome() + ": ";
            
            Object[] cartas = jogadores[i].getBaralho().toArray();
            for (int j = 0; j < cartas.length; j++) {
                //System.out.println("j: " + j + ", length: " + cartas.length);
                Carta c = (Carta) cartas[j];
                aux = aux + "(" + (Integer) c.getCaracteristica("ID") + ") " + formataCarta(c) + " ";
            }

            sendMessage(channel, aux);
        }
        Carta morto = uno.getCartaMorto();
        sendMessage(channel, "Carta no morto: (" + (Integer) morto.getCaracteristica("ID") + ") " + formataCarta(morto));
    }

}
