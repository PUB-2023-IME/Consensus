# Importa as bibliotecas que serão usadas no decorrer do script.
import pygame
import sys
import random

# Define a classe Node que representará os nós do grafo.
class Node:
    def __init__(self, x, y, nodeNumber):
        '''
        Inicializa um objeto Node.

        Args:
            x (int): A coordenada x do nó.
            y (int): A coordenada y do nó.
            nodeNumber (int): O número identificador do nó.

        Returns:
            None
        '''
        self.x = x
        self.y = y
        self.color = 'BLACK'
        self.number = nodeNumber

# Define como variáveis globais as cores que serão usadas para desenhar o grafo através da biblioteca pygame.
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
RED = (255, 0, 0)
BLUE = (0,0,255)

# Define as variáveis globais que armazenarão o tamanho das dimensões da tela pygame.
WIDTH = 0
HEIGHT = 0

# Define a variável global que será responsável por armazenar uma lista de objetos do tipo Node, em outras palavras, essa variável armazenará a lista de nós do grafo.
NODES = []

# Define a variável global que será responsável por armazenar a matriz de adjacência do grafo.
ADJACENCYMATRIX = []

# Define a variável global que armazenará a probabilidade de que um dado vértice entre em consenso em uma dada rodada (Tal probabilidade será representada por um número inteiro entre 0 e 10, onde 0 indica uma probabilidade de 0% e 10 indica uma probabilidade de 100%).
consensusProbability = 0

def processFile(filename):
    '''
    Processa um arquivo de entrada contendo informações sobre o grafo.

    Args:
        filename (str): O nome do arquivo a ser processado.

    Returns:
        None
    '''

    global WIDTH, HEIGHT, NODES, ADJACENCYMATRIX

    with open(filename, 'r') as file:
        unprocessedFile = file.readlines()

    linesNumber = len(unprocessedFile)
    n = int((linesNumber - 2) / 2)

    for i in range(len(unprocessedFile)):
        unprocessedFile[i] = unprocessedFile[i].strip()

    WIDTH = int(unprocessedFile[0])
    HEIGHT = int(unprocessedFile[1])

    nodeCount = 0
    for i in range(2, 2 + n):
        x, y = eval(unprocessedFile[i])
        node = Node(x, y, nodeCount)
        NODES.append(node)
        nodeCount += 1

    for i in range(2 + n, len(unprocessedFile)):
        ADJACENCYMATRIX.append(unprocessedFile[i].split(' '))


def draw_graph(screen):
    '''
    Desenha o grafo na tela.

    Args:
        screen (pygame.Surface): A superfície onde o grafo será desenhado.

    Returns:
        None
    '''

    screen.fill(WHITE)  # Preenche a tela com a cor branca

    # Desenha as arestas do grafo
    for i in range(len(ADJACENCYMATRIX)):
        for j in range(len(ADJACENCYMATRIX[i])):
            if ADJACENCYMATRIX[i][j] == '1':
                pygame.draw.line(screen, BLACK, (NODES[i].x, NODES[i].y),
                                 (NODES[j].x, NODES[j].y), 1)

    # Desenha os nós do grafo com números
    font = pygame.font.Font(None, 20)
    for node in NODES:
        pygame.draw.circle(screen, node.color, (node.x, node.y), 8)
        pygame.draw.circle(screen, BLACK, (node.x, node.y), 10, 2)
        text_surface = font.render(str(node.number), True, WHITE)
        text_rect = text_surface.get_rect(center=(node.x, node.y))
        screen.blit(text_surface, text_rect)


def getInformationIteratively():
    '''
    Obtém informações do usuário iterativamente.

    Args:
        None

    Returns:
        None
    '''

    NODES[0].color = input()
    NODES[1].color = input()

    global consensusProbability
    consensusProbability = int(input())

    colorOtherNodes = input()
    if(colorOtherNodes == 'Y'):
        nodesToColor = eval(input())
        for tuple in nodesToColor:
            type(tuple[0])
            NODES[tuple[0]].color = tuple[1]


def checkNeighborhood(nodeI, nodeJ):
    '''
    Verifica se dois nós são vizinhos.

    Args:
        nodeI (int): O índice do primeiro nó.
        nodeJ (int): O índice do segundo nó.

    Returns:
        bool: True se os nós são vizinhos, False caso contrário.
    '''

    return (ADJACENCYMATRIX[nodeI][nodeJ] == '1')


def existBlackNodes():
    '''
    Verifica se existem nós pretos no grafo.

    Args:
        None

    Returns:
        bool: True se houver nós pretos, False caso contrário.
    '''

    for node in NODES:
        if node.color == 'BLACK':
            return True
    return False


def searchBlackNodes():
    '''
    Procura por nós pretos no grafo e executa o processo de consenso neles.

    Args:
        None

    Returns:
        None
    '''

    for node in NODES:
        if node.color == 'BLACK':
            consensus(node)


def consensus(node):
    '''
    Executa o processo de consenso em um nó específico.

    Args:
        node (Node): O nó no qual o processo de consenso será executado.

    Returns:
        None
    '''

    blueNodes, redNodes = 0, 0
    for i in range(len(ADJACENCYMATRIX)):
        if ADJACENCYMATRIX[i][node.number] == '1':
            if (NODES[i].color == 'BLUE'): blueNodes += 1
            if (NODES[i].color == 'RED'): redNodes += 1
    corDominante = 'BLUE' if blueNodes >= redNodes else 'RED'

    if (corDominante != 'BLACK'):
        drawnNumber = random.randint(1, 10)
        if (drawnNumber <= consensusProbability):
            node.color = corDominante


def startSimulation():
    '''
    Inicia a simulação do grafo.

    Args:
        None

    Returns:
        None
    '''

    pygame.init()
    screen = pygame.display.set_mode((WIDTH, HEIGHT))
    pygame.display.set_caption("Visualização do Grafo")

    clock = pygame.time.Clock()

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        # Atualiza a tela antes de começar a contagem de rodadas
        draw_graph(screen)
        pygame.display.flip()

        # Contagem de rodadas
        rounds = 0
        while existBlackNodes():
            searchBlackNodes()
            rounds += 1

            # Atualiza a tela após cada rodada
            draw_graph(screen)

            # Renderiza e exibe o valor de rounds na tela
            font = pygame.font.Font(None, 36)
            text = font.render(f"Rodadas: {rounds}", True, BLACK)
            screen.blit(text, (10, 10))  # Posição do texto na tela
            
            pygame.display.flip()

            # Pausa curta para tornar a atualização visível
            pygame.time.delay(1000)

        print("Total de rodadas para consenso:", rounds)
        running = False  # Termina o loop após a contagem de rodadas

        # Loop principal espera até o usuário fechar a janela
        while running:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False

        pygame.quit()
        sys.exit()

def main():
  '''
  Função principal do programa.

  Args:
      None

  Returns:
      None
  '''
  
  processFile('graph.txt')

  getInformationIteratively()

  startSimulation()  


if __name__ == "__main__":
  main()
