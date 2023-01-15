package fr.hyrasia.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static fr.hyrasia.Plugin.CONFIGURATION;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        // Edit the join message to look better
        event.setJoinMessage(CONFIGURATION.getString("message.join")
                .replace("<player>", player.getDisplayName()));
    }
}