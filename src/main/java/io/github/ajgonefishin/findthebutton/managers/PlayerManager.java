package io.github.ajgonefishin.findthebutton.managers;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private GameManager gameManager;
    public PlayerManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private final HashMap<UUID, Integer> score = new HashMap<>();
    private final List<Player> players = new ArrayList<>();

    BossBar bossBar = Bukkit.createBossBar(Utils.colorCode("Placeholder title"), BarColor.GREEN, BarStyle.SOLID);

    public void addScore(UUID uuid, int number) {
        if (getScore(uuid) == null) {
            score.put(uuid, number);
        } else {
            int currentScore = getScore(uuid);
            score.put(uuid, number + currentScore);
        }
    }

    public Integer getScore(UUID uuid) {
        return score.get(uuid);
    }

    public void clearScores() {
        score.clear();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isActivePlayer(Player onlinePlayer) {
        for (Player player : players) {
            if (player.equals(onlinePlayer)) {
                return true;
            }
        }
        return false;
    }

    public BossBar getBossBar() {
        return bossBar;
    }
}
