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

import java.util.Stack;


public class BaralhoUno extends Baralho {
    private static final int TAMANHO = 108;
    private Stack morto = new Stack();

    //TODO: Este constructor está horrível. Tentar arranjar um jeito mais inteligente de instanciar as cartas. Talvez carregando de um XML?
    public BaralhoUno() {
        super(TAMANHO);

        Carta c = pop();
        c.setCaracteristica("NUMERO", 0);
        c.setCaracteristica("COR", Cor.AMARELO);
        c.setCaracteristica("TIPO", "NORMAL");
        push(c);

        c = pop();
        c.setCaracteristica("NUMERO", 0);
        c.setCaracteristica("COR", Cor.AZUL);
        c.setCaracteristica("TIPO", "NORMAL");
        push(c);

        c = pop();
        c.setCaracteristica("NUMERO", 0);
        c.setCaracteristica("COR", Cor.VERDE);
        c.setCaracteristica("TIPO", "NORMAL");
        push(c);

        c = pop();
        c.setCaracteristica("NUMERO", 0);
        c.setCaracteristica("COR", Cor.VERMELHO);
        c.setCaracteristica("TIPO", "NORMAL");
        push(c);


        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= 9; j++) {
                c = pop();
                c.setCaracteristica("NUMERO", j);
                c.setCaracteristica("COR", Cor.AMARELO);
                c.setCaracteristica("TIPO", "NORMAL");
                push(c);

                c = pop();
                c.setCaracteristica("NUMERO", j);
                c.setCaracteristica("COR", Cor.AZUL);
                c.setCaracteristica("TIPO", "NORMAL");
                push(c);

                c = pop();
                c.setCaracteristica("NUMERO", j);
                c.setCaracteristica("COR", Cor.VERDE);
                c.setCaracteristica("TIPO", "NORMAL");
                push(c);

                c = pop();
                c.setCaracteristica("NUMERO", j);
                c.setCaracteristica("COR", Cor.VERMELHO);
                c.setCaracteristica("TIPO", "NORMAL");
                push(c);
            }

            c = pop();
            c.setCaracteristica("TIPO", "+2");
            c.setCaracteristica("COR", Cor.AMARELO);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "+2");
            c.setCaracteristica("COR", Cor.AZUL);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "+2");
            c.setCaracteristica("COR", Cor.VERDE);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "+2");
            c.setCaracteristica("COR", Cor.VERMELHO);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "PULA");
            c.setCaracteristica("COR", Cor.AMARELO);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "PULA");
            c.setCaracteristica("COR", Cor.AZUL);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "PULA");
            c.setCaracteristica("COR", Cor.VERDE);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "PULA");
            c.setCaracteristica("COR", Cor.VERMELHO);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "INVERTE");
            c.setCaracteristica("COR", Cor.AMARELO);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "INVERTE");
            c.setCaracteristica("COR", Cor.AZUL);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "INVERTE");
            c.setCaracteristica("COR", Cor.VERDE);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "INVERTE");
            c.setCaracteristica("COR", Cor.VERMELHO);
            push(c);
        }

        for (int i = 0; i < 4; i++) {
            c = pop();
            c.setCaracteristica("TIPO", "+4");
            c.setCaracteristica("COR", Cor.PRETO);
            push(c);

            c = pop();
            c.setCaracteristica("TIPO", "WILD");
            c.setCaracteristica("COR", Cor.PRETO);
            push(c);
        }

        baralho.addAll(morto);
        int tmnho = getTamanho();

        for (int i = 0; i < tmnho; i++) {
            c = pop();

            ajeitaNomeCarta(c);
            System.out.print(((Integer) c.getCaracteristica("ID")).toString() + " - " + (String) c.getCaracteristica("NOME") + "|\t");

            push(c);
        }
    }

    private void ajeitaNomeCarta(Carta c) {
        if ((Cor) c.getCaracteristica("COR") == Cor.PRETO) {
            c.setCaracteristica("NOME", ((String) c.getCaracteristica("TIPO")));
        }
        else {
            if (((String) c.getCaracteristica("TIPO")).equalsIgnoreCase("NORMAL"))
                c.setCaracteristica("NOME", (((Integer) c.getCaracteristica("NUMERO")).toString()));
            else 
                c.setCaracteristica("NOME", ((String) c.getCaracteristica("TIPO")));
                
            c.setCaracteristica("NOME", (((String) c.getCaracteristica("NOME")) + " " + (String) (((Cor) c.getCaracteristica("COR")).toString())));
        }

   }

    public void push(Carta carta) {
        if ((getTamanho() < getCapacidade()) && (morto.search(carta) == -1)) {
            morto.push(carta);
        }
    }

    public Carta pop() {
        if (getTamanho() == 0) {
            embaralha();
        }

        return super.pop();
    }

    public void embaralha() {
        Stack mortoAux = new Stack();
        mortoAux.push(morto.pop());

        baralho.addAll(morto);

        morto = mortoAux;

        super.embaralha();
    }

    public Carta peekMorto() {
        return (Carta) morto.peek();
    }

    public Stack getMorto() {
        return this.morto;
    }

    public int getTamanhoMorto() {
        return morto.size();
    }
}
