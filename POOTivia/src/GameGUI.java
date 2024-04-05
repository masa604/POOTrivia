import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A classe GameGUI representa a interface grafica do jogo POO Trivia durante o jogo.
 * GameGUI e subclasse da GUI e apresenta as perguntas e opcoes, tal como
 * processa as respostas do jogador.
 */
public class GameGUI extends GUI {
    /** Rotulo para exibir a pergunta atual. */
    private JLabel perguntaText;

    /** indice para controlar a pergunta atual na lista de perguntas. */
    private int index;

    /** Lista de botoes para as opcoes de resposta. */
    private ArrayList<JButton> options;

    /** Painel principal da interface grafica. */
    private JPanel panel;

    /** Painel para exibir o texto da pergunta. */
    private JPanel panelText;

    /** Painel que contem os botoes de opcoes de resposta. */
    private JPanel buttonsPanel;

    /** Lista de perguntas que o jogador acertou. */
    private ArrayList<Pergunta> acertou;

    /** Lista de perguntas que o jogador errou. */
    private ArrayList<Pergunta> errou;

    /**
     * Construtor da classe GameGUI que inicializa os componentes da interface grafica e
     * configura a primeira pergunta.
     * @param perguntas Lista de perguntas a serem utilizadas no jogo.
     */
    public GameGUI(ArrayList<Pergunta> perguntas) {
        this.perguntas = perguntas;
        this.index = 0;

        this.acertou = new ArrayList<>();
        this.errou = new ArrayList<>();

        perguntaText = new JLabel();
        options = new ArrayList<>();

        panelText = new JPanel(new FlowLayout());
        panelText.add(perguntaText);

        buttonsPanel = new JPanel();

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panelText);
        panel.add(buttonsPanel);

        this.add(panel);

        setupQuestion();
    }

    /**
     * Configura a pergunta atual e as opcoes de resposta na interface grafica.
     */
    private void setupQuestion() {
        Pergunta currentQuestion = perguntas.get(index);

        perguntaText.setText(currentQuestion.getQuestao());
        perguntaText.setFont(new Font("Arial", Font.BOLD,15));
        ArrayList<String> buttonsText = currentQuestion.getOptionsToShow();
        createOptionsText(buttonsText);
    }

    /**
     * Cria os botoes de opcoes de resposta com base nas opcoes fornecidas.
     * @param buttonsText Lista de textos das opcoes de resposta.
     */
    private void createOptionsText(ArrayList<String> buttonsText) {
        options.clear();
        buttonsPanel.removeAll();
        for (String text : buttonsText) {
            JButton button = new JButton(text);
            options.add(button);
            buttonsPanel.add(button);
            button.addActionListener(new BotListener());
        }

        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    /**
     * Encerra o jogo, solicitando o nome do jogador e salvando os resultados.
     * @param perguntasErradas Lista de perguntas que o jogador errou.
     * @param perguntasCertas Lista de perguntas que o jogador acertou.
     */
    private void encerrarJogo(ArrayList<Pergunta> perguntasErradas, ArrayList<Pergunta> perguntasCertas) {
        String nome = JOptionPane.showInputDialog("Jogo encerrado. Insira o seu nome:");
        if (nome != null && !nome.isEmpty()) {
            // Obter data e hora
            LocalDateTime currentDateTime = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = currentDateTime.format(formatter);

            Ficheiro.saveGame(formattedDateTime, nome, perguntasErradas, perguntasCertas);
        } else {
            JOptionPane.showMessageDialog(null, "Nome invalido", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Classe interna que implementa as acoes dos botoes na interface grafica durante o jogo.
     */
    private class BotListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JButton chosen = (JButton) e.getSource();
                String buttonText = chosen.getText();

                Pergunta currentQuestion = perguntas.get(index);
                if (currentQuestion.acertou(buttonText)) {
                    JOptionPane.showMessageDialog(null, "Acertou!");
                    acertou.add(currentQuestion);
                } else {
                    JOptionPane.showMessageDialog(null, "Errou!");
                    errou.add(currentQuestion);
                }

                index++;
                if (index < perguntas.size()) {
                    setupQuestion();
                } else {
                    JOptionPane.showMessageDialog(null, "Fim das perguntas!");
                    encerrarJogo(errou, acertou); //recebe primeiro as erradas
                    FinalGUI finalGUI = new FinalGUI();
                    finalGUI.setTitle("Final do Jogo");
                    finalGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    finalGUI.setSize(900, 600);
                    finalGUI.setLocationRelativeTo(null);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir janela", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
