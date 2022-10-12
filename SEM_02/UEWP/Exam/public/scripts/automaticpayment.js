function openPopup() {
    document.getElementById("popupbox").style.display = "block";
}

function addCard() {
    document.getElementById("current_card_number").innerHTML = document.getElementById("cardnumber").value;
    document.getElementById("current_card_month").innerHTML = document.getElementById("expirationmonth").value;
    document.getElementById("current_card_year").innerHTML = document.getElementById("expirationyear").value;
    document.getElementById("current_card_cvv").innerHTML = document.getElementById("cvv").value;

    document.getElementById("popupbox").style.display = "none";
    document.getElementById("currentcard").style.display = "block";
}

function removeCard() {
    document.getElementById("currentcard").style.display = "none";
}