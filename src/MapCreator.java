import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;

public class MapCreator {
    public static void main(String[] args) {
        JFrame map = new JFrame("Map");
        map.setPreferredSize(new Dimension(1000,1000));

        map.add(new Map());
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        map.pack();
        map.setVisible(true);
        //System.out.println(map);

        try {
            new StringCutter("area1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
class Map extends JPanel {
    public Map() {
        this.setBackground(Color.white);


    }
}