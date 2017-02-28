package netcracker.intensive.rover.programmable;

/**
 * Этот класс должен уметь все то, что умеет обычный Rover, но при этом он еще должен уметь выполнять программы,
 * содержащиеся в файлах
 */

import netcracker.intensive.rover.GroundVisor;
import netcracker.intensive.rover.Rover;
import netcracker.intensive.rover.command.RoverCommand;
import netcracker.intensive.rover.stats.SimpleRoverStatsModule;

import java.util.List;
import java.util.Map;

import static netcracker.intensive.rover.programmable.RoverProgram.STATS;

/**
 * Created by YFJ on 06.02.2017.
 */
public class ProgrammableRover extends Rover implements ProgramFileAware {
    private SimpleRoverStatsModule module;
    private RoverCommandParser parser;

    public ProgrammableRover(GroundVisor visor, SimpleRoverStatsModule module) {
        super(visor);
        this.module = module;

    }

    public void executeProgramFile(String path){
        parser = new RoverCommandParser(this, path);
        if (getSettings().get(STATS).equals(true))
        {
            module.registerPosition(this.getCurrentPosition());
            for (RoverCommand roverCommand : getCommands()) {
                roverCommand.execute();
                module.registerPosition(this.getCurrentPosition());
            }
        }
        else{
            for (RoverCommand roverCommand : getCommands()) {
                roverCommand.execute();
            }
        }
    }

    public Map<String, Object> getSettings()
    {
        return parser.getProgram().getSettings();
    }

    public List<RoverCommand> getCommands()
    {
        return parser.getProgram().getCommands();
    }
}