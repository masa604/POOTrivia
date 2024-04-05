/**
 * A classe Natacao representa uma pergunta relacionada a natacao no jogo.
 * Natacao e uma subclasse da classe abstrata Desporto.
 */
public class Natacao extends Desporto {

    /** Opcao correta para a pergunta de natacao. */
    private String rightOption;

    /** Construtor vazio da pergunta de natacao. */
    public Natacao(){}

    /**
     * Construtor de Natacao com questao, pontos e opcao correta.
     * Adiciona 10 pontos se a pergunta for respondida corretamente.
     * @param questao A questao associada a pergunta.
     * @param questionPoints Pontos atribuidos a pergunta.
     * @param rightOption Opcao correta para a pergunta de natacao.
     */
    public Natacao(String questao, int questionPoints, String rightOption) {
        super(questao, questionPoints + 10);
        this.rightOption = rightOption;
        this.questionPoints += 10;
    }

    /**
     * Obtem a opcao correta para a pergunta de natacao.
     * @return A opcao correta.
     */
    public String getRightOption() {
        return rightOption;
    }

    /**
     * Define a opcao correta para a pergunta de natacao.
     * @param rightOption A nova opcao correta.
     */
    public void setRightOption(String rightOption) {
        this.rightOption = rightOption;
    }

    /**
     * Metodo protegido para verificar se a resposta escolhida pelo jogador esta correta, para a pergunta de natacao.
     * @param answer A resposta fornecida.
     * @return true se a resposta estiver correta, false caso contrario.
     */
    @Override
    protected boolean acertou(String answer){
        return answer.equalsIgnoreCase(this.rightOption);
    }
}
