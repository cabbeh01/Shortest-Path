public class Point {
    double x;
    double y;
    int id;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }


    public Point(double x, double y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static class Branch {
        Branch(double weight, Point node){
            this.weight = weight;
            this.node = node;
        }

        public double weight;
        public Point node;
    }


    @Override
    public int compareTo(Point n) {
        return Double.compare(this.f, n.f);
    }

}
