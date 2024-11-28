package com.awexer.aJoinLeaveMessages.commands;

import com.awexer.aJoinLeaveMessages.AJoinLeaveMessages;
import com.awexer.aJoinLeaveMessages.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHandler implements CommandExecutor {

    private FileConfiguration config;

    public CommandHandler(FileConfiguration config) {
        this.config = config;
    }

    public void reload(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("ajoinleavemessages.command")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    AJoinLeaveMessages.getInstance().reloadAll();
                    MessageUtils.message(config.getString("messages_for_commands.reloaded", "&aSuccessfully reloaded config!"), sender);
                } else {
                    MessageUtils.message(config.getString("messages_for_commands.bad-args", "&6Invalid usage! try /ajoinleavemessages!"), sender);
                }
            } else {
                if (sender instanceof Player senderPlayer) {
                    senderPlayer.openInventory(AJoinLeaveMessages.getInstance().getInitGui().getGui());
                }
            }
        } else {
            MessageUtils.message(config.getString("messages_for_commands.no-perm", "&cYou can't do it!"), sender);
        }
        return false;
    }
}
