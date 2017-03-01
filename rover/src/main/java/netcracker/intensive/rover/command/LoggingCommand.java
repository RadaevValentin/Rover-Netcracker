package netcracker.intensive.rover.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingCommand implements RoverCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingCommand.class);
    private RoverCommand rCom;
    public void logInfo(String message){
        LOGGER.info(message);
    }
    public void logDebug(String message){
        LOGGER.debug(message);
    }

    public LoggingCommand(RoverCommand r){
        rCom = r;
    }

    public void execute(){
        rCom.execute();
        LOGGER.debug(rCom.toString());
    }

    public String toString(){
       return rCom.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoggingCommand that = (LoggingCommand) o;
        return rCom.equals(that.rCom);
    }

    @Override
    public int hashCode() {
        return rCom.hashCode();
    }
}