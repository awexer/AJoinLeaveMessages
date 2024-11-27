package com.awexer.aJoinLeaveMessages.gui;

import com.awexer.aJoinLeaveMessages.AJoinLeaveMessages;
import com.awexer.aJoinLeaveMessages.messages.JoinMessages;
import com.awexer.aJoinLeaveMessages.messages.LeaveMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class InitializeGui {
    private Inventory gui;

    public void createGui(FileConfiguration config) {
        gui = Bukkit.createInventory(null, config.getInt("gui.slots", 54),
                    ChatColor.translateAlternateColorCodes('&', config.getString("gui.name", "&4&lLeave/join messages")));

        List<String> leave_messages = LeaveMessages.getMessages();
        List<String> join_messages = JoinMessages.getMessages();

        if (!join_messages.isEmpty() && !leave_messages.isEmpty()) {
            if (Material.matchMaterial(config.getString("gui.join_icon", "lime_dye").toUpperCase()) != null) {
                for (String str : join_messages) {
                    addJoinItem(config, str, join_messages);
                }
            }

            if (Material.matchMaterial(config.getString("gui.leave_icon", "red_dye").toUpperCase()) != null) {
                for (String str : leave_messages) {
                    addLeaveItem(config, str, leave_messages);
                }
            }

            if (Material.matchMaterial(config.getString("gui.default_icon", "orange_dye").toUpperCase()) != null) {
                addDefaultItem(config);
            }
        }
    }

    private void addJoinItem(FileConfiguration config, String str, List<String> join_messages) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(config.getString("gui.join_icon", "lime_dye").toUpperCase())));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("gui.join_name", "&aJoin: %message%").replace("%message%", str).replace("%p%", "YOUR_NAME")));

        if (!config.getStringList("gui.join_lore").isEmpty()) {
            List<String> lore = new ArrayList<>();
            for (String string : config.getStringList("gui.join_lore")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', string).replace("%message%", str));
            }
            meta.setLore(lore);
        }

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(new NamespacedKey(AJoinLeaveMessages.getInstance(), "join_message"), PersistentDataType.INTEGER, join_messages.indexOf(str));
        item.setItemMeta(meta);
        gui.addItem(item);
    }

    private void addLeaveItem(FileConfiguration config, String str, List<String> leave_messages) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(config.getString("gui.leave_icon", "red_dye").toUpperCase())));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("gui.leave_name", "&cLeave: %message%").replace("%message%", str).replace("%p%", "YOUR_NAME")));

        if (!config.getStringList("gui.leave_lore").isEmpty()) {
            List<String> lore = new ArrayList<>();
            for (String string : config.getStringList("gui.leave_lore")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', string).replace("%message%", str));
            }
            meta.setLore(lore);
        }

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(new NamespacedKey(AJoinLeaveMessages.getInstance(), "leave_message"), PersistentDataType.INTEGER, leave_messages.indexOf(str));
        item.setItemMeta(meta);
        gui.addItem(item);
    }

    private void addDefaultItem(FileConfiguration config) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(config.getString("gui.default_icon", "orange_dye").toUpperCase())));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("gui.default_name", "&6Default messages").replace("%p%", "YOUR_NAME")));

        if (!config.getStringList("gui.default_lore").isEmpty()) {
            List<String> lore = new ArrayList<>();
            for (String string : config.getStringList("gui.default_lore")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', string));
            }
            meta.setLore(lore);
        }

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(new NamespacedKey(AJoinLeaveMessages.getInstance(), "default_message"), PersistentDataType.INTEGER, 1);
        item.setItemMeta(meta);
        gui.addItem(item);
    }

    public void clearGui() {
        gui.clear();

        FileConfiguration config = AJoinLeaveMessages.getInstance().getConfig();

        List<String> leave_messages = LeaveMessages.getMessages();
        List<String> join_messages = JoinMessages.getMessages();

        if (!join_messages.isEmpty() && !leave_messages.isEmpty()) {
            if (Material.matchMaterial(config.getString("gui.join_icon", "lime_dye").toUpperCase()) != null) {
                for (String str : join_messages) {
                    addJoinItem(config, str, join_messages);
                }
            }

            if (Material.matchMaterial(config.getString("gui.leave_icon", "red_dye").toUpperCase()) != null) {
                for (String str : leave_messages) {
                    addLeaveItem(config, str, leave_messages);
                }
            }

            if (Material.matchMaterial(config.getString("gui.default_icon", "orange_dye").toUpperCase()) != null) {
                addDefaultItem(config);
            }
        }
    }

    public Inventory getGui() {
        return gui;
    }
}
