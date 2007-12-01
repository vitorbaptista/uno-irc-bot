/* =================================================== *
 *                                                     *
 *                        UNO                          *
 *        http://projetos.vitorbaptista.com/uno        *
 *               Licenciado sob a GPLv3                *
 *                                                     *
 *    Feito em 2007 por Daniel Pires, Felipe Lacet,    *
 *   Nath?lia Sobral, Pedro Dantas e V?tor Baptista.   *
 *                                                     *
 *      UFPB - Linguagem de Programa??o 2 - 2007.2     *
 *                                                     *
 * =================================================== */


package baralho;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;


/**
 * Trata das cartas e suas caracter??sticas.
 *
 * @author V??tor Baptista
 * @version %I%, %G%
 */
public class Baralho {
    private int tamanho;
    private Vector baralho = new Vector(tamanho);
    private Stack pilha = new Stack();

    /**
     * Cria um baralho com o <code>tamanho</code> passado como par??metro. No
     * caso de valores negativos ou zero, ele assume 52.
     *
     * @param tamanho tamanho do baralho
     */
    public Baralho(int tamanho) {
        this.tamanho = (tamanho > 0) ? tamanho : 52;

        for (int i = 0; i < tamanho; i++) {
            Carta c = new Carta();
            c.setCaracteristica("NUMERO", String.valueOf(i));
            baralho.add(c);
            pilha.push(i);
        }
    }

    /**
     * Embaralha a pilha de <code>Cartas</code>.
     *
     * @see Carta
     */
    public void embaralha() {
        /* Cria um vector com numeros de todas as cartas. */
        Vector v = new Vector(tamanho);

        for (int i = 0; i < tamanho; v.add(i++)) {
            ;
        }

        /* O vector anterior e usado aqui.
         * Escolhemos um indice aleatorio, ate o maximo do tamanho do vector,
         * o colocamos na pilha e o excluimos do vetor. Isto foi feito para
         * evitar que perdessemos muito tempo gerando numeros aleatorios para
         * embaralhar o baralho. */
        for (int i = 0; i < tamanho; i++) {
            Random r = new Random();
            int j = r.nextInt(v.size());
            pilha.push(v.elementAt(j));
            v.remove(j);
        }
    }

    /**
     * Retira um objeto <code>Carta</code> do topo da pilha e o retorna.
     *
     * @return a carta do topo da pilha
     *
     * @see Carta
     */
    public Carta pop() {
        return (Carta) baralho.elementAt((Integer) pilha.pop());
    }

    /**
     * Retorna um {@link Stack} com a pilha de cartas.
     *
     * @return pilha de cartas
     *
     * @see Carta
     */
    public Stack getPilha() {
        return this.pilha;
    }

    /**
     * Retorna o <code>tamanho</code> do baralho.
     *
     * @return tamanho do baralho
     */
    public int getTamanho() {
        return tamanho;
    }
}
