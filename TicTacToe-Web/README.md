#The project is a Tic Tac Toe game
## Overview
you want to create a game in a web server. This will allow you and your friend to play together.

### WebChatServer - Endpoints
**Connect to the websocket**

From the `websocketConfig` class. This will create a new client connect to the web server. The server and client communicate using `json` messages.
- `ws://localhost:8080/TicTacToe-Web/gameplay/{gameID}

### Rest API - Endpoints

From the `GameController` class. this will allow you to manipulate game's feature (create a game , connect to a specific game or connect to a random game)

**POST a new game id**
this will return a json content type
-http://localhost:8080/TicTacToe-Web/v1/game/start

**POST game play**
this will return a json content type
-http://localhost:8080/TicTacToe-Web/v1/game/start/gameplay
See a sample of the response data:
```
{"gameId":"e9883c05-f4ac-4b97-b341-0bb4fb0f398d","winner":null,"player1":{"login":"l"}
,"player2":null,"board":[[1,0,0],[0,0,0],[0,0,0]],"status":"NEW"}

```

### WebChatServer - client

Your client is in the `webapp` folder, when started the application will run at `http://localhost:8080/TicTacToe-Web/`; which will load the `index.html` file.

Your client-side code will be in the `js/socket_js.js`and `js/script.js` javascript file.