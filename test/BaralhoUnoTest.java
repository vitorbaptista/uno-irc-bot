package test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;

import jogodecartas.*;
import jogodecartas.uno.*;

public class BaralhoUnoTest {
    private BaralhoUno baralho = new BaralhoUno();

    @Test public void testeCapacidadeBaralho() {
        assertEquals("Erro na capacidade do baralho!", 108, baralho.getCapacidade());
    }

    @Test public void testaExibicaoCartas() {
        baralho.embaralha();

        System.out.println(baralho.getBaralho().size() + " cartas.");
        System.out.println("ID\tTipo\tNumero\tCor");
        int size = baralho.getBaralho().size();
        for (int i = 0; i < size; i++) {
            Carta c = baralho.pop();
            System.out.println(c.getCaracteristica("ID") + "\t" + c.getCaracteristica("TIPO") + "\t" + c.getCaracteristica("NUMERO") + "\t" + ((Cor) c.getCaracteristica("COR")).toString());
        }

        baralho.embaralha();
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BaralhoUnoTest.class);
    } 
}
