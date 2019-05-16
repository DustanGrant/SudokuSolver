import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {
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

    Puzzle puzz = new Puzzle();

    @Test
    void testDisplay() {
        puzz.display(puzzle1EasyKnownSolution);
    }

    @Test
    void testPopulatePuzzleSpace() {
        puzz.populatePuzzleSpace(puzzle1EasyKnownSolution);
        puzz.display(puzz.puzzleSpace);
        puzz.populateClueSpace(puzzle1EasyKnownSolution);
        if (puzz.clueSpace[0][0] == 1)
        System.out.print(puzz.clueSpace[0][0]);
    }

    @Test
    void testCheckRows() {
        int[][] rowError = new int[][]{ //[0][2] will have a conflict
                {8, 0, 3, 9, 3, 0, 0, 0, 2},
                {0, 0, 9, 0, 0, 0, 0, 4, 0},
                {7, 0, 2, 1, 0, 0, 9, 6, 0},
                {2, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 6, 0, 0, 0, 0, 0, 7, 0},
                {0, 7, 0, 0, 0, 6, 0, 0, 5},
                {0, 2, 7, 0, 0, 8, 4, 0, 6},
                {0, 3, 0, 0, 0, 0, 5, 0, 0},
                {5, 0, 0, 0, 6, 2, 0, 0, 8}
        };

        puzz.populatePuzzleSpace(rowError);
        assertFalse(puzz.checkRows(0,2));

    }

    @Test
    void testCheckColumns() {
        int[][] columnError = new int[][]{ //[0][2] will have a conflict
                {8, 0, 7, 9, 3, 0, 0, 0, 2},
                {0, 0, 9, 0, 0, 0, 0, 4, 0},
                {7, 0, 2, 1, 0, 0, 9, 6, 0},
                {2, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 6, 0, 0, 0, 0, 0, 7, 0},
                {0, 7, 0, 0, 0, 6, 0, 0, 5},
                {0, 2, 7, 0, 0, 8, 4, 0, 6},
                {0, 3, 0, 0, 0, 0, 5, 0, 0},
                {5, 0, 0, 0, 6, 2, 0, 0, 8}
        };

        puzz.populatePuzzleSpace(columnError);
        assertFalse(puzz.checkColumns(0,2));
    }

    @Test
    void testCheckCells() {
        int[][] cellError = new int[][]{ //[2][5] will have a conflict
                {8, 0, 0, 9, 3, 0, 0, 0, 2},
                {0, 0, 9, 0, 0, 0, 0, 4, 0},
                {7, 0, 2, 1, 0, 3, 9, 6, 0},
                {2, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 6, 0, 0, 0, 0, 0, 7, 0},
                {0, 7, 0, 0, 0, 6, 0, 0, 5},
                {0, 2, 7, 0, 0, 8, 4, 0, 6},
                {0, 3, 0, 0, 0, 0, 5, 0, 0},
                {5, 0, 0, 0, 6, 2, 0, 0, 8}
        };
        puzz.populatePuzzleSpace(cellError);
        assertFalse(puzz.checkCells(2,5));
    }

    @Test
    void testPopulateClueSpace() {
        puzz.populatePuzzleSpace(puzzle1EasyKnownSolution);
        puzz.populateClueSpace(puzzle1EasyKnownSolution);
        puzz.display(puzz.clueSpace);
    }

    @Test
    void testSolve() {
        puzz.populatePuzzleSpace(puzzle1EasyKnownSolution);
        puzz.populateClueSpace(puzzle1EasyKnownSolution);
        puzz.solve();
    }
}