package com.awexer.aJoinLeaveMessages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class LeaveJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        FileConfiguration data = AJoinLeaveMessages.getInstance().getData();
        String name = event.getPlayer().getName();
        if (data.contains(name)) {
            if (data.contains(name + ".join")) {
                if (!Objects.requireNonNull(data.getString(name + ".join")).equalsIgnoreCase("null")) {
                    event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(data.getString(name + ".join", "null").replace("%p%", name))));
                }
            } else {
                data.set(name + ".join", "null");
                try {
                    data.save(AJoinLeaveMessages.getInstance().getDataFile());
                } catch (IOException e) {
                    AJoinLeaveMessages.getInstance().getSLF4JLogger().error(e.getMessage());
                }
            }
        } else {
            data.set(name + ".join", "null");
            data.set(name + ".leave", "null");
            try {
                data.save(AJoinLeaveMessages.getInstance().getDataFile());
            } catch (IOException e) {
                AJoinLeaveMessages.getInstance().getSLF4JLogger().error(e.getMessage());
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        FileConfiguration data = AJoinLeaveMessages.getInstance().getData();
        String name = event.getPlayer().getName();
        if (data.contains(name)) {
            if (data.contains(name + ".leave")) {
                if (!Objects.requireNonNull(data.getString(name + ".leave")).equalsIgnoreCase("null")) {
                    event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(data.getString(name + ".leave", "null").replace("%p%", name))));
                }
            } else {
                data.set(name + ".leave", "null");
                try {
                    data.save(AJoinLeaveMessages.getInstance().getDataFile());
                } catch (IOException e) {
                    AJoinLeaveMessages.getInstance().getSLF4JLogger().error(e.getMessage());
                }
            }
        } else {
            data.set(name + ".join", "null");
            data.set(name + ".leave", "null");
            try {
                data.save(AJoinLeaveMessages.getInstance().getDataFile());
            } catch (IOException e) {
                AJoinLeaveMessages.getInstance().getSLF4JLogger().error(e.getMessage());
            }
        }
    }

}
