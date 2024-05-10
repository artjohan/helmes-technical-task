import Sector from "./Sector.js";

function SectorSelector({sectors, addSector, selectedSectors}) {
    return (
        <div>
            {sectors.map((sector) => (
                <Sector
                    name={sector.name}
                    id={sector.id}
                    subsectors={sector.subsectors}
                    key={sector.id}
                    addSector={addSector}
                    selectedSectors={selectedSectors}
                />
            ))}
        </div>
    );
}

export default SectorSelector;