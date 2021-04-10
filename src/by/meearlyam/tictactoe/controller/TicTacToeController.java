package by.meearlyam.tictactoe.controller;

import by.meearlyam.tictactoe.model.AIPlayer;
import by.meearlyam.tictactoe.model.GameGrid;
import by.meearlyam.tictactoe.model.HumanPlayer;
import by.meearlyam.tictactoe.model.Player;
import by.meearlyam.tictactoe.util.InputParser;

import java.io.BufferedReader;
import java.io.IOException;

public class TicTacToeController {

    private BufferedReader reader;
    private InputParser parser;
    private Player firstPlayer;
    private Player secondPlayer;
    private GameGrid grid;

    public TicTacToeController(BufferedReader reader, InputParser parser) {
        this.reader = reader;
        this.parser = parser;
        this.grid = new GameGrid();
        this.firstPlayer = new HumanPlayer(grid,1, reader, parser);
    }

    public void printGrid() {
        System.out.print(grid);
    }

    public boolean checkWin(int symNum) {
        return grid.checkWin(symNum);
    }

    public boolean isGridFull() {
        return grid.isGridFull();
    }

    public void clearGrid() {
        grid.clearGrid();
    }

    public void defineSinglePlayerMode() throws IOException {
        String userInput;
        while (true) {
            userInput = reader.readLine();
            switch (userInput) {
                case "1":
                    secondPlayer = new HumanPlayer(grid,2, reader, parser);
                    return;
                case "2":
                    secondPlayer = new AIPlayer(grid, 2);
                    return;
                default:
                    break;
            }
        }
    }

    public void performFirstPlayerMove() {
        firstPlayer.makeMove();
    }

    public void performSecondPlayerMove() {
        secondPlayer.makeMove();
    }

}
