import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ControlArea extends JPanel { //Vänstra panelen på fönstret

    //Färgvariabler för punkter och kortastevägen
    public static Color startP = Color.white;
    public static Color endP = Color.cyan.darker();
    public static Color shortestRoad = Color.cyan;

    //Text
    public static JLabel lblPoints = new JLabel("Points: ");
    public static JLabel lblPolygons = new JLabel("Polygons: ");
    public static JLabel lblEdges = new JLabel("Edges: ");

    JButton btnStartPoint = new JButton("Startpoint");
    JButton btnEndPoint = new JButton("Endpoint");
    JLabel lblShortest = new JLabel("Shortest path");
    JButton btnShortestRoadColor = new JButton("");
    JButton btnPotential = new JButton("Potential: OFF");
    JButton btnForbidden = new JButton("Forbidden: OFF");
    JButton btnStartColor = new JButton("");
    JButton btnEndColor = new JButton("");
    JButton btnoutFill = new JButton("");
    JButton btnoutFill1= new JButton("");
    JLabel text = new JLabel();

    JLabel lblEmpty = new JLabel("");
    JLabel lblEmpty1 = new JLabel("");
    JLabel lblEmpty2 = new JLabel("");


    //Här skapar vi den största delen av det grafiska gränsnittet, som t.ex. knappar eller labels.
    public ControlArea(JFrame jp){
        this.setBackground(Color.DARK_GRAY.darker());

        this.add(lblEmpty1,BorderLayout.NORTH);
        this.add(text,BorderLayout.NORTH);
        this.add(btnStartPoint,BorderLayout.NORTH);
        this.add(btnStartColor,BorderLayout.NORTH);

        this.add(btnEndPoint,BorderLayout.NORTH);
        this.add(btnEndColor,BorderLayout.NORTH);

        this.add(lblEmpty,BorderLayout.NORTH);

        this.add(lblShortest,BorderLayout.NORTH);
        this.add(btnShortestRoadColor,BorderLayout.NORTH);

        this.add(btnPotential,BorderLayout.NORTH);
        this.add(btnForbidden,BorderLayout.NORTH);

        this.add(lblEmpty2,BorderLayout.NORTH);


        this.add(lblPolygons);
        this.add(lblPoints);
        this.add(lblEdges);


        setPrefSizes();
        setColorToButton();

        Image img = new ImageIcon("res/ShortestPath.png").getImage();
        Image newimg = img.getScaledInstance(200, 80,  Image.SCALE_DEFAULT);
        text.setIcon(new ImageIcon(newimg));

        lblEmpty.setFont(new Font("Arial Black", Font.PLAIN, 24));
        lblShortest.setFont(new Font("Arial Black", Font.PLAIN, 16));
        lblShortest.setForeground(Color.white);
        lblEmpty.setHorizontalAlignment(SwingConstants.CENTER);
        buttonLayout(btnEndPoint,btnStartPoint,btnPotential,btnForbidden,btnStartColor,btnEndColor
        ,btnShortestRoadColor); //Ändrar temat på knapparna

        labelLayout(lblEdges,lblPoints,lblPolygons);

        buttonFillout(btnoutFill1,btnoutFill);

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
                        if (newP.x > 1000 || newP.x < 0 || newP.y > 1000 || newP.y < 0 ) { //Kolla ifall den är utanför boundry
                            JOptionPane.showConfirmDialog(jp, "Point is outside the map! Choose a new point","Error: Must be between 0-1000px", JOptionPane.PLAIN_MESSAGE);
                            Area.start = temp;
                        } else {
                            Area.start = newP;
                            MapCreator.s.resetNodes();
                        }
                    }
                    else{
                        Area.start = temp;
                    }
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
                        if (newP.x > 1000 || newP.x < 0 || newP.y > 1000 || newP.y < 0 ) { //Kolla ifall den är utanför boundry
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
            startP = JColorChooser.showDialog(new JColorChooser(), "Choose startpoint colour",startP);

            if(startP == null) //Ifall man inte väljer något så behåller den färgen från innan
                startP = temp;
            btnStartColor.setBackground(startP);
            jp.repaint();
        });

        btnEndColor.addActionListener(e -> {
            Color temp = endP;
            endP = JColorChooser.showDialog(new JColorChooser(), "Choose endpoint colour",endP);

            if (endP == null) //Ifall man inte väljer något så behåller den färgen från innan
                endP = temp;
            btnEndColor.setBackground(endP);
            jp.repaint();
        });


        btnShortestRoadColor.addActionListener(e -> {
            shortestRoad = JColorChooser.showDialog(
                    new JColorChooser(),
                    "Choose road colour",shortestRoad);

            btnShortestRoadColor.setBackground(shortestRoad);
            jp.repaint();
        });

        btnPotential.addActionListener(e -> {
            Area.potential = !Area.potential;
            if(Area.potential)
                btnPotential.setText("Potential: ON");
            else
                btnPotential.setText("Potential: OFF");

            Area.start = new Point(Area.start.x,Area.start.y);
            Area.end = new Point(Area.end.x,Area.end.y);;

            MapCreator.s.resetNodes();
        });

        btnForbidden.addActionListener(e -> {

            Area.blockedAreas = !Area.blockedAreas;

            if(Area.blockedAreas)
                btnForbidden.setText("Forbidden: PÅ");
            else
                btnForbidden.setText("Forbidden: AV");

            Point tempStart = Area.start;
            Point tempEnd = Area.end;
            //Eftersom blockedareas tar väck visa vägar så måste vi trigga printpath igen för att rita om den
            Area.start = new Point(tempStart.x,tempStart.y);
            Area.end = new Point(tempEnd.x,tempEnd.y);;

            MapCreator.s.resetNodes();
        });

    }

    public void setPrefSizes(){
        btnStartPoint.setPreferredSize(new Dimension(160, 30));
        btnEndPoint.setPreferredSize(new Dimension(160, 30));
        btnStartColor.setPreferredSize(new Dimension(30, 30));
        btnEndColor.setPreferredSize(new Dimension(30, 30));
        btnShortestRoadColor.setPreferredSize(new Dimension(30, 30));
        lblShortest.setPreferredSize(new Dimension(160, 30));
        btnPotential.setPreferredSize(new Dimension(160, 30));
        btnForbidden.setPreferredSize(new Dimension(160, 30));
        lblEmpty.setPreferredSize(new Dimension(185,30));
        lblEmpty1.setPreferredSize(new Dimension(185,30));
        lblEmpty2.setPreferredSize(new Dimension(185,200));
        lblEmpty2.setPreferredSize(new Dimension(185,200));
        btnoutFill.setPreferredSize(new Dimension(30,30));
        btnoutFill1.setPreferredSize(new Dimension(30,30));
    }

    //Smidig funktion för att skapa våran layout för knapparna
    private static void buttonLayout(JButton ... a) {
        for(JButton button : a){
            button.setFocusable(false);
            button.setUI(new BasicButtonUI());
        }
    }

    private static void buttonFillout(JButton ... a) {
        for(JButton button : a){
            button.setFocusable(false);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setBackground(Color.DARK_GRAY.darker());
        }
    }

    private static void labelLayout(JLabel ... a) {
        for(JLabel label : a){
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial Black", Font.PLAIN, 20));
        }
    }

    public void setColorToButton(){
        btnStartColor.setBackground(startP);
        btnEndColor.setBackground(endP);
        btnShortestRoadColor.setBackground(shortestRoad);
    }


    //Ritar ut loggans punkt färger och linjens färg
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(shortestRoad);
        g.fillPolygon(new int[]{135, 215, 210, 128}, new int[]{110,71,58, 99}, 4);
        g.setColor(startP);
        g.fillOval(120, 93, 22, 22);
        g.setColor(endP);
        g.fillOval(199, 53, 22, 22);
    }
}