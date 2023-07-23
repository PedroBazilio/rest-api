# rest-api for Perinnity

## Este reposítório contém o código referente ao API requisitada pela Perinity para o controle de pessoas, departamentos e tarefas.*

#### * O projeto é funcional, porém incompleto. Existem regras de negócios que precisam ser revisadas e estão em desenvolvimento.
---

## Conteúdo

- [Tecnologias](#tecnologias)
- [Endpoints](#endpoints)
- [Como Rodar](#como-rodar)

  
## Tecnologias
- Java Spring Boot
- Hibernate
- JPA
- PostgreSQL
- Docker

## Endpoints

- **GET /pessoas**:  Lista funcioários trazendo nome, departamento e total de horas gastas em tarefas.
- **POST /pessoas**: Cria um novo funcionário
- **PUT /pessoas/{id}**: Atualiza um usuário existente.
- **DELETE /pessoas/{id}**: Deleta um funcionário.
- **POST /tarefas**: Cria uma nova tarefa.
- **PUT /tarefas/alocar/{id}**: Aloca um funcinário para uma tarefa.
- **PUT /tarefas/finalizar/{id}**: Finaliza uma tarefa
- **GET /tarefas/pendentes**: Lista 3 tarefas sem um funcionário alocado ordenando da mais antiga para a mais nova


## Como Rodar

#### *Obrsevações: O ambiente utilizado foi linux, com java 17, maven e docker instalados*

### Para rodar o projeto de forma satisfatória, siga os passos:

1 - Clone o repositório:
  `git clone https://github.com/PedroBazilio/rest-api.git`

2 - Abra o projeto no terminal: 
 ```shell
cd rest-api
```
3 - Builde o projeto utilizando Maven:
```shell
mvn clean package -DskipTests
```
4 - Rode o seguinte comando para baixar os containers Docker:
```shell
docker compose up
```
### Após isto, caso não haja erros você terá o projeto rodando e o banco de dados funcional. 
O banco é pré populado e caso deseje acessá-lo é só utilizar o seu gerenciador de bancos preferido e usar as seguintes credenciais:

- Host: localhost
- Port: 5432
- User: postgres
- Password: postgres
- Database: postgres

5 - Após isto, você terá a api disponível em: [localhost](http://localhost:8080)

6 - Para encerrar os contêineres Docker, digite o comando:
```shell
CTRL + C
```
