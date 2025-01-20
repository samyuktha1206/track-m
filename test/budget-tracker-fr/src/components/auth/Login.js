import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import { signInWithEmailAndPassword } from 'firebase/auth';
import { auth } from '../../firebase';
import '../../styles/Login.css';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate =useNavigate();

  const handleLogin = async () => {
    try{
      await signInWithEmailAndPassword(auth, email, password);
      navigate('/dashboard');
    }
    catch(error) {
      setError(error);
    }
  };

  return (
    <div className = "signup-form">
      <h2 className = "header">Login to your account</h2>
      <input
        className = "cred"
        type = "email"
        placeholder = "Email"
        value = {email}
        onChange = {(e) => setEmail(e.target.value)}
      />

      <input
        className = "cred"
        type = "password"
        placeholder = "Password"
        value = {password}
        onChange = {(e) => setPassword(e.target.value)}
      />

      <button className = "signup-button" onClick = {handleLogin}>Login</button>
      {error && <p style = {{color: 'red'}}>{error}</p>}
    </div>
  )
}
export default Login;