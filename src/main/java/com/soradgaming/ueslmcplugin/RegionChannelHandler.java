package com.soradgaming.ueslmcplugin;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.String;

public class RegionChannelHandler extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(this, this);
    }

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) throws InterruptedException {
        final Player player = event.getPlayer();
        final String name = player.getName();
        final String regionName = event.getRegionName();

        changeChannel(name, regionName);
        player.sendMessage("Test Message");
    }

    public void changeChannel(String player, String channel) throws InterruptedException {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel true");
        wait(1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "channel " + channel);
        wait(1);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set ultrachat.channel false");
    }
}