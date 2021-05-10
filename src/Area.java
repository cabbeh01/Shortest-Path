import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Area extends JPanel {
    //Creating random object
    Random r = new Random();

    //StringCutter SC;
    Graph g;

    //Points for the start and end-point
    static Point start = new Point(50,50);
    static Point end  = new Point(950,950);

    public Area(){
        try {
            new StringCutter("area1.txt");
            new StringCutter("forbidden.txt");

            generateUsablePoints();
            generateUsableEdges();
            //this.repaint();
            //g = new Graph(Graph.a.size()*4);
            //addtoGraph();
            //g.DFS(0);
           // System.out.println(Arrays.toString(g.visiting.toArray()));
            //Graph.aStar(start,end);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void resetNodes(){
        Graph.points.clear();
        Graph.a.clear();
        generateUsablePoints();
        generateUsableEdges();
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

                    //Räknarutlängden för varje ritad kant
                    double distance = Math.sqrt(Math.pow(Graph.points.get(i).x-Graph.points.get(j).x,2)+Math.pow(Graph.points.get(i).y-Graph.points.get(j).y,2));
                    Edge newedge =new Edge(Graph.points.get(i),Graph.points.get(j),distance,i);
                    //Graph.points.get(i).addneighbours(Graph.points.get(j));
                    //Graph.points.get(j).addneighbours(Graph.points.get(i));
                    Graph.points.get(i).addBranch(distance,Graph.points.get(j));
                    Graph.points.get(j).addBranch(distance,Graph.points.get(i));
                    Graph.a.add(newedge);
                }
            }
        }

        System.out.println("Antalet punkter: " + Graph.points.size());
        System.out.println("Antalet Polygoner: " + Graph.polygons.size());

        for(Polygon poly : Graph.polygons){
            Graph.a.removeIf(e -> Polygon.edgeCrossesPoly(poly, e));
        }

        System.out.println("Antalet edges: " + Graph.a.size());
        System.out.println(Graph.a.get(40).length + " px");
    }




    //The paint component to draw the panel
    @Override
    public void paint(Graphics g) {
        super.paint(g);


        Graphics2D g2d = (Graphics2D)g;

        this.setBackground(Color.WHITE);

        //Enabling antialias to get a smoother experience
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
        g2d.scale(0.72,0.72);
        g2d.rotate(-(Math.PI/2),520,520);


        for(Polygon a : Graph.polygons){
            if(a.getClass().getName().equals("Forbidden")){
                g2d.setColor(Color.MAGENTA);
            }
            else{
                g2d.setColor(Color.BLACK);
            }
            g2d.fillPolygon(a.yarray,a.xarray,8);
        }



        //Drawing points
        g2d.setColor(Color.GREEN);

        g2d.fillOval((int)Graph.points.get(0).getY()-5,(int)Graph.points.get(0).getX()-5,10,10);
        g2d.fillOval((int)Graph.points.get(1).getY()-5,(int)Graph.points.get(1).getX()-5,10,10);


        g2d.setColor(Color.MAGENTA);



        /*for(Point p2 : Graph.points.get(0).getNeighbours()){
            g2d.fillOval((int)p2.getY()-5,(int)p2.getX()-5,10,10);
        }*/

        /*
        g2d.setColor(Color.MAGENTA);
        for(int i = 0; i<Graph.points.size(); i++){
            g2d.fillOval((int)Graph.points.get(i).getY()-5,(int)Graph.points.get(i).getX()-5,10,10);
        }*/



        g2d.setColor(Color.BLUE);
        /*for(Edge e1 : Graph.a){
            g2d.drawLine((int)e1.start.getY(),(int)e1.start.getX(),(int)e1.end.getY(),(int)e1.end.getX());
        }*/


        for(int i = 0; i<Graph.a.size(); i++){ //DEBUGA KANTER

            if(i == 40){
                g2d.setColor(Color.GREEN);
            }
            else
                g2d.setColor(Color.BLUE);


            g2d.drawLine((int)Graph.a.get(i).start.getY(),(int)Graph.a.get(i).start.getX(),(int)Graph.a.get(i).end.getY(),(int)Graph.a.get(i).end.getX());
            //g2d.fillOval((int)Graph.points.get(i).getY()-5,(int)Graph.points.get(i).getX()-5,10,10);
        }

        g2d.setColor(Color.BLACK);

        //Potential
        try {
            Potential pe = new Potential(20,20,20,20,20);
            pe.render(g);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Point newP = Graph.aStar(Graph.points.get(0),Graph.points.get(1));
        Graph.printPath(start,newP,g2d);


        //Start and endpoint
        g2d.setColor(Color.RED);
        g2d.fillOval((int)start.y-10,(int)start.x-10,20,20);
        g2d.setColor(Color.GREEN);
        g2d.fillOval((int)end.y-10,(int)end.x-10,20,20);
    }



    /*
    //Variable to see if the game is paused
    public boolean paused = false;


    //This method is the heart of the program.
    //It loops through each frame to update simulation and calls the redraw function
    public void gameloop(){
        //Constants
        final int TICKSPERSECONDS = 64;
        final int SKIPTICK = 1000 / TICKSPERSECONDS;
        final int MAXFRAMESKIP = 10;

        //Getting the current time in milliseconds
        double ngt = System.currentTimeMillis();

        //Amount of iterations
        int iterations;

        //Infinity loop
        while (true) {
            iterations = 0;
            //Checks so the time is running forward and checks so the iteration is lower than the frameskip
            while (System.currentTimeMillis() > ngt && iterations < MAXFRAMESKIP) {
                //Skips to redraw if the simulation is paused
                if(paused){
                    continue;
                }
                else{
                    //Checking collision

                    //Repaints
                    this.repaint();

                }
                //Adding the SkipTicks
                ngt += SKIPTICK;

                //Count +1 on iteration
                iterations++;
            }
        }
    }
*/
}
