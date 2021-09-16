package com.soradgaming.ueslmcplugin.ConditionalEvents;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.Objects;

public class PlayerWorldChangeEvent implements Listener {

    private final UESLMCPlugin plugin;

    public PlayerWorldChangeEvent() {
        plugin = UESLMCPlugin.plugin;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldFrom = event.getFrom().getName();
        String worldTo = player.getWorld().getName();

        //Parkour Join (Database Update)
        if (worldFrom.equals("World")) {
            if (worldTo.equals("ParkourParadise") && plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".parkourparadise_completed")) {
                player.sendMessage("You have already claimed the prize for this course!");
                player.sendMessage("Use /minigames to leave the course and return to minigames");
            } else if (worldTo.equals("PlanetParkour") && plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".planetparkour_completed")) {
                player.sendMessage("You have already claimed the prize for this course!");
                player.sendMessage("Use /minigames to leave the course and return to minigames");
            } else if (worldTo.equals("PlanetParkour") || worldTo.equals("ParkourParadise")) {
                player.sendMessage("Use /minigames to leave the course and return to minigames");
            }
        }
    }
}
