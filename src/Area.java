import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Area extends JPanel{

    //Start och slutpunkter
    static Point start = new Point(50,50);
    static Point end  = new Point(950,950);

    static boolean potential = false;
    static boolean blockedAreas = false;

    static Point newP;
    public static ArrayList<Point> pointsToAdd = new ArrayList<>();

    public Area(){
        resetNodes();
        //addNode(new Point(10,10));
    }

    //Lägger till separata noder till grafen
    public void addNode(Point node){
        pointsToAdd.add(node);
        resetNodes();
    }

    //Vad vi kallar på för att rita om hela grafen när vi ändrar något
    public void resetNodes(){

        Graph.polygons.clear();
        Graph.points.clear();
        Graph.edges.clear();

        try {
            new StringCutter("area2.txt");
            if (blockedAreas)
                new StringCutter("forbidden.txt");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        generateUsablePoints();
        generateUsableEdges();

        newP = Graph.aStar(Graph.points.get(0),Graph.points.get(1));
        this.repaint();
    }

    //Genererar tillåtna punkter på kartan
    public static void generateUsablePoints(){
        Graph.points.add(start);
        Graph.points.add(end);
        Graph.points.addAll(pointsToAdd);

        for(Polygon pol : Graph.polygons){
            if(!pol.getClass().getName().equals("Forbidden")){
                Graph.points.addAll(Arrays.asList(pol.getCorners()));
            }
        }

        for(Polygon poly : Graph.polygons){
            Graph.points.removeIf(p -> Polygon.isInPoly(poly, p));
        }
    }

    //Här genererar vi kanterna och listan som sedan skapas i backend med addBranch,
    public static void generateUsableEdges(){
        for(int i = 0; i< Graph.points.size(); i++){
            for(int j = i+1; j< Graph.points.size(); j++){
                //Räknar ut längden för varje ritad kant
                double distance = Math.sqrt(Math.pow(Graph.points.get(i).x-Graph.points.get(j).x,2)+Math.pow(Graph.points.get(i).y-Graph.points.get(j).y,2));

                //Skapar kanter av två punkter
                Graph.edges.add(new Edge(Graph.points.get(i),Graph.points.get(j),distance,i));

                //Skapar länkarna/vägarna i backend
                Graph.points.get(i).addBranch(distance,Graph.points.get(j));
                Graph.points.get(j).addBranch(distance,Graph.points.get(i));
            }
            //Start heurestiken baseras på första slut punktens position
            Graph.points.get(i).h = Math.sqrt(Math.pow(Graph.points.get(i).x-Graph.points.get(1).x,2)+Math.pow(Graph.points.get(i).y-Graph.points.get(1).y,2));
        }

        //Tar bort de kanterna som är inuti byggnader eller liknande
        Graph.polygons.forEach(p -> Graph.edges.removeIf(e -> Polygon.edgeCrossesPoly(p,e)));

        ControlArea.lblPoints.setText("Points: " + Graph.points.size());
        ControlArea.lblPolygons.setText("Polygons: " + Graph.polygons.size());
        ControlArea.lblEdges.setText("Edges: " + Graph.edges.size());
    }


    //Ritar upp vägarna/byggnader och bakgrunderna
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        this.setBackground(Color.DARK_GRAY.darker());

        //Antialias för att få en mindre pixlig bild
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Vänder utritandet så det matchar stddraws koordinatsystem
        g2d.scale(0.72,0.72);
        g2d.translate(10,-5);
        g2d.rotate(-(Math.PI/2),520,520);

        //Skapar en bakgrund som är samma storlek som boundry från kartorna
        g2d.setColor(Color.darkGray);
        g2d.fillRect(StringCutter.boundary[0],StringCutter.boundary[1],1000,1000);
        Color myColour = new Color(255, 0, 0, 127);
        for(Polygon a : Graph.polygons){ //Vi loopar genom byggnader och blockerade områden

            if(a.getClass().getName().equals("Forbidden")){
                g2d.setColor(myColour);
                g2d.fillPolygon(a.yarray,a.xarray,8);
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawRect(a.yarray[0],a.xarray[0],(int)a.getLeft().length,(int) a.getBottom().length);
                g2d.setStroke(new BasicStroke(1));
            }
            else{
                g2d.setColor(Color.BLACK);
                g2d.fillPolygon(a.yarray,a.xarray,8);
            }
        }

        g2d.setColor(Color.lightGray);
        for(int i = 0; i<Graph.edges.size(); i++){ //Rita kanterna grafiskt
            g2d.drawLine((int)Graph.edges.get(i).start.getY(),(int)Graph.edges.get(i).start.getX(),(int)Graph.edges.get(i).end.getY(),(int)Graph.edges.get(i).end.getX());
        }

        g2d.setColor(Color.BLACK);

        //Skapar våran potential uträkning ifall vi trycker på knappen
        if(potential){
            try {
                Potential p = new Potential();
                p.render(g);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Beräknar kortaste pathen samt renderar den
        Graph.renderPath(start,newP,g2d);

        //Start and slutpunkt
        g2d.setColor(ControlArea.startP);
        g2d.fillOval((int)start.y-10,(int)start.x-10,20,20);
        g2d.setColor(ControlArea.endP);
        g2d.fillOval((int)end.y-10,(int)end.x-10,20,20);

        //Externa punkter som läggs till
        g2d.setColor(Color.RED);
        for(Point p: pointsToAdd){
            g2d.fillOval((int)p.y-10,(int)p.x-10,20,20);
        }
    }
}
