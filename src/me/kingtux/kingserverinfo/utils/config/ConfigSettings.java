package me.kingtux.kingserverinfo.utils.config;

public class ConfigSettings {
    private ConfigManager configManager;
    private String prefix, ServerInfoCommand, ServerInfoDescription;

    public ConfigSettings(ConfigManager configManager){
        this.configManager = configManager;
        getAllSettings();
    }


    public void getAllSettings() {

        prefix = configManager.getMainConfig().getString("Prefix");
        ServerInfoCommand = configManager.getMainConfig().getString("BaseCommand");
        ServerInfoDescription = configManager.getMainConfig().getString("BaseCommand-description");

    }


    public String getPrefix() {
        return prefix;
    }

    public String getServerInfoCommand() {
        return ServerInfoCommand;
    }

    public String getServerInfoDescription() {
        return ServerInfoDescription;
    }
}
