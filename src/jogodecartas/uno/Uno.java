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
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;


public class Uno implements JogoDeCartas {
    private BaralhoUno baralho;
    private Carta cartaMesa;
    private Vector jogadores;
    private LinkedList fila;
    private Estado estado = Estado.INICIADO;

    public Uno() {
        baralho = new BaralhoUno();

        cartaMesa = baralho.pop();

        jogadores = new Vector();
        fila = new LinkedList(jogadores);
    }

    public boolean joga(Carta c) {
        if ((c.getCaracteristica("COR") == cartaMesa.getCaracteristica("COR")) ||
                (c.getCaracteristica("NUMERO") == cartaMesa.getCaracteristica(
                    "NUMERO")) || (c.getCaracteristica("COR") == Cor.PRETO)) {
            cartaMesa = c;
            passa();

            return true;
        }

        return false;
    }

    public Carta getCartaMesa() {
        return cartaMesa;
    }

    public Carta puxa() {
        return baralho.pop();
    }

    public void passa() {
        if (fila.size() > 1) {
            fila.addLast(fila.poll());
        }
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
            fila.add(j);
        }
    }

    public void retiraJogador(Jogador j) {
        Iterator i = jogadores.iterator();

        while (i.hasNext()) {
            Jogador next = (Jogador) i.next();

            if (next.getNome().equalsIgnoreCase(j.getNome())) {
                i.remove();
                fila.remove(next);

                break;
            }
        }
    }

    public Vector getJogadores() {
        return jogadores;
    }

    public Jogador[] getFila() {
        return (Jogador[]) fila.toArray();
    }

    public Jogador getProximoJogador() {
        return (Jogador) fila.peek();
    }

    public Estado getEstado() {
        return estado;
    }

    public Jogador getVencedor() {
        return null;
    }

    protected LinkedList inverte() {
        LinkedList inverso = new LinkedList();
        ListIterator iterator = jogadores.listIterator();

        while (iterator.hasPrevious()) {
            inverso.add(iterator.previous());
        }

        return inverso;
    }
}
