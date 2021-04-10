package by.meearlyam.tictactoe.model;

/**
 * GameGrid class
 *
 * @author Vera Shavela
 * @version 1.0.0
 */
public class GameGrid {

    private static final String[] GAME_SYMBOLS = {" ", "X", "O"};
    private static final String ROW_TEMPLATE = "  %s  |  %s  |  %s  \n";
    private static final String ROW_SEPARATOR = "-----------------\n";

    private int[][] grid;

    public GameGrid() {
        grid = new int[3][3];
        clearGrid();
    }

    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }

    public void clearGrid() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                grid[i][j] = 0;
            }
        }
    }

    /**
     * This method checks is the cell empty or not
     *
     * @param row           grid row number
     * @param col           grid column number
     * @return              boolean value that represents emptiness of the cell
     */
    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == 0;
    }

    /**
     * This method checks if grid is full and the game ended in a draw
     *
     * @return              boolean value that represents is the grid is full
     */
    public boolean isGridFull() {
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
    public boolean checkWin(int symNum) {
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
     * This method returns string representation of Tic Tac Toe grid current state
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nGRID:\n");

        sb.append(
                String.format(ROW_TEMPLATE,
                        GAME_SYMBOLS[grid[0][0]],
                        GAME_SYMBOLS[grid[0][1]],
                        GAME_SYMBOLS[grid[0][2]])
        );
        for(int i = 1; i < 3; i++) {
            sb.append(ROW_SEPARATOR);
            sb.append(
                    String.format(ROW_TEMPLATE,
                            GAME_SYMBOLS[grid[i][0]],
                            GAME_SYMBOLS[grid[i][1]],
                            GAME_SYMBOLS[grid[i][2]])
            );
        }
        return sb.toString();
    }
}
