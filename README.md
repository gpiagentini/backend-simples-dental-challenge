# Simples Dental Challenge Backend

Projeto feito para o Challenge de FullStack da Simples Dental.

## Overview do Projeto
- Spring boot: 3.4.2
- Java 21
- Maven
- Postgres

## Descrição Funcional

Para esse desafio, foi preciso utilizar um banco de dados para criação de um CRUD, onde iremos manipular duas entidades, de relacionamento **1 - n**:

- **Profissionais**
- **Contatos**

A entidade **Posição** foi criada para gerenciar a lista de posições disponíveis para inserção.

---

O Sistema permite algumas ações para as entidades **Profissionais** e **Contatos**.

### Buscar

Temos duas formas de buscar as entidades:

- Por id
- Buscar lista
  - Podemos filtrar os campos específicos que queremos. (fields=<campo1>,<campo2>)
  - Podemos passar um texto e retornar os campos que fazem parte desse texto. (q=<text>)

### Alterar
Podemos alterar os campos das entidade a partir do ID da entidade.

Profissionaç
```
  "nome": string,
  "cargo": string,
  "nascimento": yyyy-mm-dd
```

Contato
```
  "nome": string,
  "contato": string,
  "profissional": int
```

### Criar
Podemos criar uma nova entidade.

Profissional
```
  "nome": string,
  "cargo": string,
  "data_nascimento": yyyy-mm-dd
```

Contato
```
  "nome": string,
  "contato": string,
  "profissional": int
```

### Excluir
Podemos excluir as entidades pelo id.

- Profissional (Exclusão lógica)
- Contato (Exclusão da Entidade)

## Build and Run

Esse projeto foi desenvolvido com Maven e Java 21. Para realizar o build do projeto, podemos rodar o comando abaixo:

``` cmd
  mvn clean install
```

``` cmd
  java -jar target/simples-dental-api-1.0.0.jar
```

### Docker

Esse projeto conta com um Setup em Docker, para facilitar o processo de avaliação e teste.
O Setup em Docker conta om um banco de dados PostgreSQL.
Para rodas o projeto em Docker, podemos ir até a pasta **/scripts/docker** e rodar o seguinte comando:
O script está configurado para rodar na **Porta 80**.

``` cmd
  <sudo> docker-compose up --build
```

### Documentação do Swagger

Uma vez funcionando, podemos acessos o endereço http://localhost/swagger-ui/index.html para acessar o Swagger.
Case o projeto seja executado remotamente, alterar o "localhost" pelo endereço do servidor.

### Variáveis de ambiente

O Projeto conta com algumas variáveis de ambiente, para personalização das comfigurações:

- **SPRING_PROFILES_ACTIVE**: "dev" ou "prod".
- **DB_HOST**: Host do Banco de dados
- **DB_PORT**: Porta do banco de dados
- **DB_USER**: Usuário do banco de dados
- **DB_PASSWORD**: Senha para acesso do banco de dados
- **SERVER_MAX_THREADS**: Máximo de threads do servidor para atender as requisições
- **DB_POOL_SIZE**: Máximo de conexões no Pool
- **DB_CONN_TIMEOUT**: Timeout para pegar uma conexão do banco de dados
- **DB_CONN_LIFETIME**: Tempo de vida das conexões

### Linux

``` cmd
export SPRING_PROFILES_ACTIVE=dev
```

### Windows

``` cmd
set SPRING_PROFILES_ACTIVE=dev
```

``` Powershell
$env:SPRING_PROFILES_ACTIVE="dev"
```

## Contatos e Feedbacks

Agradeceria muito qualquer comentário/feedbacks/melhorias sobre o challenge acima.

Seguem dados de contato

- E-mail: gmtpiagentini@gmail.com
- Celular: (11) 99511-7433
- Site pessoal: www.gpiagentini.com

Muito obrigado pela oportunidade!
