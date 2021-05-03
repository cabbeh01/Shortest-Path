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

            //Vi måste slå ut negativa tal
            double distance1 = top.end.y - p.y;
            double distance2 = right.end.y - p.y;
            double distance3 = left.end.y - p.y;
            double distance4 = bottom.end.y - p.y;

            //Vi måste kolla om den är till vänster och hur långt ifrån
            if(top.compareTo(p)>0 && left.length > distance1 ){
                return false;
            }

            if(right.compareTo(p)>0 && top.length > distance2){
                return false;
            }

            if(left.compareTo(p)>0 && bottom.length > distance3){
                return false;
            }

            if(bottom.compareTo(p)>0 && right.length > distance4){
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