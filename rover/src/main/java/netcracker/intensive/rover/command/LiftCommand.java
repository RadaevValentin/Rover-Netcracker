package netcracker.intensive.rover.command;

import netcracker.intensive.rover.Rover;

public class LiftCommand implements RoverCommand{
    public static final String LIFT = "LIFT";
    private Rover rov;
    public LiftCommand(Rover r){
        rov = r;
    }
    public void execute(){
        rov.lift();
    }

    public String toString(){
        return "Rover lifted";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LiftCommand that = (LiftCommand) o;
        return rov.equals(that.rov);
    }

    @Override
    public int hashCode() {
        return rov.hashCode();
    }
}
