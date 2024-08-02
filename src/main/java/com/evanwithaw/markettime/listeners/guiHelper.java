package com.evanwithaw.markettime.listeners;

import com.evanwithaw.markettime.MarketTime;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class guiHelper implements Listener {
    @EventHandler
    public void clickInventory(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Wintervale Market")) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.getType() == Material.NETHER_STAR) {
                ItemMeta meta = clickedItem.getItemMeta();
                if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Go to the Market")) {
                    Player player = (Player) event.getWhoClicked();
                    Location marketLocation = MarketTime.getInstance().getMarketConfigHandler().getMarketLocation();
                    if (marketLocation != null) {
                        player.teleport(marketLocation);
                        player.sendMessage(ChatColor.GREEN + "Teleported to the market!");
                    } else {
                        player.sendMessage(ChatColor.RED + "The market location is not set!");
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}