package com.soradgaming.ueslmcplugin;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.String;

public class ChannelHandler extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        for(Player p : Bukkit.getOnlinePlayers())
        {
                p.sendMessage("Test");
        }
    }

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event)
    {
        Player player = Bukkit.getPlayer(event.getUUID());
        if (player == null) return;

        String regionName = event.getRegionName();
        player.sendMessage("Test Message");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set ultrachat.channel true");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "channel " + regionName);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set ultrachat.channel false");

    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event){

        Player player = event.getPlayer();
        World world = player.getWorld();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set ultrachat.channel true");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "channel " + world);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set ultrachat.channel false");
    }
}