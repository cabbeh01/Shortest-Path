import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ControlArea extends JPanel {

    public static Color startP = Color.RED;
    public static Color endP = Color.GREEN;
    public static Color road = Color.BLUE;
    public static Color shortestRoad = Color.RED;


    JButton btnStartPoint = new JButton("Startpunkt");
    JButton btnEndPoint = new JButton("Slutpunkt");
    JButton btnShortestRoad = new JButton("Kortaste vägen");
    JButton btnShortestRoadColor = new JButton("");
    JButton btnPotential = new JButton("Potential: AV");
    JButton btnStartColor = new JButton("");
    JButton btnEndColor = new JButton("");


    JLabel lblEmpty = new JLabel("");



    public ControlArea(JFrame jp){
        this.setBackground(Color.DARK_GRAY);


        this.add(btnStartPoint,BorderLayout.NORTH);
        this.add(btnStartColor,BorderLayout.NORTH);

        this.add(btnEndPoint,BorderLayout.NORTH);
        this.add(btnEndColor,BorderLayout.NORTH);

        this.add(lblEmpty,BorderLayout.NORTH);

        this.add(btnShortestRoad,BorderLayout.NORTH);
        this.add(btnShortestRoadColor,BorderLayout.NORTH);
        this.add(btnPotential,BorderLayout.NORTH);
        setPrefSizes();
        setColorToButton();

        lblEmpty.setFont(new Font("Arial Black", Font.PLAIN, 24));
        lblEmpty.setHorizontalAlignment(SwingConstants.CENTER);
        buttonLayout(btnEndPoint,btnStartPoint,btnPotential,btnStartColor,btnEndColor
        ,btnShortestRoad,btnShortestRoadColor);

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
                    for(Polygon poly : Graph.polygons){
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
                    //jp.repaint();
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
                    "950,950"
            );
            if(result != null && result.length() > 0){
                try{
                    Point temp = Area.start;
                    Point newP = new Point(Double.parseDouble(result.split(",")[0]),Double.parseDouble(result.split(",")[1]));
                    int a = 0;
                    for(Polygon poly : Graph.polygons){
                        if(Polygon.isInPoly(poly,newP)){
                            JOptionPane.showConfirmDialog(jp, "Point is on a building! Choose a new point","Error: isn't a valid point", JOptionPane.PLAIN_MESSAGE);
                            a++;
                            break;
                        }
                    }
                    if(a==0){
                        if (newP.x > 1000 || newP.x < 0 || newP.y > 1000 || newP.y < 0 ) { //Kolla ifall den är utanför
                            JOptionPane.showConfirmDialog(jp, "Point is outside the map! Choose a new point","Error: Must be between 0-1000px", JOptionPane.PLAIN_MESSAGE);
                            Area.end = temp;
                        }
                        else{
                            Area.end = newP;
                            MapCreator.s.resetNodes();
                        }
                    } else{
                        Area.end = temp;
                    }
                }
                catch (Exception s){
                    JOptionPane.showConfirmDialog(jp, s.getMessage(),"Error: isn't a valid point", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        btnStartColor.addActionListener(e -> {
            Color temp = startP;
            startP = JColorChooser.showDialog(new JColorChooser(), "Choose start point colour",startP);

            btnStartColor.setBackground(startP);
            jp.repaint();
        });

        btnEndColor.addActionListener(e -> {
            endP = JColorChooser.showDialog(
                    new JColorChooser(),
                    "Choose Background Color",Color.BLACK);

            btnEndColor.setBackground(endP);
            jp.repaint();
        });


        btnShortestRoadColor.addActionListener(e -> {
            shortestRoad = JColorChooser.showDialog(
                    new JColorChooser(),
                    "Choose Background Color",Color.BLACK);

            btnShortestRoadColor.setBackground(shortestRoad);
            jp.repaint();
        });

        btnPotential.addActionListener(e -> {
            Area.potential = !Area.potential;
            if(Area.potential){
                btnPotential.setText("Potential: PÅ");
            }
            else{
                btnPotential.setText("Potential: AV");
            }
            jp.repaint();
        });

    }

    public void setPrefSizes(){
        btnStartPoint.setPreferredSize(new Dimension(160, 30));
        btnEndPoint.setPreferredSize(new Dimension(160, 30));
        btnStartColor.setPreferredSize(new Dimension(30, 30));
        btnEndColor.setPreferredSize(new Dimension(30, 30));
        btnShortestRoadColor.setPreferredSize(new Dimension(30, 30));
        btnShortestRoad.setPreferredSize(new Dimension(160, 30));
        btnPotential.setPreferredSize(new Dimension(160, 30));


        lblEmpty.setPreferredSize(new Dimension(185,30));
    }

    private static void buttonLayout(JButton ... a) {
        for(JButton button : a){
            button.setFocusable(false);
            button.setUI(new BasicButtonUI());
        }
    }

    public void setColorToButton(){
        btnStartColor.setBackground(startP);
        btnEndColor.setBackground(endP);
        btnShortestRoadColor.setBackground(shortestRoad);
    }


    //The paint component to draw the panel
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}

