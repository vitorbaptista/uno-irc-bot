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


package jogodecartas.uno;

import jogodecartas.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;
import java.util.Set;
import java.util.Random;

/**
 * Classe do Uno. Implementa a interface JogoDeCartas.
 *
 * Possui um baralho com 108 cartas, sendo quatro 0, duas de cada de 1 a 9, de PULA, INVERTE e +2, sendo das cores AMARELO, AZUL, VERDE e VERMELHO, e quatro +4 e WILD(muda cor) PRETAs.
 * Cada jogador recebe 7 cartas inicialmente, e uma carta é retirada para o morto. Cada jogador pode jogar cartas de mesma cor, mesmo número, ou pretas. Ganha quem descartar todas as cartas primeiro.
 *
 * @author Vítor Baptista
 * @version 7.12.22
 * @since 7.12.03 */
public class Uno implements JogoDeCartas {
    private BaralhoUno baralho;
    private Vector jogadores;
    private LinkedList fila;
    private Estado estado;
    private Jogador vencedor;
    private int numCartasPuxar;
    private Cor corMorto;
    private int numJogadas;
    private final int NUMCARTASINICIO = 7;
    private int numJogadoresPassar;

    /**
     * Constructor padrão. Inicializa o baralho, jogadores, fila e estado.
     */
    public Uno() {
        baralho = new BaralhoUno();
        jogadores = new Vector();
        fila = new LinkedList(jogadores);
        estado = Estado.FIM;
        vencedor = null;
        numCartasPuxar = 1;
        corMorto = null;
        numJogadas = 0;
    }

    /**
     * Caso o estado do jogo seja FIM e haja pelo menos dois jogadores, inicia o jogo.
     */
    public void inicia() {
        if ((estado == Estado.FIM) && (jogadores.size() > 1)) {
            estado = Estado.INICIADO;
            vencedor = null;
            numCartasPuxar = 1;
	    numJogadoresPassar = 1;
            corMorto = null;
            baralho.embaralha();

            Iterator i = jogadores.iterator();

            // Distribui as cartas para cada jogador.
            while (i.hasNext()) {
                Jogador j = (Jogador) i.next();

                for (int k = 0; k < NUMCARTASINICIO; k++)
                    j.adicionaCarta(baralho.pop());
            }

            // Coloca uma carta no morto
            baralho.push(baralho.pop());
            joga(getCartaMorto());

            // Caso ele tenha jogado um +4 ou WILD e precise mudar a cor
            if (estado == Estado.MUDA_COR) {
                Random rand = new Random();
                int cor = rand.nextInt(4);

                switch (cor) {
                case 0:
                  setCorMorto(Cor.AMARELO);
                  break;
                case 1:
                  setCorMorto(Cor.AZUL);
                  break;
                case 2:
                  setCorMorto(Cor.VERMELHO);
                  break;
                case 3:
                  setCorMorto(Cor.VERDE);
                  break;
                default:
                  setCorMorto(Cor.AZUL);
                  break;
                }
            }
            
            // Muda o estado para o primeiro jogador poder jogar
            if (estado == Estado.PASSA)
                estado = Estado.JOGA;

            // Zera o número de jogadas
            numJogadas = 0;
        }
    
    }

    private boolean podeJogar(Carta c) {
        // Caso o jogador possa jogar e tenha a carta, ou seja a primeira jogada(colocando a carta do morto)
        if ((estado == Estado.JOGA && getProximoJogador().getBaralho().contains(c)) || estado == Estado.INICIADO) {
            System.out.println("Entrou no pode jogar");

            // Se o jogador anterior não tiver jogado um +4 e...
            boolean eMais4 = (((String) baralho.peekMorto().getCaracteristica("TIPO")).equalsIgnoreCase("+4") && numCartasPuxar == 4);

            // Nem tiver jogado um +2 e a carta a ser jogada não é um mais dois
            boolean conservaMais2 = (((String) baralho.peekMorto().getCaracteristica("TIPO")).equalsIgnoreCase("+2") && numCartasPuxar > 1 && !((String) c.getCaracteristica("TIPO")).equalsIgnoreCase("+2"));
            
            // A cor for igual ou...
            boolean mesmaCor = baralho.peekMorto().getCaracteristica("COR") == c.getCaracteristica("COR");
 
            // Mesmo número ou...
            boolean mesmoNumero = baralho.peekMorto().getCaracteristica("NUMERO") == c.getCaracteristica("NUMERO");

            // Mesmo tipo, diferente de NORMAL, ou...
            boolean mesmoTipo = !(((String) baralho.peekMorto().getCaracteristica("TIPO")).equalsIgnoreCase("NORMAL")) && ((String) baralho.peekMorto().getCaracteristica("TIPO")).equalsIgnoreCase((String) c.getCaracteristica("TIPO"));

            // A carta for preta ou...
            boolean cartaPreta = ((Cor) c.getCaracteristica("COR")) == Cor.PRETO;

            // A carta do morto for preta
            boolean cartaMortoPreta = ((Cor) baralho.peekMorto().getCaracteristica("COR")) == Cor.PRETO;
 
            System.out.println("!eMais4: " + !eMais4 + "\t!conservaMais2: " + !conservaMais2 + "\tmesmaCor: " + mesmaCor + "\tmesmoNumero: " + mesmoNumero + "\tmesmoTipo: " + mesmoTipo + "\tcartaPreta: " + cartaPreta + "\tcartaMortoPreta: " + cartaMortoPreta);

            if (!eMais4 && !conservaMais2 && (mesmaCor || mesmoNumero || mesmoTipo || cartaPreta || cartaMortoPreta))
                // Então pode jogar
                return true;
            else
                // Se não, não.
                return false;
        }
        else
            return false;
    }

