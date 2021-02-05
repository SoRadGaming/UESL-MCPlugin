package com.soradgaming.ueslmcplugin.Listeners;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListeners implements Listener {

    private UESLMCPlugin plugin;

    public JoinListeners() {
        plugin = UESLMCPlugin.plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (plugin.data.contains(player.getUniqueId().toString())) {
            if (!(plugin.data.contains(player.getUniqueId().toString() + ".planetparkour_completed"))) {
                plugin.data.set(player.getUniqueId().toString() + ".planetparkour_completed", false);
                plugin.saveFile();
            }
            if (!(plugin.data.contains(player.getUniqueId().toString() + ".parkourparadise_completed"))) {
                plugin.data.set(player.getUniqueId().toString() + ".parkourparadise_completed", false);
                plugin.saveFile();
            }
        } else {
            plugin.getLogger().info("Adding " + player.getName() + " to Data Base");
            plugin.parkourparadise_completed.add(player.getUniqueId());
            plugin.data.set(player.getUniqueId().toString() + ".planetparkour_completed", false);
            plugin.planetparkour_completed.add(player.getUniqueId());
            plugin.data.set(player.getUniqueId().toString() + ".parkourparadise_completed", false);
            plugin.saveFile();
        }
    }
}
