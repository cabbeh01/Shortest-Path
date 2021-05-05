import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapCreator {

    public static ArrayList<Polygon> polys = new ArrayList<>();

    public static void main(String[] args) {

        StdDraw.setXscale(0,1000);
        StdDraw.setYscale(0,1000);

        JFrame map = new JFrame("Map");
        map.setPreferredSize(new Dimension(1000, 1000));

        map.pack();
        map.setVisible(true);

        JPanel area = new Area();

        area.setPreferredSize(new Dimension(map.getWidth(),map.getHeight()));
        map.add(area);

        map.setTitle("Shortest Path");
        map.setResizable(false);
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setVisible(true);
        //System.out.println(map);

        //*StdDraw.line(442,377,599,377);


        Graph g = new Graph(polys.size()*4);

        for(Edge c : Graph.a){
            for(Edge d : Graph.a){
                if(!c.equals(d)){
                    if(Polygon.edgeCrossEdge(c,d)){
                       continue;
                    }


                    g.addEdge(c.id,d.id);
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.line(c.start.x,c.end.y,d.start.x,d.end.y);
                    StdDraw.line(c.end.x,c.start.y,d.end.x,d.start.y);
                    StdDraw.line(c.start.x,c.start.y,d.end.x,d.end.y);
                    StdDraw.line(c.end.x,c.end.y,d.start.x,d.start.y);
                }
            }
        }
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



        //Icon to the program
        //map.setIconImage(ImageIO.read(new File("res/icon.png")));
    }

}
