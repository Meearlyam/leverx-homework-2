package by.meearlyam.tictactoe.util;

import by.meearlyam.tictactoe.exception.InputParsingException;

import java.io.IOException;

public interface InputParser {
    int[] parseMove(String input) throws IOException, InputParsingException;
}
