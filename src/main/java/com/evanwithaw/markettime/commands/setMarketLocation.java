package com.evanwithaw.markettime.commands;

import com.evanwithaw.markettime.MarketTime;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setMarketLocation implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("markettime.setlocation")) {
                MarketTime.getInstance().getMarketConfigHandler().setMarketLocation(player.getLocation());
                player.sendMessage("Market location set to your current location.");
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to set the market location.");
            }
        } else {
            sender.sendMessage("This command can only be run by a player.");
        }
        return true;
    }
}