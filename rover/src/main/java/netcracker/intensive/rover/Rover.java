package netcracker.intensive.rover;

import netcracker.intensive.rover.constants.Direction;

import static netcracker.intensive.rover.constants.Direction.SOUTH;

public class Rover implements Moveable, Liftable, Landable, Turnable{
    private boolean fly = false;
    private GroundVisor visor;
    private Point curPoint = new Point(0, 0);
    private Direction dir = SOUTH;


    public Rover(GroundVisor visor) {
        this.visor = visor;
    }

    public Direction getDirection() {
        return dir;
    }
    public  void setDir(Direction d) {
        dir = d;
    }

    public Point getCurrentPosition() {
        return curPoint;
    }
    public void setCurPoint(Point cur) {
        try{
            visor.getC(cur);
            curPoint.setPoint(cur.getX(), cur.getY());
        }
        catch(OutOfGroundException e){
            lift();
            e.getMessage();
            e.printStackTrace();
        }
    }


    public void land(Point position, Direction direction){
        try{
            if (visor.checkCell(position.getX(), position.getY())) {
                curPoint = position;
                dir = direction;
                fly = false;
            }
        }
        catch (OutOfGroundException ex) {
            ex.getMessage();
            lift();
        }
    }

    public void lift(){
        fly = true;
        dir = null;
        curPoint = null;
    }

    public boolean isAirborne() {
        if(fly==true || fly==false) {return fly;}
        else{
            try {
                if (visor.checkCell(getCurrentPosition().getX(), getCurrentPosition().getY())) {fly=false;}
                else{return fly=true;}
            }
            catch(OutOfGroundException ex) {
                System.out.println(ex.getMessage());
                lift();
            }
        }
        return fly;
    }

    public void setFly(boolean x) {
        fly = x;
    }

    public void move(){
        if(!fly){
            try {
                switch (this.getDirection()) {
                    case NORTH:
                        if (visor.checkCell(getCurrentPosition().getX(), getCurrentPosition().getY() - 1)) {
                            setCurPoint(new Point(getCurrentPosition().getX(), getCurrentPosition().getY() - 1));
                        }
                        break;
                    case SOUTH:
                        if (visor.checkCell(getCurrentPosition().getX(), getCurrentPosition().getY() + 1)) {
                            setCurPoint(new Point(getCurrentPosition().getX(), getCurrentPosition().getY() + 1));
                        }
                        break;
                    case EAST:
                        if (visor.checkCell(getCurrentPosition().getX() + 1, getCurrentPosition().getY())) {
                            setCurPoint(new Point(getCurrentPosition().getX() + 1, getCurrentPosition().getY()));
                        }
                        break;
                    case WEST:
                        if (visor.checkCell(getCurrentPosition().getX() - 1, getCurrentPosition().getY())) {
                            setCurPoint(new Point(getCurrentPosition().getX() - 1, getCurrentPosition().getY()));
                        }
                        break;
                }
            }
            catch (OutOfGroundException ex) {
                lift();
                ex.getMessage();
                ex.printStackTrace();

            }
        }
        else{
            System.out.println("Введите команду приземления.");
        }
    }

    public void turnTo(Direction direction){
        setDir(direction);
        }


}
