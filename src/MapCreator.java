import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MapCreator {

    //public static ArrayList<Polygon> polys = new ArrayList<>();
    public static Dimension dim = new Dimension(1000, 800);

    public static void main(String[] args) throws IOException {

        JFrame map = new JFrame("Map");
        map.setPreferredSize(dim);


        map.pack();
        map.setVisible(true);

        JPanel area = new Area();
        JPanel control = new ControlArea(map);

        area.setPreferredSize(new Dimension(map.getWidth(),map.getHeight()));
        map.add(area);
        control.setPreferredSize(new Dimension(250,200));
        map.add(control,BorderLayout.EAST);

        map.setTitle("Shortest Path");
        map.setResizable(false);
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setVisible(true);

        //Icon to the program
        map.setIconImage(ImageIO.read(new File("res/icon.png")));
        //System.out.println(map);

        //*StdDraw.line(442,377,599,377);
/*
        StdDraw.setXscale(0,1000);
        StdDraw.setYscale(0,1000);*/



        /*
        //Drawing part which draws the StdDraw stuff
        for(Polygon p : Graph.ap){

            for(int i = 0; i<p.edges.length; i++){
                StdDraw.line(p.xarray[i],p.yarray[i],p.xarray[i+1],p.yarray[i+1]);
            }

            StdDraw.setPenColor(Color.BLACK);
            double rad = StdDraw.getPenRadius();
            //Den ritar punkten vid polygon 2, tror vi måste definera hår långt åt sidorna den ska kolla
            if(Polygon.isInPoly(p,new Point(400,470))){
                StdDraw.setPenColor(Color.red);
                StdDraw.circle(400,470,2);
            }
            if(Polygon.isInPoly(p,new Point(530,400))){
                StdDraw.setPenColor(Color.blue);
                StdDraw.circle(530,400,2);
            }
            if(Polygon.isInPoly(p,new Point(500,600))){
                StdDraw.setPenColor(Color.cyan);
                StdDraw.circle(500,600,2);
            }
            if(Polygon.isInPoly(p,new Point(500,300))){
                StdDraw.setPenColor(Color.magenta);
                StdDraw.circle(500,300,2);
            }
            StdDraw.setPenRadius(rad);
            StdDraw.setPenColor(Color.BLACK);
        }
*/
/*

        for(Edge c : Graph.a){
            for(Edge d : Graph.a){
                if(!c.equals(d)){

                    g.addEdge(c.id,d.id);
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.line(c.start.x,c.start.y,d.start.x,d.start.y);
                }
            }
        }*/
/*
        int pointid = 0;
        for(Polygon c : Graph.ap){
            for(Polygon d : Graph.ap){
                if(!c.equals(d)){
                    for(Point a : c.getCorners()){
                        if(!Polygon.isInPoly(c,a)){
                            a.setId(pointid);
                            Graph.points.add(a);
                            pointid++;
                        }
                    }
                }
            }
        }

        int edgeid = 0;
        for(Point c : Graph.points){
            for(Point d : Graph.points){
                if(!c.equals(d)){
                    Edge temp = new Edge(c,d,0,edgeid);
                    Graph.a.add(temp);
                    edgeid++;
                }
            }
        }*/

        /*
        for(Edge c : Graph.a){
            for(Edge d : Graph.a){
                if(!c.equals(d)){
                    if(!Polygon.edgeCrossEdge(c,d)){
                        g.addEdge(c.id,d.id);
                        StdDraw.setPenColor(Color.RED);
                        StdDraw.line(c.start.x,c.start.y,d.start.x,d.start.y);
                    }
                }
            }
        }*/

    }

}
