import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapCreator {

    //Storleken på fönstret
    public static Dimension dim = new Dimension(1000, 800);

    //Skapar ett objekt av area
    public static Area s = new Area();

    public static void main(String[] args) throws IOException { //Vi skapar vårat fönster och lägger till vårat grafiska användar gränsnitt

        JFrame map = new JFrame("Map");
        map.setPreferredSize(dim);

        map.pack();
        map.setVisible(true);

        JPanel area = s;
        JPanel control = new ControlArea(map);


        area.setPreferredSize(new Dimension(map.getWidth(),map.getHeight()));
        map.add(area);
        control.setPreferredSize(new Dimension(250,200));
        map.add(control,BorderLayout.WEST);

        map.setTitle("Shortest Path");
        map.setResizable(false);
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setVisible(true);

        //Ikon till programmet
        map.setIconImage(ImageIO.read(new File("res/icon.png")));
    }
}
