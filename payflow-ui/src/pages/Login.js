import { useState } from "react";
import { login } from "../services/api";

function Login({ setToken }) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {
        try {
            const data = await login({ email, password });

            if (data.token) {
                localStorage.setItem("token", data.token);
                setToken(data.token);
            } else {
                alert("Invalid credentials");
            }
        } catch (err) {
            console.error(err);
            alert("Error connecting to server");
        }
    };

    return (
        <div>
            <h2>Login</h2>

            <input
                placeholder="Email"
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                type="password"
                placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
            />

            <button onClick={handleLogin}>Login</button>
        </div>
    );
}

export default Login;