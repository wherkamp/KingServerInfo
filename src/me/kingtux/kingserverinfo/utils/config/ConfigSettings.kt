package me.kingtux.kingserverinfo.utils.config

import me.kingtux.kingserverinfo.commands.Arguments
import me.kingtux.kingserverinfo.utils.KingTuxUtils
import me.kingtux.kingserverinfo.utils.SkullParser
import me.kingtux.kingserverinfo.utils.mediagui.Items
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*
import java.util.logging.Level


class ConfigSettings(private val configManager: ConfigManager) {
    var mediaSize: Int = 0
        private set
    var prefix: String
        private set
    var serverInfoCommand: String
        private set
    var serverInfoDescription: String
        private set
    var ownerInfo: String
        private set
    var guiTitle: String
        private set
    var owner: String
        private set
    var baseCommandDataInfo: List<String>
        private set
    var rules: List<String>
        private set
    var onJoinBroudcastMessage: List<String>
        private set
    var onJoinPersonalMessage: List<String>
        private set
    var onLeaveMessage: List<String>
        private set

    var staffList: MutableMap<String, String>? = null
    var guiItems: ArrayList<Items>? = null
        private set
    var customArguments: ArrayList<Arguments>? = null
        private set

    init {
        prefix = configManager.getMainConfig().getString("prefix")
        serverInfoCommand = configManager.getMainConfig().getString("BaseCommand")
        serverInfoDescription = configManager.getMainConfig().getString("BaseCommand-description")
        baseCommandDataInfo = configManager.getMainConfig().getStringList("BaseCommand-data-info")
        rules = configManager.getMainConfig().getStringList("Rules")
        onJoinBroudcastMessage = configManager.getMainConfig().getStringList("OnJoin-Event.Broudcast-Message")
        onJoinPersonalMessage = configManager.getMainConfig().getStringList("OnJoin-Event.Personal-Message")
        onLeaveMessage = configManager.getMainConfig().getStringList("OnLeave-Event.Broudcast-Message")
        owner = configManager.getMainConfig().getString("Staff.Owner.Owner-Name")
        ownerInfo = configManager.getMainConfig().getString("Staff.Owner.Owner-Info")
        mediaSize = configManager.getMainConfig().getInt("Media.Size")
        guiTitle = KingTuxUtils.color(configManager.getMainConfig().getString("Media.Title"))


        getMediaGuifromConfig()
        getStaffFromConfig()
        getNewArgumentsFromConfig()
    }

    private fun getNewArgumentsFromConfig() {
        customArguments = ArrayList()
        for (stringArguments in configManager.argumentsConfig!!.getConfigurationSection("Arguments").getKeys(false)) {

            val basePathToArguments = "Arguments." + stringArguments
            if (checkArgumentConfig(basePathToArguments)) {
                val argument: Arguments
                val argumentAlias = configManager.argumentsConfig!!.getStringList(basePathToArguments + ".Alias")
                val argumentPlayerMessage = configManager.argumentsConfig!!.getStringList(basePathToArguments + ".Player-Message")
                val argumentPlayerCommand = configManager.argumentsConfig!!.getStringList(basePathToArguments + ".Player-Commands")
                val argumentConsoleCommand = configManager.argumentsConfig!!.getStringList(basePathToArguments + ".Console-Commands")
                val argumentBroudCastMessage = configManager.argumentsConfig!!.getStringList(basePathToArguments + ".Broudcast-Message")
                val argumentDescription = configManager.argumentsConfig!!.getString(basePathToArguments + ".Description")
                argument = Arguments(stringArguments, argumentDescription, argumentAlias,
                        argumentPlayerMessage, argumentBroudCastMessage,
                        argumentPlayerCommand, argumentConsoleCommand)
                customArguments!!.add(argument)
            }
        }
    }

    private fun getMediaGuifromConfig() {
        guiItems = ArrayList()

        for (ItemName in configManager.getMainConfig().getConfigurationSection("Media.Items").getKeys(false)) {
            val Position = "Media.Items." + ItemName
            val itemStack: ItemStack
            if (configManager.getMainConfig().getString(Position + ".Icon.Item.Item-Type").contains(":UUID:")) {
                itemStack = SkullParser.createSkull(configManager.getMainConfig().getString(Position + ".Icon.Item.Item-Type"))
            } else {
                itemStack = ItemStack(Material.getMaterial(configManager.getMainConfig().getString(Position + ".Icon.Item.Item-Type")))
            }
            val displayName = configManager.getMainConfig().getString(Position + ".Icon.Item.Display-Name")
            val lore = configManager.getMainConfig().getStringList(Position + ".Item.Icon.Lore")
            val itemMeta = itemStack.itemMeta
            itemMeta.displayName = KingTuxUtils.color(displayName)
            itemMeta.lore = KingTuxUtils.color(lore)
            itemStack.itemMeta = itemMeta
            val items = Items(configManager.getMainConfig().getInt(Position + ".Icon.Position"), itemStack, configManager.getMainConfig().getString(Position + ".Link.Link"), configManager.getMainConfig().getString(Position + ".Link.Before-Link-Message"), configManager.getMainConfig().getBoolean(Position + ".Link.Clickable"))
            //System.out.println(items.toString());
            guiItems!!.add(items)
        }
    }

    private fun getStaffFromConfig() {
        staffList = HashMap()
        for (StaffMember in configManager.getMainConfig().getConfigurationSection("Staff.Staff-Members").getKeys(false)) {

            val StaffMemberInfo = configManager.getMainConfig().getString("Staff.Staff-Members.$StaffMember.Staff-Info")
            staffList!![StaffMember] = StaffMemberInfo
        }

    }

    private fun checkArgumentConfig(basePathToArguments: String): Boolean {
        if (configManager.argumentsConfig!!.getString(basePathToArguments) == null) {
            Bukkit.getLogger().log(Level.SEVERE, "All Arguments must have a Argument?!")
            return false
        } else if (configManager.argumentsConfig!!.getString(basePathToArguments + ".Description") == null) {
            Bukkit.getLogger().log(Level.SEVERE, "All Arguments must have a Description")
            return false
        }
        return true
    }


}
