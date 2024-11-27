package com.awexer.aJoinLeaveMessages.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@SuppressWarnings("deprecation")
public class MessageUtils {
    public static void message(String str, CommandSender sender) {
        if (!str.isEmpty()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
        }
    }
}
