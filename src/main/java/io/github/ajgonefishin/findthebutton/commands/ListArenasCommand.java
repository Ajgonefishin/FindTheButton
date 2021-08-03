package io.github.ajgonefishin.findthebutton.commands;

import io.github.ajgonefishin.findthebutton.Arena;
import io.github.ajgonefishin.findthebutton.managers.GameManager;
import io.github.ajgonefishin.findthebutton.managers.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListArenasCommand implements CommandExecutor {

    private GameManager gameManager;
    public ListArenasCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("listarenas")) {
            if (gameManager.getArenaManager().getArenas().size() == 0) {
                sender.sendMessage(Utils.colorCode("&7No arenas found! Set up an arena with /setuparena."));
                return true;
            }

            sender.sendMessage(Utils.colorCode("&7Arenas:"));
            for (Arena arena : gameManager.getArenaManager().getArenas()) {
                sender.sendMessage(arena.getName());
            }
            return true;
        }

        return false;
    }
}
