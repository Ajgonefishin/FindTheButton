package io.github.ajgonefishin.findthebutton.commands.tabcompleters;

import io.github.ajgonefishin.findthebutton.managers.GameManager;
import io.github.ajgonefishin.findthebutton.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NewGameStateTabCompleter implements TabCompleter {

    private GameManager gameManager;
    public NewGameStateTabCompleter(GameManager gameManager) { this.gameManager = gameManager; }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setgamestate") && sender instanceof Player player) {
            if (args.length == 1) {
                List<String> gameStateList = new ArrayList<>();
                for (GameState gameState : GameState.values()) {
                    gameStateList.add(gameState.toString().toLowerCase());
                }
                return gameStateList;
            }
            if (args.length > 1) {
                return new ArrayList<>();
            }
        }
        return null;
    }
}
