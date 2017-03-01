package netcracker.intensive.rover.stats;

import netcracker.intensive.rover.Point;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleRoverStatsModule implements RoverStatsModule {
    private ArrayList<Point> statList = new ArrayList<Point>();

    public void registerPosition(Point position){
        if(!isVisited(position)) statList.add(position);
    }
    public boolean isVisited(Point point){
        return statList.contains(point);
    }
    public Collection<Point> getVisitedPoints(){
        return statList;
    }
}
