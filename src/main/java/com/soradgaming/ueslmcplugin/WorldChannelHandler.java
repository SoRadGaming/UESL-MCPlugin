package com.soradgaming.ueslmcplugin;

import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.nio.channels.Channel;

public class WorldChannelHandler implements Listener {
    public static UltraChatAPI chat = new UltraChatAPI();

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String worldFrom = event.getFrom().getName();
        String worldTo = player.getWorld().getName();

        if (worldTo.startsWith("factions") && !worldFrom.startsWith("factions")) {
            changeChannel(name, "factions");
        } else if (worldTo.equals("Creative")) {
            changeChannel(name, "creative");
        } else if (worldTo.startsWith("survival") && !worldFrom.startsWith("survival")) {
            changeChannel(name, "survival");
        } else if (worldTo.equals("Breeze2")) {
            changeChannel(name, "Breeze2");
        } else if (worldTo.startsWith("IridiumSkyblock") && !worldFrom.startsWith("IridiumSkyblock")) {
            changeChannel(name, "IridiumSkyblock");
        } else if (worldTo.equals("PlanetParkour")) {
            changeChannel(name, "PlanetParkour");
        } else if (worldTo.equals("ParkourParadise")) {
            changeChannel(name, "ParkourParadise");
        } else {
            return;
        }
    }
    /*
    public static void changeChannel(String name, String channel) {
        Player player = Bukkit.getServer().getPlayer(name);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " permission set ultrachat.channel true");
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert player != null;
        player.performCommand("channel " + channel);
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " permission set ultrachat.channel false");
    }
    */

    public static void changeChannel(String name, String channel) {
        Player player = Bukkit.getServer().getPlayer(name);
        Channel c = (Channel) chat.getChannelManager().getChannelByName(channel);
        chat.getChannelManager().setPlayerChannel(player, (ChatChannel) c);
    }

}
