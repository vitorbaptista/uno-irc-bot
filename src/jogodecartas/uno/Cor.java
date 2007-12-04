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

public final class Cor {
    public static final Cor AMARELO = new Cor("AMARELO");
    public static final Cor AZUL = new Cor("AZUL");
    public static final Cor VERMELHO = new Cor("VERMELHO");
    public static final Cor VERDE = new Cor("VERDE");
    public static final Cor PRETO = new Cor("PRETO");
    private String id;

    private Cor(String id) {
        this.id = id;
    }

    public String toString() {
        return this.id;
    }
}
