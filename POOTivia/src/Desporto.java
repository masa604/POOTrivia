import java.util.ArrayList;

/**
 * A classe Desporto representa uma pergunta relacionada a desporto no jogo.
 * Desporto e uma subclasse da classe abstrata Pergunta.
 */
public class Desporto extends Pergunta {

    /** Construtor vazio da pergunta de desporto. */
    public Desporto(){}

    /**
     * Construtor de Desporto com questao e pontos.
     * Adiciona 3 pontos se a pergunta de uma categoria de desporto for respondida corretamente.
     * @param questao A questao associada a pergunta.
     * @param questionPoints Pontos atribuidos a pergunta.
     */
    public Desporto(String questao, int questionPoints) {
        super(questao, questionPoints + 3);
        this.questionPoints += 3;
    }

    /**
     * Metodo protegido para definir as opcoes de resposta da pergunta de desporto a serem exibidas.
     * As opcoes definidas sao Verdadeiro e Falso.
     */
    protected void questionOptionsToShow(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Verdadeiro");
        options.add("Falso");
        this.optionsToShow = options;
    }

    /**
     * Metodo protegido para verificar se a resposta escolhida pelo jogador esta correta, para a pergunta de desporto.
     * @param answer A resposta fornecida.
     * @return Retorna sempre false, pois a pergunta de desporto nao tem resposta correta definida.
     */
    protected boolean acertou(String answer){
        return false;
    }
}
