package fr.hyrasia.command;

import fr.nooblib.data.json.Json;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PluginCommand extends Command {
    private final String name, description, usageMessage;
    private final String[] aliases;
    private final CommandExecutor executor;

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
        this.executor = executor;
    }

    @Override public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        return executor.onCommand(sender, this, label, args);
    }

    @NotNull @Override public String getName() { return name; }
    @NotNull @Override public String getDescription() { return description; }
    public String getUsageMessage() { return usageMessage; }
    @NotNull @Override public List<String> getAliases() { return List.of(aliases); }
}