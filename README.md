# dslist
---
 [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
# 🚀 Sistema de Seleção de Jogos - Spring Boot

Este é um **projeto de estudo** desenvolvido com o framework **Spring Boot**. O objetivo principal é construir uma API RESTful para selecionar e listar jogos de diferentes tipos, como **RPG e Aventura** e **Plataforma**, utilizando um banco de dados **H2** para persistência. A aplicação oferece endpoints para consultar jogos, categorias e associá-los a diferentes listas.

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

O projeto utiliza o banco de dados **H2**, que é embutido no projeto e não exige instalação externa. As principais tabelas no banco de dados são:

- **tb_game**: Armazena os jogos, com informações como título, ano, gênero e plataformas.
- **tb_game_list**: Armazena os tipos de listas de jogos (RPG e Aventura, Plataforma).
- **tb_belonging**: Representa a relação entre jogos e listas, com a posição do jogo na lista.

### Código SQL de Inserção (Import.sql):

```sql
INSERT INTO tb_game_list (name) VALUES ('Aventura e RPG');
INSERT INTO tb_game_list (name) VALUES ('Jogos de plataforma');

-- Inserção de jogos:
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES
('Mass Effect Trilogy', 4.8, 2012, 'Role-playing (RPG), Shooter', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png', 'RPG com elementos de tiro em terceira pessoa.', 'RPG épico com uma trama envolvente e jogabilidade imersiva. Cada escolha do jogador impacta o universo do jogo.'),
('Red Dead Redemption 2', 4.7, 2018, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/2.png', 'Jogo de Aventura com temática de faroeste.', 'Exploração no Velho Oeste, com uma história profunda e escolhas que afetam o desenrolar da trama.'),
('The Witcher 3: Wild Hunt', 4.7, 2014, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/3.png', 'Aventura épica com monstros e magia.', 'Uma história de caçada a monstros, com missões paralelas envolventes e um mundo aberto rico.'),
('Sekiro: Shadows Die Twice', 3.8, 2019, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/4.png', 'Aventura com combate difícil e focado em habilidade.', 'Desafiante e único em seu estilo de combate, com uma narrativa envolvente no Japão feudal.'),
('Ghost of Tsushima', 4.6, 2012, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/5.png', 'Aventura épica com samurais.', 'O último samurai luta para proteger sua terra de invasores mongóis, explorando um mundo aberto cheio de desafios.');

-- Jogos de Plataforma:
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES
('Super Mario World', 4.7, 1990, 'Platform', 'Super Ness, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/6.png', 'Clássico jogo de plataforma com Mario.', 'Jogo de plataforma com a missão de salvar a Princesa Peach.'),
('Hollow Knight', 4.6, 2017, 'Platform', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/7.png', 'Aventura de plataforma metroidvania.', 'Jogo metroidvania com uma narrativa sutil e explorativa em um mundo subterrâneo.'),
('Ori and the Blind Forest', 4, 2015, 'Platform', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/8.png', 'Aventura de plataforma com narrativa emocional.', 'Jogo de plataforma com belíssima arte e uma história comovente sobre o vínculo entre Ori e seu mentor.'),
('Cuphead', 4.6, 2017, 'Platform', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/9.png', 'Plataforma com estética de desenhos animados dos anos 1930.', 'Desafiante e com uma estética única de desenhos animados vintage.'),
('Sonic CD', 4, 1993, 'Platform', 'Sega CD, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/10.png', 'Clássico jogo de plataforma com Sonic.', 'Jogo de plataforma clássico onde Sonic corre para salvar o mundo do vilão Dr. Robotnik.');

-- Relacionamentos (Belonging):
INSERT INTO tb_belonging (list_id, game_id, position) VALUES 
(1, 1, 0), 
(1, 2, 1),
(1, 3, 2),
(1, 4, 3),
(1, 5, 4),
(2, 6, 0),
(2, 7, 1),
(2, 8, 2),
(2, 9, 3),
(2, 10, 4);
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
    {
        "id": 2,
        "title": "Red Dead Redemption 2",
        "year": 2018,
        "imgUrl": "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/2.png",
        "shortDescription": "Jogo de Aventura com temática de faroeste."
    },
    ...
]
```

---
