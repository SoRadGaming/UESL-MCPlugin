package com.soradgaming.ueslmcplugin.Listeners;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListeners implements Listener {

    private UESLMCPlugin plugin;

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
            plugin.data.addDefault(player.getUniqueId().toString(), player.getUniqueId());
            plugin.data.set(player.getUniqueId().toString() + ".planetparkour_completed", false);
            plugin.data.set(player.getUniqueId().toString() + ".parkourparadise_completed", false);
            plugin.saveFile();
        }
    }
}
