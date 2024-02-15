import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Graph {
    //Com o intuito de facilitar a interpretação da matriz de adjacência do grafo em questão, consideremos o primeiro vértice como sendo o
    //vértice 0.

    //Variável que guardará a dimensão da matriz de adjacência do grafo em questão.
    //Tal dimensão está guardada na primeira linha do arquivo Graph.txt.
    public static int dimensãoMatriz; //A dimensão da matriz de adjacência representa também o número de vértices do grafo em questão.

    //Variável que guardará a matriz de adjacência do grafo em questão.
    public static int[][] matrizAdjacencia;

    //Variável que guardará a lista de vértices que contém a cor de cada um dos vértices do grafo em questão.
    public static char[] listaVertices;

    public static void populaMatrizAdjacencia(String linha, int i){
        for(int j = 0; j < dimensãoMatriz; j++){
            //Para cada linha, todos os caracteres são lidos e guardados na matriz de adjacência.
            matrizAdjacencia[i][j] = Character.getNumericValue(linha.charAt(j));
        }
    }

    public static void leArquivo(){
        //Variável que guardará o nome do arquivo a ser lido.
        String nomeArquivo = "Graph.txt";

        //Cria um objeto do tipo File (arquivo) com o nome do arquivo a ser lido.
        File arquivo = new File(nomeArquivo);

        try{
            //Cria um objeto do tipo FileReader (leitor de arquivo) para ler o arquivo em questão.
            FileReader leitor = new FileReader(arquivo);

            //Cria um objeto do tipo BufferedReader para ler linhas de texto eficientemente.
            BufferedReader bufferedReader = new BufferedReader(leitor);

            //Lê a primeira linha do arquivo em questão.
            String linha = bufferedReader.readLine();

            //Guardo na variável dimensãoMatriz o valor da primeira linha do arquivo Graph.txt.
            dimensãoMatriz = Integer.parseInt(linha);

            //Crio a matriz de adjacência do grafo.
            matrizAdjacencia = new int[dimensãoMatriz][dimensãoMatriz];

            //Crio uma variável para manter o controle da linha atual
            int i = 0;
            //Lê as demais linhas do arquivo em questão.
            while((linha = bufferedReader.readLine()) != null) {
                populaMatrizAdjacencia(linha, i);
                //Atualizo o número da linha atual.
                i += 1;
            }

            //Fechando o BufferedReader.
            bufferedReader.close();

            
        } catch(IOException e) {
            //A classe IOException é usada para lidar com exceções (erros) durante operações de
            //entrada e saída em java. Como por exemplo, quando o programa tenta abrir um arquivo
            //que não existe ou quando um erro de leitura ou escrita de dados ocorre.

            //A variável "e" representa a exceção ocorrida. Mais especificamente, "e" é uma instância
            //da classe IOException.

            //O método printStackTrace() é utilizado para imprimir a pilha de chamadas da exceção no console.
            //Isso é útil pois tal operação fornece informações sobre onde e porque a exceção ocorreu.
            e.printStackTrace();
        }
    }

    public static void criaMatrizAdjacencia(){
        //Chama a função leArquivo que por sua vez tem embutida em si a função populaMatrizAdjacencia.
        leArquivo();
    }

    public static void mostraMatrizAdjacencia(){
        StringBuilder resultado = new StringBuilder("");
        for(int i = 0; i < dimensãoMatriz; i++){
            for(int j = 0; j < dimensãoMatriz; j++){
                resultado.append(matrizAdjacencia[i][j] + " ");
            }
            resultado.append("\n");
        }
        System.out.println(resultado.toString());
    }

    public static void criaListaVertices(){
        /*
         * Essa função criará uma lista de caracteres 'V', 'A' e 'N' onde os caracteres 'V' representam a cor vermelha, os caracteres 'A' 
         * representam a cor azul e os caracteres N representam a cor nula (sem cor).
         * 
         * Tal lista de caracteres deverá ser interpretada da seguinte forma, o caracter que estiver na posição i indicará a cor do vértice i
         * do grafo em questão. Por exemplo, suponha um grafo com 5 vértices tal que a lista de vértices desse grafo seja a seguinte:
         * ['V','N','A','V','A'], isto quer dizer que, os vértices 0 e 3 possuem a cor vermelha, o vértice 4 possui a cor azul e o vértice 2
         * não possui cor alguma.
         * 
         * De início, a lista de vértices será criada de forma que todos os vértices do grafo em questão não possuam nenhuma cor.
        */

        listaVertices = new char[dimensãoMatriz];
        for(int i = 0; i < dimensãoMatriz; i++){
            listaVertices[i] = 'N';
        }
    }

    public static void mostraListaVertices(){
        StringBuilder result = new StringBuilder("");
        for(int i = 0; i < dimensãoMatriz; i++){
            result.append(listaVertices[i] + "\t");
        }
        System.out.println(result.toString());
    }

    public static void ativaModoIterativo(){
        //Crio um objeto do tipo Scanner para ler da entrada padrão.
        Scanner scanner = new Scanner(System.in);

        //Pergunto ao usuário quantos vértices ele deseja colorir.
        System.out.println("Dado que existem " + dimensãoMatriz + " vértices, quantos você deseja colorir ?");
        int numeroVerticesColoridos = scanner.nextInt();

        for(int i = 0; i < numeroVerticesColoridos; i++){
            //Pergunto ao usuário qual vértice ele deseja colorir.
            System.out.println("Qual vértice você deseja colorir ?");
            int vertice = scanner.nextInt();

            //Pergunto ao usuário qual cor o vértice que ele deseja colorir deverá ter.
            System.out.println("O vértice em questão terá cor vermelha (V) ou cor azul (A) ?");
            char cor = scanner.next().charAt(0);

            //Adiciono a cor selecionada ao vértice selecionado.
            listaVertices[vertice] = cor;
        }
        
        scanner.close();
    }

    public static boolean verificaVizinho(int verticeI, int verticeJ){
        //Verifica se o vértice i é vizinho do vértice j, isto é, verifica se o vértice i possui uma aresta até o vértice j.
        if(matrizAdjacencia[verticeI][verticeJ] == 1) return true;
        else return false;
    }

    public static void main(String[] args){
        
        //Crio a matriz de adjacencia e a lista de vértices baseado no arquivo Graph.txt.
        criaMatrizAdjacencia();
        criaListaVertices();

        //Ativo o modo iterativo para saber quais vértices o usuário quer colorir inicialmente.
        System.out.println();
        ativaModoIterativo();
        System.out.println();

        //mostraMatrizAdjacencia();
        mostraListaVertices();
        
    }
}
