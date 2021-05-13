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

    public static double getvalue(Point pointS, Point pointE) {

        if(!Area.potential)
            return 0;

        //Tar två punkter och tar ut increment för x och y
        double fullpotential = 0;
        double hippityx = (pointS.x - pointE.x)/50;
        double hiippityy =(pointS.y - pointE.y)/50;

        //Skapar 50 punker med hjälp av increment värderna
        for (int i = 0; i < 50; i++) {
            fullpotential =+ potentialAt(new Point(pointS.x+hippityx*i,pointS.y+hiippityy*i));
        }

        return (fullpotential/50)*100; //Värdet multipliceras med 100 så att det är i samma storlek som längden
    }

    public void render(Graphics g){ //Skapar cirklarna

        //Cirklarna är inte en korrekt illustration av algoritmet, endast för att vi ska ungefär se dens påverkan
        Color myColour = new Color(255, 100, 20, 67);
        g.setColor(myColour);

        for(int i = 0; i<11; i++){
            g.fillOval(y-i*i*5, x-i*i*5,10+i*i*10,10+i*i*10);
        }
    }
}