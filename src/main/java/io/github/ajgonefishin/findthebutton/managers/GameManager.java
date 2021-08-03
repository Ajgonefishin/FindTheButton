package io.github.ajgonefishin.findthebutton.managers;

import io.github.ajgonefishin.findthebutton.Arena;
import io.github.ajgonefishin.findthebutton.FindTheButton;
import io.github.ajgonefishin.findthebutton.GameState;
import io.github.ajgonefishin.findthebutton.tasks.GameRunningTask;
import io.github.ajgonefishin.findthebutton.tasks.StartGameTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    private final FindTheButton plugin;
    public GameState gameState = GameState.PRELOBBY;

    private final PlayerManager playerManager;
    private final ButtonManager buttonManager;
    private final ArenaManager arenaManager;
    private final BoardManager boardManager;

    public StartGameTask startGameTask;
    public GameRunningTask gameRunningTask;

    public GameManager(FindTheButton plugin) {
        this.plugin = plugin;

        this.playerManager = new PlayerManager(this);
        this.buttonManager = new ButtonManager(this);
        this.arenaManager = new ArenaManager(this);
        this.boardManager = new BoardManager(this);
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == GameState.PLAYING && gameState == GameState.STARTING) return;

        this.gameState = gameState;
        switch (gameState) {
            case PRELOBBY -> { // Pre lobby - no one is in the lobby or the arena. The "lobby" should technically be the arena spawn location.
                Bukkit.broadcastMessage("Game is in the pre-lobby, no further actions. (GameState.PRELOBBY)");
            }
            case LOBBY -> { // Players are teleported to the lobby. This should be the arena spawn location. Lasts for a certain amount of time, then starts.
                Bukkit.broadcastMessage("Sending players to the lobby. (GameState.LOBBY)");

                this.startGameTask = new StartGameTask(this);
                this.startGameTask.runTaskTimer(this.plugin, 0, 20);
            }
            case STARTING -> { // The game initializes; Buttons are randomized, lobby gates drop, set to game state PLAYING.
                Bukkit.broadcastMessage("Game initializing. Please stand by. (GameState.STARTING)");
                if (this.startGameTask != null) { this.startGameTask.cancel(); }

                Random rand = new Random();
                ArrayList<Arena> arenas = getArenaManager().getArenas();
                if (arenas.size() == 0) {
                    Bukkit.broadcastMessage("No arenas have been set up! Returning to pre lobby.");
                    setGameState(GameState.PRELOBBY);
                    break;
                }
                Arena currentArena = arenas.get(rand.nextInt(arenas.size())); // Get a random arena for now; eventually, implement a voting a system.

                for (Player player : Bukkit.getOnlinePlayers()) {
                    playerManager.addScore(player.getUniqueId(), 0);
                    player.teleport(currentArena.getLocation());
                }

                setGameState(GameState.PLAYING);
            }
            case PLAYING -> { // The game is in an active play state. Set to an ending game state after a certain period of time. (Or maybe a score goal?)
                Bukkit.broadcastMessage("Game is now active! (GameState.PLAYING)");

                this.gameRunningTask = new GameRunningTask(this);
                this.gameRunningTask.runTaskTimer(this.plugin, 0, 20);
            }
            case END -> { // The game is ending. Teleport players to one location (the arena spawn location?) and put lobby gates back up.
                Bukkit.broadcastMessage("Game is in end stage! Congratulations to the winner! (GameState.END)");
                if (this.gameRunningTask != null) { this.gameRunningTask.cancel(); }

                //Teleport current players to spawn

                //Have an end of game timer, approx 10 seconds to say who won.

                setGameState(GameState.RESTARTING);
            }
            case RESTARTING -> { // Put buttons back, teleport players away from the arena (a designated "main lobby"?), set to game state PRELOBBY.
                Bukkit.broadcastMessage("Game is restarting and resetting. (GameState.RESTARTING)");
                buttonManager.clearButtons();
                playerManager.clearScores();

                setGameState(GameState.PRELOBBY);
            }
            default -> throw new IllegalStateException("Unexpected value: " + gameState);
        }
    }

    public PlayerManager getPlayerManager() { return playerManager; }
    public ButtonManager getButtonManager() { return buttonManager; }
    public ArenaManager getArenaManager() { return arenaManager; }
    public BoardManager getBoardManager() { return boardManager; }
}
