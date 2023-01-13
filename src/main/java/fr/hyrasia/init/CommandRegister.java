package fr.hyrasia.init;

import fr.hyrasia.Plugin;
import fr.hyrasia.command.PluginCommand;
import fr.hyrasia.commands.utils.CommandGameMode;
import fr.nooblib.data.json.Json;
import org.bukkit.command.CommandExecutor;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;

import java.io.File;

public class CommandRegister {
    private static Plugin plugin;

    public static void registerCommands(Plugin plugin){
        CommandRegister.plugin = plugin;

        registerCommand(json("gamemode.json"), new CommandGameMode());
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
}