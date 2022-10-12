
function openNav() {
    document.getElementById("menubar").style.width = "100%";
    document.getElementById("menubar").style.display = "block";
}


function closeNav() {
    document.getElementById("menubar").style.display = "none";
}

var inputRange = document.getElementsByClassName('pullee')[0],
    maxValue = 150, // the higher the smoother when dragging
    speed = 12, // thanks to @pixelass for this
    currValue, rafID;

// set min/max value
inputRange.min = 0;
inputRange.max = maxValue;

// listen for unlock
function unlockStartHandler() {
    // clear raf if trying again
    window.cancelAnimationFrame(rafID);

    // set to desired value
    currValue = +this.value;
}

function unlockEndHandler() {

    // store current value
    currValue = +this.value;

    // determine if we have reached success or not
    if(currValue >= maxValue) {
        successHandler();
    }
    else {
        rafID = window.requestAnimationFrame(animateHandler);
    }
}

// handle range animation
function animateHandler() {

    // update input range
    inputRange.value = currValue;

    // determine if we need to continue
    if(currValue > -1) {
        window.requestAnimationFrame(animateHandler);
    }

    // decrement value
    currValue = currValue - speed;
}

// handle successful unlock
function successHandler() {
    const params = new URLSearchParams(window.location.search);
    let startTime = params.get("time");
    const car = params.get("car");
    const price = params.get("price").split(" ",2);
    const PRICE = price[0];
    window.location.href = "payment.html"+"?time=" + startTime +"&car=" + car +"&price=" + PRICE;
    // reset input range
    inputRange.value = 0;

};

// bind events
inputRange.addEventListener('mousedown', unlockStartHandler, false);
inputRange.addEventListener('mousestart', unlockStartHandler, false);
inputRange.addEventListener('mouseup', unlockEndHandler, false);
inputRange.addEventListener('touchend', unlockEndHandler, false);

// FOLLOWING SECTIONS CREATES A CLOCK FOR RIDE DURATION
window.onload = function() {
    var intervalId = window.setInterval(function(){
         startTimer();
    }, 1000);
};
let seconds = 0;
let minutes = 0;
let hour = 0;
var output = "00:00:00";
document.getElementById('box2').innerHTML = output;

function startTimer(){
    seconds++;
    if(seconds==60){
        seconds = 0;
        minutes++;
    }
    if(minutes==60){
        minutes = 0;
        hour++;
    }
    let m = minutes;
    let s = seconds;
    let h = hour;
    if(minutes<10){m = 0+""+minutes}
    if(seconds<10){s = 0+""+seconds}
    if(hour<10){h = 0+""+hour}
    output = h+":"+m+":"+s;
    document.getElementById('box2').innerHTML = "Time: " + output;

    const priceperminute = new URLSearchParams(window.location.search).get("price").split(" ")[0];
    const price = ((60 * hour + minutes + seconds/60) * priceperminute).toFixed(2);

    document.getElementById("box1").innerHTML = "Price: " + price + " DKK";
}