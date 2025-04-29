![Logo Finance Control](./imgs/financeControlLogo.png)

---

![GitHub License](https://img.shields.io/github/license/danielBRTanimacao/FinanceControl?labelColor=101010)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/danielBRTanimacao/FinanceControl/XXXXXX.yml?style=flat&labelColor=101010)

Já aconteceu de dia 5 ou dia 15 acordar felizão com o salário na conta e não ter ideia de onde foi cada centavo dois dias depois? Aqui é onde o Finance Control entra em ação! Com ele você consegue controlar suas finanças - literalmente - rastreando onde vai cada real através de transações cadastradas na plataforma: coloque o dia que seu salário caiu, registre cada compra ou investimento que fizer e tenha de forma visível o seu fluxo de caixa, simples como torrar todo o seu dinheiro...

## Stack 🛠️

![Java](https://img.shields.io/badge/Java-ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

![Vue.js](https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![TailwindCSS](https://img.shields.io/badge/tailwindcss-38B2AC.svg?style=for-the-badge&logo=tailwind-css&logoColor=white)

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

![GitHub](https://img.shields.io/badge/GitHub-fff?style=for-the-badge&logo=github&logoColor=181717)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088ff?style=for-the-badge&logo=github-actions&logoColor=fff)

## Arquitetura 📊

A arquitetura escolhida para o projeto quanto à nivel de repositório foi a de "monorepo", onde o projeto se divide em duas ou mais estruturas (diretórios) diferentes mas dentro do mesmo repositório. É possível observar os diretórios `backend/` e `frontend/` dentro deste repositório, e isso caracteriza a estrutura monorepo.

### Front-End 🌞

Quanto ao diretório `frontend/` a escolha - deveras polêmica, com manipulação de votação - foi o Vue (gerenciado com auxilio do Vite), que é um framework progressivo para construção de interfaces web reativas. Sua arquitetura é baseada no padrão MVVM (Model-View-ViewModel), promovendo separação de responsabilidades e reatividade declarativa. Cuidado para não confundir com Model de MVVM com Model de MVC: Model em MVVM (Vue) representa um estado de um componente, enquanto Model em MVC diz respeito à uma tabela do banco de dados.

A View é definida com templates baseados em HTML, que são compilados em funções de renderização. O ViewModel, representado pela instância Vue, atua como intermediário entre a View e o Model, expondo dados reativos e manipulando eventos.

A reatividade é garantida por um sistema de observação eficiente, baseado em proxies ou `Object.defineProperty`, que rastreia dependências e propaga mudanças automaticamente para a UI.

A arquitetura é modular: componentes Vue encapsulam template, lógica e estilo, promovendo reutilização. O Vue Router gerencia navegação entre views, e o Pinia (ou Vuex, em versões anteriores) oferece gerenciamento de estado global previsível.

O build é orientado pelo Vite, que possui suporte a TypeScript, SSR (via Nuxt.js), e integração com REST/GraphQL.

```mermaid
flowchart LR

User((User))


subgraph VUE
    Router[Vue Router]:::Arch
    Component[Vue Component]:::Arch
    Store[State Store]:::Arch
    ViewModel{{Reactivity System}}:::Arch
end

subgraph BACKEND
    Backend{Spring}
end


User --HTTP:REQUEST--> Router --> Component
Component --> ViewModel
Component --> Store
Store --Fetch / Persist--> Backend
Backend --Return Data--> Store
ViewModel --> Component
Store --> Component --> Router --HTTP:RESPONSE--> User


style User fill:#dff,color:#00f,stroke:#00f;

style VUE fill:#111,color:#42b883,stroke:#42b883;
classDef Arch fill:#35495e,color:#42b883,stroke:#42b883;

style BACKEND fill:#f7f7f7,color:#070,stroke:#070;
style Backend fill:#080,color:#f7f7f7,stroke:#f7f7f7;


linkStyle 0,1,2,3,4 stroke:#42b883,color:#fff
linkStyle 5,6,7,8,9 stroke:#2288a3,color:#fff
```

### Back-End 🌚

A nível de projeto, o diretório `backend/` apresenta a estrutura padrão do Spring com Java; O Spring é um framework modular e extensível, amplamente utilizado no desenvolvimento de aplicações Java corporativas. Sua arquitetura é baseada em princípios como Inversão de Controle (IoC), Injeção de Dependência (DI) e Programação Orientada a Aspectos (AOP), promovendo alta coesão, baixo acoplamento e fácil testabilidade.

O núcleo da arquitetura é o Spring Core Container, responsável por gerenciar o ciclo de vida dos beans (componentes da aplicação), configurados via anotações ou arquivos XML. O contêiner utiliza o ApplicationContext para carregar definições de beans e prover serviços como internacionalização, propagação de eventos e acesso a recursos.

A camada de acesso a dados é abstraída pelo módulo Spring Data, que se integra facilmente com JPA, JDBC e bancos NoSQL. O uso de repositórios permite encapsular regras de persistência com interfaces, eliminando grande parte do boilerplate code.

```mermaid
flowchart LR

Front{Front}

subgraph CLOUD
    subgraph SPRING
        Controller[Controller]:::Arch
        Service[Service Layer]:::Arch
        Repository[Repository]:::Arch
        Entity{{Entity/DTO}}:::Arch
    end

    subgraph DATABASE
        DB[(PostgreSQL)]
    end
end

Front --Fetch / Persist--> Controller
Controller --> Service
Service --> Repository
Repository -->|Query / Persist| Entity
Entity --SQL Query--> DB
DB --SQL Result--> Entity --> Repository --> Service --> Controller --Return Data--> Front

style Front fill:#35495e,color:#42b883,stroke:#42b883;

style CLOUD fill:#777,color:#0f0,stroke:#ccc;
style SPRING fill:#ddd,color:#070,stroke:#fff;

style DATABASE fill:#fff,color:#336791,stroke:#336791;
style DB fill:#336791,color:#fff,stroke:#fff;

classDef Arch fill:#080,color:#efe,stroke:#efe;


linkStyle 0,1,2,3,4 stroke:#ff7820,color:#fff
linkStyle 5,6,7,8,9 stroke:#070,color:#fff
```

### Banco de Dados

A persistência dos dados gerenciados e armazenados pela aplicação se dão com um banco de dados relacional: PostgreSQL. Migrações de banco de dados pode ser gerenciadas pelo próprio Spring, mas não é o mais indicado em cenários de produção. As tabelas do banco são definidas no Spring através da anotação `@Entity` nas classes escritas - por motivos de boas práticas - em `entity/`, sub-diretório de `backend/`.

![DB Schema](./imgs/db-schema.svg)

<!--
// Favor NÃO remover estes comentários
// Código de geração do SVG acima, gerado em https://dbdiagram.io/d
// Use DBML to define your database structure
// Docs: https://dbml.dbdiagram.io/docs


Project "Finance Control" {
  database_type: 'PostgreSQL'
}


Table "category_entity" {
  "id" SERIAL [pk, increment]
  "name" VARCHAR(255) [not null]
}

Table "users" {
  "id" UUID [pk]
  "name" VARCHAR(50) [not null]
  "email" VARCHAR(255) [unique, not null]
  "password" VARCHAR(255) [not null]
}

Table "transaction_entity" {
  "id" SERIAL [pk, increment]
  "category_id" INTEGER [not null]
  "user_id" UUID [not null]
  "value" NUMERIC
  "earned_date" DATE
  "created_date" TIMESTAMP
  "updated_date" TIMESTAMP
}

Ref "fk_transaction_category":"category_entity"."id" < "transaction_entity"."category_id"
Ref "fk_transaction_user":"users"."id" < "transaction_entity"."user_id"
 -->

## Requisitos

- Node >= 23.0.0
- NPM >= 10.0.0
- Java/JDK >= 17.0.0

Para que tudo funcione perfeitamente, tanto Front quanto Back, assim como a conexão entre eles, é importante definir as variáveis de ambiente no seu ambiente de desenvolvimento. Abaixo a listagem de quais definir, tendo como modelo o arquivo `./frontend/.env-example`:

| Variável                | Tipo     | Necessidade | Default | Descrição                            |
| :---------------------- | :------- | :---------: | :------ | :----------------------------------- |
| `VUE_APP_API_BASE_PATH` | `string` |  Required   | -       | Path padrão para URL da API          |
| `VUE_APP_API_USER_PATH` | `string` |  Required   | -       | Path padrão para endpoint de usuário |

## Comandos

### Front-End

#### Configurar o Setup

```sh
npm install
```

#### Iniciar Servidor Local

```sh
npm run dev
```

#### Compilar para Produção

```sh
npm run build
```

### Back-End

#### Configurar o Setup

```sh
mvn clean install
```

#### Iniciar Servidor Local

```sh
mvn spring-boot:run
```

#### Rodar Testes Automatizados

```sh
mvn tests
```

<!--
## To-Do List

-   [ ] Lista
-   [ ] de
-   [ ] Tarefas
-->

## Licença

This project is under [MIT - Massachusetts Institute of Technology](https://choosealicense.com/licenses/mit/). A short and simple permissive license with conditions only requiring preservation of copyright and license notices. Licensed works, modifications, and larger works may be distributed under different terms and without source code.
