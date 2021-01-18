package com.soradgaming.ueslmcplugin;

import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldChangeChannel extends JavaPlugin implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event){

        Player player = event.getPlayer();
        World world = player.getWorld();
        World from = event.getFrom();

        if(from != world){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set ultrachat.channel true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "channel " + world);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set ultrachat.channel false");
        }
    }

}