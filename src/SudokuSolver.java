/*
The sudoku puzzle is represented by a 9X9 array with i = y and j = x with the orgin located at the top left corner
i notates row number taob to bottom, j notates column number left to right
 */

/*
NOTES FOR NEXT TIME
having tried everything the solve regresses to the first entry where it can't go back any more
need a way to assign a value to the first variable marking it as a boundary and having an exception in the solve logic
 */

import java.util.Scanner;

public class SudokuSolver {
    Scanner input = new Scanner(System.in);
    private  int iterationCounter = 0; //tracks number of iterations
    private int[][] puzzleSpace = new int[9][9]; //9x9 array that represents the Sudoku puzzle to be solved
    private boolean[][] clueSpace = new boolean[9][9]; //keeps track of the constants and variables of the puzzle, constants are set to true
    private int[][] iterationSpace = new int[9][9]; //keeps track of the number of times a value has been incremented
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

    /*
    Accepts a puzzle defined outside the class and passes it to puzzleSpace
     */
    public void passPuzzletoClass(int[][] puzzle){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzleSpace[i][j] = puzzle[i][j];
            }
        }
    }

    /*
    Prints puzzleSpace array in a sudoku fashion
     */
    public void printPuzzle(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                System.out.print(puzzleSpace[i][j]);
                if (j == 2 || j== 5){
                    System.out.print("|");
                }
                if (j == 8){
                    System.out.print("\n");;
                }
            }
            if (i == 2 || i == 5){
                System.out.print("---+---+---\n");
            }
        }
        System.out.println("======================== Iterations" + iterationCounter);
    }

    /*
    populates the native cluespace if a puzzle is defined with the class
     */
    public void populateClueSpace(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzleSpace[i][j] != 0){
                    clueSpace[i][j] = true;
                }
                else {
                    clueSpace[i][j] = false;
                }
            }
        }
    }

    /*
    Accepts user input to populate the puzzleSpace array
    zeros represent unknown values
     */
    public void populatePuzzleByUserInput(){
        int rowTemp;
        int columnTemp;
        int valueTemp;

        while (true) {
            for (int i = 0; i < 9; i++){
                for (int j = 0; j < 9; j++){
                    System.out.println("row: " + (i+1) + " column: " + (j+1) + " Enter a value");
                    puzzleSpace[i][j] = input.nextInt();
                    if (puzzleSpace[i][j] != 0) //prints the puzzle when a non zero value is entered
                        printPuzzle();
                }
            }
        }
    }

    /*
    returns false if a puzzle value is invalid, can be split into multiple methods
     */
    public boolean checkValue(int iValue, int jValue) {
        boolean doesItMatch = true;//stores the result of the check

        //returns false for invalid value
        if (puzzleSpace[iValue][jValue] > 9){
            doesItMatch = false;
        }

        //checks for matching values in rows, row number denoted bu iValue
        for (int j = 0; j < 9; j++) {
            if (puzzleSpace[iValue][jValue] == puzzleSpace[iValue][j] && j != jValue) {
                doesItMatch = false;
                break;
            }
        }
        //checks for matching values in columns, column number denoted by jvalue
        for (int i = 0; i < 9; i++) {
            if (puzzleSpace[iValue][jValue] == puzzleSpace[i][jValue] && i != iValue) {
                doesItMatch = false;
                break;
            }
        }
        //checks for matching values in cells
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cellSpace[iValue][jValue] == cellSpace[i][j] && (iValue != i || jValue != j)) {
                    if (puzzleSpace[iValue][jValue] == puzzleSpace[i][j]) {
                        doesItMatch = false;
                        break;
                    }
                }
            }
        }
        return doesItMatch;
    }

    /*
    uses a brute force method to solve a given puzzle
    The steps are as follows
    1 - start on the first space
    2 - check if the value is a constant or variable
    3 -
    if constant
        move to the next row entry
    if variable
        increment the value by 1 and check
        if true
            move to next row entry
        if false
           increment value by one and repeat, increment attempt counter by one

     */
    public void solvePuzzle(int[][] puzzle, int iterations){ //iterations specifies the number of times the puzzle will be reiterated and printed
        passPuzzletoClass(puzzle);
        populateClueSpace(); //fills the cluespace based on the puzzle values

        for (int i = 0; i < 9; /*do nothing*/){
            for (int j = 0; j < 9; /*do nothing*/){
                while (clueSpace[i][j] == false){ //checks if value is constant
                    puzzleSpace[i][j]++; //increments the value by one
                    iterationSpace[i][j]++; //counts it as an iteration of that particular number
                    iterationCounter++; //counts it as an iteration
                    printPuzzle(); //prints the new iteration
                    if ( checkValue(i, j) ){ //exits loop if there are no conflicts
                        break;
                    }
                    if (iterationSpace[i][j] == 9){ //exits the loop if every number has been tried to no avail
                        break;
                    }
                }
                if (iterationCounter >= iterations){ //limits the solve to a certain number of iterations
                    break;
                }
                if (iterationSpace[i][j] <= 9 && checkValue(i, j)) { //will only move to the next entry if the current iteration passes
                    j++; //moves to the next column
                    if (j > 8){ //moves to the first entry of the next row if at the end of the column
                        j = 0;
                        i++;
                    }
                }
                if (iterationSpace[i][j] == 9 && !checkValue(i, j)){ //moves to previous entries if the maximum number of iterations has been reached
                    iterationSpace[i][j] = 0; //resets the iteration counter
                    puzzleSpace[i][j] = 0; //resets the value that can'tbe resolved
                    while (true) { //stops it from stopping on a constant value
                        j--;
                        if (j < 0){ //moves to the last entry of the previous row if at the end of the column
                            j = 8;
                            i--;
                        }
                        if (clueSpace[i][j] == false){ //will only exit if not on a clue
                            break;
                        }
                    }
                }
            }
        }
    }
}
