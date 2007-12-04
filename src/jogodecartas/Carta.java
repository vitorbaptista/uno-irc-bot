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

import java.util.Dictionary;
import java.util.Hashtable;


/**
 * Define uma carta de baralho. Pode ter diversas características definidas
 * pelo usuário.
 *
 * @author Vítor Baptista
 * @version %I%, %G%
 */
public class Carta {
    private Dictionary caracteristicas;

    /**
     * Constructor default.
     */
    public Carta() {
        caracteristicas = new Hashtable();
    }

    /**
     * Retorna um Object com o valor da característica passada por parâmetro,
     * se existir. Se não, retorna null.
     *
     * @param caracteristica nome da característica
     *
     * @return valor da característica passada como parâmetro
     */
    public Object getCaracteristica(String caracteristica) {
        return caracteristicas.get(caracteristica);
    }

    /**
     * Define o valor, passado no segundo parâmetro, da característica, passada
     * no primeiro parâmetro.
     *
     * @param caracteristica característica a ser modificada
     * @param valor valor da característica
     */
    public void setCaracteristica(String caracteristica, Object valor) {
        caracteristicas.put(caracteristica, valor);
    }
}
