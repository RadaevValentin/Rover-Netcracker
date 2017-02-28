package netcracker.intensive.rover;

import netcracker.intensive.rover.constants.CellState;

public class GroundCell {
    private CellState cellEmpty = CellState.FREE;

    public GroundCell(CellState c){
        this.cellEmpty = c;
    }

    public GroundCell(){}



    public CellState getState() {
        if(cellEmpty.equals(null)){
            cellEmpty = CellState.FREE;
            return CellState.FREE;
        }
        else{return cellEmpty;}
    }
}