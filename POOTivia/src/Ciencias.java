import java.util.ArrayList;
import java.util.Collections;

/**
 * A classe Ciencias representa uma pergunta relacionada a ciencias no jogo.
 * Ciencias e uma subclasse da classe abstrata Pergunta.
 */
public class Ciencias extends Pergunta {

    /** Opcoes de resposta faceis associadas a pergunta. */
    private ArrayList<String> easyOptions;

    /** Opcoes de resposta dificeis associadas a pergunta. */
    private ArrayList<String> hardOptions;

    /** Opcao correta para a pergunta. */
    private String rightOption;

    /** Construtor vazio de Ciencias. */
    public Ciencias(){}

    /**
     * Construtor de Ciencias com questao, pontos, opcoes faceis, opcoes dificeis e opcao correta.
     * Adiciona 5 pontos se a pergunta for respondida corretamente.
     * @param questao A questao associada a pergunta.
     * @param questionPoints Pontos atribuidos a pergunta.
     * @param easyOptions Opcoes de resposta faceis associadas a pergunta.
     * @param hardOptions Opcoes de resposta dificeis associadas a pergunta.
     * @param rightOption Opcao correta para a pergunta.
     */
    public Ciencias(String questao, int questionPoints, ArrayList<String> easyOptions, ArrayList<String> hardOptions, String rightOption) {
        super(questao, questionPoints);
        this.easyOptions = easyOptions;
        this.hardOptions = hardOptions;
        this.rightOption = rightOption;
        this.questionPoints += 5; // Atribui pontos adicionais se a pergunta for respondida corretamente.
    }

    /**
     * Obtem as opcoes de resposta faceis associadas a pergunta de ciencias.
     * @return As opcoes de resposta faceis.
     */
    public ArrayList<String> getEasyOptions() {
        return easyOptions;
    }

    /**
     * Define as opcoes de resposta faceis associadas a pergunta de ciencias.
     * @param easyOptions As novas opcoes de resposta faceis.
     */
    public void setEasyOptions(ArrayList<String> easyOptions) {
        this.easyOptions = easyOptions;
    }

    /**
     * Obtem as opcoes de resposta dificeis associadas a pergunta de ciencias.
     * @return As opcoes de resposta dificeis.
     */
    public ArrayList<String> getHardOptions() {
        return hardOptions;
    }

    /**
     * Define as opcoes de resposta dificeis associadas a pergunta de ciencias.
     * @param hardOptions As novas opcoes de resposta dificeis.
     */
    public void setHardOptions(ArrayList<String> hardOptions) {
        this.hardOptions = hardOptions;
    }

    /**
     * Obtem a opcao correta para a pergunta de ciencias.
     * @return A opcao correta.
     */
    public String getRightOption() {
        return rightOption;
    }

    /**
     * Define a opcao correta para a pergunta de ciencias.
     * @param rightOption A nova opcao correta.
     */
    public void setRightOption(String rightOption) {
        this.rightOption = rightOption;
    }

    /**
     * Metodo protegido para calcular e definir as opcoes de resposta da pergunta a serem exibidas.
     * Se o indice da pergunta for superior a 3, apresenta apenas opcoes dificeis, caso contrario, apresenta apenas opcoes faceis.
     */
    protected void questionOptionsToShow(){
        if(this.numeropergunta > 3){
            Collections.shuffle(this.hardOptions);
            this.optionsToShow = this.hardOptions;
        } else {
            Collections.shuffle(this.easyOptions);
            this.optionsToShow = this.easyOptions;
        }
    }

    /**
     * Metodo protegido para verificar se a resposta escolhida pelo jogador esta correta, para a pergunta de ciencias.
     * @param answer A resposta fornecida.
     * @return true se a resposta estiver correta, false caso contrario.
     */
    protected boolean acertou(String answer){
        return answer.equalsIgnoreCase(this.rightOption);
    }
}
