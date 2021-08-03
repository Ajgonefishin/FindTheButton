package io.github.ajgonefishin.findthebutton.commands;

import io.github.ajgonefishin.findthebutton.GameState;
import io.github.ajgonefishin.findthebutton.managers.GameManager;
import io.github.ajgonefishin.findthebutton.managers.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartGameCommand implements CommandExecutor {

    private GameManager gameManager;
    public StartGameCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("startgame")) {
            if (gameManager.gameState != GameState.PRELOBBY) {
                sender.sendMessage(Utils.colorCode("&7A game is currently active!"));
                return true;
            }

            gameManager.setGameState(GameState.LOBBY);
            return true;
        }
        return false;
    }
}
