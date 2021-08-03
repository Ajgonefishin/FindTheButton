package io.github.ajgonefishin.findthebutton.listeners;

import io.github.ajgonefishin.findthebutton.managers.GameManager;
import io.github.ajgonefishin.findthebutton.managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.Set;

public class JoinListener implements Listener {

    private GameManager gameManager;
    public JoinListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        if (Bukkit.getOnlinePlayers().size() <= 1) {
            gameManager.getPlayerManager().getBossBar().setTitle(Utils.colorCode("&b&lFind the Button&r: Waiting for players... &7(&r" + Bukkit.getOnlinePlayers().size() + Utils.colorCode("/12&7)")));

        } else if (Bukkit.getOnlinePlayers().size() == 2) {

        }
        //gameManager.getPlayerManager().getBossBar().addPlayer(event.getPlayer());
        //gameManager.getBoardManager().setScoreboard(event.getPlayer());
    }

}
