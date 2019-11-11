//
// NOTE:
//  -javac gui.java
//  -java gui <username> <port no.>
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class gui{
      public static void main(String args[]){
          try{
            // Array for card denominations;
            // Supposed to be 4 only (gonna store server's cards), but for testing purposes, has 12 cards
            ArrayList<String> list=new ArrayList<String>();
            list.add("AC");
            list.add("AD");
            list.add("AH");
            list.add("AS");
            list.add("2C");
            list.add("2D");
            list.add("2H");
            list.add("2C");
            list.add("3C");
            list.add("3D");
            list.add("3H");
            list.add("3S");
            // Shuffles cards
            Collections.shuffle(list);
            // Variables for code passing
            int turnNo = 1;
            int playerID = 1;
            // Create frame with title
            JFrame frame = new JFrame("1-2-3-PASS!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Removes title bar; so the close (x) button is not seen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setSize(600,400);
            frame.setResizable(false);
            frame.setUndecorated(true);
            frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
            // Frame size
            // Panel for cards, Shuffle, and Enter buttons
            JPanel panel = new JPanel();
            // Panel for cards buttons only
            JPanel midTop = new JPanel();
            // Panel for Shuffle and Enter buttons
            JPanel midBot = new JPanel();
            panel.setBackground(new Color(53, 101, 77));
            // Creates the card buttons
            JButton button1 = new JButton(new ImageIcon(((new ImageIcon("PNG-cards-1.3/"+list.get(0)+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH)));
            JButton button2 = new JButton(new ImageIcon(((new ImageIcon("PNG-cards-1.3/"+list.get(1)+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH)));
            JButton button3 = new JButton(new ImageIcon(((new ImageIcon("PNG-cards-1.3/"+list.get(2)+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH)));
            JButton button4 = new JButton(new ImageIcon(((new ImageIcon("PNG-cards-1.3/"+list.get(3)+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH)));
            // Shuffle button; PENDING JUDGEMENT: Might be removed; Replaced with timer?
            JButton shuffle = new JButton("SHUFFLE");
            // Button design
            shuffle.setBackground(Color.white);
            shuffle.setPreferredSize(new Dimension(150, 40));
            shuffle.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Enter button
            JButton enter = new JButton("ENTER");
            // Button design
            enter.setEnabled(false);
            enter.setForeground(Color.white);
            enter.setBackground(new Color(184, 0, 20));
            enter.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Button design
            button1.setBackground(new Color(53, 101, 77));
            button2.setBackground(new Color(53, 101, 77));
            button3.setBackground(new Color(53, 101, 77));
            button4.setBackground(new Color(53, 101, 77));
            button1.setBorderPainted(false);
            button2.setBorderPainted(false);
            button3.setBorderPainted(false);
            button4.setBorderPainted(false);
            // ActionListeners; prints chosen card and disables the rest
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  String code = "";
                  if(turnNo < 10){
                        code = "0";
                  }
                  code = code+turnNo;
                  if(playerID < 10){
                        code = code+"0";
                  }
                  code = code+playerID+list.get(0);
                  System.out.println(code);
                  button1.setEnabled(false);
                  button2.setEnabled(false);
                  button3.setEnabled(false);
                  button4.setEnabled(false);
                  shuffle.setEnabled(false);
                }
            });
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  String code = "";
                  if(turnNo < 10){
                        code = "0";
                  }
                  code = code+turnNo;
                  if(playerID < 10){
                        code = code+"0";
                  }
                  code = code+playerID+list.get(1);
                  System.out.println(code);
                  button1.setEnabled(false);
                  button2.setEnabled(false);
                  button3.setEnabled(false);
                  button4.setEnabled(false);
                  shuffle.setEnabled(false);
                }
            });
            button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  String code = "";
                  if(turnNo < 10){
                        code = "0";
                  }
                  code = code+turnNo;
                  if(playerID < 10){
                        code = code+"0";
                  }
                  code = code+playerID+list.get(2);
                  System.out.println(code);
                  button1.setEnabled(false);
                  button2.setEnabled(false);
                  button3.setEnabled(false);
                  button4.setEnabled(false);
                  shuffle.setEnabled(false);
                }
            });
            button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  String code = "";
                  if(turnNo < 10){
                        code = "0";
                  }
                  code = code+turnNo;
                  if(playerID < 10){
                        code = code+"0";
                  }
                  code = code+playerID+list.get(3);
                  System.out.println(code);
                  button1.setEnabled(false);
                  button2.setEnabled(false);
                  button3.setEnabled(false);
                  button4.setEnabled(false);
                  shuffle.setEnabled(false);
                }
            });
            // Rearranges cards in random manner
            shuffle.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  Collections.shuffle(list);
                  ImageIcon newImg1 = new ImageIcon(((new ImageIcon("PNG-cards-1.3/"+list.get(0)+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH));
                  ImageIcon newImg2 = new ImageIcon(((new ImageIcon("PNG-cards-1.3/"+list.get(1)+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH));
                  ImageIcon newImg3 = new ImageIcon(((new ImageIcon("PNG-cards-1.3/"+list.get(2)+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH));
                  ImageIcon newImg4 = new ImageIcon(((new ImageIcon("PNG-cards-1.3/"+list.get(3)+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH));
                  button1.setIcon(newImg1);
                  button2.setIcon(newImg2);
                  button3.setIcon(newImg3);
                  button4.setIcon(newImg4);
                  frame.repaint();
                }
            });
            // Press enter for win
            enter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  // This print will be the enter signal from client to server
                  System.out.println(args[0] + " has pressed Enter!!!");
                  enter.setBackground(new Color(200, 168, 1));
                  enter.setEnabled(false);
                }
            });
            // Panel for optional buttons and connection info
            JPanel panel2 = new JPanel();
            panel2.setBackground(new Color(53, 101, 77));
            // Reset button; to revert unlickable buttons
            JButton reset = new JButton("RESET TEST");//supposed place of PAUSE
            reset.setBackground(Color.white);
            reset.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Currently a test button for win scenario
            JButton help = new JButton("WIN TEST");//supposed place of HELP
            help.setBackground(Color.white);
            help.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Working exit button
            JButton exit = new JButton("EXIT");
            exit.setBackground(Color.white);
            exit.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Text for Player ID and designs
            JTextField pId = new JTextField();
            pId.setText("Player ID: XX");
            pId.setEditable(false);
            pId.setForeground(Color.white);
            pId.setBackground(new Color(53, 101, 77));
            pId.setPreferredSize(new Dimension(180, 20));
            pId.setHorizontalAlignment(JTextField.CENTER);
            pId.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Text for Player count and designs
            JTextField players = new JTextField();
            players.setText("Total Player: XX");
            players.setEditable(false);
            players.setForeground(Color.white);
            players.setBackground(new Color(53, 101, 77));
            players.setPreferredSize(new Dimension(180, 20));
            players.setHorizontalAlignment(JTextField.CENTER);
            players.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Text for Port ID and designs
            JTextField portId = new JTextField();
            portId.setText("Port: "+args[1]);
            portId.setEditable(false);
            portId.setForeground(Color.white);
            portId.setBackground(new Color(53, 101, 77));
            portId.setPreferredSize(new Dimension(180, 20));
            portId.setHorizontalAlignment(JTextField.CENTER);
            portId.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Action Listeners
            reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  button1.setEnabled(true);
                  button2.setEnabled(true);
                  button3.setEnabled(true);
                  button4.setEnabled(true);
                  enter.setBackground(new Color(184, 0, 20));
                  enter.setEnabled(false);
                  exit.setEnabled(true);
                  shuffle.setEnabled(true);
                }
            });
            help.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  button1.setEnabled(false);
                  button2.setEnabled(false);
                  button3.setEnabled(false);
                  button4.setEnabled(false);
                  enter.setBackground(new Color(0, 171, 102));
                  enter.setEnabled(true);
                  exit.setEnabled(false);
                  shuffle.setEnabled(false);
                }
            });
            exit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  frame.dispose();
                }
            });
            // Panel for player info
            JPanel panel3 = new JPanel();
            panel3.setBackground(new Color(53, 101, 77));
            // Text for Player name and designs
            JTextField name = new JTextField();
            name.setText("Name: "+args[0]);
            name.setEditable(false);
            name.setForeground(Color.white);
            name.setBackground(new Color(53, 101, 77));
            name.setPreferredSize(new Dimension(180, 50));
            name.setHorizontalAlignment(JTextField.CENTER);
            name.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Text for Turn number and designs
            JTextField turn = new JTextField();
            turn.setText("Turn: XX");
            turn.setEditable(false);
            turn.setForeground(Color.white);
            turn.setBackground(new Color(53, 101, 77));
            turn.setPreferredSize(new Dimension(180, 50));
            turn.setHorizontalAlignment(JTextField.CENTER);
            turn.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Text for Score and designs
            JTextField score = new JTextField();
            score.setText("Score: XX");
            score.setEditable(false);
            score.setForeground(Color.white);
            score.setBackground(new Color(53, 101, 77));
            score.setPreferredSize(new Dimension(180, 50));
            score.setHorizontalAlignment(JTextField.CENTER);
            score.setBorder(new LineBorder(new Color(53, 101, 77),1));
            // Layout for top bar that contains player info
            GridLayout topLayout = new GridLayout(1,3);
            panel3.setLayout(topLayout);
            panel3.add(name);
            panel3.add(turn);
            panel3.add(score);
            // Layout for card buttons and associated buttons
            midTop.add(button1);
            midTop.add(button2);
            midTop.add(button3);
            midTop.add(button4);
            GridLayout midLayout = new GridLayout(2,1,0,10);
            midBot.setLayout(midLayout);
            midBot.setBackground(new Color(53, 101, 77));
            midBot.add(shuffle);
            midBot.add(enter);
            panel.add(BorderLayout.PAGE_START,midTop);
            panel.add(BorderLayout.PAGE_END,midBot);
            // Layout for bottom buttons and info
            GridLayout bottomLayout = new GridLayout(2,3,10,0);
            panel2.setLayout(bottomLayout);
            panel2.add(reset);
            panel2.add(help);
            panel2.add(exit);
            panel2.add(pId);
            panel2.add(players);
            panel2.add(portId);
            // Combination of panels in the frame
            frame.getContentPane().add(BorderLayout.CENTER,panel);
            frame.getContentPane().add(BorderLayout.PAGE_END,panel2);
            frame.getContentPane().add(BorderLayout.PAGE_START,panel3);
            frame.setVisible(true);
          }catch(Exception ie){
            System.out.println("Format should be: -java gui <username> <port no.>");
          }
     }
}
