import React, {useState, useEffect} from 'react';
import { auth } from '../../firebase';
import '../../styles/ViewLogs.css';

function ViewLogs({latestTransaction}){
  const [transactions, setTransactions]=useState([]);
  useEffect(()=>{
    const fetchTransactions = async () =>{
      try{
        const currentUser = auth.currentUser;
        if(!currentUser){
          console.error('No authenticated user found.');
          return;
        }
        const token = await currentUser.getIdToken();
        const response = await fetch('http://51.20.85.188:8080/api/transactions/recent10',{
          method: 'GET',
          headers:{
            'Authorization': `Bearer ${token}`,
          },
        });
        if(response.ok){
        const data= await response.json();
        setTransactions(data);
        }
        else {
          console.error('Failed to fetch transactions. Satus: ', response.status);
        }
      }
      catch(error) {console.error('Error while fetching transactions: ', error);}
    };
    fetchTransactions();

  }, []); 

  useEffect(()=>{
    if(latestTransaction){
      setTransactions((prevTransactions)=>[latestTransaction, ...prevTransactions.slice(0,9),]);
    }
  }, [latestTransaction]);

  return (
    <div>
      <h2 className="viewlog-header">Your recent transactions</h2>
      <table>
        <thead>
          <tr>
            <th></th>
            <th>Date</th>
            <th>Category</th>
            <th>Subcategory</th>
            <th>Amount</th>
            <th>Details</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction, index)=>(
            <tr key={index}>
              <td>{index+1}</td>
              <td>{transaction.date}</td>
              <td>{transaction.categoryName}</td>
              <td>{transaction.subcategoryName}</td>
              <td>{transaction.amount}</td>
              <td>{transaction.details}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
export default ViewLogs;