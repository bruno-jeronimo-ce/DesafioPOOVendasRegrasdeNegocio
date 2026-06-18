# API REST - Sistema de Vendas

## Descrição

Projeto desenvolvido para a disciplina de Orientação a Objetos Avançado utilizando Java 17, Spring Boot, JPA/Hibernate e banco de dados H2.

O sistema permite o gerenciamento de clientes, produtos, estoque e vendas, aplicando os princípios SOLID.

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3
* Spring Data JPA
* Hibernate
* H2 Database
* Maven
* Lombok

---

## Estrutura do Projeto

```text
src/main/java/vendas

├── controller
├── dto
│   ├── request
│   └── response
├── exception
├── model
├── repository
├── service
│   └── interfaces
├── strategy
└── VendasApplication.java
```

---

## Endpoints

### Clientes

* POST /api/clientes
* GET /api/clientes
* GET /api/clientes/{id}
* PUT /api/clientes/{id}
* DELETE /api/clientes/{id}

### Produtos

* POST /api/produtos
* GET /api/produtos
* GET /api/produtos/{id}
* PUT /api/produtos/{id}
* DELETE /api/produtos/{id}

### Estoque

* GET /api/estoque
* GET /api/estoque/{produtoId}
* PUT /api/estoque/{produtoId}

### Vendas

* POST /api/vendas
* GET /api/vendas
* GET /api/vendas/{id}
* GET /api/vendas/cliente/{clienteId}

---

## Regras de Negócio

### Estoque

* Não permite estoque negativo.
* Todo produto possui estoque associado.
* O estoque é atualizado automaticamente.

### Vendas

* Uma venda pertence a um cliente.
* Uma venda possui um ou mais itens.
* O estoque é validado antes da venda.
* O estoque é decrementado automaticamente.
* O valor total da venda é calculado automaticamente.
* Vendas sem estoque suficiente são rejeitadas.

---

## Princípios SOLID Aplicados

### S - Single Responsibility Principle

* Controllers: requisições HTTP
* Services: regras de negócio
* Repositories: acesso aos dados
* DTOs: transporte de dados

### O - Open/Closed Principle

Utilização de estratégia de cálculo de vendas.

### L - Liskov Substitution Principle

Implementações podem substituir suas abstrações sem alterar o comportamento.

### I - Interface Segregation Principle

Interfaces específicas para cada módulo.

### D - Dependency Inversion Principle

Controllers dependem de interfaces e não de implementações concretas.

---

## Autor

Bruno Jeronimo

Curso: Análise e Desenvolvimento de Sistemas

Disciplina: Orientação a Objetos Avançado

Faculdade Princesa do Oeste
