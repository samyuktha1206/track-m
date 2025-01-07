import React, {useState} from 'react';
import '../../styles/LogTransaction.css';
import { auth } from '../../firebase';

function LogTransaction({onAddTransaction}) {
  const[amount, setAmount]=useState('');
  const[category, setCategory]=useState('');
  const[subcategory, setSubcategory]=useState('');
  const[details, setDetails]=useState('');

  const handleSubmit=async (e) => {
    e.preventDefault();

    if(!amount || !category || !subcategory){
      alert('Please fill in fields marked *');
      return;
    }

    const transactionData={
      amount_spent: parseFloat(amount),
      categoryName: category,
      subcategoryName: subcategory,
      details,
    };

    try{
      const currentUser = auth.currentUser;
      if(!currentUser){
        console.error('User not authenticated.');
        return;
      }
      const token = await currentUser.getIdToken();
      const response = await fetch('http://51.20.85.188:8080/api/transactions', {
        method:'POST',
        headers:{
          'Authorization': `Bearer ${token}`,
          'Content-type': 'application/json',
        },
        body: JSON.stringify(transactionData),
      });

      if(response.ok){
        alert('Transaction saved sucessfully!');
        onAddTransaction(transactionData);
        // setDate('');
        setAmount('');
        setCategory('');
        setSubcategory('');
        setDetails('');
      }
      else{
        alert('Failed to save transaction.');
      }
    } catch (error){
      console.error('Error: ', error);
      alert('An error occured while saving the transaction.');
        setAmount('');
        setCategory('');
        setSubcategory('');
        setDetails('');
    }
  };

  return(
    <div className="log-transaction-container">
      {/* <h2 className="header">Log your transactions here...</h2> */}
      <div className="form">
        <form onSubmit={handleSubmit}>
          {/* <div className="date">
            <label>Date:</label>
            <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
          </div> */}
          <div className="amount">
            {/* <label className="label">Amount Spent</label> */}
            <input className="input_box" type="number" placeholder="Enter amount spent" value={amount} onChange={(e) => setAmount(e.target.value)} />
          </div>
          <div className="category">
            {/* <label className="label">Category</label> */}
            <select className="input_box" value={category} onChange={(e) => setCategory(e.target.value)}>
              <option value="">Select Category</option>
              <option value="Food">Food</option>
              <option value="Transport">Transport</option>
              <option value="Entertainment">Entertainment</option>
              <option value="Bills">Bills</option>
              <option value="Essentials">Essentials</option>
            </select>
          </div>
          <div className="subcategory">
            {/* <label className="label">Subcategory</label> */}
            <input className="input_box" type="text" value={subcategory} onChange={(e) => setSubcategory(e.target.value)}/>
          </div>
          <div className="details">
            {/* <label className="label">Details</label> */}
            <textarea className="input_box" placeholder="Enter details of your transaction" value={details} onChange={(e) => setDetails(e.target.value)} />
          </div>
          <button  className="submit-button" type="submit">Log Transaction</button>
        </form>
      </div>
    </div>
  );
}

export default LogTransaction;
