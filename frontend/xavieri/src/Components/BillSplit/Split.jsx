import { useState } from "react"
import { addNewLog } from "../API/logsApiService"
import { useAuth } from "../auth"


const BillSplit = ({currencies,type,friendsList}) => {

    const authContext=useAuth()
    const [expenseAmount, setExpenseAmount] = useState(0)
    const [expenseCurrency, setExpenseCurrency] = useState('Expense Currency')
    const [expenseTypeValue, setExpenseType] = useState('Expense Type')
    const [memberList, setMemberList] = useState(new Map())
    const [splitType, setSplitType] = useState('Split inbetween')
    const [expenseDate,setExpenseDate]=useState(new Date().toISOString().slice(0, 10))
    const [expensePaidBy,setPaidBy]=useState('Paid By')

    function addMember(selectedMember) {
    
        const selectedId=friendsList.find(e=>e.friend===selectedMember)
        const updatedList = new Map(memberList);
        updatedList.set(selectedId.id, selectedMember);
        setMemberList(updatedList);
    };

    function removeMember(selectedMember) {
        const updatedList = new Map(memberList);
        updatedList.delete(selectedMember);
        setMemberList(updatedList);
    };
    function handlesubmit() {
        // console.log(memberList)
        const serializedData = JSON.stringify([...memberList]);
        const LOG={
            amount:expenseAmount,
            currency:expenseCurrency,
            expenseType:expenseTypeValue,
            mapString:serializedData,
            split:splitType,
            onDate:expenseDate,
            paidBy:expensePaidBy,
            flag:expensePaidBy===authContext.username,
            admin:authContext.username
        }
        console.log(expensePaidBy===authContext.username)
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
                    <option value={expenseCurrency} disabled selected>{expenseCurrency}</option>
                    {currencies.map((c,id)=>(
                        <option key={id} value={c.currency}>{c.currency}</option>
                    ))}
                </select>
                <br />

                <label htmlFor="type">Type</label>
                <select value={expenseTypeValue} id="type" onChange={(e) => setExpenseType(e.target.value)}>
                    <option value={expenseTypeValue} disabled selected>{expenseTypeValue}</option>
                    {type.map((et,id)=>(
                        <option key={id} value={et.type}>{et.type}</option>
                    ))}

                </select>

                <br />

                <label htmlFor="members">Members</label>
                <select id="members" onChange={(e) => addMember(e.target.value)}>
                <option value={memberList} disabled selected>Member included</option>
               
                {friendsList.map((friend,x)=>(
                        <option key={x} id={friend.id} value={friend.friend}>{friend.friend}</option>
                    ))}
                    
                </select>

                {/* {Object.keys(memberList).length > 0 ? (
                <div>
                <h2>Selected Members:</h2>
                {Object.values(memberList).map((item, index) => {
                const [id, name] = Object.entries(item)[0]; // Extracting the key-value pair
                return (
                    <p key={index}> {name} <span onClick={() => removeMember(name)}> X</span></p>
                );
            })}
            </div>
            ) : (
                <p>No members selected</p>
            )} */}
            {memberList.size > 0 ? (
                <div>
                    <h2>Selected Members:</h2>
                    {Array.from(memberList).map(([id, name], index) => (
                        <p key={index}>{name} <span onClick={() => removeMember(id)}>X</span></p>
                    ))}
                </div>
            ) : (
                <p>No members selected</p>
            )}


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