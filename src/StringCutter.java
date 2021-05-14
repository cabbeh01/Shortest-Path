import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringCutter {

    File nFile;
    Scanner inp;

    //Lagrar boundarykoordinaterna
    public static int[] boundary = new int[4];

    //Läser av en textfil och lägger in polygonerna/förbjudna områden i grafens lista
    public StringCutter(String file) throws FileNotFoundException {
        nFile = new File(System.getProperty("user.dir")+"\\"+"maps"+"\\"+file);
        inp = new Scanner(nFile);

        String bound = inp.nextLine();//Första raden i textfilen som är storleken på kartan
        String boundTitle = bound;

        if(!boundTitle.contains("Forbidden")){ //Vi gör detta endast för kartan för där finns border
            bound = bound.replaceAll("[()]","").replaceAll("[A-Za-z:]","");
            String [] boundArr = bound.split("[, ]");
            for(int i= 1; i<=4;i++){
                boundary[i-1] = (int)Double.parseDouble(boundArr[i]);
            }
        }

        int idEdge = 0;
        while(inp.hasNext()){

            int id = Integer.parseInt(inp.nextLine().replaceAll("Polygon: ",""));
            inp.nextLine();
            int i = 0;
            int length;

            int[] xPoints = new int[8];
            int[] yPoints = new int[8];

            Edge[] edges = new Edge[4];

            while(i<4) {//Polygon med fyra kanter

                //Filtrerar ner data till endast värden
                String[] dat = inp.nextLine().replaceAll("[()]", "").replaceAll(". Length: ", ",").split(",");

                xPoints[i] = (int)Double.parseDouble(dat[0]);
                yPoints[i] =(int)Double.parseDouble(dat[1]);
                xPoints[i+1] = (int)Double.parseDouble(dat[2]);
                yPoints[i+1] = (int)Double.parseDouble(dat[3]);
                length = (int)Double.parseDouble(dat[4]);

                //Vi använder loopen för att skapa punkterna, sedan kanterna och efter loopen skapar vi polygonen
                Point startpoint = new Point(Double.parseDouble(dat[0]),Double.parseDouble(dat[1]));
                Point endpoint = new Point(Double.parseDouble(dat[2]),Double.parseDouble(dat[3]));

                edges[i] = new Edge(startpoint,endpoint,length,idEdge);

                i++;
                idEdge++;
            }

            //Här skapar vi polygonen från edges listan och punkterna
            if(boundTitle.contains("Forbidden")){
                Forbidden polygon = new Forbidden(edges,xPoints,yPoints,8,id);
                Graph.polygons.add(polygon);
            }
            else{
                Polygon polygon = new Polygon(edges,xPoints,yPoints,8,id);
                Graph.polygons.add(polygon);
            }
        }
    }
}
