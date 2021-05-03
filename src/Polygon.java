public class Polygon {
    int[] xarray;
    int[] yarray;
    int npoint;

    Edge top, right, bottom, left;

    /*public Polygon(int[] xarray, int[] yarray, int npoint){
        this.xarray = xarray;
        this.yarray = yarray;
        this.npoint = npoint;
    }*/

    public Polygon(Edge top, Edge right, Edge bottom, Edge left){
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public int[] getX() {
        return xarray;
    }

    public int[] getY() {
        return yarray;
    }

    public int getNpoint() {return npoint; }
}
