package com.soradgaming.ueslmcplugin.Listeners;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import me.ryandw11.ultrachat.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.soradgaming.ueslmcplugin.Chat.Chat.*;

public class JoinListeners implements Listener {

    private final UESLMCPlugin plugin;

    public JoinListeners() {
        plugin = UESLMCPlugin.plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        //World lobbyy = Bukkit.getServer().getWorld("Lobbyy");
        //Location location = new Location(lobbyy, 1.5, 57.0, 0.5, 90, 0);
        Player player = event.getPlayer();
        int playerCount =  Bukkit.getServer().getOnlinePlayers().size();

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
            //player.teleport(location);
            player.sendTitle(ChatColor.YELLOW + "" + "Welcome",ChatUtil.translateColorCode("#1782FE") + " to UESL World", 20, 60, 20);
        } else {
            //player.teleport(location);
            player.sendTitle(ChatColor.YELLOW + "" + "Welcome Back",ChatUtil.translateColorCode("#1782FE") + player.getName() + " to UESL World", 20, 60, 20);
        }

        if (playerCount <= plugin.getConfig().getInt("Chat_Change_Value")) {
                changeChannel(player, "global");
        } else {
            if (plugin.getConfig().getBoolean("Enable_Join_Channel")) {
                changeChannel(player, plugin.getConfig().getString("Join_Channel"));
            } else {
                ChatChangerSilent();
            }
        }
    }
}
