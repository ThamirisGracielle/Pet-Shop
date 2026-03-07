# 🐾 Pet Shop - Sistema de Gerenciamento

API REST para gerenciamento de pet shop, incluindo cadastro de clientes, pets, serviços e agendamentos.

## 📋 Sobre o Projeto

Sistema completo para gestão de pet shop desenvolvido com Spring Boot, permitindo o controle de clientes, pets, serviços oferecidos e agendamentos de forma eficiente e organizada.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Lombok**
- **MapStruct**
- **Bean Validation**
- **Swagger/OpenAPI**

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
src/main/java/thamiris/gracielle/pet_shop/
├── controller/          # Endpoints REST
├── service/            # Lógica de negócio
├── repository/         # Acesso ao banco de dados
├── model/              # Entidades JPA
├── dataTransferObject/ # DTOs
├── mapper/             # Conversão entre entidades e DTOs
├── exception/          # Exceções customizadas
└── config/             # Configurações
```

## 📦 Funcionalidades

### 👥 Clientes
- ✅ Cadastrar cliente
- ✅ Listar todos os clientes
- ✅ Buscar cliente por ID
- ✅ Atualizar dados do cliente
- ✅ Remover cliente

### 🐶 Pets
- ✅ Cadastrar pet
- ✅ Listar todos os pets
- ✅ Buscar pet por ID
- ✅ Listar pets por cliente
- ✅ Atualizar dados do pet
- ✅ Remover pet

### 💼 Serviços
- ✅ Cadastrar serviço
- ✅ Listar todos os serviços
- ✅ Buscar serviço por ID
- ✅ Atualizar serviço
- ✅ Remover serviço

### 📅 Agendamentos
- ✅ Criar agendamento
- ✅ Listar todos os agendamentos
- ✅ Buscar agendamento por ID
- ✅ Listar agendamentos por pet
- ✅ Listar agendamentos por data
- ✅ Cancelar agendamento
- ✅ Atualizar status do agendamento

## 🔧 Pré-requisitos

- Java 21 ou superior
- Maven 3.8+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## ⚙️ Configuração

### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/pet-shop.git
cd pet-shop
```

### 2. Configure o banco de dados

Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE pet_shop;
```

### 3. Configure as credenciais

Edite o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pet_shop
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Execute o projeto
```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

Acesse a documentação interativa Swagger:
```
http://localhost:8080/swagger-ui.html
```

## 🔗 Endpoints Principais

### Clientes
```
POST   /cliente       - Criar cliente
GET    /cliente       - Listar clientes
GET    /cliente/{id}  - Buscar cliente
PUT    /cliente/{id}  - Atualizar cliente
DELETE /cliente/{id}  - Remover cliente
```

### Pets
```
POST   /pet           - Criar pet
GET    /pet           - Listar pets
GET    /pet/{id}      - Buscar pet
GET    /pet/dono/{id} - Listar pets por cliente
PUT    /pet/{id}      - Atualizar pet
DELETE /pet/{id}      - Remover pet
```

### Serviços
```
POST   /servicos       - Criar serviço
GET    /servicos       - Listar serviços
GET    /servicos/{id}  - Buscar serviço
PUT    /servicos/{id}  - Atualizar serviço
DELETE /servicos/{id}  - Remover serviço
```

### Agendamentos
```
POST   /agendamento              - Criar agendamento
GET    /agendamento              - Listar agendamentos
GET    /agendamento/{id}         - Buscar agendamento
GET    /agendamento/pet/{id}     - Listar por pet
GET    /agendamento/date/{date}  - Listar por data
PUT    /agendamento/{id}/cancel  - Cancelar agendamento
PUT    /agendamento/{id}/status  - Atualizar status
```

## 📊 Modelo de Dados

### Cliente
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "telefone": "11987654321"
}
```

### Pet
```json
{
  "id": 1,
  "nome": "Rex",
  "especie": "CACHORRO",
  "raca": "Labrador",
  "idade": "2020-05-15",
  "donoId": 1
}
```

### Agendamento
```json
{
  "clientId": 1,
  "petId": 1,
  "serviceId": 1,
  "dataHora": "2024-03-20T10:00:00",
  "appointmentStatus": "AGENDADO"
}
```

## 🛡️ Tratamento de Erros

A API retorna códigos HTTP apropriados:

- `200 OK` - Sucesso
- `201 Created` - Recurso criado
- `204 No Content` - Recurso removido
- `400 Bad Request` - Erro de validação
- `404 Not Found` - Recurso não encontrado
- `409 Conflict` - Recurso duplicado

### Exemplo de resposta de erro:
```json
{
  "error": "Cliente não encontrado com ID: 999"
}
```

## 🧪 Testes

Execute os testes:
```bash
mvn test
```

## 📝 Validações

O sistema implementa validações em:
- Email válido
- Telefone com 10 ou 11 dígitos
- Campos obrigatórios
- Data de agendamento no futuro
- Pet pertence ao cliente no agendamento

## 🎯 Próximos Passos

- [ ] Implementar testes de integração
- [ ] Adicionar autenticação e autorização (Spring Security)
- [ ] Configurar CORS
- [ ] Implementar paginação
- [ ] Adicionar filtros de busca avançados
- [ ] Desenvolver frontend
- [ ] Deploy em produção

## 👨‍💻 Autor

**Thamiris Gracielle**

