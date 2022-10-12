loadText();

function loadText() {
    const params = new URLSearchParams(window.location.search);
    document.getElementById("carName").innerText = params.get("car");
    document.getElementById("pricePM").innerText = params.get("price");
}

//FOLLOWING SECTION HANDLES THE SLIDER
var inputRange = document.getElementsByClassName('pullee')[0],
    maxValue = 150,
    speed = 12,
    currValue, rafID;

inputRange.min = 0;
inputRange.max = maxValue;

function unlockStartHandler() {
    window.cancelAnimationFrame(rafID);
    currValue = +this.value;
}
function unlockEndHandler() {
    currValue = +this.value;
    if(currValue >= maxValue) {
        setTimeout(successHandler,250);
    }
    else {
        rafID = window.requestAnimationFrame(animateHandler);
    }
}
function animateHandler() {
    inputRange.value = currValue;
    if(currValue > -1) {
        window.requestAnimationFrame(animateHandler);
    }
    currValue = currValue - speed;
}
function successHandler() {
    const date = new Date();
    const timeStamp = date.getTime();
    const params = new URLSearchParams(window.location.search);
    const car = params.get("car");
    const price = params.get("price").split(" ",2);
    const PRICE = price[0];
    window.location.assign("rideStarted.html"+"?time="+timeStamp+"&car=" + car +"&price=" + PRICE);
    inputRange.value = 0;
}

inputRange.addEventListener('mousedown', unlockStartHandler, false);
inputRange.addEventListener('mousestart', unlockStartHandler, false);
inputRange.addEventListener('mouseup', unlockEndHandler, false);
inputRange.addEventListener('touchend', unlockEndHandler, false);

// FOLLOWING SECTIONS CREATES A COUNTDOWN CLOCK FOR A RESERVATION
window.onload = function() {
    var intervalId = window.setInterval(function(){
        if(seconds>=0){ startTimer();}
        if(seconds==0){
            window.location.assign("index.html");
        }
    }, 1000);
};
let seconds = 900;
var output = "15 minutes 00 seconds";
document.getElementById('timeStamp').innerHTML = output;
function startTimer(){
        let tempSecond = seconds%60;
        let tempMinute = (seconds-tempSecond)/60;
        seconds--;
        if(tempSecond<10){
            output = tempMinute+" minutes "+0+tempSecond + " seconds";
        }else{
            output = tempMinute+" minutes "+tempSecond + " seconds";
        }
        document.getElementById('timeStamp').innerHTML = output;
}