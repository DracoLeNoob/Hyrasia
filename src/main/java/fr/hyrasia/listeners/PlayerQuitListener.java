package fr.hyrasia.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static fr.hyrasia.Plugin.CONFIGURATION;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event){
        Player player = event.getPlayer();

        // Edit the quit message to look better
        event.setQuitMessage(CONFIGURATION.getString("message.quit")
                .replace("<player>", player.getDisplayName()));
    }
}