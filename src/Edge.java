public class Edge implements Comparable<Point>{
    Point start, end;
    double length;
    int id;

    public Edge(Point start, Point end,double length,int id){
        this.start = start;
        this.end = end;
        this.length = length;
        this.id = id;
    }

    @Override
    public int compareTo(Point point) {

        //x1 = start    x2 = slut   x3 = ny punkt
        double dx1 = (end.getX() - start.getX());
        double dy1 = (end.getY() - start.getY());
        double dx2 = (point.getX() - start.getX()); //x3 -x1;
        double dy2 = (point.getY() - start.getY()); //y3 -y1;

        double compare = (dx1*dy2 - dy1*dx2);
        if(compare>0){  //Större än noll så är punkten inuti, vänster sida om edgen
            return 1;
        }
        else if(compare <0){  //Mindre än noll så är punkten utanför, höger sida om edgen
            return -1;
        }
        return 0;
    }
}