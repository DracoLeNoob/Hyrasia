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

public class Home {
    private final Player player;
    private final String home;
    private final Location location;

    static void initTable(Player player){
        Database database = new Database();

        database.queryUpdate("CREATE TABLE IF NOT EXISTS <table>(<row1>, <row2>)"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<row1>", "home VARCHAR(20)")
                .replace("<row2>", "location JSON"));
    }

    public Home(Player player, String home, Location location) {
        initTable(player);

        this.player = player;
        this.home = home;
        this.location = location;
    }

    public Home(Player player, String home){
        initTable(player);

        Database database = new Database();
        this.player = player;
        this.home = home;

        Optional<DataSet> optional = database.queryRequest("SELECT location FROM <table> WHERE home='<home>'"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<home>", home));

        if(optional.isEmpty() || optional.get().getLength() < 1){
            this.location = player.getLocation();
            return;
        }

        DataSet homes = optional.get();
        String text = homes.getRow(0)[0].get().toString();
        Json json = new Json(text);
        this.location = Plugin.locationFromJson(json);
    }

    public void putInDatabase(){
        deleteFromDatabase();

        Database database = new Database();
        database.queryUpdate("INSERT INTO <table> VALUES('<home>', '<location>')"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<home>", home)
                .replace("<location>", Plugin.locationToJson(location).toString()));
    }

    public void deleteFromDatabase(){
        Database database = new Database();
        database.queryUpdate("DELETE FROM <table> WHERE home='<home>'"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_"))
                .replace("<home>", home));
    }

    public Player getPlayer() { return player; }
    public String getHome() { return home; }
    public Location getLocation() { return location; }

    public static boolean exists(Player player, String home){
        initTable(player);

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

    public static List<Home> getHomesOf(Player player){
        initTable(player);

        Database database = new Database();
        Optional<DataSet> optional = database.queryRequest("SELECT * FROM <table>"
                .replace("<table>", "homes_" + player.getUniqueId().toString()
                        .replace("-", "_")));

        if(optional.isEmpty())
            return new ArrayList<>();

        DataSet set = optional.get();
        List<Home> homes = new ArrayList<>();

        set.forEach(row -> {
            String name = row[0].get().toString();
            Location location = Plugin.locationFromJson(new Json(row[1].get().toString()));

            homes.add(new Home(player, name, location));
        });

        return homes;
    }
}