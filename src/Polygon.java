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


            double distance = right.length - p.y;

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

            if (top.compareTo(p)>0 && bottom.compareTo(p)>0 && left.compareTo(p)>0 && right.compareTo(p)>0)
                return true;

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