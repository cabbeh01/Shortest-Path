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

}
