package io.github.ajgonefishin.findthebutton.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Utils {

    public static String colorCode(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String serializeLocation(Location loc) {
        return loc.getWorld().getName() + ", " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ();
    }

    public static Location deserializeLocation(String str) {
        String[] coords = str.split(", ");
        return new Location(Bukkit.getWorld(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]));
    }

    public static String formattedGameTime(int gameTime) {
        int minute = (gameTime - (gameTime % 60)) / 60;
        int second = gameTime % 60;

        if (second < 10) {
            return minute + ":0" + second;
        } else {
            return minute + ":" + second;
        }
    }

}
