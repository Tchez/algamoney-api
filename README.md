# Algamoney API
> Projeto em desenvolvimento

### Esse projeto é uma API que estou desenvolvendo para aprimorar meus conhecimentos em Java e Spring Boot.

<br/>

## Como executar 

<br/>

### Pré-requisitos

- Docker e Docker Compose instalados.

### Configuração

#### Clone este repositório para sua máquina local.
```bash
   git clone https://github.com/Tchez/algamoney-api
   cd algamoney-api
```


#### Renomeie o arquivo `application.properties.example` para `application.properties`.

```bash
mv src/main/resources/application.properties.example src/main/resources/application.properties
```

#### Construa e inicie os containers usando Docker Compose.

```bash
docker-compose up --build
```
> use a flag -d caso queira ocultar execução

## Uso

### Endpoints disponíveis:

CRUD de Aluno

CRUD de Categoria - em desenvolvimento
