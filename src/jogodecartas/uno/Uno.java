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

    public Carta puxaCarta() {
        return null;
    }

    public void adicionaJogador(Jogador j) {
    }

    public void retiraJogador(Jogador j) {
    }

    public Vector getJogadores() {
        return null;
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
