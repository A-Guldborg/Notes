const config = {
    apiKey: "AIzaSyAPXHIXq__9Fi_a4oFsCKdSKoQRD5j5FhE",
    authDomain: "cafeanalog-2e676.firebaseapp.com",
    projectId: "cafeanalog-2e676",
    storageBucket: "cafeanalog-2e676.appspot.com",
    messagingSenderId: "1060151044036",
    appId: "1:1060151044036:web:b9a99e31b62dd5bd876691",
    measurementId: "G-B4XBGEH2QL"
};


export function getFirebaseConfig() {
    if (!config || !config.apiKey) {
        throw new Error('No Firebase configuration object provided.' + '\n' +
            'Add your web app\'s configuration object to firebase-config.js');
    } else {
        return config;
    }
}


