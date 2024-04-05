import java.util.ArrayList;
import java.util.Collections;

/**
 * A classe Futebol representa uma pergunta relacionada a futebol no jogo.
 * Futebol e uma subclasse da classe Desporto.
 */
class Futebol extends Desporto {

    /** Lista de nomes de jogadores de futebol associados a pergunta de futebol. */
    private ArrayList<String> nomes;

    /** Lista de numeros de jogadores de futebol associados a pergunta de futebol. */
    private ArrayList<String> numeros;

    /** Opcao correta para a pergunta relacionada aos nomes. */
    private String rightOptionNome;

    /** Opcao correta para a pergunta relacionada aos numeros. */
    private String rightOptionNumero;

    /** Construtor vazio de Futebol. */
    public Futebol(){};

    /**
     * Construtor de Futebol com questao, pontos, listas de nomes e numeros,
     * e opcoes corretas para nomes e numeros.
     * Adiciona 1 ponto se a pergunta for respondida corretamente.
     * @param questao A questao associada a pergunta.
     * @param questionPoints Pontos atribuidos a pergunta.
     * @param nomes Lista de nomes associados a pergunta de futebol.
     * @param numeros Lista de numeros associados a pergunta de futebol.
     * @param rightOptionNome Opcao correta para a pergunta relacionada aos nomes.
     * @param rightOptionNumero Opcao correta para a pergunta relacionada aos numeros.
     */
    public Futebol(String questao, int questionPoints, ArrayList<String> nomes, ArrayList<String> numeros, String rightOptionNome, String rightOptionNumero) {
        super(questao, questionPoints + 1);
        this.nomes = nomes;
        this.numeros = numeros;
        this.rightOptionNome = rightOptionNome;
        this.rightOptionNumero = rightOptionNumero;
    }

    /**
     * Obtem a lista de nomes de jogadores associada a pergunta de futebol.
     * @return A lista de nomes.
     */
    public ArrayList<String> getNomes() {
        return nomes;
    }

    /**
     * Define a lista de nomes de jogadores associada a pergunta de futebol.
     * @param nomes A nova lista de nomes.
     */
    public void setNomes(ArrayList<String> nomes) {
        this.nomes = nomes;
    }

    /**
     * Obtem a lista de numeros de jogadores associada a pergunta de futebol.
     * @return A lista de numeros.
     */
    public ArrayList<String> getNumeros() {
        return numeros;
    }

    /**
     * Define a lista de numeros de jogadores associada a pergunta de futebol.
     * @param numeros A nova lista de numeros.
     */
    public void setNumeros(ArrayList<String> numeros) {
        this.numeros = numeros;
    }

    /**
     * Metodo protegido para calcular e definir as opcoes de resposta da pergunta a serem exibidas.
     * Se o indice da pergunta for inferior a 3, apresenta a lista de nomes como opcoes, caso contrario, a lista de numeros como opcoes.
     */
    @Override
    protected void questionOptionsToShow() {
        if(this.numeropergunta < 3){
            Collections.shuffle(this.nomes);
            this.optionsToShow = this.nomes;
        } else {
            Collections.shuffle(this.numeros);
            this.optionsToShow = this.numeros;
        }
    }

    /**
     * Metodo protegido para verificar se a resposta escolhida pelo jogador esta correta, para a pergunta de futebol.
     * Verifica se a resposta coincide com a opcao correta relacionada aos nomes ou aos numeros.
     * @param answer A resposta fornecida.
     * @return true se a resposta estiver correta, false caso contrario.
     */
    @Override
    protected boolean acertou(String answer){
        return answer.equalsIgnoreCase(this.rightOptionNome) || answer.equalsIgnoreCase(this.rightOptionNumero);
    }
}
