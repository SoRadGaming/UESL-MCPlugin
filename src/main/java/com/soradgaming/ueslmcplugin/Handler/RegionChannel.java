package com.soradgaming.ueslmcplugin.Handler;

import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.lang.String;

public class RegionChannel implements Listener {
    public static UltraChatAPI chat = new UltraChatAPI();

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();

        //Channel Event
        if (regionName.equals("hub")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("minigames")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("shub")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("hglobby")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("duels_arena")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("spleef")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("build_battle")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("tntrun")) {
            changeChannel(player, regionName);
        } else if (regionName.equals("paintball")) {
            changeChannel(player, regionName);
        }

        //Custom Events

        //Skyblock_Portal
        if (regionName.equals("skyblock_portal_1") || regionName.equals("skyblock_portal_2") || regionName.equals("skyblock_portal_3") || regionName.equals("skyblock_portal_4") || regionName.equals("skyblock_portal_5") || regionName.equals("skyblock_portal_6")) {
            player.performCommand("is home");
            player.sendMessage("Welcome to Skyblocks!");
        }
        //Skyblock_Death_Loop_Fix
        if (regionName.equals("skyblock_death_box")) {
            player.performCommand("is home");
        }
        //Dues Gamemode
        if (regionName.equals("duels_arena")) {
            player.setGameMode(GameMode.ADVENTURE);
        }
        //SecretHub Parkour Finish
        if (regionName.equals("shub_portal")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player + " permission set essentials.warps.secrethub true");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title" + player + " title {\"text\":\"Congratulations\", \"bold\":true, \"italic\":false, \"color\":\"blue\"}");
            player.sendMessage("Use /shub to get here in the future");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp secrethub " + player);
        }
        //ParkourParadise Portal
        if (regionName.equals("ParkourParadise_Portal")) {
            player.performCommand("pa join parkourparadise");
        }
    }

    public static void changeChannel(Player p, String channel) {
        ChatChannel c = chat.getChannelManager().getChannelByName(channel);
        chat.getChannelManager().setPlayerChannel(p, c);
    }
}