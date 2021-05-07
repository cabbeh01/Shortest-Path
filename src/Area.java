import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Area extends JPanel {
    //Creating random object
    Random r = new Random();

    StringCutter SC;

    //Points for the start and end-point
    static Point start = new Point(50,50);
    static Point end  = new Point(950,950);

    public Area(){
        try {
            SC = new StringCutter("area2.txt");
            generateUsablePoints();
            generateUsableEdges();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Denna metod gör att vi hittar hörnpunkterna på byggnaderna utifrån de
    //koordinater som vi har
    public void generateUsablePoints(){
        for(Polygon pol : Graph.ap){
            Graph.points.addAll(Arrays.asList(pol.getCorners()));
        }
        /*for(Polygon poly : Graph.ap){
            Graph.points.removeIf(p -> Polygon.isInPoly(poly, p));
        }*/
        this.repaint();
    }

    public void generateUsableEdges(){
        ArrayList<Edge> edges = new ArrayList<>();
        //ArrayList<Edge> edges2 = new ArrayList<>();
        int id = 0;
        for(Point a : Graph.points){
            for(Point b: Graph.points){
                if(!a.equals(b)){
                    edges.add(new Edge(a,b,0,id++));
                }
            }
        }

        System.out.println("Antalet punkter: " + Graph.points.size());
        Graph.a.clear();
        System.out.println("Antalet Polygoner: " + Graph.ap.size());


        for(Edge e : edges){
            if(!Graph.a.contains(e)){
                Graph.a.add(e);
            }
        }


        for(Edge e : edges){
            for(Polygon poly : Graph.ap){
                if(Polygon.edgeCrossesPoly(poly,e)){
                    Graph.a.remove(e);
                }
            }
        }

        this.repaint();
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

    //The paint component to draw the panel
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        this.setBackground(Color.WHITE);

        //Enabling antialias to get a smoother experience
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
        g2d.scale(0.72,0.72);
        g2d.rotate(-(Math.PI/2),520,520);


        for(Polygon a : Graph.ap){
            g2d.fillPolygon(a.yarray,a.xarray,8);
        }

        g2d.setColor(Color.RED);
        g2d.fillOval((int)start.x,(int)start.y,20,20);
        g2d.setColor(Color.GREEN);
        g2d.fillOval((int)end.x,(int)end.y,20,20);



        g2d.setColor(Color.MAGENTA);

        /*
        for(Polygon p : Graph.ap){
            for(int i = 0; i< p.getCorners().length; i++){
                g2d.fillOval(p.getY()[i]-5,p.getX()[i]-5,10,10);
            }
        }*/


        /*
        for(Point p : Graph.points){
            g2d.fillOval((int)p.getY()-5,(int)p.getX()-5,10,10);
        }*/


        g2d.setColor(Color.BLUE);
        for(Edge e1 : Graph.a){
            g2d.drawLine((int)e1.start.getY(),(int)e1.start.getX(),(int)e1.end.getY(),(int)e1.end.getX());
        }

        System.out.println("Antalet edges: " + Graph.a.size());

        /*int s = r.nextInt() * 5;
        if(s>2.5){
            g2d.setColor(Color.blue);
        }
        else{
            g2d.setColor(Color.red);
        }*/
        /*
        for(Edge c : Graph.a){
            for(Edge d : Graph.a){
                if(!c.equals(d)){
                    if(!Polygon.edgeCrossEdge(c,d)){
                        continue;
                    }
                    if(Polygon.edgeCrossesPoly(Graph.ap.get(3), d)){
                        continue;
                    }
                    g2d.drawLine((int)c.start.y,(int)c.start.x,(int)d.start.y,(int)d.start.x);
                    //g2d.drawLine((int)c.start.y,(int)c.end.x,(int)d.start.y,(int)d.end.x);

                }
            }
        }*/

        g2d.setColor(Color.BLACK);
    }
}
