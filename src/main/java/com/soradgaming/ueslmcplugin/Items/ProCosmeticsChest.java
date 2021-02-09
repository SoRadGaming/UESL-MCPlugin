package com.soradgaming.ueslmcplugin.Items;

import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;

public class ProCosmeticsChest implements Listener {

    //Chest Click Usage - Working
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player p = event.getPlayer();
            try {
                if (p.getInventory().getItemInMainHand().getType() == Material.CHEST && Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"))) {
                    event.setCancelled(true);
                    p.performCommand("cosmetics");
                } else {
                    event.setCancelled(true);
                }
            }
            catch (Exception var3_3) {
                System.out.println("[UESL-MCPlugin] A open-chest error has been thrown. Please contact SoRadGaming if the problem persist.");
            }
        }
    }

    /*
    //Prevent Moving the Chest - Error in Console (can move in hot bar but not out of player inventory) - Broken
    @EventHandler
    public void onChestMoveItems(InventoryClickEvent event) {
        ItemStack chest = event.getCurrentItem();
        assert chest != null;
        if (chest.isSimilar(chest) && chest.hasItemMeta() && Objects.requireNonNull(chest.getItemMeta()).getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)")) && event.getSlot() == event.getRawSlot()) {
            event.setCancelled(true);
        }
    }
    */

    //Prevent Dropping the Chest - Working
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack chest = event.getItemDrop().getItemStack();
        if (chest.isSimilar(chest) && chest.hasItemMeta() && Objects.requireNonNull(chest.getItemMeta()).getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aCosmetic Menu &7(Right-click)"))) {
            event.setCancelled(true);
        }
    }

    //Give Chest on Join - Working
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (p.getWorld().getName().equals("Lobbyy")) {
            ItemStack chest = this.Chest();
            if (!p.getInventory().contains(chest)) {
                p.getInventory().setItem(8, chest);
                System.out.println("[UESL-MCPlugin] " + p.getName() + " does not have a chest. Giving it now.");
            }
        } else {
            System.out.println("[UESL-MCPlugin] A player join error has been thrown. Please contact SoRadGaming if the problem persist.");
        }
    }

    //Give Chest on Region Change
    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player p = event.getPlayer();
        ItemStack chest = this.Chest();
        String region = event.getRegionName();
        assert p != null;

        if (region.equals("hub") || region.equals("shub") || region.equals("minigames") || region.equals("hglobby")) {
            if (!p.getInventory().contains(this.Chest()) && !p.getInventory().getItemInOffHand().isSimilar(chest)) {
                if (region.equals("minigames")) {
                    p.getInventory().setItem(8, chest);
                } else {
                    p.getInventory().addItem(this.Chest());
                    System.out.println("[UESL-MCPlugin] " + p.getName() + " does not have a chest. Giving it now.");
                }
            }
        } else {
            if (p.getInventory().contains(this.Chest()) || p.getInventory().getItemInOffHand().isSimilar(chest)) {
                p.getInventory().removeItem(this.Chest());
                System.out.println("[UESL-MCPlugin] " + p.getName() + " has entered a non-chest region. Removing chest or making it invalid now.");
            } else {
                System.out.println("[UESL-MCPlugin] A player Region/World Change error has been thrown. Please contact SoRadGaming if the problem persist.");
            }
        }
    }

    //Chest Item - Working
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


