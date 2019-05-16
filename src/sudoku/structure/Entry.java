package sudoku.structure;

public abstract class Entry {
    int value;
    int[][] position;
    abstract boolean isConstant();
    abstract boolean isEqual(Entry compareThis);
}
