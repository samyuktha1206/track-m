import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import { createUserWithEmailAndPassword } from 'firebase/auth';
import { auth } from '../../firebase';
import '../../styles/Signup.css';

 function Signup() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmpassword, setConfirmpassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  
  const handleSignup = async () => {

    if(password!==confirmpassword){
      alert('Passwords don\'t match. Try again.');
      setPassword('');
      setConfirmpassword('');
      return;
    }

    try{
      await createUserWithEmailAndPassword(auth, email, password);
      const currentUser = auth.currentUser;
      if(!currentUser){
        console.error("User not authorised.")
        return;
      }
      const token = await currentUser.getIdToken(true);
      console.log('Token', token);
      const userDetails = {
        name : name,
        email : email,
      }
      const response = await fetch('http://51.20.85.188:8080/api/user',{
        method : 'POST',
        headers : {
          'Authorization' : `Bearer ${token}`,
          'Content-type' : 'application/json'
        },
        body : JSON.stringify(userDetails),
      });
      if(response.ok){
        alert('Your account has been created');
      }
      else{
        console.error('User creation unsuccessfull. Error: ', error);
      }
      navigate('/dashboard');
    }

    catch(error){
      console.error('Signup error:', error.message)
      setError(error.message);
    }

  };

  return (
    <div className="signup-form">
      <h2 className="header">Create your account</h2>
      <input
        className = "cred"
        type = "text"
        placeholder = "Name"
        value = {name}
        onChange = {(e) => setName(e.target.value)}
      />
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

      <input
        className = "cred"
        type = "password"
        placeholder = "Confirm Password"
        value = {confirmpassword}
        onChange = {(e) => setConfirmpassword(e.target.value)}
      />

      <button className="signup-button" onClick = {handleSignup}>Sign Up</button>
      {error && <p style = {{color: 'red'}}>{error}</p>}
    </div>
  );
 }

 export default Signup;