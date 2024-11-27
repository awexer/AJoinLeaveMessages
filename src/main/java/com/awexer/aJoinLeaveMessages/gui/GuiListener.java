package com.awexer.aJoinLeaveMessages.gui;

import com.awexer.aJoinLeaveMessages.AJoinLeaveMessages;
import com.awexer.aJoinLeaveMessages.messages.JoinMessages;
import com.awexer.aJoinLeaveMessages.messages.LeaveMessages;
import com.awexer.aJoinLeaveMessages.utils.DataUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class GuiListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (AJoinLeaveMessages.getInstance().getInitGui().getGui().equals(event.getClickedInventory())) {
            ItemStack item = event.getCurrentItem();

            if (item == null || item.getType() == Material.AIR) {
                return;
            }

            NamespacedKey join_key = new NamespacedKey(AJoinLeaveMessages.getInstance(), "join_message");
            NamespacedKey leave_key = new NamespacedKey(AJoinLeaveMessages.getInstance(), "leave_message");
            NamespacedKey default_key = new NamespacedKey(AJoinLeaveMessages.getInstance(), "default_message");

            if (item.getPersistentDataContainer().get(join_key, PersistentDataType.INTEGER) != null) {
                int i = item.getPersistentDataContainer().get(join_key, PersistentDataType.INTEGER);
                List<String> join_messages = JoinMessages.getMessages();
                String str = join_messages.get(i);
                event.setCancelled(true);
                DataUtils.setData("join", str, event.getWhoClicked().getName());
            } else if (item.getPersistentDataContainer().get(leave_key, PersistentDataType.INTEGER) != null) {
                int i = item.getPersistentDataContainer().get(leave_key, PersistentDataType.INTEGER);
                List<String> leave_messages = LeaveMessages.getMessages();
                String str = leave_messages.get(i);
                event.setCancelled(true);
                DataUtils.setData("leave", str, event.getWhoClicked().getName());
            } else if (item.getPersistentDataContainer().get(default_key, PersistentDataType.INTEGER) != null) {
                event.setCancelled(true);
                DataUtils.setData("leave", "null", event.getWhoClicked().getName());
                DataUtils.setData("join", "null", event.getWhoClicked().getName());
            }
        }
    }
}
