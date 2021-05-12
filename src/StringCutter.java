import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringCutter {

    File nFile;
    Scanner inp;
    //public static int[] boundary = new int[4];

    public StringCutter(String file) throws FileNotFoundException {
        nFile = new File(System.getProperty("user.dir")+"\\"+"maps"+"\\"+file);
        inp = new Scanner(nFile);

        String bound = inp.nextLine();
        String boundTitle = bound;

        //Gettinge the bound
        /*
        bound = bound.replaceAll("[()]","").replaceAll("[A-Za-z:]","");
        String [] boundArr = bound.split("[, ]");

        for(String a: boundArr){
            System.out.println(a);
        }

        System.out.println(bound);
        for(int i= 1; i<=4;i++){
            boundary[i-1] = (int)Double.parseDouble(boundArr[i]);
        }*/

        int idEdge = 0;
        while(inp.hasNext()){

            int id = Integer.parseInt(inp.nextLine().replaceAll("Polygon: ",""));
            inp.nextLine();
            int i = 0;
            int length;

            int[] xPoints = new int[8];
            int[] yPoints = new int[8];

            Edge[] edges = new Edge[4];

            while(i<4) {

                //Filtrerar ner data till endast värden
                String[] dat = inp.nextLine().replaceAll("[()]", "").replaceAll(". Length: ", ",").split(",");

                xPoints[i] = (int)Double.parseDouble(dat[0]);
                yPoints[i] =(int)Double.parseDouble(dat[1]);
                xPoints[i+1] = (int)Double.parseDouble(dat[2]);
                yPoints[i+1] = (int)Double.parseDouble(dat[3]);
                length = (int)Double.parseDouble(dat[4]);

                //Vi använder din loop och går igenom en kant i taget efter loopen bygger vi polygonen innan vi går till nästa polygon
                Point startpoint = new Point(Double.parseDouble(dat[0]),Double.parseDouble(dat[1]));
                Point endpoint = new Point(Double.parseDouble(dat[2]),Double.parseDouble(dat[3]));

                edges[i] = new Edge(startpoint,endpoint,length,idEdge);

                i++;
                idEdge++;
            }

            //MapCreator.polys.add(polygon);
            //Graph.a.addAll(Arrays.asList(polygon.edges));
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
