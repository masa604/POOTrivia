import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A classe FinalGUI representa a interface grafica exibida no final do jogo,
 * mostrando o TOP3 e oferecendo a opcao de jogar novamente ou sair do programa.
 */
public class FinalGUI extends JFrame {
    /** Painel principal da interface grafica. */
    private JPanel panel;

    /** Painel para exibir o rotulo "TOP3 GAMES:". */
    private JLabel finalLabel;

    /** Botao para iniciar um novo jogo. */
    private JButton jogarDeNovo;

    /** Botao para sair do programa. */
    private JButton sair;

    /** Painel para conter os botoes de jogar novamente e sair. */
    private JPanel buttonsPanel;

    /** Painel para exibir as informacoes do top 3. */
    private JPanel panelTop;

    /** Arraylist de JLabels para os tres melhores jogos. */
    private ArrayList<JLabel> top3Labels;

    /**
     * Construtor da classe FinalGUI que inicializa os componentes da interface grafica
     * e configura a exibicao do top 3.
     */
    public FinalGUI() {
        jogarDeNovo = new JButton("Jogar de Novo.");
        sair = new JButton("Sair do programa.");

        BotListener botListener = new BotListener();
        jogarDeNovo.addActionListener(botListener);
        sair.addActionListener(botListener);

        finalLabel = new JLabel("TOP3 GAMES:");
        finalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        buttonsPanel = new JPanel();
        buttonsPanel.add(jogarDeNovo);
        buttonsPanel.add(sair);

        ArrayList<String> top3Info = Ficheiro.calcularTOP3();

        panelTop = new JPanel();

        top3Labels = new ArrayList<>(); // Array de JLabels para os três melhores jogos
        for (int i = 0; i < top3Info.size(); i++) {
            top3Labels.add(new JLabel());
            top3Labels.get(i).setText(top3Info.get(i)); // Define o texto para cada JLabel
            panelTop.add(top3Labels.get(i));
        }

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(finalLabel);
        panel.add(panelTop);
        panel.add(buttonsPanel);

        add(panel);
        setVisible(true);
    }

    /**
     * Classe interna que implementa as acoes dos botoes na interface grafica no final do jogo.
     */
    private class BotListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == jogarDeNovo) {
                    new Jogo();
                } else if (e.getSource() == sair) {
                    JOptionPane.showMessageDialog(null, "A sair...");
                    System.exit(0);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir janela", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
