import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

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

    //Variável que determinará a probabilidade de que um dado vértice desligado entre em consenso a cada rodada.
    public static int probabilidadeConsenso;

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

        System.out.println("Qual é a probabilidade de consenso desejada ? (1 = 10%, 2 = 20%, ... , 10 = 100%)");
        probabilidadeConsenso = scanner.nextInt();
        System.out.println();

        //Pergunto ao usuário quantos vértices ele deseja colorir.
        System.out.println("Dado que existem " + dimensãoMatriz + " vértices, quantos você deseja colorir ?");
        int numeroVerticesColoridos = scanner.nextInt();
        System.out.println();

        for(int i = 0; i < numeroVerticesColoridos; i++){
            //Pergunto ao usuário qual vértice ele deseja colorir.
            System.out.println("Qual vértice você deseja colorir ?");
            int vertice = scanner.nextInt();
            System.out.println();

            //Pergunto ao usuário qual cor o vértice que ele deseja colorir deverá ter.
            System.out.println("O vértice em questão terá cor vermelha (V) ou cor azul (A) ?");
            char cor = scanner.next().charAt(0);
            System.out.println();

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

    public static boolean existemDesligados(){

        for(int vertice = 0; vertice < dimensãoMatriz; vertice++){ 
            //Caso encontre algum vértice com a cor nula 'N', então existe pelo menos um vértice desligado.
            if(listaVertices[vertice] == 'N') return true;
        }  
        //Caso encontre não encontre nenhum vértice com a cor nula 'N', então não existem vértices desligados.
        return false;
    }

    public static void procuraDesligados(){
        for(int vertice = 0; vertice < dimensãoMatriz; vertice++){
            //Caso encontre algum vértice desligado, ou seja, algum vértice sem cor, então o algoritmo de consenso é rodado nesse vértice.
            if(listaVertices[vertice] == 'N') consenso(vertice);
        }
    }

    public static void consenso(int vertice){
        int A = 0; int V = 0;
        for(int i = 0; i < dimensãoMatriz; i++){
            if(matrizAdjacencia[vertice][i] == 1){
                //Caso o fluxo de execução chegue até aqui, então o vértice i é vizinho do vértice recebido como parâmetro.
                if(listaVertices[i] == 'A') A++; //Verifica se a cor do vizinho atual é azul.
                if(listaVertices[i] == 'V') V++; //Verifica se a cor do vizinho atual é vermelha.
            }
        }
        //Vejo qual das duas cores aparece mais vezes dentre os vizinhos do vértice recebido como parâmetro.
        char corDominante;
        if(A > V) corDominante = 'A';
        else if (A < V) corDominante = 'V';
        else corDominante = 'N';
        //Repare que se ocorrer que para um dado vértice os seus vizinhos tenham a mesma quantidade de cores 'A' e 'V', então a cor dominante
        //será setada como 'N' (cor nula), isto é, sem cor. Ou seja, nada ocorrerá no vértice em questão

        if(corDominante != 'N'){
            //Cria um objeto do tipo Random.
            Random random = new Random(); //Poderiamos usar aqui uma semente para gerar os números pseudo-aleatórios se quiséssemos que eles fossem determinísticos. 
            int numeroSorteado = random.nextInt(10)+1; //Essa variável guardará um número entre 1 e 10.
            if(numeroSorteado <= probabilidadeConsenso){
                //Caso o fluxo de execução chegue até aqui, então o vértice recebido como parâmetro terá a sua cor alterada da cor nula para a cor que mais
                //aparece dentre os seus vizinhos, isto é, a corDominante.
                listaVertices[vertice] = corDominante;
            }
            //Caso o número sorteado seja > 8, isto é 8 < numeroSorteado <= 10. Então não é necessário realizar nenhuma operação, já que a ideia é que,
            //nesses casos, o vértice permaneça sem cor (Cor == 'N', isto é, nula).
        }
    }

    public static int contaRodadasParaConsenso(){
        int resultado = 0;

        System.out.print("Cores iniciais dos vértices do grafo: ");
        mostraListaVertices(); //Mostra o estado atual da lista de vértices, ou seja, mostra a cor atual de cada vértice do grafo.
        System.out.println("\n");
        while(existemDesligados()){
            procuraDesligados(); //Procura vértices ainda não coloridos, isto é, desligados, e roda o algoritmo de consenso nesses vértices, caso existam.
            mostraListaVertices(); //Mostra o estado atual da lista de vértices, ou seja, mostra a cor atual de cada vértice do grafo.
            System.out.println();
            resultado++;
        }

        return resultado;
    }

    public static void main(String[] args){
        
        //Crio a matriz de adjacencia e a lista de vértices baseado no arquivo Graph.txt.
        criaMatrizAdjacencia();
        criaListaVertices();
        

        System.out.println("\nMatriz de adjacência do grafo: ");
        mostraMatrizAdjacencia();

        //Ativo o modo iterativo para saber quais vértices o usuário quer colorir inicialmente.
        System.out.println();
        ativaModoIterativo();
        System.out.println();

        
        //mostraListaVertices();
        System.out.println("Foram necessárias "+contaRodadasParaConsenso()+" rodadas para chegar no consenso");
        
    }
}
