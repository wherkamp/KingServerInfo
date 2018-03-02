package me.kingtux.kingserverinfo.utils.config

import me.kingtux.kingserverinfo.KingServerInfoMain
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration

import java.io.File
import java.io.IOException
import java.util.logging.Level

class ConfigManager(private val mainclass: KingServerInfoMain) {
    private var mainConfig: File? = null
    private var configVersion: Double = 0.toDouble()
    private val currentVersion: Double = 0.toDouble()

    private val argumentsFile: File
    var argumentsConfig: FileConfiguration? = null
        private set

    private val version: Double
        get() = getMainConfig().getDouble("Config-Version")

    init {
        argumentsFile = File(mainclass.dataFolder, "CustomArguments.yml")
    }

    fun setupConfig() {
        mainConfig = File(mainclass.dataFolder, "config.yml")
        if (!mainConfig!!.exists()) {
            //Create The Config
            mainclass.logger.log(Level.WARNING, "The Config did not exist creating one")
            mainclass.saveDefaultConfig()

        }
        if (!argumentsFile.exists()) {
            mainclass.logger.log(Level.WARNING, "The Arguments Config did not exist creating one")
            mainclass.saveResource("CustomArguments.yml", false)

        }
        argumentsConfig = YamlConfiguration()

        try {
            argumentsConfig!!.load(argumentsFile)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }

        setupConfigSettings()
        configVersion = version
        if (configVersion == 0.0) {
            getMainConfig().set("Config-Version", 1.0)
            try {
                getMainConfig().save(mainConfig!!)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        ConfigUpdater(getMainConfig()).updateConfig()
    }

    private fun setupConfigSettings() {
        mainclass.configSettings = ConfigSettings(this)
    }

    fun getMainConfig(): FileConfiguration {
        return mainclass.config
    }


    fun reloadConfig() {
        try {
            argumentsConfig!!.load(argumentsFile)
            getMainConfig().load(mainConfig!!)
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
