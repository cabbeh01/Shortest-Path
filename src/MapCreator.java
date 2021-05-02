import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MapCreator {


    public static void main(String[] args) {
        JFrame map = new JFrame("Map");
        map.setPreferredSize(new Dimension(1000,1000));

        map.pack();
        map.setVisible(true);
        //System.out.println(map.getHeight());

        JPanel area = new Area();

        area.setPreferredSize(new Dimension(map.getWidth(),map.getHeight()));
        map.add(area);
        System.out.println();


        map.setTitle("Shortest Path");
        map.setResizable(false);
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setVisible(true);
        //System.out.println(map);

        try {
            new StringCutter("area1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Icon to the program
        //map.setIconImage(ImageIO.read(new File("res/icon.png")));
    }

}