    /**
     * Joga uma <code>Carta</code>
     *
     * @param c carta a ser jogada
     *
     * @return boolean conseguiu jogar?
     */
    public boolean joga(Carta c) {
        // Pode jogar?
        System.out.print("Pode jogar? ");
        if (podeJogar(c)) {
            System.out.println("SIM!");
            String tipo = (String) c.getCaracteristica("TIPO");
            if (tipo.equalsIgnoreCase("+2"))
                ataca(2);
            else
                if (tipo.equalsIgnoreCase("+4"))
                    ataca(4);
                else
                    if (tipo.equalsIgnoreCase("INVERTE"))
                        fila = inverte(fila);

		    else if (tipo.equalsIgnoreCase("PULA"))
			numJogadoresPassar = 2;

            if (c.getCaracteristica("COR") == Cor.PRETO)
                estado = Estado.MUDA_COR;
            else
                estado = Estado.PASSA;

            getProximoJogador().getBaralho().remove(c);
            baralho.push(c);
            numJogadas++;
            checaFimPartida();

            return true;
        }
        else
            System.out.println("NÃO");
            return false;
    }

    /**
     * Joga a carta com o nome passado como parâmetro.
     *
     * @param nomeCarta nome da carta
     *
     * @return boolean conseguiu jogar?
     */
    public boolean joga(String nomeCarta) {
        System.out.println("Tentando jogar " + nomeCarta);
        if (estado == Estado.JOGA) {
            Carta aux = null;
            // Pega o baralho do jogador
            Object[] cartas = getProximoJogador().getBaralho().toArray();

            System.out.println("Procurando carta na mão do jogador...");
            // Compara cada carta p/ saber se há uma com o nome passado por parâmetro
            for (int i = 0; i < cartas.length; i++) {
                System.out.println( ((String) ((Carta) cartas[i]).getCaracteristica("TIPO")) + " '" + ((String) ((Carta) cartas[i]).getCaracteristica("NOME")) + "' == '" + nomeCarta + "'");
                if (((String) ((Carta) cartas[i]).getCaracteristica("NOME")).equalsIgnoreCase(nomeCarta)) {
                    // Se houver, joga ela e retorna o valor retornado pela joga()
                    System.out.println("Encontrou, jogando...");
                    return joga((Carta) cartas[i]);
                }
            }

            // Se não, retorna falso
            return false;
        }
        else
            return false;
    }

    /**
     * Aumenta o número de cartas que o próximo jogador terá que puxar.
     *
     * @param valor número de cartas a mais
     */
    private void ataca(int valor) {
        numCartasPuxar += ((numCartasPuxar == 1) ? (valor - 1) : valor);
    }

    /**
     * Modifica a cor do morto, caso o jogador tenha jogado uma carta que o permita.
     *
     * @param c nova cor
     */
    public void setCorMorto(Cor c) {
        if (estado == Estado.MUDA_COR) {
            corMorto = c;
            estado = (numJogadas == 0) ? Estado.JOGA : Estado.PASSA;
        }
    }

    /**
     * Caso possível, o jogador atual puxa n cartas, dependendo se o atacaram ou não. Adiciona as cartas à mão do jogador e retorna um <code>Vector</code> com elas.
     *
     * @return Vector cartas adicionadas.
     */
    public Vector puxa() {
        if (estado == Estado.JOGA) {
            Vector v = new Vector(numCartasPuxar);

            // Puxa tantas cartas quando houver no numCartasPuxar
            for (; numCartasPuxar > 0; numCartasPuxar--) {
                // Retira a carta do baralho
                Carta c = (Carta) baralho.pop();
                // Adiciona à mão do jogador
                getProximoJogador().adicionaCarta(c);
                // e ao Vector
                v.add(c);
            }

            // Volta o numCartasPuxar para o padrão
            numCartasPuxar = 1;
            // Muda o estado do jogo
            estado = Estado.PASSA;

            // Retorna o Vector
            return v;
        }
        else
            return new Vector(0);
    }

