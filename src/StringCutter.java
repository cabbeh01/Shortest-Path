import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StringCutter {

    File nFile;
    Scanner inp;
    public static int[] boundary = new int[4];
    public ArrayList<Polygon.Polygongfx> polys = new ArrayList<>();


    public StringCutter(String file) throws FileNotFoundException {
        nFile = new File(System.getProperty("user.dir")+"\\"+"maps"+"\\"+file);
        inp = new Scanner(nFile);

        //System.out.println(System.getProperty("user.dir")+"\\"+"maps"+"\\"+file);

        String bound = inp.nextLine();

        //Pattern p = Pattern.compile("\\([^)]*\\)");
        bound = bound.replaceAll("[()]","").replaceAll("[A-Za-z:]","");
        String [] test = bound.split("[, ]");
        //String [] test = bound.split(",");


        for(String a: test){
            System.out.println(a);

        }
        //System.out.println((int)Double.parseDouble(test[1]));
        //System.out.println((int)Double.parseDouble(test[2]));
        //System.out.println((int)Double.parseDouble(test[3]));
        //System.out.println((int)Double.parseDouble(test[4]));

        System.out.println(bound);
        for(int i= 1; i<=4;i++){
            boundary[i-1] = (int)Double.parseDouble(test[i]);
        }

        //System.out.println("("+boundary[0] +","+boundary[1]+")");
        //System.out.println("("+boundary[2] +","+boundary[3]+")");

        //Kvar att fixa är att göra om bindarys till en tvådim array. För tillfälligt ligger
        //Värdena i en array


        //--Samt måste vi fixa inskanning av punkter på en polygon

        //Läser in koordinater i textfilerna och skapar Polygoner av det
        //Kan hända att vi måste skapa Kanter av det och sedan bygga Polygoner av kanterna
        while(inp.hasNext()){
            inp.nextLine();
            inp.nextLine();
            int i = 0;
            int length = 0;

            int[] xPoints = new int[8];
            int[] yPoints = new int[8];

            Point temppoint = new Point(0,0);

            Edge tempEdge;
            Edge topedge = new Edge(temppoint,temppoint,0);
            Edge rightedge = new Edge(temppoint,temppoint,0);
            Edge bottomedge = new Edge(temppoint,temppoint,0);
            Edge leftedge = new Edge(temppoint,temppoint,0);
            while(i<4) {

                //Filtrerar ner data till endast värden
                String[] dat = inp.nextLine().replaceAll("[()]", "").replaceAll(". Length: ", ",").split(",");

                xPoints[i] = (int)Double.parseDouble(dat[0]);
                yPoints[i] =(int)Double.parseDouble(dat[1]);
                xPoints[i+1] = (int)Double.parseDouble(dat[2]);
                yPoints[i+1] = (int)Double.parseDouble(dat[3]);
                length = (int)Double.parseDouble(dat[4]);

                //Vi använder din loop och går igenom en kant i taget efter loopen bygger vi polygonen innan vi går till nästa polygon
                Point startpoint = new Point(xPoints[i],yPoints[i]);
                Point endpoint = new Point(xPoints[i+1],yPoints[i+1]);

                tempEdge = new Edge(startpoint,endpoint,length);
                if (i == 0)
                    bottomedge = tempEdge;
                else if (i == 1)
                    rightedge = tempEdge;
                else if (i == 2)
                    topedge = tempEdge;
                else if (i == 3)
                    leftedge = tempEdge;

                StdDraw.line(xPoints[i],yPoints[i],xPoints[i+1],yPoints[i+1]);

                i++;
            }

            //vi behöver skapa 4 edges för varje polygon, denna loopen ^ skapar en
            //Vi behöver skapa en array med polygoner som vi kan spara de i
            Polygon.Polygonmath polygon = new Polygon.Polygonmath(bottomedge,rightedge,topedge,leftedge);
            polys.add(new Polygon.Polygongfx(xPoints,yPoints,8));
            StdDraw.setPenColor(Color.BLACK);
            double rad = StdDraw.getPenRadius();
            //Den ritar punkten vid polygon 2, tror vi måste definera hår långt åt sidorna den ska kolla
            if(polygon.isInPoly(new Point(790,300))){
                StdDraw.setPenColor(Color.red);
                StdDraw.circle(790,300,2);

            }
            StdDraw.setPenRadius(rad);
            StdDraw.setPenColor(Color.BLACK);

        }
    }

}
