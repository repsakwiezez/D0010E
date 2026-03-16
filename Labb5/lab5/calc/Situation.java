package calc;

import javax.swing.JLabel;

/**
 * Modellerar det delade tillståndet i kalkylatorn.
 *
 * <p>Innehåller displayens etikett, aktuell beräkningsstatus och den
 * valda binära operatorn.</p>
 */
 class Situation {
    /** Nuvarande tillstånd i inmatningsflödet. */
    State state = State.Input1;

    /** Etiketten som visar det aktuella värdet. */
    JLabel display;

    /** Den knapp som representerar den valda binära operationen. */
    BinOpButton binaryOperator;

    /** Vänster operand i en binär operation. */
    int leftOperand;

    /**
     * Skapar en ny situation med den givna displayen.
     *
     * @param display etiketten som visar kalkylatorns värde
     */
    Situation(JLabel display) {
        this.display = display;
    }
}
