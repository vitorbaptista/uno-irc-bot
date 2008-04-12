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


package jogodecartas;

import jogodecartas.uno.*;

import org.jibble.pircbot.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


public class Bot extends PircBot {
    protected Map jogos;

    public Bot() {
        this.setAutoNickChange(true);
        jogos = new HashMap();
        setMessageDelay(25);
        setVerbose(true);
    }

    public Bot(String nick) {
        this();
        this.setName(nick);
    }

    public Bot(String nick, String server) {
        this(nick);

        try {
            connect(server);
        } catch (Exception e) {
        }
    }

    protected void processaComando(String channel, String sender, String comando) {
        String[] c = comando.split("\\s");

        // Se houver um jogo no canal onde recebeu a mensagem...
        if (jogos.containsKey(channel)) {
            JogoDeCartas jogo = (JogoDeCartas) jogos.get(channel);
            Jogador[] jogadores = jogo.getJogadores();

            Jogador j = null;
            for (int i = 0; i < jogadores.length; i++)
                if (jogadores[i].getNome().equalsIgnoreCase(sender))
                    j = jogadores[i];

            boolean estaNaVez = (j == jogo.getProximoJogador());

            // Caso ele queira ver os jogadores...
            if (c[0].equalsIgnoreCase("jogadores")) {
                    String aux = "";

                    for (int i = 0; i < jogadores.length; i++)
                        aux += " " + jogadores[i].getNome();

                    sendMessage(channel, "Jogadores: " + aux);
            }
            // ou caso ele queira jogar uma carta
            else if (estaNaVez && c[0].equalsIgnoreCase("joga")) {
                String nomeCarta = "";
                for (int i = 1; i < c.length; i++)
                    nomeCarta += c[i] + " ";
                jogo.joga(nomeCarta.trim());
            }
           else if (c[0].equalsIgnoreCase("sai")) {
                  jogo.retiraJogador(j);
            }
        }
        
        if (c[0].equalsIgnoreCase("info")) {
            info(channel);
        }
    }

    private void info(String channel) {
        if (jogos.containsKey(channel)) {
            JogoDeCartas jogo = (JogoDeCartas) jogos.get(channel);

            // Formata a lista de jogadores p/ escrever
            Jogador js[] = jogo.getJogadores();
            String jogadoresString = "Jogadores: ";
            if (js.length > 0)
                jogadoresString = Colors.BOLD + js[0].getNome() + Colors.NORMAL + " ";
            for (int i = 1; i < js.length; i++)
                jogadoresString = jogadoresString + js[i].getNome() + Colors.NORMAL + " "; 
        
            sendMessage(channel, jogadoresString);


        }

    }
}
