import { useState } from "react"
import { addNewLog } from "../API/logsApiService"
import { AuthContext, useAuth } from "../auth"


const BillSplit = ({currencies,type,friendsList}) => {
    const authContext=useAuth()
    const [expenseAmount, setExpenseAmount] = useState(0)
    const [expenseCurrency, setExpenseCurrency] = useState('Expense Currency')
    const [expenseTypeValue, setExpenseType] = useState('Expense Type')
    const [memberList, setMemberList] = useState([]);
    const [splitType, setSplitType] = useState('Split inbetween')
    const [expenseDate,setExpenseDate]=useState(new Date().toISOString().slice(0, 10))
    const [expensePaidBy,setPaidBy]=useState('Paid By')

    function addMember(selectedMember) {
        if (selectedMember !== '' && !memberList.includes(selectedMember)) {
            setMemberList([...memberList, selectedMember]);
        }
    };

    function removeMember(member) {
        setMemberList(memberList.filter((m) => m !== member));
    };
    function handlesubmit() {
        const LOG={
            amount:expenseAmount,
            currency:expenseCurrency,
            expenseType:expenseTypeValue,
            members:memberList,
            split:splitType,
            onDate:expenseDate
        }
        addNewLog(LOG).then(
            (response)=>console.log(response.data)
        ).catch(
            (error)=>console.log(error)
        )

    }

    return (
        <section id="">
            <div><h2>Bills</h2></div>
            <div>
                <label htmlFor="amount">Amount</label>
                <input id="amount" value={expenseAmount} type="number" onChange={(e) => setExpenseAmount(e.target.value)} /> <br />

                <label htmlFor="currency">Currency</label>
                <select value={expenseCurrency} id="currency" onChange={(e) => setExpenseCurrency(e.target.value)}>
                    <option value={expenseCurrency} disabled>{expenseCurrency}</option>
                    {currencies.map((c,id)=>(
                        <option key={id} value={c.currency}>{c.currency}</option>
                    ))}
                </select>
                <br />

                <label htmlFor="type">Type</label>
                <select value={expenseTypeValue} defaultValue={expenseTypeValue} id="type" onChange={(e) => setExpenseType(e.target.value)}>
                    <option value={expenseTypeValue} disabled>{expenseTypeValue}</option>
                    {type.map((et,id)=>(
                        <option key={id} value={et.type}>{et.type}</option>
                    ))}

                </select>

                <br />

                <label htmlFor="members">Members</label>
                <select id="members" onChange={(e) => addMember(e.target.value)}>
                <option value="" disabled selected>Member included</option>
                {friendsList.map((friend,id)=>(
                        <option key={id} value={friend.friend}>{friend.friend}</option>
                    ))}
                    
                </select>

                {memberList.length > 0 ? memberList.map((name, id) => (
                    <p key={id}>{name}<span onClick={() => removeMember(name)}>X</span></p>
                )) : " "}


                <br />
                <label htmlFor="split">Split amount</label>
                <select value={splitType} id="split" onChange={(e) => setSplitType(e.target.value)}>
                    <option value={splitType} disabled selected>divide amount</option>
                    <option value="custom">custom</option>
                    <option value="equally">equally</option>
                </select>

                <br />
                <label htmlFor="date">Date</label>
                <input type="date" id="date" value={expenseDate} onChange={(e)=>setExpenseDate(e.target.value)}/>
                <br />

                <label htmlFor="paidby">Paid By</label>
                <select defaultValue={expensePaidBy} id="paidby" onChange={(e) => setPaidBy(e.target.value)}>
                <option value={expensePaidBy} disabled>Paid by</option>
                <option value={authContext.username}>Me</option>
                {friendsList.map((friend,id)=>(
                    <option key={id} value={friend.friend}>{friend.friend}</option>
                ))}
                    
                </select>

                <button onClick={handlesubmit}>Add Expense</button>
            </div>
        </section>
    )
}
export default BillSplit