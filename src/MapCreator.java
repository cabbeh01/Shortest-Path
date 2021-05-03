import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MapCreator {

    public static StringCutter SC;

    public static void main(String[] args) {
        /*JFrame map = new JFrame("Map");
        map.setPreferredSize(new Dimension(1000,1000));

        map.pack();
        map.setVisible(true);
        //System.out.println(map.getHeight());


        JPanel area = new Area();

        area.setPreferredSize(new Dimension(map.getWidth(),map.getHeight()));
        map.add(area);

        map.setTitle("Shortest Path");
        map.setResizable(false);
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setVisible(true);*/
        //System.out.println(map);

        StdDraw.setXscale(0,1000);
        StdDraw.setYscale(0,1000);

        //*StdDraw.line(442,377,599,377);

        try {
            new StringCutter("testarea.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Icon to the program
        //map.setIconImage(ImageIO.read(new File("res/icon.png")));
    }

}
