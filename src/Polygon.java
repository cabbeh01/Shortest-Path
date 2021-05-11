public class Polygon {

    int[] xarray;
    int[] yarray;
    int npoint;

    int id;

    //bottom right top left
    //0     1       2   3
    Edge[] edges;

    public Polygon(Edge[] edges,int[] xarray, int[] yarray, int npoint,int id){
        this.edges = edges;
        this.id = id;
        this.xarray = xarray;
        this.yarray = yarray;
        this.npoint = npoint;
    }

    public static boolean edgeCrossesPoly(Polygon p ,Edge a){
        Edge temp1 = new Edge(p.edges[0].start,p.edges[2].start,0,0);
        Edge temp2 = new Edge(p.edges[3].start,p.edges[1].start,0,0);

        if(!Polygon.edgeNotCrossEdge(temp1,a) || !Polygon.edgeNotCrossEdge(temp2,a)){
            a.start.removeBranch(a.end);
            a.end.removeBranch(a.start);
            return true;
        }
        int count = 0;
        for(Edge e : p.edges){
            if(Polygon.edgeNotCrossEdge(e,a)){
                count++;
            }
        }

        if(count != 4){
            a.start.removeBranch(a.end);
            a.end.removeBranch(a.start);
            return true;
        }
        return false;
    }

    public static boolean isPointOnLine(Edge a, Point b) {
        return a.compareTo(b) == 0;
    }

    public static boolean edgeNotCrossEdge(Edge a, Edge b){

        if(isPointOnLine(a,b.start) && !isPointOnLine(a,b.end) || isPointOnLine(a,b.end) && !isPointOnLine(a,b.start)){
            return true;
        }

        if(a.compareTo(b.start) == 0 || a.compareTo(b.end) == 0 ){
            return true;
        }

        //Höger sida om
        if(b.compareTo(a.start) > 0 && b.compareTo(a.end) > 0 || a.compareTo(b.start) > 0 && a.compareTo(b.end) > 0){
            return true;
        }
        //Vänster sida om
        return b.compareTo(a.start) < 0 && b.compareTo(a.end) < 0 || a.compareTo(b.start) < 0 && a.compareTo(b.end) < 0;

    }

    public static boolean isInPoly(Polygon pol,Point p){

        int count = 0;
        for(Edge a : pol.edges){
            if(a.compareTo(p) > 0){
                //return false;
                count++;

                a.start.removeBranch(a.end);
                a.end.removeBranch(a.start);
            }
        }

        if(count == pol.edges.length){
            return true;
        }
        return false;
    }

    public Edge getBottom() {
        return edges[0];
    }

    public Edge getLeft() {
        return edges[3];
    }

    public Edge getRight() {
        return edges[1];
    }

    public Edge getTop() {
        return edges[2];
    }

    public Point[] getCorners(){

        Point[] points = new Point[4];
        points[0] = this.getRight().start;
        points[1] = this.getTop().start;
        points[2] = this.getLeft().start;
        points[3] = this.getBottom().start;
        return points;
    }

}