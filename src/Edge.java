public class Edge implements Comparable<Point>{
    Point start;
    Point end;
    //Object direction;

    public Edge(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }


    @Override
    public int compareTo(Point point) {

        //x1 = start
        //x2 = slut

        //x3 = ny punkt

        double dx1 = (end.getX() - start.getY());
        double dy1 = (end.getY() - start.getY());
        double dx2 = (point.getX() - start.getX()); //x3 -x1;
        double dy2 = (point.getY() - start.getY()); //y3 -y1;
        double compare = (dx1*dy2 - dy1*dx2);
        if(compare>0){
            return 1;
        }
        else if(compare <0){
            return -1;
        }
        return 0;
    }
}

