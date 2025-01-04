import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyCg48mg3PZ8Q6e1Ai963mQAdl2p4-XOlO4",
  authDomain: "budget-tracker-4920e.firebaseapp.com",
  projectId: "budget-tracker-4920e",
  storageBucket: "budget-tracker-4920e.firebasestorage.app",
  messagingSenderId: "252491680575",
  appId: "1:252491680575:web:8a18ebe1d87cb57f24618a",
  measurementId: "G-CEYXY86WT5"
};

const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);