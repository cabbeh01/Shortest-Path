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
/*
        if(temp1.compareTo(a.start) == 0 && temp1.compareTo(a.end) == 0){
            return true;
        }
        if(temp2.compareTo(a.start) == 0 && temp2.compareTo(a.end) == 0){
            return true;
        }*/
        if(!Polygon.edgeNotCrossEdge(temp1,a)){
            return true;
        }
        if(!Polygon.edgeNotCrossEdge(temp2,a)){
            return true;
        }

        int count = 0;
        for(Edge e : p.edges){

            if(Polygon.edgeNotCrossEdge(e,a)){
                count++;
            }
        }
        if(Polygon.isInPoly(p,a.start)){
            return true;
        }
        if(Polygon.isInPoly(p,a.end)){
            return true;
        }


        //System.out.println(count);

        //System.out.println(count);
        if(count == 4){
            return false;
        }






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

        return true;
    }

    public static boolean isPointOnLine(Edge a, Point b) {
        return a.compareTo(b) == 0;
    }



    public static boolean edgeNotCrossEdge(Edge a, Edge b){

        if(isPointOnLine(b,a.start) && !isPointOnLine(b,a.end) || isPointOnLine(b,a.end) && !isPointOnLine(b,a.start)){
            return true;
        }

        if(isPointOnLine(a,b.start) && !isPointOnLine(a,b.end) || isPointOnLine(a,b.end) && !isPointOnLine(a,b.start)){
            return true;
        }

        if(a.compareTo(b.start) == 0){
            return true;
        }

        if(a.compareTo(b.end) == 0){
            return true;
        }
        /*
        if(a.compareTo(b.start) == 0 && a.compareTo(b.end) != 0 || a.compareTo(b.end) == 0 && a.compareTo(b.start) != 0){
            return true;
        }

        if(a.compareTo(b.start) > 0 && a.compareTo(b.end) > 0 && !(a.compareTo(b.start) < 0 && a.compareTo(b.end) <0 )){
            return true;
        }

        if(a.compareTo(b.start) < 0 && a.compareTo(b.end) < 0 && !(a.compareTo(b.start) > 0 && a.compareTo(b.end) >0 )){
            return true;
        }*/

        //Höger sida om
        if(b.compareTo(a.start) > 0 && b.compareTo(a.end) > 0 || a.compareTo(b.start) > 0 && a.compareTo(b.end) > 0){
            return true;
        }
        if(b.compareTo(a.start) < 0 && b.compareTo(a.end) < 0 || a.compareTo(b.start) < 0 && a.compareTo(b.end) < 0){
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