package com.awexer.aJoinLeaveMessages;

import com.awexer.aJoinLeaveMessages.commands.CommandHandler;
import com.awexer.aJoinLeaveMessages.gui.GuiListener;
import com.awexer.aJoinLeaveMessages.gui.InitializeGui;
import com.awexer.aJoinLeaveMessages.messages.JoinMessages;
import com.awexer.aJoinLeaveMessages.messages.LeaveMessages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class AJoinLeaveMessages extends JavaPlugin {
    private JoinMessages joinMessages;
    private LeaveMessages leaveMessages;
    private CommandHandler handler;
    private InitializeGui initGui;
    private static AJoinLeaveMessages instance;
    private FileConfiguration configuration;
    private FileConfiguration data;
    private File dataFile;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        configuration = getConfig();
        initGui = new InitializeGui();
        dataFile = new File(getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            saveResource("data.yml", false);
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
        handler = new CommandHandler(configuration);
        Objects.requireNonNull(getCommand("ajoinleavemessages")).setExecutor(handler);
        Bukkit.getPluginManager().registerEvents(new LeaveJoinListener(), instance);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), instance);
        joinMessages = new JoinMessages();
        leaveMessages = new LeaveMessages();
        joinMessages.createMessages(configuration);
        leaveMessages.createMessages(configuration);
        initGui.createGui(configuration);
    }

    public File getDataFile() {
        return dataFile;
    }

    public static AJoinLeaveMessages getInstance() {
        return instance;
    }

    public void reloadAll() {
        reloadConfig();
        configuration = getConfig();
        dataFile = new File(getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            saveResource("data.yml", false);
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
        joinMessages.createMessages(configuration);
        leaveMessages.createMessages(configuration);
        handler.reload(configuration);
        initGui.clearGui();
    }

    public FileConfiguration getData() {
        return data;
    }

    public InitializeGui getInitGui() {
        return initGui;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
