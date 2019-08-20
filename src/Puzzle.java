/**
 *This class defines a 3x3 sudoku puzzle and all its properties
 * contains methods to populate the arrays, check for valid values, print the puzzle for easy reading, and solve the puzzle
 */
public class Puzzle {
    /** 9x9 Array that represents a sudoku puzzle*/
    public int[][] puzzleSpace;
    /** Keeps track of the unchangeable puzzle values, if 1 it means that the corresponding
     * puzzleSpace entry is constant
     */
    public int[][] clueSpace;
    /** Used as a reference to compare values in the puzzle's 3x3 sub cells*/
    private final int [][] cellSpace = new int[][]{
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {1, 1, 1, 2, 2, 2, 3, 3, 3},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {4, 4, 4, 5, 5, 5, 6, 6, 6},
            {7, 7, 7, 8, 8, 8, 9, 9, 9},
            {7, 7, 7, 8, 8, 8, 9, 9, 9},
            {7, 7, 7, 8, 8, 8, 9, 9, 9}
    };
    private final int[][] rowSpace = new int[][] {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5, 5, 5, 5, 5},
            {6, 6, 6, 6, 6, 6, 6, 6, 6},
            {7, 7, 7, 7, 7, 7, 7, 7, 7},
            {8, 8, 8, 8, 8, 8, 8, 8, 8},
            {9, 9, 9, 9, 9, 9, 9, 9, 9}
    };

    private final int[][] columnSpace = new int[][] {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 4, 5, 6, 7, 8, 9}
    };

    /**
     *  default constructor just cause.
     */
    public Puzzle() {
        puzzleSpace = new int[9][9];
        clueSpace = new  int[9][9];
    }

    /**
     *Constructor, accepts an array of a specific size and type
     * refuses any other size of array, any array with values < 1 or > 9
     * refuses any array with matching values in rows columns or cells
     * When a puzzle is passed as an argument, performs the following in the following order
     * 1 - Checks the dimensions of the argument array, throws exception if invalid
     * 2 - runs fullCheck on every non zero entry, reports if invalid
     * 3 - populates clueSpace and puzzleSpace
     * @param solvable sudoku puzzle formatted as a 9x9 int array
     */
    public Puzzle(int[][] solvable) {
        puzzleSpace = new int[9][9];
        clueSpace = new  int[9][9];
        populatePuzzleSpace(solvable);
        populateClueSpace(puzzleSpace);
    }

    /**
     * passes a 9x9 array to the puzzleSpace array
     * @param solvable the puzzle to be solved
     */
    public void populatePuzzleSpace(int[][] solvable) {

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                puzzleSpace[row][column] = solvable[row][column];
            }
        }
    }

    /**
     * Uses the values of the unsolved puzzle to populate
     * an array that keeps track of what entries are constant and immutable
     * true if constant
     * @param solvable puzzle to be solved
     */
    public void populateClueSpace(int[][] solvable) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (puzzleSpace[row][column] == 0) //for a blank space in the puzzle
                    clueSpace[row][column] = 0;
                else
                    clueSpace[row][column] = 1;
            }
        }
    }


    public void display(int [][] array) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                System.out.printf("%4d", array[row][column]);
            }
            System.out.print("\n"); //new line after printing a row
        }
        System.out.print("\n\n"); //new lines after printing a puzzle
    }

    /**
     * Runs all checks
     * @param row the row number
     * @param column the column number
     * @return
     */
    public boolean fullCheck(int row, int column) {
        boolean passCheck = true;

        passCheck = checkRange(row, column);

        if (passCheck ==true)
            passCheck = checkRows(row, column);

        if (passCheck == true)
            passCheck = checkColumns(row, column);

        if (passCheck ==true)
            passCheck = checkCells(row, column);

        return passCheck;
    }
    

    //maybe change the name of the boolean to something clearer?
    public boolean checkRange(int row, int column) {
        boolean passCheck = true;

        if (puzzleSpace[row][column] > 9)
            passCheck = false;

        return passCheck;
    }

    /**
     * Compares the given value to those in the same row
     * @param row the row number
     * @param  column the column number
     * @return true if there are no conflicts
     */
    public boolean checkRows(int row, int column) { //we only want to compare the value in question to the other 8 values in that particular row
        boolean passCheck = true;

        for (int variableColumn = 0; variableColumn < 9; variableColumn++) { //Only the column number needs to change, the row will be as specified by the input parameter
            if (column != variableColumn) { //prevents the value in question from checking against itself
                if (puzzleSpace[row][column] == puzzleSpace[row][variableColumn]) { //Checks if the entry in question is equal to any other entry in the row
                    passCheck = false;
                }

            }
        }
        return passCheck;
    }

    /**
     * Compares the given row to those in the same column
     * @param row the row number
     * @param column the column number
     * @return true if there are no conflicts
     */
    public boolean checkColumns(int row, int column) {
        boolean passCheck = true;

        for (int variablerow = 0; variablerow < 9; variablerow++){
            if (row != variablerow) {
                if (puzzleSpace[row][column] == puzzleSpace[variablerow][column]) {
                    passCheck = false;
                }
            }
        }

        return passCheck;
    }

    /**
     * Compares the given array entry to those with the same cellSpace value
     * @param row the row number
     * @param column the column number
     * @return true if there are no conflicts
     */
    public boolean checkCells(int row, int column) {
        boolean passCheck = true;

        for (int variableRow = 0; variableRow < 9; variableRow++) { //I'm just going to go through the whole array for this one
            for (int variableColumn = 0; variableColumn < 9; variableColumn++) {
                if (cellSpace[row][column] == cellSpace[variableRow][variableColumn]) { //if they are both in the same cell
                    if (row != variableRow && column != variableColumn) { //the value isn't being compared against itself
                        if (puzzleSpace[row][column] == puzzleSpace[variableRow][variableColumn]) { //if there is a conflict
                            passCheck = false;
                        }
                    }
                }
            }
        }

        return passCheck;
    }

    public void solve () {
        int iterationCounter = 0; //tracks the number of iterations
        int row = 0; //row number
        int column = 0; //column number
        int switchInt = 2; //start at step 2 //why start on step 2? maybe reconsider the numbering?
        boolean exitWhenFalse = true;
        do { //Q: is this the best structure for this algorithm?
            switch (switchInt) {

                case 2: { //Check the clueSpace value
                    if (row == 8 && column == 8 && clueSpace[row][column] == 1) { //value is constant and position is (8, 8)
                        switchInt = 7; //end solve
                    }
                    else if (clueSpace[row][column] == 0) { //value is variable
                        switchInt = 3; //go to step 3
                    }
                    // why can't this just be an else?
                    else if ( clueSpace[row][column] == 1 && (row != 8 || column != 8) ) { //value is constant and only one coordinate can be == 8
                        switchInt = 4; //go to step 4
                    }
                    System.out.printf("Position: (%d, %d), Iteration count: %d, Step: %d\n", row, column, iterationCounter, switchInt);
                    display(puzzleSpace);
                    break;
                }

                case 3: { //Increment puzzleSpace entry, run check, increment iterationCounter
                    puzzleSpace[row][column]++; //increment before checking
                    iterationCounter++;
                    
                    //calling fullcheck every time complicates the logic and slows things down
                    //it should be callled once and assigned to a local variable
                    if (row == 8 && column == 8 && fullCheck(row, column) == true) { //successful and at final entry
                        switchInt = 7; //end solve
                    }
                    else if ( (row != 8 || column != 8) && fullCheck(row, column) == true) { //successful and not at final entry
                        switchInt = 4; //go to Step 4
                    }
                    else if (puzzleSpace[row][column] < 9 && fullCheck(row, column) == false) { //unsuccessful and still options left to try
                        //repeat Step 3
                    }
                    // can't this also just be an else?
                    else if (puzzleSpace[row][column] >= 9 && fullCheck(row, column) == false) { //unsuccessful with no remaining options
                        switchInt = 5; // go to step 5
                    }
                    System.out.printf("Position: (%d, %d), Iteration count: %d, Step: %d\n", row, column, iterationCounter, switchInt);
                    display(puzzleSpace);
                    break;
                }

                case 4: { //move to next position
                    if (column < 8) { //not at end of row
                        column++;
                        // it's unnecessary to have this twice, move it to the end
                        switchInt = 2; //return to step 2
                    }
                    // this should just be an else
                    else if (column == 8) { //end of row
                        column = 0;
                        row++;
                        switchInt = 2; //return to step 2
                    }
                    System.out.printf("Position: (%d, %d), Iteration count: %d, Step: %d\n", row, column, iterationCounter, switchInt);
                    display(puzzleSpace);
                    break;
                }

                case 5: { //moves to previous position, zeroes out current position if variable
                    //the logic here can be simplified to two steps, if it's variable zero it out
                    //then back up based on whether or not it's at the start of the row
                    //if done right no commands should be repeated here
                    
                    if (column != 0 && clueSpace[row][column] == 1) { //constant value not at start of row
                        column--;
                        //everything goes to step 6 anyway we only need to say it once
                        switchInt = 6; // go to Step 6
                    }
                    else if (column == 0 && clueSpace[row][column] == 1) { //constant value at start of row
                        column = 8;
                        row--;
                        switchInt = 6;
                    }
                    else if (column != 0 && clueSpace[row][column] == 0) { //variable value not at start of row
                        puzzleSpace[row][column] = 0; // reset value
                        column--;
                        switchInt = 6;
                    }
                    // maybe just else?
                    else if (column == 0 && clueSpace[row][column] == 0) { // variable value at start of row
                        puzzleSpace[row][column] = 0;
                        column = 8;
                        row--;
                        switchInt = 6;
                    }
                    System.out.printf("Position: (%d, %d), Iteration count: %d, Step: %d\n", row, column, iterationCounter, switchInt);
                    display(puzzleSpace);
                    break;
                }

                case 6: { //check clue status of new position
                    if (clueSpace[row][column] == 0) //value is variable
                        switchInt = 3; //go to step 3
                    else if (clueSpace[row][column] == 1) // value is constant
                        switchInt = 5; //go to step 5
                    System.out.printf("Position: (%d, %d), Iteration count: %d, Step: %d\n", row, column, iterationCounter, switchInt);
                    display(puzzleSpace);
                    break;
                }

                case 7: {
                    System.out.println("Puzzle finished.");
                    System.out.println("Iteration count: " + iterationCounter);
                    display(puzzleSpace);
                    exitWhenFalse = false;
                    break;
                }

                default: {
                    break;
                }
            }
        } while (exitWhenFalse);
    }

}
