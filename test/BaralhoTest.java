/**
 * Testa os métodos da classe Baralho.
 *
 * @author      Vítor Baptista
 * @version     %I%, %G%
 */

package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;
import java.util.Stack;

import jogodecartas.*;

public class BaralhoTest {
    private Baralho b = new Baralho(52);

    @Test public void testaEmbaralhamento() {
        Stack pilhaAntes = new Stack(),
              pilhaDepois = new Stack();

        for (int i = 0; i < b.getTamanho(); i++)
            pilhaAntes.push(b.pop());

        b.embaralha();
        for (int i = 0; i < b.getTamanho(); i++)
            pilhaDepois.push(b.pop());

        assertEquals("Erro no embaralhamento! O baralho não foi embaralhado.", false, pilhaAntes.equals(pilhaDepois));
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BaralhoTest.class);
    }
}
