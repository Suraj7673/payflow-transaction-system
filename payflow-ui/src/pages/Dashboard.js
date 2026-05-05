import Transactions from "./Transactions";
import axios from "axios";
import { useEffect, useState } from "react";
import Transfer from "./Transfer";

function Dashboard({ token }) {
    const [balance, setBalance] = useState(null);

    const fetchBalance = () => {
        axios.get("http://localhost:8080/wallet/balance", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(res => setBalance(res.data.balance))
            .catch(() => setBalance("Error"));
    };

    useEffect(() => {
        fetchBalance();
    }, []);

    return (
        <div style={{
            maxWidth: "600px",
            margin: "40px auto",
            padding: "20px",
            fontFamily: "Arial",
            border: "1px solid #ddd",
            borderRadius: "12px",
            boxShadow: "0 2px 8px rgba(0,0,0,0.1)"
        }}>
            <h1 style={{ textAlign: "center" }}>💸 Payflow</h1>

            <div style={{
                textAlign: "center",
                marginBottom: "20px",
                padding: "15px",
                backgroundColor: "#f5f5f5",
                borderRadius: "10px"
            }}>
                <h2>Balance</h2>
                <h1 style={{ color: "#2ecc71" }}>₹{balance}</h1>
            </div>

            <Transfer token={token} />

            <hr style={{ margin: "20px 0" }} />

            <Transactions token={token} />

            <button
                onClick={fetchBalance}
                style={{
                    marginTop: "20px",
                    width: "100%",
                    padding: "10px",
                    backgroundColor: "#3498db",
                    color: "white",
                    border: "none",
                    borderRadius: "6px",
                    cursor: "pointer"
                }}
            >
                🔄 Refresh Balance
            </button>
        </div>
    );
}

export default Dashboard;