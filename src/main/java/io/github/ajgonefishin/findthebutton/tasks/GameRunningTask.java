package io.github.ajgonefishin.findthebutton.tasks;

import io.github.ajgonefishin.findthebutton.GameState;
import io.github.ajgonefishin.findthebutton.managers.BoardManager;
import io.github.ajgonefishin.findthebutton.managers.GameManager;
import io.github.ajgonefishin.findthebutton.managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRunningTask extends BukkitRunnable {

    private GameManager gameManager;
    public GameRunningTask(GameManager gameManager) { this.gameManager = gameManager; }

    public int gameTimer = 180;

    @Override
    public void run() {
        if (gameManager.gameState != GameState.PLAYING) { cancel(); }

        gameTimer--;

        for (Player player : Bukkit.getOnlinePlayers()) {
            //gameManager.getBoardManager().updateScoreboard(player);
        }

        if (gameTimer <= 0) {
            cancel();
            gameManager.setGameState(GameState.END);
            return;
        }

        if (gameTimer % 60 == 0) {
            if (gameTimer / 60 == 1) {
                Bukkit.broadcastMessage(Utils.colorCode("&b&lFind The Button&r: " + (gameTimer / 60) + Utils.colorCode("&r minute left!")));
            } else {
                Bukkit.broadcastMessage(Utils.colorCode("&b&lFind The Button&r: " + (gameTimer / 60) + Utils.colorCode("&r minutes left!")));
            }

        }

    }
}
