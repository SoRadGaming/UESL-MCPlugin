package com.soradgaming.ueslmcplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldChannelHandler extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(this, this);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) throws InterruptedException {
        final Player player = event.getPlayer();
        final String name = player.getDisplayName();
        final String world = player.getWorld().toString();
        final String from = event.getFrom().toString();
        player.sendMessage("Test Message");

        if (world.startsWith("factions") && !from.startsWith("factions")) {
            changeChannel(name, "factions");
        }
        else if (world.equals("Creative")) {
            changeChannel(name, "creative");
        }
        else if (world.startsWith("survival") && !from.startsWith("survival")) {
            changeChannel(name, "survival");
        }
        else if (world.equals("Breeze2")) {
            changeChannel(name, "Breeze2");
        }
        else if (world.startsWith("IridiumSkyblock") && !from.startsWith("IridiumSkyblock")) {
            changeChannel(name, "IridiumSkyblock");
        }
        else if (world.equals("PlanetParkour")) {
            changeChannel(name, "PlanetParkour");
        }
        else if (world.equals("ParkourParadise")) {
            changeChannel(name, "ParkourParadise");
        }
    }

    public void changeChannel(String player, String channel) throws InterruptedException {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel true");
        wait(1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "channel " + channel);
        wait(1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel false");
    }
}
