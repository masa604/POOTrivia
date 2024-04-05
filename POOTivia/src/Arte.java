import java.util.ArrayList;
import java.util.Collections;

/**
 * A classe Arte representa uma pergunta relacionada a arte no jogo.
 * Arte e uma subclasse da classe abstrata Pergunta.
 */
public class Arte extends Pergunta {

    /** Opcoes de resposta associadas a pergunta. */
    private ArrayList<String> questionOptions;

    /** Opcao correta da pergunta. */
    private String rightOption;

    /** Construtor vazio de Arte. */
    public Arte(){}

    /**
     * Construtor de Arte com questao, pontos, opcoes de resposta e opcao correta.
     * Multiplica os pontos por 10 se a pergunta for respondida corretamente.
     * @param questao A questao associada a pergunta.
     * @param questionPoints Pontos atribuídos a pergunta.
     * @param questionOptions Opcoes de resposta associadas a pergunta.
     * @param rightOption Opcao correta para a pergunta.
     */
    public Arte(String questao, int questionPoints, ArrayList<String> questionOptions, String rightOption) {
        super(questao, questionPoints);
        this.questionOptions = questionOptions;
        this.rightOption = rightOption;
        this.questionPoints *= 10;
    }

    /**
     * Obtem as opcoes de resposta associadas a pergunta de arte.
     * @return As opcoes de resposta.
     */
    public ArrayList<String> getQuestionOptions() {
        return questionOptions;
    }

    /**
     * Define as opcoes de resposta associadas a pergunta de arte.
     * @param questionOptions As novas opcoes de resposta.
     */
    public void setQuestionOptions(ArrayList<String> questionOptions) {
        this.questionOptions = questionOptions;
    }

    /**
     * Obtem a opcao correta para a pergunta de arte.
     * @return A opcao correta.
     */
    public String getRightOption() {
        return rightOption;
    }

    /**
     * Define a opcao correta para a pergunta de arte.
     * @param rightOption A nova opcao correta.
     */
    public void setRightOption(String rightOption) {
        this.rightOption = rightOption;
    }

    /**
     * Metodo protegido para calcular e definir as opcoes de resposta da pergunta a serem exibidas.
     * Se o indice da pergunta for inferior a 3, apresenta apenas 3 opcoes, caso contrario, apresenta todas as opcoes.
     */
    protected void questionOptionsToShow(){
        ArrayList<String> copyOptions = new ArrayList<>(this.questionOptions);
        Collections.shuffle(copyOptions);
        copyOptions.remove(this.rightOption);
        if(this.numeropergunta < 3 && copyOptions.size() >= 2){
            this.optionsToShow = new ArrayList<>(copyOptions.subList(0, 2)); // Selecionamos 2 opcoes aleatórias.
            this.optionsToShow.add(this.rightOption);
        } else {
            this.optionsToShow = questionOptions;
        }
    }

    /**
     * Metodo protegido para verificar se a resposta escolhida pelo jogador esta correta, para a pergunta de arte.
     * @param answer A resposta fornecida.
     * @return true se a resposta estiver correta, false caso contrario.
     */
    protected boolean acertou(String answer){
        return answer.equalsIgnoreCase(this.rightOption);
    }
}
