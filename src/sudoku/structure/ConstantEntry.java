package sudoku.structure;

//literally just has to hold a value, a cell value, and say it is constant
public class ConstantEntry extends Entry {

    @Override
    boolean isConstant() {
        return true;
    }
}
