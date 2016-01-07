import javax.swing.*;

import java.awt.*; //edit this later to take out wildcard

/**
 * Created by WilliamBeckler on 1/5/2016.
 */
public class HotelReservationSystem extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JButton button;
    private JTextField txtField;
    private ImageIcon image1;
    private ImageIcon image2;

    public static void main(String[] args) {
        HotelReservationSystem hotelGUI = new HotelReservationSystem();
        hotelGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //hotelGUI.setSize(800, 600);
        hotelGUI.pack(); //like setSize, but fits everything you add into the window
        hotelGUI.setVisible(true);
        hotelGUI.setTitle("Hotel Reservation System");
    }

    public HotelReservationSystem() {
        setLayout(new FlowLayout());

        image1 = new ImageIcon(getClass().getResource("pikachu.jpg"));
        label1 = new JLabel(image1);
        add(label1);

        image2 = new ImageIcon(getClass().getResource("pie.jpg"));
        label2 = new JLabel(image2);
        add(label2);
    }
}
