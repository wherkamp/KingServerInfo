package me.kingtux.kingserverinfo.utils.config;

import me.kingtux.kingserverinfo.KingServerInfoMain;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.logging.Level;

public class ConfigManager {
    private KingServerInfoMain mainclass;
    private File mainConfig;

    public ConfigManager(KingServerInfoMain mainclass) {
        this.mainclass = mainclass;

    }

    public void setupConfig() {
        mainConfig = new File(mainclass.getDataFolder(), "config.yml");
        if (!(mainConfig.exists())) {
            //Create The Config
            mainclass.logger.log(Level.WARNING, "The Config did not exist creating one");
            mainclass.saveDefaultConfig();

        }
        setupConfigSettings();

    }

    public void setupConfigSettings() {


        mainclass.setConfigSettings(new ConfigSettings(this));
    }

    public FileConfiguration getMainConfig() {
        return mainclass.getConfig();
    }


}
