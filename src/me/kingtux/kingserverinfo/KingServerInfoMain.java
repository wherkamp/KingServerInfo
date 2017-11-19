package me.kingtux.kingserverinfo;


import me.kingtux.kingserverinfo.commands.ServerInfoCommand;
import me.kingtux.kingserverinfo.events.ClickEvent;
import me.kingtux.kingserverinfo.utils.MediaGui.MediaGui;
import me.kingtux.kingserverinfo.utils.config.ConfigManager;
import me.kingtux.kingserverinfo.utils.config.ConfigSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KingServerInfoMain extends JavaPlugin {
    private ConfigManager configManager;
    private ConfigSettings configSettings;
    public Logger logger = Bukkit.getLogger();
    private MediaGui mediaGui;

    public void onEnable() {
        configManager = new ConfigManager(this);
        configManager.setupConfig();

        Bukkit.getLogger().log(Level.INFO,
                ChatColor.translateAlternateColorCodes('&',
                        configSettings.getPrefix() + " Successfully Got the Config and Settings"));
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(configSettings.getServerInfoCommand(), new ServerInfoCommand(configSettings.getServerInfoCommand(), configSettings.getServerInfoDescription(), this));


        } catch (Exception e) {
            e.printStackTrace();
        }

        mediaGui = new MediaGui(this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClickEvent(this), this);
    }

    public void onDisable() {

    }

    public ConfigSettings getConfigSettings() {
        return configSettings;
    }

    public void setConfigSettings(ConfigSettings cF) {
        this.configSettings = cF;
    }


    public MediaGui getMediaGui() {
        return mediaGui;
    }
}
