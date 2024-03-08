# Desafio Triágil

API Restful que permite que usuários criem e gerenciem times de Pokémon de forma simples e eficiente.

## Tecnologias utilizadas

<div>
<img height="30" width="40" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Java-Light.svg"/>
<img height="30" width="40" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Spring-Light.svg" />
<img height="30" width="40" src="https://github.com/tandpfun/skill-icons/blob/main/icons/MySQL-Light.svg" />
<img height="30" width="40" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Docker.svg"/>
</div>

## 1 . Instalação

#### Passo 1 - Clone o projeto

```shell
cd \
git clone https://github.com/lucasdanilo1/desafio-triagil/
```

#### Passo 2 - Acesse a pasta do projeto

```shell
cd desafio-triagil
```

#### Passo 3 - Execute o docker-compose

```shell
docker-compose up
```

-------------------------------------------------------------------------------------------------------------

Após a conclusão do processo, a aplicação estará prontamente acessível através do seguinte endereço: http://localhost:8080.

Além disso, para uma exploração mais detalhada dos endpoints disponíveis e suas funcionalidades, a aplicação foi documentada utilizando o Swagger. Você pode acessar esta documentação em: http://localhost:8080/swagger-ui/index.html#/

-------------------------------------------------------------------------------------------------------------

#### Funcionalidades

1. **Criação de time:**
   
   - **Descrição:** Rota para criação de um time.
   - **Método:** POST
   - **Endpoint:** `/api/teams`

2. **Listagem de times registrados:**
   
   - **Descrição:** Lista todos os times registrados.
   - **Método:** GET
   - **Endpoint:** `/api/teams`

3. **Listagem de times registrados por usuário**
   
   - **Descrição:** Busca times registrados por determinado usuário.
   - **Método:** GET
   - **Endpoint:** `/api/teams/{user}`
