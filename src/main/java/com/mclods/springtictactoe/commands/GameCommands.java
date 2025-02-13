package com.mclods.springtictactoe.commands;

import com.mclods.springtictactoe.commands.tictactoe.Game;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GameCommands {
    private final Game game;

    GameCommands(Game game) {
        this.game = game;
    }

    @ShellMethod(key="game", value="Start a game")
    void startGame() {
        game.startGame();
    }
}
