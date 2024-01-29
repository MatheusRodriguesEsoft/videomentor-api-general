# TCC UniCesumar - VideoMentor

## Informações

- Autor: Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
- Data: 18/10/2023

## Video Mentor - General API

Esse projeto consiste em uma REST API que utiliza Spring Boot Framework Java.

Se você quiser aprender mais sobre o Spring Boot, por favor visite o website: https://spring.io/projects/spring-boot .

### Variáveis de Ambiente

Antes de executar a aplicação, considere configurar as seguintes variáveis de ambiente:

- **POSTGRES_URL**
  - URL do Postgres.


- **POSTGRES_USER**
  - Usuário de acesso ao Postgres.


- **POSTGRES_PASSWORD**
  - Senha do usuário de acesso ao Postgres.


- **POSTGRES_URL_TEST**
  - URL do Postgres para testes.


- **POSTGRES_USER_TEST**
  - Usuário de acesso ao Postgres para testes.


- **POSTGRES_PASSWORD_TEST**
  - Senha do usuário de acesso ao Postgres para testes.

### OpenAPI

Este projeto utiliza a especificação [MicroProfile OpenAPI](https://github.com/eclipse/microprofile-open-api/)
através da implementação [Smallrye OpenAPI](https://github.com/smallrye/smallrye-open-api/).
Utilize (http://localhost:8080/swagger-ui/index.html) para vizualisar a api com o Smallrye OpenAPI em ambiente de desenvolvimento.

A interface do Swagger pode ser acessada em **/openapi/ui** e o documento com as definições da OpenAPI pode
ser acessado em **/openapi/doc**, esses endpoints estarão disponíveis independente do ambiente em que aplicação for executada.

### Gerando aplicação com spring initializr
Essa aplicação foi gerada inicialmente utilizando a ferramenta spring initializr, disponível em https://start.spring.io/ .

### Executando a Aplicação em Dev Mode

Você pode executar sua aplicação em dev mode que permite `live coding` usando:

`$ mvn spring-boot:run`

### Empacotar e Executar a Aplicação

### Configurações por ambiente

As configurações definidas pelo arquivo `application.yaml` ficam separadas por ambiente, **dev** e **prod**, filhas da pasta **src/env**.

Resources comuns para todos os ambientes devem ser colocados na pasta **src/main/resources**, e resources que são específicos por ambiente devem ser colocados na pasta env correspondente.

As definições de resources por ambiente estão no arquivo **pom.xml**, onde há um profile default que carrega o ambiente dev, e no profile native carrega os do ambiente prod.