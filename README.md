# redbee-challenge

Clone and Install a mySQL engine. Provide the connection details to the [application.conf](https://github.com/mbarbuscio/redbee-challenge/blob/master/challenge/conf/application.conf).

Run the [SQL DDL Script](https://github.com/mbarbuscio/redbee-challenge/blob/master/sql/DDL.sql) to create the database.

Open the [project](https://github.com/mbarbuscio/redbee-challenge/tree/master/challenge) and run with SBT

Go to (http://localhost:9000) or call to (http://localhost:9000/api) with the methods below

## Assumptions

No actual user auth is provided. In a real-world scenario, an authority would authenticate requests and the current [UserHandler](https://github.com/mbarbuscio/redbee-challenge/blob/master/challenge/app/utils/actions/UserHandler.scala) would instead decode a token. The application expects a custom header "X-User" with a string value that should contain the username mocking the owner of the entities being inserted in the DB.


## API Specification

### GET /api/boards
  - Does: Gets the list of boards for a user
  - Receives: Nothing
  - Required Headers: (X-User, "username")
  - Returns: The boards for the user

### GET /api/boards/:id                         
  - Does: Gets a specific board
  - Receives: The Board Id
  - Required Headers: (X-User, "username")
  - Returns: The board and the list of subscriptions

### POST /api/boards
  - Does: Creates a new board
  - Receives: A Json object, with the new board name { "name":"New Board" }
  - Required Headers: (X-User, "username"),(Content-Type, "application/json")
  - Returns: An HTTP Result
  
### DELETE /api/boards/:id
  - Does: Deletes a board and its subscriptions
  - Receives: The Board Id
  - Required Headers: (X-User, "username")
  - Returns: An HTTP Result

### POST /api/boards/:boardId/hashtags
  - Does: Adds a Hashtag Subscription to a board
  - Receives: The Board Id and a Json object, with the new subscription name { "hashtag":"New Hashtag" }
  - Required Headers: (X-User, "username")
  - Returns: An HTTP Result
  
### DELETE /api/boards/:boardId/hashtags/:id
  - Does: Deletes a subscription from a board
  - Receives: The Board Id and the Subscription Id
  - Required Headers: (X-User, "username")
  - Returns: An HTTP Result
