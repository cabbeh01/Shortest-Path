public class Polygon {

    int[] xarray;
    int[] yarray;
    int npoint;

    int id;

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
        Edge temp1 = new Edge(p.edges[0].start,p.edges[2].start,0,0);
        Edge temp2 = new Edge(p.edges[3].start,p.edges[1].start,0,0);

        for(Edge e : p.edges){

            if(e.compareTo(a.start) == 0){
                return true;
            }
            if(e.compareTo(a.end) == 0){
                return true;
            }

        }
/*
        if(edgeNotCrossEdge(temp1,a)){
            return true;
        }
        if(!edgeNotCrossEdge(temp2,a)){
            return true;
        }
*/
        /*if(temp1.compareTo(a.end)> 0 || temp2.compareTo(a.end)> 0){
            return true;
        }*/
        /*
        if(temp2.compareTo(a.start)< 0 || temp2.compareTo(a.end)< 0){
            return true;
        }*/


        /*
        for(int i = 0; i<p.edges.length; i++){
            if((p.edges[i].start == a.start && p.edges[i].end == a.end )){
                return true;
            }
        }*/

        return false;
    }

    public static boolean isPointOnLine(Edge a, Point b) {
        return a.compareTo(b) == 0;
    }



    public static boolean edgeNotCrossEdge(Edge a, Edge b){

        /*if(a.compareTo(b.start) == 0 && a.compareTo(b.end) != 0 || a.compareTo(b.end) == 0 && a.compareTo(b.start) != 0){
            return true;
        }

        if(a.compareTo(b.start) > 0 && a.compareTo(b.end) > 0 && !(a.compareTo(b.start) < 0 && a.compareTo(b.end) <0 )){
            return true;
        }

        if(a.compareTo(b.start) < 0 && a.compareTo(b.end) < 0 && !(a.compareTo(b.start) > 0 && a.compareTo(b.end) >0 )){
            return true;
        }*/

        //Höger sida om
        if(b.compareTo(a.start) > 0 && b.compareTo(a.end) > 0){
            return true;
        }

        /*

        if(a.compareTo(b.start) > 0 && a.compareTo(b.end) > 0 || b.compareTo(a.start) > 0 && b.compareTo(a.end) > 0 ||
        a.compareTo(b.start) < 0 && a.compareTo(b.end) < 0 || b.compareTo(a.start) < 0 && b.compareTo(b.end) < 0){
            return true;
        }*/

        return false;
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

}