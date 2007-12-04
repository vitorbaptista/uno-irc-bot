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
import java.util.Vector;


public class Uno implements JogoDeCartas {
    private static final int TAMANHO_BARALHO = 108;
    private Baralho baralho;
    private Vector jogadores;
    private Estado estado = Estado.INICIADO;

    public Uno() {
        baralho = new Baralho(TAMANHO_BARALHO);

        jogadores = new Vector();
    }

    public boolean joga(Carta c) {
        return false;
    }

    public Carta puxa() {
        return null;
    }

    public void passa() {
    }

    public void adicionaJogador(Jogador j) {
        Iterator i = jogadores.iterator();
        boolean possuiJogador = false;

        while (i.hasNext()) {
            if (((Jogador) i.next()).getNome().equalsIgnoreCase(j.getNome())) {
                possuiJogador = true;
            }
        }

        if (!possuiJogador) {
            jogadores.add(j);
        }
    }

    public void retiraJogador(Jogador j) {
    }

    public Vector getJogadores() {
        return jogadores;
    }

    public Jogador getProximoJogador() {
        return null;
    }

    public Estado getEstado() {
        return null;
    }

    public Jogador getVencedor() {
        return null;
    }
}
