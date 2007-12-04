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


package jogodecartas;

import java.util.Vector;


/* TODO: Pensar em como esta classe deve funcionar. Pensei em criá-la para podermos implementar realmente
 * as regras do jogo em uma subclasse, assim poderíamos ter diversos jogos usando as mesmas classes.
 * Quem sabe, o mesmo servidor. Talvez seria melhor usar uma interface ao invés de uma classe abstrata? */
public interface JogoDeCartas {
    boolean joga(Carta c);

    Carta puxa();

    void passa();

    void adicionaJogador(Jogador j);

    void retiraJogador(Jogador j);

    Vector getJogadores();

    Jogador getProximoJogador();

    Estado getEstado();

    Jogador getVencedor();
}
