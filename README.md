# Web Quiz Engine

A multi-users web service for creating and solving quizzes

## **About**

In the Internet, you can often find sites where you need to answer some questions. It can be educational sites, sites with psychological tests, job search services, or just entertaining sites like web quests. The common thing for them is the ability to answer questions (or quizzes) and then see some results. In this project, you will develop a multi-users web service for creating and solving quizzes.

## **Learning outcomes**

You will clearly understand what is the backend development and how to use many modern technologies together to get a great result. If you would like to continue the project, you could develop a web or mobile client for this web service. You will learn about REST API, an embedded database, security, and other technologies.

## **What you'll do and what you'll learn**

- [ ] [Solving a simple quiz](#solving-a-simple-quiz)
- [ ] Lots of quizzes
- [ ] Making quizzes more interesting
- [ ] Moving quizzes to DB
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