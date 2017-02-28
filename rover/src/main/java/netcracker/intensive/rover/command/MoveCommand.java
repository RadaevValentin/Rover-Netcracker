package netcracker.intensive.rover.command;

import netcracker.intensive.rover.Rover;

public class MoveCommand implements RoverCommand {
    public static final String MOVE = "MOVE";
    private Rover rov;
    public MoveCommand(Rover r){
        rov = r;
    }


    @Override
    public void execute() {
        rov.move();
    }

    public String toString(){
        return "Rover moved";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoveCommand that = (MoveCommand) o;
        return rov.equals(that.rov);
    }

    @Override
    public int hashCode() {
        return rov.hashCode();
    }
}
