import java.io.Serializable;
import java.util.ArrayList;

/**
 * A classe abstrata Pergunta representa uma pergunta no jogo.
 * Implementa a interface Serializable para suportar serializacao.
 */
public abstract class Pergunta implements Serializable {

    /** A questao associada a pergunta, que corresponde a linha escolhida. */
    protected String questao;

    /** Pontos atribuidos a pergunta. Sao alterados em cada subclasse. */
    protected int questionPoints;

    /**
     * Numero da pergunta representa o indice. definido ao ler a pergunta do ficheiro.
     */
    protected int numeropergunta;

    /** Opcoes de resposta associadas a pergunta. */
    protected ArrayList<String> optionsToShow;

    /** Construtor da pergunta. */
    public Pergunta(){}

    /**
     * Construtor da pergunta com questao e pontos.
     * @param questao A questao associada a pergunta.
     * @param questionPoints Pontos atribuidos a pergunta.
     */
    public Pergunta(String questao, int questionPoints) {
        this.questao = questao;
        this.questionPoints = questionPoints;
    }

    /**
     * Obtem a questao associada a pergunta.
     * @return A questao da pergunta.
     */
    public String getQuestao() {
        return questao;
    }

    /**
     * Define a questao associada a pergunta.
     * @param questao A nova questao da pergunta.
     */
    public void setQuestao(String questao) {
        this.questao = questao;
    }

    /**
     * Obtem os pontos atribuidos a pergunta.
     * @return Os pontos da pergunta.
     */
    public int getQuestionPoints() {
        return questionPoints;
    }

    /**
     * Define os pontos atribuidos a pergunta.
     * @param questionPoints Os novos pontos da pergunta.
     */
    public void setQuestionPoints(int questionPoints) {
        this.questionPoints = questionPoints;
    }

    /**
     * Obtem o numero da pergunta.
     * @return O numero da pergunta.
     */
    public int getNumeropergunta() {
        return numeropergunta;
    }

    /**
     * Define o numero da pergunta.
     * @param numeropergunta O novo numero da pergunta.
     */
    public void setNumeropergunta(int numeropergunta) {
        this.numeropergunta = numeropergunta;
    }

    /**
     * Obtem as opcoes associadas a pergunta.
     * @return As opcoes da pergunta.
     */
    public ArrayList<String> getOptionsToShow() {
        return optionsToShow;
    }

    /**
     * Define as opcoes associadas a pergunta.
     * @param optionsToShow As novas opcoes da pergunta.
     */
    public void setOptionsToShow(ArrayList<String> optionsToShow) {
        this.optionsToShow = optionsToShow;
    }

    /**
     * Metodo abstrato para calcular e definir as opcoes de resposta da pergunta a serem exibidas.
     */
    protected abstract void questionOptionsToShow();

    /**
     * Metodo abstrato para verificar se a resposta escolhida pelo jogador esta correta.
     * @param answer A resposta fornecida.
     * @return true se a resposta estiver correta, false caso contrario.
     */
    protected abstract boolean acertou(String answer);
}
