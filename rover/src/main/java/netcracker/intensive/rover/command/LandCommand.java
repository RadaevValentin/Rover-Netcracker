package netcracker.intensive.rover.command;


import netcracker.intensive.rover.Point;
import netcracker.intensive.rover.Rover;
import netcracker.intensive.rover.constants.Direction;

public class LandCommand implements RoverCommand {
    public static final String LAND = "LAND";
    private Rover rov;
    private Direction curDir;
    private Point curPoint;

    public LandCommand(Rover r, Point p, Direction d){
        rov = r;
        curDir = d;
        curPoint = p;
    }


    public void execute() {
        rov.land(curPoint, curDir);
    }

    public String toString(){
        return "Land at (" + curPoint.getX() + ", " + curPoint.getY() + ") heading " + curDir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LandCommand that = (LandCommand) o;
        if (!rov.equals(that.rov)) {
            return false;
        }
        if (curDir != that.curDir) {
            return false;
        }
        return curPoint.equals(that.curPoint);
    }

    @Override
    public int hashCode() {
        int result = rov.hashCode();
        result = 31 * result + curDir.hashCode();
        result = 31 * result + curPoint.hashCode();
        return result;
    }
}


