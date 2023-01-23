package kro.dodoworld.survivalweapons.util;

import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;

/**
 * Wait, How did You found this class?
 */
public class Main extends JFrame {

    private Main(){
        JLabel label = new JLabel("그냥 플러그인 폴더에 넣으시면 됩니다!");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("플러그인 적용 방법");
        setLayout(new FlowLayout());

        this.add(label);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }
    public static void main(String[] args){
        new Main();
        String fonts[]
                = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
        }

    }
}