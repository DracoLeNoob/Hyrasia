package fr.hyrasia.commands;

import fr.hyrasia.commands.home.Home;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HomeCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            Player player = (Player) sender;
            List<Home> homes = Home.getHomesOf(player);
            List<String> values = new ArrayList<>();

            homes.forEach(home -> values.add(home.getHome()));
            values.removeIf(value -> !value.startsWith(args[0]));

            return values;
        }

        return List.of("");
    }
}