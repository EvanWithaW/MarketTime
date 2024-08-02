package com.evanwithaw.markettime;

import com.evanwithaw.markettime.utils.MarketConfigHandler;
import com.evanwithaw.markettime.commands.openMarket;
import com.evanwithaw.markettime.commands.setMarketLocation;
import com.evanwithaw.markettime.listeners.MarketProtector;
import com.evanwithaw.markettime.listeners.guiHelper;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class MarketTime extends JavaPlugin {

    private static MarketTime instance;
    private MarketConfigHandler marketConfigHandler;

    public static MarketTime getInstance() {
        return instance;
    }

    public MarketConfigHandler getMarketConfigHandler() {
        return marketConfigHandler;
    }

    @Override
    public void onEnable() {
        instance = this;
        try {
            marketConfigHandler = new MarketConfigHandler(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register the command executor
        getServer().getPluginCommand("market").setExecutor(new openMarket());
        getServer().getPluginCommand("setmarketlocation").setExecutor(new setMarketLocation());
        try {
            new MarketProtector().startCheckingPlayers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Register the listener
        getServer().getPluginManager().registerEvents(new guiHelper(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}