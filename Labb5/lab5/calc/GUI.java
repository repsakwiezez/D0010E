package calc;

import javax.swing.*;
import java.awt.*;

/**
 * Grafiskt användargränssnitt för kalkylatorn.
 * <p>
 * Bygger upp displayen och knapparna, samt håller ihop det gemensamma tillståndet.
 */
public class GUI extends JFrame {

    /**
     * Skapar och visar fönstret.
     */
    public GUI() {
        super("Räkna rätt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel display = new JLabel("0", SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        display.setOpaque(true);
        display.setBackground(Color.WHITE);

        Situation situation = new Situation(display);

        JPanel canvas = new JPanel(new GridBagLayout());
        JPanel keyPad = new JPanel(new GridLayout(4,4));

        keyPad.add(new DigitButton("7", situation));
        keyPad.add(new DigitButton("8", situation));
        keyPad.add(new DigitButton("9", situation));
        keyPad.add(new BinOpButton("/", situation, (a, b) -> a / b));

        keyPad.add(new DigitButton("4", situation));
        keyPad.add(new DigitButton("5", situation)); 
        keyPad.add(new DigitButton("6", situation));
        keyPad.add(new BinOpButton("*", situation, (a, b) -> a * b));

        keyPad.add(new DigitButton("1", situation));
        keyPad.add(new DigitButton("2", situation));
        keyPad.add(new DigitButton("3", situation));
        keyPad.add(new BinOpButton("-", situation, (a, b) -> a - b));
        
        keyPad.add(new DigitButton("0", situation));
        keyPad.add(new EqualsButton(situation));
        keyPad.add(new CancelButton(situation));
        keyPad.add(new BinOpButton("+", situation, (a, b) -> a + b));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        canvas.add(display,gbc);

        gbc.gridy = 1;
        canvas.add(keyPad, gbc);

        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

}
