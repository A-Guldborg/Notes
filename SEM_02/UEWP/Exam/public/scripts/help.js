//STRINGS TO MATCH SEARCH INPUT WITH
var help_with_delete = "We’re sorry to hear you're considering leaving us! If we can do anything to help you out, please contact us and let us know how. If you still want to delete all info on you, you can freely do so from the profile page. Do note, that everything will be deleted and unretrievable. Once an account has been deleted, we can no longer get the data back - so be sure before deleting your account!"
var help_with_payment = "After a ride has ended, you will be led to the payment page where you can swipe to pay using your preferred payment method such as MobilePay or credit card! If you don’t want to enter your payment information every ride, you can set up automatic payment from your profile. Fill in your credit card informations which will be securely stored, and we will charge you for your rides automatically - It’s that easy!"
var help_with_book = "To book a ride, navigate to the home page and click the car nearest you. Click the reserve button on the pop-up so nobody else can pick up your car. Find the car and click start ride to unlock the doors.Once finished, open the app and click stop ride to get to the payment page, where you will be presented with information about your ride."

//ADD EVENT LISTENER TO THE SEARCHBAR
var searchField = document.getElementById('searchBar');
searchField.addEventListener("keypress", function (e) {
    if (e.code === "Enter") {  //checks whether the pressed key is "Enter"
        submit(e);
    }
});

//FUNCTION TO OPEN THE RELEVANT SITE
function submit(e){
    e.preventDefault();
    var inputText = searchField.value;
    if(help_with_delete.includes(inputText)){
        window.location.assign("help_delete.html");
    }else if(help_with_payment.includes(inputText)){
        window.location.assign("help_payment.html");
    } else if(help_with_book.includes(inputText)){
        window.location.assign("help_ride.html");
    }
}

