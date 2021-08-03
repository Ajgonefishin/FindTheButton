package io.github.ajgonefishin.findthebutton.managers;

import io.github.ajgonefishin.findthebutton.GameState;
import io.github.ajgonefishin.findthebutton.ScoreboardBuilder;
import io.github.ajgonefishin.findthebutton.tasks.GameRunningTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Arrays;

public class BoardManager {

    private GameManager gameManager;
    public BoardManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    ScoreboardBuilder mainScoreboard = new ScoreboardBuilder("main",
            Arrays.asList(
                    Utils.colorCode("&eGaming moment?"),
                    Utils.colorCode("&3yeah")
            ));


    public void setScoreboard(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard board = scoreboardManager.getNewScoreboard();
        Objective objective = board.registerNewObjective("main", "dummy", Utils.colorCode("&b&lFind the Button"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team spacer = board.registerNewTeam("spacer");
        spacer.addEntry(ChatColor.GRAY + "");
        objective.getScore(ChatColor.GRAY + "").setScore(11);

        Team timeCounter = board.registerNewTeam("timeCounter");
        timeCounter.addEntry(ChatColor.BLUE + "");
        objective.getScore(ChatColor.BLUE + "").setScore(10);

        Team playerScoreCounter = board.registerNewTeam("scoreCounter");
        playerScoreCounter.addEntry(ChatColor.RED + "");
        objective.getScore(ChatColor.RED + "").setScore(9);

        spacer.addEntry(ChatColor.GRAY + "" + ChatColor.DARK_GRAY);
        objective.getScore(ChatColor.GRAY + "" + ChatColor.DARK_GRAY).setScore(8);

        switch (gameManager.gameState) {
            case LOBBY -> {
                timeCounter.setPrefix( Utils.colorCode("&eGame starts in&r: ") + Utils.formattedGameTime(gameManager.startGameTask.countdownTimer));
            }
            case PLAYING -> {
                timeCounter.setPrefix( Utils.colorCode("&eTime remaining&r: ") + Utils.formattedGameTime(gameManager.gameRunningTask.gameTimer));
                playerScoreCounter.setPrefix(ChatColor.YELLOW + player.getName() + Utils.colorCode("&r - " + gameManager.getPlayerManager().getScore(player.getUniqueId())) + "buttons");
            }
            case END -> {
                timeCounter.setPrefix( Utils.colorCode("&eGame has ended!"));
                playerScoreCounter.setPrefix("");
            }
        }

        player.setScoreboard(board);
    }

    public void updateScoreboard(Player player) {
        Scoreboard board = player.getScoreboard();

        Team timeCounter = board.getTeam("timeCounter");
        Team playerScoreCounter = board.getTeam("scoreCounter");

        switch (gameManager.gameState) {
            case LOBBY -> {
                timeCounter.setPrefix( Utils.colorCode("&eGame starts in&r: ") + Utils.formattedGameTime(gameManager.startGameTask.countdownTimer));
            }
            case PLAYING -> {
                timeCounter.setPrefix( Utils.colorCode("&eTime remaining&r: ") + Utils.formattedGameTime(gameManager.gameRunningTask.gameTimer));
                playerScoreCounter.setPrefix(ChatColor.YELLOW + player.getName() + Utils.colorCode("&r - " + gameManager.getPlayerManager().getScore(player.getUniqueId())) + " buttons     ");

            }
            case END -> {
                timeCounter.setPrefix( Utils.colorCode("&eGame has ended. &r0:00"));
                playerScoreCounter.setPrefix("");

            }
        }
    }

}
