createCars();

function openCar(element) {
    document.getElementById("popupcar").innerHTML = element.car;
    document.getElementById("popuppower").innerHTML = element.power;
    document.getElementById("popupdistance").innerHTML = element.distance;
    document.getElementById("popupprice").innerHTML = element.price;
    document.getElementById("popupreservation").innerHTML = element.reservation;

    document.getElementById("popupbox").style.display = "flex";

    currentCar = element;
}

let currentCar;

document.getElementById("reservebutton").addEventListener('click', (e) => {
    openBookCar(currentCar);
});

function openBookCar(element) {
    window.location.assign("bookCar.html?car=" + element.car + "&power=" + element.power + "&distance=" + element.distance + "&price=" + element.price);
}

document.getElementById("closebutton").addEventListener('click', (e) => {
    document.getElementById("popupbox").style.display = "none";
});

function createCars() {
    const cars = getCar();
    for (let i = 0; i < cars.length; i++) {
        let node = document.getElementById("btn" + (i+1));
        node.addEventListener('click', (e) => {
            openCar(cars[i])
        });
    }
}



function getCar() {
    return [
        {
            car: "Fiat Punto",
            power: "450hp",
            distance: "240km",
            price: "2 DKK/min",
            reservation: "Free",
        },
        {
            car: "Tesla Model 3",
            power: "550hp",
            distance: "120km",
            price: "4 DKK/min",
            reservation: "Taken",
        },
        {
            car: "Toyota Rav4 Hybrid",
            power: "350hp",
            distance: "300km",
            price: "2.5 DKK/min",
            reservation: "Free",
        },
        {
            car: "Toyota Rav4 Hybrid",
            power: "350hp",
            distance: "700km",
            price: "2.5 DKK/min",
            reservation: "Taken",
        },
        {
            car: "Hyundai Kona",
            power: "150hp",
            distance: "175km",
            price: "2.2 DKK/min",
            reservation: "Free",
        }
    ];
}