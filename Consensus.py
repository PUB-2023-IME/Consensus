import random

def main():
  
  # Consenso começa como falso
  consenso = False

  # Matriz de adjacencia de um grafo fixo
  adj = [[0, 1, 0, 1, 1, 1, 1, 1, 0, 0],
         [1, 0, 1, 1, 0, 0, 0, 1, 1, 1],
         [0, 1, 0, 1, 0, 0, 0, 0, 0, 1],
         [1, 1, 1, 0, 1, 0, 0, 0, 0, 0],
         [1, 0, 0, 1, 0, 1, 0, 0, 0, 0],
         [1, 0, 0, 0, 1, 0, 1, 0, 0, 0],
         [1, 0, 0, 0, 0, 1, 0, 1, 0, 0],
         [1, 1, 0, 0, 0, 0, 1, 0, 1, 0],
         [0, 1, 0, 0, 0, 0, 0, 1, 0, 1],
         [0, 1, 1, 0, 0, 0, 0, 0, 1, 0]]

  # Número de vértices do grafo dado
  vertices = len(adj)

  # Lista inicial de posiçaõ dos vértices, com todos desligados
  new = consensus(vertices, 0)

  # Os usuários definem QUANTOS leds cada jogador acenderá ao iniciar do jogo
  on = initial_light(vertices)

  # Os usuários definem QUAIS leds cada jogador acenderá ao iniciar o jogo
  old = escolhas(new, on, vertices)
  print(f"Esta é a situação inicial do jogo: {old}")

  # Iguala os itens de new e o old para iniciar o jogo
  for i in range (0, vertices):
    new[i] = old[i]

  # Estado de consenso de vértices vermelhos
  consensoR = consensus(vertices, "R")
  # Estado de consenso de vértices azuis
  consensoB = consensus(vertices, "B")

  # Contador de rodadas
  rodada = 0

  # Enquanto não atingir o estado de consenso:
  while consenso == False:
    
    print(f"rodada: {rodada}")
    rodada += 1

    # Saída de controle: para verificação da execução do programa
    print(f"atual: {old}")

    # Para cada vértice:
    for i in range (0, vertices):
        
        # Lista para guardar quais são os vizinhos do led aceso
        vizinhos = []

        # Verificar quais vértices estão acesos
        if old[i] != 0:

            # Dentre os vértices acesos:
            for k in range (0, vertices):
        
                # Verificar quais são os seus vizinhos
                if adj[i][k] == 1:
        
                    # Guardar a info de onde estão os vizinhos
                    vizinhos.append(k)

                    # Variáveis para guardar as quantidades de cada estado de led
                    blue = 0
                    red = 0
                    total = 0
                    
                    # Dentre os vizinhos:
                    for m in range (0, vertices):
                        
                        # Checar o vizinho do vizinho
                        if adj[k][m] == 1:
                            
                            # Se for azul
                            if old[m] == "B":
                                blue += 1
                                total += 1

                            #Se for vermelho
                            elif old[m] == "R":
                                red += 1
                                total += 1
                    
                    # Probabilidade do led permanecer como está
                    prob_ficar = 0.1
                    # Probabilidade do led se tornar azul
                    prob_blue = ((1 - prob_ficar)*blue)/total
                    # Probabilidade do led se tornar vermelho
                    prob_red = ((1 - prob_ficar)*red)/total

                    # Possibilidades do resultado do sorteio
                    possib = ["B","R", 0]
                    # Sorteio aleatório com pesos para cada possibilidade
                    sorteio = random.choices(possib, weights = (prob_blue, prob_red, prob_ficar))
                
                    # Se o resultado do sorteio for azul ou vermelho, o led muda para a cor sorteada e essa info é armazenada na lista new
                    if sorteio[0] == "B" or sorteio[0] == "R":
                        new[k] = sorteio[0]
                    
                    # Do contrário (o resultado do sorteio é igual a 0), o led permanece como está e essa info é armazenada na lista new
                    else:
                        new[k] = old[k]

    # As alterações estão em new ao fim da rodada. Old é atualizado com essas informações para que new possa armazenar as informações da próxima rodada 
    for n in range (len(new)):
        old[n] = new[n]
    
    # Saída de controle: para verificação da execução do programa
    print(f"novo: {new}")

    # As interações são encerradas quando os vértices do grafo atingem um estado de consenso.
    if old == consensoR or old == consensoB:
        consenso = True

                
