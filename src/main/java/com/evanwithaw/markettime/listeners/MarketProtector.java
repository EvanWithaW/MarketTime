package com.evanwithaw.markettime.listeners;

import com.evanwithaw.markettime.MarketTime;
import com.evanwithaw.markettime.utils.marketGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MarketProtector {
    private static final double MARKET_RADIUS = MarketTime.getInstance().getMarketConfigHandler().getMarketRadius(); // The radius around the market location
    private static final String MARKET_PERMISSION = "markettime.access"; // The permission to access the market

    public void startCheckingPlayers() {
        Bukkit.getScheduler().runTaskTimer(MarketTime.getInstance(), () -> {
            Location marketLocation = MarketTime.getInstance().getMarketConfigHandler().getMarketLocation();
            if (marketLocation != null) {
                for (Player player : Objects.requireNonNull(Bukkit.getWorld("superflat")).getPlayers()) {
                    if (player.getLocation().distance(marketLocation) <= MARKET_RADIUS && !player.hasPermission(MARKET_PERMISSION) && !marketGUI.shouldBeOpen()) {
                        // Teleport the player to the spawn location
                        player.teleport(player.getWorld().getSpawnLocation());
                        player.sendMessage(ChatColor.RED + "You do not have permission to be in the market after hours.");
                    }
                }
            }
        }, 0L, 20L * 5); // Run every 5 seconds
    }
}