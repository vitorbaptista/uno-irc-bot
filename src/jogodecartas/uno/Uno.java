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
    private Vector jogadores;
    private LinkedList fila;
    private Estado estado = Estado.FIM;
    private int numCartasPuxar = 1;
    private boolean podePassar = false;

    public Uno() {
        baralho = new BaralhoUno();

        baralho.push(baralho.pop());

        jogadores = new Vector();
        fila = new LinkedList(jogadores);
    }

    public int getNumCartasPuxar() {
        return numCartasPuxar;
    }

    public void inicia() {
        if ((estado != Estado.INICIADO) && (jogadores.size() > 0)) {
            estado = Estado.INICIADO;

            Iterator i = jogadores.iterator();

            while (i.hasNext()) {
                Jogador j = (Jogador) i.next();

                for (int k = 0; k < 7; k++) {
                    j.adicionaCarta(baralho.pop());
                }
            }

            podePassar = false;
        }
    }

    private void ataca(int valor) {
        numCartasPuxar += ((numCartasPuxar == 1) ? (valor - 1) : valor);
    }

    public boolean joga(Carta c) {
        if ((estado == Estado.INICIADO) && !podePassar &&
                getProximoJogador().getBaralho().contains(c)) {
            System.out.println("Entrou 1 - " + numCartasPuxar);
            System.out.println((Cor) c.getCaracteristica("COR"));

            if (numCartasPuxar > 1) {
                if ((baralho.peekMorto().getCaracteristica("TIPO") == "+2") &&
                        (c.getCaracteristica("TIPO") == "+2")) {
                    getProximoJogador().getBaralho().remove(c);
                    ataca(2);
                    baralho.push(c);
                    podePassar = true;
                    passa();

                    return true;
                }
            } else if ((c.getCaracteristica("COR") == baralho.peekMorto()
                                                                 .getCaracteristica("COR")) ||
                    (c.getCaracteristica("NUMERO") == baralho.peekMorto()
                                                                 .getCaracteristica("NUMERO")) ||
                    ((c.getCaracteristica("TIPO") != "NORMAL") &&
                    (c.getCaracteristica("TIPO") == baralho.peekMorto()
                                                               .getCaracteristica("TIPO"))) ||
                    (c.getCaracteristica("COR") == Cor.PRETO)) {
                System.out.println("Entrou 2");

                getProximoJogador().getBaralho().remove(c);

                if (c.getCaracteristica("TIPO") == "+2") {
                    ataca(2);
                } else if (c.getCaracteristica("TIPO") == "+4") {
                    ataca(4);
                } else if (c.getCaracteristica("TIPO") == "PULA") {
                    podePassar = true;
                    passa();
                } else if (c.getCaracteristica("TIPO") == "INVERTE") {
                    Jogador j = getProximoJogador();
                    fila = inverte();
                    baralho.push(c);
                    podePassar = false;

                    return true;
                }

                baralho.push(c);
                podePassar = true;
                passa();

                return true;
            }
        }

        return false;
    }

    public Carta getCartaMorto() {
        return baralho.peekMorto();
    }

    public Vector puxa() {
        if (!podePassar) {
            Vector v = new Vector(numCartasPuxar);

            for (; numCartasPuxar > 0; numCartasPuxar--) {
                System.out.println("Adicionando carta!");

                Carta c = (Carta) baralho.pop();
                getProximoJogador().adicionaCarta(c);
                v.add(c);
            }

            numCartasPuxar = 1;
            podePassar = true;

            return v;
        } else {
            return new Vector(0);
        }
    }

    public void passa() {
        if (podePassar && (fila.size() > 1)) {
            fila.addLast(fila.poll());
            podePassar = false;
        }
    }

    public void adicionaJogador(Jogador j) {
        if (estado != Estado.INICIADO) {
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
        Jogador[] j = { new Jogador("HAH") };

        return (Jogador[]) fila.toArray(j);
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
        LinkedList normal = new LinkedList(fila);

        while (normal.size() > 0) {
            inverso.addFirst(normal.removeFirst());
        }

        return inverso;
    }
}
