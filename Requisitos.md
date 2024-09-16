# Requisitos Hackthon Health&Med 

> Considerações importantes:
> - Não haverá a necessidade do desenvolvimento de Frontend.
> - Os times do Hackathon terão autonomia para tomar as decisões de arquitetura e decidir como será feito o desenvolvimento, aplicando os conhecimentos adquiridos durante o curso de Pós Graduação SOAT.

## Requisitos Funcionais

- [ ] Cadastro do Usuário (Médico)
  - O médico deverá poder se cadastrar, preenchendo os campos obrigatórios: Nome, CPF, Número CRM, E-mail e Senha.
- [ ] Autenticação do Usuário (Médico)
  - O sistema deve permitir que o médico faça login usando o E-mail e uma Senha.
- [ ] Cadastro/Edição de Horários Disponíveis (Médico)
  - O sistema deve permitir que o médico faça o Cadastro, Edição de seus
dias e horários disponíveis para agendamento de consultas.
- [ ] Cadastro do Usuário (Paciente)
  - O paciente poderá se cadastrar preenchendo os campos: Nome, CPF, E-mail e Senha.
- [ ] Autenticação do Usuário (Paciente)
  - O sistema deve permitir que o paciente faça login usando o E-mail e
Senha.  
- [ ] Busca por Médicos (Paciente)
  - O sistema deve permitir que o paciente visualize a listagem dos médicos disponíveis.
- [ ] Agendamento de Consultas (Paciente)
  - Após selecionar o médico, o paciente deve visualizar os dias e horários
disponíveis do médico.
  - O paciente poderá selecionar o horário de preferência e realizar o agendamento.
- [ ] Notificação de consulta marcada (Médico)
  - Após o agendamento, feito pelo usuário Paciente, o médico deverá
  ```
  receber um e-mail contendo:
  Título do e-mail:
  ˮHealth&Med - Nova consulta agendadaˮ
  Corpo do e-mail:
  ˮOlá, Dr. {nome_do_médico}!
  Você tem uma nova consulta marcada! Paciente: {nome_do_paciente}.
  Data e horário: {data} às {horário_agendado}.ˮ
  ```

## Requisitos Não Funcionais

- [ ] Concorrência de Agendamentos
  - O sistema deve ser capaz de suportar múltiplos acessos simultâneos e garantir que apenas uma marcação de consulta seja permitida para um determinado horário.
- [ ] Validação de Conflito de Horários
  - O sistema deve validar a disponibilidade do horário selecionado em tempo real, assegurando que não haja sobreposição de horários para consultas agendadas.
     
## Entregáveis Mínimos

Os grupos deverão entregar o seguinte:

- [ ] Desenvolvimento de um MVP da solução, contemplando os requisitos funcionais e não funcionais listados acima.
- [ ] Pipeline CI/CD
  - Demonstração do pipeline de deploy da aplicação.
- [ ] Testes unitários
  - Implantação de testes unitários que garantam o funcionamento da solução.
- [ ] Não há a necessidade do desenvolvimento do Frontend da Solução.
- [ ] Formato da entregável: Vídeo gravado que demonstre o funcionamento do sistema cumprindo os requisitos solicitados e Documentação escrita (README ou Arquivo)
  - A duração máxima do vídeo deverá ser de, no máximo, 10 minutos. Vídeos mais longos não serão corrigidos.
     
