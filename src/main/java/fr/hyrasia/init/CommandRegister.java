package fr.hyrasia.init;

import fr.hyrasia.Plugin;
import fr.hyrasia.command.PluginCommand;
import fr.hyrasia.commands.utils.CommandDay;
import fr.hyrasia.commands.utils.CommandGameMode;
import fr.hyrasia.commands.utils.CommandNight;
import fr.nooblib.data.json.Json;
import org.bukkit.command.CommandExecutor;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;

public class CommandRegister {
    private static Plugin plugin;

    public static void registerCommands(Plugin plugin){
        CommandRegister.plugin = plugin;

        registerCommand(json("gamemode.json"), new CommandGameMode());
        registerCommand(json("day.json"), new CommandDay());
        registerCommand(json("night.json"), new CommandNight());
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