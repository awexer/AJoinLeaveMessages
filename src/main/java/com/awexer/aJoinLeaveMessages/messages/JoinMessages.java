package com.awexer.aJoinLeaveMessages.messages;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class JoinMessages {

    private static final List<String> messages = new ArrayList<>();

    public void createMessages(FileConfiguration config) {
        messages.clear();
        String prefix = config.getString("messages.join_prefix", "&c>");
        for (String str : config.getStringList("messages.join")) {
            String replaced = str.replace("%prefix%", prefix);
            messages.add(replaced);
        }
    }

    public static List<String> getMessages() {
        return messages;
    }
}
