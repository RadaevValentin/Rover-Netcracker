package netcracker.intensive.rover.command;

import netcracker.intensive.rover.Rover;
import netcracker.intensive.rover.constants.Direction;

public class TurnCommand implements RoverCommand {
    public static final String TURN = "TURN";
    private Rover rov;
    private Direction curDir;
    public TurnCommand(Rover r, Direction d){
        this.rov = r;
        this.curDir = d;
    }
    public TurnCommand(String dir){
        this.curDir = Direction.valueOf(dir.toUpperCase());
    }
    @Override
    public void execute() {
        rov.turnTo(curDir);
    }
    public String toString(){
        return "Heading " + curDir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TurnCommand that = (TurnCommand) o;

        if (!rov.equals(that.rov)) return false;
        return curDir == that.curDir;

    }

    @Override
    public int hashCode() {
        int result = rov.hashCode();
        result = 31 * result + curDir.hashCode();
        return result;
    }
}
