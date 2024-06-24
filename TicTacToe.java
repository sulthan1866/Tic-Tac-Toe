import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.lang.Thread.sleep;

public class TicTacToe implements ActionListener {

    Random num = new Random();
    JFrame frame = new JFrame("Regular");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel title = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton restart = new JButton();
    boolean p1;

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.CYAN);
        frame.setVisible(true);


        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,700,100);



        title.setForeground(Color.BLUE);
        title.setText("Tic-Tac-Toe");
        title.setBackground(Color.BLACK);
        title.setVisible(true);
        title.setFont(new Font("Ink Free",Font.BOLD,75));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);

        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.setLayout(new GridLayout(3,3));
        titlePanel.setLayout(new GridLayout(1,2));
        for(int i = 0;i<9;i++){
            buttons[i]= new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setText(" ");
            buttons[i].setBackground(Color.CYAN);
            buttons[i].setFont(new Font("Ink Free",Font.ITALIC,100));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(title);
        titlePanel.add(restart);
        restart.setSize(100,100);
        restart.addActionListener(this);

        restart.setText("Restart");
        restart.setFont(new Font("Ink Free",Font.CENTER_BASELINE,20));

        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);
        try {
            firstTurn();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void check(){

        int count = 0;
        for (int i = 0; i < 9; i++) {
            if(buttons[i].getText()!=" "){
                count++;
            }

        }if(count==9) draw();

        for (int i = 0; i < 8; i+=3) {
            if ((buttons[i].getText() == "X") && (buttons[i + 1].getText() == "X") && (buttons[i + 2].getText() == "X")) {
                xWin(i, i + 1, i + 2);
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((buttons[i].getText() == "X") && (buttons[i + 3].getText() == "X") && (buttons[i + 6].getText() == "X")) {
                xWin(i, i + 3, i + 6);
            }
        }
        if((buttons[0].getText()=="X")&&(buttons[4].getText()=="X")&&(buttons[8].getText()=="X")){
            xWin(0,4,8);
        }
        if((buttons[2].getText()=="X")&&(buttons[4].getText()=="X")&&(buttons[6].getText()=="X")){
            xWin(2,4,6);
        }
        for (int i = 0; i < 8; i+=3) {
            if ((buttons[i].getText() == "O") && (buttons[i + 1].getText() == "O") && (buttons[i + 2].getText() == "O")) {
                oWin(i, i + 1, i + 2);
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((buttons[i].getText() == "O") && (buttons[i + 3].getText() == "O") && (buttons[i + 6].getText() == "O")) {
                oWin(i, i + 3, i + 6);
            }
        }
        if((buttons[0].getText()=="O")&&(buttons[4].getText()=="O")&&(buttons[8].getText()=="O")){
            oWin(0,4,8);
        }
        if((buttons[2].getText()=="O")&&(buttons[4].getText()=="O")&&(buttons[6].getText()=="O")){
            oWin(2,4,6);
        }


    }
    public void xWin(int a,int b,int c){
        title.setText("X Won");
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);

        }

    }
    public void oWin(int a,int b,int c){
        title.setText("O Won");
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);

        }

    }
    public void draw(){
        title.setText("Draw");
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);

        }
    }
    public void firstTurn() throws InterruptedException {

        sleep(2500);

        if(num.nextInt(2)==0){
            p1=true;
            title.setText("X Turn");
        }else{
            p1=false;
            title.setText("O Turn");
        }
    }
    public void restart(){
        for (int i = 0; i < 9; i++) {
            buttons[i].setText(" ");
            buttons[i].setBackground(Color.CYAN);
            buttons[i].setEnabled(true
            );
        }
        title.setText("Tic-Tac-Toe");
            try {
                firstTurn();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==restart){
            restart();
        }
        for (int i = 0; (i) < 9; (i)++) {
            if(e.getSource()==buttons[i] && buttons[i].getText()==" "){
                if(p1 ){
                    buttons[i].setForeground(Color.GREEN);
                    buttons[i].setText("X");
                    p1=false;
                    title.setText("O Turn");
                    check();
                }else{
                    buttons[i].setForeground(Color.BLUE);
                    buttons[i].setText("O");
                    p1=true;
                    title.setText("X Turn");
                    check();
                }
            }

        }

    }
}
