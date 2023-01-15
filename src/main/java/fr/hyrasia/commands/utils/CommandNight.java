package fr.hyrasia.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.hyrasia.Plugin.CONFIGURATION;

public class CommandNight implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        player.getWorld().setTime(13000);
        player.sendMessage("§6Il fait maintenant §cnuit §6dans votre monde !");

        return true;
    }
}