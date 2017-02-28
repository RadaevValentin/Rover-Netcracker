package netcracker.intensive.rover;

import static netcracker.intensive.rover.constants.CellState.FREE;

public class GroundVisor {
    private Ground gr;

    public GroundCell getC(Point cur) throws OutOfGroundException {
        return gr.getCell(cur.getX(), cur.getY());
    }

    public boolean checkCell(int x, int y) throws OutOfGroundException {
        return gr.isCellEmpty(x, y).equals(FREE);
    }

    public boolean hasObstacles(Point p) throws OutOfGroundException {
        return !checkCell(p.getX(), p.getY());
    }

    public GroundVisor(Ground g){
        gr = g;
    }
}
