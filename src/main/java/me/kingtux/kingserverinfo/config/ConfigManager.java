package me.kingtux.kingserverinfo.config;

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
  private double configVersion, currentVersion;

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
      mainclass.getLogger().log(Level.WARNING, "The Config did not exist creating one");
      mainclass.saveDefaultConfig();

    }
    if (!(argumentsFile.exists())) {
      mainclass.getLogger().log(Level.WARNING, "The Arguments Config did not exist creating one");
      mainclass.saveResource("CustomArguments.yml", false);

    }
    argumentsConfig = new YamlConfiguration();

    try {
      argumentsConfig.load(argumentsFile);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();
    }

    setupConfigSettings();
    configVersion = getVersion();
    if (configVersion == 0) {
      getMainConfig().set("Config-Version", 1.0);
      try {
        getMainConfig().save(mainConfig);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private double getVersion() {
    return getMainConfig().getDouble("Config-Version");
  }

  private void setupConfigSettings() {
    mainclass.setConfigSettings(new ConfigSettings(this));
  }

  public FileConfiguration getMainConfig() {
    return mainclass.getConfig();
  }

  public FileConfiguration getArgumentsConfig() {
    return argumentsConfig;
  }


  public void reloadConfig() {
    try {
      argumentsConfig.load(argumentsFile);
      getMainConfig().load(mainConfig);
    } catch (InvalidConfigurationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
