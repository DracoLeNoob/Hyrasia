package fr.hyrasia.commands.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static fr.hyrasia.Plugin.CONFIGURATION;

public class CommandDeleteHome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 1){
            sender.sendMessage(CONFIGURATION.getString("message.no-arguments"));
            return true;
        }

        Player player = (Player) sender;
        String name = args[0];

        if(!Home.exists(player, name)){
            player.sendMessage(CONFIGURATION.getString("message.home.not-set"));
            return true;
        }

        Home home = new Home(player, name);
        home.deleteFromDatabase();
        player.sendMessage(CONFIGURATION.getString("message.home.delete"));

        return true;
    }
}