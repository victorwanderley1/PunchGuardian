# Punch Guardian
O Punch Guardian é um gerenciador de registros de marcação de pontos no ambiente de trabalho. Desenvolvido com a intenção
de simplificar e aprimorar o controle dos registros de entrada e saída, este projeto nasceu da necessidade de superar desafios
pessoais e otimizar a gestão do tempo no ambiente profissional.

Este projeto visa oferecer uma solução eficaz para essa questão, proporcionando uma marcação de pontos mais fácil e eficiente,
contribuindo para um equilíbrio mais saudável entre trabalho e demais atividades. Vale ressaltar que o Punch Guardian está em constante
desenvolvimento, e sua contribuição e feedback são sempre bem-vindos!

[![Java CI with Gradle and Docker](https://github.com/victorwanderley1/PunchGuardian/actions/workflows/gradle.yml/badge.svg)](https://github.com/victorwanderley1/PunchGuardian/actions/workflows/gradle.yml)

### Frameworks e tecnologias utilizadas:

![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit5-F37440?style=for-the-badge&logo=junit5&logoColor=green)
![SpringSecurity](https://img.shields.io/badge/Spring--Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

## Variáveis de ambiente
Configurar as variáveis para conexão com o banco de dados:
- SPRING_DATASOURCE_PASSWORD
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_URL

Definir a Time Zone para que sejam padronizados os horários das marcações:
- TZ

Definir chave para a autenticação JWT (opcional, mas indicado)
- JWT_SECRET

## Execução:

`docker run -d -p 8080:8080 -e TZ=America/Recife -e SPRING_DATASOURCE_PASSWORD=postgres -e SPRING_DATASOURCE_USERNAME=postgres -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/PunchGuardianProducao --restart=on-failure victorwanderley/punch-guardian-backend:latest`

## Documentação Swagger
Para ter acesso a documentação swagger relacionada aos endpoints
utilizar o endereço da aplicação mais o final  "swagger-ui/index.html"

Ex:
`http://localhost:8080/swagger-ui/index.html`