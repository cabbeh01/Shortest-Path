import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlArea extends JPanel {


    JButton btnStartPoint = new JButton("Startpunkt");
    JButton btnEndPoint = new JButton("Slutpunkt");

    public ControlArea(JFrame jp){
        this.setBackground(Color.DARK_GRAY);
        this.add(btnStartPoint,BorderLayout.NORTH);
        this.add(btnEndPoint,BorderLayout.NORTH);
        setPrefSizes();
        buttonLayout(btnEndPoint,btnStartPoint);

        btnStartPoint.addActionListener(e -> {
            String result = (String)JOptionPane.showInputDialog(
                    jp,
                    "Enter the coordinate for the startpoint, in format (x,y)",
                    "Startpoint",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "50,50"
            );
            if(result != null && result.length() > 0){
                try{
                    //Lägga till boundary som man inte få gå utanför
                    Point temp = Area.start;
                    Point newP = new Point(Double.parseDouble(result.split(",")[0]),Double.parseDouble(result.split(",")[1]));
                    int a = 0;
                    for(Polygon poly : Graph.ap){
                        if(Polygon.isInPoly(poly,newP)){
                            JOptionPane.showConfirmDialog(jp, "Point is on a building! Choose a new point","Error: isn't a valid point", JOptionPane.PLAIN_MESSAGE);
                            a++;
                            break;
                        }
                    }
                    if(a==0){
                        Area.start = newP;
                        Area.resetNodes();
                    }
                    else{
                        Area.start = temp;
                    }
                    jp.repaint();
                }
                catch (Exception s){
                    JOptionPane.showConfirmDialog(jp, s.getMessage(),"Error: isn't a valid point", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        btnEndPoint.addActionListener(e -> {
            String result = (String)JOptionPane.showInputDialog(
                    jp,
                    "Enter the coordinate for the endpoint, in format (x,y)",
                    "Endpoint",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "50,50"
            );
            if(result != null && result.length() > 0){
                try{
                    Point temp = Area.start;
                    Point newP = new Point(Double.parseDouble(result.split(",")[0]),Double.parseDouble(result.split(",")[1]));
                    int a = 0;
                    for(Polygon poly : Graph.ap){
                        if(Polygon.isInPoly(poly,newP)){
                            JOptionPane.showConfirmDialog(jp, "Point is on a building! Choose a new point","Error: isn't a valid point", JOptionPane.PLAIN_MESSAGE);
                            a++;
                            break;
                        }
                    }
                    if(a==0){
                        Area.end = newP;
                        Area.resetNodes();
                    }
                    else{
                        Area.end = temp;
                    }

                    jp.repaint();
                }
                catch (Exception s){
                    JOptionPane.showConfirmDialog(jp, s.getMessage(),"Error: isn't a valid point", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
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

