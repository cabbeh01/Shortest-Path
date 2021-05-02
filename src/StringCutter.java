import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringCutter {

    File nFile;
    Scanner inp;
    public StringCutter(String file) throws FileNotFoundException {
        nFile = new File(System.getProperty("user.dir")+"\\"+"maps"+"\\"+file);
        inp = new Scanner(nFile);

        System.out.println(System.getProperty("user.dir")+"\\"+"maps"+"\\"+file);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new StringCutter("area2.txt");
    }
}
