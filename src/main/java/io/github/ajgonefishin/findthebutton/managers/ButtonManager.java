package io.github.ajgonefishin.findthebutton.managers;

import io.github.ajgonefishin.findthebutton.GameButton;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ButtonManager {

    private GameManager gameManager;
    public ButtonManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public ArrayList<GameButton> gameButtons = new ArrayList<>();

    public void addPlayerUUIDToButton(UUID uuid, Location loc) { // Add a player's UUID to a button using the button's location.
        GameButton pressedButton = getButtonFromLocation(loc);
        if (pressedButton == null) { //Add new button if there's no button at that location.
            GameButton newButton = new GameButton(loc);
            newButton.addPlayerUUID(uuid);
            newButton.setButtonLocation(loc);
            gameButtons.add(newButton);
        } else if (!pressedButton.hasPlayerPressed(uuid)){ //If the player hasn't pressed the button, add the player to the button.
            pressedButton.addPlayerUUID(uuid);
        }
    }

    public GameButton getButtonFromLocation(Location loc) {
        for (GameButton button : gameButtons) {
            if (button.getButtonLocation().equals(loc)) {
                return button;
            }
        }
        return null;
    }

    public boolean hasPlayerPressedButton(UUID uuid, GameButton button) {
        if (button == null) {
            return false;
        } else {
            return button.hasPlayerPressed(uuid);
        }

    }

    public void clearButtons() {
        gameButtons.clear();
    }


}
