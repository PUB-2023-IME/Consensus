# Algoritmo de Consenso em Grafos

## Descrição

Este é um programa Java que simula o processo de consenso em um grafo, onde os vértices do grafo representam entidades que desejam chegar a um acordo em relação a um certo atributo (representado por uma cor). O algoritmo implementado aqui utiliza uma matriz de adjacência para representar as conexões entre os vértices e atualiza as cores dos vértices com base nas cores de seus vizinhos.

O algoritmo funciona da seguinte forma:

1. **Leitura de Arquivo e Criação da Matriz de Adjacência (Inicialização do Grafo):** O programa lê o arquivo Graph.txt, que contém a dimensão da matriz de adjacência e a própria matriz de adjacência. Esta matriz representa as conexões entre os vértices do grafo.
  - **Como interpretar a matriz de adjacência ?**

    - A matriz de adjacência é uma estrutura de dados bidimensional que representa as conexões entre os vértices de um grafo.
    - No contexto deste código, a matriz de adjacência é lida a partir de um arquivo chamado Graph.txt.
    - Cada linha e coluna da matriz representa um vértice do grafo.
    - O valor armazenado em uma determinada posição [i][j] da matriz indica se existe uma aresta entre os vértices i e j.
    - Um valor de 1 indica a presença de uma aresta, enquanto um valor de 0 indica a ausência de uma aresta.
    - 
2. **Criação da Lista de Vértices e Inicialização de Cores**: Com base na matriz de adjacência criada acima, é criada uma lista de vértices que será responsável por armazenar a cor de cada um dos vértices do grafo gerado pelo arquivo Graph.txt. As cores dos vértices são inicializadas como 'N' (sem cor). Isso significa que nenhum vértice possui uma cor atribuída inicialmente.
  - **Como interpretar a lista de vértices?**
    - A lista de vértices é uma estrutura de dados unidimensional que armazena as cores atribuídas a cada vértice do grafo.
    - Cada elemento da lista representa um vértice do grafo.
    - No início, todos os vértices são inicializados com a cor nula 'N', indicando que não foram coloridos.
    - Durante a execução do programa, o usuário tem a opção de especificar manualmente as cores de alguns vértices.
    - Após a execução do algoritmo de consenso, cada vértice terá uma cor atribuída com base nas cores de seus vizinhos.
      
3. **Modo Iterativo**: Por hora, o programa solicita que o usuário selecione manualmente a probabilidade de consenso em cada rodada, além das cores iniciais de alguns vértices. Isso é útil para testar cenários específicos ou configurar o estado inicial do grafo de acordo com requisitos específicos.

4. **Algoritmo de Consenso**: O algoritmo de consenso é aplicado aos vértices desligados do grafo até que todos os vértices tenham uma cor atribuída. O algoritmo segue as seguintes etapas:
   - Para cada vértice desligado (sem cor), o algoritmo verifica a cor dominante entre seus vizinhos.
   - Se houver uma cor que predomine entre os vizinhos, o vértice assume essa cor com uma certa probabilidade de consenso. A probabilidade de consenso pode ser ajustada pelo usuário e determina a probabilidade de um vértice assumir a cor predominante de seus vizinhos.
   - Se não houver uma cor predominante entre os vizinhos ou se a probabilidade de consenso não for atingida, o vértice permanece sem cor.
   - O algoritmo continua iterando sobre os vértices desligados até que todos os vértices tenham uma cor atribuída.
   - 
5. **Contagem de Rodadas para Consenso**: O programa conta o número de rodadas necessárias para que todos os vértices alcancem um consenso sobre suas cores. Cada rodada representa uma iteração do algoritmo sobre os vértices desligados até que todos os vértices tenham uma cor atribuída.

## Instalação

1. Clone o repositório para sua máquina local.
2. Compile o código-fonte Graph.java usando um compilador Java.
  - Ex: Rode no terminal o seguinte código: "javac Graph.java"

## Uso

1. Garanta que o arquivo Graph.txt esteja presente no mesmo diretório que o arquivo compilado Graph.class.
2. Execute o programa Graph.class.
   - Ex: Rode no terminal o seguinte código: "java Graph"
4. Siga as instruções para selecionar as configurações iniciais do grafo.
5. O programa imprimirá o número de rodadas necessárias para que todos os vértices alcancem um consenso sobre suas cores, dada a configuração inicial solicitada.

## Exemplo de Arquivo Graph.txt

O arquivo Graph.txt deve seguir o seguinte formato do arquivo Graph.txt fornecido no código.

Basicamente, o arquivo Graph.txt contém em sua primeira linha a dimensão N da matriz quadrada que representará a matriz de adjacência do grafo e, nas linhas abaixo, o arquivo em questão contém a matriz de adjacência citada.

O arquivo Graph.txt pode ser mudado conforme a vontade do usuário, desde que se mantenham as configurações citadas acima. Isto é, a matriz de adjacência sempre será uma matriz quadrada e a primeira linha do arquivo Graph.txt sempre deverá conter a dimensão N de tal matriz quadrada.
