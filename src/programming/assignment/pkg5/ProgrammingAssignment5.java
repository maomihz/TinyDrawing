package programming.assignment.pkg5;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * @author Seancheey
 */
public class ProgrammingAssignment5 extends JFrame {
    public ProgrammingAssignment5() {
        super("Java2D Drawings by Qiyi Shan & MaomiHz");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //  setResizable(false);
        add(new MainPanel());
    }

    public static void main(String[] args) {
        new ProgrammingAssignment5().setVisible(true);
    }
}
