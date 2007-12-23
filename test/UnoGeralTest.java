package test;

import jogodecartas.*;
import jogodecartas.uno.*;
import java.util.Set;
import java.util.Iterator;
import java.util.Scanner;

public class UnoGeralTest {
    private Uno uno = new Uno();
    
    private void exibe() {
        Jogador js[] = uno.getFila();
        for (int i = 0; i < 3; i++)
            System.out.print(js[i].getNome() + " - ");

        System.out.println("+" + uno.getNumCartasPuxar());

        System.out.println();

        Iterator i = uno.getJogadores().iterator();

        while (i.hasNext()) {
            Jogador j = (Jogador) i.next();
            System.out.print(j.getNome() + ": ");
            
            Iterator s = j.getBaralho().iterator();
            while (s.hasNext()) {
                Carta c = (Carta) s.next();

                    System.out.print("(" + (Integer) c.getCaracteristica("ID") + ") " + ((Cor) c.getCaracteristica("COR")).toString().substring(0, 4) + ((c.getCaracteristica("NUMERO") != null) ? (Integer) c.getCaracteristica("NUMERO") : "-1") + "-" + ((String) c.getCaracteristica("TIPO")).substring(0,2) + "\t");
            }
            System.out.println();
        }

        Carta morto = uno.getCartaMorto();
        System.out.println("Carta no morto: (" + (Integer) morto.getCaracteristica("ID") + ") " + (Integer) morto.getCaracteristica("NUMERO") + " - " + (Cor) morto.getCaracteristica("COR") + " - " + (String) morto.getCaracteristica("TIPO"));
    }

    private void joga() {
        Scanner input = new Scanner(System.in);
        System.out.print("Jogar carta: ");
        int carta = input.nextInt();

        if (carta == -1)
            uno.puxa();
        else
            if (carta == -2)
                uno.passa();
            else
                if (carta == -3) {
                    int c = input.nextInt();
                    Cor cor;
                    switch (c) {
                        case 0:
                            uno.setCorMorto(Cor.AMARELO);
                            break;
                        case 1:
                            uno.setCorMorto(Cor.AZUL);
                            break;
                        case 2:
                            uno.setCorMorto(Cor.VERDE);
                            break;
                        case 3:
                            uno.setCorMorto(Cor.VERMELHO);
                            break;
                        default:
                            break;
                    }
                }
                else {

        Iterator i = uno.getProximoJogador().getBaralho().iterator();
        while (i.hasNext()) {
            Carta c = (Carta) i.next();
            if (((Integer) c.getCaracteristica("ID")) == carta) {
                uno.joga(c);
                break;
            }
        }
            }
    }

    UnoGeralTest() {
        uno.adicionaJogador(new Jogador("j"));
        uno.adicionaJogador(new Jogador("k"));
        uno.adicionaJogador(new Jogador("l"));


        uno.inicia();

        while (true) {
            exibe();
            joga();
        }
    
    }
}
