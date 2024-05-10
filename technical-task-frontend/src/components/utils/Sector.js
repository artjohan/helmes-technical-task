import React, {useState} from 'react';
import {Icon} from 'semantic-ui-react';

function Sector({id, name, subsectors, addSector, depth = 0, selectedSectors}) {
    const [expanded, setExpanded] = useState(false);

    const handleToggle = () => {
        setExpanded(!expanded);
    };

    const handleSelect = (e, {value}) => {
        addSector(value);
    };

    return (
        <div>
            <div className="sector" style={{
                backgroundColor: subsectors.length === 0 ? (selectedSectors.includes(id) ? '#e0f7fa' : 'transparent') : 'transparent',
                width: `${500 - depth * 50}px`
            }}>
                <div
                    style={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                        cursor: 'pointer',
                        fontWeight: !subsectors.length ? 'normal' : 'bold',
                    }}
                    onClick={subsectors.length ? handleToggle : () => handleSelect(null, {value: id})}
                >
                    {subsectors && (
                        <Icon
                            name={expanded ? 'chevron down' : 'chevron right'}
                            onClick={handleToggle}
                            style={{marginRight: '5px', visibility: subsectors.length > 0 ? 'visible' : 'hidden'}}
                        />
                    )}
                    {name}
                </div>
            </div>
            {expanded && subsectors && (
                <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', margin: "auto"}}>
                    {subsectors.map((subsector) => (
                        <Sector key={subsector.id} {...subsector} addSector={addSector} depth={depth + 1}
                                selectedSectors={selectedSectors}/>
                    ))}
                </div>
            )}
        </div>
    );
}

export default Sector;
