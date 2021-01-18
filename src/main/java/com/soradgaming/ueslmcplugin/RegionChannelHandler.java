package com.soradgaming.ueslmcplugin;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.lang.String;

public class RegionChannelHandler implements Listener {

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String regionName = event.getRegionName();

        changeChannel(name, regionName);
        player.sendMessage("Test Message Region");
    }

    public void changeChannel(String player, String channel) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel true");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sudo" + player + " /channel " + channel);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel false");
    }
}