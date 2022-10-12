/**
 * To find your Firebase config object:
 * 
 * 1. Go to your [Project settings in the Firebase console](https://console.firebase.google.com/project/_/settings/general/)
 * 2. In the "Your apps" card, select the nickname of the app for which you need a config object.
 * 3. Select Config from the Firebase SDK snippet pane.
 * 4. Copy the config object snippet, then add it here.
 */
const config = {
  apiKey: "AIzaSyA9XRQnOvoAz6xRGm85UwUnh5WboW9N_xo",
  authDomain: "friendlychat-95b97.firebaseapp.com",
  projectId: "friendlychat-95b97",
  storageBucket: "friendlychat-95b97.appspot.com",
  messagingSenderId: "489347898870",
  appId: "1:489347898870:web:ccec110ced0790f358bbd7"
};

export function getFirebaseConfig() {
  if (!config || !config.apiKey) {
    throw new Error('No Firebase configuration object provided.' + '\n' +
    'Add your web app\'s configuration object to firebase-config.js');
  } else {
    return config;
  }
}