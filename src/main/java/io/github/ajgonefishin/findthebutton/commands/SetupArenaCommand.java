package io.github.ajgonefishin.findthebutton.commands;

import io.github.ajgonefishin.findthebutton.managers.GameManager;
import io.github.ajgonefishin.findthebutton.managers.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupArenaCommand implements CommandExecutor {

    private GameManager gameManager;
    public SetupArenaCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setuparena") && sender instanceof Player player) {
            if (args.length == 1) {
                gameManager.getArenaManager().addArena(args[0].toLowerCase(), player.getLocation());
                player.sendMessage(Utils.colorCode("&7Created new arena &r\"" + args[0].toLowerCase() + "\" &7at &a" + Utils.serializeLocation(player.getLocation())));
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(Utils.colorCode("&7Please specify a name for your arena!"));
                return false;
            }
        }
        return false;
    }
}
