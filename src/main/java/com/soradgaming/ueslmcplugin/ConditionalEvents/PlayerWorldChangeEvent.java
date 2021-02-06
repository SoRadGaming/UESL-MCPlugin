package com.soradgaming.ueslmcplugin.ConditionalEvents;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.Objects;

public class PlayerWorldChangeEvent implements Listener {

    private UESLMCPlugin plugin;

    public PlayerWorldChangeEvent() {
        plugin = UESLMCPlugin.plugin;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldFrom = event.getFrom().getName();
        String worldTo = player.getWorld().getName();

        //Half Heart PlanetParkour Fix
        if (worldFrom.equals("PlanetParkour")) {
                player.setHealth(20);
                player.addPotionEffects(Collections.singleton(new PotionEffect(PotionEffectType.REGENERATION, 1, 20)));
        }

        //Parkour Join
        if (worldFrom.equals("World")) {
            if (worldTo.equals("ParkourParadise") && plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".parkourparadise_completed")) {
                    player.sendMessage("You have already claimed the prize for this course!");
            } else if (worldTo.equals("PlanetParkour") && plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".planetparkour_completed")) {
                    player.sendMessage("You have already claimed the prize for this course!");
            }
            player.sendMessage("Use /minigames to leave the course and return to minigames");
        }
    }
}
