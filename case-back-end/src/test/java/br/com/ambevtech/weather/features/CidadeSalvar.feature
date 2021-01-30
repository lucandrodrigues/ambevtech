#language: pt

  Funcionalidade: Salvar uma cidade

    Como aplicação eu preciso salvar a cidade.

    Esquema do Cenario: Salvar a cidade
      Dado que possuo uma cidade para salvar "<nome>"
      Quando salvar o resgitro
      Então deve me retornar um status "<status>"

      Exemplos:
      | nome        | status    |
      | Blumenau    | 200       |
      | Assis       | 409       |
      | Invalida    | 404       |
      |             | 400       |
