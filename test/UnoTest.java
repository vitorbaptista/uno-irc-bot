package test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;
import java.util.Set;
import java.util.Iterator;

import jogodecartas.*;
import jogodecartas.uno.*;

public class UnoTest {
    private Uno uno = new Uno();

    @Before public void testaIniciaEAdicionaJogadores() {
        uno.adicionaJogador(new Jogador("i"));
        uno.adicionaJogador(new Jogador("j"));

        uno.inicia();

        assertEquals("Erro ao adicionar jogadores!", 2, uno.getJogadores().size());
        assertEquals("Erro ao iniciar o jogo!", Estado.INICIADO, uno.getEstado());
    }

    @Test public void testaDistribuicaoCartas() {
        Iterator i = uno.getJogadores().iterator();
        boolean ok = true;

        while (i.hasNext()) {
            Jogador j = (Jogador) i.next();
            Set k = j.getBaralho();
            if (k.size() != 7) {
                System.err.println("Jogador " + j.getNome() + " possui apenas " + k.size() + " cartas.");
                ok = false;
            }
        }

        assertEquals("Erro ao distribuir as cartas!", true, ok);
    }

    @Test public void testaJoga() {
        Carta c = uno.getCartaMorto();
        System.out.println("Carta no Morto: (" + (Integer) c.getCaracteristica("ID") + ") " + (String) c.getCaracteristica("TIPO") + " - " + (Integer) c.getCaracteristica("NUMERO") + " - " + (Cor) c.getCaracteristica("COR"));

        Iterator i = uno.getProximoJogador().getBaralho().iterator();
        //while (i.hasNext()) {
            Carta jc = (Carta) i.next();
            System.out.println("Tentou jogar: (" + (Integer) jc.getCaracteristica("ID") + ") " + (String) jc.getCaracteristica("TIPO") + " - " + (Integer) jc.getCaracteristica("NUMERO") + " - " + (Cor) jc.getCaracteristica("COR"));


            //if (jc.getCaracteristica("COR") == c.getCaracteristica("COR") || jc.getCaracteristica("NUMERO") == c.getCaracteristica("NUMERO"))
                uno.joga(jc);
        //}

        System.out.println("Carta no Morto: (" + (Integer) c.getCaracteristica("ID") + ") " + (String) c.getCaracteristica("TIPO") + " - " + (Integer) c.getCaracteristica("NUMERO") + " - " + (Cor) c.getCaracteristica("COR"));
    }

    @Test public void testaGetProximoJogador() {
        Jogador j = uno.getProximoJogador();

        int numJogadores = uno.getJogadores().size(),
            numTentativas = numJogadores+1;
        
        while (!uno.getProximoJogador().getNome().equalsIgnoreCase(j.getNome()) && numTentativas > 0) {
            numTentativas--;
        }

        assertEquals("Erro ao pegar o próximo jogador!", true, numTentativas > 0);
    }

    @Test public void testaRetiraJogador() {
        Jogador j = uno.getProximoJogador();

        int tamanhoInicial = uno.getJogadores().size();
        uno.retiraJogador(j);
        int tamanhoFinal = uno.getJogadores().size();

        assertEquals("Erro ao retirar jogador! Não retirou nada.", true, tamanhoInicial > tamanhoFinal);
        assertEquals("Erro ao retirar jogador! Retirou o jogador errado.", false, j.getNome().equalsIgnoreCase(uno.getProximoJogador().getNome()));
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(UnoTest.class);
    } 
}
