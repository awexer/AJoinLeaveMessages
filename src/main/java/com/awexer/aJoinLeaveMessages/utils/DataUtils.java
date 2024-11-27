package com.awexer.aJoinLeaveMessages.utils;

import com.awexer.aJoinLeaveMessages.AJoinLeaveMessages;

import java.io.IOException;

public class DataUtils {

    public static void setData(String type, String str, String player) {
        AJoinLeaveMessages.getInstance().getData().set(player + "." + type, str);
        try {
            AJoinLeaveMessages.getInstance().getData().save(AJoinLeaveMessages.getInstance().getDataFile());
        } catch (IOException e) {
            AJoinLeaveMessages.getInstance().getSLF4JLogger().error(e.getMessage());
        }
    }
}
