# 🚀 DESAFIO TÉCNICO - SISTEMA DE DELIVERY

## 📋 Contexto

Sistema de delivery desenvolvido em Java/Spring Boot similar ao iFood/Uber Eats. O sistema gerencia restaurantes, produtos, pedidos e usuários com autenticação JWT e diferentes perfis de acesso.

## 🎯 REQUISITOS FUNCIONAIS

### 1. Gestão de Usuários
- ✅ Cadastro de clientes
- ✅ Login/logout com JWT
- ✅ Perfis: CLIENTE, RESTAURANTE, ADMIN

### 2. Gestão de Restaurantes
- ✅ CRUD de restaurantes
- ✅ Categorias (Italiana, Brasileira, Fast Food, etc.)
- ✅ Status: ABERTO, FECHADO, PAUSADO
- ✅ Horário de funcionamento

### 3. Gestão de Produtos
- ✅ CRUD de produtos por restaurante
- ✅ Categorias de produtos (Bebidas, Pratos principais, etc.)
- ✅ Produtos indisponíveis

### 4. Sistema de Pedidos
- ✅ Criar pedido (carrinho → pedido)
- ✅ Status do pedido: PENDENTE → CONFIRMADO → PREPARANDO → SAIU_ENTREGA → ENTREGUE → CANCELADO
- ✅ Cálculo de taxa de entrega
- ✅ Histórico de pedidos

## 🏗 ESTRUTURA TÉCNICA

### Stack Utilizada
- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL** (Produção)
- **H2** (Testes)
- **Maven**
- **JUnit 5 + Mockito**
- **Lombok**

### Arquitetura do Projeto
```
src/main/java/Desafio/Tecnico/sistema_delivery/
├── controller/     # REST Controllers - Endpoints da API
├── service/        # Regras de negócio
├── repository/     # Acesso a dados (JPA Repositories)
├── dto/           # DTOs para requests/responses
├── entity/        # Entidades JPA
├── config/        # Configurações (Security, CORS, etc.)
├── exception/     # Tratamento de exceções customizadas
└── util/          # Classes utilitárias
```

## 🚀 Como Executar

### Pré-requisitos
- Java 21+
- Maven 3.6+
- PostgreSQL (opcional - pode usar H2)

### Configuração do Banco de Dados

#### PostgreSQL (Produção)
```properties
# src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_delivery
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

#### H2 (Desenvolvimento/Testes)
```properties
# src/main/resources/application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
```

### Executando a Aplicação

#### Via Maven
```bash
# Clone o repositório
git clone <url-do-repositorio>
cd sistema-delivery

# Execute a aplicação
./mvnw spring-boot:run

# Ou no Windows
mvnw.cmd spring-boot:run
```

#### Via IDE
1. Importe o projeto como Maven Project
2. Execute a classe `SistemaDeliveryApplication.java`

### Acessos
- **API Base URL**: `http://localhost:8080`
- **H2 Console** (se habilitado): `http://localhost:8080/h2-console`
- **Swagger/OpenAPI** (se configurado): `http://localhost:8080/swagger-ui.html`

## 📚 Endpoints da API

### Autenticação
```http
POST /api/auth/register    # Cadastro de usuário
POST /api/auth/login       # Login
POST /api/auth/logout      # Logout
```

### Usuários
```http
GET    /api/users          # Listar usuários (ADMIN)
GET    /api/users/{id}     # Buscar usuário por ID
PUT    /api/users/{id}     # Atualizar perfil
DELETE /api/users/{id}     # Deletar usuário (ADMIN)
```

### Restaurantes
```http
GET    /api/restaurants           # Listar restaurantes
GET    /api/restaurants/{id}      # Buscar restaurante por ID
POST   /api/restaurants           # Criar restaurante (RESTAURANTE/ADMIN)
PUT    /api/restaurants/{id}      # Atualizar restaurante
DELETE /api/restaurants/{id}      # Deletar restaurante
GET    /api/restaurants/category/{categoria}  # Buscar por categoria
```

### Produtos
```http
GET    /api/products                    # Listar produtos
GET    /api/products/{id}               # Buscar produto por ID
POST   /api/products                    # Criar produto (RESTAURANTE)
PUT    /api/products/{id}               # Atualizar produto
DELETE /api/products/{id}               # Deletar produto
GET    /api/restaurants/{id}/products   # Produtos de um restaurante
```

### Pedidos
```http
GET    /api/orders              # Listar pedidos do usuário
GET    /api/orders/{id}         # Buscar pedido por ID
POST   /api/orders              # Criar pedido
PUT    /api/orders/{id}/status  # Atualizar status do pedido
GET    /api/orders/history      # Histórico de pedidos
```

## 🧪 Testes

### Executar Testes
```bash
# Todos os testes
./mvnw test

# Testes específicos
./mvnw test -Dtest=SistemaDeliveryApplicationTests
```

### Estrutura de Testes
```
src/test/java/Desafio/Tecnico/sistema_delivery/
├── controller/     # Testes de integração dos controllers
├── service/        # Testes unitários dos services
├── repository/     # Testes dos repositories
└── integration/    # Testes de integração end-to-end
```

## 🔒 Segurança

### JWT Configuration
- **Secret Key**: Configurada via application.properties
- **Expiração**: 24 horas (configurável)
- **Refresh Token**: Implementado para renovação automática

### Perfis de Acesso
- **CLIENTE**: Criar pedidos, visualizar restaurantes e produtos
- **RESTAURANTE**: Gerenciar próprios produtos e pedidos
- **ADMIN**: Acesso total ao sistema

## 📊 Modelo de Dados

### Principais Entidades
- **User**: Usuários do sistema (clientes, restaurantes, admins)
- **Restaurant**: Restaurantes cadastrados
- **Product**: Produtos oferecidos pelos restaurantes
- **Order**: Pedidos realizados
- **OrderItem**: Itens de um pedido

### Status dos Pedidos
```
PENDENTE → CONFIRMADO → PREPARANDO → SAIU_ENTREGA → ENTREGUE
                    ↓
                CANCELADO
```

## 🛠 Ferramentas de Desenvolvimento

### Maven Wrapper
O projeto inclui o Maven Wrapper, não sendo necessário instalar o Maven:
```bash
./mvnw clean install    # Linux/Mac
mvnw.cmd clean install  # Windows
```

### Lombok
Utilizado para reduzir boilerplate code. Certifique-se de ter o plugin do Lombok instalado na sua IDE.

## 📈 Próximos Passos

- [ ] Implementar cache com Redis
- [ ] Adicionar documentação Swagger/OpenAPI
- [ ] Implementar notificações em tempo real
- [ ] Adicionar sistema de avaliações
- [ ] Implementar geolocalização para cálculo de entrega
- [ ] Adicionar testes de performance

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---

**Desenvolvido como parte do Desafio Técnico - Sistema de Delivery** 🚀
