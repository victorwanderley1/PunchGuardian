# Punch Guardian
O Punch Guardian é um gerenciador de registros de marcação de pontos no ambiente de trabalho. Desenvolvido com a intenção de simplificar e aprimorar o controle dos registros de entrada e saída, este projeto nasceu da necessidade de superar desafios pessoais e otimizar a gestão do tempo no ambiente profissional.

Este projeto visa oferecer uma solução eficaz para essa questão, proporcionando uma marcação de pontos mais fácil e eficiente, contribuindo para um equilíbrio mais saudável entre trabalho e demais atividades. Vale ressaltar que o Punch Guardian está em constante desenvolvimento, e sua contribuição e feedback são sempre bem-vindos!

[![Java CI with Gradle and Docker](https://github.com/victorwanderley1/PunchGuardian/actions/workflows/gradle.yml/badge.svg)](https://github.com/victorwanderley1/PunchGuardian/actions/workflows/gradle.yml)

## Variáveis de ambiente
Configurar as variáveis para conexão com o banco de dados:
- SPRING_DATASOURCE_PASSWORD
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_URL

Também definir a Time Zone para que sejam padronizados os horários das marcações
- TZ

## Execução:

`docker run -d -p 8080:8080 -e TZ=America/Recife -e SPRING_DATASOURCE_PASSWORD=postgres -e SPRING_DATASOURCE_USERNAME=postgres -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/PunchGuardianProducao --restart=on-failure victorwanderley/punch-guardian-backend:latest`

### A fazeres:
1. [x] ~~Criar funcionalidade de marcação de pontos~~
2. [x] ~~Criar funcionalidade de verificar pontos marcados~~
3. [ ] Criar funcionalidade de verificar quantas horas úteis restam
4. [ ] Implementar cálculo para abater feriados das horas úteis
5. [ ] Implementar regra para ajuste de ponto
6. [x] ~~Implementar testes para as classes funcionais~~
