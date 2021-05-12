import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Area extends JPanel {

    //Points for the start and end-point
    static Point start = new Point(50,50);
    static Point end  = new Point(950,950);
    static boolean potential = false;

    public Area(){
        try {
            new StringCutter("area1.txt");
            //new StringCutter("forbidden.txt");

            generateUsablePoints();
            generateUsableEdges();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void resetNodes(){
        Graph.polygons.clear();
        Graph.points.clear();
        Graph.edges.clear();

        try {
            new StringCutter("area4.txt");
            if (blockedareas)
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

    //Denna metod gör att vi hittar hörnpunkterna på byggnaderna utifrån de
    //koordinater som vi har
    public static void generateUsablePoints(){
        Graph.points.add(start);
        Graph.points.add(end);
        for(Polygon pol : Graph.polygons){
           if(!pol.getClass().getName().equals("Forbidden")){
               Graph.points.addAll(Arrays.asList(pol.getCorners()));
            }
        }

        for(Polygon poly : Graph.polygons){
            Graph.points.removeIf(p -> Polygon.isInPoly(poly, p));
        }

    }

    public static void generateUsableEdges(){
        for(int i = 0; i< Graph.points.size(); i++){
            for(int j = i+1; j< Graph.points.size(); j++){
                if(i != j){

                    //Räknar ut längden för varje ritad kant
                    double distance = Math.sqrt(Math.pow(Graph.points.get(i).x-Graph.points.get(j).x,2)+Math.pow(Graph.points.get(i).y-Graph.points.get(j).y,2));
                    Edge newedge = new Edge(Graph.points.get(i),Graph.points.get(j),distance,i);
                    //Graph.points.get(i).addneighbours(Graph.points.get(j));
                    //Graph.points.get(j).addneighbours(Graph.points.get(i));
                    Graph.points.get(i).addBranch(distance,Graph.points.get(j));
                    Graph.points.get(j).addBranch(distance,Graph.points.get(i));
                    Graph.edges.add(newedge);
                }
            }
        }

        System.out.println("Antalet punkter: " + Graph.points.size());
        System.out.println("Antalet Polygoner: " + Graph.polygons.size());

        Graph.polygons.forEach(p -> Graph.edges.removeIf(e -> Polygon.edgeCrossesPoly(p,e)));

        /*for(Polygon poly : Graph.polygons){ //Ta bort grannarna för e
            Graph.a.removeIf(e -> Polygon.edgeCrossesPoly(poly, e));
        }*/

        System.out.println("Antalet edges: " + Graph.edges.size());
    }


    //The paint component to draw the panel
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        this.setBackground(Color.WHITE);

        //Enabling antialias to get a smoother experience
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.scale(0.72,0.72);
        g2d.rotate(-(Math.PI/2),520,520);


        for(Polygon a : Graph.polygons){

            if(a.getClass().getName().equals("Forbidden")){
                //int alpha = 127; // 50% transparent
                Color myColour = new Color(255, 0, 0, 127);
                g2d.setColor(myColour);
                g2d.fillPolygon(a.yarray,a.xarray,8);
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawRect(a.yarray[0],a.xarray[0],(int)a.getRight().length,(int) a.getBottom().length);
                g2d.setStroke(new BasicStroke(1));

            }
            else{
                g2d.setColor(Color.BLACK);
                g2d.fillPolygon(a.yarray,a.xarray,8);
            }

        }



        //g2d.setColor(Color.BLUE);
       /* for(Edge e1 : Graph.a){
            g2d.drawLine((int)e1.start.getY(),(int)e1.start.getX(),(int)e1.end.getY(),(int)e1.end.getX());
        }*/

        g2d.setColor(Color.BLUE);
        for(int i = 0; i<Graph.edges.size(); i++){ //DEBUGA KANTER
            g2d.drawLine((int)Graph.edges.get(i).start.getY(),(int)Graph.edges.get(i).start.getX(),(int)Graph.edges.get(i).end.getY(),(int)Graph.edges.get(i).end.getX());
        }

        g2d.setColor(Color.BLACK);

        //Potential
        if(potential){
            try {
                //Potential pe = new Potential(MapCreator.dim.height/2+100,MapCreator.dim.height/2+125,100,5,5, this);
                Potential p = new Potential();
                //resetNodes();
                //this.repaint();
                p.render(g);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Graph.printPath(start,newP,g2d);

        //Start and endpoint
        g2d.setColor(ControlArea.startP);
        g2d.fillOval((int)start.y-10,(int)start.x-10,20,20);
        g2d.setColor(ControlArea.endP);
        g2d.fillOval((int)end.y-10,(int)end.x-10,20,20);
    }

}
