package test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;
import java.util.Vector;

import jogodecartas.*;
import jogodecartas.uno.*;

public class TesteUno {
    private Uno uno = new Uno();

    @Before public void testaAdicionaJogador() {
        uno.adicionaJogador(new Jogador("i"));
        uno.adicionaJogador(new Jogador("j"));

        System.err.println("Size: " + uno.getJogadores().size());

        assertEquals("Erro ao adicionar jogadores!", 2, uno.getJogadores().size());
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
        return new JUnit4TestAdapter(TesteUno.class);
    } 
}
