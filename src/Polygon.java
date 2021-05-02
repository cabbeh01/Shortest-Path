public class Polygon {
    int[] xarray;
    int[] yarray;
    int npoint;

    public Polygon(int[] xarray, int[] yarray, int npoint){
        this.xarray = xarray;
        this.yarray = yarray;
        this.npoint = npoint;
    }

    public int[] getX() {
        return xarray;
    }

    public int[] getY() {
        return yarray;
    }

    public int getNpoint() {return npoint; }
}
