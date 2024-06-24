import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HomePage implements ActionListener {
    Random R = new Random();
    JFrame frame = new JFrame("Welcome");
    JPanel titleP = new JPanel();
    JLabel title = new JLabel();
    JPanel options = new JPanel();
    JButton[] button = new JButton[4];
    HomePage(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.CYAN);
        frame.setVisible(true);

        titleP.setLayout(new BorderLayout());
        titleP.setBounds(0,0,700,100);

        title.setForeground(Color.BLUE);
        title.setText("Tic-Tac-Toe");
        title.setBackground(Color.BLACK);
        title.setVisible(true);
        title.setFont(new Font("Ink Free",Font.BOLD,75));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);

     options.setLayout(new GridLayout(2,2));

        for (int i = 0; i < 4; i++) {
            button[i] = new JButton();
            options.add(button[i]);
            button[i].setBackground(Color.CYAN);
            button[i].setFont(new Font("Ink Free",Font.ITALIC,100));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
        }
        button[0].setText("Regular");
        button[1].setText("No Draw");
        button[1].setFont(new Font("Ink Free",Font.ITALIC,75));
        button[2].setText("Super");
        button[3].setText("Random");

        titleP.add(title);
        frame.add(titleP,BorderLayout.NORTH);
        frame.add(options);
    }

    public void Random(){
        int op = R.nextInt(3);
        if(op==0){
            TicTacToe t = new TicTacToe();
        } else if (op==1) {
            NoDraw n = new NoDraw();
        } else {
            SuperTicTacToe s = new SuperTicTacToe();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button[0]) {
            TicTacToe t = new TicTacToe();
        } else if (e.getSource()==button[1]) {
            NoDraw n = new NoDraw();
        } else if (e.getSource()==button[2]) {
            SuperTicTacToe s = new SuperTicTacToe();
        }else if(e.getSource()==button[3]){
            Random();
        }
    }
}
