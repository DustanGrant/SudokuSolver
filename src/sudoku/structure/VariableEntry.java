package sudoku.structure;

public class VariableEntry extends Entry{
    boolean isConstant() {
        return false;
    }

    //unlike the ConstantEntryClass we have methods to change the value here

    /**
     * raises the value by 1, if it is at max reset to 0 and return false
     * @return true otherwise
     */
    public boolean incrementValue() {
        //implement here
        return false;
    }

    /**
     * lowers the value by 1, if already one reset to 0
     * @return false if the value is already one
     *          true otherwise
     */
    public boolean decrenmentValue() {
        //implement here
        return false;
    }

    //exactly as advertised
    public void setValue(int newValue) {
        value = newValue;
    }
}
