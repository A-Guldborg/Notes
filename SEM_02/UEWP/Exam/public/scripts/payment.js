loadText();

function loadText() {
    const params = new URLSearchParams(window.location.search);
    const startTime = new Date(Number(params.get("time")));
    const endTime = new Date();

    const id = "3456-" + parseInt(Math.random() * 1000);

    document.getElementById("receiptdate").innerText = "Date: " + startTime.toLocaleDateString();
    document.getElementById("receiptid").innerText = "ID: " + id;
    document.getElementById("receiptdistance").innerText = "Distance: " + ((endTime - startTime) / (1000 * 60 * 60) * 50).toFixed(2) + "km"; // Calculate number of hours driven and times it with standard speed 5kmph
    document.getElementById("receiptstart").innerText = "Start: " + getTime(startTime);
    document.getElementById("receiptend").innerText = "End: " + getTime(endTime);
    document.getElementById("receiptcar").innerText = "Car: " + params.get("car");
    document.getElementById("receiptprice").innerText = "Price: " + (((endTime - startTime) / (1000 * 60) * params.get("price")).toFixed(2) + " DKK");
}


function getTime(time) {
    let hours = "" + time.getHours();
    hours = addZero(hours);
    let minutes = "" + time.getMinutes();
    minutes = addZero(minutes);
    return hours+":"+minutes;
}

function addZero(time) {
    if (time.length === 1) {
        time = "0" + time;
    }
    return time;
}

// Following section is for the slider
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
        document.getElementById("popupbox").style.display = "block";
        setTimeout(successHandler,6000);
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
    window.location.href = "index.html"
    // reset input range
    inputRange.value = 0;

}

// bind events
inputRange.addEventListener('mousedown', unlockStartHandler, false);
inputRange.addEventListener('mousestart', unlockStartHandler, false);
inputRange.addEventListener('mouseup', unlockEndHandler, false);
inputRange.addEventListener('touchend', unlockEndHandler, false);