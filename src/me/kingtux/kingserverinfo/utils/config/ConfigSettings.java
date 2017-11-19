package me.kingtux.kingserverinfo.utils.config;

import me.kingtux.kingserverinfo.utils.JsonManager;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConfigSettings {
    private ConfigManager configManager;
    private String prefix, serverInfoCommand, serverInfoDescription, ownerInfo;
    private List<String> baseCommandDataInfo, rules, onJoinBroudcastMessage, onJoinPersonalMessage, onLeaveMessage;
    private ArrayList<TextComponent> staffList;
    private Player owner;
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
        owner = Bukkit.getServer().getPlayer(configManager.getMainConfig().getString("Staff.Owner.Owner-Name"));
        ownerInfo = configManager.getMainConfig().getString("Staff.Owner.Owner-Info");

        getStaffFromConfig();
    }

    private void getStaffFromConfig() {
        staffList = new ArrayList<TextComponent>();
        for (final String StaffMember : configManager.getMainConfig().getConfigurationSection("Staff.Staff-Members").getKeys(false)) {

            String StaffMemberInfo = configManager.getMainConfig().getString("Staff.Staff-Members." + StaffMember + ".Staff-Info");
            staffList.add(JsonManager.MakeHoverableMessage(StaffMember, StaffMemberInfo));
        }

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

    public Player getOwner() {
        return owner;
    }
}
