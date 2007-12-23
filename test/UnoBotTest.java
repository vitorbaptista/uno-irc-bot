    /**
     * Testa os métodos da classe UnoBot.
     *
     * @author      Vítor Baptista
     * @version     %I%, %G%
     */

    package test;

    import org.junit.Before;
    import org.junit.Test;
    import static org.junit.Assert.assertEquals;
    import junit.framework.JUnit4TestAdapter;
    import java.util.Stack;
    import java.io.IOException;

    import jogodecartas.*;
    import jogodecartas.uno.*;

    public class UnoBotTest {
        private UnoBot bot = new UnoBot("unobot", "irc.dal.net");

        @Before public void mudaNomeBot() {
            bot.setVerbose(true);
        }

        @Before public void testaConnect() {
            /*try {
	        bot.connect("irc.freenode.net");
            while (!bot.isConnected())
                java.lang.Thread.sleep(500);
        }
	    catch (Exception e) {}
*/
        assertEquals("Erro na conexão! O bot não está conectado.", true, bot.isConnected());
    }

    @Test public void testaJoin() {
        try {
            java.lang.Thread.sleep(1000);
            bot.joinChannel("#unoteste");
            java.lang.Thread.sleep(1000);
        }
        catch (Exception e) {}

	    String sh[] = bot.getChannels();
        boolean entrou = false;

        for (int i = 0; i < sh.length; i++)
	    if (sh[i].equalsIgnoreCase("#unoteste")) {
        entrou = true;
        break;
        }

	    assertEquals("Erro no joinChannel! Não entrou no canal.", true, entrou);
    }

    @Test public void loopInfinito() {
        try {
        //    java.lang.Thread.sleep(10000);
        }
        catch (Exception e) {}
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(UnoBotTest.class);
    }
}
