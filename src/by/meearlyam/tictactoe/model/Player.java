package by.meearlyam.tictactoe.model;

public abstract class  Player {

    protected GameGrid grid;
    protected int stepValue;

    protected Player(GameGrid grid, int stepValue) {
        this.grid = grid;
        this.stepValue = stepValue;
    }

    public abstract void makeMove();
}
