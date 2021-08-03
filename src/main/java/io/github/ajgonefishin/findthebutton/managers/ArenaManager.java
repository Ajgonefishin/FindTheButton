package io.github.ajgonefishin.findthebutton.managers;

import io.github.ajgonefishin.findthebutton.Arena;
import org.bukkit.Location;

import java.util.ArrayList;

public class ArenaManager {

    private GameManager gameManager;
    public ArenaManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private final ArrayList<Arena> arenas = new ArrayList<Arena>();

    private int arenaCount = 0;

    public void addArena(String arenaName, Location spawnLoc) {
        this.arenaCount++;
        Arena newArena = new Arena(arenaName, this.arenaCount, spawnLoc);
        arenas.add(newArena);
    }

    public Arena getArenaByName(String arenaName) {
        for (Arena arena : arenas) {
            if(arena.getName().equals(arenaName)) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArenaFromLocation(Location spawnLoc) {
        for (Arena arena : arenas) {
            if (arena.getLocation().equals(spawnLoc)) {
                return arena;
            }
        }
        return null;
    }

    public ArrayList<Arena> getArenas() {
        return this.arenas;
    }

}
