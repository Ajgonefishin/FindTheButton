package io.github.ajgonefishin.findthebutton.listeners;

import io.github.ajgonefishin.findthebutton.managers.GameManager;
import io.github.ajgonefishin.findthebutton.GameState;
import io.github.ajgonefishin.findthebutton.managers.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class InteractListener implements Listener {

    private GameManager gameManager;
    public InteractListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void buttonPressed(PlayerInteractEvent event) {
        if (gameManager.gameState != GameState.PLAYING) { return; }
        UUID playerUUID = event.getPlayer().getUniqueId();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location buttonLoc = event.getClickedBlock().getLocation();

            if (event.getClickedBlock().getType() == Material.STONE_BUTTON && !gameManager.getButtonManager().hasPlayerPressedButton(playerUUID, gameManager.getButtonManager().getButtonFromLocation(buttonLoc))) {
                gameManager.getPlayerManager().addScore(playerUUID, 1);
                gameManager.getButtonManager().addPlayerUUIDToButton(playerUUID, buttonLoc);

                event.getPlayer().sendMessage(Utils.colorCode("&7You pressed a button. Your score has increased by 1. This button has been pressed &a") + gameManager.getButtonManager().getButtonFromLocation(buttonLoc).getPressCount().toString() + Utils.colorCode(" &7times."));
                //getLogger().info(player.getDisplayName() + "'s score is now " + gameManager.getPlayerManager().getScore(player).toString());
            }
        }

    }

}
