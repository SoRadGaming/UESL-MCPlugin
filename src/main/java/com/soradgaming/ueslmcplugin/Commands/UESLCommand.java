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
            sender.sendMessage(ChatUtil.translateColorCode("#1782FE") + "/uesl reload" + ChatColor.BLUE + "  To reload the config");
            sender.sendMessage(ChatColor.BLUE + "Plugin made by: " + ChatUtil.translateColorCode("#1782FE") + "SoRadGaming");
            sender.sendMessage(ChatColor.BLUE + "---------------------------------------------------");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.isOp()) {
                plugin.saveFile();
                sender.sendMessage("[UESL-MCPlugin] Data Reloaded");
                plugin.getLogger().info("Data Reloaded");
                plugin.reloadConfig();
                plugin.loadFile();
                sender.sendMessage("[UESL-MCPlugin] Config Reloaded");
                plugin.getLogger().info("Config Reloaded");
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

                sender.sendMessage(Objects.requireNonNull(plugin.data.getString(player.getUniqueId().toString())));
                sender.sendMessage(Objects.requireNonNull(plugin.data.getString("planetparkour_completed" + Objects.requireNonNull(player).getUniqueId().toString() + ".planetparkour_completed")));
                sender.sendMessage(Objects.requireNonNull(plugin.data.getString("parkourparadise_completed" + Objects.requireNonNull(player).getUniqueId().toString() + ".parkourparadise_completed")));

            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
                return true;
            }
        } else if (args.length == 5 && args[0].equalsIgnoreCase("data")) {
            if (sender.isOp()) {
                if (args[1].equals("edit")) {
                    Player player = Bukkit.getServer().getPlayer(args[2]);
                    boolean TorF = Boolean.parseBoolean(args[4]);

                    if (player == null) {
                        sender.sendMessage(ChatColor.RED + "Player can not be null!");
                        return true;
                    }

                    if (args[3].equals("planetparkour_completed") || args[3].equals("parkourparadise_completed")) {
                        plugin.data.set(player.getUniqueId().toString() + "." + args[3], TorF);
                        plugin.saveFile();
                    } else sender.sendMessage(ChatColor.RED + "Invalid Data Set");
                }
            } else sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
        }
        return false;
    }
}
