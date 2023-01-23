package kro.dodoworld.survivalweapons.util;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.FlowLayout;
/**
 * Wait, How did You found this class?
 */
public class Main extends JFrame {

    private Main(){
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("플러그인 적용 방법");

        setLayout(new FlowLayout());
        JLabel label = new JLabel("이 파일을 plugins 폴더에 넣으세요!");

        this.add(label);
        setVisible(true);
    }
    public static void main(String[] args){
        new Main();
    }
}