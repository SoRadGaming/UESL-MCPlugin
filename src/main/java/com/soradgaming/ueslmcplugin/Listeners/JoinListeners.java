package com.soradgaming.ueslmcplugin.Listeners;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import me.ryandw11.ultrachat.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListeners implements Listener {

    private final UESLMCPlugin plugin;

    public JoinListeners() {
        plugin = UESLMCPlugin.plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //Data Handle
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

        //On Join Welcome Message and TP
        if (!player.hasPlayedBefore()) {
            player.performCommand("hub");
            player.sendTitle(ChatColor.YELLOW + "" + "Welcome",ChatUtil.translateColorCode("#1782FE") + " to UESL World", 20, 60, 20);
        } else {
            player.performCommand("hub");
            player.sendTitle(ChatColor.YELLOW + "" + "Welcome Back",ChatUtil.translateColorCode("#1782FE") + player.getName() + " to UESL World", 20, 60, 20);
        }
    }
}
