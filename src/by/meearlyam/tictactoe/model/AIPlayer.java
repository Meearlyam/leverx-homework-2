package by.meearlyam.tictactoe.model;

import java.util.Random;

public class AIPlayer extends Player {

    private Random random;

    public AIPlayer(GameGrid grid, int stepValue) {
        super(grid, stepValue);
        random = new Random();
    }

    /**
     * This method perform AI move in game. It occupies random empty cell.
     */
    @Override
    public void makeMove() {
        int row;
        int col;
        while(true) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (grid.isCellEmpty(row, col)) {
                grid.setCell(row, col, stepValue);
                break;
            }
        }
        System.out.println("AI have made its move:");
    }
}
