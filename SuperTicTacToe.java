import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.lang.Thread.sleep;

public class SuperTicTacToe implements ActionListener {

    Random num = new Random();
    JFrame frame = new JFrame("Super");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel RPanel = new JPanel(new BorderLayout());
    JLabel title = new JLabel();
    JButton[][] buttons = new JButton[9][9];
    JButton restart = new JButton();
    boolean p1;
    boolean[] xWin = new boolean[9];
    boolean[] oWin = new boolean[9];
    boolean[] draw = new boolean[9];

    SuperTicTacToe(){
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
        title.setFont(new Font("Ink Free",Font.BOLD,100));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);

        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.setLayout(new GridLayout(9,9));
        titlePanel.setLayout(new GridLayout(1,2));

        for(int i = 0;i<9;i++){
            for (int j = 0; j < 9; j++) {
                buttons[i][j]= new JButton();
                buttons[i][j].setText(" ");
                buttons[i][j].setBackground(Color.CYAN);
                buttons[i][j].setFont(new Font("Ink Free", Font.ITALIC, 50));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j <6; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j <9; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j <3; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j <6; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 6; j <9; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j <3; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j <6; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j <9; j++) {
                buttonPanel.add(buttons[i][j]);
            }
        }

        titlePanel.add(title);
        titlePanel.add(restart);
        restart.setSize(100,100);
        restart.addActionListener(this);

        restart.setText("Restart");
        restart.setFont(new Font("Ink Free",Font.CENTER_BASELINE,20));

        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(RPanel,BorderLayout.LINE_END);
        frame.add(buttonPanel);
        try {
            firstTurn();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void subCheck(int a){

        int subCount = 0;
        for (int i = 0; i < 9; i++) {
            if(buttons[a][i].getText()!=" ") subCount++;
        }if(subCount==9)subDraw(a);
        for (int i = 0; i < 8; i+=3) {
            if ((buttons[a][i].getText() == "X") && (buttons[a][i + 1].getText() == "X") && (buttons[a][i + 2].getText() == "X")) {
                xSubWin(a);
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((buttons[a][i].getText() == "X") && (buttons[a][i + 3].getText() == "X") && (buttons[a][i + 6].getText() == "X")) {
                xSubWin(a);
            }
        }
        if((buttons[a][0].getText()=="X")&&(buttons[a][4].getText()=="X")&&(buttons[a][8].getText()=="X")){
            xSubWin(a);
        }
        if((buttons[a][2].getText()=="X")&&(buttons[a][4].getText()=="X")&&(buttons[a][6].getText()=="X")){
            xSubWin(a);
        }
        for (int i = 0; i < 8; i+=3) {
            if ((buttons[a][i].getText() == "O") && (buttons[a][i + 1].getText() == "O") && (buttons[a][i + 2].getText() == "O")) {
                oSubWin(a);
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((buttons[a][i].getText() == "O") && (buttons[a][i + 3].getText() == "O") && (buttons[a][i + 6].getText() == "O")) {
                oSubWin(a);
            }
        }
        if((buttons[a][0].getText()=="O")&&(buttons[a][4].getText()=="O")&&(buttons[a][8].getText()=="O")){
            oSubWin(a);
        }
        if((buttons[a][2].getText()=="O")&&(buttons[a][4].getText()=="O")&&(buttons[a][6].getText()=="O")){
            oSubWin(a);
        }
    }
    public void check(){

        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
              if(buttons[i][j].getText()!=" ") {
                    count++;
            }
            }

        }if(count==81) draw();

        for (int i = 0; i < 8; i+=3) {
            if (xWin[i] && xWin[i+1] && xWin[i+2]) {
                xWin(i, i + 1, i + 2);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (xWin[i] && xWin[i+3] && xWin[i+6]) {
                xWin(i, i + 3, i + 6);
            }
        }
        if(xWin[0]&&xWin[4]&&xWin[8]){
            xWin(0,4,8);
        }
        if(xWin[2]&&xWin[4]&&xWin[6]){
            xWin(2,4,6);
        }
        for (int i = 0; i < 8; i+=3) {
            if (oWin[i] && oWin[i+1] && oWin[i+2]) {
                oWin(i, i + 1, i + 2);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (oWin[i] && oWin[i+3] && oWin[i+6]) {
                oWin(i, i + 3, i + 6);
            }
        }
        if(oWin[0]&&oWin[4]&&oWin[8]){
            oWin(0,4,8);
        }
        if(oWin[2]&&oWin[4]&&oWin[6]){
            oWin(2,4,6);
        }
    }

    public void xSubWin(int a){
        for (int i = 0; i < 9; i++) {
            buttons[a][i].setText("X");
            buttons[a][i].setEnabled(false);
            buttons[a][i].setBackground(Color.GREEN);
        }
        xWin[a]=true;
    }
    public void oSubWin(int a){
        for (int i = 0; i < 9; i++) {
            buttons[a][i].setText("O");
            buttons[a][i].setEnabled(false);
            buttons[a][i].setBackground(Color.RED);
        }
        oWin[a]=true;
    }
    public void subDraw(int a){
        for (int i = 0; i < 9; i++) {
            buttons[a][i].setEnabled(false);
            buttons[a][i].setBackground(Color.DARK_GRAY);
        }
        draw[a]=true;
    }


    public void xWin(int a, int b, int c){
        title.setText("X Won");
        for (int i = 0; i < 9; i++) {
            buttons[a][i].setBackground(Color.BLUE);
            buttons[b][i].setBackground(Color.BLUE);
            buttons[c][i].setBackground(Color.BLUE);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j].setEnabled(false);
            }
        }

    }
    public void oWin(int a, int b, int c){
        title.setText("O Won");
        for (int i = 0; i < 9; i++) {
            buttons[a][i].setBackground(Color.BLUE);
            buttons[b][i].setBackground(Color.BLUE);
            buttons[c][i].setBackground(Color.BLUE);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j].setEnabled(false);
            }
        }

    }
    public void draw(){
        title.setText("Draw");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <9 ; j++) {
                buttons[i][j].setEnabled(false);
            }
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
            for (int j = 0; j < 9; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setBackground(Color.CYAN);
                buttons[i][j].setEnabled(true);
            }
        }
        title.setText("Tic-Tac-Toe");
        try {
            firstTurn();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void moveSub(int a){
        lockAll();
        if(xWin[a]||oWin[a]||draw[a]){
            setFreeAll();
        }
        else{

                setFree(a);

        }
    }
    public void lockAll(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j].setEnabled(false);

            }

        }
    }
    public void setFree(int a){
        for (int i = 0; i < 9; i++) {
            buttons[a][i].setEnabled(true);
        }
    }
    public void setFreeAll(){
        for (int i = 0; i < 9; i++) {
            if(xWin[i]){
                continue;
            }
            else{
                for (int j = 0; j < 9; j++) {
                    buttons[i][j].setEnabled(true);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==restart){
            restart();
        }

        for (int i = 0; (i) < 9; (i)++) {
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == buttons[i][j] && buttons[i][j].getText() == " ") {
                    if (p1) {
                        buttons[i][j].setForeground(Color.GREEN);
                        buttons[i][j].setText("X");
                        p1 = false;
                        title.setText("O Turn");
                        subCheck(i);
                        check();
                        moveSub(i);
                    } else {
                        buttons[i][j].setForeground(Color.BLUE);
                        buttons[i][j].setText("O");
                        p1 = true;
                        title.setText("X Turn");
                        subCheck(i);
                        check();
                        moveSub(i);
                    }
                }
            }

        }

    }
}
