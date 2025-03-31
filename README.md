
# Crud-Vehicle 🚗🚕🚙

## Linguagem 📄

Este README também está disponível em outras línguas:
- 🇺🇸 [Inglês](README-en.md)

## O Projeto 📊

Este é um CRUD simples de veículos desenvolvido com SpringBoot.

## Tecnologias Utilizadas 🧭

- **SpringBoot** - Framework principal
- **Spring Data JPA** - Para interações com o banco de dados
- **H2 Database** - Banco de dados na memória para testes e desenvolvimento
- **Swagger** - Para documentação da API
- **Maven** - Gerenciador de dependências
- **JUnit** - Para testes unitários
- **MockMVC** - Para testes de integração

## Pré-requisitos

- Java 8 ou superior
- Maven
- IDE(recomendado IntelliJ ou VSCode)

## Como rodar o projeto

1. Copie o repositório
```bash
git clone https://github.com/guibarbian/CRUD-Vehicle
cd CRUD-Vehicle
```
2. Instale as dependências
```bash
mvn install
```
3. Rode a aplicação
```bash
mvn spring-boot:run
```
A aplicação vai ser executada em http://localhost:8080

Você pode usar algum cliente de API como Postman ou Insomnia para testar os endpoints manualmente

# Endpoints

Esta API tem os seguintes Endpoints

| Método | Endpoint         | Descrição               |
|--------|------------------|-------------------------|
| GET    | `/vehicles`      | Lista todos os veículos |
| GET    | `/vehicles/{id}` | Acha um veículo por ID  |
| POST   | `/vehicles`      | Cria um novo veículo    |
| PUT    | `/vehicles/{id}` | Atualiza um veículo     |
| DELETE | `/vehicles/{id}` | Deleta um veículo       |

## 

Para criar ou atualizar um veículo, você deve enviar um corpo JSON com os seguintes atributos:
```json
{
  "type":"tipoDoVeículo",
  "brand": "marcaDoVeículo",
  "model": "modeloDoVeículo",
  "manufacturingYear": "anoEmNúmeros"
}
```

⚠️ Os únicos "type"s aceitos são ⚠️
```
"car", "motorcycle" e "truck"
```

⚠️ E eles devem ter um atributo especial ⚠️

````json
{
  "door": "númeroDePortasApenasParaCarro",
  "hasElectricStart": "booleanApenasParaMoto",
  "maxCargo": "cargaMáximaSuportadaApenasParaCaminhão"
}
````


# Desenvolvido com ⚙

- **IntelliJ IDEA**

# Autor ✏

- Guilherme A. Barbian 


