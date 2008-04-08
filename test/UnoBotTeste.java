package test;

import jogodecartas.*;
import jogodecartas.uno.*;

public class UnoBotTeste {
    public static void main(String[] args) {
        System.out.println("Inicializando");
        
        UnoBot uno = new UnoBot("unobot2", "irc.dal.net");

        try {
            uno.joinChannel("#unobot");
        } catch (Exception e) {}

        while (true) ;
    }
}