    /**
     * Caso o estado do jogo esteja em PASSA, passa a vez para o próximo jogador. Caso não hajam erros, retorna true, caso contrário, false.
     *
     * @return boolean passou?
     */
    public boolean passa() {
        if (estado == Estado.PASSA) {
            // A carta no morto é Pula? Se sim, passa 2, se não, 1.
//            numJogadoresPassar = (((String) getCartaMorto().getCaracteristica("TIPO")).equalsIgnoreCase("PULA")) ? 2 : 1;


            // Passa numJogadoresPassar
            for (; numJogadoresPassar > 0; numJogadoresPassar--)
                fila.addLast(fila.poll());

            // Seta o estado para aguardar a próxima jogada
            estado = Estado.JOGA;

	    numJogadoresPassar = 1;

            return true;
        }

        return false; 
    }

    /**
     * Adiciona um jogador à partida, caso ele já não esteja nela e a partida ainda não tenha começado.
     *
     * @param j jogador
     */
    public void adicionaJogador(Jogador j) {
        if (estado == Estado.FIM) {
            Iterator i = jogadores.iterator();
            boolean possuiJogador = false;

            // Caso haja algum jogador com o mesmo nome...
            while (i.hasNext())
                if (((Jogador) i.next()).getNome().equalsIgnoreCase(j.getNome()))
                    possuiJogador = true;

            // Não entra nesse if, logo, não adiciona.
            // Se não, adiciona-o ao vector de jogadores e à fila.
            if (!possuiJogador) {
                jogadores.add(j);
                fila.add(j);
            }
        }
 
    }

    /**
     * Retira um jogador da partida.
     *
     * @param j jogador a ser retirado
     *
     * @see Jogador
     */
    public void retiraJogador(Jogador j) {
        Iterator i = jogadores.iterator();

        while (i.hasNext()) {
            Jogador next = (Jogador) i.next();

            if (next.getNome().equalsIgnoreCase(j.getNome())) {
                i.remove();
                fila.remove(next);
                checaFimPartida();

                break;
            }
        }
    }

    /**
     * Retorna um array de Jogadores contendo todos os participantes da partida.
     *
     * @return Jogador[] jogadores
     */
    public Jogador[] getJogadores() {
        // Este jogador só é criado pois a toArray retorna o mesmo tipo passado como argumento.
        Jogador[] j = { new Jogador("") };

        return (Jogador[]) fila.toArray(j);
    }

    /**
     * Retorna o próximo jogador que irá jogar, sem mudar sua posição na fila.
     *
     * @return Jogador próximo jogador
     *
     * @see Jogador
     */
    public Jogador getProximoJogador() {
        return (Jogador) fila.peek();
    }

    /**
     * Retorna a carta que está no topo do morto.
     *
     * @return Carta carta no morto
     *
     * @see Carta
     */
    public Carta getCartaMorto() {
        return baralho.peekMorto();
    }

    /**
     * Retorna a cor do morto.
     *
     * @return Cor cor do morto
     */
    public Cor getCorMorto() {
        return (baralho.peekMorto().getCaracteristica("COR") == Cor.PRETO) ? corMorto : (Cor) baralho.peekMorto().getCaracteristica("COR"); 
    }

    /**
     * Retorna o estado atual do jogo.
     *
     * @return Estado estado do jogo
     *
     * @see Estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Retorna o vencedor do jogo atual. Caso ainda não haja vencedor, retorna null.
     *
     * @return Jogador vencedor
     */
    public Jogador getVencedor() {
        return vencedor;
    }

    /**
     * Checa pelo fim da partida. Ele ocorre quando não há mais nenhum jogador, ou só um, e ele é declarado o vencedor, ou alguém descartou todo seu baralho.
     */
    private void checaFimPartida() {
        if (estado != Estado.FIM) {
            Jogador[] jogadores = getJogadores();

            if (jogadores.length == 0)
                estado = Estado.FIM;
            else 
                if (jogadores.length == 1) {
                    vencedor = jogadores[0];
                    estado = Estado.FIM;
                }
            else
                for (int i = 0; i < jogadores.length; i++)
                    if (jogadores[i].getBaralho().isEmpty()) {
                        vencedor = jogadores[i];
                        estado = Estado.FIM;
                        break;
                    }
        }
    }

    /**
     * Recebe uma <code>LinkedList</code> como parâmetro, inverte seus elementos e retorna o resultado.
     *
     * @param fila fila a ser invertida
     *
     * @return LinkedList fila invertida
     */
    protected LinkedList inverte(LinkedList fila) {
        if (fila.size() > 0) {
            LinkedList inverso = new LinkedList();

            // Retira o primeiro jogador, para que ele continue na frente da fila.
            Jogador j = (Jogador) fila.removeFirst();

            // Inverte a fila
            while (fila.size() > 0) {
                inverso.addFirst(fila.removeFirst());
            }

            // Adiciona o primeiro jogador à cabeça da fila
            inverso.addFirst(j);

            return inverso;
        }
        else
            return fila;
    }

    // Estes métodos estão aqui só para debugar
    public int getNumCartasPuxar() {
        return numCartasPuxar;
    }
}
