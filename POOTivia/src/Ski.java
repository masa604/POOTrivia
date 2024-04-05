/**
 * A classe Ski representa uma pergunta relacionada a ski no jogo.
 * Ski e uma subclasse da classe Desporto.
 */
public class Ski extends Desporto {

    /** Opcao correta para a pergunta de ski. */
    private String rightOption;

    /** Construtor vazio da pergunta de ski. */
    public Ski(){}

    /**
     * Construtor de ski com questao, pontos e opcao correta.
     * Multiplica os pontos por 2 se a pergunta for respondida corretamente.
     * @param questao A questao associada a pergunta.
     * @param questionPoints Pontos atribuidos a pergunta.
     * @param rightOption Opcao correta para a pergunta de ski.
     */
    public Ski(String questao, int questionPoints, String rightOption) {
        super(questao, questionPoints * 2);
        this.rightOption = rightOption;
        this.questionPoints *= 2;
    }

    /**
     * Obtem a opcao correta para a pergunta de ski.
     * @return A opcao correta.
     */
    public String getRightOption() {
        return rightOption;
    }

    /**
     * Define a opcao correta para a pergunta de ski.
     * @param rightOption A nova opcao correta.
     */
    public void setRightOption(String rightOption) {
        this.rightOption = rightOption;
    }

    /**
     * Metodo protegido para verificar se a resposta escolhida pelo jogador esta correta, para a pergunta de ski.
     * @param answer A resposta fornecida.
     * @return true se a resposta estiver correta, false caso contrario.
     */
    @Override
    protected boolean acertou(String answer){
        return answer.equalsIgnoreCase(this.rightOption);
    }
}
