package com.soradgaming.ueslmcplugin.Commands;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.lang.String;

//Conditional Events

public class ConditionalEvents implements Listener {

    //Event WorldGuard Region Enter
    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();
        String p = event.getPlayer().getName();

        //Skyblock_Portal
        if (regionName.equals("skyblock_portal_1") || regionName.equals("skyblock_portal_2") || regionName.equals("skyblock_portal_3") || regionName.equals("skyblock_portal_4") || regionName.equals("skyblock_portal_5") || regionName.equals("skyblock_portal_6")) {
            player.performCommand("is home");
            player.sendMessage("Welcome to Skyblocks!");
        }
        //Skyblock_Death_Loop_Fix
        if (regionName.equals("skyblock_death_box")) {
            player.performCommand("is home"); // event not activated on respawn
        }
        //Dues Gamemode
        if (regionName.equals("duels_arena")) {
            if (player.getGameMode() != GameMode.SPECTATOR) {
                player.setGameMode(GameMode.ADVENTURE);
            }
        }
        //SecretHub Parkour Finish
        if (regionName.equals("shub_portal")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p + " permission set essentials.warps.secrethub true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title" + p + " title {\"text\":\"Congratulations\", \"bold\":true, \"italic\":false, \"color\":\"blue\"}");
            player.sendMessage("Use /shub to get here in the future");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp secrethub " + p);
        }
        //ParkourParadise Portal
        if (regionName.equals("ParkourParadise_Portal")) {
            player.performCommand("pa join parkourparadise");
        }
    }

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().toString();

        if (!(!event.isAnchorSpawn() && event.isBedSpawn()) || !(event.isAnchorSpawn() && !event.isBedSpawn())) {
            if (world.equals("IridiumSkyblock_nether") || world.equals("IridiumSkyblock")) {
                player.performCommand("is home");
            }
        }
    }
}
