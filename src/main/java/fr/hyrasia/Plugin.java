package fr.hyrasia;

import fr.hyrasia.init.CommandRegister;
import fr.hyrasia.init.ListenerRegister;
import fr.nooblib.data.json.Json;
import fr.nooblib.data.mysql.Database;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

// Main class of the plugin
public class Plugin extends JavaPlugin {
    private static Plugin instance;
    public static final Json CONFIGURATION = new Json("C:/Users/Tim/IdeaProjects/hyrasia/server/plugins/hyrasia/config.json", false);;

    // When the plugin enables
    @Override
    public void onEnable() {
        // Load the configuration
        Json db = CONFIGURATION.getJson("database");
        // Init the database
        Database.initAuth(db.getString("url"), db.getString("user"), db.getString("password"));
        instance = this;

        // Register commands and listeners
        CommandRegister.registerCommands(this);
        ListenerRegister.registerListeners(this);
    }

    // Get a location from json
    public static Location locationFromJson(Json json){
        World world = instance.getServer().getWorld(json.getString("world"));
        double x = json.getDouble("x");
        double y = json.getDouble("y");
        double z = json.getDouble("z");
        float pitch = json.getFloat("pitch");
        float yaw = json.getFloat("yaw");

        return new Location(world, x, y, z, yaw, pitch);
    }

    // Convert location to json
    public static Json locationToJson(Location location){
        Json json = new Json();
        json.put("world", Objects.requireNonNull(location.getWorld()).getName());
        json.put("x", location.getX());
        json.put("y", location.getY());
        json.put("z", location.getZ());
        json.put("pitch", location.getPitch());
        json.put("yaw", location.getYaw());

        return json;
    }
}