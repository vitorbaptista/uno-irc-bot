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
 * Quem sabe, o mesmo servidor. */
public abstract class JogoDeCartas {
    private String nomeJogo;
    private Baralho baralho;
    private Vector jogadores;

    abstract boolean joga(Carta c);

    abstract void adicionaJogador(Jogador j);

    abstract Vector getJogadores();

    abstract Carta puxaCarta();
}
