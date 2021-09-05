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


import java.io.FileNotFoundException;
import java.util.List;


public class Chat implements Listener {
    private static List<String> regionChannels;
    private static List<String> worldChannels;
    private static int rcn;
    private static int wcn;
    private final UESLMCPlugin plugin;

    public Chat() {
        plugin = UESLMCPlugin.plugin;
    }

    public int playerCount;
    public static UltraChatAPI chat = new UltraChatAPI();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws FileNotFoundException {
        playerCount = Bukkit.getServer().getOnlinePlayers().size();
        ChatChanger();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) throws FileNotFoundException {
        playerCount = Bukkit.getServer().getOnlinePlayers().size();
        ChatChanger();
        if (playerCount == plugin.getConfig().getInt("Chat_Change_Value") - 1) {
            Bukkit.broadcastMessage("Server Chat Changing to Global");
        }
    }

    public static void changeChannel(Player p, String channel) {
        ChatChannel c = chat.getChannelManager().getChannelByName(channel);
        chat.getChannelManager().setPlayerChannel(p, c);
    }


    //Grab the Data of a Selected Channel to see its properties (Placeholder/ Internal Use)
    public static void getChannelData() {
        final UESLMCPlugin plugin;
        plugin = UESLMCPlugin.plugin;
        List<String> channels = plugin.getConfig().getStringList("Channels");
        int channelTotal = channels.size();

        for (int i = 0; i <= channelTotal; i++) {
            String name = channels.get(i);
            boolean region_chat = plugin.channel.getBoolean(name + ".region_chat");
            boolean world_chat = plugin.channel.getBoolean(name + ".world_chat");
            if (region_chat) {
                rcn++;
                regionChannels.add(name);
            } else if (world_chat) {
                wcn++;
                worldChannels.add(name);
            } else {
                plugin.getLogger().info("Channel File Has Wrong Data");
            }
        }

        if (channelTotal == rcn + wcn) {
            plugin.getLogger().info("Channel File Has Initialised Correctly");
        } else {
            plugin.getLogger().info("Channel File Has Wrong Data, Region and World Channel Mismatch");
        }
    }

    //Handler for World Channels
    @EventHandler(priority = EventPriority.MONITOR)
    public void worldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldTo = player.getWorld().getName();
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();

        if (plugin.getConfig().getBoolean("Chat_Changer")) {
            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                changeChannel(player, "global");
            } else {
                for (int i = 0; wcn <= i; i++) {
                    String name = worldChannels.toString();
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

        if (plugin.getConfig().getBoolean("Chat_Changer")) {
            if (playerCount < plugin.getConfig().getInt("Chat_Change_Value")) {
                changeChannel(player, "global");
            } else {
                for (int i = 0; rcn <= i; i++) {
                    String name = regionChannels.toString();
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
    public static void ChatChanger() throws FileNotFoundException {
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
                                    String name = regionChannels.toString();
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
                                String name = worldChannels.toString();
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
