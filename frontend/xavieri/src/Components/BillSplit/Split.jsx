import { useState, useEffect } from "react"


const BillSplit = ({currencies,type}) => {
    const [expenseAmount, setExpenseAmount] = useState(0)
    const [expenseCurrency, setExpenseCurrency] = useState('')
    const [expenseTypeValue, setExpenseType] = useState('')
    const [memberList, setMemberList] = useState([]);
    const [splitType, setSplitType] = useState('')
    console.log("These are")

    function addMember(selectedMember) {
        if (selectedMember !== '' && !memberList.includes(selectedMember)) {
            setMemberList([...memberList, selectedMember]);
        }
    };

    function removeMember(member) {
        setMemberList(memberList.filter((m) => m !== member));
    };
    function handlesubmit() {
        console.log(expenseAmount)
        console.log(expenseCurrency)
        console.log(expenseTypeValue)
        console.log(memberList)
        console.log(splitType)
    }

    return (
        <section id="">
            <div><h2>Bills</h2></div>
            <div>
                <label htmlFor="amount">Amount</label>
                <input id="amount" value={expenseAmount} type="number" onChange={(e) => setExpenseAmount(e.target.value)} /> <br />

                <label htmlFor="currency">Currency</label>
                <select value={expenseCurrency} id="currency" onChange={(e) => setExpenseCurrency(e.target.value)}>
                    {currencies.map((c,id)=>(
                        <option key={id} value={c.currency}>{c.currency}</option>
                    ))}
                </select>
                <br />

                <label htmlFor="type">Type</label>
                <select value={expenseTypeValue} id="type" onChange={(e) => setExpenseType(e.target.value)}>
                    {type.map((et,id)=>(
                        <option key={id} value={et.type}>{et.type}</option>
                    ))}

                </select>
                <br />

                <label htmlFor="members">Members</label>
                <select id="members" onChange={(e) => addMember(e.target.value)}>
                    <option value="mem1">member1</option>
                    <option value="mem2">mem2</option>
                    <option value="mem3">mem3</option>
                    <option value="mem4">mem4</option>
                </select>
                {memberList.length > 0 ? memberList.map((name, id) => (
                    <p key={id}>{name}<span onClick={() => removeMember(name)}>X</span></p>
                )) : " "}
                <br />
                <label htmlFor="split">Split amount</label>
                <select value={splitType} id="split" onChange={(e) => setSplitType(e.target.value)}>
                    <option value="custom">custom</option>
                    <option value="equally">equally</option>
                </select>
                <button onClick={handlesubmit}>show</button>

            </div>
        </section>
    )
}
export default BillSplit