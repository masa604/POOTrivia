import javax.swing.*;
import java.util.ArrayList;

/**
 * A classe Jogo representa um jogo de trivia com 5 perguntas.
 * A interacao com o utilizador e realizada atraves de uma interface grafica (GUI), associada a cada jogo.
 */
public class Jogo {

    /** A interface grafica do jogo. */
    private GUI gui;

    /** Lista de perguntas que aparecem no jogo. */
    private ArrayList<Pergunta> perguntasJogo = new ArrayList<>();

    /**
     * Construtor que inicia o jogo.
     * Carrega as perguntas de um ficheiro, escolhe 5 perguntas aleatorias
     * e inicializa a interface grafica.
     */
    public Jogo(){
        ArrayList<Pergunta> listaPerguntas = new ArrayList<>();

        // Carrega perguntas do ficheiro "perguntas.txt"
        Ficheiro.loadPerguntas("pootrivia.txt", listaPerguntas);

        // Escolhe 5 perguntas aleatorias
        this.perguntasJogo = Ficheiro.escolhePerguntas(listaPerguntas);

        // Inicializa a interface grafica
        gui = new GUI();
        gui.setPerguntas(this.perguntasJogo);

        // Configuracões da GUI
        gui.setTitle("POOTrivia");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(900, 600);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }

    /**
     * Metodo principal que inicia o jogo criando uma instancia da classe Jogo.
     */
    public static void main(String[] args) {

        Jogo game = new Jogo();
    }
}
