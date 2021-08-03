package io.github.ajgonefishin.findthebutton.commands;

import io.github.ajgonefishin.findthebutton.managers.GameManager;
import io.github.ajgonefishin.findthebutton.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NewGameStateCommand implements CommandExecutor {

    private GameManager gameManager;
    public NewGameStateCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("setgamestate")) {
            if (args.length > 1) {
                sender.sendMessage("Too many arguments! Please only specify one game state.");
            } else {
                try {
                    gameManager.setGameState(GameState.valueOf(args[0].toUpperCase()));
                }
                catch(Exception e) {
                    sender.sendMessage("Not a valid game state! Please try again. You wrote: " + args[0]);
                }
            }
            return true;
        }
        return false;
    }
}
