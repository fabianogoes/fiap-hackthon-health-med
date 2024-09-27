# FIAP - Hackthon Health&Med

A Health&Med, é uma Operadora de Saúde cujo objetivo é digitalizar seus processos e operação. O principal gargalo da empresa é o Agendamento de Consultas Médicas, que atualmente ocorre exclusivamente por meio de ligações para a central de atendimento da empresa.   
Recentemente, a empresa recebeu um aporte e decidiu investir no desenvolvimento de um sistema proprietário, visando proporcionar um **processo de Agendamentos de Consultas Médicas 100% digital e mais ágil**.   
Para viabilizar o desenvolvimento de um sistema que esteja conforme as melhores práticas de desenvolvimento, a _Health&Med_ contratou os alunos da turma SOAT da Pós-Graduação da FIAP para fazer a análise do projeto e desenvolver o MVP da solução.   

O objetivo do **Hackathon** é a entrega de um produto de MVP desenvolvido e que cumpra os [requisitos funcionais e não funcionais](./Requisitos.md).

[![Coverage](.github/badges/jacoco.svg)](https://github.com/fabianogoes/fiap-hackthon-health-med/actions/workflows/ci.yml)

## Stack

- [x] [Kotlin](https://kotlinlang.org/) `1.9.24`
- [x] [Spring Boot](https://spring.io/projects/spring-boot) `3.3.2`
- [x] [Spring Data](https://spring.io/projects/spring-data) / [JPA](https://jakarta.ee/specifications/persistence/2.2/apidocs/)
- [x] [Spring Security](https://spring.io/projects/spring-security)
- [x] [Spring Mail](https://docs.spring.io/spring-framework/reference/integration/email.html)
- [x] [JWT](https://jwt.io/)
- [x] [PostgreSQL](https://www.postgresql.org/docs/12/index.html) `12`
- [x] [Docker](https://docs.docker.com/get-started/get-docker/) / [Docker Compose](https://docs.docker.com/compose/)
- [x] [GitHub Actions CI/CD](https://docs.github.com/pt/actions)
- [x] [MockK](https://mockk.io/)
- [x] [Kotlin Faker](https://serpro69.github.io/kotlin-faker/)
- [ ] [Kluent](https://markusamshove.github.io/Kluent/)

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

- [Monolith First](https://martinfowler.com/bliki/MonolithFirst.html) `design`
- [Modularity Patterns](https://martinfowler.com/articles/refactoring-dependencies.html) `design`
- [Hexagonal architecture](https://alistair.cockburn.us/hexagonal-architecture/) `pattern`
- [The Dependency Inversion Principle (DIP)](https://martinfowler.com/articles/dipInTheWild.html) `pattern`
- [Optimistic Locking in JPA](https://www.baeldung.com/jpa-optimistic-locking)  `concurrency`


## Importante 

### Chave para geração do JWT

Para gerar uma chave de JWT que é uma variável de ambiente necessária para rodar a aplicação,
é preciso que essa chave seja criada usando `HMAC-SHA algorithm`, 
use um [gerador on-line](https://www.freeformatter.com/hmac-generator.html#before-output) para gerar uma nova sempre que precisar.

### Envio de Email

Para funcionar o envio de email é preciso configurar as seguintes variáveis de ambiente:

- `MAIL_HOST`: exemplo `smtp.gmail.com`
- `MAIL_PORT`: exemplo `587`
- `MAIL_USERNAME`: exemplo `schedule@gmail.com`
- `MAIL_PASSWORD`: exemplo `your email password`
- `MAIL_SMTP_AUTH`: exemplo `true`
- `MAIL_SMTP_STARTTLS`: exemplo `true`

### Referências

- [Building web applications with Spring Boot and Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin)
- [MockK](https://mockk.io/)
- [Guide to Spring Boot Testing with Kotlin](https://www.baeldung.com/kotlin/spring-boot-testing)
- [Guide to Spring Email](https://www.baeldung.com/spring-email)
- [Sending email in Spring with Thymeleaf](https://www.thymeleaf.org/doc/articles/springmail.html)
- [Using ThymeLeaf and FreeMarker Emails Templates with Spring](https://www.baeldung.com/spring-email-templates)
- [kotlin-faker](https://serpro69.github.io/kotlin-faker/)
