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
import java.util.Set;

/**
 * Classe do Uno. Implementa a interface JogoDeCartas.
 *
 * Possui um baralho com 108 cartas, sendo quatro 0, duas de cada de 1 a 9, de PULA, INVERTE e +2, sendo das cores AMARELO, AZUL, VERDE e VERMELHO, e quatro +4 e WILD(muda cor) PRETAs.
 * Cada jogador recebe 7 cartas inicialmente, e uma carta é retirada para o morto. Cada jogador pode jogar cartas de mesma cor, mesmo número, ou pretas. Ganha quem descartar todas as cartas primeiro.
 *
 * @author Vítor Baptista
 * @version 7.12.05
 * @since 7.12.03 */
public class Uno implements JogoDeCartas {
    private BaralhoUno baralho;
    private Vector jogadores;
    private LinkedList fila;
    public Estado estado = Estado.FIM;
    private int numCartasPuxar = 1;
    private int numJogadoresPassar = 1;
    private boolean podePassar = false;
    public Cor corMorto = null;
    private Jogador vencedor = null;

    public Uno() {
        baralho = new BaralhoUno();

        jogadores = new Vector();
        fila = new LinkedList(jogadores);
    }

    public int getNumCartasPuxar() {
        return numCartasPuxar;
    }

    public void inicia() {
        if ((estado != Estado.INICIADO) && (jogadores.size() > 0)) {
            estado = Estado.INICIADO;
            baralho.embaralha();

            Iterator i = jogadores.iterator();

            while (i.hasNext()) {
                Jogador j = (Jogador) i.next();

                for (int k = 0; k < 1; k++) {
                    j.adicionaCarta(baralho.pop());
                }
            }


            numCartasPuxar = 1;
            numJogadoresPassar = 1;
            vencedor = null;

            //TODO: É provável que haja um bug aqui. Talvez a mesma carta seja colocada no morto 2x,
            //      resultando numa carta perdida, já que, quando o baralho for embaralhado novamen-
            //      te, só poderá haver 108 cartas, mas haverão 109.
            baralho.push(baralho.pop());
            joga(getCartaMorto());
            corMorto = (Cor) getCartaMorto().getCaracteristica("COR");
            //podePassar = false;
            if (estado == Estado.AGUARDANDO_PASSAR)
                estado = Estado.AGUARDANDO_JOGADA;
 
        }
    }

    private void ataca(int valor) {
        numCartasPuxar += ((numCartasPuxar == 1) ? (valor - 1) : valor);
    }

    public boolean joga(Carta c) {
        //if ((estado == Estado.INICIADO) && !podePassar &&
        //        getProximoJogador().getBaralho().contains(c)) {
        if ((estado == Estado.AGUARDANDO_JOGADA && getProximoJogador().getBaralho().contains(c)) || estado == Estado.INICIADO) {

            if (numCartasPuxar > 1) {
                if ((baralho.peekMorto().getCaracteristica("TIPO") == "+2") &&
                        (c.getCaracteristica("TIPO") == "+2")) {
                    getProximoJogador().getBaralho().remove(c);
                    ataca(2);
                    baralho.push(c);
                    //podePassar = true;
                    corMorto = (Cor) c.getCaracteristica("COR");
                    estado = Estado.AGUARDANDO_PASSAR;
        
                    checaFimPartida(); //Talvez isso devesse estar no fim de Joga()


                    return true;
                }
            } else if ((c.getCaracteristica("COR") == getCorMorto()) ||
                    (c.getCaracteristica("NUMERO") == getCartaMorto().getCaracteristica("NUMERO")) ||
                    (c.getCaracteristica("COR") == getCorMorto() || 
                    ((c.getCaracteristica("TIPO") != "NORMAL") &&
                    (c.getCaracteristica("TIPO") == getCartaMorto().getCaracteristica("TIPO"))) ||
                    (c.getCaracteristica("COR") == Cor.PRETO))) {

                    getProximoJogador().getBaralho().remove(c);                    
                    corMorto = (Cor) c.getCaracteristica("COR");
                if (c.getCaracteristica("TIPO") == "+2") {
                    ataca(2);
                } else if (c.getCaracteristica("TIPO") == "+4") {
                    ataca(4);
                    baralho.push(c);
                    estado = Estado.AGUARDANDO_MUDA_COR;
                    checaFimPartida(); //Talvez isso devesse estar no fim de Joga()



                    return true;
                } else if (c.getCaracteristica("TIPO") == "PULA") {
                    numJogadoresPassar = 2;
                } else if ((Cor) c.getCaracteristica("COR") == Cor.PRETO) {
                    baralho.push(c);
                    estado = Estado.AGUARDANDO_MUDA_COR;
                    checaFimPartida(); //Talvez isso devesse estar no fim de Joga()



                    return true;
                } else if (c.getCaracteristica("TIPO") == "INVERTE") {
                    // Há um bug aqui. Quando ele inverte, ele já passa direto.
                    // Este não é o comportamento esperado. Ele deve inverter e esperar o jogador
                    // passar.
                    Jogador j = getProximoJogador();
                    fila = inverte();
                    baralho.push(c);
                    //podePassar = false;
                    estado = Estado.AGUARDANDO_JOGADA;
                    checaFimPartida(); //Talvez isso devesse estar no fim de Joga()


                    return true;
                }

                baralho.push(c);
                estado = Estado.AGUARDANDO_PASSAR;
                checaFimPartida(); //Talvez isso devesse estar no fim de Joga()


                return true;
            }
        }

        return false;
    }

