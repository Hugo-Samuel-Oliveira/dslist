# dslist
---
 [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
---
# 🚀 Sistema de Seleção de Jogos - Spring Boot

Este é um **projeto de estudo** desenvolvido com o framework **Spring Boot**. O objetivo principal é construir uma API RESTful para selecionar e listar jogos de diferentes tipos, como **RPG e Aventura** e **Plataforma**, utilizando o banco de dados **H2** para testes e **PostgreSQL** para ambiente de desenvolvimento. A aplicação oferece endpoints para consultar jogos, categorias e associá-los a diferentes listas.

⚠️ **Este projeto é destinado a fins educacionais e não deve ser usado em produção.**

## 📌 Funcionalidades

A API expõe os seguintes endpoints para consultar os jogos e suas respectivas listas:

### **1. Jogos**
- **GET /games**: Retorna todos os jogos cadastrados no sistema.
- **GET /games/{id}**: Retorna os detalhes de um jogo específico (determinados pelo `id`).

### **2. Listas de Jogos**
- **GET /lists**: Retorna todos os tipos de listas de jogos (RPG e Aventura, Jogos de Plataforma).
- **GET /lists/{id}/games**: Retorna todos os jogos de uma lista específica. Quando `id` for 1, retorna todos os jogos de **RPG e Aventura**. Quando `id` for 2, retorna todos os jogos de **Plataforma**.

## 🧩 Estrutura do Projeto

A estrutura do código segue a arquitetura típica de uma aplicação Spring Boot, com as seguintes principais classes e componentes:

- **Game**: Entidade principal que representa o jogo e mapeia para a tabela `tb_game` no banco de dados.
- **GameList**: Representa os tipos de listas de jogos (RPG e Aventura, Plataforma), mapeando para a tabela `tb_game_list`.
- **Belonging**: Entidade que representa a relação entre jogos e suas listas, armazenada na tabela `tb_belonging`.
- **DTOs (Data Transfer Objects)**: Objetos usados para transferir dados entre a aplicação e a API, incluindo `GameDTO`, `GameMinDTO`, `GameListDTO`, entre outros.
- **Controllers**: Controladores que definem os endpoints da API RESTful, como `GameController` e `GameListController`.
- **Repositories**: Interfaces que permitem a interação com o banco de dados usando Spring Data JPA, como `GameRepository` e `GameListRepository`.
- **Services**: Camada de serviços que contém a lógica de negócios, como `GameService` e `GameListService`.

## 🛠 Banco de Dados

O projeto usa o banco de dados **H2** para testes e **PostgreSQL** para desenvolvimento. Para configurar o PostgreSQL, foi utilizado o Docker. A seguir, o arquivo `docker-compose.yml` para rodar o PostgreSQL e o PgAdmin:

### 🎯 docker-compose.yml

```yaml
version: "3.7"
services:
  # ====================================================================================================================
  # POSTGRES SERVER
  # ====================================================================================================================
  pg-docker:
    image: postgres:14-alpine
    container_name: dev-postgresql
    environment:
      POSTGRES_DB: mydatabase
      POSTGRES_PASSWORD: 1234567
    ports:
      - 5433:5432
    volumes:
      - ./.data/postgresql/data:/var/lib/postgresql/data
    networks:
      - dev-network
  # ====================================================================================================================
  # PGADMIN
  # ====================================================================================================================
  pgadmin-docker:
    image: dpage/pgadmin4
    container_name: dev-pgadmin
    environment:
      PGADMIN_DEFAULT_EMAIL: me@example.com
      PGADMIN_DEFAULT_PASSWORD: 1234567
    ports:
      - 5050:80
    volumes:
      - ./.data/pgadmin:/var/lib/pgadmin
    depends_on:
      - pg-docker
    networks:
      - dev-network
# ======================================================================================================================
# REDE
# ======================================================================================================================
networks:
  dev-network:
    driver: bridge
```

## 🌍 Como Rodar o Projeto

### Clonando o Repositório

1. Primeiro, clone o repositório do projeto para sua máquina local:

```bash
git clone https://github.com/usuário/repositorio.git
cd repositorio
```

2. Agora, para **executar o projeto no ambiente de testes** com **H2**, mantenha a seguinte configuração no arquivo `application.properties`:

```properties
spring.profiles.active=${APP_PROFILE:test}
```

Isso fará com que a aplicação use o banco de dados **H2** (modo de testes).

3. Para **executar o projeto no ambiente de desenvolvimento** com **PostgreSQL**, altere a configuração no arquivo `application.properties` para:

```properties
spring.profiles.active=${APP_PROFILE:dev}
```

Isso fará com que a aplicação use o **PostgreSQL**.

4. Caso esteja utilizando o ambiente de desenvolvimento, execute o seguinte comando para iniciar os containers do Docker:

```bash
docker-compose up -d
```

5. O banco de dados PostgreSQL será iniciado automaticamente no container `dev-postgresql` na porta `5433`.

6. Para acessar o **PgAdmin** no ambiente de desenvolvimento, abra o navegador e vá para a URL:

```
http://localhost:5050
```

Use as credenciais `me@example.com` / `1234567` para logar no PgAdmin.

7. Conecte-se ao banco de dados PostgreSQL configurado, utilizando as seguintes credenciais:
   - **Host**: `pg-docker`
   - **User**: `postgres`
   - **Password**: `1234567`
   - **Database**: `mydatabase`

### Acessando o Banco de Dados H2 no Modo de Testes

Quando o **perfil de testes** (`test`) está ativo, o H2 é usado como banco de dados. Para acessar o banco de dados **H2**:

1. Ao executar a aplicação, a URL de acesso ao console H2 será exibida no terminal. Geralmente, o endereço padrão do console H2 é:

```
http://localhost:8080/h2-console
```

2. Ao acessar o console H2, use as seguintes credenciais para se conectar ao banco de dados:

   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **User**: `sa`
   - **Password**: (deixe em branco)

3. Após logar, você poderá visualizar e consultar as tabelas no banco de dados H2.

## 🗂 Estrutura das Tabelas

As principais tabelas no banco de dados PostgreSQL são:

- **tb_game**: Armazena os jogos, com informações como título, ano, gênero e plataformas.
- **tb_game_list**: Armazena os tipos de listas de jogos (RPG e Aventura, Plataforma).
- **tb_belonging**: Representa a relação entre jogos e listas, com a posição do jogo na lista.

#### Código SQL de Inserção (Import.sql):

```sql
-- Inserção das listas de jogos:
INSERT INTO tb_game_list (name) VALUES ('Aventura e RPG');
INSERT INTO tb_game_list (name) VALUES ('Jogos de plataforma');

-- Inserção de jogos:
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES
('Mass Effect Trilogy', 4.8, 2012, 'Role-playing (RPG), Shooter', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png', 'RPG com elementos de tiro em terceira pessoa.', 'RPG épico com uma trama envolvente e jogabilidade imersiva. Cada escolha do jogador impacta o universo do jogo.');
...
```

## 🌍 Exemplo de Uso dos Endpoints

### **1. Consultando todos os jogos**

Para consultar todos os jogos cadastrados no sistema, utilize o seguinte endpoint:

```bash
GET /games
```

**Resposta esperada:**
```json
[
    {
        "id": 1,
        "title": "Mass Effect Trilogy",
        "year": 2012,
        "genre": "RPG",
        "platforms": "XBox, Playstation, PC",
        "score": 4.8,
        "imgUrl": "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png",
        "shortDescription": "RPG com elementos de tiro em terceira pessoa.",
        "longDescription": "RPG épico com uma trama envolvente e jogabilidade imersiva. Cada escolha do jogador impacta o universo do jogo."
    },
    ...
]
```

### **2. Consultando detalhes de um jogo específico**

Para consultar os detalhes de um jogo específico, basta utilizar o `id` do jogo:

```bash
GET /games/{id}
```

**Exemplo:**

```bash
GET /games/1
```

**Resposta esperada:**
```json
{
    "id": 1,
    "title": "Mass Effect Trilogy",
    "year": 2012,
    "genre": "RPG",
    "platforms": "XBox, Playstation, PC",
    "score": 4.8,
    "imgUrl": "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png",
    "shortDescription": "RPG com elementos de tiro em terceira pessoa.",
    "longDescription": "RPG épico com uma trama envolvente e jogabilidade imersiva. Cada escolha do jogador impacta o universo do jogo."
}
```

### **3. Consultando todas as listas de jogos**

Para obter todos os tipos de listas de jogos:

```bash
GET /lists
```

**Resposta esperada:**
```json
[
    {
        "id": 1,
        "name": "Aventura e RPG"
    },
    {
        "id": 2,
        "name": "Jogos de plataforma"
    }
]
```

### **4. Consultando jogos de uma lista específica**

Para obter jogos de uma lista específica, utilize o `id` da lista. Por exemplo:

```bash
GET /lists/1/games
```

**Resposta esperada:**
```json
[
    {
        "id": 1,
        "title": "Mass Effect Trilogy",
        "year": 2012,
        "imgUrl": "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png",
        "shortDescription": "RPG com elementos de tiro em terceira pessoa."
    },
    ...
]
```

---

## 📝 Observação para Gerar o `create.sql`

1. No arquivo `application-dev.properties`, **descomente as linhas** abaixo para gerar automaticamente o arquivo `create.sql` que criará as tabelas no banco de dados:

```properties
#spring.jpa.properties.jakarta.persistence.schema-generation.create-source=metadata
#spring.jpa.properties.jakarta.persistence.schema-generation.scripts.action=create
#spring.jpa.properties.jakarta.persistence.schema-generation.scripts.create-target=create.sql
#spring.jpa.properties.hibernate.hbm2ddl.delimiter=;
```

2. O arquivo `create.sql` será gerado automaticamente pela aplicação. Após isso, copie o conteúdo gerado.

3. **No PgAdmin**, crie um novo banco de dados com o nome `dslist`.

4. Após criar o banco de dados, cole o conteúdo do `create.sql` gerado no editor de queries do PgAdmin e execute-o para criar as tabelas necessárias.

Agora, o projeto estará configurado para usar **H2** para testes e **PostgreSQL** para desenvolvimento, e você poderá acessar as tabelas criadas diretamente no PgAdmin.

---
