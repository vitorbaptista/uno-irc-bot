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

        if (c[0].equalsIgnoreCase("msg")) {
            sendMessage(channel, sender + ": " + comando.substring(4));
        } else if (c[0].equalsIgnoreCase("jogadores")) {
            if (jogos.containsKey(channel)) {
                Jogador[] jogadores = ((JogoDeCartas) jogos.get(channel)).getJogadores();
                String j = "";

                for (int i = 0; i < jogadores.length; i++)
                    j += " " + jogadores[i].getNome();

                sendMessage(channel, "Jogadores: " + j);
            }
        }
    }
}
