import { useState } from "react";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";

function App() {
  const [token, setToken] = useState(localStorage.getItem("token"));

  return (
      <div>
        <h1>Payflow</h1>
        {token ? <Dashboard token={token} /> : <Login setToken={setToken} />}
      </div>
  );
}

export default App;