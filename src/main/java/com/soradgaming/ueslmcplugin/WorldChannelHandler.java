package com.soradgaming.ueslmcplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChannelHandler implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) throws InterruptedException {
        final Player player = event.getPlayer();
        final String name = player.getName();
        final String worldFrom = event.getFrom().getName();
        final String worldTo = player.getWorld().getName();
        player.sendMessage("Test Message");

        if (worldTo.startsWith("factions") && !worldFrom.startsWith("factions")) {
            changeChannel(name, "factions");
        }
        else if (worldTo.equals("Creative")) {
            changeChannel(name, "creative");
        }
        else if (worldTo.startsWith("survival") && !worldFrom.startsWith("survival")) {
            changeChannel(name, "survival");
        }
        else if (worldTo.equals("Breeze2")) {
            changeChannel(name, "Breeze2");
        }
        else if (worldTo.startsWith("IridiumSkyblock") && !worldFrom.startsWith("IridiumSkyblock")) {
            changeChannel(name, "IridiumSkyblock");
        }
        else if (worldTo.equals("PlanetParkour")) {
            changeChannel(name, "PlanetParkour");
        }
        else if (worldTo.equals("ParkourParadise")) {
            changeChannel(name, "ParkourParadise");
        }
    }

    private void changeChannel(String player, String channel) throws InterruptedException {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel true");
        wait(1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "channel " + channel);
        wait(1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel false");
    }
}