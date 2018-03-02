package me.kingtux.kingserverinfo.utils.mediagui

import me.kingtux.kingserverinfo.utils.JsonManager
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.inventory.ItemStack

class Items(val position: Int, val item: ItemStack, link: String, beforeLinkMessage: String, val isClickable: Boolean?) {
    val link: TextComponent


    init {
        this.link = JsonManager.makeLinkText(beforeLinkMessage, link)
    }

    override fun toString(): String {
        return "Items{" +
                "position=" + position +
                ", link=" + link.toString() +
                ", item=" + item.toString() +
                ", clickable=" + isClickable +
                '}'.toString()
    }
}
