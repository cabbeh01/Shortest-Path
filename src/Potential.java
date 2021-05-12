import java.awt.*;

public class Potential {

    static int x = MapCreator.dim.height/2+100;
    static int y = MapCreator.dim.height/2+125;
    static int A = 130;
    static int sigX = 130;
    static int sigY = 130;

    public static double potentialAt(Point p){
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

        for(int i = 0; i<11; i++){
            g.fillOval(y-i*i*5, x-i*i*5,10+i*i*10,10+i*i*10);
        }
        g.setColor(Color.MAGENTA);


    }
}
