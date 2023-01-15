package fr.hyrasia.init;

import fr.hyrasia.Plugin;
import fr.hyrasia.listeners.PlayerJoinListener;
import fr.hyrasia.listeners.PlayerQuitListener;
import org.bukkit.event.Listener;

// Class to register listeners
public class ListenerRegister {
    private static Plugin plugin;

    // Register listeners
    public static void registerListeners(Plugin plugin){
        ListenerRegister.plugin = plugin;

        registerListener(new PlayerJoinListener());
        registerListener(new PlayerQuitListener());
    }

    // Register a listener
    private static void registerListener(Listener listener){
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}