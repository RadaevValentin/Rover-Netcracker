package netcracker.intensive.rover;

import netcracker.intensive.rover.constants.CellState;

import java.util.Arrays;
import java.util.List;

import static netcracker.intensive.rover.constants.CellState.*;

public class Ground extends GroundCell{
    private int width;
    private int length;
    private GroundCell[][] landscape;


    public CellState isCellEmpty(int x, int y) throws OutOfGroundException {
        if ((x < width && x >= 0) && (y < length && y >= 0)) {
            if(landscape[x][y].getState().equals(FREE)) return FREE;
            else return OCCUPIED;
        }
        else {
            throw new OutOfGroundException("Попытка выхода за границы поля");
        }
    }

    public void initialize(GroundCell ... grounds) {
        List<GroundCell> groundCells = Arrays.asList(grounds);
        if(groundCells.size()>=(getWidth() * getLength())){
            for (int j = 0; j < getLength(); j++) {
                for (int i = 0; i < getWidth(); i++) {
                    setCell(i, j, groundCells.get(getWidth() * j + i));
                }
            }
        }
        else {
            throw new java.lang.IllegalArgumentException("Wrong number of Cells. Fulfill empty cells.");
        }
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getLength(); j++) {
                if (landscape[i][j].equals(null)) {
                    throw new java.lang.IllegalArgumentException("Wrong number of Cells. Fulfill empty cells.");
                }
            }
        }
    }

    public GroundCell getCell(int x, int y) throws OutOfGroundException {
        if ((x < width && x >= 0) && (y < length && y >= 0)) {
            return landscape[x][y];
        }
        else {
            throw new OutOfGroundException("Wrong coordinates. Out of ground boundaries");
        }
    }
    public void setCell(int a, int b, GroundCell cell ) {
        landscape[a][b] = cell;
    }

    public Ground(int f, int g){
        width = f;
        length = g;
        landscape = new GroundCell[f][g];
    }

    public int getWidth(){
        return this.width;
    }
    public int getLength(){
        return this.length;
    }

}


