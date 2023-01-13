package fr.hyrasia;

import fr.hyrasia.init.CommandRegister;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        CommandRegister.registerCommands(this);
    }
}