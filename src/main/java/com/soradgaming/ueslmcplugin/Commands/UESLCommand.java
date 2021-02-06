package com.soradgaming.ueslmcplugin.Commands;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import me.ryandw11.ultrachat.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

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
            sender.sendMessage(ChatColor.BLUE + "Do " + ChatUtil.translateColorCode("#1782FE") + " /uesl help " + ChatColor.BLUE + "for the list of commands!");
            sender.sendMessage(ChatColor.BLUE + "=============={" + ChatUtil.translateColorCode("#1782FE") + "UESL-MCPlugin" + ChatColor.BLUE + "}==============");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            if (!sender.isOp()) {
                sender.sendMessage("§4[ERROR] You don't have permission to do that");
                return true;
            }
            sender.sendMessage(ChatColor.BLUE + "-----------------=[" + ChatUtil.translateColorCode("#1782FE") + "UESL-MCPlugin" + ChatColor.BLUE + "]=-----------------");
            sender.sendMessage(ChatUtil.translateColorCode("#1782FE") + "/uesl help" + ChatColor.BLUE + "  The help command.");
            sender.sendMessage(ChatUtil.translateColorCode("#1782FE") + "/uesl reload" + ChatColor.BLUE + "  To reload the config");
            sender.sendMessage(ChatColor.BLUE + "Plugin made by: " + ChatUtil.translateColorCode("#1782FE") + "SoRadGaming");
            sender.sendMessage(ChatColor.BLUE + "---------------------------------------------------");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.isOp()) {
                sender.sendMessage("§4[ERROR] Undefined Variable");
            } else {
                sender.sendMessage("§4[ERROR] You don't have permission to do that");
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("reload")) {
            if (args[1].equals("data")) {
                if (sender.isOp()) {
                    plugin.saveFile();
                    plugin.loadFile();
                    sender.sendMessage("[UESL-MCPlugin] Data Reloaded");
                    plugin.getLogger().info("[UESL-MCPlugin] Data Reloaded");
                } else {
                    sender.sendMessage("§4[ERROR] You don't have permission to do that");
                }
            }  else if (args[1].equals("config")) {
                if (sender.isOp()) {
                    plugin.reloadConfig();
                    plugin.loadFile();
                    sender.sendMessage("[UESL-MCPlugin] Config Reloaded");
                    plugin.getLogger().info("[UESL-MCPlugin] Config Reloaded");
                } else {
                    sender.sendMessage("§4[ERROR] You don't have permission to do that");
                }
            }
        }
        return false;
    }
}
