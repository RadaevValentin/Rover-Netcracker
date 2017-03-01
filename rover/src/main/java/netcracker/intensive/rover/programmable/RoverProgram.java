package netcracker.intensive.rover.programmable;

import netcracker.intensive.rover.command.RoverCommand;

import java.util.*;

public class RoverProgram {
    public static final String LOG = "log";
    public static final String STATS = "stats";
    public static final String SEPARATOR = "===";

    private Map<String, Object> configMap = new HashMap<>();
    private List<RoverCommand> commandList = new ArrayList<>();
    public RoverProgram(Map<String, Object> m, List<RoverCommand> l){
        configMap = m;
        commandList = l;
    }

    public Map<String, Object> getSettings() {
        return new ConfigMapWrapper(configMap);
    }

    public List<RoverCommand> getCommands() {
        return commandList;
    }

    private class ConfigMapWrapper extends HashMap<String, Object>
    {
        public ConfigMapWrapper(Map<String, Object> configMap) {
            super();
            for (String key : configMap.keySet()) {
                super.put(key, configMap.get(key));
            }
        }

        @Override
        public Object put(String key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Map<? extends String, ? extends Object> m) {
            throw new UnsupportedOperationException();
        }
    }
}
