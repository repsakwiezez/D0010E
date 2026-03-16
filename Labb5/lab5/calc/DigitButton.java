package calc;

/**
 * En knapp som representerar en siffra i kalkylatorn.
 */
class DigitButton extends CalculatorButton {

    /**
     * Skapar en ny sifferknapp.
     *
     * @param text      texten som visas på knappen
     * @param situation delat tillstånd för kalkylatorn
     */
    DigitButton(String text, Situation situation) {
        super(text, situation);
    }

    @Override
    void transition() {
        String currentText  = situation.display.getText();
        String digit = this.getText();

        switch (situation.state) {
            case Input1:
            case Input2:
                if (currentText.equals("0")) {
                    situation.display.setText(digit);
                } else {
                    situation.display.setText(currentText + digit);
                }
                break;

            case OpReady:
                situation.display.setText(digit);
                situation.state = State.Input2;
                break;
            
            case HasResult:
                situation.display.setText(digit);
                situation.state = State.Input1;
                break;
        }
    }
}
