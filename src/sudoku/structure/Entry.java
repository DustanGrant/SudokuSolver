package sudoku.structure;

public abstract class Entry {
    //protected because variableEntry will need methods to change it but constantEntry won't
    private int maxValue; //the maximum value of the entry according to the puzzle size, ex. 4, 9, 16, 25, actually extremely limited in choices, maybe an enum?
    protected int value;
    private int cellPosition; //two digit number denoting the cell then the #entry of that cell ex.the second entry in a puzzle is at 01, cell zero, entry 1

    //I don't think this will ever be called but the compiler was yelling at me
    public Entry() {}

    /*
     * When this is being called we will need to calculate the cell position based on the two indices of the puzzle array of entry objects
     * that calculation could either happen inside the constructor or outside by a function in the puzzle class
     * If it happens inside this constructor it would have to scale to puzzles of any size  
     */
    public Entry(int value, int position) {
        this.value = value;
        this.cellPosition = position;
    }

    public boolean isEqual(Entry compareThis) {
        return this.value == compareThis.value;
    }

    public int getMaxValue(){ return maxValue; }
    public int getValue(){ return value; }
    public int getCellPosition(){ return cellPosition; }
    abstract boolean isConstant();
}
