## Previsão do Tempo (back-end)
Case para a AmbevTech

### Banco de dados
- utilizado banco MySQL para persistência dos dados
- as credenciais para comunicação com o banco de dados se encontram no arquivo application.yml.
- o banco será criado e populado com duas cidades automaticamente (schema: weather).
- será criado um outro banco para execução dos testes (schema: weather_teste)

### Tecnologias utilizadas:
- Framework: SpringBoot
- Versionamento do banco de dados: Flyway
- Testes automatizados: Cucumber
- Documentação das apis: Swagger

### Observações da aplicação
- Utilizado duas APIs externas: uma para validar a cidade e conseguir as coordenadas e a outra que consulta a previsão diária dos próximos 7 dias.
- Utilizado método interno para limitar somente 5 dias conforme solicitado
- Adicionado cache de 15 minutos em dois services para evitar o consumo indevido
- Utilizado métodos bem simples e inserido logs em posições estratégicas para facilitar a manutenção e escalabilidade de código.