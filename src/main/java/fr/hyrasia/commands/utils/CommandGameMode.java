package fr.hyrasia.commands.utils;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGameMode implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length != 1) {
            sender.sendMessage("§4Vous n'avez pas entré le bon nombre d'arguments...");
            return true;
        }

        // Get what gamemode was give
        String gm = args[0];
        GameMode gamemode = switch (gm) {
            case "survival", "s", "0" -> GameMode.SURVIVAL;
            case "creative", "c", "1" -> GameMode.CREATIVE;
            case "adventure", "a", "2" -> GameMode.ADVENTURE;
            case "spectator", "sp", "3" -> GameMode.SPECTATOR;
            default -> null;
        };

        // Check if the gamemode exists
        if (gamemode == null) {
            sender.sendMessage("§4Vous n'avez pas entré un gamemode valide...");
            return true;
        }

        // Change the gamemode
        player.setGameMode(gamemode);
        player.sendMessage("§6Vous êtes désormais en gamemode §c" + gamemode.name());

        return true;
    }
}