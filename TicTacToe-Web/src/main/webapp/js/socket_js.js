const url = 'http://localhost:8080/TicTacToe-Web/v1';
let stompClient;
let gameId;
let playerType;
let websocket;
 
function connectToSocket(gameId,data) {
    
    console.log("connecting to the game");
    websocket = new WebSocket("ws://localhost:8080/TicTacToe-Web/gameplay/"+ gameId);
    //alert("connected")
     //alert(data.gameId);
       
    websocket.onmessage=function (message){
	data =JSON.parse(message.data);
	console.log("test retour sendmessage "+data)
	console.log(playerType);
	displayResponse(data);
	 //gameId = data.gameId;
	 console.log(gameId);
	//alert("test retour"+message);
    }
     console.log("gameid"+gameId);
    //displayResponse(data);


}


function create_game() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = 'X';
                reset();
                connectToSocket(gameId,data)
                 //displayResponse(data)
                alert("Your created a game. Game id is: " + data.gameId);
                gameOn = true;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}


function connectToRandom() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/connect/random",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login
            }),
            success: function (data) {
	            console.log("game id "+gameId)
                gameId = data.gameId;
                playerType = 'O';
                reset();
                connectToSocket(gameId);
                alert("Congrats you're playing with: " + data.player1.login);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToSpecificGame() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        let gameId1 = document.getElementById("game_id").value;
        if (gameId1 == null || gameId1 === '') {
            alert("Please enter game id");
        }
        gameId=gameId1;
        $.ajax({
            url: url + "/game/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player": {
                    "login": login
                },
                "gameId": gameId
            }),
            success: function (data) {
	            console.log("game id "+gameId)
                gameId = data.gameId;
                playerType = 'O';
                reset();
                //alert(JSON.stringify(data));
                connectToSocket(gameId,data);
                alert("Congrats you're playing with: " + data.player1.login);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}