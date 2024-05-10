import React, {useEffect, useState} from "react";
import Form from "../utils/Form.js";


function Home() {
    const [sectors, setSectors] = useState([]);

    useEffect(() => {
        const getSectors = async () => {
            try {
                const response = await fetch(
                    `http://localhost:8080/sectors`
                );
                if (response.ok) {
                    const data = await response.json();
                    setSectors(data);
                } else {
                    console.error(response.statusText);
                }
            } catch (error) {
                console.error(error);
            }
        };

        getSectors();
    }, [])

    return (
        <div>
            <h1 style={{paddingTop: "70px", paddingBottom: "10px", textAlign: "center"}}>Please enter your name and pick
                the Sectors you are currently involved in.</h1>
            <Form sectors={sectors}/>
        </div>
    )
}

export default Home;