var turns = [["#", "#", "#"], ["#", "#", "#"], ["#", "#", "#"]];
var turn = "";
var gameOn = false;

//handle message
function sendMessage(data){
	websocket.send(data);
}

   
function playerTurn(turn, id) {
    if (gameOn) {
        var spotTaken = $("#" + id).text();
        if (spotTaken === "#") {
            makeAMove(playerType, id.split("_")[0], id.split("_")[1]);
        }
    }
}

function makeAMove(type, xCoordinate, yCoordinate) {
	console.log(gameId + "type" +type)
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": type,
            "coordinateX": xCoordinate,
            "coordinateY": yCoordinate,
            "gameId": gameId
        }),
        success: function (data) {
            gameOn = false;
            websocket.send(JSON.stringify(data));
           // displayResponse(data);
           // displayResponse(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponse(data) {
	gameOn = true;
	let xcount=0;
	let ocount=0;
	console.log("displayresponse data "+JSON.stringify(data));
    let board = data.board;
    for (let i = 0; i < board.length; i++) {
        for (let j = 0; j < board[i].length; j++) {
            if (board[i][j] === 1) {
                turns[i][j] = 'X'
                xcount++;
                  
            } else if (board[i][j] === 2) {
                turns[i][j] = 'O';
                ocount++;
              
            }
            let id = i + "_" + j;
            $("#" + id).text(turns[i][j]);
            
              if(xcount<ocount || xcount==ocount)
                    {
	                   if(playerType==='X')
	                   {gameOn = true;}
	                   if(playerType==='O')
	                   {gameOn = false;}
                    }else
                    {if(playerType==='X')
	                   {gameOn = false;}
	                      if(playerType==='O')
	                   {gameOn = true;}
	                   }
	             
           
        }
    }
    if (data.winner != null) {
        alert("Winner is " + data.winner);
    }
    
    
   
}

$(".tic").click(function () {
    var slot = $(this).attr('id');
    playerTurn(turn, slot);
});

function reset() {
    turns = [["#", "#", "#"], ["#", "#", "#"], ["#", "#", "#"]];
    $(".tic").text("#");
}

$("#reset").click(function () {
    reset();
});