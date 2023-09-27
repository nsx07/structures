# Documentação da Implementação de Árvore Binária

Esta é uma implementação em Java de uma estrutura de dados de árvore binária, projetada para armazenar elementos de tipo genérico `T`. Esta árvore binária é implementada como um pacote chamado `classes` e fornece um conjunto de operações comuns de árvores binárias, como adicionar elementos, remover elementos, buscar elementos e imprimir a estrutura da árvore. Também inclui métodos para limpar a árvore, bem como métodos para recuperar a profundidade e altura dos nós dentro da árvore.

## Sumário
- [Métodos da API](#api-methods)
- [Métodos Privados](#private-methods)
- [Classes Privadas](#private-classes)

## Métodos da API

### `void add(T element)`
Este método adiciona um novo elemento à árvore binária. Se a árvore estiver vazia, ele cria um novo nó raiz com o elemento fornecido. Caso contrário, insere o elemento em uma posição apropriada na árvore com base no código de hash do elemento.

### `T remove(T element)`
Este método remove um elemento da árvore binária mantendo a estrutura da árvore binária. Ele retorna o elemento que foi removido ou `null` se o elemento não for encontrado na árvore.

### `T get(T element)`
Este método recupera um elemento da árvore binária com base em seu valor. Ele retorna o elemento se encontrado ou `null` se o elemento não estiver presente na árvore.

### `T get(Function<? super T, Boolean> predicate)`
Este método recupera um elemento da árvore binária com base em uma função de predicado personalizada. Ele retorna o elemento que satisfaz o predicado ou `null` se nenhum elemento atender ao critério.

### `void printTree()`
Este método imprime a estrutura da árvore binária, exibindo o valor de cada nó, sua profundidade e o número total de elementos na árvore. A estrutura da árvore é impressa em um formato facilmente compreensível.

### `void clear()`
Este método limpa a árvore binária definindo o nó raiz como `null` e redefinindo o comprimento da árvore para zero.

## Métodos Privados

A implementação da árvore binária também inclui vários métodos privados usados para operações internas da árvore. Esses métodos não são destinados ao uso externo, mas são cruciais para o funcionamento da árvore binária.

## Classes Privadas

### `BTreePrinter` (classe privada e estática)
Esta classe é responsável por imprimir a estrutura da árvore binária de uma forma visualmente agradável. Ela formata e imprime os nós, seus valores, profundidades e o número de elementos na árvore. Ela usa códigos de cores ANSI para destacar e indentação para tornar a estrutura da árvore mais legível.

### `TreeNode<T>` (classe privada)
Esta classe define a estrutura de um nó na árvore binária. Cada nó possui um elemento de dados de tipo `T`, um nó filho esquerdo e um nó filho direito. A classe também inclui um método `hashCode` para calcular o código de hash com base no elemento de dados e um método `toString` para imprimir os detalhes do nó.
