<h1 align="center"> CRUD DynamoDB in Java </h1>

<p align="center"> CRUD basico com Java e Spring-boot usando DynamoDB </p>

## :traffic_light: Rotas

### Save Book
```
POST /api/book

{
    "title": "title",
    "author": "author",
    "genre": ["test", "test2"],
    "publisher": "publisher",
    "synopsis": "synopsis",
    "comments": "comments",
    "stars": 5,
    "coverUrl": "coverUrl"
}
```

### Update Book
```
PUT /api/book/{id}

{
    "title": "title",
    "author": "author",
    "genre": ["test", "test2"],
    "publisher": "publisher",
    "synopsis": "synopsis",
    "comments": "comments",
    "stars": 5,
    "coverUrl": "coverUrl"
}
```

### Delete Book
```
DELETE /api/book/{id}
```

### Get Book by Id
```
GET /api/book/{id}
```

### Get All Books
```
GET /api/books
```

### Get All Books by Title
```
GET /api/books/{title}
```

## :arrow_forward: Como Rodar

### Credencias

Primeiro obter as [Credenciais](https://docs.aws.amazon.com/sdk-for-javascript/v2/developer-guide/getting-your-credentials.html) da AWS

Com elas preencher as configurações do application.properites do projeto
```
amazon.dynamodb.accesskey = {{amazon.dynamodb.accesskey}}
amazon.dynamodb.region = {{amazon.dynamodb.region}}
amazon.dynamodb.secretkey = {{amazon.dynamodb.secretkey}}
amazon.dynamodb.endpoint = {{amazon.dynamodb.endpoint}}
```

### Tabela
Criar uma tabela chamada "books" no dynamoDB usando o campo "id" como hash atrribute

### Run
Iniciar projeto
