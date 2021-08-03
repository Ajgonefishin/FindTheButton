package io.github.ajgonefishin.findthebutton;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

public class Arena {

    private final int id;
    private final String name;
    private final Location spawnLocation;
    private final ArrayList<UUID> playerUUIDs = new ArrayList<UUID>();

    public Arena(String name, int arenaID, Location spawnLoc) {
        this.name = name;
        this.spawnLocation = spawnLoc;
        this.id = arenaID;
    }

    public String getName() { return this.name; }

    public Location getLocation() {
        return this.spawnLocation;
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<UUID> getPlayerUUIDs() {
        return this.playerUUIDs;
    }

}
