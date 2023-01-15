package fr.hyrasia.command;

import fr.hyrasia.Plugin;
import fr.nooblib.data.json.Json;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static fr.hyrasia.Plugin.CONFIGURATION;

public class PluginCommand extends Command {
    private final String name, description, usageMessage;
    private final String[] aliases;
    private final String permission;
    private final boolean playerCommand;
    private final CommandExecutor executor;
    private TabCompleter completer;

    public PluginCommand(Json data, CommandExecutor executor){
        super(
                data.getString("name"),
                data.getString("description"),
                data.getString("usage-message"),
                List.of(data.getStringArray("aliases"))
        );

        this.name = data.getString("name");
        this.description = data.getString("description");
        this.usageMessage = data.getString("usage-message");
        this.aliases = data.getStringArray("aliases");
        this.permission = data.getString("permission");
        this.playerCommand = data.getBoolean("player-command");
        this.executor = executor;
        this.completer = null;
    }

    public PluginCommand(Json data, CommandExecutor executor, TabCompleter completer){
        this(data, executor);
        this.completer = completer;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) throws IllegalArgumentException {
        if(completer == null)
            return List.of("");

        return Objects.requireNonNull(completer.onTabComplete(sender, this, label, args));
    }

    @Override public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if(playerCommand && !(sender instanceof Player)){
            sender.sendMessage(CONFIGURATION.getString("message.not-player"));
            return true;
        }

        if(!sender.hasPermission(permission)){
            sender.sendMessage(CONFIGURATION.getString("message.no-permission"));
            return true;
        }

        return executor.onCommand(sender, this, label, args);
    }

    @NotNull @Override public String getName() { return name; }
    @NotNull @Override public String getDescription() { return description; }
    @NotNull @Override public List<String> getAliases() { return List.of(aliases); }
}