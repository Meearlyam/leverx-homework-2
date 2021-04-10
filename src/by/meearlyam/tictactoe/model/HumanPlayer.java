package by.meearlyam.tictactoe.model;

import by.meearlyam.tictactoe.exception.InputParsingException;
import by.meearlyam.tictactoe.util.InputParser;

import java.io.BufferedReader;
import java.io.IOException;

public class HumanPlayer extends Player {

    private static final String CHOOSE_CELL_STRING = "Enter 2 digits (row and column) separated by the space to define which cell you want to occupy:";
    private static final String ALREADY_OCCUPIED_CELL_STRING = "You chose already occupied cell! Make another move.";

    private BufferedReader inputReader;
    private InputParser inputParser;

    public HumanPlayer(GameGrid grid, int stepValue, BufferedReader inputReader, InputParser inputParser) {
        super(grid, stepValue);
        this.inputReader = inputReader;
        this.inputParser = inputParser;
    }


    /**
     * This method perform player move in game. It occupies the chosen cell.
     */
    @Override
    public void makeMove() {
        int[] moveCoords;
        int row;
        int col;

        System.out.println(
                String.format("Player %d, please, make your move:", stepValue)
        );

        while (true) {
            try {
                System.out.println(CHOOSE_CELL_STRING);
                moveCoords = inputParser.parseMove(inputReader.readLine());

                row = moveCoords[0] - 1;
                col = moveCoords[1] - 1;

                if (!grid.isCellEmpty(row, col)) {
                    System.out.println(ALREADY_OCCUPIED_CELL_STRING);
                    continue;
                }

                grid.setCell(row, col, stepValue);
                break;
            }
            catch (InputParsingException | IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
