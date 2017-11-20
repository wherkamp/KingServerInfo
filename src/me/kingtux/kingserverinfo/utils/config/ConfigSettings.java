package me.kingtux.kingserverinfo.utils.config;

import me.kingtux.kingserverinfo.commands.Arguments;
import me.kingtux.kingserverinfo.utils.JsonManager;
import me.kingtux.kingserverinfo.utils.MediaGui.Items;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class ConfigSettings {
    private ConfigManager configManager;
    private int mediaSize;
    private String prefix, serverInfoCommand, serverInfoDescription, ownerInfo, guiTitle, owner;
    private List<String> baseCommandDataInfo, rules, onJoinBroudcastMessage, onJoinPersonalMessage, onLeaveMessage;
    private ArrayList<TextComponent> staffList;
    private ArrayList<Items> guiItems;

    private ArrayList<Arguments> customArguments;

    public ArrayList<Arguments> getCustomArguments() {
        return customArguments;
    }

    public ConfigSettings(ConfigManager configManager) {
        this.configManager = configManager;
        getAllSettings();
    }

    public void getAllSettings() {

        prefix = configManager.getMainConfig().getString("prefix");
        serverInfoCommand = configManager.getMainConfig().getString("BaseCommand");
        serverInfoDescription = configManager.getMainConfig().getString("BaseCommand-description");
        baseCommandDataInfo = configManager.getMainConfig().getStringList("BaseCommand-data-info");
        rules = configManager.getMainConfig().getStringList("Rules");
        onJoinBroudcastMessage = configManager.getMainConfig().getStringList("OnJoin-Event.Broudcast-Message");
        onJoinPersonalMessage = configManager.getMainConfig().getStringList("OnJoin-Event.Personal-Message");
        onLeaveMessage = configManager.getMainConfig().getStringList("OnLeave-Event.Broudcast-Message");
        owner = configManager.getMainConfig().getString("Staff.Owner.Owner-Name");
        ownerInfo = configManager.getMainConfig().getString("Staff.Owner.Owner-Info");
        mediaSize = configManager.getMainConfig().getInt("Media.Size");
        guiTitle = configManager.getMainConfig().getString("Media.Title");


        getMediaGuifromConfig();
        getStaffFromConfig();
        getNewArgumentsFromConfig();
    }

    private void getNewArgumentsFromConfig() {
        for (String stringArguments : configManager.getArgumentsConfig().getConfigurationSection("Arguments").getKeys(false)) {

            String basePathToArguments = "Arguments." + stringArguments;
            if (checkArgumentConfig(basePathToArguments) == true) {
                Arguments argument;
                //Checking if it has Alias
                if (configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Alias") == null) {
                    argument = new Arguments(configManager.getArgumentsConfig().getString(basePathToArguments),
                            configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Message"),
                            configManager.getArgumentsConfig().getString(basePathToArguments + ".Description"));
                } else {
                    argument = new Arguments(configManager.getArgumentsConfig().getString(basePathToArguments),
                            configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Message"),
                            configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Alias"),
                            configManager.getArgumentsConfig().getString(basePathToArguments + ".Description"));
                }

                customArguments.add(argument);
            }
        }
    }

    private void getMediaGuifromConfig() {
        guiItems = new ArrayList<Items>();

        for (final String ItemName : configManager.getMainConfig().getConfigurationSection("Media.Items").getKeys(false)) {
            String Position = "Media.Items." + ItemName;
            Items NewItem = new Items(configManager.getMainConfig().getInt(Position + ".Icon.Position"),
                    configManager.getMainConfig().getString(Position + ".Icon.Name"),
                    configManager.getMainConfig().getString(Position + ".Icon.Item-Name"),
                    configManager.getMainConfig().getString(Position + ".Icon.Item"),
                    configManager.getMainConfig().getString(Position + ".Link"),
                    configManager.getMainConfig().getBoolean(Position + ".Icon.Clickable"),
                    configManager.getMainConfig().getStringList(Position + ".Icon.Sub-Text"));
            System.out.println(NewItem.toString());
            guiItems.add(NewItem);
        }
    }


    private void getStaffFromConfig() {
        staffList = new ArrayList<TextComponent>();
        for (final String StaffMember : configManager.getMainConfig().getConfigurationSection("Staff.Staff-Members").getKeys(false)) {

            String StaffMemberInfo = configManager.getMainConfig().getString("Staff.Staff-Members." + StaffMember + ".Staff-Info");
            staffList.add(JsonManager.MakeHoverableMessage(StaffMember, StaffMemberInfo));
        }

    }

    private Boolean checkArgumentConfig(String basePathToArguments) {
        if (configManager.getArgumentsConfig().getString(basePathToArguments) == null) {
            Bukkit.getLogger().log(Level.SEVERE, "All Arguments must have a Description");
            return false;
        } else if (configManager.getArgumentsConfig().getString(basePathToArguments + ".Description") == null) {
            Bukkit.getLogger().log(Level.SEVERE, "All Arguments must have a Description");
            return false;
        } else if (configManager.getArgumentsConfig().getStringList(basePathToArguments + "Message") == null) {
            Bukkit.getLogger().log(Level.SEVERE, "All Arguments must have a Description");
            return false;
        }
        return true;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getServerInfoCommand() {
        return serverInfoCommand;
    }

    public String getServerInfoDescription() {
        return serverInfoDescription;
    }

    public List<String> getBaseCommandDataInfo() {
        return baseCommandDataInfo;
    }

    public List<String> getRules() {
        return rules;
    }

    public ArrayList<TextComponent> getStaffList() {
        return staffList;
    }

    public List<String> getOnJoinBroudcastMessage() {
        return onJoinBroudcastMessage;
    }

    public List<String> getOnJoinPersonalMessage() {
        return onJoinPersonalMessage;
    }

    public List<String> getOnLeaveMessage() {
        return onLeaveMessage;
    }

    public String getOwner() {
        return owner;
    }

    public String getGuiTitle() {
        return guiTitle;
    }

    public int getMediaSize() {
        return mediaSize;
    }

    public ArrayList<Items> getGuiItems() {
        return guiItems;
    }

    public String getOwnerInfo() {
        return ownerInfo;
    }
}
