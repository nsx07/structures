
# Algoritmos de ordenação

Um algoritmo de ordenação é um algoritmo que organiza os elementos de um array em ordem crescente ou decrescente.

# Bubble sort

O bubble sort é um algoritmo de ordenação simples que funciona comparando pares de elementos adjacentes de um array e trocando-os se estiverem na ordem incorreta. O algoritmo começa comparando o primeiro e o segundo elemento do array. Se o primeiro elemento for maior que o segundo elemento, eles são trocados. Em seguida, o algoritmo compara o segundo e o terceiro elemento, e assim por diante, até o final do array.

O bubble sort é um algoritmo in-place, o que significa que ele não requer nenhum armazenamento adicional para realizar a ordenação. No entanto, ele é um algoritmo ineficiente, pois pode realizar um número excessivo de comparações e trocas.

Exemplo:

Considere o array [5, 1, 4, 2, 3]. O bubble sort começaria comparando o primeiro e o segundo elemento. Como 5 é maior que 1, eles seriam trocados, resultando no array [1, 5, 4, 2, 3]. Em seguida, o algoritmo compararia o segundo e o terceiro elemento. Como 5 é maior que 4, eles seriam trocados, resultando no array [1, 4, 5, 2, 3]. O algoritmo continuaria comparando pares de elementos adjacentes até que não houvesse mais trocas. No final, o array estaria ordenado em ordem crescente: [1, 2, 3, 4, 5].

![](https://raw.githubusercontent.com/nsx07/structures/main/bubblesort.PNG)

# Insertion sort

O insertion sort é um algoritmo de ordenação que funciona comparando cada elemento do array com os elementos anteriores. Se o elemento atual for menor que um dos elementos anteriores, ele é inserido na posição correta.

O insertion sort é um algoritmo in-place, o que significa que ele não requer nenhum armazenamento adicional para realizar a ordenação. No entanto, ele pode ser ineficiente para arrays grandes, pois pode realizar um número excessivo de comparações.

Exemplo:

Considere o array [5, 1, 4, 2, 3]. O insertion sort começaria com o primeiro elemento, que já está na posição correta. Em seguida, o algoritmo compararia o segundo elemento com o primeiro elemento. Como 1 é menor que 5, o elemento 1 seria inserido antes do elemento 5, resultando no array [1, 5, 4, 2, 3]. O algoritmo continuaria comparando os elementos subsequentes com os elementos anteriores, inserindo-os nas posições corretas. No final, o array estaria ordenado em ordem crescente: [1, 2, 3, 4, 5].

![](https://raw.githubusercontent.com/nsx07/structures/main/insertionsort.jpg)

# Quick sort

O quick sort é um algoritmo de ordenação de divide e impera que funciona dividindo o array em dois subarrays, ordenando cada subarray recursivamente e, em seguida, combinando os dois subarrays ordenados.

O quick sort é um algoritmo eficiente para arrays de tamanhos médios a grandes. No entanto, ele pode ser ineficiente para arrays pequenos, pois pode realizar um número excessivo de comparações.

Exemplo

Considere o array [5, 1, 4, 2, 3]. O quick sort começaria escolhendo um elemento pivot, geralmente o elemento central do array. Em seguida, o algoritmo compararia cada elemento do array com o pivot. Se um elemento for menor que o pivot, ele será movido para o subarray esquerdo. Se um elemento for maior ou igual ao pivot, ele será movido para o subarray direito.

Depois que todos os elementos do array forem classificados em subarrays esquerdo e direito, o algoritmo recursivamente ordenará cada subarray. No final, os dois subarrays ordenados serão combinados para formar um array ordenado.

![](https://raw.githubusercontent.com/nsx07/structures/main/quicksort.png)

# Conclusão

Este programa apresentou três algoritmos de ordenação simples: bubble sort, insertion sort e quick sort. O bubble sort é o algoritmo mais simples, mas também é o menos eficiente. O insertion sort é um pouco mais eficiente que o bubble sort, mas ainda pode ser ineficiente para arrays grandes. O quick sort é o algoritmo mais eficiente, mas pode ser ineficiente para arrays pequenos.

A escolha do algoritmo de ordenação mais adequado depende do tamanho e da ordem inicial do array. Para arrays pequenos, o bubble sort ou o insertion sort podem ser suficientes. Para arrays grandes, o quick sort é geralmente a melhor escolha.