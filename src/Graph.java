import java.awt.*;
import java.util.*;
import java.util.List;

//Algoritm för att söka kortaste vägen och print funktionen
class Graph {

    static ArrayList<Edge> edges = new ArrayList<>();
    static ArrayList<Polygon> polygons = new ArrayList<>();
    static ArrayList<Point> points = new ArrayList<>();

    public static Point aStar(Point start, Point target){ //Börjar med att skicka in var vi är och var vi vill gå
        PriorityQueue<Point> closedList = new PriorityQueue<>();
        PriorityQueue<Point> openList = new PriorityQueue<>();

        start.g = 0; //Startkostnaden för den kortaste vägen från start till nuvarande nod
        start.f = start.g + start.calcHeuristicPoint(target);   //Längden från start till slut som kan vara om det existerar

        openList.add(start);

        while(!openList.isEmpty()){
            Point n = openList.peek();
            if(n == target){
                return n;
            }

            for(Point.Branch edge : n.neighboursEd){ //Skapar en ny kant i backend som skapar förhållande mellan noderna
                Point m = edge.node;
                double totalWeight = n.g + edge.weight; //Totala vikten för den nya kanten

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calcHeuristicPoint(target);
                    openList.add(m);
                }
                else {
                    if(totalWeight < m.g){//Vägen till grannen är bättre än förgående
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calcHeuristicPoint(target);

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

        return null; //Om ingen väg kan hittas
    }

    //Våran metod för att rita vägen efter vi har har hittat den
    public static void renderPath(Point start, Point target, Graphics2D g){
        Point n = target;

        if(n==null){
            return;
        }

        List<Point> points = new ArrayList<>();

        while(n.parent != null){
            points.add(n);
            n = n.parent;
        }

        points.add(n);
        Collections.reverse(points);

        //Start punkten
        Point prev = start;

        //Går igenom en punkt itaget
        for(Point p : points){
            g.setColor(ControlArea.shortestRoad);
            g.setStroke(new BasicStroke(10));
            g.drawLine((int)prev.y,(int)prev.x,(int)p.y,(int)p.x);
            prev = p; //Sparar förra punkten för att kunna rita om
        }
    }
}