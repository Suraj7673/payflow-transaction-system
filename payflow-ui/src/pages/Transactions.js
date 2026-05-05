import { useEffect, useState } from "react";
import axios from "axios";

function Transactions({ token }) {
    const [transactions, setTransactions] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios.get("http://localhost:8080/transactions", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(res => {
                setTransactions(res.data);
                setLoading(false);
            })
            .catch(err => {
                console.error(err);
                setTransactions([]);
                setLoading(false);
            });
    }, [token]);

    return (
        <div style={{ marginTop: "20px" }}>
            <h3>Transaction History</h3>

            {loading ? (
                <p>Loading...</p>
            ) : transactions.length === 0 ? (
                <p>No transactions</p>
            ) : (
                <ul style={{ listStyle: "none", padding: 0 }}>
                    {transactions.map(tx => (
                        <li
                            key={tx.id}
                            style={{
                                padding: "10px",
                                marginBottom: "8px",
                                borderRadius: "8px",
                                backgroundColor:
                                    tx.status === "SUCCESS" ? "#e6ffed" : "#ffe6e6",
                                border:
                                    tx.status === "SUCCESS"
                                        ? "1px solid #28a745"
                                        : "1px solid #dc3545"
                            }}
                        >
                            <strong>
                                {tx.status === "SUCCESS" ? "✅ SUCCESS" : "❌ FAILED"}
                            </strong>
                            <br />
                            Amount: ₹{tx.amount}
                            <br />
                            From: {tx.fromUserId} → To: {tx.toUserId}
                            <br />
                            Time: {tx.createdAt}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default Transactions;