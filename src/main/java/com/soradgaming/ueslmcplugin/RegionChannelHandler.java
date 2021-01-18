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
        assert player != null;
        String name = player.getName();
        String regionName = event.getRegionName();

        changeChannel(name, regionName);
        player.sendMessage("Test Message Region");
    }

    public void changeChannel(String name, String channel) {
        Player player = Bukkit.getServer().getPlayer(name);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " permission set ultrachat.channel true");
        assert player != null;
        player.performCommand("channel " + channel);
        player.performCommand("msg " + name + "test");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " permission set ultrachat.channel false");
    }
}