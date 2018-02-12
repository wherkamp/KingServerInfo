package me.kingtux.kingserverinfo.utils;

import com.mojang.authlib.GameProfile;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class SkullParser {
    public static ItemStack createSkull(String skull) {
        String[] skullParsed = skull.split(":");
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, ((short) 3));
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString(skullParsed[2])));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getSkull(String url) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url.isEmpty()) return head;


        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new com.mojang.authlib.properties.Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }
}
