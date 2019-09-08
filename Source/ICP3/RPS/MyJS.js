function myFunction(a) {
    var x = Math.floor(Math.random() * 3 + 1);
    document.getElementById("result").innerText = "Wait!";
    var y = "";
    if (a == 1) {
        if (x == 1) {
            y = "Tie";
        } else if (x == 2) {
            y = "you lost"
        } else {
            y = "you won"
        }
    } else if (a == 2) {

        if (x == 1) {
            y = "you won";
        } else if (x == 2) {
            y = "Tie"
        } else {
            y = "you lost"
        }
    } else {
        if (x == 1) {
            y = "you lost";
        } else if (x == 2) {
            y = "you won"
        } else {
            y = "Tie"
        }
    }
    var b="";
    switch (x) {
        case 1:
            b = "Rock";
            break
        case 2:
            b = "paper";
            break
        case 3:
            b = "scissor"
    }
    document.getElementById("result").innerText = "Computer picked " + b + ", " + y;
}