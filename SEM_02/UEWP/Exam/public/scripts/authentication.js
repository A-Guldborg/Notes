import { GoogleAuthProvider, getAuth, signInWithPopup, signOut, onAuthStateChanged } from "https://www.gstatic.com/firebasejs/9.4.1/firebase-auth.js";
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.4.1/firebase-app.js";

const firebaseConfig = {
    apiKey: "AIzaSyAjnYTMsWsRQZbOWl3JigDNDP7qEvSXcE4",
    authDomain: "uewp-47ca2.firebaseapp.com",
    projectId: "uewp-47ca2",
    storageBucket: "uewp-47ca2.appspot.com",
    messagingSenderId: "233583661124",
    appId: "1:233583661124:web:d077c93b52950f828eac11"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const provider = new GoogleAuthProvider(app);
let user;

function setUserInformation(user) {
    document.getElementById("account-pic").setAttribute("src", user.photoURL);
    document.getElementById("account-pic").style["border-radius"] = "100px";
    document.getElementById("account-name").innerText = user.displayName;
}

export function addLogin(node) {
    node.addEventListener('click', (e) => {
        signInWithPopup(auth, provider)
            .then((result) => {
                // The signed-in user info.
                user = result.user;
                setUserInformation(user);
            });
    });
}

export function isLoggedIn() {
    return !!getAuth().currentUser;
}

export function logout() {
    signOut(auth).then(() => {
        console.log("Succesfully logged out");
    }).catch((e) => {
        console.log("An error occurred while logging out user:");
        console.log(e.message);
    });
}

export function createButtons() {
    if (isLoggedIn()) {
        createProfileButtons();
    } else {
        createLoginButton();
    }
}

function createLoginButton() {
    addButtons([
        {text: "Login with Google", link: "", logout: false}
    ]);
}

function createProfileButtons() {
    addButtons([
        {text: "My receipts", link: "receipts.html", logout: false},
        {text: "Automatic payment", link: "automaticpayment.html", logout: false},
        {text: "Log out", link: "", logout:true},
        {text: "Delete account", link: "", logout:true}
    ]);
}

function addButtons(buttons) {
    document.getElementById("all-profile-buttons").innerHTML = "";
    for (let i = 0; i < buttons.length; i++) {
        let copy = document.getElementById("originalButton").cloneNode(true);
        copy.removeAttribute("id");
        copy.querySelector(".button").innerHTML = buttons[i].text;
        if (buttons[i].link === "") {
            copy.querySelector("a").removeAttribute("href");
        } else {
            copy.querySelector("a").setAttribute("href", buttons[i].link);
        }
        document.getElementById("all-profile-buttons").appendChild(copy);
        if (buttons[i].text === "Login with Google") {
            addLogin(copy);
        }
        if (buttons[i].logout) {
            copy.addEventListener('click', (e) => {
                logout();
                createLoginButton();
            });
        }
    }
}

function setLogo() {
    document.getElementById("account-pic").setAttribute("src", "img/logo.svg");
    document.getElementById("account-pic").removeAttribute("style");
    document.getElementById("account-name").innerHTML = "";
}

// Following is inspired by our code in the Café Analog project (#oo3)
function authStateObserver() {
    let user = getAuth().currentUser;
    if (user) {
        setUserInformation(user);
    } else {
        setLogo();
    }
    createButtons();
}

createButtons();

onAuthStateChanged(getAuth(), authStateObserver);