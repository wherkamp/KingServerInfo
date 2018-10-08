package me.kingtux.kingserverinfo;


import me.kingtux.kingserverinfo.commands.ServerInfoCommand;
import me.kingtux.kingserverinfo.events.ClickEvent;
import me.kingtux.kingserverinfo.events.JoinEvent;
import me.kingtux.kingserverinfo.events.LeaveEvent;
import me.kingtux.kingserverinfo.utils.config.ConfigManager;
import me.kingtux.kingserverinfo.utils.config.ConfigSettings;
import me.kingtux.kingserverinfo.utils.mediagui.MediaGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class KingServerInfoMain extends JavaPlugin {

    private ConfigManager configManager;
    private ConfigSettings configSettings;
    private MediaGui mediaGui;

    public void onEnable() {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            configManager = new ConfigManager(this);
            configManager.setupConfig();

            getLogger().log(Level.INFO,
                    ChatColor.translateAlternateColorCodes('&',
                            configSettings.getPrefix() + " Successfully Got the Config and Settings"));

            enableEvents();
            getCommmandMap().register(configSettings.getServerInfoCommand(),
                    new ServerInfoCommand(configSettings.getServerInfoCommand(),
                            configSettings.getServerInfoDescription(), this));
            mediaGui = new MediaGui(this);
//      Metrics metrics = new Metrics(this);

        } else {
            getLogger().log(Level.SEVERE, "I find your lack of the PlaceHolderAPI disturbing.");
            getServer().getPluginManager().disablePlugin(this);
        }


    }

    public void reloadPlugin() {
        configManager.reloadConfig();
        configSettings.getAllSettings();

        getLogger().log(Level.INFO,
                ChatColor.translateAlternateColorCodes('&',
                        configSettings.getPrefix() + " Successfully Got the Config and Settings"));

    }


    public void onDisable() {

    }

    public ConfigSettings getConfigSettings() {
        return configSettings;
    }

    public void setConfigSettings(ConfigSettings cF) {
        this.configSettings = cF;
    }

    private void enableEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new ClickEvent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LeaveEvent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);

    }

    private CommandMap getCommmandMap() {
        if (isPaper()) {
            getLogger().log(Level.INFO, "It's a Paper BOI");
            try {
                Bukkit.getServer().getCommandMap();
                //Just in case
            } catch (NoSuchMethodError e) {
                getLogger().log(Level.INFO, "Paper's getCommandMap is missing reverting to old method.");
            }
        }
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            return (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isPaper() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public MediaGui getMediaGui() {
        return mediaGui;
    }
}
