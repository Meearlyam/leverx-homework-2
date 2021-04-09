package by.meearlyam.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * TicTacToe class
 *
 * @author Vera Shavela
 * @version 1.0.0
 */
public class TicTacToe {

    private static final String WELCOME_STRING = "Welcome to Tic Tac Toe console game!";
    private static final String CHOOSE_MODE_STRING = "\nPlease, choose the game mode or Exit option (enter the corresponding digit):" +
            "\n1. Play with another human in the same console;" +
            "\n2. Play with AI" +
            "\n3. Exit";
    private static final String WARNING_STRING = "\nCaution: Any input that differs from digits in range [1, 3] won't make any result.";
    private static final String ALREADY_OCCUPIED_CELL_STRING = "You chose already occupied cell! Make another move.";
    private static final String CHOOSE_CELL_STRING = "Enter 2 digits (row and column) separated by the space to define which cell you want to occupy:";
    private static final String REPEAT_OR_NOT_STRING = "\nWould you like to play again? If yes, please, enter 'y', and any other character otherwise.";
    private static final String GOODBYE_STRING = "\nThank you for playing, goodbye!";
    private static final String PLAYER_WINS_TEMPLATE = "\nPlayer #%d WINS!";
    private static final String END_IN_A_DRAW = "DRAW! The game is over.";

    private static final String[] GAME_SYMBOLS = {" ", "X", "O"};
    private static final String ROW_TEMPLATE = "  %s  |  %s  |  %s  ";
    private static final String ROW_SEPARATOR = "-----------------";

    private static int[][] grid = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    /**
     * This method prints current state of the Tic Tac Toe grid
     */
    private static void drawGrid() {
        System.out.println("\nGRID:");
        System.out.println(
                String.format(ROW_TEMPLATE,
                        GAME_SYMBOLS[grid[0][0]],
                        GAME_SYMBOLS[grid[0][1]],
                        GAME_SYMBOLS[grid[0][2]])
        );
        for(int i = 1; i < 3; i++) {
            System.out.println(ROW_SEPARATOR);
            System.out.println(
                    String.format(ROW_TEMPLATE,
                            GAME_SYMBOLS[grid[i][0]],
                            GAME_SYMBOLS[grid[i][1]],
                            GAME_SYMBOLS[grid[i][2]])
            );
        }
        System.out.println();
    }

    /**
     * This method checks is the cell empty or not
     *
     * @param row           grid row number
     * @param col           grid column number
     * @return              boolean value that represents emptiness of the cell
     */
    private static boolean isCellEmpty(int row, int col) {
        return grid[row][col] == 0;
    }

    /**
     * This method checks if grid is full and the game ended in a draw
     *
     * @return              boolean value that represents is the grid is full
     */
    private static boolean isGridFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * This method checks does current player win or not
     *
     * @param symNum        number that represents player symbol
     * @return              boolean value that represents does the player win or not
     */
    private static boolean checkWin(int symNum) {
        for (int i = 0; i < 3; i++) {
            if ((grid[i][0] == symNum && grid[i][1] == symNum && grid[i][2] == symNum) ||
                    (grid[0][i] == symNum && grid[1][i] == symNum && grid[2][i] == symNum)) {
                return true;
            }
        }
        return (grid[0][0] == symNum && grid[1][1] == symNum && grid[2][2] == symNum) ||
                (grid[0][2] == symNum && grid[1][1] == symNum && grid[2][0] == symNum);
    }

    /**
     * This method perform AI move in game. It occupies random empty cell.
     *
     * @param symNum        value that represents player symbol number in game
     */
    private static void performAIMove(int symNum) {
        Random random = new Random();
        int row;
        int col;
        while(true) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (isCellEmpty(row, col)) {
                grid[row][col] = symNum;
                break;
            }
        }
        System.out.println("AI have made its move:");
    }


    /**
     * This method perform player move in game. It occupies the chosen cell.
     *
     * @param reader        user input reader
     * @param symNum        value that represents player symbol number in game
     * @throws IOException  in case of reading user input errors.
     */
    private static void performHumanMove(BufferedReader reader, int symNum) throws IOException {
        boolean isStepInvalid = true;
        String[] userInput;
        int row;
        int col;

        System.out.println(
                String.format("Player %d, please, make your move:", symNum)
        );

        while (isStepInvalid) {
            System.out.println(CHOOSE_CELL_STRING);
            userInput = reader.readLine().split("[ ]+");

            if (userInput.length == 2) {
                try {
                    row = Integer.parseInt(userInput[0]);
                    col = Integer.parseInt(userInput[1]);

                    if (row < 1 || row > 3 || col < 1 || col > 3) {
                        System.out.println(WARNING_STRING);
                        continue;
                    }

                    row--;
                    col--;
                    if (!isCellEmpty(row, col)) {
                        System.out.println(ALREADY_OCCUPIED_CELL_STRING);
                        continue;
                    }

                    grid[row][col] += symNum;
                    isStepInvalid = false;

                }
                catch (NumberFormatException e) {
                    System.out.println(WARNING_STRING);
                }
            }
        }
    }

    /**
     * This method starts Tic Tac Toe game
     *
     * @param args          represents the arguments given to this method when running HelloLeverX
     */
    public static void main(String[] args) throws IOException {

        boolean isModeDefined = false;
        boolean isSinglePlayerMode = true;
        String userInput;
        int currSymbol;

        System.out.println(WELCOME_STRING);
        System.out.println(CHOOSE_MODE_STRING);
        System.out.println(WARNING_STRING);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {

                while (!isModeDefined) {
                    userInput = reader.readLine();
                    switch (userInput) {
                        case "1":
                            isSinglePlayerMode = false;
                            isModeDefined = true;
                            break;
                        case "2":
                            isModeDefined = true;
                            break;
                        case "3":
                            return;
                        default:
                            System.out.println(WARNING_STRING);
                            break;
                    }
                }

                drawGrid();
                while (true) {

                    currSymbol = 1;
                    performHumanMove(reader, currSymbol);

                    drawGrid();
                    if(checkWin(currSymbol)) {
                        System.out.println(
                                String.format(PLAYER_WINS_TEMPLATE, currSymbol)
                        );
                        break;
                    }
                    if(isGridFull()) {
                        System.out.println(END_IN_A_DRAW);
                        break;
                    }

                    currSymbol = 2;
                    if (isSinglePlayerMode) {
                        performAIMove(currSymbol);
                    }
                    else {
                        performHumanMove(reader, currSymbol);
                    }

                    drawGrid();
                    if(checkWin(currSymbol)) {
                        System.out.println(
                                String.format(PLAYER_WINS_TEMPLATE, currSymbol)
                        );
                        break;
                    }
                    if(isGridFull()) {
                        System.out.println(END_IN_A_DRAW);
                        break;
                    }
                }

                System.out.println(REPEAT_OR_NOT_STRING);
                userInput = reader.readLine();
                if (userInput.equals("y")) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            grid[i][j] = 0;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        System.out.println(GOODBYE_STRING);
    }
}
