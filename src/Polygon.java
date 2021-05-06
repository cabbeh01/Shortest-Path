import jdk.dynalink.linker.LinkerServices;

public class Polygon {

    int[] xarray;
    int[] yarray;
    int npoint;

    int id;
    Edge bottom, right, top, left;

    Edge[] edges;

    //bottom right top left
    //0     1       2   3
    public Polygon(Edge[] edges,int[] xarray, int[] yarray, int npoint,int id){
        this.edges = edges;
        this.id = id;
        this.xarray = xarray;
        this.yarray = yarray;
        this.npoint = npoint;
    }

    public static boolean edgeCrossesPoly(Polygon p ,Edge a){
        Edge temp1 = new Edge(p.edges[1].start,p.edges[3].start,0,0);
        Edge temp2 = new Edge(p.edges[0].start,p.edges[2].start,0,0);

        for(Edge e : p.edges){
            if(!edgeNotCrossEdge(e,a)){
                return true;
            }
        }

        if(a.start == temp1.start && a.end == temp1.end || a.start == temp1.end && a.end == temp1.start){
            return true;
        }
        else if(a.start == temp2.start && a.end == temp2.end || a.start == temp2.end && a.end == temp2.start){
            return true;
        }
        /*for(int i = 0; i<p.edges.length; i++){
            if((p.edges[i].start == a.start && p.edges[i].end == a.end )){
                return true;
            }
        }*/

        return false;
    }
    private final static double EPSILON = 0.0000001;
    public static boolean isPointOnLine(Edge a, Point b) {
        // Move the image, so that a.first is on (0|0)
        Edge aTmp = new Edge(new Point(0, 0), new Point(
                a.end.x - a.start.x, a.end.y - a.start.y),0,0);
        Point bTmp = new Point(b.x - a.start.x, b.y - a.start.y);
        double r = a.compareTo(bTmp);
        return Math.abs(r) > EPSILON;
    }



    public static boolean edgeNotCrossEdge(Edge a, Edge b){

        if(isPointOnLine(a, b.start) || isPointOnLine(a, b.end)){
            return true;
        }
        else

            if(a.compareTo(b.start) > 0 && a.compareTo(b.end) > 0 ||
                a.compareTo(b.start) < 0 && a.compareTo(b.end) < 0){
            return false;
        }

        return true;
    }

    public static boolean isInPoly(Polygon pol,Point p){


        // double distance = right.length - p.y;

        //Vi måste kolla om den är till vänster och hur långt ifrån. Kanske något x som ska vara y
            /*if(top.compareTo(p)>0 && p.x < top.end.x && p.x > top.start.x && top.end.y - p.y > 0 && top.end.y -p.y < left.length){
                return true;
            }

            if(right.compareTo(p)>0 && p.y < right.end.y && p.y > right.start.y && right.end.x - p.x > 0 && right.end.x-p.x < top.length){
                return true;
            }

            if(left.compareTo(p)>0 && p.y < left.start.y && p.y > left.end.y && left.end.x - p.x > 0 && left.end.x - p.x < bottom.length){
                return true;
            }

            if(bottom.compareTo(p)>0 && p.x < bottom.start.x && p.x > bottom.end.x && bottom.end.y - p.y > 0 && bottom.end.y - p.y < right.length){
                return true;
            }*/

        for(Edge a : pol.edges){
            if(a.compareTo(p) <= 0){
                return false;
            }
        }
        return true;
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

    public int getId() {
        return id;
    }

    public Point[] getCorners(){
        Point[] points = new Point[4];
        points[0] = this.getRight().start;
        points[1] = this.getTop().start;
        points[2] = this.getLeft().start;
        points[3] = this.getBottom().start;
        return points;
    }


    public int[] getX() {
        return xarray;
    }

    public int[] getY() {
        return yarray;
    }

    public int getNpoint() {return npoint; }

}