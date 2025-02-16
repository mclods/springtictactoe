package com.mclods.springtictactoe.commands.tictactoe;

import com.mclods.springtictactoe.commands.tictactoe.impl.GameImpl;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class GameImplTests {
    private final ByteArrayOutputStream testOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;

    @BeforeEach
    void beforeEach() {
        System.setOut(new PrintStream(testOutputStream));
    }

    @AfterEach
    void afterEach() {
        System.setOut(originalOutputStream);
    }

    private String getConsoleOutput() {
        return testOutputStream.toString().replaceAll(System.lineSeparator(), "\n");
    }

    @Test
    @DisplayName("Test output when game is a tie")
    void testOutputWhenGameIsATie() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("John Wick", "Brad Pitt", "1");

        GameBoard mockGameBoard = mock(GameBoard.class);
        when(mockGameBoard.getSize()).thenReturn(1);
        when(mockGameBoard.updateBoard(anyInt(), anyInt(), anyChar())).thenReturn(true);
        when(mockGameBoard.isBoardFull()).thenReturn(true);

        Game game = new GameImpl(mockGameBoard, mockScanner);
        game.startGame();

        String mockOutput = """
                ---------------Java Tic Tac Toe---------------
                **********************************************
                ------------------Game Starts-----------------
                **********************************************
                -------------------Player 1-------------------
                Enter player name (Symbol X):
                -------------------Player 2-------------------
                Enter player name (Symbol O):
                Choose a position for John Wick(X) (1 - 9):
                **********************************************
                -----------------It's a Tie :(----------------
                **********************************************
                """;

        assertEquals(mockOutput, getConsoleOutput());
    }

    @Test
    @DisplayName("Test output when game is a win")
    void testOutputWhenGameIsAWin() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("John Wick", "Brad Pitt", "1");

        GameBoard mockGameBoard = mock(GameBoard.class);
        when(mockGameBoard.getSize()).thenReturn(1);
        when(mockGameBoard.updateBoard(anyInt(), anyInt(), anyChar())).thenReturn(true);
        when(mockGameBoard.isBoardFull()).thenReturn(false);
        when(mockGameBoard.checkWin()).thenReturn(true);

        Game game = new GameImpl(mockGameBoard, mockScanner);
        game.startGame();

        String mockOutput = """
                ---------------Java Tic Tac Toe---------------
                **********************************************
                ------------------Game Starts-----------------
                **********************************************
                -------------------Player 1-------------------
                Enter player name (Symbol X):
                -------------------Player 2-------------------
                Enter player name (Symbol O):
                Choose a position for John Wick(X) (1 - 9):
                **********************************************
                -----------------It's a Win :)----------------
                *** John Wick WON!!!
                **********************************************
                """;

        assertEquals(mockOutput, getConsoleOutput());
    }

    @Test
    @DisplayName("Test game board is printed correct number of times when game is a tie")
    void testGameBoardIsPrintedCorrectNumberOfTimesWhenGameIsATie() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("John Wick", "Brad Pitt", "1", "5", "7");

        GameBoard mockGameBoard = mock(GameBoard.class);
        when(mockGameBoard.getSize()).thenReturn(1);
        when(mockGameBoard.updateBoard(anyInt(), anyInt(), anyChar())).thenReturn(true);
        when(mockGameBoard.isBoardFull()).thenReturn(false, false, true);
        when(mockGameBoard.checkWin()).thenReturn(false);

        Game game = new GameImpl(mockGameBoard, mockScanner);
        game.startGame();

        verify(mockGameBoard, times(4)).printBoard();
    }

    @Test
    @DisplayName("Test game board is printed correct number of times when game is a win")
    void testGameBoardIsPrintedCorrectNumberOfTimesWhenGameIsAWin() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("John Wick", "Brad Pitt", "1", "5", "7", "6");

        GameBoard mockGameBoard = mock(GameBoard.class);
        when(mockGameBoard.getSize()).thenReturn(1);
        when(mockGameBoard.updateBoard(anyInt(), anyInt(), anyChar())).thenReturn(true);
        when(mockGameBoard.isBoardFull()).thenReturn(false);
        when(mockGameBoard.checkWin()).thenReturn(false, false, false, true);

        Game game = new GameImpl(mockGameBoard, mockScanner);
        game.startGame();

        verify(mockGameBoard, times(5)).printBoard();
    }

    @Test
    @DisplayName("Test game loss or win is checked after each turn")
    void testGameLossOrWinIsCheckedAfterEachTurn() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("John Wick", "Brad Pitt", "1", "5", "7", "6", "2", "3");

        GameBoard mockGameBoard = mock(GameBoard.class);
        when(mockGameBoard.getSize()).thenReturn(1);
        when(mockGameBoard.updateBoard(anyInt(), anyInt(), anyChar())).thenReturn(true);
        when(mockGameBoard.isBoardFull()).thenReturn(false);
        when(mockGameBoard.checkWin()).thenReturn(false, false, false, false, false, true);

        Game game = new GameImpl(mockGameBoard, mockScanner);
        game.startGame();

        verify(mockGameBoard, times(6)).isBoardFull();
        verify(mockGameBoard, times(6)).checkWin();
    }

    @Test
    @DisplayName("Test turn does not proceed until correct position is entered")
    void testTurnDoesNotProceedUntilCorrectPositionIsEntered() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("John Wick", "Brad Pitt", "1", "1", "1", "1", "2");

        GameBoard mockGameBoard = mock(GameBoard.class);
        when(mockGameBoard.getSize()).thenReturn(1);
        when(mockGameBoard.updateBoard(anyInt(), anyInt(), anyChar())).thenReturn(true, false, false, false, true);
        when(mockGameBoard.isBoardFull()).thenReturn(false);
        when(mockGameBoard.checkWin()).thenReturn(false, false, true);

        Game game = new GameImpl(mockGameBoard, mockScanner);
        game.startGame();

        String mockOutput = """
                ---------------Java Tic Tac Toe---------------
                **********************************************
                ------------------Game Starts-----------------
                **********************************************
                -------------------Player 1-------------------
                Enter player name (Symbol X):
                -------------------Player 2-------------------
                Enter player name (Symbol O):
                Choose a position for John Wick(X) (1 - 9):
                Choose a position for Brad Pitt(O) (1 - 9):
                Wrong position entered!!!
                Choose a position for Brad Pitt(O) (1 - 9):
                Wrong position entered!!!
                Choose a position for Brad Pitt(O) (1 - 9):
                Wrong position entered!!!
                Choose a position for Brad Pitt(O) (1 - 9):
                Choose a position for John Wick(X) (1 - 9):
                **********************************************
                -----------------It's a Win :)----------------
                *** John Wick WON!!!
                **********************************************
                """;

        assertEquals(mockOutput, getConsoleOutput());
        verify(mockGameBoard, times(3)).isBoardFull();
        verify(mockGameBoard, times(3)).checkWin();
    }

    @Test
    @DisplayName("Test updateBoard gets called correctly")
    void testUpdateBoardGetsCalledCorrectly() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("John Wick", "Brad Pitt", "7");

        GameBoard mockGameBoard = mock(GameBoard.class);
        when(mockGameBoard.getSize()).thenReturn(3);
        when(mockGameBoard.updateBoard(anyInt(), anyInt(), anyChar())).thenReturn(true);
        when(mockGameBoard.isBoardFull()).thenReturn(true);

        Game game = new GameImpl(mockGameBoard, mockScanner);
        game.startGame();

        verify(mockGameBoard, times(1)).updateBoard(eq(2), eq(0), eq('X'));
    }

    @Test
    @DisplayName("Test updateBoard gets called correctly for multiple rounds")
    void testUpdateBoardGetsCalledCorrectlyForMultipleRounds() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("John Wick", "Brad Pitt", "7", "6", "5");

        GameBoard mockGameBoard = mock(GameBoard.class);
        when(mockGameBoard.getSize()).thenReturn(3);
        when(mockGameBoard.updateBoard(anyInt(), anyInt(), anyChar())).thenReturn(true);
        when(mockGameBoard.isBoardFull()).thenReturn(false);
        when(mockGameBoard.checkWin()).thenReturn(false, false, true);

        Game game = new GameImpl(mockGameBoard, mockScanner);
        game.startGame();

        verify(mockGameBoard, times(1)).updateBoard(eq(2), eq(0), eq('X'));
        verify(mockGameBoard, times(1)).updateBoard(eq(1), eq(2), eq('O'));
        verify(mockGameBoard, times(1)).updateBoard(eq(1), eq(1), eq('X'));
    }
}
