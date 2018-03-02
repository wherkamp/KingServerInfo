package me.kingtux.kingserverinfo.utils

import org.bukkit.ChatColor
import java.util.*

object KingTuxUtils {

    /**
     * @param oldList
     * @return List with Color codes added!
     */
    fun color(oldList: List<String>): List<String> {
        val newString = ArrayList<String>()
        for (s in oldList) {
            newString.add(ChatColor.translateAlternateColorCodes('&', s))
        }
        return newString
    }

    /**
     * @param oldString
     * @return new String with colors!
     */
    fun color(oldString: String): String {
        return ChatColor.translateAlternateColorCodes('&', oldString)
    }

    /**
     * @param oldList
     * @param oldString
     * @param newString
     * @return List with the String replaced out of it!
     */
    fun replaceList(oldList: List<String>?, oldString: String, newString: String): List<String> {

        val newList = ArrayList<String>()
        if (oldList == null) {
            return newList
        }
        for (s in oldList) {
            newList.add(s.replace(oldString, newString))
        }
        return newList
    }
}
