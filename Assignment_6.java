import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/*
 * For this assignment, you will plan and write a Java applet to display a shape with
attributes determined by the user.
A level 1 grade will be assigned for completing the following:
Allow the user to control the colour and type (e.g. rectangle, square, circle,
triangle) of the shape, using buttons. At least three shape type choices and

three colour choices should be included. The size of the shape can be hard-
coded into the applet. The current attributes of the shape (size, colour and

shape type) are displayed along with the shape.

A level 2 grade will be assigned for completing all the requirements for a level 1
applet as well as including a text box which allows the user to enter the size of
the shape displayed. At least four shape type choices and four colour choices
should be included.

A level 3 grade will be assigned for completing all the requirements for a level 2
applet as well as using “radio” buttons to control the shape and colour attributes.
A regular button, labelled “Draw” should be added to allow the user to instruct the
applet to actually render the shape, once the choices have been made using the
radio buttons.

A level 4 grade will be assigned for completing all the requirements for a level 3
applet as well as including simple animation to move the shape slowly across the
window from left to right. Add a second button, labelled “Animate” to allow the
user to request this. Consider ways of controlling the positions of the button, text
box and radio buttons being used.

Remember: Fully comment your code
Include an appropriate applet header
 */

public class Assignment_6 extends JFrame implements ActionListener {

    public String shape = "circle"; // variubale for shape
    public Color color = Color.red; // variable for colour
    public int size = 50; // variable for size

    // jframe that will act as window
    // JFrame window = new JFrame("Assignment_6");
    // radio button array for colour
    public JRadioButton[] colourSelection = new JRadioButton[5];
    // radio button array for shape
    JRadioButton[] shapeSelection = new JRadioButton[5];
    // text field for user to input size
    TextField sizeInput = new TextField("Shape Size", 10);

    Button draw = new Button("Draw"); // draws final shape on screen
    Button animate = new Button("Animate"); // animates drawn shape

    boolean valid = false; // variable for valid input

    static void p(String e) { // shortcut to print
        System.out.print(e);
    }

    static void pl(String f) { // shortcut to println
        System.out.println(f);
    }

    public int isInt(String g) { // checks if string is an int
        try {
            size = Integer.parseInt(g); // store input as size

            if (size < 0) { // show error message if size is negative
                valid = false;
                return -1;
            }
            valid = true;
            return size; // return size

        } catch (NumberFormatException e) { // show error message if not
            JOptionPane.showMessageDialog(null, "Exception or error " + e + " has occured!", "Exception or Error", 0);
            valid = false;
            return -10; // different reutrn error code so pop-up only happens once
        }
    }

    public Assignment_6() { // initialises window (Jframe)
        // setting up jframe that will act as window
        setSize(500, 500);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.gray); // dosnt work for some reason

        // creating radio buttons and labels for colour
        // all coordinates were taken from eclipse windowbuilder
        colourSelection[0] = new JRadioButton("Red");
        colourSelection[0].setBounds(34, 53, 109, 23);
        colourSelection[0].setSelected(true); // make this the default

        colourSelection[1] = new JRadioButton("Yellow");
        colourSelection[1].setBounds(34, 157, 109, 23);

        colourSelection[2] = new JRadioButton("Green");
        colourSelection[2].setBounds(34, 79, 109, 23);

        colourSelection[3] = new JRadioButton("Blue");
        colourSelection[3].setBounds(34, 105, 109, 23);

        colourSelection[4] = new JRadioButton("Purple");
        colourSelection[4].setBounds(34, 131, 109, 23);

        // creating radio buttons and labels for shape
        shapeSelection[0] = new JRadioButton("Circle");
        shapeSelection[0].setBounds(166, 53, 109, 23);
        shapeSelection[0].setSelected(true); // make default

        shapeSelection[1] = new JRadioButton("Square");
        shapeSelection[1].setBounds(166, 79, 109, 23);

        shapeSelection[2] = new JRadioButton("Triangle");
        shapeSelection[2].setBounds(166, 105, 109, 23);

        shapeSelection[3] = new JRadioButton("Arc");
        shapeSelection[3].setBounds(166, 131, 109, 23);

        shapeSelection[4] = new JRadioButton("Text");
        shapeSelection[4].setBounds(166, 157, 109, 23);

        // labels to display headers for button
        JLabel lblNewLabel = new JLabel("Shape");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(34, 32, 109, 14);
        getContentPane().add(lblNewLabel);

        JLabel lblColour = new JLabel("Colour");
        lblColour.setHorizontalAlignment(SwingConstants.CENTER);
        lblColour.setBounds(166, 32, 109, 14);
        getContentPane().add(lblColour);

        // creating button group for colour
        ButtonGroup colour = new ButtonGroup();

        for (int i = 0; i < colourSelection.length; i++) {// add buttons using for loop
            colour.add(colourSelection[i]);
            getContentPane().add(colourSelection[i]);
        }

        // creating buttion group for shape
        ButtonGroup shape = new ButtonGroup();

        for (int i = 0; i < shapeSelection.length; i++) { // add butons using for loop
            shape.add(shapeSelection[i]);
            getContentPane().add(shapeSelection[i]);
        }

        // creating and adding textarea for size input
        sizeInput.setBounds(300, 151, 100, 22);
        getContentPane().add(sizeInput);

        // draw and animate button setup
        draw.setBounds(300, 50, 100, 30); // setting bounds
        animate.setBounds(300, 100, 100, 30);

        add(draw); // adding to jframe
        add(animate);

        // animate button shouldnt show until shape is drawn
        animate.setVisible(false);

        // adding actionlisteners to all buttons
        draw.addActionListener(this);

        setVisible(true);
    }

    public void paintComponent(Graphics g) {

        if (valid) {
            switch (shape) {

                case "circle":
                    g.setColor(color);
                    g.drawOval(100, 300, size, size);

                    break;

                case "square":

                    break;

                case "triangle":

                    break;

                case "arc":
                    pl("arc");

                    break;

                case "text":
                    pl("text");

                    break;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                new Assignment_6();
            }
        });
    }

    /// overriden actionPerformed method
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == draw) {

            // check if size is a valid input
            size = isInt(sizeInput.getText());
            if (size > 250 || size == -1) {
                JOptionPane.showMessageDialog(null,
                        "Please input a size that is:\nGreater than 0\nSmaller than 250", "Invalid size!", 0);
                valid = false;
            }

            if (valid) { // only do stuff if the input is valid
                animate.setVisible(true); // show animate button

            }
            pl("F");
            paintComponent(getGraphics());
            ;
        }
    }
}
