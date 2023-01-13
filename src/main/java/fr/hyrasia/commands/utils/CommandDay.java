package fr.hyrasia.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandDay implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("bukkit.command.time")){
            sender.sendMessage("§4Vous n'avez pas la permission d'utiliser cette commande...");
            return true;
        }

        if(!(sender instanceof Player player)){
            sender.sendMessage("§4Seul un joueur peut utiliser cette commande...");
            return true;
        }

        player.getWorld().setTime(0);
        player.sendMessage("§6Il fait maintenant §cjour §6dans votre monde !");

        return true;
    }
}