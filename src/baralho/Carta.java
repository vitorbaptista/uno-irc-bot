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

import java.util.Properties;


/**
 * Define uma carta de baralho. Pode ter diversas caracter??sticas definidas
 * pelo usu??rio.
 *
 * @author V??tor Baptista
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
     * Retorna uma String com o valor da caracter??stica passada por
     * par??metro, se existir. Se n??o, retorna uma string vazia.
     *
     * @param carac nome da caracter??stica
     *
     * @return valor da caracter??stica passada como par??metro
     */
    public String getCaracteristica(String carac) {
        return caracteristicas.getProperty(carac);
    }

    /**
     * Define o valor, passado no segundo par??metro, da caracter??stica,
     * passada no primeiro par??metro.
     *
     * @param carac caracter??stica a ser modificada
     * @param valor valor da caracter??stica
     */
    public void setCaracteristica(String carac, String valor) {
        caracteristicas.setProperty(carac, valor);
    }
}
