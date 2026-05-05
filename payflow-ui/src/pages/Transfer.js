import { useState } from "react";
import axios from "axios";

function Transfer({ token }) {
    const [toUserId, setToUserId] = useState("");
    const [amount, setAmount] = useState("");
    const [message, setMessage] = useState("");

    const handleTransfer = () => {
        axios.post("http://localhost:8080/transfer", {
            toUserId: Number(toUserId),
            amount: Number(amount)
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(res => setMessage(res.data))
            .catch(() => setMessage("Transfer failed"));
    };

    return (
        <div>
            <h3>Transfer Money</h3>

            <input
                type="number"
                placeholder="Receiver User ID"
                value={toUserId}
                onChange={(e) => setToUserId(e.target.value)}
                style={{ width: "100%", marginBottom: "10px", padding: "8px" }}
            />

            <input
                type="number"
                placeholder="Amount"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                style={{ width: "100%", marginBottom: "10px", padding: "8px" }}
            />

            <button
                onClick={handleTransfer}
                style={{
                    width: "100%",
                    padding: "10px",
                    backgroundColor: "#27ae60",
                    color: "white",
                    border: "none",
                    borderRadius: "6px"
                }}
            >
                Send
            </button>

            {message && <p style={{ marginTop: "10px" }}>{message}</p>}
        </div>
    );
}

export default Transfer;