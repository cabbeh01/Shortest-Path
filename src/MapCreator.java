import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MapCreator {

    //public static ArrayList<Polygon> polys = new ArrayList<>();
    public static Dimension dim = new Dimension(1000, 800);

    public static Area s = new Area();
    public static void main(String[] args) throws IOException {

        JFrame map = new JFrame("Map");
        map.setPreferredSize(dim);


        map.pack();
        map.setVisible(true);

        JPanel area = s;
        JPanel control = new ControlArea(map);

        area.setPreferredSize(new Dimension(map.getWidth(),map.getHeight()));
        map.add(area);
        control.setPreferredSize(new Dimension(250,200));
        map.add(control,BorderLayout.EAST);

        map.setTitle("Shortest Path");
        map.setResizable(false);
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setVisible(true);

        //Icon to the program
        map.setIconImage(ImageIO.read(new File("res/icon.png")));
        //System.out.println(map);
    }
}
