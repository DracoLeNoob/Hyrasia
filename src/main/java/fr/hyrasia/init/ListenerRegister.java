package fr.hyrasia.init;

import fr.hyrasia.Plugin;
import fr.hyrasia.listeners.PlayerJoinListener;
import fr.hyrasia.listeners.PlayerQuitListener;
import org.bukkit.event.Listener;

public class ListenerRegister {
    private static Plugin plugin;

    public static void registerListeners(Plugin plugin){
        ListenerRegister.plugin = plugin;

        registerListener(new PlayerJoinListener());
        registerListener(new PlayerQuitListener());
    }

    private static void registerListener(Listener listener){
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}