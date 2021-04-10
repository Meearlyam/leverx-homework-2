package by.meearlyam.tictactoe;

import by.meearlyam.tictactoe.controller.TicTacToeController;
import by.meearlyam.tictactoe.util.ConsoleInputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * GameRunner class
 *
 * @author Vera Shavela
 * @version 1.0.0
 */
public class GameRunner {

    private static final String WELCOME_STRING = "Welcome to Tic Tac Toe console game!";
    private static final String CHOOSE_MODE_STRING = "\nPlease, choose the game mode or Exit option (enter the corresponding digit):" +
            "\n1. Play with another human in the same console;" +
            "\n2. Play with AI";
    private static final String WARNING_STRING = "\nCaution: Any input that differs from specified digits (1 and 2) won't make any result.";
    private static final String REPEAT_OR_NOT_STRING = "\nWould you like to play again? If yes, please, enter 'y', and any other character otherwise.";
    private static final String GOODBYE_STRING = "\nThank you for playing, goodbye!";
    private static final String PLAYER_WINS_TEMPLATE = "\nPlayer #%d WINS!";
    private static final String END_IN_A_DRAW = "DRAW! The game is over.";

    private static final int FIRST_PLAYER_VALUE = 1;
    private static final int SECOND_PLAYER_VALUE = 2;

    /**
     * This method starts Tic Tac Toe game
     *
     * @param args          represents the arguments given to this method when running HelloLeverX
     */
    public static void main(String[] args) throws IOException {

        TicTacToeController controller;

        System.out.println(WELCOME_STRING);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            controller = new TicTacToeController(reader, new ConsoleInputParser());

            while (true) {

                System.out.println(CHOOSE_MODE_STRING);
                System.out.println(WARNING_STRING);
                controller.defineSinglePlayerMode();

                controller.printGrid();
                while (true) {
                    controller.performFirstPlayerMove();
                    controller.printGrid();
                    if(isGameOver(controller, FIRST_PLAYER_VALUE)) {
                        break;
                    }

                    controller.performSecondPlayerMove();
                    controller.printGrid();
                    if(isGameOver(controller, SECOND_PLAYER_VALUE)) {
                        break;
                    }
                }

                System.out.println(REPEAT_OR_NOT_STRING);
                if (reader.readLine().equals("y")) {
                    controller.clearGrid();
                } else {
                    break;
                }
            }
        }

        System.out.println(GOODBYE_STRING);
    }
    
    private static boolean isGameOver(TicTacToeController controller, int playerValue) {
        if(controller.checkWin(playerValue)) {
            System.out.println(
                    String.format(PLAYER_WINS_TEMPLATE, playerValue)
            );
            return true;
        }
        if(controller.isGridFull()) {
            System.out.println(END_IN_A_DRAW);
            return true;
        }
        return false;
    }
}
