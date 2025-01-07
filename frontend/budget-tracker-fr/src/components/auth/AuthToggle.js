import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import { signInWithEmailAndPassword, createUserWithEmailAndPassword } from "firebase/auth";
import {auth} from '../../firebase';
import '../../styles/AuthToggle.css'

function AuthToggle(){
  const [isLogin, setIsLogin] = useState(true);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmpassword, setConfirmpassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const toggleAuthMode =() => setIsLogin(!isLogin);

  //Handle login
  const handleLogin = async () => {
    try{
      await signInWithEmailAndPassword(auth, email, password);
      navigate('/dashboard');
    } catch (error) {
      setError(error.message);
    }
  };

  //Handle signup
  const handleSignup = async () => {
    if (password!==confirmpassword) {
      alert('Password don\'t match. Try again.');
      setPassword('');
      setConfirmpassword('');
      return;
    }

    try {
      await createUserWithEmailAndPassword(auth, email, password);
      const currentUser = auth.currentUser;
      if(!currentUser){
        console.error("User not authorised.");
        return;
      }

      const token = currentUser.getIdToken(true);
      const userDetails = {
        name,
        email,
      };

      const response = await fetch ('http://51.20.85.188:8080/api/user',{
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-type': 'application/json',
        }
      });

      if(response.ok){
        alert('Your account has been created!');
        navigate('/dashboard');
      } else {
        console.error('User creation unsuccessful. Error:', error);
      }
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div className = "auth-container">
      <h2 className = "header">{isLogin? 'Login to your account' : 'Create your account'}</h2>
      {error && <p className = "error">{error}</p>}
      {!isLogin && (
        <input
          className = "cred"
          type = "text"
          placeholder = "Name"
          value = {name}
          onChange = {(e) => setName(e.target.value)}
        />
      )}
      <input
        className = "cred"
        type = "email"
        Placeholder = "Email"
        value = {email}
        onChange = {(e) => setEmail(e.target.value)}
      />

      <input
        className = "cred"
        type = "password"
        placeholder = "Enter password"
        value = {password}
        onChange = {(e) => setPassword(e.target.value)}
      />

      {!isLogin && (
        <input
          className = "cred"
          type = "password"
          Placeholder = "Confirm passowrd"
          value = {confirmpassword}
          onChange = {(e) => setConfirmpassword(e.target.value)}
        />
      )}

      <button className = "auth-button" onClick = {isLogin ? handleLogin : handleSignup}>
        {isLogin? 'Login':'Signup'}
      </button>
      <div className="toggle-container">
        <p className = "toggle-text">
          {isLogin? 'Don\'t have an account?' : 'Already have an account?'}{' '}
          <span className = "toggle-link" onClick ={toggleAuthMode}>
            {isLogin? 'Sign up' : 'Login'}
          </span>
        </p>
      </div>
      
    </div>
  )
}

export default AuthToggle;