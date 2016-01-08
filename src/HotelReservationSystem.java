import com.sun.xml.internal.txw2.TXW;

import javax.swing.*;

import java.awt.*; //edit this later to take out wildcard
import java.awt.event.*;

/**
 * Created by WilliamBeckler on 1/5/2016.
 */
public class HotelReservationSystem extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton button1;
    private JButton button2;
    private JTextField txtField;
    private ImageIcon image1;
    private ImageIcon image2;
    private int x = 0, y = 0;
    private int rando, guess;
    private JMenuBar menubar ;
    private JMenu file;
    private JMenuItem exit;

    public static void main(String[] args) {
        HotelReservationSystem hotelGUI = new HotelReservationSystem();
        hotelGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hotelGUI.setVisible(true);
        hotelGUI.setSize(800, 600);
        hotelGUI.setTitle("Hotel Reservation System");
    }

    public HotelReservationSystem() {
        setLayout(new FlowLayout());

        menubar = new JMenuBar();
        setJMenuBar(menubar);

        file = new JMenu("File");
        menubar.add(file);

        exit = new JMenuItem("Exit");
        file.add(exit);

        exitProc exi = new exitProc();
        exit.addActionListener(exi);
    }

    public class exitProc implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }
}
