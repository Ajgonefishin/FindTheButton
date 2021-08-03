package io.github.ajgonefishin.findthebutton.tasks;

import io.github.ajgonefishin.findthebutton.GameState;
import io.github.ajgonefishin.findthebutton.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartGameTask extends BukkitRunnable {

    private GameManager gameManager;
    public StartGameTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public int countdownTimer = 16;

    @Override
    public void run() {
        if (gameManager.gameState != GameState.LOBBY) { cancel(); }

        countdownTimer--;

        for (Player player : Bukkit.getOnlinePlayers()) {
            //gameManager.getBoardManager().updateScoreboard(player);
        }

        switch(countdownTimer) {
            case 1 -> {
                Bukkit.broadcastMessage("Game starting in 1 second!");
                return;
            }
            case 0 -> {
                cancel();
                gameManager.setGameState(GameState.STARTING);
                return;
            }
        }

        if (countdownTimer % 5 == 0 || countdownTimer < 5)  {
            Bukkit.broadcastMessage("Game starting in " + countdownTimer + " seconds!");
        }

    }
}
