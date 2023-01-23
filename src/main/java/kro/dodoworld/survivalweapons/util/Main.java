package kro.dodoworld.survivalweapons.util;

import javax.swing.JFrame;
import javax.swing.JTextField;
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
        JTextField field = new JTextField("그냥 서버 폴더 안에 plugins 폴더에 이 파일을 넣고 서버를 키시면 됩니다!");

        this.add(field);
        setVisible(true);
    }
    public static void main(String[] args){
        new Main();
    }
}