    public boolean joga(String nomeCarta) {
        Carta aux = null;
        Object[] cartas = getProximoJogador().getBaralho().toArray();

        System.out.print("Tentando jogar: " + nomeCarta);
        for (int i = 0; i < cartas.length; i++) {
            System.out.print((((Carta) cartas[i])).getCaracteristica("NOME") + "\t");
            if (((String) ((Carta) cartas[i]).getCaracteristica("NOME")).equalsIgnoreCase(nomeCarta)) {
                aux = (Carta) cartas[i];
                System.out.println();
                return joga(aux);
            }
        }

        System.out.println();
        return false;
    }

    public Carta getCartaMorto() {
        return baralho.peekMorto();
    }

    public Cor getCorMorto() {
        return (baralho.peekMorto().getCaracteristica("COR") == Cor.PRETO) ? corMorto : (Cor) baralho.peekMorto().getCaracteristica("COR");
    }

    //Há um bug aqui. Caso a primeira carta jogada seja uma preta, quando o jogador
    //for trocar a cor, ele não poderá jogar imediatamente após, já terá passado.
    public void setCorMorto(Cor c) {
        System.out.println("Entrou no set");
        if (estado == Estado.AGUARDANDO_MUDA_COR) {// && corMorto == Cor.PRETO) {
            System.out.println("Entrou no if do set");
            corMorto = c;
            estado = Estado.AGUARDANDO_PASSAR;
        }
    }

    public Vector puxa() {
        if (estado != Estado.AGUARDANDO_PASSAR) {
            Vector v = new Vector(numCartasPuxar);

            for (; numCartasPuxar > 0; numCartasPuxar--) {
                System.out.println("Adicionando carta!");

                Carta c = (Carta) baralho.pop();
                getProximoJogador().adicionaCarta(c);
                v.add(c);
            }

            numCartasPuxar = 1;
            //podePassar = true;
            estado = Estado.AGUARDANDO_PASSAR;

            return v;
        } else {
            return new Vector(0);
        }
    }

    public boolean passa() {
        if (estado == Estado.AGUARDANDO_PASSAR && (fila.size() > 1)) {
            for (; numJogadoresPassar > 0; numJogadoresPassar--)
                fila.addLast(fila.poll());
            numJogadoresPassar = 1;

            //podePassar = false;
            estado = Estado.AGUARDANDO_JOGADA;

            return true;
        }

        return false;
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

    /*public Vector getJogadores() {
        return jogadores;
    }*/

    public Jogador[] getJogadores() {
        // Este jogador só é criado pois a toArray retorna o mesmo tipo passado como argumento.
        Jogador[] j = { new Jogador("") };

        return (Jogador[]) fila.toArray(j);
    }

    public Jogador getProximoJogador() {
        return (Jogador) fila.peek();
    }

    public Estado getEstado() {
        return estado;
    }

    private void checaFimPartida() {
        Jogador[] jogadores = getJogadores();

        if (jogadores.length == 0)
            estado = Estado.FIM;
        else if (jogadores.length == 1) {
            vencedor = jogadores[i];
            estado = Estado.FIM;
        }
        else
            for (int i = 0; i < jogadores.length; i++)
                if (jogadores[i].getBaralho().isEmpty()) {
                    vencedor = jogadores[i];
                    estado = Estado.FIM;
                    break;
                }
    }

    public Jogador getVencedor() {
        return vencedor;
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
