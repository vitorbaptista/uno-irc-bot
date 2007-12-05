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

import java.util.Collections;
import java.util.Stack;


/**
 * Trata das cartas e suas características.
 *
 * @author Vítor Baptista
 * @version %I%, %G%
 */
public class Baralho {
    private int capacidade;
    protected Stack baralho = new Stack();

    /**
     * Cria um baralho com capacidade de cartas passada como parâmetro. No caso
     * de valores negativos, ele assume 0.
     *
     * @param capacidade capacidade do baralho
     */
    public Baralho(int capacidade) {
        this.capacidade = (capacidade >= 0) ? capacidade : 0;

        for (int i = capacidade - 1; i >= 0; i--) {
            Carta c = new Carta();
            c.setCaracteristica("ID", i);
            baralho.push(c);
        }
    }

    /**
     * Embaralha o baralho de <code>Cartas</code>.
     *
     * @see Carta
     */
    public void embaralha() {
        /* Embaralha o baralho. */
        Collections.shuffle(baralho);
    }

    /**
     * Retira um objeto <code>Carta</code> do topo da baralho e o retorna.
     *
     * @return a carta do topo da baralho
     *
     * @see Carta
     */
    public Carta pop() {
        if (getTamanho() > 0) {
            return (Carta) baralho.pop();
        } else {
            return null;
        }
    }

    /**
     * Adiciona um objeto <code>Carta</code> à baralho.
     *
     * @param carta <code>Carta</code> a ser adicionada.
     *
     * @see Carta
     */
    public void push(Carta carta) {
        if (getTamanho() < capacidade) {
            baralho.push(carta);
        }
    }

    /**
     * Retorna um {@link Stack} com o baralho de cartas.
     *
     * @return baralho de cartas
     *
     * @see Carta
     */
    public Stack getBaralho() {
        return this.baralho;
    }

    /**
     * Retorna a quantidade de cartas que o baralho contém.
     *
     * @return tamanho do baralho
     */
    public int getTamanho() {
        return baralho.size();
    }

    /**
     * Retorna a capacidade de cartas do baralho.
     *
     * @return capacidade do baralho
     */
    public int getCapacidade() {
        return capacidade;
    }
}
