package com.soradgaming.ueslmcplugin.Commands;

import com.soradgaming.ueslmcplugin.Builder.ChannelBuilder;
import com.soradgaming.ueslmcplugin.Chat.Chat;
import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import me.ryandw11.ultrachat.api.UltraChatAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.Objects;

import static com.soradgaming.ueslmcplugin.ConditionalEvents.WorldGuardEvent.PlayerRegion;

public class UESLCommand implements CommandExecutor {

    private final UESLMCPlugin plugin;

    public UESLCommand() {
        plugin = UESLMCPlugin.plugin;
    }
    public static UltraChatAPI chat = new UltraChatAPI();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "=============={" + ChatColor.GREEN + "UESL-MCPlugin" + ChatColor.BLUE + "}==============");
            sender.sendMessage(ChatColor.BLUE + "Plugin developed by:" + ChatColor.GREEN + " SoRadGaming");
            sender.sendMessage(ChatColor.BLUE + "Version: " + ChatColor.GREEN + String.format("%s", plugin.getDescription().getVersion()));
            sender.sendMessage(ChatColor.BLUE + "Plugin:" + ChatColor.GREEN + " https://github.com/SoRadGaming/UESL-MCPlugin");
            sender.sendMessage(ChatColor.BLUE + "Do " + ChatColor.GREEN + "/uesl help " + ChatColor.BLUE + "for the list of commands!");
            sender.sendMessage(ChatColor.BLUE + "=============={" + ChatColor.GREEN + "UESL-MCPlugin" + ChatColor.BLUE + "}==============");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
                return true;
            }
            sender.sendMessage(ChatColor.BLUE + "-----------------=[" + ChatColor.GREEN + "UESL-MCPlugin" + ChatColor.BLUE + "]=-----------------");
            sender.sendMessage(ChatColor.GREEN + "/uesl help" + ChatColor.BLUE + "  The help command.");
            sender.sendMessage(ChatColor.GREEN + "/uesl reload" + ChatColor.BLUE + "  To reload the plugin");
            sender.sendMessage(ChatColor.GREEN + "/uesl data player" + ChatColor.BLUE + "  To View a players data");
            sender.sendMessage(ChatColor.GREEN + "/uesl edit player dataset value" + ChatColor.BLUE + "  Modify the data in player.yml");
            sender.sendMessage(ChatColor.BLUE + "Plugin made by: " + ChatColor.GREEN + "SoRadGaming");
            sender.sendMessage(ChatColor.BLUE + "---------------------------------------------------");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.isOp()) {
                plugin.saveFile();
                plugin.saveChannel();
                plugin.reloadConfig();
                plugin.loadFile();
                plugin.loadChannel();
                Chat.getChannelData();
                try {
                    Chat.ChatChanger();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                plugin.getLogger().info("Reloaded");
                sender.sendMessage(ChatColor.GREEN + "Reloaded");
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
                return true;
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("data")) {
            if (sender.isOp()) {
                Player player = Bukkit.getServer().getPlayer(args[1]);

                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Player can not be null!");
                    return true;
                }

                boolean planetparkour_completed_boolean = plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".planetparkour_completed");
                boolean parkourparadise_completed_boolean = plugin.data.getBoolean(Objects.requireNonNull(player).getUniqueId().toString() + ".parkourparadise_completed");

                sender.sendMessage(ChatColor.BLUE + player.getName() + ChatColor.WHITE + " Data: ");
                sender.sendMessage(ChatColor.DARK_GREEN + "planetparkour_completed: " + ChatColor.YELLOW + planetparkour_completed_boolean);
                sender.sendMessage(ChatColor.DARK_GREEN + "parkourparadise_completed: " + ChatColor.YELLOW + parkourparadise_completed_boolean);

            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
                return true;
            }
        } else if (args.length == 4 && args[0].equalsIgnoreCase("edit")) {
            if (sender.isOp()) {
                    Player player = Bukkit.getServer().getPlayer(args[1]);
                    boolean TorF = Boolean.parseBoolean(args[3]);

                    if (player == null) {
                        sender.sendMessage(ChatColor.RED + "Player can not be null!");
                        return true;
                    }

                    if (args[2].equals("planetparkour_completed") || args[2].equals("parkourparadise_completed")) {
                        plugin.data.set(player.getUniqueId().toString() + "." + args[3], TorF);
                        plugin.saveFile();
                        player.sendMessage("Set " + ChatColor.BLUE + player.getName() + " " + ChatColor.DARK_GREEN + args[2] + " " + ChatColor.YELLOW + TorF);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Invalid Data Set");
                        return true;
                    }

            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
                return true;
            }
        }
        return false;
    }
}
