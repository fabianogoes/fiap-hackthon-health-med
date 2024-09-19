# FIAP - Hackthon Health&Med

A Health&Med, é uma Operadora de Saúde cujo objetivo é digitalizar seus processos e operação. O principal gargalo da empresa é o Agendamento de Consultas Médicas, que atualmente ocorre exclusivamente por meio de ligações para a central de atendimento da empresa.   
Recentemente, a empresa recebeu um aporte e decidiu investir no desenvolvimento de um sistema proprietário, visando proporcionar um **processo de Agendamentos de Consultas Médicas 100% digital e mais ágil**.   
Para viabilizar o desenvolvimento de um sistema que esteja conforme as melhores práticas de desenvolvimento, a _Health&Med_ contratou os alunos da turma SOAT da Pós-Graduação da FIAP para fazer a análise do projeto e desenvolver o MVP da solução.   

O objetivo do **Hackathon** é a entrega de um produto de MVP desenvolvido e que cumpra os [requisitos funcionais e não funcionais](./Requisitos.md).

## Stack

- Kotlin
- Spring Boot
- Spring Data / JPA
- Spring Security
- JWT
- PostgreSQL
- Docker / Docker Compose
- GitHub Actions

## Módulos

- `app` - entrypoint, módulo responsável por subir a aplicação
- `doctor` - domínio de médicos
- `patient` - domínio de pacientes
- `schedule` - domínio de agendamentos
- `user` - domínio de usuário e controle de acesso
- `shared` - crosscutting 

> Cada módulo segue o design de Arquitetura Hexagonal:

- `adapters\` - adaptadores primários e secundários
  - `primary\` - adaptar primário para API REST
  - `secondary\` - adaptador secundário para Acesso ao Banco de dados
- `domain\` - camada de domínio isolada de arquiteturas externas
  - `entity` - entidades de domínio
  - `exception` - classe de exceptions
  - `usecase` - camada de Use Case 
- `ports\` - Portas primárias e secundárias

## Motivações

- [Monolith First](https://martinfowler.com/bliki/MonolithFirst.html)
- [Modularity Patterns](https://martinfowler.com/articles/refactoring-dependencies.html)
- [Hexagonal architecture](https://alistair.cockburn.us/hexagonal-architecture/)
- [The Dependency Inversion Principle (DIP)](https://martinfowler.com/articles/dipInTheWild.html)


## Importante 

Para gerar uma chave de JWT que é uma variável de ambiente necessária para rodar a aplicação,
é preciso que essa chave seja criada usando `HMAC-SHA algorithm`, 
use um [gerador on-line](https://www.freeformatter.com/hmac-generator.html#before-output) para gerar uma nova sempre que precisar.
