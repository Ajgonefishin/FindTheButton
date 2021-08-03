package io.github.ajgonefishin.findthebutton.commands;

import io.github.ajgonefishin.findthebutton.managers.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurrentScoreCommand implements CommandExecutor {

    private GameManager gameManager;
    public CurrentScoreCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("myscore")) {
            if (sender instanceof Player player) {
                sender.sendMessage("Your score is " + gameManager.getPlayerManager().getScore(player.getUniqueId()));
            } else {
                sender.sendMessage("You're not a player, you can't run this command.");
            }
            return true;
        }

        return false;
    }
}
