import random

def main():
  
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

  # Lista com todos os vértices desligados
  new = consensus(vertices, 0)

  # Quantos leds cada jogador acenderá ao iniciar do jogo
  on = initial_light(vertices)

  # Quais leds cada jogador acenderá ao iniciar o jogo
  old = escolhas(new, on, vertices)
  print(f"Esta é a situação inicial do jogo: {old}")

  # Iguala os itens de new e o old para iniciar o jogo
  for i in range (0, vertices):
    new[i] = old[i]

  consensoR = consensus(vertices, "R")
  consensoB = consensus(vertices, "B")

  rodada = 0
  while consenso == False:
    
    # print(f"rodada: {rodada}")
    rodada = rodada + 1

    print(f"atual: {old}")
    for i in range (10):
        
        vizinhos = []

        # Verificar quais vértices estão acesos
        if old[i] != 0:

            # Dentre os vértices acesos:
            for k in range (10):
        
                # Verificar quais são os seus vizinhos
                if adj[i][k] == 1:
        
                    # Guardar a info de onde estão os vizinhos
                    vizinhos.append(k)
                    blue = 0
                    red = 0
                    total = 0
                    rodada = 0
                    
                    for m in range (10):
                        if adj[k][m] == 1:
                            if old[m] == "B":
                                blue += 1
                                total += 1
                            elif old[m] == "R":
                                red += 1
                                total += 1
                    
                    prob_ficar = 0.1
                    prob_blue = ((1 - prob_ficar)*blue)/total
                    prob_red = ((1 - prob_ficar)*red)/total
                
                    possib = ["B","R", 0]
                    sorteio = random.choices(possib, weights = (prob_blue, prob_red, prob_ficar))
                
                    if sorteio[0] == "B" or sorteio[0] == "R":
                        new[k] = sorteio[0]
                    else:
                        new[k] = old[k]

    for n in range (len(new)):
        old[n] = new[n]
    
    print(f"novo: {new}")

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