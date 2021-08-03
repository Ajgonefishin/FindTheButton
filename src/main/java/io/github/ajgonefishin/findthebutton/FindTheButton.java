package io.github.ajgonefishin.findthebutton;

import io.github.ajgonefishin.findthebutton.commands.CurrentScoreCommand;
import io.github.ajgonefishin.findthebutton.commands.NewGameStateCommand;
import io.github.ajgonefishin.findthebutton.commands.SetupArenaCommand;
import io.github.ajgonefishin.findthebutton.commands.StartGameCommand;
import io.github.ajgonefishin.findthebutton.commands.tabcompleters.NewGameStateTabCompleter;
import io.github.ajgonefishin.findthebutton.listeners.InteractListener;
import io.github.ajgonefishin.findthebutton.listeners.JoinListener;
import io.github.ajgonefishin.findthebutton.managers.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FindTheButton extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {
        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new InteractListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new JoinListener(gameManager), this);

        this.getCommand("myscore").setExecutor(new CurrentScoreCommand(gameManager));
        this.getCommand("setgamestate").setExecutor(new NewGameStateCommand(gameManager));
        this.getCommand("setuparena").setExecutor(new SetupArenaCommand(gameManager));
        this.getCommand("startgame").setExecutor(new StartGameCommand(gameManager));

        this.getCommand("setgamestate").setTabCompleter(new NewGameStateTabCompleter(gameManager));
    }

    @Override
    public void onDisable() {

    }

}
