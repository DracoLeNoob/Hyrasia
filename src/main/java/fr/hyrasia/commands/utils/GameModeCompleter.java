package fr.hyrasia.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

// /gamemode Command completer
public class GameModeCompleter implements TabCompleter {
    private final List<String> values = List.of(
            "survival", "s", "0", "creative", "c", "1",
            "adventure", "a", "2", "spectator", "sp", "3"
    );

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            List<String> remaining = new ArrayList<>(values);
            remaining.removeIf(value -> !value.startsWith(args[0]));

            return remaining;
        }

        return List.of("");
    }
}