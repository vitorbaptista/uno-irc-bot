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

import java.util.Properties;


/**
 * Define uma carta de baralho. Pode ter diversas características definidas
 * pelo usuário.
 *
 * @author Vítor Baptista
 * @version %I%, %G%
 */
public class Carta {
    private Properties caracteristicas;

    /**
     * Constructor default.
     */
    public Carta() {
        caracteristicas = new Properties();
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
