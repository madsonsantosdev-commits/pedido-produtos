# Pedido de Produtos — Java (Spring Boot) + Angular + SQL Server (Clean Architecture)

Aplicação full stack para gestão de pedidos de produtos, com CRUD completo (criar, listar, detalhar, atualizar e remover) usando:
Back-end: Java + Spring Boot + MVC (REST API)
Front-end: Angular
Banco: SQL Server
Arquitetura: Clean Architecture, com separação entre domínio, casos de uso, infraestrutura e interfaces.
O projeto foi pensado para ser um exemplo prático de boas práticas de organização, manutenibilidade e testabilidade — ideal para portfólio e estudo.

# Funcionalidades (MVP)

Produtos
Criar produto
Listar produtos
Buscar produto por ID
Atualizar produto
Remover produto
Pedidos
Criar pedido com itens (produto + quantidade)
Listar pedidos
Buscar pedido por ID (com itens)
Atualizar status do pedido (ex: CRIADO, PAGO, ENVIADO, CANCELADO)
Remover pedido
Regras (exemplos)
Pedido deve ter ao menos 1 item
Quantidade do item deve ser > 0
Total do pedido = soma (preço do produto * quantidade)

# Modelagem (entidades principais)

Product

id (UUID/int)

name
price
active
createdAt
Order
id
customerName
status
total
createdAt
OrderItem
id
orderId
productId
quantity
unitPrice
lineTotal

# Clean Architecture (visão geral)

A aplicação é dividida em camadas com dependências apontando sempre para o centro (Domínio):

Domain (Enterprise Rules)
Entidades e regras do negócio puras (sem framework).

Application (Use Cases)
Casos de uso (services) e portas (interfaces) como OrderRepository.

Infrastructure (Frameworks & Drivers)
Implementações concretas: JPA, SQL Server, clients, etc.

Interface Adapters (Web/API)
Controllers REST, DTOs, mappers.

# API (exemplo de endpoints)

Products

POST /api/products

GET /api/products

GET /api/products/{id}

PUT /api/products/{id}

DELETE /api/products/{id}

Orders

POST /api/orders

GET /api/orders

GET /api/orders/{id}

PATCH /api/orders/{id}/status

DELETE /api/orders/{id}

# Tecnologias

Back-end
Java 17+
Spring Boot (Web, Validation, Data JPA)
Maven/Gradle
SQL Server Driver
Flyway (opcional, recomendado) para migrations
OpenAPI/Swagger
Front-end
HttpClient
Componentização por features
Banco
SQL Server

pedido-produtos/
├─ src/main/java/com/madson/pedidoprodutos/
│  ├─ PedidoProdutosApplication.java
│  ├─ domain/
│  │  ├─ entities/
│  │  ├─ enums/
│  │  └─ exceptions/
│  ├─ application/
│  │  ├─ ports/
│  │  │  ├─ in/
│  │  │  └─ out/
│  │  ├─ services/
│  │  └─ dto/
│  ├─ adapters/
│  │  ├─ web/
│  │  │  ├─ controllers/
│  │  │  ├─ requests/
│  │  │  ├─ responses/
│  │  │  ├─ mappers/
│  │  │  └─ exceptionhandler/
│  │  └─ persistence/
│  │     ├─ jpa/
│  │     │  ├─ entities/
│  │     │  ├─ repositories/
│  │     │  └─ mappers/
│  │     └─ adapters/
│  └─ config/
├─ src/main/resources/
│  ├─ application.yml
│  └─ db/migration/
└─ pom.xml