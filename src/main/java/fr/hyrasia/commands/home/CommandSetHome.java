package fr.hyrasia.commands.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.hyrasia.Plugin.CONFIGURATION;

public class CommandSetHome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 1){
            sender.sendMessage(CONFIGURATION.getString("message.no-arguments"));
            return true;
        }

        String name = args[0];

        if(name.length() > CONFIGURATION.getInteger("home.name-limit")){
            sender.sendMessage(CONFIGURATION.getString("message.home.name-limit"));
            return true;
        }

        Player player = (Player) sender;

        if(Home.getHomesOf(player).size() >= CONFIGURATION.getInteger("home.limit")){
            player.sendMessage(CONFIGURATION.getString("message.home.limit"));
            return true;
        }

        Home home = new Home(player, name, player.getLocation());
        home.putInDatabase();
        player.sendMessage(CONFIGURATION.getString("message.home.set"));

        return true;
    }
}