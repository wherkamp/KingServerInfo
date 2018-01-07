package me.kingtux.kingserverinfo.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class KingTuxUtils {

    /**
     * @param oldList
     * @return List with Color codes added!
     */
    public static List<String> color(List<String> oldList) {
        List<String> newString = new ArrayList<>();
        for (String s : oldList) {
            newString.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return newString;
    }

    /**
     * @param oldString
     * @return new String with colors!
     */
    public static String color(String oldString) {
        String newString = ChatColor.translateAlternateColorCodes('&', oldString);
        return newString;
    }

    /**
     * @param oldList
     * @param oldString
     * @param newString
     * @return List with the String replaced out of it!
     */
    public static List<String> replaceList(List<String> oldList, String oldString, String newString) {
        List<String> newList = new ArrayList<>();
        if (oldList == null) {
            return newList;
        }
        for (String s : oldList) {
            newList.add(s.replace(oldString, newString));
        }
        return newList;
    }
}
