let bal = 100;

function loadHeader() {
    document.getElementById("header").innerHTML =
        "    <div class=\"mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-grid\">" +
    "            <div class=\"mdl-layout__header-row mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-desktop\">" +
        "            <h1><i class=\"material-icons\">local_cafe</i> CafeAnalog</h1>" +
        "        </div>" +
        "        <div hidden id=\"user-pic\"></div>" +
        "        <div hidden id=\"user-name\"></div>" +
        "    </div>";
}

function loadMenu() {
    document.getElementById("menubox").innerHTML =
        "        <ul>\n" +
        "            <a href=\"index.html\"><li>Shop</li></a>\n" +
        "            <a href=\"receipts.html\"><li>Receipts</li></a>\n" +
        "            <li onclick=\"addMoney()\">Add money</li>\n" +
        "            <li>Scoreboard</li>\n" +
        "            <li>Add friends</li>\n" +
        "            <li hidden id=\"sign-out\">" +
        "              Sign out" +
        "            </li>" +
        "            <li id=\"sign-in\">" +
        "                Sign in" +
        "            </li>" +
        "         </ul>\n" +
        "         <p onload=\"loadBalance()\">\n" +
        "             Balance: <span id=\"bal\"></span>\n" +
        "         </p>"
    ;

    loadBalance();
}

function loadShopItems() {
    const shopList = document.getElementById("allcoffee");
    const origReceipt = document.getElementById("originalCoffee");

    const items = getShopItems();

    if (items.length === 0) {
        shopList.innerHTML = "<p>Could not find any purchasable items</p>";
    } else {
        for (let i = 0; i < items.length; i++) {
            let clone = origReceipt.cloneNode(true);
            clone.setAttribute("id", "coffee" + i);
            clone.setAttribute("onclick", "buyItem('" + items[i].name + "', " + items[i].price + ")");
            clone.querySelector(".name").innerHTML = items[i].name;
            clone.querySelector(".price").innerHTML = items[i].price + ",-";
            shopList.appendChild(clone);
        }
    }
}

function getShopItems() {
    return [
        {
            price: 32,
            name: "Latte"
        },
        {
            price: 30,
            name: "Cocoa"
        },
        {
            price: 22,
            name: "Espresso"
        },
        {
            price: 35,
            name: "Flat white"
        },
        {
            price: 33,
            name: "Cappuccino"
        },
        {
            price: 27,
            name: "Mocha"
        },
        {
            price: 24,
            name: "Americano"
        },
        {
            price: 26,
            name: "Macchiato"
        }
    ];
}

function hideTickets() {
    document.getElementById("purchaseSuccessful").hidden = true;
    document.getElementById("purchaseDenied").hidden = true;
}

function buyItem(name, price) {
    if (bal >= price) {
        deductMoney(price);
        document.getElementById("type").innerText = name;
        document.getElementById("purchaseSuccessful").hidden = false;
        console.log("Purchased: " + name + " for " + price + ",-");
        const now = new Date();
        console.log(now.getTime());
    } else {
        document.getElementById("purchaseDenied").hidden = false;
        console.log("Not enough money");
    }
}

function loadReceipts() {
    const receiptList = document.getElementById("receiptList");
    const origReceipt = document.getElementById("originalReceipt");

    const receipts = getReceipts();

    if (receipts.length === 0) {
        receiptList.innerHTML = "<p>Could not find any receipts</p>";
    } else {
        for (let i = 0; i < receipts.length; i++) {
            let clone = origReceipt.cloneNode(true);
            clone.removeAttribute("id");
            clone.querySelector(".name").innerHTML = receipts[i].name;
            clone.querySelector(".price").innerHTML = receipts[i].price + ",-";
            clone.querySelector(".time").innerHTML = dateToString(new Date(receipts[i].time));
            receiptList.appendChild(clone);
        }
    }
}

function dateToString(date) {
    return (addZero(date.getHours()) +":"+ addZero(date.getMinutes()) + " " + date.getDate() + "/" + date.getMonth() + "-" + date.getFullYear());
}

function addZero(i) {
    if (i < 10) {i = "0" + i};
    return i;
}

function getReceipts() {
    return [
        {
            price: 32,
            name: "Latte",
            time: 1651225296798
        },
        {
            price: 27,
            name: "Macchiato",
            time: 1640529196798
        }
    ];
}

function loadBalance() {
    document.getElementById("bal").innerText = bal + ",-";
}

function deductMoney(value) {
    bal -= value;
    loadBalance();
}

function addMoney() {
    bal += 50;
    loadBalance();
}

loadHeader();
loadMenu();