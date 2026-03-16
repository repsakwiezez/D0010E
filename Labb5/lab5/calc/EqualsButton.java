package calc;

/**
 * Knapp som räknar ut resultatet av den valda binära operationen.
 */
class EqualsButton extends CalculatorButton {

    /**
     * Skapar en equals-knapp.
     *
     * @param situation delat tillstånd för kalkylatorn
     */
    EqualsButton(Situation situation) {
        super("=", situation);
    }

    @Override
    void transition() {
        switch (situation.state) {
            case Input2:
                int rightOperand = Integer.parseInt(situation.display.getText());

                int result = situation.binaryOperator.applyAsInt(situation.leftOperand, rightOperand);

                situation.display.setText(String.valueOf(result));

                situation.binaryOperator.resetBorder();

                situation.state = State.HasResult;
                break;
        
            default:
                break;
        }
    }
}