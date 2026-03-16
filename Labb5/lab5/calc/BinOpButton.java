package calc;

import java.util.function.IntBinaryOperator;
import java.awt.Color;

/**
 * En knapp som representerar en binär operator (+, -, *, /).
 * <p>
 * Den håller referens till operationen och kan utföra den på två heltal.
 */
class BinOpButton extends CalculatorButton {

    /** Den funktion som utför den binära operationen. */
    private IntBinaryOperator operator;
    
    /**
     * Skapar en ny binop-knapp.
     *
     * @param text texten som visas på knappen
     * @param situation gemensamt tillstånd för kalkylatorn
     * @param operator funktion som utför den binära operationen
     */
    BinOpButton(String text, Situation situation, IntBinaryOperator operator) {
        super(text, situation);
        this.operator = operator;
    }

    /**
     * Utför den binära operationen på två heltal.
     *
     * @param first det första talet
     * @param second det andra talet
     * @return resultatet av operationen
     */
    int applyAsInt(int first, int second) {
        return operator.applyAsInt(first, second);
    }

    @Override
    void transition() {
        switch (situation.state) {
            case Input1:
                situation.leftOperand = Integer.parseInt(situation.display.getText());
                situation.binaryOperator = this;
                setBorderColor(Color.RED);
                situation.state = State.OpReady;
                break;

            case OpReady:
                situation.binaryOperator.resetBorder();
                situation.binaryOperator = this;
                setBorderColor(Color.RED);
                break;

            case Input2:
                break;

            case HasResult:
                situation.binaryOperator.resetBorder();
                situation.leftOperand = Integer.parseInt(situation.display.getText());
                situation.binaryOperator = this;
                setBorderColor(Color.RED);
                situation.state = State.OpReady;
                break;
        }
    }
}