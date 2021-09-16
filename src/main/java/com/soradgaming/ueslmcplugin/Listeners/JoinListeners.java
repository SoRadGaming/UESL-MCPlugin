package com.soradgaming.ueslmcplugin.Listeners;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

import static com.soradgaming.ueslmcplugin.Chat.Chat.*;

public class JoinListeners implements Listener {

    private final UESLMCPlugin plugin;

    public JoinListeners() {
        plugin = UESLMCPlugin.plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int playerCount =  Bukkit.getServer().getOnlinePlayers().size();
        List<String> parkour = plugin.getConfig().getStringList("Parkour");

        //Chat Checker
        if (playerCount <= plugin.getConfig().getInt("Chat_Change_Value")) {
            changeChannel(player, "global");
        } else {
            if (plugin.getConfig().getBoolean("Enable_Join_Channel")) {
                changeChannel(player, plugin.getConfig().getString("Join_Channel"));
            } else {
                ChatChangerSilent();
            }
        }

        //Data Handle
        if (plugin.data.contains(player.getUniqueId().toString())) {
            for (String name : parkour) {
                String data_name = plugin.parkour.getString(name + ".data_name");

                if (!(plugin.data.contains(player.getUniqueId().toString() + "." + data_name))) {
                    plugin.data.set(player.getUniqueId().toString() + "." + data_name, false);
                    plugin.saveFile();
                }
            }
        } else {
            plugin.getLogger().info("Adding " + player.getName() + " to Data Base");
            for (String name : parkour) {
                String data_name = plugin.parkour.getString(name + ".data_name");
                plugin.data.set(player.getUniqueId().toString() + "." + data_name, false);
            }
            plugin.saveFile();
        }
    }
}
