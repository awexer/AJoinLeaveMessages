package com.awexer.aJoinLeaveMessages.messages;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class LeaveMessages {

    private static final List<String> messages = new ArrayList<>();

    public void createMessages(FileConfiguration config) {
        messages.clear();
        String prefix = config.getString("messages.leave_prefix", "&c>");
        for (String str : config.getStringList("messages.leave")) {
            String replaced = str.replace("%prefix%", prefix);
            messages.add(replaced);
        }
    }

    public static List<String> getMessages() {
        return messages;
    }
}
