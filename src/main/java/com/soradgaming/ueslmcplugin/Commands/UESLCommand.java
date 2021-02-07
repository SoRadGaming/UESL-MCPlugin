package com.soradgaming.ueslmcplugin.Commands;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import me.ryandw11.ultrachat.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UESLCommand implements CommandExecutor {

    private UESLMCPlugin plugin;
    public UESLCommand() {
        plugin = UESLMCPlugin.plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "=============={" + ChatUtil.translateColorCode("#1782FE") + "UESL-MCPlugin" + ChatColor.BLUE + "}==============");
            sender.sendMessage(ChatColor.BLUE + "Plugin developed by:" + ChatUtil.translateColorCode("#1782FE") + " SoRadGaming");
            sender.sendMessage(ChatColor.BLUE + "Version: " + ChatUtil.translateColorCode("#1782FE") + String.format("%s", plugin.getDescription().getVersion()));
            sender.sendMessage(ChatColor.BLUE + "Plugin:" + ChatUtil.translateColorCode("#1782FE") + " https://github.com/SoRadGaming/UESL-MCPlugin");
            sender.sendMessage(ChatColor.BLUE + "Do " + ChatUtil.translateColorCode("#1782FE") + "/uesl help " + ChatColor.BLUE + "for the list of commands!");
            sender.sendMessage(ChatColor.BLUE + "=============={" + ChatUtil.translateColorCode("#1782FE") + "UESL-MCPlugin" + ChatColor.BLUE + "}==============");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
                return true;
            }
            sender.sendMessage(ChatColor.BLUE + "-----------------=[" + ChatUtil.translateColorCode("#1782FE") + "UESL-MCPlugin" + ChatColor.BLUE + "]=-----------------");
            sender.sendMessage(ChatUtil.translateColorCode("#1782FE") + "/uesl help" + ChatColor.BLUE + "  The help command.");
            sender.sendMessage(ChatUtil.translateColorCode("#1782FE") + "/uesl reload" + ChatColor.BLUE + "  To reload the plugin");
            sender.sendMessage(ChatUtil.translateColorCode("#1782FE") + "/uesl data player" + ChatColor.BLUE + "  To View a players data");
            sender.sendMessage(ChatUtil.translateColorCode("#1782FE") + "/uesl data edit player dataset value" + ChatColor.BLUE + "  Modify the data in player.yml");
            sender.sendMessage(ChatColor.BLUE + "Plugin made by: " + ChatUtil.translateColorCode("#1782FE") + "SoRadGaming");
            sender.sendMessage(ChatColor.BLUE + "---------------------------------------------------");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.isOp()) {
                plugin.saveFile();
                plugin.reloadConfig();
                plugin.loadFile();
                plugin.getLogger().info("Reloaded");
                sender.sendMessage("[UESL-MCPlugin] Data Reloaded");
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

                sender.sendMessage(ChatColor.BLUE + player.getName() + " Data " + ChatColor.DARK_GREEN + "planetparkour_completed:" + ChatColor.YELLOW + Boolean.toString(planetparkour_completed_boolean) + ";" + ChatColor.DARK_GREEN + " parkourparadise_completed:" + ChatColor.YELLOW + Boolean.toString(parkourparadise_completed_boolean) + ";");

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
                        player.sendMessage("Set " + ChatColor.BLUE + player.toString() + " " + ChatColor.DARK_GREEN + args[2] + "" + ChatColor.YELLOW + Boolean.toString(TorF));
                    } else sender.sendMessage(ChatColor.RED + "Invalid Data Set");

            } else sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
        }
        return false;
    }
}
