import java.util.ArrayList;

public class Point implements Comparable<Point>{
    double x,y;
    int id;
    private static int idCounter = 0;

    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    public double h;

    public Point parent = null;

    ArrayList<Branch> neighboursEd; //Backend listan av länkar mellan noder

    public Point(double x, double y){
        this.x = x;
        this.y = y;
        this.id = idCounter++;
        this.h = 0;
        this.neighboursEd=new ArrayList<>();
    }

    public void addBranch(double weight, Point p){ //Lägger till den nyskapade branchen i backend listan
        Branch ed = new Branch(weight,p);
        neighboursEd.add(ed);
    }

    public void removeBranch(Point p){
        neighboursEd.removeIf(es -> es.node == p);
    }

    public double calcHeuristicPoint(Point point){
        return this.h;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public class Branch {
        Branch(double weight, Point node){ //För att skapa en ny branch så skapar vi en branch och en punkt den kopplar till
            this.weight = weight + Potential.getvalue(Point.this,node); //Här adderar vi potential värdet till vikten
            this.node = node;
        }

        public double weight;
        public Point node;
    }

    //Jämför f värdet mellan denna en annan punkt
    @Override
    public int compareTo(Point n) {
        return Double.compare(this.f, n.f);
    }
}