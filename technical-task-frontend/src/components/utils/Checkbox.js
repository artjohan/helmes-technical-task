import React from "react";

const Checkbox = ({ value, setValue, label, hasErr, errMsg }) => {

    return (
        <div style={{ margin: "25px" }}>
            <div className="ui toggle checkbox">
                <input type="checkbox" onClick={() => setValue(!value)} />
                <label style={{ width: "155px" }}>{label}</label>
                {hasErr &&
                    <div className='ui pointing red basic label'>
                        {errMsg}
                    </div>}
            </div>
        </div>
    );
}

export default Checkbox;