package calc;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Basklass för knappar i kalkylatorn.
 * <p>
 * Innehåller gemensam layout och uppdatering av knapparnas utseende.
 */
abstract class CalculatorButton extends JButton {
    /** Delat tillstånd mellan knapparna. */
    Situation situation;

    /** Ursprungsramen som sparas för att kunna återställa efter att ha markerats. */
    Border defaultBorder;

    /**
     * Skapar en ny kalkylatorknapp.
     *
     * @param text texten som visas på knappen
     * @param situation gemensamt tillstånd för kalkylatorn
     */
    public CalculatorButton(String text, Situation situation) {
        super(text);
        this.situation = situation;

        this.defaultBorder = this.getBorder();

        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(60,60));

        this.addActionListener(e -> transition());
    }

    /**
     * Körs vid knapptryckning och uppdaterar tillståndet.
     */
    abstract void transition();

    /**
     * Sätter knappens bakgrundsfärg.
     *
     * @param color den nya bakgrundsfärgen
     */
    void setColor(Color color) {
        this.setBackground(color);
    }

    /**
     * Sätter knappens kantfärg.
     *
     * @param color färgen som ska användas för kanten
     */
    void setBorderColor(Color color) {
        this.setBorder(BorderFactory.createLineBorder(color, 2));
    }

    /**
     * Återställer knappens kant till dess ursprungliga utseende.
     */
    void resetBorder() {
        this.setBorder(defaultBorder);
    }

    @Override
    public String toString() {
        return this.getText();
    }

}
