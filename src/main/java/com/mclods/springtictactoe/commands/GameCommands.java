package com.mclods.springtictactoe.commands;

import com.mclods.springtictactoe.commands.tictactoe.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GameCommands {

    private final Game game;

    @Autowired
    GameCommands(Game game) {
        this.game = game;
    }

    @ShellMethod(key="game", value="Start tic tac toe game")
    void startGame() {
        game.startGame();
    }
}
