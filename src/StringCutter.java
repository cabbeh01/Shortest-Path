import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class StringCutter {

    File nFile;
    Scanner inp;
    public static int[] boundary = new int[4];
    public static Polygon[] polys = new Polygon[30];

    public StringCutter(String file) throws FileNotFoundException {
        nFile = new File(System.getProperty("user.dir")+"\\"+"maps"+"\\"+file);
        inp = new Scanner(nFile);

        System.out.println(System.getProperty("user.dir")+"\\"+"maps"+"\\"+file);
    }

    public static void main(String[] args) throws FileNotFoundException {
        StringCutter sc = new StringCutter("area2.txt");

        String bound = sc.inp.nextLine();

        //Pattern p = Pattern.compile("\\([^)]*\\)");
        bound = bound.replaceAll("[()]","").replaceAll("[A-Za-z:]","");
        String [] test = bound.split("[, ]");
        //String [] test = bound.split(",");


        for(String a: test){
            System.out.println(a);

        }
        System.out.println((int)Double.parseDouble(test[1]));
        System.out.println((int)Double.parseDouble(test[2]));
        System.out.println((int)Double.parseDouble(test[3]));
        System.out.println((int)Double.parseDouble(test[4]));

        System.out.println(bound);
        for(int i= 1; i<=4;i++){
            boundary[i-1] = (int)Double.parseDouble(test[i]);
        }

        System.out.println("("+boundary[0] +","+boundary[1]+")");
        System.out.println("("+boundary[2] +","+boundary[3]+")");

        //Kvar att fixa är att göra om bindarys till en tvådim array. För tillfälligt ligger
        //Värdena i en array


        //Samt måste vi fixa inskanning av punkter på en polygon



    }
}
