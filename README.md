# JPA App

App REST utilizando Hibernate e JPA.

Testes realizados utilizando o aplicativo Postman.

Deve-se criar um banco de dados MySQL com o nome "restapi" para utilização (ou alterar o spring.datasource.url no arquivo src/main/resources/application.properties).

## Country

GET    http://localhost:8080/country ---------------------------------------- Lista todos os países
POST   http://localhost:8080/country ---------------------------------------- Registra novo país
PUT    http://localhost:8080/country/{countryId} ---------------------------- Atualiza país
DELETE http://localhost:8080/country/{countryId} ---------------------------- Deleta país

Exemplo de POST para criar o registro de um país:
![image](https://user-images.githubusercontent.com/12296364/40627188-dc391978-6293-11e8-996a-f310f6b9fdae.jpg)

Exemplo de GET para mostrar todos os países registrados:
![image](https://user-images.githubusercontent.com/12296364/40627415-45cb984c-6295-11e8-9fe9-92433544b1ec.jpg)

Resultado de SELECT no banco de dados:
![image](https://user-images.githubusercontent.com/12296364/40627425-56977574-6295-11e8-86c8-cd7d3ac48123.jpg)

## User

GET    http://localhost:8080/user ------------------------------------------- Lista todos os usuários
GET    http://localhost:8080/country/{countryId}/user ----------------------- Lista todos os usuários, por país
POST   http://localhost:8080/country/{countryId}/user ----------------------- Registra novo usuário, em um país
PUT    http://localhost:8080/country/{countryId}/user/{userId} -------------- Atualiza usuário
DELETE http://localhost:8080/country/{countryId}/user/{userId} -------------- Deleta usuário

Exemplo de POST para criar o registro de um usuário:
![image](https://user-images.githubusercontent.com/12296364/40627431-64cd52f8-6295-11e8-9652-19086b2e0b5b.jpg)

Exemplo de GET para mostrar todos os usuário registrados para um país:
![image](https://user-images.githubusercontent.com/12296364/40627442-6f7d71e2-6295-11e8-8111-881ce41371fc.jpg)

Resultado de SELECT no banco de dados:
![image](https://user-images.githubusercontent.com/12296364/40627450-78554132-6295-11e8-96c1-eda450b787fa.jpg)