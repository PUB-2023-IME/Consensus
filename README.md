# Consensus

Todo esse código foi baseado na reunião do dia 08/03/2024 e nas anotações da reunião. 

Ideia de como vai ser implementado. 

1. Gerar a matriz Adj de adjacência do grafo dado

2. Gerar uma lista A que corresponde ao estado de cada vértice, inicialmente com todos os vértices desligados
—> ler o número de linhas que tem a matriz de adjacência para saber quantos (v) vértices o grafo tem e gerar essa lista com v itens com todos os valores iguais a 0

3. Pedir para o usuário definir quantos leds cada um escolherá inicialmente

4. Pedir para o usuário inserir quais vértices ele escolhe
—> Lembrando que se o usuário escolher o vértice 1 o índice dele na lista corresponde a posição 1-1=0
—> A escolha dos vértices é intercalada: 1° usuário escolhe, 2° usuário escolhe, 1° usuário escolhe, 2° usuário escolhe... (até cada usuário atingir o número estipulado no passo 3)
—> Exibir uma mensagem de erro e tente novamente caso o usuário escolha um led que já foi escolhido ou um que nao existe

5. Atualizar a lista A com o novo estado de cada vértice (0 para desligado, B para azul e R para vermelho) de acordo com a escolha dos usuários

6. Criar uma cópia da lista A (chamarei de lista N)

7. Exibir a lista A

8. Ler cada item da lista A. Para cada item:

8.1. Checar a matriz de adjacência para ver quais são os vértices ligados ao vértice que está sendo analisado e armazenar essa informação (guardar quantos vértices no total são vizinhos do que está sendo olhado)

8.2. Checar na lista A qual é a situação de cada um dos vértices vizinhos e armazenar essa informação (guardar o número de vizinhos vermelhos em uma variável, o número de azuis em outra, e desligados em outra)

8.3. Definir um valor F (entre 0 e 1) para a probabilidade do vértice se manter em seu estado atual
—> Fixar um valor (F = 0,6, por exemplo) ou sortear um valor aleatório para F em cada interação (o que eu acho mais legal)

8.4. Calcular as probabilidades (B: azul, R: vermelho, 0: desligado).
—> P(B) = (1 - F) * (n° de vértices azuis vizinhos / total de vértices vizinhos);
—> P(R) = (1 - F) * (n° de vértices vermelhos vizinhos / total de vértices vizinhos);
—> P(0) = (1 - F) * (n° de vértices desligados vizinhos / total de vértices vizinhos);

8.5. O novo estado do vértice será determinado de acordo com o resultado do sorteio;
—> Se o resultado for 0, o vértice permanece como está;
—> Do contrário, muda de acordo com o resultado do sorteio (B ou R)

8.6. Armazena o novo estado de cada vértice na lista N, no índice correspondente ao vértice analisado

9. Repetir os passos 8.1 a 8.6 em looping até analisar todos os vértices da lista A

10. Quando todos os vértices de A forem analisados, seus novos estados estarão guardados em N. Guardar, então, N “por cima” de A (A = N) pois agora este é o novo estado atual.
—> Fim de uma rodada.

11. Repetir o processo a partir de 7.
—> Fazer isso até obter o consenso ou resetar.


Ideias:
—> Para rodar qualquer grafo, fazer o algoritmo ler um arquivo .txt que contém a matriz de adjacência do grafo que se quer ao invés de inserir o grafo diretamente no programa (Mas isso não é necessário para o que precisamos já que usaremos um grafo fixo)
—> Se há vértices desligados como vizinhos, “ignorá-los” no cálculo das probabilidades


