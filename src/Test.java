/*
The objective of the test program is to allow me to monitor the solving process allowing the solve to undergo only as many iterations as I allow.
I should build a test for the checkValue function
I should have the following commands:
    -A function that accepts a number from me in testing and passes it to the solve function as a number of iterations argument
    -commands that allow me to view all the arrays: puzzleSpace, iterationSpace, clueSpace
 */
import java.util.Scanner;
public class Test {
    public static void main(String args[]){
        int[][] puzzle1EasyKnownSolution = new int[][]{
                {8, 0, 0, 9, 3, 0, 0, 0, 2},
                {0, 0, 9, 0, 0, 0, 0, 4, 0},
                {7, 0, 2, 1, 0, 0, 9, 6, 0},
                {2, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 6, 0, 0, 0, 0, 0, 7, 0},
                {0, 7, 0, 0, 0, 6, 0, 0, 5},
                {0, 2, 7, 0, 0, 8, 4, 0, 6},
                {0, 3, 0, 0, 0, 0, 5, 0, 0},
                {5, 0, 0, 0, 6, 2, 0, 0, 8}
        };

        int[][] blank = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[][] puzzle2 = new int[][]{
                {0, 7, 0, 0, 1, 0, 0, 2, 0},
                {5, 0, 0, 0, 2, 7, 0, 0, 1},
                {0, 0, 2, 5, 0, 8, 0, 0, 4},
                {0, 0, 0, 9, 0, 0, 0, 0, 0},
                {8, 6, 0, 1, 0, 0, 9, 5, 3},
                {0, 0, 0, 3, 0, 0, 1, 0, 0},
                {3, 0, 0, 0, 0, 0, 0, 0, 9},
                {0, 2, 0, 7, 5, 0, 0, 0, 0},
                {0, 0, 0, 2, 3, 0, 4, 8, 0}
        };

        int[][] puzzle3 = new int[][]{
                {6, 5, 9, 0, 1, 0, 2, 8, 0},
                {1, 0, 0, 0, 5, 0, 0, 3, 0},
                {2, 0, 0, 8, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 3, 5, 0, 7, 0},
                {8, 0, 0, 9, 0, 0, 0, 0, 2},
                {0, 0, 3, 0, 7, 8, 6, 4, 0},
                {3, 0, 2, 0, 0, 9, 0, 0, 4},
                {0, 0, 0, 0, 0, 1, 8, 0, 0},
                {0, 0, 8, 7, 6, 0, 0, 0, 0}
        };

        int[][] puzzle4 = new int[][]{
                {7, 6, 2, 0, 0, 0, 9, 0, 0},
                {0, 5, 9, 2, 8, 0, 0, 0, 0},
                {0, 0, 0, 4, 0, 0, 0, 7, 0},
                {5, 1, 0, 0, 0, 6, 0, 0, 0},
                {0, 0, 6, 0, 0, 8, 7, 5, 4},
                {0, 2, 0, 0, 7, 0, 0, 6, 9},
                {0, 0, 0, 0, 0, 0, 0, 8, 7},
                {0, 0, 3, 0, 1, 0, 0, 9, 0},
                {2, 0, 0, 0, 0, 0, 5, 1, 0}
        };



        Puzzle solve = new Puzzle(puzzle4);
        solve.solve();
    }
}
