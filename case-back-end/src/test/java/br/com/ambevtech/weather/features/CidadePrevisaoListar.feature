#language: pt

  Funcionalidade: Consultar Previsão do Tempo

    Como aplicação eu preciso consultar a previsão do tempo de uma cidade.

    Esquema do Cenario: Consultar Previsão do Tempo
      Dado que possuo uma cidade para consultar a previsão "<id>"
      Quando confirmar o registro
      Então deve me retornar um status de "<status>"

      Exemplos:
      | id  | status    |
      | 1   | 200       |
      | 9   | 404       |
      |     | 400       |
