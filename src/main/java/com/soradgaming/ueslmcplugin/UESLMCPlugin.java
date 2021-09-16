package com.soradgaming.ueslmcplugin;

import com.soradgaming.ueslmcplugin.Chat.Chat;
import com.soradgaming.ueslmcplugin.Commands.CommandTabCompleter;
import com.soradgaming.ueslmcplugin.Commands.UESLCommand;
import com.soradgaming.ueslmcplugin.Items.ProCosmeticsChest;
import com.soradgaming.ueslmcplugin.Listeners.JoinListeners;
import com.soradgaming.ueslmcplugin.Parkour.Parkour;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public final class UESLMCPlugin extends JavaPlugin {

    public static UESLMCPlugin plugin;
    public List<UUID> planetparkour_completed = new ArrayList<>();
    public List<UUID> parkourparadise_completed = new ArrayList<>();
    public File channelFile;
    public FileConfiguration channel;
    public File parkourFile;
    public FileConfiguration parkour;
    public File dataFile = new File(getDataFolder() + "/data/players.yml");
    public FileConfiguration data = YamlConfiguration.loadConfiguration(dataFile);

    @Override
    public void onEnable() {

        plugin = this;

        if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            getLogger().info("Hooked into WorldGuard");
        } else {
            throw new RuntimeException("Could not find WorldGuard! Plugin can not work without it!");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("UltraChat")) {
            getLogger().info("Hooked into UltraChat");
        } else {
            throw new RuntimeException("Could not find UltraChat! Plugin can not work without it!");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("WorldGuardEvents")) {
            getLogger().info("Hooked into WorldGuardEvents");
        } else {
            throw new RuntimeException("Could not find WorldGuardEvents! Plugin can not work without it!");
        }

        //Load EventHandler and Commands
        loadMethod();

        //Load Parkour
        loadParkour();

        //Load Data
        loadFile();

        //Load Channel
        loadChannel();

        //Config
        registerConfig();

        //Channel Data Creation
        com.soradgaming.ueslmcplugin.Chat.Chat.getChannelData();
    }

    @Override
    public void onDisable() {
        saveFile();
        saveChannel();
        saveParkour();
        getLogger().info("The plugin has been disabled correctly!");
    }

    //Loads all of the Events and Commands
    public void loadMethod() {
        //Registers Commands
        Objects.requireNonNull(getCommand("uesl")).setExecutor(new UESLCommand());
        Objects.requireNonNull(getCommand("uesl")).setTabCompleter(new CommandTabCompleter());
        // Registers Events
        Bukkit.getServer().getPluginManager().registerEvents(new Chat(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Parkour(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListeners(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ProCosmeticsChest(), this);
    }

    //Save the data file.
    public void saveFile() {
        try {
            data.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    //Load the data file
    public void loadFile() {
        if (dataFile.exists()) {
            try {
                data.load(dataFile);

            } catch (IOException | InvalidConfigurationException e) {

                e.printStackTrace();
            }
        } else {
            try {
                data.save(dataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Save the Channel file
    public void saveChannel() {

        try {
            channel.save(channelFile);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    //Load the Channel file
    public void loadChannel() {
        channelFile = new File(getDataFolder(), "channel.yml");
        if (!channelFile.exists()) {
            channelFile.getParentFile().mkdirs();
            saveResource("channel.yml", false);
        }

        channel = new YamlConfiguration();
        try {
            channel.load(channelFile);

        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();
        }
    }

    //Save the Channel file
    public void saveParkour() {

        try {
            parkour.save(parkourFile);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    //Load the Channel file
    public void loadParkour() {
        parkourFile = new File(getDataFolder(), "parkour.yml");
        if (!parkourFile.exists()) {
            parkourFile.getParentFile().mkdirs();
            saveResource("parkour.yml", false);
        }

        parkour = new YamlConfiguration();
        try {
            parkour.load(parkourFile);

        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();
        }
    }

    //Config
    private void registerConfig() {
        saveDefaultConfig();
    }

}