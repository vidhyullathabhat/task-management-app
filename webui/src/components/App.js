import React from 'react';
import './App.css';
import Header from "./Header";
import AddTasker from "./AddTasker";

function App() {

    return (
        <div id={"container"}>
            <Header />
            <AddTasker/>
        </div>
    );
}

export default App;