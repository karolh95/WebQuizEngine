# Web Quiz Engine

A multi-users web service for creating and solving quizzes

## **About**

In the Internet, you can often find sites where you need to answer some questions. It can be educational sites, sites with psychological tests, job search services, or just entertaining sites like web quests. The common thing for them is the ability to answer questions (or quizzes) and then see some results. In this project, you will develop a multi-users web service for creating and solving quizzes.

## **Learning outcomes**

You will clearly understand what is the backend development and how to use many modern technologies together to get a great result. If you would like to continue the project, you could develop a web or mobile client for this web service. You will learn about REST API, an embedded database, security, and other technologies.

## **What you'll do and what you'll learn**

- [x] [Solving a simple quiz](#solving-a-simple-quiz)
- [X] [Lots of quizzes](#lots-of-quizzes)
- [X] [Making quizzes more interesting](#making-quizzes-more-interesting)
- [ ] [Moving quizzes to DB](#moving-quizzes-to-db)
- [ ] User authorization
- [ ] Advanced queries

## Solving a simple quiz

At the first stage, you need to develop a simple JSON API that always returns the same quiz to be solved. The API should support only two operations: getting the quiz and solving it by passing an answer. Each operation is described in more detail below.

Once the stage is completed, you will have a working web service with an comprehensive API.

### Get the quiz

The quiz has exactly three fields: `title` (string) `text` (string) and `options` (array). To get the quiz, the client sends the `GET` request to `/api/quiz`. The server should return the following JSON structure:

```json
{
  "title": "The Java Logo",
  "text": "What is depicted on the Java logo?",
  "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
}
```

In your API, the names of attributes must be exactly the same (`title`, `text`, `options`), but you can assign any values to them. The quiz should contain four items in the `options` array. The correct answer must be the *third option*, but since the indexes start from zero, *its index is 2*.

There is no need to force your server to respond a JSON with line breaks and additional spaces. This is used only to demonstrate the response in a human-readable format. Actually, your server returns a long single-line JSON: `{"title":"The Java Logo","text":"What is depicted on the Java logo?","options":["Robot","Tea leaf","Cup of coffee","Bug"]}`.

### Solve the quiz

To solve the quiz, the client need to pass the `answer` parameter using the `POST` request to `/api/quiz` with content as parameter `answer` and value. This parameter is the index of a chosen option from `options` array. We suppose that in our service indexes start from zero.

The server should return JSON with two fields: `success` (`true` or `false`) and `feedback` (just a string). There are two possible responses from the server:

- If the passed answer is correct (`POST` to `/api/quiz` with content `answer=2`):
    ```json 
    {
        "success":true,
        "feedback":"Congratulations, you're right!"
    }
    ```
- If the answer is incorrect (e.g., `POST` to `/api/quiz` with content `answer=1`):
    ```json 
    {
      "success":false,
      "feedback":"Wrong answer! Please, try again."
    }
    ```
  
You can write any other strings in the `feedback` field, but the names of the fields and the `true`/`false` values must match this example.

## Lots of quizzes

At this stage, you will improve the web service to create, get and solve lots of quizzes, not just a single one. All quizzes should be stored in the service's memory, without an external storage.

The format of requests and responses will be similar to the first stage, but you will make the API more REST-friendly and extendable. Each of the four possible operations is described below.

### Create a new quiz

To create a new quiz, the client needs to send a JSON as the request's body via `POST` to `/api/quizzes`. The JSON should contain the four fields: `title` (a string), `text` (a string), `options` (an array of strings) and `answer` (integer index of the correct option). At this moment, all the keys are optional.

Here is a new JSON quiz as an example:

```json
{
  "title": "The Java Logo",
  "text": "What is depicted on the Java logo?",
  "options": ["Robot","Tea leaf","Cup of coffee","Bug"],
  "answer": 2
}
```

The `answer` equals 2 corresponds to the third item from the `options` array (`"Cup of coffee"`).

The server response is a JSON with four fields: `id`, `title`, `text` and `options`. Here is an example.

```json
{
  "id": 1,
  "title": "The Java Logo",
  "text": "What is depicted on the Java logo?",
  "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
}
```

The `id` field is a generated unique integer identifier for the quiz. Also, the response may or may not include the `answer` field depending on your wishes. This is not very important for this operation.

At this moment, it is admissible if a creation request does not contain some quiz data. In the next stages, we will improve the service to avoid some server errors.

### Get a quiz by id

To get a quiz by `id`, the client sends the `GET` request to `/api/quizzes/{id}`.

Here is a response example:

```json
{
  "id": 1,
  "title": "The Java Logo",
  "text": "What is depicted on the Java logo?",
  "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
}
```

The response **must not** include the `answer` field, otherwise, any user will be able to find the correct answer for any quiz.

If the specified quiz does not exist, the server should return the `404 (Not found)` status code.

### Get all quizzes

To get all existing quizzes in the service, the client sends the `GET` request to `/api/quizzes`.

The response contains a JSON array of quizzes like the following:

```json
[
  {
    "id": 1,
    "title": "The Java Logo",
    "text": "What is depicted on the Java logo?",
    "options": ["Robot","Tea leaf","Cup of coffee","Bug"]
  },
  {
    "id": 2,
    "title": "The Ultimate Question",
    "text": "What is the answer to the Ultimate Question of Life, the Universe and Everything?",
    "options": ["Everything goes right","42","2+2=4","11011100"]
  }
]
```

The response **must not** include the `answer` field, otherwise, any user will be able to find the correct answer for any quiz.

If there are no quizzes, the service returns an empty JSON array: `[]`.

In both cases, the status code is `200 (OK)`.

### Solving a quiz

To solve the quiz, the client sends a `POST` request to `/api/quizzes/{id}/solve` and passes the `answer` parameter in the content. This parameter is the index of a chosen option from `options` array. As before, it starts from zero.

The service returns a JSON with two fields: `success` (`true` or `false`) and `feedback` (just a string). There are three possible responses.

- If the passed answer is correct (e.g., `POST` to `/api/quizzes/1/solve` with content `answer=2`):
    ```json
    {
      "success":true,
      "feedback":"Congratulations, you're right!"
    }
    ```
- If the answer is incorrect (e.g., `POST` to `/api/quizzes/1/solve` with content `answer=1`):
    ```json
    {
      "success":false,
      "feedback":"Wrong answer! Please, try again."
    }
    ```
- If the specified quiz does not exist, the server returns the `404 (Not found)` status code.

You can write any other strings in the feedback field, but the names of fields and the true/false values must match this example.

## Making quizzes more interesting

Currently, your service allows creating new quizzes, but there may be problems if the client didn't provide all the quiz data. In such cases, the service will create an incorrect unsolvable quiz which is very frustrating for those who are trying to solve it.

At this stage, you should fix this so that the service does not accept incorrect quizzes. Another task is to make quizzes more interesting by supporting the arbitrary number of correct options (from zero to all). It means that to solve a quiz, the client needs to send all correct options at once, or zero if all options are wrong.

There are only two modified operations for creating and solving quizzes. All other operations should not be changed or deleted.

### Create a new quiz

To create a new quiz, the client needs to send a JSON as the request's body via `POST` to `/api/quizzes`. The JSON should contain the four fields:

- `title`: a string, **required**;
- `text`: a string, **required**;
- `options`: an array of strings, required, should contain at least 2 items;
- `answer`: an array of indexes of correct options, optional, since all options can be wrong.

Here is a new JSON quiz as an example:

```json
{
  "title": "Coffee drinks",
  "text": "Select only coffee drinks.",
  "options": ["Americano","Tea","Cappuccino","Sprite"],
  "answer": [0,2]
}
```

The `answer` equals `[0,2]` corresponds to the first and the third item from the `options` array (`"Americano"` and `"Cappuccino"`).

The server response is a JSON with four fields: `id`, `title`, `text` and `options`. Here is an example:

```json
{
  "id": 1,
  "title": "Coffee drinks",
  "text": "Select only coffee drinks.",
  "options": ["Americano","Tea","Cappuccino","Sprite"]
}
```

The `id` field is a generated unique integer identifier for the quiz. Also, the response may or may not include the `answer` field depending on your wishes. This is not very important for this operation.

If the request JSON does not contain `title` or `text`, or they are empty strings (`""`), then the server should respond with the `400 (Bad request)` status code. If the number of options in the quiz is less than 2, the server returns the same status code.

### Solving a quiz

To solve a quiz, the client sends the `POST` request to `/api/quizzes/{id}/solve` with a JSON that contains the indexes of all chosen options as the answer. This looks like a regular JSON object with key `"answer"` and value as the array: `{"answer": [0,2]}`. As before, indexes start from zero.

It is also possible to send an empty array `[]` since some quizzes may not have correct options.

## Moving quizzes to DB

At this stage, you will permanently store the data in a database, so that after restarting the service you will not lose all quizzes created by the users. You don't need to change the API of your service at this stage.
We recommend you use the H2 database in the disk-based storage mode (not in-memory).

To start working with it, just add a couple of new dependencies in your `build.gradle` file:

```groovy
dependencies {
    // ...
    runtimeOnly 'com.h2database:h2'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    // ...
}
```
The first dependency will allow using the H2 database in your application, and the second will allow using Spring Data JPA.

You also need to configure the database inside the `application.properties` file. Do not change the database path.

```properties
spring.datasource.url=jdbc:h2:file:../quizdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false
```

This config will automatically create the database and update tables inside it.

You should use exactly the same name for DB: `quizdb`.

If you want to see SQL statements generated by Spring ORM, just add the following line in the properties file:

```properties
spring.jpa.show-sql=true
```

You can use any tables in your database to complete this stage. The main thing is that when you restart the service, quizzes should not be lost. Our tests will create and get them via the API developed at the previous stages.

