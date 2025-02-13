package com.mclods.springtictactoe.commands.tictactoe;

import com.mclods.springtictactoe.commands.tictactoe.impl.PlayerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerImplTests {
    Player pl = new PlayerImpl("Samuel", 'S');

    @Test
    @DisplayName("Get player name")
    void testGetName() {
        assertEquals("Samuel", pl.getName());
    }

    @Test
    @DisplayName("Get player symbol")
    void testGetSymbol() {
        assertEquals('S', pl.getSymbol());
    }
}
