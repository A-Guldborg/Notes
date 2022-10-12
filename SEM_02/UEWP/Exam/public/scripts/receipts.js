createReceipts();

function openReceipt(element) {
    document.getElementById("popupdate").innerHTML = element.date;
    document.getElementById("popupid").innerHTML = element.id;
    document.getElementById("popupdistance").innerHTML = element.distance;
    document.getElementById("popupstart").innerHTML = element.start;
    document.getElementById("popupend").innerHTML = element.end;
    document.getElementById("popupcar").innerHTML = element.car;
    document.getElementById("popupprice").innerHTML = element.price;

    document.getElementById("popupbox").style.display = "block";
}

document.getElementById("closebutton").addEventListener('click', (e) => {
    document.getElementById("popupbox").style.display = "none";
});

function createReceipts() {
    const receipts = getReceipts();
    for (let i = 0; i < receipts.length; i++) {
        let copy = document.getElementById("originalReceipt").cloneNode(true);
        copy.removeAttribute("id");
        copy.querySelector(".receipt_date").innerHTML = receipts[i].date;
        copy.querySelector(".receipt_id").innerHTML = receipts[i].id;

        copy.addEventListener('click', (e) => {
            openReceipt(receipts[i])
        });

        document.getElementById("all-receipts").appendChild(copy);
    }
}

function getReceipts() {
    return [
        {
            date: "28/05-2022",
            id: "3456-452",
            distance: "45km",
            start: "14.55",
            end: "15.30",
            car: "Fiat Punto",
            price: "156 DKK"
        },
        {
            date: "27/05-2022",
            id: "3456-451",
            distance: "32km",
            start: "8.32",
            end: "9.01",
            car: "Tesla Model 3",
            price: "132 DKK"
        },
        {
            date: "26/05-2022",
            id: "3456-450",
            distance: "12km",
            start: "11.30",
            end: "11.45",
            car: "Toyota Rav4 Hybrid",
            price: "32 DKK"
        }
    ];
}