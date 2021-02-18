package com.soradgaming.ueslmcplugin.Items;

import com.soradgaming.ueslmcplugin.UESLMCPlugin;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;

public class ProCosmeticsChest implements Listener {

    private final UESLMCPlugin plugin;

    public ProCosmeticsChest() {
        plugin = UESLMCPlugin.plugin;
    }

    //Chest Click Usage
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player p = event.getPlayer();
            try {
                if (p.getInventory().getItemInMainHand().getType() == Material.CHEST && Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"))) {
                    event.setCancelled(true);
                    p.performCommand("procosmetics open main");
                }
            }
            catch (Exception var3_3) {
                System.out.println("[UESL-MCPlugin] A open-chest error has been thrown. Please contact SoRadGaming if the problem persist.");
            }
        }
    }
    //Prevent Dropping the Chest
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack chest = event.getItemDrop().getItemStack();
        if (chest.isSimilar(chest) && chest.hasItemMeta() && Objects.requireNonNull(chest.getItemMeta()).getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"))) {
            event.setCancelled(true);
        }
    }
    //Give Chest on Join
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (p.getWorld().getName().equals("Lobbyy") && plugin.getConfig().getBoolean("Procosmetic_Chest")) {
            ItemStack chest = this.Chest();
            if (!p.getInventory().contains(chest)) {
                p.getInventory().setItem(8, chest);
                System.out.println("[UESL-MCPlugin] " + p.getName() + " does not have a chest. Giving it now.");
            }
        }
    }
    //Chest Region
    @EventHandler
    public void onChangeRegion(RegionEnteredEvent event) {
            Player p = event.getPlayer();
            String region = event.getRegionName();
            assert p != null;
            String world = p.getWorld().getName();
            ItemStack chest = this.Chest();
            boolean regionlogic = region.equals("minigames") || region.equals("hub") || region.equals("shub") || region.equals("hglobby");

            if (world.equals("World") || world.equals("Lobbyy") && regionlogic && plugin.getConfig().getBoolean("Procosmetic_Chest")) {
                if (!p.getInventory().contains(this.Chest()) && !p.getInventory().getItemInOffHand().isSimilar(chest)) {
                    System.out.println("[UESL-MCPlugin] " + p.getName() + " does not have a chest. Giving it now.");
                    p.getInventory().setItem(8, this.Chest());
                }
            } else {
                if (p.getInventory().contains(this.Chest()) && !p.getInventory().getItemInOffHand().isSimilar(chest) && plugin.getConfig().getBoolean("Procosmetic_Chest")) {
                    System.out.println("[UESL-MCPlugin] " + p.getName() + " has entered a non-chest region. Removing compass or making it invalid now.");
                    p.getInventory().removeItem(this.Chest());
                }
            }

    }
    //Chest Item
    public ItemStack Chest() {
        ItemStack chest = new ItemStack(Material.CHEST);
        ItemMeta chestMeta = chest.getItemMeta();
        assert chestMeta != null;
        chestMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"));
        chestMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "&7Right-click to open the Cosmetic menu.")));
        chestMeta.addEnchant(Enchantment.DURABILITY, 0, false);
        chestMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        chest.setItemMeta(chestMeta);
        return chest;
    }
}


