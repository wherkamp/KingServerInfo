package me.kingtux.kingserverinfo.utils.config;

import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigManager {
    private KingServerInfoMain mainclass;
    private File mainConfig;

    private File argumentsFile;
    private FileConfiguration argumentsConfig;

    public ConfigManager(KingServerInfoMain mainclass) {
        this.mainclass = mainclass;
        argumentsFile = new File(mainclass.getDataFolder(), "CustomArguments.yml");


    }

    public void setupConfig() {
        mainConfig = new File(mainclass.getDataFolder(), "config.yml");
        if (!(mainConfig.exists())) {
            //Create The Config
            mainclass.logger.log(Level.WARNING, "The Config did not exist creating one");
            mainclass.saveDefaultConfig();

        }
        if (!(argumentsFile.exists())) {
            mainclass.logger.log(Level.WARNING, "The Arguments Config did not exist creating one");
            mainclass.saveResource("CustomArguments.yml", false);

        }
        argumentsConfig = new YamlConfiguration();

        try {
            argumentsConfig.load(argumentsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        setupConfigSettings();

    }

    public void setupConfigSettings() {


        mainclass.setConfigSettings(new ConfigSettings(this));
    }

    public FileConfiguration getMainConfig() {
        return mainclass.getConfig();
    }

    public FileConfiguration getArgumentsConfig() {
        return argumentsConfig;
    }


}
