package calc;

/**
 * Knapp som återställer kalkylatorn till startläget.
 */
class CancelButton extends CalculatorButton {

    /**
     * Skapar en cancel-knapp.
     *
     * @param situation delat tillstånd för kalkylatorn
     */
    CancelButton(Situation situation) {
        super("C", situation);
    }

    @Override
    void transition() {
        situation.display.setText("0");
        situation.state = State.Input1;
    
        if (situation.binaryOperator != null) {
            situation.binaryOperator.resetBorder();
            situation.binaryOperator = null;
        }
    }
}
