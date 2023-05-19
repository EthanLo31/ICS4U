package Applets;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class TrafficLight extends Applet
      implements ActionListener {
   int colourNum;
   Font textFont;
   Button bttn1 = new Button("Red");
   Button bttn2 = new Button("Amber");
   Button bttn3 = new Button("Green");

   public void init() {
      this.setSize(300, 300);
      setBackground(Color.lightGray);
      colourNum = 1;
      textFont = new Font("Serif", Font.BOLD, 24);
      bttn1.addActionListener(this);
      bttn2.addActionListener(this);
      bttn3.addActionListener(this);
      add(bttn1);
      add(bttn2);
      add(bttn3);
   }

   public void paint(Graphics g) {
      Color redOff = new Color(87, 0, 0);
      Color amberOff = new Color(87, 87, 0);
      Color greenOff = new Color(1, 87, 0);

      g.setColor(Color.black);
      g.fillRect(100, 50, 100, 200);
      switch (colourNum) {
         case 1:
            g.setColor(Color.red);
            g.fillOval(150 - (45 / 2), 75, 45, 45);
            g.drawOval(150 - (50 / 2), 72, 50, 50);

            g.setColor(amberOff);
            g.fillOval(150 - (45 / 2), 125, 45, 45);

            g.setColor(greenOff);
            g.fillOval(150 - (45 / 2), 125 + (125 - 75), 45, 45);
            break;
         case 2:
            g.setColor(redOff);
            g.fillOval(150 - (45 / 2), 75, 45, 45);

            g.setColor(Color.yellow);
            g.fillOval(150 - (45 / 2), 125, 45, 45);
            g.drawOval(150 - (50 / 2), 122, 50, 50);

            g.setColor(greenOff);
            g.fillOval(150 - (45 / 2), 125 + (125 - 75), 45, 45);
            break;
         case 3:
            g.setColor(redOff);
            g.fillOval(150 - (45 / 2), 75, 45, 45);

            g.setColor(amberOff);
            g.fillOval(150 - (45 / 2), 125, 45, 45);

            g.setColor(Color.green);
            g.fillOval(150 - (45 / 2), 125 + (125 - 75), 45, 45);
            g.drawOval(150 - (50 / 2), 122 + (125 - 75), 50, 50);
            break;
      }

      g.setColor(Color.black);
      g.setFont(textFont);
      g.drawString("Traffic Light", 150 - (g.getFontMetrics(textFont).stringWidth("Traffic Light") / 2), 290);
   }

   public void actionPerformed(ActionEvent evt) {

      if (evt.getSource() == bttn1)
         colourNum = 1;
      else if (evt.getSource() == bttn2)
         colourNum = 2;
      else
         colourNum = 3;

      repaint();
   }
}
