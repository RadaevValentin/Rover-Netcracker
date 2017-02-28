package netcracker.intensive.rover.programmable;

import netcracker.intensive.rover.Point;
import netcracker.intensive.rover.command.*;
import netcracker.intensive.rover.constants.Direction;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static netcracker.intensive.rover.programmable.RoverProgram.*;

public class RoverCommandParser {


    private ProgrammableRover rov;
    private String file;
    private RoverProgram roverProgram;
    Map<String, Object> conf;
    List<RoverCommand> com;
    File fileParse;

    public RoverCommandParser(ProgrammableRover rover, String file)
    {
        rov = rover;
        this.file = file;
    }

    public RoverProgram getProgram()
    {
        ArrayList<String> list = new ArrayList<>();
        String s;
        try(BufferedReader reader = new BufferedReader(new FileReader(URLDecoder.decode(this.getClass().getResource(file).getFile(), "UTF-8")))) {
            while ((s = reader.readLine()) != null) {
                String[] str = s.split(" ");
                for (int i = 0; i < str.length; i++) {
                    list.add(str[i]);
                }
            }
        }
        catch(Exception e)
        {
            throw new RoverCommandParserException(e);
        }
        conf = new HashMap<>();
        com = new ArrayList<>();
        for (int f = 0; f < list.size(); f++)
        {
            if (list.get(f).equals(SEPARATOR))
            {
                int y = 0, u = f + 1;
                while (y < f)
                {
                    if(list.get(y).equals(LOG)) {
                        if (list.get(y + 1).toUpperCase().equals("ON")) {
                            conf.put(list.get(y), true);
                        }
                        else {
                            if (list.get(y + 1).toUpperCase().equals("OFF")) {
                                conf.put(list.get(y), false);
                            }
                        }
                    }
                    if(list.get(y).equals(STATS)) {
                        if (list.get(y + 1).toUpperCase().equals("ON")) {
                            conf.put(list.get(y), true);
                        }
                        else {
                            if (list.get(y + 1).toUpperCase().equals("OFF")) {
                                conf.put(list.get(y), false);
                            }
                        }
                    }
                    y++;
                }
                while (u < list.size()) {
                    if (conf.get(LOG).equals(true)) {
                        switch (list.get(u).toUpperCase()) {
                            case MoveCommand.MOVE:
                                com.add(new LoggingCommand(new MoveCommand(rov)));
                                break;
                            case LandCommand.LAND:
                                com.add(new LoggingCommand(new LandCommand(rov, new Point(Integer.parseInt(list.get(u + 1)), Integer.parseInt(list.get(u + 2))), Direction.valueOf(list.get(u + 3).toUpperCase()))));
                                break;
                            case LiftCommand.LIFT:
                                com.add(new LoggingCommand(new LiftCommand(rov)));
                                break;
                            case TurnCommand.TURN:
                                com.add(new LoggingCommand(new TurnCommand(rov, Direction.valueOf(list.get(u + 1).toUpperCase()))));
                                break;
                        }
                        u++;
                    } else {
                        if (conf.get(LOG).equals(false)) {
                            switch (list.get(u).toUpperCase()) {
                                case MoveCommand.MOVE:
                                    com.add(new MoveCommand(rov));
                                    break;
                                case LandCommand.LAND:
                                    com.add(new LandCommand(rov, new Point(Integer.parseInt(list.get(u + 1)), Integer.parseInt(list.get(u + 2))), Direction.valueOf(list.get(u + 3).toUpperCase())));
                                    break;
                                case LiftCommand.LIFT:
                                    com.add(new LiftCommand(rov));
                                    break;
                                case TurnCommand.TURN:
                                    com.add(new TurnCommand(rov, Direction.valueOf(list.get(u + 1).toUpperCase())));
                                    break;
                            }
                            u++;
                        }
                    }
                }
            }
        }
        return roverProgram = new RoverProgram(conf, com);
    }
}