# ---------------------------------------------------------
#                   LISTA DE CONSENSO
# ---------------------------------------------------------

def consensus(x, y):
  """ (int, parameter) ---> (list)
  Esta funcao recebe um número inteiro positivo x e um parâmetro y e retorna uma lista com x itens, todos iguais ao parâmetro.
  """
  lista = []

  for i in range (x):
    lista.append(y)

  return lista

# ---------------------------------------------------------
#            MÁXIMO DE LUZES INICIAIS DO JOGO
# ---------------------------------------------------------

def initial_light(x):
  """(int) ---> (int)
  Esta funcao recebe um número inteiro positivo x, que corresponde ao número de vértices total do grafo, e retorna o número máximo de leds que cada usuário pode optar por acender no inicio do jogo.
  """
  max = int(0.1*x)
  print(f"Cada jogador poderá acender no máximo {max} leds para começar. Lembre-se que os jogadores devem acender o mesmo número de vértices.")
  
  while True:
    try:
      choose = int(input(f"Para iniciar o jogo, escolha quantos leds, entre 1 e {max}, cada jogador acenderá inicialmente: "))
      if choose >= 1 and choose <= max:
        return choose
      else:
        raise ValueError("O valor inserido não corresponde ao intervalo dado")
    except ValueError as erro:
      print(f"Erro: {erro}. Por favor, tente novamente inserindo um valor válido.")
  
# ---------------------------------------------------------
#             ESCOLHAS PARA INICIAR O JOGO
# ---------------------------------------------------------

def escolhas(x, y, z):
  """ (lista, int, int) ---> (lista)
  Esta funcao recebe uma lista x de zeros, um numero natural positivo y correspondente a quantos leds cada usuário escolherá acender para iniciar o jogo, e a quantidade natural positiva z de vértices do grafo. A funcao retorna uma lista atualizada com quais vertices foram acesos e quais são as suas respectivas cores.
  """

  print("Selecao de cores: Qual cor te representará?")
  
  while True:
    try:
      frst = input("Jogador numero 1, digite B para azul ou R para vermelho: ").strip().upper()
      if frst == "B":
        scnd = "R"
        print("Jogador numero 2, você será representado pela cor vermelha.")
      elif frst == "R":
        scnd = "B"
        print("Jogador numero 2, você será representado pela cor azul.")
      else:
        raise ValueError("A entrada inserida não corresponde às opcoes fornecidas")
      break
    except ValueError as erro:
      print(f"Erro: {erro}. Por favor, tente novamente e digite uma entrada válida.")

  for i in range(y): #numero de escolhas de cada
    print(f"Este é o estado atual dos leds: \n{x}")

    while True:
      try:
        escolha = int(input(f"Jogador 1, escolha o seu {i+1} led para acender: ")) -1
        if escolha >= 0 and escolha <= z-1:
          if x[escolha] == 0:
            x[escolha] = frst
            break
          else:
            raise ValueError("A posição escolhida está indisponível pois o led já está aceso")
        else:
          raise ValueError("A posição escolhida não existe")
      except ValueError as erro:
        print(f"Erro: {erro}. Por favor, tente novamente e escolha uma posição válida.")

    while True:
      try:
        escolha = int(input(f"Jogador 2, escolha o seu {i+1} led para acender: ")) -1
        if escolha >= 0 and escolha <= z-1:
          if x[escolha] == 0:
            x[escolha] = scnd
            break
          else:
            raise ValueError("A posição escolhida está indisponível pois o led já está aceso")
        else:
          raise ValueError("A posição escolhida não existe")
      except ValueError as erro:
        print(f"Erro: {erro}. Por favor, tente novamente e escolha uma posição válida.")
    
  return x
      
main()