// Criação: 20/09/2021

import java.util.Scanner;

 public class Graph {
 
    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);

        // Vamos criar o nosso Grafo de exemplo
        int vertices = 10;
        int[][] adjMatrix = new int[vertices][vertices];
        // Definie a matriz de adjacência

        // linha 0
        adjMatrix[0][1] = 1;
        adjMatrix[0][3] = 1;
        //linha 1
        adjMatrix[1][0] = 1;
        adjMatrix[1][2] = 1;
        adjMatrix[1][3] = 1;
        // linha 2
        adjMatrix[2][1] = 1;
        adjMatrix[2][3] = 1;
        // linha 3
        adjMatrix[3][0] = 1;
        adjMatrix[3][1] = 1;
        adjMatrix[3][2] = 1;

        // Inicializa a matriz com 0s
        graph.initializeMatrix(adjMatrix);
        
        // imprimir o grafo com as arestas e os vértices
        graph.printGraph(adjMatrix);

        // Perguntar ao usuário quantos vértices ele quer definir
        System.out.println("Quantos vértices você quer definir?");
        int numVertices = scanner.nextInt();

        // Perguntar ao usuário a posição e a cor de cada um dos vértices
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Qual a posição i do vertice?");
            int posi = scanner.nextInt();

            System.out.println("Qual a posição j do vertice?");
            int posj = scanner.nextInt();

            System.out.println("Qual a cor do vértice?");
            int cor = scanner.nextInt();

            adjMatrix[posi][posj] = cor;

        }

        // Imprimir o grafo que o usuário definiu
        graph.printGraph(adjMatrix);

        // começar a rodar o algoritmo de consenso


    }

    // Método que inicializa a matriz com 0s
    public void initializeMatrix(int[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    // Método que imprime o grafo vAI TER QUE SER ADPTADO PARA MOSTRAR UM GRAFO E NÃO MATRIZ
    public void printGraph(int[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método que verifica quais leds estão ligados 
    public void checkLeds(int[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j] != 0) {
                    // Aqui vamos chamar o método que verifica os vizinhos
                    // e influencia os vizinhos
                }
            }
        }
    }




    



    
    







 }

