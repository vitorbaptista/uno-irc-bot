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


/**
 * Interface de jogos de cartas. Todos jogos que a implementarem poderão rodar no servidor.
 *
 * @author Vítor Baptista
 * @version 7.12.03 
 * @since 7.12.03 */
public interface JogoDeCartas {
    /**
     * Inicia o jogo, já distribuindo as cartas para os jogadores e embaralhando o baralho. */
    void inicia();
    
    /**
     * Joga uma <code>Carta</code> na mesa.
     *
     * Retorna um boolean indicando se teve sucesso ou não ao jogar a carta.
     *
     * @param c <code>Carta</code> a ser jogada.
     * @return boolean foi jogada com sucesso?
     *
     * @see Carta */
    boolean joga(Carta c);

    /**
     * Puxa cartas. Retorna um vector com todas as cartas que ele deveria puxar.
     *
     * @return Vector cartas que precisava puxar. */
    Vector puxa();

    /**
     * Passa a vez. Retorna um boolean indicando se passou ou não.
     *
     * @return boolean passou a vez? */
    boolean passa();

    /**
     * Retorna a carta que está no morto.
     *
     * @return Carta carta no morto. */
    Carta getCartaMorto();

    /**
     * Adiciona um jogador à partida.
     *
     * @param j jogador. 
     *
     * @see Jogador */
    void adicionaJogador(Jogador j);

    /**
     * Retira um jogador da partida.
     *
     * @param j jogador.
     *
     * @see Jogador */
    void retiraJogador(Jogador j);

    /**
     * Retorna um array de jogadores com todos os jogadores participantes na ordem do jogo.
     *
     * @return Jogador[] jogadores.
     *
     * @see Jogador */
    Jogador[] getJogadores();

    /**
     * Retorna o próximo Jogador a jogar.
     *
     * @return Jogador próximo jogador.
     *
     * @see Jogador */
    Jogador getProximoJogador();

    /**
     * Retorna o estado atual do jogo.
     *
     * @return Estado estado do jogo
     *
     * @see Estado */
    Estado getEstado();

    /**
     * Retorna o vencedor do jogo, caso este já tenha terminado.
     * Caso ainda não haja vencedor, retorna null.
     *
     * @return Jogador vencedor, caso não haja, null.
     *
     * @see Jogador */
    Jogador getVencedor();
}
