package io.github.ajgonefishin.findthebutton;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

public class GameButton {
    private ArrayList<UUID> presserUUIDS = new ArrayList<UUID>();
    private Location buttonLocation;

    public GameButton(Location location) {
        this.buttonLocation = location;
    }

    public void addPlayerUUID(UUID uuid) {
        presserUUIDS.add(uuid);
    }

    public Location getButtonLocation() {
        return buttonLocation;
    }

    public boolean hasPlayerPressed(UUID uuid) {
        for (UUID presserUUID : presserUUIDS) {
            if (presserUUID.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Integer getPressCount() {
        return presserUUIDS.size();
    }

    public void setButtonLocation(Location buttonLocation) {
        this.buttonLocation = buttonLocation;
    }

    public void setPresserUUIDS(ArrayList<UUID> presserUUIDS) {
        this.presserUUIDS = presserUUIDS;
    }
}
