import React, {useState, useEffect} from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
// import Login from './components/auth/Login';
// import Signup from './components/auth/Signup';
import Dashboard from './components/dashboard/Dashboard';
import { auth } from './firebase';
import AuthToggle from './components/auth/AuthToggle';

function App() {
  const [user, setUser] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const unsubscribe = auth.onAuthStateChanged((currentUser) => {
      setUser(currentUser);
      setLoading(false);
    });

    return () => unsubscribe();
  }, []);

  if (loading) {
    return <p>Loading...</p>
  }

  return (
    <Router>
      <Routes>
        {/* <Route path="/login" element = {user? <Navigate to = "/dashboard"/> : <Login/>}/>
        <Route path ="/signup" element = {user? <Navigate to = "/dashboard"/>:<Signup/>}/> */}
        <Route path = "/auth" element = {user? <Navigate to = "/dashboard"/>: <AuthToggle/>}/>
        <Route path = "/dashboard" element ={user? <Dashboard/> : <Navigate to = "/auth"/>}/>
        <Route path ="*" element = {<Navigate to ={user? "/dashboard": "/auth"}/>}/>
      </Routes>
    </Router>
  );
}



export default App;
