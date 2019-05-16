package sudoku.structure;

public abstract class Puzzle {
    Entry[][] entries;
    int[][][] cellMap; //essentially a lookup table of cell coordinates

    abstract boolean checkRows(int[][] position);
    abstract boolean checkColums(int[][] position);
    abstract boolean checkCells(int[][] position);
    abstract void display();
}
