package test;

import jogodecartas.*;
import jogodecartas.uno.*;

public class UnoBotTeste {
    public static void main(String[] args) {
        System.out.println("Inicializando");
        
        UnoBot uno = new UnoBot("unobot2", "irc.brasirc.org");

        try {
            uno.joinChannel("#unobot");

            while (true)
              Thread.sleep(1000);
        } catch (Exception e) {}
    }
}
