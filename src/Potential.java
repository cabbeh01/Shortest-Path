import java.awt.*;

public class Potential {

    int x;
    int y;
    int A;
    int sigX;
    int sigY;

    Point[] five = new Point[5];

    public Potential(int x, int y, int A, int sigX, int sigY,Area window) throws Exception {
        if(sigX == 0 || sigY == 0){
            throw new Exception("Zero isn't valid number");
        }

        this.x = x;
        this.y = y;
        this.A = A;
        this.sigX = sigX;
        this.sigY = sigY;



        //(int)Math.sqrt(potentialAt(new Point(MapCreator.dim.height/2-100-i*100
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


        Color myColour = new Color(255, 100, 20, 67);
        g.setColor(myColour);
        //g.fillOval(MapCreator.dim.height/2-100, MapCreator.dim.height/2+25-100,400,400);
        g.setColor(myColour);

        //g.fillOval(MapCreator.dim.height/2, MapCreator.dim.height/2+25,200,200);

        for(int i = 0; i<10; i++){
            g.fillOval(y-i*i*5, x-i*i*5,10+i*i*10,10+i*i*10);
        }
        g.setColor(Color.MAGENTA);


    }
}
