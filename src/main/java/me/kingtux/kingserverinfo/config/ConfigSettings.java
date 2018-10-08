package me.kingtux.kingserverinfo.config;

import me.kingtux.kingserverinfo.commands.Arguments;
import me.kingtux.kingserverinfo.mediagui.MediaGuiItem;
import me.kingtux.kingserverinfo.utils.SkullParser;
import me.kingtux.kingserverinfo.utils.TuxUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;


public class ConfigSettings {
    private ConfigManager configManager;
    private int mediaSize;
    private String prefix, serverInfoCommand, serverInfoDescription, ownerInfo, guiTitle, owner;
    private List<String> baseCommandDataInfo, rules, onJoinBroudcastMessage, onJoinPersonalMessage, onLeaveMessage;

    private Map<String, String> staffList;
    private ArrayList<MediaGuiItem> guiItems;
    private ArrayList<Arguments> customArguments;

    protected ConfigSettings(ConfigManager configManager) {
        this.configManager = configManager;
        getAllSettings();
    }

    public Map<String, String> getStaffList() {
        return staffList;
    }

    public ArrayList<Arguments> getCustomArguments() {
        return customArguments;
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
        guiTitle = TuxUtils.color(configManager.getMainConfig().getString("Media.Title"));


        getMediaGuifromConfig();
        getStaffFromConfig();
        getNewArgumentsFromConfig();
    }

    private void getNewArgumentsFromConfig() {
        customArguments = new ArrayList<>();
        for (String stringArguments : configManager.getArgumentsConfig().getConfigurationSection("Arguments").getKeys(false)) {

            String basePathToArguments = "Arguments." + stringArguments;
            if (checkArgumentConfig(basePathToArguments)) {
                Arguments argument;
                String argumentArg = stringArguments;
                List<String> argumentAlias = configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Alias");
                List<String> argumentPlayerMessage = configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Player-Message");
                List<String> argumentPlayerCommand = configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Player-Commands");
                List<String> argumentConsoleCommand = configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Console-Commands");
                List<String> argumentBroudCastMessage = configManager.getArgumentsConfig().getStringList(basePathToArguments + ".Broudcast-Message");
                String argumentDescription = configManager.getArgumentsConfig().getString(basePathToArguments + ".Description");
                argument = new Arguments(argumentArg, argumentDescription, argumentAlias,
                        argumentPlayerMessage, argumentBroudCastMessage,
                        argumentPlayerCommand, argumentConsoleCommand);
                customArguments.add(argument);
            }
        }
    }

    private void getMediaGuifromConfig() {
        guiItems = new ArrayList<>();

        for (final String ItemName : configManager.getMainConfig().getConfigurationSection("Media.Items").getKeys(false)) {
            String Position = "Media.Items." + ItemName;
            ItemStack itemStack;
            if (configManager.getMainConfig().getString(Position + ".Icon.Item.Item-Type").toLowerCase().contains(":uuid:") ||
                    configManager.getMainConfig().getString(Position + ".Icon.Item.Item-Type").toLowerCase().contains(":headlib:")) {
                itemStack = SkullParser.createSkull(configManager.getMainConfig().getString(Position + ".Icon.Item.Item-Type"));
            } else {
                Material material = Material.getMaterial(
                        configManager.getMainConfig().getString(Position + ".Icon.Item.Item-Type"));
                if (material != null) {
                    itemStack = new ItemStack(material);
                } else {
                    itemStack = new ItemStack(Material.BARRIER);
                    System.out.println(
                            configManager.getMainConfig().getString(Position + ".Icon.Item.Item-Type")
                                    + " is not a material");
                }
            }
            String displayName = configManager.getMainConfig().getString(Position + ".Icon.Item.Display-Name");
            List<String> lore = configManager.getMainConfig().getStringList(Position + ".Item.Icon.Lore");
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(TuxUtils.color(displayName));
            itemMeta.setLore(TuxUtils.color(lore));
            itemStack.setItemMeta(itemMeta);
            MediaGuiItem items = new MediaGuiItem(configManager.getMainConfig().getInt(Position + ".Icon.Position"), itemStack, configManager.getMainConfig().getString(Position + ".Link.Link"), configManager.getMainConfig().getString(Position + ".Link.Before-Link-Message"), configManager.getMainConfig().getBoolean(Position + ".Link.Clickable"));
            //System.out.println(items.toString());
            guiItems.add(items);

        }
    }

    private void getStaffFromConfig() {
        staffList = new HashMap<>();
        for (final String StaffMember : configManager.getMainConfig().getConfigurationSection("Staff.Staff-Members").getKeys(false)) {

            String StaffMemberInfo = configManager.getMainConfig().getString("Staff.Staff-Members." + StaffMember + ".Staff-Info");
            staffList.put(StaffMember, StaffMemberInfo);
        }

    }

    private Boolean checkArgumentConfig(String basePathToArguments) {
        if (configManager.getArgumentsConfig().getString(basePathToArguments) == null) {
            Bukkit.getLogger().log(Level.SEVERE, "All Arguments must have a Argument?!");
            return false;
        } else if (configManager.getArgumentsConfig().getString(basePathToArguments + ".Description") == null) {
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

    public ArrayList<MediaGuiItem> getGuiItems() {
        return guiItems;
    }

    public String getOwnerInfo() {
        return ownerInfo;
    }


}
