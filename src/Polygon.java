public class Polygon {

    public static class Polygonmath {

        Edge bottom, right, top, left;

        public Polygonmath(Edge bottom, Edge right, Edge top, Edge left){
            this.bottom = bottom;
            this.right = right;
            this.top = top;
            this.left = left;
        }

        public boolean isInPoly(Point p){

            //Vi måste kolla om den är till vänster och hur långt ifrån. Kanske något x som ska vara y
            if(top.compareTo(p)>0 && p.x < top.end.x && p.x > top.start.x){
                return true;
            }

            if(right.compareTo(p)>0 && p.x < right.end.x && p.x > right.start.x){
                return true;
            }

            if(left.compareTo(p)>0 && p.x < left.end.x && p.x > left.start.x){
                return true;
            }

            if(bottom.compareTo(p)>0 && p.x < bottom.end.x && p.x > bottom.start.x){
                return true;
            }

            return false;
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