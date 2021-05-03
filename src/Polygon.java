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
            double distance2 = right.end.x - p.x;
            double distance3 = left.end.x - p.x;
            double distance4 = bottom.end.y - p.y;

            //Vi måste kolla om den är till vänster och hur långt ifrån
            if(top.compareTo(p)>0 && distance1 > 0 && distance1 < left.length){
                return true;
            }

            if(right.compareTo(p)>0 && distance2 > 0 && distance2 < top.length){
                return true;
            }

            if(left.compareTo(p)>0 && distance3 > 0 && distance3 < bottom.length){
                return true;
            }

            if(bottom.compareTo(p)>0 && distance4 > 0 && distance4 < right.length){
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