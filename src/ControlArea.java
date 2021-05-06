import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ControlArea extends JPanel {


    JButton btnStartPoint = new JButton("Startpunkt");
    JButton btnEndPoint = new JButton("Slutpunkt");

    public ControlArea(){
        this.setBackground(Color.DARK_GRAY);
        this.add(btnStartPoint,BorderLayout.NORTH);
        this.add(btnEndPoint,BorderLayout.NORTH);
        setPrefSizes();
        buttonLayout(btnEndPoint,btnStartPoint);
    }

    public void setPrefSizes(){
        btnStartPoint.setPreferredSize(new Dimension(120, 30));
        btnEndPoint.setPreferredSize(new Dimension(120, 30));
    }

    private static void buttonLayout(JButton ... a) {
        for(JButton button : a){
            button.setFocusable(false);
            button.setUI(new BasicButtonUI());
        }
    }

    //The paint component to draw the panel
    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }
}

