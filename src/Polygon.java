public class Polygon {

    public static class Polygonmath {

        Edge top, right, bottom, left;

        public Polygonmath(Edge top, Edge right, Edge bottom, Edge left){
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.left = left;
        }

        public boolean isInPoly(Point p){

            if(top.compareTo(p)>0){
                return false;
            }

            if(right.compareTo(p)>0){
                return false;
            }

            if(left.compareTo(p)<0){
                return false;
            }

            if(bottom.compareTo(p)<0){
                return false;
            }

            return true;
        }
    }

    public static class Polygongfx {

        int[] xarray;
        int[] yarray;
        int npoint;

        public Polygongfx(int[] xarray, int[] yarray, int npoint) {
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
}