package com.mclods.springtictactoe.commands.tictactoe;

public interface GameBoard {
    int getSize();
    void printBoard();
    boolean updateBoard(int xIndex, int yIndex, char playerSymbol);
    void resetBoard();
    boolean isBoardFull();
    boolean checkWin();
}
