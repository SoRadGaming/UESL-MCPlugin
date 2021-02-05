package com.soradgaming.ueslmcplugin;

import com.soradgaming.ueslmcplugin.ConditionalEvents.WorldGuardEvent;
import com.soradgaming.ueslmcplugin.Chat.RegionChannel;
import com.soradgaming.ueslmcplugin.Chat.WorldChannel;
import me.ryandw11.ultrachat.UltraChat;
import me.ryandw11.ultrachat.commands.*;
import me.ryandw11.ultrachat.listner.JoinListner;
import me.ryandw11.ultrachat.listner.NoSwear;
import me.ryandw11.ultrachat.listner.Spy;
import me.ryandw11.ultrachat.listner.StopChat;
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
    public File dataFile = new File(getDataFolder() + "/data/players.yml");
    public FileConfiguration data = YamlConfiguration.loadConfiguration(dataFile);

    @Override
    public void onEnable() {

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

        //Load EventHandler
        loadMethod();

        //Load Data
        loadFile();
    }

    @Override
    public void onDisable() {
        saveFile();
    }

    //Loads all of the Events and Commands.
    public void loadMethod() {
        Bukkit.getServer().getPluginManager().registerEvents(new RegionChannel(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WorldChannel(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WorldGuardEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListner(), this);
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
}