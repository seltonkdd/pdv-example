# 💻 Exemplo de PDV

Projeto feito com Spring Boot para simular um PDV simples de mercado. 
API Rest de cadastro de clientes e produtos e consulta de produtos e preços por ID.

## Tecnologias Utilizadas
- **Java**
- **Spring Boot**
- **Maven**
- **PostgreSQL 17**
- **Spring Data JPA**
- **Swagger UI**

## Estrutura do projeto

```
src/
├── main/
│   ├── java/selton/dev/pdv_exemplo/
│   │   ├── controller/
│   │   │   └── PDVController.java
│   │   ├── dto/
│   │   │   ├── ClienteDTO.java
│   │   │   ├── PDVRequest.java
│   │   │   └── PDVResponse.java
│   │   ├── exception/
│   │   │   ├── ErrorMessage.java
|   |   |   ├── GlobalExceptionHandler.java
│   │   │   └── custom/
│   │   │       ├── CpfInvalidoException.java
│   │   |       └── EntityNotFoundException.java
│   │   ├── model/
│   │   │   ├── Cliente.java
|   |   |   └── Produto.java
|   |   ├── repository/
|   |   |   ├── ClienteRepository.java
|   |   |   └── ProdutoRepository.java
|   |   ├── service/
|   |   |   ├── ClienteService.java
|   |   |   ├── PDVService.java
|   |   |   └── ProdutoService.java
|   |   ├── utils/
|   |   |   └── CpfUtils.java
│   │   └── PdvExemploApplication.java
│   └── resources/
└── test
```

## Entidades

**Cliente**

| Atributo | Tipo | Obrigatório |
|------|---------------|-----------|
| **ID** | Long | Sim |
| **Nome** | String | sim |
| **CPF** | String | Sim |

**Produto**

| Atributo | Tipo | Obrigatório |
|------|---------------|-----------|
| **ID** | Long | Sim |
| **Nome** | String | sim |
| **Preço** | Double | Sim |
| **Preço fidelidade** | Double | Não |

## Endpoints

### POST `/api/pdv/clientes`

Cadastra um cliente novo

**Request Body:**

```json
{
    "nome": "João",
    "cpf": "12345678911"
}
```

**Response: 201 CREATED**

### GET `/api/pdv/clientes?cpf=12345678911`

Busca um cliente pelo CPF.

**Response: 200 OK**

```json
{
    "nome": "João",
    "cpf": "12345678911"
}
```

### POST `/api/pdv/produtos`

Cadastra um produto novo.

**Request Body:**

```json
{
    "nomeProduto": "Salgadinho Doritos",
    "preco": 10.90,
    "precoFidelidade": 8.90 //pode ser nulo
}
```

**Response: 201 CREATED**

### GET `/api/pdv/produtos/{id}`

Busca um produto pelo ID.

**Response: 200 OK**

```json
{
    "id": 1,
    "nomeProduto": "Salgadinho Doritos",
    "preco": 10.90,
    "precoFidelidade": 8.90
}
```

### DELETE `/api/pdv/produtos/{id}`

Deleta um produto pelo ID.

**Response: 204 NO_CONTENT**

### POST `/api/pdv`

Faz uma consulta do produto e seu preço padrão/fidelidade se cpf for informado e o produto tenha um preço fidelidade.

**Request Body**

```json
{
    "id": 1,
    "cpf": "12345678911"
}
```

**Response: 200 OK**

```json
{
    "id": 1,
    "nomeProduto": "Salgadinho Doritos",
    "preco": 8.90
}
```

## Como Executar
### Pré-requisitos:
>>> PostgreSQL 17
Configure seu `aplication.properties`:

```
spring.application.name=pdv-exemplo
spring.datasource.url=jdbc:postgresql://localhost:5432/pdv_banco
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Para rodar:
   ```sh
   ./mvnw spring-boot:run
   ```
Acesse a aplicação em `http://localhost:8080`.

## Documentação
Acesse `http://localhost:8080/swagger-ui/index.html`