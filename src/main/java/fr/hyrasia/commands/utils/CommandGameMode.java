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
        if(!sender.hasPermission("bukkit.command.gamemode")){
            sender.sendMessage("§4Vous n'avez pas la permission d'utiliser cette commande...");
            return true;
        }

        if(!(sender instanceof Player player)){
            sender.sendMessage("§4Seul un joueur peut utiliser cette commande...");
            return true;
        }

        if(args.length != 1){
            sender.sendMessage("§4Vous n'avez pas entré le bon nombre d'arguments...");
            return true;
        }

        String gm = args[0];
        GameMode gamemode = switch(gm){
            case "survival", "s", "0" -> GameMode.SURVIVAL;
            case "creative", "c", "1" -> GameMode.CREATIVE;
            case "adventure", "a", "2" -> GameMode.ADVENTURE;
            case "spectator", "sp", "3" -> GameMode.SPECTATOR;
            default -> null;
        };

        if(gamemode == null){
            sender.sendMessage("§4Vous n'avez pas entré un gamemode valide...");
            return true;
        }

        player.setGameMode(gamemode);
        player.sendMessage("§6Vous êtes désormais en gamemode §c" + gamemode.name());

        return true;
    }
}