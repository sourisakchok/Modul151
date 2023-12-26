import React from 'react';
import {HashRouter as Router, Route, Routes} from 'react-router-dom';
import AuthenticationContext from "./contexts/authenticationcontext/AuthenticationContext";
import {Protected} from "./routes/Protected";
import Products from "./components/Products";

function App() {
    return (
        <Router>
            <AuthenticationContext>
                <Routes>
                    <Route path="/login" />
                    <Route element={<Protected authoritiesToGrantAccess={["CAN_RETRIEVE_PRODUCTS"]}/>}>
                        {/*<Route path="/" element={<Products/>}/>*/}
                    </Route>
                </Routes>
            </AuthenticationContext>
        </Router>
    );
}
export default App;
