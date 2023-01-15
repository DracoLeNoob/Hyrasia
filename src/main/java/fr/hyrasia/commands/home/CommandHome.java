package fr.hyrasia.commands.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.hyrasia.Plugin.CONFIGURATION;

public class CommandHome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 1){
            sender.sendMessage(CONFIGURATION.getString("message.no-arguments"));
            return true;
        }

        // Get home name
        Player player = (Player) sender;
        String name = args[0];

        // Check if the home exists
        if(!Home.exists(player, name)){
            player.sendMessage(CONFIGURATION.getString("message.home.not-set"));
            return true;
        }

        // Teleport the player to the home
        Home home = new Home(player, name);
        player.teleport(home.getLocation()); // TODO : avoid to teleport player if he is in a fight
        player.sendMessage(CONFIGURATION.getString("message.home.use"));

        return true;
    }
}