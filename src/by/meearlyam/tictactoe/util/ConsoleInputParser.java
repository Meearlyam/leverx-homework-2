package by.meearlyam.tictactoe.util;

import by.meearlyam.tictactoe.exception.InputParsingException;

public class ConsoleInputParser implements InputParser {

    private static final String MOVE_WARNING_STRING = "You need to enter 2 digits in range [1, 3]!";


    @Override
    public int[] parseMove(String input) throws InputParsingException {
        String[] moveStrings = input.split("[ ]+");
        int row;
        int col;

        if (moveStrings.length == 2) {
            try {
                row = Integer.parseInt(moveStrings[0]);
                col = Integer.parseInt(moveStrings[1]);

                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    throw new InputParsingException(MOVE_WARNING_STRING);
                }

                return new int[]{row, col};
            }
            catch (NumberFormatException e) {
                throw new InputParsingException(MOVE_WARNING_STRING);
            }
        }
        else {
            throw new InputParsingException(MOVE_WARNING_STRING);
        }
    }
}
