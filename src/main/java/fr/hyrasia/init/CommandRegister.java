package fr.hyrasia.init;

import fr.hyrasia.Plugin;
import fr.hyrasia.command.PluginCommand;
import fr.hyrasia.commands.HomeCompleter;
import fr.hyrasia.commands.home.CommandDeleteHome;
import fr.hyrasia.commands.home.CommandHome;
import fr.hyrasia.commands.home.CommandSetHome;
import fr.hyrasia.commands.utils.*;
import fr.nooblib.data.json.Json;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;

import java.util.Objects;

public class CommandRegister {
    private static Plugin plugin;

    public static void registerCommands(Plugin plugin){
        CommandRegister.plugin = plugin;

        // Other commands
        registerCommand(json("test.json"), new CommandTest());

        // Utils commands
        registerCommand(json("gamemode.json"), new CommandGameMode(), new GameModeCompleter());
        registerCommand(json("day.json"), new CommandDay());
        registerCommand(json("night.json"), new CommandNight());
        registerCommand(json("broadcast.json"), new CommandBroadcast());

        // Player commands
        registerCommand(json("sethome.json"), new CommandSetHome());
        registerCommand(json("home.json"), new CommandHome(), new HomeCompleter());
        registerCommand(json("delhome.json"), new CommandDeleteHome(), new HomeCompleter());
    }

    private static Json json(String file){
        String path = "C:/Users/Tim/IdeaProjects/hyrasia/server/plugins/hyrasia/commands/";
        return new Json(path + file, false);
    }

    private static void registerCommand(Json data, CommandExecutor executor){
        CraftServer server = (CraftServer) plugin.getServer();
        String pluginName = plugin.getName();
        PluginCommand command = new PluginCommand(data, executor);
        server.getCommandMap().register(pluginName, command);
    }

    private static void registerCommand(Json data, CommandExecutor executor, TabCompleter completer){
        CraftServer server = (CraftServer) plugin.getServer();
        String pluginName = plugin.getName();
        PluginCommand command = new PluginCommand(data, executor, completer);
        server.getCommandMap().register(pluginName, command);
    }
}