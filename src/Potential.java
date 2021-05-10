import java.awt.*;
import java.util.regex.Pattern;

public class Potential {

    int x;
    int y;
    int A;
    int sigX;
    int sigY;

    public Potential(int x, int y, int A, int sigX, int sigY) throws Exception {
        if(sigX == 0 || sigY == 0){
            throw new Exception("Zero isn't valid number");
        }

        this.x = x;
        this.y = y;
        this.A = A;
        this.sigX = sigX;
        this.sigY = sigY;
    }
    public double potentialAt(Point p){
        return A*Math.exp(-((Math.pow(p.x-x,2)/(2*Math.pow(sigX,2)))+(Math.pow(p.y-y,2)/(2*Math.pow(sigY,2)))));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void render(Graphics g){
/*
        g.setColor(Color.GREEN);
        g.fillOval(MapCreator.dim.height/2-100, MapCreator.dim.height/2+25-100,400,400);
        g.setColor(Color.MAGENTA);

        g.fillOval(MapCreator.dim.height/2, MapCreator.dim.height/2+25,200,200);*/
    }
}
