#language: pt

  Funcionalidade: Listar cidades

    Como aplicação eu preciso listar as cidades cadastradas.

    Esquema do Cenario: Listar cidades
      Dado que possuo um campo para buscar uma cidade "<nome>"
      Quando confirmar a pesquisa
      Então deve me retornar a quantidade de registros "<quantidade>"

      Exemplos:
      | nome                | quantidade    |
      | Chateaubriand       | 1             |
      | Assis               | 2             |
      | Invalida            | 0             |
      |                     | 2             |
