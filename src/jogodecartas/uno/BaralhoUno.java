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

import jogodecartas.Baralho;
import jogodecartas.Carta;


public class BaralhoUno extends Baralho {
    private static final int TAMANHO = 108;

    public BaralhoUno() {
        super(TAMANHO);

        System.err.println(getPilha().size());

        Carta c = pop();
        c.setCaracteristica("NUMERO", 0);
        c.setCaracteristica("COR", Cor.AMARELO);
        c.setCaracteristica("TIPO", "NORMAL");

        c = pop();
        c.setCaracteristica("NUMERO", 0);
        c.setCaracteristica("COR", Cor.AZUL);
        c.setCaracteristica("TIPO", "NORMAL");

        c = pop();
        c.setCaracteristica("NUMERO", 0);
        c.setCaracteristica("COR", Cor.VERDE);
        c.setCaracteristica("TIPO", "NORMAL");

        c = pop();
        c.setCaracteristica("NUMERO", 0);
        c.setCaracteristica("COR", Cor.VERMELHO);
        c.setCaracteristica("TIPO", "NORMAL");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                c = pop();
                c.setCaracteristica("NUMERO", j);
                c.setCaracteristica("COR", Cor.AMARELO);
                c.setCaracteristica("TIPO", "NORMAL");

                c = pop();
                c.setCaracteristica("NUMERO", j);
                c.setCaracteristica("COR", Cor.AZUL);
                c.setCaracteristica("TIPO", "NORMAL");

                c = pop();
                c.setCaracteristica("NUMERO", j);
                c.setCaracteristica("COR", Cor.VERDE);
                c.setCaracteristica("TIPO", "NORMAL");

                c = pop();
                c.setCaracteristica("NUMERO", j);
                c.setCaracteristica("COR", Cor.VERMELHO);
                c.setCaracteristica("TIPO", "NORMAL");
            }

            c = pop();
            c.setCaracteristica("TIPO", "+2");
            c.setCaracteristica("COR", Cor.AMARELO);

            c = pop();
            c.setCaracteristica("TIPO", "+2");
            c.setCaracteristica("COR", Cor.AZUL);

            c = pop();
            c.setCaracteristica("TIPO", "+2");
            c.setCaracteristica("COR", Cor.VERDE);

            c = pop();
            c.setCaracteristica("TIPO", "+2");
            c.setCaracteristica("COR", Cor.VERMELHO);

            c = pop();
            c.setCaracteristica("TIPO", "PULA");
            c.setCaracteristica("COR", Cor.AMARELO);

            c = pop();
            c.setCaracteristica("TIPO", "PULA");
            c.setCaracteristica("COR", Cor.AZUL);

            c = pop();
            c.setCaracteristica("TIPO", "PULA");
            c.setCaracteristica("COR", Cor.VERDE);

            c = pop();
            c.setCaracteristica("TIPO", "PULA");
            c.setCaracteristica("COR", Cor.VERMELHO);

            c = pop();
            c.setCaracteristica("TIPO", "INVERTE");
            c.setCaracteristica("COR", Cor.AMARELO);

            c = pop();
            c.setCaracteristica("TIPO", "INVERTE");
            c.setCaracteristica("COR", Cor.AZUL);

            c = pop();
            c.setCaracteristica("TIPO", "INVERTE");
            c.setCaracteristica("COR", Cor.VERDE);

            c = pop();
            c.setCaracteristica("TIPO", "INVERTE");
            c.setCaracteristica("COR", Cor.VERMELHO);
        }

        for (int i = 0; i < 4; i++) {
            c = pop();
            c.setCaracteristica("TIPO", "+4");
            c.setCaracteristica("COR", Cor.PRETO);

            c = pop();
            c.setCaracteristica("TIPO", "WILD");
            c.setCaracteristica("COR", Cor.PRETO);
        }

        embaralha();
    }
}
