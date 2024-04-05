import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A classe Ficheiro contem metodos para manipulacao de ficheiros, como carregar perguntas, salvar jogos,
 * listar jogos anteriores, calcular pontos de jogos e determinar o top 3 de jogos anteriores.
 */
public class Ficheiro {
    /**
     * Carrega as perguntas de um ficheiro e adiciona a lista de perguntas.
     * @param nomeFile O nome do ficheiro que contem as perguntas.
     * @param listaPerguntas A lista de perguntas para adicionar as perguntas do ficheiro.
     */
    public static void loadPerguntas(String nomeFile, ArrayList<Pergunta> listaPerguntas) {
        File f = new File(nomeFile);
        if(f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] elementos = linha.split("\\.");
                    String categoria = elementos[0];
                    String pergunta = elementos[1];

                    if (categoria.equalsIgnoreCase("arte")) {
                        if (elementos.length < 7 || elementos.length > 12) {
                            JOptionPane.showMessageDialog(null, "Pergunta mal formatada: " + linha, "Erro!", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        int numOpcoes = elementos.length - 2;
                        String[] opcoes = new String[numOpcoes];

                        for (int i = 0; i < numOpcoes; i++) {
                            opcoes[i] = elementos[i+2];
                        }

                        String respostaCerta = opcoes[0];
                        Pergunta p = new Arte(pergunta, 5, new ArrayList<>(Arrays.asList(opcoes)), respostaCerta);
                        listaPerguntas.add(p);
                    } else if (categoria.equalsIgnoreCase("ciencias")) {
                        if (elementos.length<12 || elementos.length>22) {
                            JOptionPane.showMessageDialog(null, "Pergunta mal formatada: " + linha, "Erro!", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        int numOpcoes = elementos.length - 2;
                        int numFaceis = numOpcoes/2;
                        int numDificeis = numOpcoes - numFaceis;
                        String[] opcoesDificeis = new String[numDificeis];
                        String[] opcoesFaceis = new String[numFaceis];

                        for (int i = 0; i < numOpcoes; i++) {
                            if (i<numFaceis) {
                                opcoesFaceis[i] = elementos[i+2];
                            } else {
                                opcoesDificeis[i-numFaceis] = elementos[i+2];
                            }
                        }
                        String respostaCerta = opcoesFaceis[0]; //igual pra ambas as listas
                        Pergunta p = new Ciencias(pergunta, 5 ,new ArrayList<>(Arrays.asList(opcoesFaceis)), new ArrayList<>(Arrays.asList(opcoesDificeis)), respostaCerta);
                        listaPerguntas.add(p);

                    } else if (categoria.equals("futebol")) {
                        if (elementos.length<12 || elementos.length>22) {
                            JOptionPane.showMessageDialog(null, "Pergunta mal formatada: " + linha, "Erro!", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        int numOpcoes = elementos.length - 2;
                        int numNomes = numOpcoes/2;
                        int numNumeros = numOpcoes/2;
                        String[] nomes = new String[numNomes];
                        String[] numeros = new String[numNumeros];

                        for (int i = 0; i < numOpcoes; i++) {
                            if (i<numNomes) {
                                nomes[i] = elementos[i+2];
                            } else {
                                numeros[i-numNomes] = elementos[i+2];
                            }
                        }

                        String respostaCertaN = nomes[0];
                        String respostaCertaNum = numeros[0];
                        Pergunta p = new Futebol(pergunta, 5, new ArrayList<>(Arrays.asList(nomes)), new ArrayList<>(Arrays.asList(numeros)), respostaCertaN, respostaCertaNum);
                        listaPerguntas.add(p);
                    }else if ((categoria.equalsIgnoreCase("natacao")) ){
                        String respostaCerta = elementos[2];
                        Pergunta p = new Natacao(pergunta,5,respostaCerta);
                        listaPerguntas.add(p);
                    } else if ((categoria.equalsIgnoreCase("ski"))) {
                        String respostaCerta = elementos[2];
                        Pergunta p = new Ski(pergunta,5,respostaCerta);
                        listaPerguntas.add(p);
                    }

                }
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro nao existe." + f.getAbsolutePath());
        }
    }


    /**
     * Escolhe perguntas aleatorias da lista de perguntas.
     * @param listaPerguntas A lista de perguntas disponiveis.
     * @return Uma lista de perguntas escolhidas para o jogo.
     */
    public static ArrayList<Pergunta> escolhePerguntas(ArrayList<Pergunta> listaPerguntas) {
        ArrayList<Pergunta> perguntasJogo = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        if (listaPerguntas.size()<5) {
            JOptionPane.showMessageDialog(null, "Nao ha perguntas suficientes para jogar.");
            GUI.fecharPrograma();
        }
        while (indices.size() < 5) {
            int n = (int) (Math.random() * listaPerguntas.size());
            if (!indices.contains(n)) {
                indices.add(n);
            }
        }

        for (int i = 0; i < 5; i++) {
            int index = indices.get(i);
            Pergunta element = listaPerguntas.get(index);
            element.setNumeropergunta(i+1);
            element.questionOptionsToShow();
            perguntasJogo.add(element);
        }

        if (perguntasJogo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro ao escolher perguntas para o jogo.");
        }
        return perguntasJogo;
    }

    /**
     * Define o nome do ficheiro de objetos com base na data, hora e nome do jogador.
     * @param dateTime A data e hora do jogo.
     * @param jogador O nome do jogador.
     * @return O nome do ficheiro de objetos.
     */
    private static String defineFileObjName(String dateTime ,String jogador ){
        String nomeFile = "pootrivia_jogo_";
        String[] nameParts = jogador.split(" ");
        String initials = "";
        for (String part: nameParts){
            if(!part.isEmpty()){
                initials = initials + part.charAt(0);
            }
        }
        initials = initials.toUpperCase();
        String[] dateTimeParts = dateTime.split(" ");
        String[] date = dateTimeParts[0].split("-");
        String[] time = dateTimeParts[1].split(":");
        for (String part: date){
            if(!part.isEmpty()){
                nomeFile += part;
            }
        }
        for (String part: time){
            if(!part.isEmpty()){
                nomeFile += part;
            }
        }
        nomeFile = nomeFile + "_" + initials + ".dat";
        System.out.println(nomeFile);
        return nomeFile;
    }

    /**
     * Guarda o jogo num ficheiro de objetos.
     * @param dateTime A data e hora do jogo.
     * @param nomeJogador O nome do jogador.
     * @param perguntasErradas A lista de perguntas erradas no jogo.
     * @param perguntasCertas A lista de perguntas certas no jogo.
     */
    public static void saveGame(String dateTime , String nomeJogador ,ArrayList<Pergunta> perguntasErradas, ArrayList<Pergunta> perguntasCertas){
        String nomeFile = defineFileObjName(dateTime,nomeJogador);
        File f = new File(nomeFile);
        try{
            FileOutputStream fos =  new FileOutputStream(f);
            ObjectOutputStream oos =  new ObjectOutputStream(fos);

            //ordem de escrita: string datatempo, string nome jogador, lista pergunta certas, lista pergunta erradas
            oos.writeObject(dateTime);
            oos.writeObject(nomeJogador);
            oos.writeObject(perguntasCertas);
            oos.writeObject(perguntasErradas);

            oos.close();
        }catch (FileNotFoundException ex){
            System.out.println("Erro ao criar ficheiro.");
        }catch (IOException ex){
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    /**
     * Lista todos os jogos existentes no diretorio atual.
     * @return Um array de strings que contem os nomes dos ficheiros de jogos existentes.
     */
    private static String[] listAllGamesCurrentDic() {
        String[] gameFiles = new String[0];
        try {
            File path = new File(System.getProperty("user.dir"));
            //criamos um filtro
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File path, String name) {
                    return name.startsWith("pootrivia_jogo");
                }
            };

            gameFiles = path.list(filter);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return gameFiles;
    }

    /**
     * Carrega os pontos de um jogo a partir do ficheiro de objetos fornecido.
     * @param fileName O nome do ficheiro de objetos do jogo.
     * @return Os pontos obtidos no jogo.
     */
    private static int loadGamesPoints(String fileName) {
        File f = new File(fileName);
        int gamePoints = 0;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ois.readObject();
            ois.readObject();
            ArrayList<Pergunta> perguntasCertas = (ArrayList<Pergunta>) ois.readObject();

            gamePoints = gamePoints(perguntasCertas);

            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao abrir o ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro ao ler ficheiro.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
        return gamePoints;
    }

    /**
     * Carrega informacoes de um jogo a partir do ficheiro de objetos fornecido.
     * @param fileName O nome do ficheiro de objetos do jogo.
     * @return As informacoes do jogo (jogador, pontuacao e data e hora do jogo).
     */
    public static String loadGamesInfo(String fileName) {
        String info = "";
        File f = new File(fileName);
        int gamePoints = 0;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            String dateTime = (String) ois.readObject();
            String nomeJogador = (String) ois.readObject();

            ArrayList<Pergunta> perguntasCertas = (ArrayList<Pergunta>) ois.readObject();

            gamePoints = gamePoints(perguntasCertas);
            info += "Jogador: " + nomeJogador +" |  Pontuacao obtida: " + gamePoints + " |  Data e hora do jogo: "+ dateTime + "\n";


            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao abrir o ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro ao ler ficheiro.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
        return info;
    }

    /**
     * Calcula os pontos totais obtidos num jogo com base na lista de perguntas certas.
     * @param perguntasCertas A lista de perguntas certas no jogo.
     * @return Os pontos totais obtidos no jogo.
     */
    private static int gamePoints(ArrayList<Pergunta> perguntasCertas){
        int points = 0;
        for(Pergunta p: perguntasCertas){
            points += p.getQuestionPoints();
        }
        return points;
    }

    /**
     * Calcula o top 3 de jogos existentes com base nos pontos obtidos em cada jogo.
     * @return Uma lista de strings contendo informacoes sobre o top 3 de jogos anteriores.
     */
    public static ArrayList<String> calcularTOP3(){
        String[] listGames = Ficheiro.listAllGamesCurrentDic();

        ArrayList<String> top3Games = new ArrayList<>(); // Para armazenar os top 3 jogos
        ArrayList<Integer> top3Points = new ArrayList<>(); // Para armazenar os pontos correspondentes

        for (String file : listGames) {
            int points = Ficheiro.loadGamesPoints(file); //vai calculando os pontos

            // Verifica se o jogo tem pontos suficientes para estar no top 3
            if (top3Points.size() < 3 || points > top3Points.get(2)) {
                // Encontra a posicao correta para inserir os novos pontos e nome do jogo
                int i = 0;
                while (i < top3Points.size() && points <= top3Points.get(i)) {
                    i++;
                }

                // Insere os novos pontos e nome do jogo na posicao correta
                top3Points.add(i, points);
                top3Games.add(i, file);

                // Remove o jogo com o menor número de pontos caso haja mais que 3
                if (top3Points.size() > 3) {
                    top3Points.remove(3);
                    top3Games.remove(3);
                }
            }
        }
        ArrayList<String> top3Info = new ArrayList<>();
        for (String file : top3Games) {
            String gameInfo = Ficheiro.loadGamesInfo(file); // Carregar informacoes do jogo
            top3Info.add(gameInfo);
        }

        return top3Info;
    }
}
