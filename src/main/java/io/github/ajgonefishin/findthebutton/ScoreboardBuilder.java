package io.github.ajgonefishin.findthebutton;

import io.github.ajgonefishin.findthebutton.managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.List;
import java.util.UUID;

public class ScoreboardBuilder {

    private List<String> scoreboardElements;

    private final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    private Scoreboard board = scoreboardManager.getNewScoreboard();

    public ScoreboardBuilder(String title, List<String> scoreboardElements) {
        this.scoreboardElements = scoreboardElements;

        Objective objective = board.registerNewObjective("main", "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for (int i = 0; i <= scoreboardElements.size(); i++) {
            ChatColor current = ChatColor.values()[i];

            Team newTeam = board.registerNewTeam(String.valueOf(i));
            newTeam.addEntry(ChatColor.values()[i] + "");
            objective.getScore(ChatColor.values()[i] + "").setScore(scoreboardElements.size() - i);
            newTeam.setPrefix(scoreboardElements.get(i));
        }
    }

    public void updateScoreboard() {
        for (int i = 0; i <= scoreboardElements.size(); i++) {
            Team newTeam = board.getTeam(String.valueOf(i));
            newTeam.setPrefix(scoreboardElements.get(i));
        }
    }

    public void setNewPlayer(Player player) {
        player.setScoreboard(board);
    }

}
