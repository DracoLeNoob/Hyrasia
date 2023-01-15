package fr.hyrasia.commands.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandBroadcast implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length < 1){
            sender.sendMessage("Vous devez spécifier un message à envoyer...");
            return true;
        }

        // Join all arguments to make the message
        String message = String.join(" ", args);
        message = message.replace("&", "§");

        // Broadcast the message
        Bukkit.broadcastMessage("§8[§cBROADCAST§8]: §6" + message);

        return true;
    }
}