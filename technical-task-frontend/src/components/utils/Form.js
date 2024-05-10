import React, {useEffect, useState} from "react";
import SectorSelector from "./SectorSelector";
import Checkbox from "./Checkbox.js";
import {Input} from "semantic-ui-react";
import {v4 as uuidv4} from 'uuid';

function Form({sectors}) {
    const [sessionId, setSessionId] = useState("");
    const [postSuccess, setPostSuccess] = useState(false);

    const [name, setName] = useState('');
    const [nameErr, setNameErr] = useState(false);

    const [agreedToTerms, setAgreedToTerms] = useState(false);
    const [agreedToTermsErr, setAgreedToTermsErr] = useState(false);

    const [selectedSectors, setSelectedSectors] = useState([]);
    const [selectedSectorsErr, setSelectedSectorsErr] = useState(false);

    useEffect(() => {
        setSessionId(uuidv4());
    }, []);

    const addSector = (newSectorId) => {
        setSelectedSectors(prevSelectedSectors => {
            if (prevSelectedSectors.includes(newSectorId)) {
                return prevSelectedSectors.filter(sectorId => sectorId !== newSectorId);
            } else {
                return [...prevSelectedSectors, newSectorId];
            }
        });
    };

    const postUserData = async () => {
        const payload = {
            name,
            agreedToTerms,
            sessionId,
            userSectorIds: selectedSectors
        };

        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
        };

        try {
            const response = await fetch(
                "http://localhost:8080/form",
                options
            );
            if (response.ok) {
                const data = await response.json();

                setName(data.name);
                setAgreedToTerms(data.agreedToTerms);
                setSessionId(data.sessionId);
                setSelectedSectors(data.userSectorIds);

                setPostSuccess(true);
            } else {
                const statusMsg = await response.text();
                console.error(statusMsg);

                setPostSuccess(false);
            }
        } catch (error) {
            console.error(error);
        }
    };

    const handleNameChange = (e) => {
        setName(e.target.value);
    };

    const submitForm = (e) => {
        e.preventDefault();

        setNameErr(name.trim() === "");
        setAgreedToTermsErr(!agreedToTerms);
        setSelectedSectorsErr(selectedSectors.length === 0);

        if (name.trim() !== "" && agreedToTerms && selectedSectors.length) {
            postUserData();
        } else {
            setPostSuccess(false);
        }
    };

    return (
        <div>
            <form style={{textAlign: "center"}} onSubmit={submitForm}>
                <h5 style={{color: "green", visibility: `${postSuccess ? "visible" : "hidden"}`}}>Data successfully
                    saved!</h5>
                <Input
                    placeholder="Enter your name"
                    value={name}
                    onChange={handleNameChange}
                    error={nameErr}
                    style={{width: "500px"}}
                />
                <h5 style={{color: "red", visibility: `${selectedSectorsErr ? "visible" : "hidden"}`}}>Please select at
                    least
                    one sector!</h5>
                <div style={{maxHeight: "500px", overflowY: "auto", margin: "25px"}}>
                    <SectorSelector
                        sectors={sectors}
                        addSector={addSector}
                        selectedSectors={selectedSectors}
                    ></SectorSelector>
                </div>
                <div style={{display: "flex", justifyContent: "space-between", width: "500px", margin: "0 auto"}}>
                    <Checkbox
                        value={agreedToTerms}
                        setValue={setAgreedToTerms}
                        label={"Agree to terms"}
                        hasErr={agreedToTermsErr}
                        errMsg={"Agreeing to terms is required"}
                    />
                    <button style={{margin: "20px", maxHeight: "35px"}} type="submit"
                            className="ui primary button center aligned">Save
                    </button>
                </div>
            </form>
        </div>
    );
}

export default Form;