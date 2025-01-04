import React, {useState} from 'react';
import LogTransaction from '../transactions/LogTransaction';
import ViewLogs from '../transactions/ViewLogs';
import {auth} from '../../firebase';
import '../../styles/Dashboard.css';

function Dashboard(){
  const [latestTransaction, setLatestTransaction] = useState(null);

  const addTransaction = (newTransaction)=>{
    setLatestTransaction(newTransaction);
  }

  const handleLogout = () => {
    auth.signOut();
  };

  return (
    <div className="dash-container">
      <div className="toolbar">
        <div className="account-info">
          <img className="account-icon" src="profile pic/user.png"></img>
          <div className="tool-tip">Account Info</div>
        </div>
        <div className="empty-space"></div>
        <div className="button-box">
          <button className="logout" onClick = {handleLogout}>Logout</button>
        </div>
        
      </div>
      <h1 className = "welcome">Welcome to Your Dashboard!</h1>
      <div>
        <div>
          <LogTransaction onAddTransaction={addTransaction}/>
        </div>
        <div>
          <ViewLogs latestTransaction={latestTransaction}/>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
