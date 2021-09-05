package com.soradgaming.ueslmcplugin.Chat;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import me.ryandw11.ultrachat.api.UltraChatAPI;
import me.ryandw11.ultrachat.api.channels.ChatChannel;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Chat implements WebSocket.Listener, Listener {
    private static UESLMCPlugin plugin;
    public int playerCount;
    public static UltraChatAPI chat = new UltraChatAPI();

    public Chat() {
        plugin = UESLMCPlugin.plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        playerCount = Bukkit.getServer().getOnlinePlayers().size();
        ChatChanger();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        playerCount = Bukkit.getServer().getOnlinePlayers().size();
        ChatChanger();
        if (playerCount == plugin.getConfig().getInt("Chat_Change_Value") - 1) {
            Bukkit.broadcastMessage("Server Chat Changing to Global");
        }
    }

    /*
    //Change All Players
    public static void ChatChanger() {
        UESLMCPlugin plugin;
        {
            plugin = UESLMCPlugin.plugin;
        }
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();

        if (plugin.getConfig().getBoolean("Chat_Changer")) {
            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    changeChannel(onlinePlayers, "global");
                }
            } else if (playerCount >= plugin.getConfig().getInt("Chat_Change_Value") && playerCount - 1 < plugin.getConfig().getInt("Chat_Change_Value")) {
                Bukkit.broadcastMessage("Server Chat Changing to Bungee");
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    String world = onlinePlayers.getWorld().getName();

                    Location loc = BukkitAdapter.adapt(onlinePlayers.getLocation());
                    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                    RegionQuery query = container.createQuery();
                    ApplicableRegionSet set = query.getApplicableRegions(loc);

                    for (ProtectedRegion region : set) {
                        String regionName = region.getId();
                        //Region Channel
                        if (plugin.getConfig().getBoolean("Chat_Changer")) {
                            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                                if (!regionName.equals("castle-defenders") && !regionName.equals("paintball")) {
                                    changeChannel(onlinePlayers, "global");
                                } else {
                                    switch (regionName) {
                                        case "castle-defenders":
                                        case "paintball":
                                            changeChannel(onlinePlayers, regionName);
                                            break;
                                    }
                                }
                            } else {
                                //Channel Event
                                switch (regionName) {
                                    case "hub":
                                    case "minigames":
                                    case "shub":
                                    case "hglobby":
                                    case "duels_arena":
                                    case "spleef":
                                    case "build_battle":
                                    case "castle-defenders":
                                    case "tntrun":
                                    case "paintball":
                                        changeChannel(onlinePlayers, regionName);
                                        break;
                                }
                            }
                        }
                    }

                    //World Channel
                    if (plugin.getConfig().getBoolean("Chat_Changer")) {
                        if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                            changeChannel(onlinePlayers, "global");
                        } else {
                            if (world.startsWith("factions")) {
                                changeChannel(onlinePlayers, "factions");
                            } else if (world.equals("Creative")) {
                                changeChannel(onlinePlayers, "creative");
                            } else if (world.startsWith("survival")) {
                                changeChannel(onlinePlayers, "survival");
                            } else if (world.equals("Breeze2")) {
                                changeChannel(onlinePlayers, "Breeze2");
                            } else if ((world.startsWith("IridiumSkyblock"))) {
                                changeChannel(onlinePlayers, "IridiumSkyblock");
                            } else if (world.equals("PlanetParkour")) {
                                changeChannel(onlinePlayers, "PlanetParkour");
                            } else if (world.equals("ParkourParadise")) {
                                changeChannel(onlinePlayers, "ParkourParadise");
                            }
                        }
                    }
                }
            }
        }
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();

        if (plugin.getConfig().getBoolean("Chat_Changer")) {
            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                if (!regionName.equals("castle-defenders") && !regionName.equals("paintball")) {
                    changeChannel(player, "global");
                } else {
                    switch (regionName) {
                        case "castle-defenders":
                        case "paintball":
                            changeChannel(player, regionName);
                            break;
                    }
                }
            } else {
                //Channel Event
                switch (regionName) {
                    case "hub":
                    case "minigames":
                    case "shub":
                    case "hglobby":
                    case "duels_arena":
                    case "spleef":
                    case "build_battle":
                    case "castle-defenders":
                    case "tntrun":
                    case "paintball":
                        changeChannel(player, regionName);
                        break;
                }
            }
        }
    }
    */

    public static void changeChannel(Player p, String channel) {
        ChatChannel c = chat.getChannelManager().getChannelByName(channel);
        chat.getChannelManager().setPlayerChannel(p, c);
    }

    //NEW CODE

    //Run on Start to Initialise Channel Data List
    static ArrayList<String> worldChannels = (ArrayList<String>) plugin.getConfig().get("World_Channels");
    static ArrayList<String> regionChannels = (ArrayList<String>) plugin.getConfig().get("Region_Channels");

    //Counts amount of World Channels in the Config (INT)
    public static int worldChannelsNumber() {
        int c = 0;
        Scanner sc = new Scanner((Readable) worldChannels);
        while(sc.hasNext())
        {
            sc.next();
            c++;
        }
        return c;
    }

    //Counts amount of Regional Channels in the Config (INT)
    public static int regionChannelsNumber() {
        int c = 0;
        Scanner sc = new Scanner((Readable) regionChannels);
        while(sc.hasNext())
        {
            sc.next();
            c++;
        }
        return c;
    }

    //Grab the Data of a Selected Channel to see its properties (Placeholder/ Internal Use)
    public void getChannelData(String name) {
        String channel_name = plugin.channel.getString(name + ".channel_name");
        boolean region_chat = plugin.channel.getBoolean(name + ".region_chat");
        List<String> region_name = plugin.channel.getStringList(name + ".region_name");
        boolean world_chat = plugin.channel.getBoolean(name + ".world_chat");
        List<String> world_name = plugin.channel.getStringList(name + ".world_name");
    }

    //Handler for World Channels
    @EventHandler(priority = EventPriority.MONITOR)
    public void worldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldTo = player.getWorld().getName();
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();
        int wcn = worldChannelsNumber();

        if (plugin.getConfig().getBoolean("Chat_Changer")) {
            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                changeChannel(player, "global");
            } else {
                for (int i = 0; wcn <= i; i++) {
                    String name = worldChannels.get(i);
                    int worldNumber = plugin.channel.getStringList(name + ".world_name").size();
                    String channel_name = plugin.channel.getString(name + ".channel_name");
                    for (int w = 0; worldNumber <= w; w++) {
                        String world_name = plugin.channel.getStringList(name + ".world_name").get(w);
                        if (worldTo.equals(world_name)) {
                            changeChannel(player, channel_name);
                            return;
                        }
                    }
                }
            }
        }
    }

    //Handler for Region Channels
    @EventHandler(priority = EventPriority.MONITOR)
    public void regionChange(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionName = event.getRegionName();
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();
        int rcn = regionChannelsNumber();

        if (plugin.getConfig().getBoolean("Chat_Changer")) {
            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                changeChannel(player, "global");
            } else {
                for (int i = 0; rcn <= i; i++) {
                    String name = regionChannels.get(i);
                    int regionNumber = plugin.channel.getStringList(name + ".region_name").size();
                    String channel_name = plugin.channel.getString(name + ".channel_name");
                    for (int w = 0; regionNumber <= w; w++) {
                        String region_name = plugin.channel.getStringList(name + ".region_name").get(w);
                        if (regionName.equals(region_name)) {
                            changeChannel(player, channel_name);
                            return;
                        }
                    }
                }
            }
        }
    }

    //Change All Players
    public static void ChatChanger() {
        UESLMCPlugin plugin;
        {
            plugin = UESLMCPlugin.plugin;
        }
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();
        int wcn = worldChannelsNumber();
        int rcn = regionChannelsNumber();

        if (plugin.getConfig().getBoolean("Chat_Changer")) {
            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    changeChannel(onlinePlayers, "global");
                }
            } else if (playerCount >= plugin.getConfig().getInt("Chat_Change_Value") && playerCount - 1 < plugin.getConfig().getInt("Chat_Change_Value")) {
                Bukkit.broadcastMessage("Server Chat Changing to Bungee");
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    Player player = onlinePlayers.getPlayer();
                    String worldTo = onlinePlayers.getWorld().getName();

                    Location loc = BukkitAdapter.adapt(onlinePlayers.getLocation());
                    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                    RegionQuery query = container.createQuery();
                    ApplicableRegionSet set = query.getApplicableRegions(loc);

                    for (ProtectedRegion region : set) {
                        String regionName = region.getId();
                        //Region Channel
                        if (plugin.getConfig().getBoolean("Chat_Changer")) {
                            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                                changeChannel(player, "global");
                            } else {
                                for (int i = 0; rcn <= i; i++) {
                                    String name = regionChannels.get(i);
                                    int regionNumber = plugin.channel.getStringList(name + ".region_name").size();
                                    String channel_name = plugin.channel.getString(name + ".channel_name");
                                    for (int w = 0; regionNumber <= w; w++) {
                                        String region_name = plugin.channel.getStringList(name + ".region_name").get(w);
                                        if (regionName.equals(region_name)) {
                                            changeChannel(player, channel_name);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //World Channel
                    if (plugin.getConfig().getBoolean("Chat_Changer")) {
                        if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                            changeChannel(player, "global");
                        } else {
                            for (int i = 0; wcn <= i; i++) {
                                String name = worldChannels.get(i);
                                int worldNumber = plugin.channel.getStringList(name + ".world_name").size();
                                String channel_name = plugin.channel.getString(name + ".channel_name");
                                for (int w = 0; worldNumber <= w; w++) {
                                    String world_name = plugin.channel.getStringList(name + ".world_name").get(w);
                                    if (worldTo.equals(world_name)) {
                                        changeChannel(player, channel_name);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
