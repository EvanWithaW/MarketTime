package com.evanwithaw.markettime.utils;

import com.evanwithaw.markettime.MarketTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class marketGUI {

    private static MarketConfigHandler marketConfigHandler = MarketTime.getInstance().getMarketConfigHandler();

    public static void openGUI(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, ChatColor.BLUE + "Wintervale Market");

        populateGUI(inv);

        player.openInventory(inv);
    }

    public static void populateGUI(Inventory inv) {
        for (int i = 0; i < 9; i++) {
            int timeUntilOpening = timeUntilOpening();
            ItemStack redPane = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ItemMeta redMeta = redPane.getItemMeta();
            redMeta.setDisplayName(ChatColor.RED + "Market opens in " + timeUntilOpening + " hours");
            redPane.setItemMeta(redMeta);
            inv.setItem(i, redPane);
        }
        if (shouldBeOpen()) {
            ItemStack greenPane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            ItemMeta greenMeta = greenPane.getItemMeta();
            greenMeta.setDisplayName(ChatColor.GREEN + "Market Closes in " + ChatColor.GREEN + ChatColor.BOLD + scheduler.timeUntilClosing() + ChatColor.GREEN + " hours");
            greenPane.setItemMeta(greenMeta);

            for (int k = 0; k < 4; k++) {
                inv.setItem(k, greenPane);
            }

            ItemStack netherStar = new ItemStack(Material.NETHER_STAR, 1);
            ItemMeta meta = netherStar.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Go to the Market");
            netherStar.setItemMeta(meta);
            inv.setItem(4, netherStar);

            for (int l = 5; l < 9; l++) {
                inv.setItem(l, greenPane);
            }
        } else {
            int timeUntilOpening = timeUntilOpening();
            if (timeUntilOpening > 9) {
                timeUntilOpening = 9;
            }
            for (int j = 0; j < 9 - timeUntilOpening; j++) {
                ItemStack yellowPane = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1);
                ItemMeta yellowMeta = yellowPane.getItemMeta();
                yellowMeta.setDisplayName(ChatColor.YELLOW + "Market opens in " + timeUntilOpening + " hours");
                yellowPane.setItemMeta(yellowMeta);
                inv.setItem(j, yellowPane);
            }
        }
    }

    public static boolean shouldBeOpen() {
        int currentTime = scheduler.getTime();
        int openTime = marketConfigHandler.getMarketOpenTime();
        int closeTime = marketConfigHandler.getMarketCloseTime();

        if (closeTime > openTime) {
            // Market closes on the same day it opens
            return currentTime >= openTime && currentTime < closeTime;
        } else {
            // Market is open past midnight
            return currentTime >= openTime || currentTime < closeTime;
        }
    }


    public static int timeUntilOpening() {
        int time = scheduler.getTime();
        int openTime = marketConfigHandler.getMarketOpenTime();

        if (time <= openTime) {
            return openTime - time;
        } else {
            return (24 - time) + openTime;
        }
    }
}