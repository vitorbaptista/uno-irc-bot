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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;


/**
 * Trata do nome, características e mão do jogador.
 *
 * @author Vítor Baptista
 * @version %I%, %G%
 */
public class Jogador {
    private String nome;
    private Set baralho;
    private Properties caracteristicas;

    /**
     * Cria um jogador com o nome passado por parâmetro.
     *
     * @param nome nome do jogador
     */
    public Jogador(String nome) {
        this.nome = nome;
        this.baralho = new HashSet();
    }

    /**
     * Adiciona uma <code>Carta</code> à mão do jogador.
     *
     * @param carta carta a ser adicionada
     */
    public void adicionaCarta(Carta carta) {
        baralho.add(carta);
    }

    /**
     * Retira uma <code>Carta</code> da mão do jogador.
     *
     * @param caracteristica característica da carta a ser retirada
     * @param valor valor da característica
     */
    public void retiraCarta(String caracteristica, String valor) {
        Iterator i = baralho.iterator();

        while (i.hasNext()) {
            if (((Carta) i.next()).getCaracteristica(caracteristica) == valor) {
                i.remove();

                break;
            }
        }
    }

    /**
     * Modifica o nome do jogador.
     *
     * @param nome novo nome do jogador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna uma String com o nome do jogador.
     *
     * @return nome do jogador
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna uma String com o valor da característica passada por parâmetro,
     * se existir. Se não, retorna uma string vazia.
     *
     * @param caracteristica nome da característica
     *
     * @return valor da característica passada como parâmetro
     */
    public String getCaracteristica(String caracteristica) {
        return caracteristicas.getProperty(caracteristica);
    }

    /**
     * Define o valor, passado no segundo parâmetro, da característica, passada
     * no primeiro parâmetro.
     *
     * @param caracteristica característica a ser modificada
     * @param valor valor da característica
     */
    public void setCaracteristica(String caracteristica, String valor) {
        caracteristicas.setProperty(caracteristica, valor);
    }
}
