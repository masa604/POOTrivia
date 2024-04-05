import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A classe GUI representa a interface grafica do jogo POO Trivia.
 * Estende a classe JFrame e implementa acoes para os botoes de iniciar, TOP3 e sair.
 */
public class GUI extends JFrame {
    /** Lista de perguntas a serem utilizadas no jogo. */
    protected ArrayList<Pergunta> perguntas;

    /** O titulo do jogo. */
    private JLabel titulo;

    /** Imagem a exibir como background da interface grafica. */
    private JLabel background;

    /** Botao para iniciar o jogo. */
    private JButton iniciar;

    /** Botao para exibir o TOP3 jogadores. */
    private JButton top3;

    /** Botao para encerrar o jogo. */
    private JButton sair;

    /** Painel para conter os botoes de iniciar, TOP3 e sair. */
    private JPanel buttonsPanel;

    /** Painel principal da interface grafica. */
    private JPanel panel;

    /**
     * Construtor da GUI que inicializa os componentes da interface grafica.
     */
    public GUI() {
        super();


        titulo = new JLabel("POO Trivia", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 100));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        Color cinza = new Color(128, 128, 128);
        titulo.setForeground(cinza);


        ImageIcon backgroundImage = new ImageIcon("BackgroundGUI.jpg");
        background = new JLabel(backgroundImage);
        background.setLayout(new BorderLayout());

        iniciar = new JButton("Iniciar Jogo");
        top3 = new JButton("TOP3");
        sair = new JButton("Encerrar Jogo");

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.add(iniciar);
        buttonsPanel.add(top3);
        buttonsPanel.add(sair);


        background.add(titulo, BorderLayout.NORTH);
        background.add(buttonsPanel, BorderLayout.SOUTH);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(background);

        this.setContentPane(panel);

        BotListener botListener = new BotListener();
        iniciar.addActionListener(botListener);
        sair.addActionListener(botListener);
        top3.addActionListener(botListener);
    }

    /**
     * Exibe a janela com as informacoes do TOP3.
     */
    private static void exibirTop3() {
        ArrayList<String> top3Info = Ficheiro.calcularTOP3();

        StringBuilder message = new StringBuilder("Top 3 Jogadores\n");
        for (String info : top3Info) {
            message.append(info).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Top 3", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe uma mensagem de encerramento e finaliza o programa.
     */
    public static void fecharPrograma() {
        JOptionPane.showMessageDialog(null, "Jogo encerrado.");
        System.exit(0);
    }

    /**
     * Classe interna que implementa as acoes dos botoes na interface grafica.
     */
    private class BotListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == iniciar) {
                    GameGUI gameGUI = new GameGUI(perguntas);
                    gameGUI.setTitle("Jogo");
                    gameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gameGUI.setSize(900, 600);
                    gameGUI.setLocationRelativeTo(null);
                    gameGUI.setVisible(true);
                } else if (e.getSource() == sair) {
                    fecharPrograma();
                } else if (e.getSource() == top3) {
                    exibirTop3();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir janela", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Define a lista de perguntas a serem utilizadas no jogo.
     * @param perguntas A lista de perguntas.
     */
    public void setPerguntas(ArrayList<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }
}
