package com.mclods.springtictactoe.commands.tictactoe.config;

import com.mclods.springtictactoe.commands.tictactoe.Game;
import com.mclods.springtictactoe.commands.tictactoe.GameBoard;
import com.mclods.springtictactoe.commands.tictactoe.impl.GameBoardImpl;
import com.mclods.springtictactoe.commands.tictactoe.impl.GameImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class GameConfig {
    private final int BOARD_SIZE = 3;

    @Bean
    public GameBoard gameBoard() {
        return new GameBoardImpl(3);
    }

    @Bean
    public Game game(GameBoard gb) {
        Scanner sc = new Scanner(System.in);
        return new GameImpl(gb, sc);
    }
}
