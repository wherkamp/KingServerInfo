package me.kingtux.kingserverinfo


import me.kingtux.kingserverinfo.bstats.Metrics
import me.kingtux.kingserverinfo.commands.ServerInfoCommand
import me.kingtux.kingserverinfo.events.ClickEvent
import me.kingtux.kingserverinfo.events.JoinEvent
import me.kingtux.kingserverinfo.events.LeaveEvent
import me.kingtux.kingserverinfo.utils.config.ConfigManager
import me.kingtux.kingserverinfo.utils.config.ConfigSettings
import me.kingtux.kingserverinfo.utils.mediagui.MediaGui
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandMap
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class KingServerInfoMain : JavaPlugin() {

    var configManager: ConfigManager? = null
    var configSettings: ConfigSettings? = null
    var mediaGui: MediaGui? = null
        private set

    override fun onEnable() {
        if (Bukkit.getServer().pluginManager.isPluginEnabled("PlaceholderAPI")) {
            configManager = ConfigManager(this)
            configManager!!.setupConfig()

            logger.log(Level.INFO,
                    ChatColor.translateAlternateColorCodes('&',
                            configSettings!!.prefix + " Successfully Got the Config and Settings"))
            try {
                val bukkitCommandMap = Bukkit.getServer().javaClass.getDeclaredField("commandMap")

                bukkitCommandMap.isAccessible = true
                val commandMap = bukkitCommandMap.get(Bukkit.getServer()) as CommandMap
                commandMap.register(configSettings!!.serverInfoCommand, ServerInfoCommand(configSettings!!.serverInfoCommand, configSettings!!.serverInfoDescription, this))

            } catch (e: Exception) {
                e.printStackTrace()
            }

            enableEvents()
            mediaGui = MediaGui(this)
            val metrics = Metrics(this)


        } else {
            logger.log(Level.SEVERE, "I find your lack of the PlaceHolderAPI disturbing.")
            server.pluginManager.disablePlugin(this)
        }


    }

    fun reloadPlugin() {
        configManager!!.reloadConfig()
        logger.log(Level.INFO,
                ChatColor.translateAlternateColorCodes('&',
                        configSettings!!.prefix + " Successfully Got the Config and Settings"))

    }


    override fun onDisable() {
    }

    private fun enableEvents() {
        Bukkit.getServer().pluginManager.registerEvents(ClickEvent(this), this)
        Bukkit.getServer().pluginManager.registerEvents(LeaveEvent(this), this)
        Bukkit.getServer().pluginManager.registerEvents(JoinEvent(this), this)

    }
}
