package com.mclods.springtictactoe.commands.tictactoe.impl;

import com.mclods.springtictactoe.commands.tictactoe.Player;

public class PlayerImpl implements Player {
    private final String playerName;
    private final char playerSymbol;

    public PlayerImpl(String playerName, char playerSymbol) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
    }

    @Override
    public String getName() {
        return playerName;
    }

    @Override
    public char getSymbol() {
        return playerSymbol;
    }
}
