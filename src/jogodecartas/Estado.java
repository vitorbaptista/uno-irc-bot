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

public final class Estado {
    public static final Estado INICIADO = new Estado("INICIADO");
    public static final Estado AGUARDANDO_JOGADA = new Estado("AGUARDANDO JOGADA");
    //public static final Estado AGUARDANDO_PUXA = new Estado("AGUARDANDO PUXA");
    public static final Estado AGUARDANDO_PASSAR = new Estado("AGUARDANDO PASSAR");
    public static final Estado AGUARDANDO_MUDA_COR = new Estado("AGUARDANDO MUDA COR");
    public static final Estado FIM = new Estado("FIM");
    private String id;

    private Estado(String id) {
        this.id = id;
    }

    public String toString() {
        return this.id;
    }
}
