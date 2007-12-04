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


public class UnoBot extends Bot {
    //Este constructor está aqui mas está estranho. Não deveria precisar dele.
    //Afinal, há um constructor com esta assinatura na superclasse.
    public UnoBot(String nick, String server) {
        super(nick, server);
    }

    protected void processaComando(String channel, String sender, String comando) {
        super.processaComando(channel, sender, comando);

        String[] c = comando.split("\\s");

        if (c[0].equalsIgnoreCase("uno")) {
            if (!jogos.containsKey(channel)) {
                novoJogo(channel);
            }

            ((JogoDeCartas) jogos.get(channel)).adicionaJogador(new Jogador(
                    sender));
        }
    }

    private void novoJogo(String channel) {
        jogos.put(channel, new Uno());
    }

    protected void onMessage(String channel, String sender, String login,
        String hostname, String message) {
        if (message.charAt(0) == '!') {
            processaComando(channel, sender, message.substring(1));
        }
    }
}
