package fr.hyrasia.commands.home;

import fr.hyrasia.Plugin;
import fr.nooblib.data.json.Json;
import fr.nooblib.data.mysql.DataSet;
import fr.nooblib.data.mysql.Database;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Class to manage homes
public class Home {
    // Variables
    private final Player player;
    private final String home;
    private final Location location;

    // Method to create the MySQL table of a gave player if not exists
    static void initTable(Player player){
        Database database = new Database();

        database.queryUpdate("CREATE TABLE IF NOT EXISTS <table>(<row1>, <row2>)"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<row1>", "home VARCHAR(20)")
                .replace("<row2>", "location JSON"));
    }

    // Constructor
    public Home(Player player, String home, Location location) {
        initTable(player);

        this.player = player;
        this.home = home;
        this.location = location;
    }

    // Constructor with database loading
    public Home(Player player, String home){
        // Init the table
        initTable(player);

        Database database = new Database();
        this.player = player;
        this.home = home;

        // Check if the home is stored in the database
        Optional<DataSet> optional = database.queryRequest("SELECT location FROM <table> WHERE home='<home>'"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<home>", home));

        // If the home don't exist, create it with the player's current location
        if(optional.isEmpty() || optional.get().getLength() < 1){
            this.location = player.getLocation();
            return;
        }

        // Get the home from the database
        DataSet homes = optional.get();
        String text = homes.getRow(0)[0].get().toString();
        Json json = new Json(text);
        this.location = Plugin.locationFromJson(json);
    }

    // Save the home to the database
    public void putInDatabase(){
        // Delete the home if already exist
        deleteFromDatabase();

        // Put the home in the database
        Database database = new Database();
        database.queryUpdate("INSERT INTO <table> VALUES('<home>', '<location>')"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<home>", home)
                .replace("<location>", Plugin.locationToJson(location).toString()));
    }

    // Delete a home from the database
    public void deleteFromDatabase(){
        Database database = new Database();
        database.queryUpdate("DELETE FROM <table> WHERE home='<home>'"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<home>", home));
    }

    // Getters
    public Player getPlayer() { return player; }
    public String getHome() { return home; }
    public Location getLocation() { return location; }

    // Check if a player has a home with a specified name
    public static boolean exists(Player player, String home){
        // Create player's home table if not exist
        initTable(player);

        // Check if exists
        Database database = new Database();
        Optional<DataSet> optional = database.queryRequest("SELECT home FROM <table> WHERE home='<home>'"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<home>", home));

        if(optional.isEmpty())
            return false;

        DataSet set = optional.get();
        return set.getLength() > 0;
    }

    // Get all the homes of a player
    public static List<Home> getHomesOf(Player player){
        // Create player's home table if not exist
        initTable(player);

        // Get homes from database
        Database database = new Database();
        Optional<DataSet> optional = database.queryRequest("SELECT * FROM <table>"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_")));

        if(optional.isEmpty())
            return new ArrayList<>();

        DataSet set = optional.get();
        List<Home> homes = new ArrayList<>();

        // Load them
        set.forEach(row -> {
            String name = row[0].get().toString();
            Location location = Plugin.locationFromJson(new Json(row[1].get().toString()));

            homes.add(new Home(player, name, location));
        });

        return homes;
    }
}