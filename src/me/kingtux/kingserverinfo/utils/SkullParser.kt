package me.kingtux.kingserverinfo.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

/**
 * @author KingTux
 */
object SkullParser {
    /**
     * @param skull
     * @return
     */
    fun createSkull(skull: String): ItemStack {
        val skullParsed = skull.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val itemStack = ItemStack(Material.SKULL_ITEM, 1, 3.toShort())
        val itemMeta = itemStack.itemMeta as SkullMeta
        itemMeta.owningPlayer = Bukkit.getOfflinePlayer(UUID.fromString(skullParsed[2]))
        itemStack.itemMeta = itemMeta
        return itemStack
    }
}
