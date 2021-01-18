package com.soradgaming.ueslmcplugin;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.lang.String;

public class RegionChannelHandler implements Listener {

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) throws InterruptedException {
        final Player player = event.getPlayer();
        final String name = player.getName();
        final String regionName = event.getRegionName();

        changeChannel(name, regionName);
        player.sendMessage("Test Message");
    }

    private void changeChannel(String player, String channel) throws InterruptedException {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel true");
        wait(1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "channel " + channel);
        wait(1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel false");
    }
}