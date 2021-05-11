import java.awt.*;
import java.util.*;
import java.util.List;

//DFS Technique for undirected graph
class Graph {

    static ArrayList<Edge> a = new ArrayList<>();
    static ArrayList<Polygon> polygons = new ArrayList<>();
    static ArrayList<Point> points = new ArrayList<>();

    public static Point aStar(Point start, Point target){
        PriorityQueue<Point> closedList = new PriorityQueue<>();
        PriorityQueue<Point> openList = new PriorityQueue<>();

        start.g = 0;
        start.f = start.g + start.calculateHeuristicpoint(target);

        openList.add(start);

        while(!openList.isEmpty()){
            Point n = openList.peek();
            if(n == target){
                return n;
            }

            for(Point.Branch edge : n.neighboursEd){
                Point m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristicpoint(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristicpoint(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static void printPath(Point start, Point target, Graphics2D g){
        Point n = target;

        if(n==null)
            return;

        List<Point> ids = new ArrayList<>();
        List<Integer> id = new ArrayList<>();

        while(n.parent != null){
            ids.add(n);
            id.add(n.id);
            n = n.parent;
        }
        ids.add(n);
        id.add(n.id);
        Collections.reverse(ids);
        Collections.reverse(id);


        Point prev = start;//Start punkten
        for(Point ide : ids){
            //System.out.print("("+ide.x + "," +ide.y+" "+","+ide.h+")" +"id: " +ide.id + " ");

            g.setColor(ControlArea.shortestRoad);
            g.setStroke(new BasicStroke(10));
            g.drawLine((int)prev.y,(int)prev.x,(int)ide.y,(int)ide.x);
            prev = ide;//Sparar förra punkten
        }
        System.out.println("");
    }
}