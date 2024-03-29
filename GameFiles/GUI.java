//Main GUI File

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.border.LineBorder;

public class GUI implements Runnable {
	private ViewListener listener;

	private JFrame login;
	private JFrame frame;
	private JFrame help;
	private JFrame about;
	private JFrame win;
	private JFrame lost;

	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;

	private JButton enter;
	private JButton exit;
	private JButton shuffle;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;

	private JTextField name;
	private JTextField turn;
	private JTextField score;
	private JTextField portId;
	private JTextField pId;

	private final String IMG_FOLDER_APP = "PNG-photos/";
	private final String IMG_FOLDER_CARDS = "PNG-cards-1.3/";

//window classes
	public GUI(ViewListener listener) {
		this.listener = listener;

		initLoseWindow();
		initWinWindow();
		initGameWindow();
		initHelpWindow();
		initAboutWindow();
		initLoginWindow();
	}

	//Lose window pop up
	private void initLoseWindow(){
		lost = new JFrame();
		//lost.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		lost.setSize(355,200);
		lost.setResizable(false);
		lost.setLocationRelativeTo(null);
		lost.getContentPane().setBackground(new Color(184, 0, 20));

	    JTextField message = new JTextField();
        message.setText("YOU WERE LAST!\nYOU LOSE!!");
        message.setEditable(false);
        message.setForeground(Color.white);
        message.setBackground(new Color(184, 0, 20));
        message.setHorizontalAlignment(JTextField.CENTER);

        lost.add(message);
	}
	//Win window pop up
	private void initWinWindow(){
		win = new JFrame();
	    //win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    win.setSize(355,200);
	    win.setResizable(false);
	    win.setLocationRelativeTo(null);
	    win.getContentPane().setBackground(new Color(53, 101, 77));

	    JTextField message = new JTextField();
        message.setText("YOU WIN!!!");
        message.setEditable(false);
        message.setForeground(Color.white);
        message.setBackground(new Color(53, 101, 77));
        message.setHorizontalAlignment(JTextField.CENTER);

        win.add(message);
	}

//About Window
	private void initAboutWindow() {
		about = new JFrame();
		about.setUndecorated(true);
	    about.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    about.setSize(711,400);
	    about.setResizable(false);
	    about.setLocationRelativeTo(null);
	    about.getContentPane().setBackground(new Color(53, 101, 77));

	    JPanel mainPanel = new JPanel(new CardLayout());
	    about.add(mainPanel, BorderLayout.CENTER);

		CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

	    JPanel aboutCard1 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "about1.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel aboutCard2 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "about2.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel aboutCard3 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "about3.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel aboutCard4 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "about4.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel aboutCard5 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "about5.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel aboutCard6 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "about6.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel aboutCard7 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "about7.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    mainPanel.add(aboutCard1, "Page 1");
	    mainPanel.add(aboutCard2, "Page 2");
	    mainPanel.add(aboutCard3, "Page 3");
	    mainPanel.add(aboutCard4, "Page 4");
	    mainPanel.add(aboutCard5, "Page 5");
	    mainPanel.add(aboutCard6, "Page 6");
	    mainPanel.add(aboutCard7, "Page 7");

	    GridBagConstraints c = new GridBagConstraints();

	    JPanel padding1 = new JPanel();
	    JPanel padding2 = new JPanel();
	    JPanel padding3 = new JPanel();
	    JPanel padding4 = new JPanel();
	    JPanel padding5 = new JPanel();
	    JPanel padding6 = new JPanel();
	    JPanel padding7 = new JPanel();

	    padding1.setOpaque(false);
	    padding2.setOpaque(false);
	    padding3.setOpaque(false);
	    padding4.setOpaque(false);
	    padding5.setOpaque(false);
	    padding6.setOpaque(false);
	    padding7.setOpaque(false);

	    JButton returnButton1 = new JButton();
	    returnButton1.setBorder(BorderFactory.createEmptyBorder());
	    returnButton1.setBorderPainted(false);
    	returnButton1.setFocusPainted(false);
    	returnButton1.setContentAreaFilled(false);

	    returnButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				about.setVisible(false);
				login.setVisible(true);
			}

	    });

	    JButton returnButton2 = new JButton();
	    returnButton2.setBorder(BorderFactory.createEmptyBorder());
	    returnButton2.setBorderPainted(false);
    	returnButton2.setFocusPainted(false);
    	returnButton2.setContentAreaFilled(false);

	    returnButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				about.setVisible(false);
				login.setVisible(true);
			}

	    });

	    try {
	    	Image img1 = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "returnleft.png"));
	    	returnButton1.setIcon(new ImageIcon(img1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
	    	Image img2 = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "returnright.png"));
	    	returnButton2.setIcon(new ImageIcon(img2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    JButton nextButton1 = new JButton();
	    nextButton1.setBorder(BorderFactory.createEmptyBorder());
	    nextButton1.setBorderPainted(false);
    	nextButton1.setFocusPainted(false);
    	nextButton1.setContentAreaFilled(false);

	    JButton nextButton2 = new JButton();
	   	nextButton2.setBorder(BorderFactory.createEmptyBorder());
	    nextButton2.setBorderPainted(false);
    	nextButton2.setFocusPainted(false);
    	nextButton2.setContentAreaFilled(false);

	    JButton nextButton3 = new JButton();
	   	nextButton3.setBorder(BorderFactory.createEmptyBorder());
	    nextButton3.setBorderPainted(false);
    	nextButton3.setFocusPainted(false);
    	nextButton3.setContentAreaFilled(false);

	    JButton nextButton4 = new JButton();
	   	nextButton4.setBorder(BorderFactory.createEmptyBorder());
	    nextButton4.setBorderPainted(false);
    	nextButton4.setFocusPainted(false);
    	nextButton4.setContentAreaFilled(false);

	    JButton nextButton5 = new JButton();
	   	nextButton5.setBorder(BorderFactory.createEmptyBorder());
	    nextButton5.setBorderPainted(false);
    	nextButton5.setFocusPainted(false);
    	nextButton5.setContentAreaFilled(false);

	    JButton nextButton6 = new JButton();
	   	nextButton6.setBorder(BorderFactory.createEmptyBorder());
	    nextButton6.setBorderPainted(false);
    	nextButton6.setFocusPainted(false);
    	nextButton6.setContentAreaFilled(false);

	    try {
	    	Image img = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "next.png"));
			nextButton1.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			nextButton2.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			nextButton3.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			nextButton4.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			nextButton5.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			nextButton6.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    nextButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 2");
			}

	    });

	    nextButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 3");
			}

	    });

	    nextButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 4");
			}

	    });

	    nextButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 5");
			}

	    });

	    nextButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 6");
			}

	    });

	    nextButton6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 7");
			}

	    });

	   	JButton prevButton1 = new JButton();
	    prevButton1.setBorder(BorderFactory.createEmptyBorder());
	    prevButton1.setBorderPainted(false);
    	prevButton1.setFocusPainted(false);
    	prevButton1.setContentAreaFilled(false);

	   	JButton prevButton2 = new JButton();
	    prevButton2.setBorder(BorderFactory.createEmptyBorder());
	    prevButton2.setBorderPainted(false);
    	prevButton2.setFocusPainted(false);
    	prevButton2.setContentAreaFilled(false);

	   	JButton prevButton3 = new JButton();
	    prevButton3.setBorder(BorderFactory.createEmptyBorder());
	    prevButton3.setBorderPainted(false);
    	prevButton3.setFocusPainted(false);
    	prevButton3.setContentAreaFilled(false);

	   	JButton prevButton4 = new JButton();
	    prevButton4.setBorder(BorderFactory.createEmptyBorder());
    	prevButton4.setFocusPainted(false);
    	prevButton4.setContentAreaFilled(false);

	   	JButton prevButton5 = new JButton();
	    prevButton5.setBorder(BorderFactory.createEmptyBorder());
	    prevButton5.setBorderPainted(false);
    	prevButton5.setFocusPainted(false);
    	prevButton5.setContentAreaFilled(false);

	   	JButton prevButton6 = new JButton();
	    prevButton6.setBorder(BorderFactory.createEmptyBorder());
	    prevButton6.setBorderPainted(false);
    	prevButton6.setFocusPainted(false);
    	prevButton6.setContentAreaFilled(false);

	    try {
	    	Image img = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "previous.png"));
			prevButton1.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			prevButton2.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			prevButton3.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			prevButton4.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			prevButton5.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			prevButton6.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    prevButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 1");
			}
	    });

	    prevButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 2");
			}
	    });

	    prevButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 3");
			}
	    });

	    prevButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 4");
			}
	    });

	    prevButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 5");
			}
	    });

	    prevButton6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 6");
			}
	    });


	    c.anchor = GridBagConstraints.SOUTH;
	    c.insets = new Insets(15, 15, 15, 15);
	    c.weightx = c.weighty = 1;
	    c.gridheight = 6;
	    c.gridwidth = 5;
	    c.gridx = c.gridy = 0;
	    aboutCard1.add(padding1, c);
	    aboutCard2.add(padding2, c);
	    aboutCard3.add(padding3, c);
	    aboutCard4.add(padding4, c);
	    aboutCard5.add(padding5, c);
	    aboutCard6.add(padding6, c);
	    aboutCard7.add(padding7, c);

	    c.gridheight = c.gridwidth = 1;
	    c.gridy = 6;
	    aboutCard1.add(returnButton1, c);
	    aboutCard2.add(prevButton1, c);
	    aboutCard3.add(prevButton2, c);
	    aboutCard4.add(prevButton3, c);
	    aboutCard5.add(prevButton4, c);
	    aboutCard6.add(prevButton5, c);
	    aboutCard7.add(prevButton6, c);

	    c.gridx = 4;
	    aboutCard1.add(nextButton1, c);
	    aboutCard2.add(nextButton2, c);
	    aboutCard3.add(nextButton3, c);
	    aboutCard4.add(nextButton4, c);
	    aboutCard5.add(nextButton5, c);
	    aboutCard6.add(nextButton6, c);
	    aboutCard7.add(returnButton2, c);
	}


//Help Window
	private void initHelpWindow() {
		help = new JFrame();
		help.setUndecorated(true);
	    help.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    help.setSize(711,400);
	    help.setResizable(false);
	    help.setLocationRelativeTo(null);
	    help.getContentPane().setBackground(new Color(53, 101, 77));

	    JPanel mainPanel = new JPanel(new CardLayout());
	    help.add(mainPanel, BorderLayout.CENTER);

		CardLayout cardLayout = (CardLayout) mainPanel.getLayout();

	    JPanel helpCard1 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "help1.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel helpCard2 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "help2.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel helpCard3 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "help3.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel helpCard4 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "help4.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    JPanel helpCard5 = new JPanel(new GridBagLayout()) {
			private static final long serialVersionUID = 1L;

			@Override
	    	protected void paintComponent(Graphics g) {
	    		super.paintComponent(g);
	    			g.drawImage(new ImageIcon(IMG_FOLDER_APP + "help5.png").getImage(), 0, 0, 711, 400, null);
	    	}
	    };

	    mainPanel.add(helpCard1, "Page 1");
	    mainPanel.add(helpCard2, "Page 2");
	    mainPanel.add(helpCard3, "Page 3");
	    mainPanel.add(helpCard4, "Page 4");
	    mainPanel.add(helpCard5, "Page 5");

	    GridBagConstraints c = new GridBagConstraints();

	    JPanel padding1 = new JPanel();
	    JPanel padding2 = new JPanel();
	    JPanel padding3 = new JPanel();
	    JPanel padding4 = new JPanel();
	    JPanel padding5 = new JPanel();

	    padding1.setOpaque(false);
	    padding2.setOpaque(false);
	    padding3.setOpaque(false);
	    padding4.setOpaque(false);
	    padding5.setOpaque(false);

	    JButton returnButton1 = new JButton();
	    returnButton1.setBorder(BorderFactory.createEmptyBorder());
	    returnButton1.setBorderPainted(false);
    	returnButton1.setFocusPainted(false);
    	returnButton1.setContentAreaFilled(false);

	    returnButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				help.setVisible(false);
				login.setVisible(true);
			}

	    });

	    JButton returnButton2 = new JButton();
	    returnButton2.setBorder(BorderFactory.createEmptyBorder());
	    returnButton2.setBorderPainted(false);
    	returnButton2.setFocusPainted(false);
    	returnButton2.setContentAreaFilled(false);


	    returnButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				help.setVisible(false);
				login.setVisible(true);
			}

	    });

	    try {
	    	Image img1 = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "returnleft.png"));
	    	returnButton1.setIcon(new ImageIcon(img1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
	    	Image img2 = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "returnright.png"));
	    	returnButton2.setIcon(new ImageIcon(img2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    JButton nextButton1 = new JButton();
	    nextButton1.setBorder(BorderFactory.createEmptyBorder());
	    nextButton1.setBorderPainted(false);
    	nextButton1.setFocusPainted(false);
    	nextButton1.setContentAreaFilled(false);

	    JButton nextButton2 = new JButton();
	    nextButton2.setBorder(BorderFactory.createEmptyBorder());
	    nextButton2.setBorderPainted(false);
    	nextButton2.setFocusPainted(false);
    	nextButton2.setContentAreaFilled(false);

	    JButton nextButton3 = new JButton();
	    nextButton3.setBorder(BorderFactory.createEmptyBorder());
	    nextButton3.setBorderPainted(false);
    	nextButton3.setFocusPainted(false);
    	nextButton3.setContentAreaFilled(false);

	    JButton nextButton4 = new JButton();
	    nextButton4.setBorder(BorderFactory.createEmptyBorder());
	    nextButton4.setBorderPainted(false);
    	nextButton4.setFocusPainted(false);
    	nextButton4.setContentAreaFilled(false);


	    try {
	    	Image img = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "next.png"));
			nextButton1.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			nextButton2.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			nextButton3.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			nextButton4.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    nextButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 2");
			}

	    });

	    nextButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 3");
			}

	    });

	    nextButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 4");
			}

	    });

	    nextButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 5");
			}

	    });

	    JButton prevButton1 = new JButton();
	    prevButton1.setBorder(BorderFactory.createEmptyBorder());
	    prevButton1.setBorderPainted(false);
    	prevButton1.setFocusPainted(false);
    	prevButton1.setContentAreaFilled(false);

	    JButton prevButton2 = new JButton();
	    prevButton2.setBorder(BorderFactory.createEmptyBorder());
	    prevButton2.setBorderPainted(false);
    	prevButton2.setFocusPainted(false);
    	prevButton2.setContentAreaFilled(false);

	    JButton prevButton3 = new JButton();
	    prevButton3.setBorder(BorderFactory.createEmptyBorder());
	    prevButton3.setBorderPainted(false);
    	prevButton3.setFocusPainted(false);
    	prevButton3.setContentAreaFilled(false);

	    JButton prevButton4 = new JButton();
	    prevButton4.setBorder(BorderFactory.createEmptyBorder());
	    prevButton4.setBorderPainted(false);
    	prevButton4.setFocusPainted(false);
    	prevButton4.setContentAreaFilled(false);

	    try {
	    	Image img = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "previous.png"));
			prevButton1.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			prevButton2.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			prevButton3.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			prevButton4.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    prevButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 1");
			}

	    });

	    prevButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 2");
			}

	    });

	    prevButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 3");
			}

	    });

	    prevButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(mainPanel, "Page 4");
			}

	    });

	    c.anchor = GridBagConstraints.SOUTH;
	    c.insets = new Insets(15, 15, 15, 15);
	    c.weightx = c.weighty = 1;
	    c.gridheight = 6;
	    c.gridwidth = 5;
	    c.gridx = c.gridy = 0;
	    helpCard1.add(padding1, c);
	    helpCard2.add(padding2, c);
	    helpCard3.add(padding3, c);
	    helpCard4.add(padding4, c);
	    helpCard5.add(padding5, c);

	    c.gridheight = c.gridwidth = 1;
	    c.gridy = 6;
	    helpCard1.add(returnButton1, c);
	    helpCard2.add(prevButton1, c);
	    helpCard3.add(prevButton2, c);
	    helpCard4.add(prevButton3, c);
	    helpCard5.add(prevButton4, c);

	    c.gridx = 4;
	    helpCard1.add(nextButton1, c);
	    helpCard2.add(nextButton2, c);
	    helpCard3.add(nextButton3, c);
	    helpCard4.add(nextButton4, c);
	    helpCard5.add(returnButton2, c);
	}


//Main Window
	private void initLoginWindow() {
		login = new JFrame("1-2-3 PASS! - CMSC 137 Project");
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(711,400);
        login.setResizable(false);
        login.setLocationRelativeTo(null);

	    JPanel mainPanel = new JPanel(new GridBagLayout());
	    //JLabel credits = new JLabel("Dacquel, Dollentes, Figueroa, Salcedo, Villaro | 2019");
	    JLabel credits = new JLabel("  ");

	    login.getContentPane().setBackground(new Color(53, 101, 77));
	    mainPanel.setBackground(new Color(53, 101, 77));

	    login.add(mainPanel, BorderLayout.CENTER);
	    login.add(credits, BorderLayout.SOUTH);

	    JLabel title = new JLabel();
		try {
			ImageIcon imgIco = new ImageIcon(ImageIO.read(new File(IMG_FOLDER_APP + "LOGO.png")).getScaledInstance(200, 200, Image.SCALE_SMOOTH));
			title.setIcon(imgIco);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	    JLabel nameLabel = new JLabel("Enter Name:");
	    nameLabel.setForeground(Color.white);
	   	nameLabel.setFont(new Font("Serif", Font.BOLD, 14));

	    JLabel serverLabel = new JLabel("Enter Server's IP:");
	    serverLabel.setForeground(Color.white);
	    serverLabel.setFont(new Font("Serif", Font.BOLD, 14));

	    JLabel portLabel = new JLabel("Enter Port:");
	    portLabel.setForeground(Color.white);
	    portLabel.setFont(new Font("Serif", Font.BOLD, 14));



        JTextField namefield = new JTextField(20);
        JTextField serverfield = new JTextField(20);
        JTextField portfield = new JTextField(20);

        JButton startButton = new JButton("PLAY");
        startButton.setForeground(Color.WHITE);
	    startButton.setBorder(BorderFactory.createEmptyBorder());
	    startButton.setBorderPainted(false);
    	startButton.setFocusPainted(false);
    	startButton.setContentAreaFilled(false);

	    try {
	    	Image img = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "rplay.png"));
	    	startButton.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}



        JButton helpButton = new JButton("HELP");
        helpButton.setForeground(Color.WHITE);
	    helpButton.setBorder(BorderFactory.createEmptyBorder());
	    helpButton.setBorderPainted(false);
    	helpButton.setFocusPainted(false);
    	helpButton.setContentAreaFilled(false);

	    try {
	    	Image img = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "help.png"));
	    	helpButton.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

        JButton aboutButton = new JButton("ABOUT");
      	aboutButton.setForeground(Color.WHITE);
	    aboutButton.setBorder(BorderFactory.createEmptyBorder());
	    aboutButton.setBorderPainted(false);
    	aboutButton.setFocusPainted(false);
    	aboutButton.setContentAreaFilled(false);

	    try {
	    	Image img = ImageIO.read(new java.io.FileInputStream(IMG_FOLDER_APP + "about.png"));
	    	aboutButton.setIcon(new ImageIcon(img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

        // startButton.setPreferredSize(new Dimension(90, 30));
        // helpButton.setPreferredSize(new Dimension(90, 30));
        // aboutButton.setPreferredSize(new Dimension(90, 30));

	    GridBagConstraints c = new GridBagConstraints();

	    c.anchor = GridBagConstraints.SOUTH;
	    c.weightx = c.weighty = 1;
	    c.gridheight = 2;
	    c.gridwidth = 6;
	    c.gridx = c.gridy = 0;
	    mainPanel.add(title, c);

	    c.gridheight = 1;
	    c.gridwidth = 2;

	    c.gridx = 0;
	    c.gridy = 5;
	    mainPanel.add(helpButton, c);

	    c.gridx = 2;
	    mainPanel.add(startButton, c);

	    c.gridx = 4;
	    mainPanel.add(aboutButton, c);

	    c.gridx = 1;
	    c.gridy = 1;
	    mainPanel.add(nameLabel, c);

	    c.gridy = 2;
	    mainPanel.add(serverLabel, c);

	    c.gridy = 3;
	    mainPanel.add(portLabel, c);

	    c.gridx = 3;
	    c.gridy = 1;
	    mainPanel.add(namefield, c);

	    c.gridy = 2;
	    mainPanel.add(serverfield, c);

	    c.gridy = 3;
	    mainPanel.add(portfield, c);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	listener.startGame(
            			namefield.getText().equals("") ?
            					"DEFAULT_PLAYER_NAME" :
            					namefield.getText(),
    					serverfield.getText().equals("") ?
            					"localhost" :
            					serverfield.getText(),
            			portfield.getText().equals("") ?
            					"1234" :
            					portfield.getText()
            	);
            }
        });

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		login.setVisible(false);
        		help.setVisible(true);
            }
        });


        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		login.setVisible(false);
        		about.setVisible(true);
            }
        });

        login.setVisible(true);
	}


//Game Window
	private void initGameWindow() {
		frame = new JFrame("1-2-3 PASS! - CMSC 137 Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Removes title bar; so the close (x) button is not seen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(711,400);
        frame.setResizable(false);
        frame.setUndecorated(true);
        //frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setLocationRelativeTo(null);
        // Frame size
        // Panel for cards, Shuffle, and Enter buttons
        panel = new JPanel();
        // Panel for cards buttons only
        JPanel midTop = new JPanel();
        // Panel for Shuffle and Enter buttons
        JPanel midBot = new JPanel();
        panel.setBackground(new Color(53, 101, 77));
        // Creates the card buttons
        button1 = new JButton(new ImageIcon(((new ImageIcon(IMG_FOLDER_CARDS + "BJ.png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH)));
        button2 = new JButton(new ImageIcon(((new ImageIcon(IMG_FOLDER_CARDS + "BJ.png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH)));
        button3 = new JButton(new ImageIcon(((new ImageIcon(IMG_FOLDER_CARDS + "BJ.png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH)));
        button4 = new JButton(new ImageIcon(((new ImageIcon(IMG_FOLDER_CARDS + "BJ.png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH)));
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        // Shuffle button; PENDING JUDGEMENT: Might be removed; Replaced with timer?
        shuffle = new JButton("SHUFFLE");
        // Button design
        shuffle.setBackground(Color.white);
        shuffle.setPreferredSize(new Dimension(150, 40));
        shuffle.setBorder(new LineBorder(new Color(53, 101, 77),1));
        // Enter button
        enter = new JButton("ENTER");
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


        // Panel for optional buttons and connection info
        panel2 = new JPanel();
        panel2.setBackground(new Color(53, 101, 77));
        // Reset button; to revert unlickable buttons
        JButton reset = new JButton("RESET TEST");//supposed place of PAUSE
        reset.setEnabled(false);
        reset.setBackground(Color.white);
        reset.setBorder(new LineBorder(new Color(53, 101, 77),1));
        // Currently a test button for win scenario
        JButton help = new JButton("WIN TEST");//supposed place of HELP
        help.setEnabled(false);
        help.setBackground(Color.white);
        help.setBorder(new LineBorder(new Color(53, 101, 77),1));
        // Working exit button
        exit = new JButton("EXIT");
        exit.setBackground(Color.white);
        exit.setBorder(new LineBorder(new Color(53, 101, 77),1));
        // Text for Player ID and designs
        pId = new JTextField();
        pId.setText("Player ID: XX");
        pId.setEditable(false);
        pId.setForeground(Color.white);
        pId.setBackground(new Color(53, 101, 77));
        pId.setPreferredSize(new Dimension(180, 20));
        pId.setHorizontalAlignment(JTextField.CENTER);
        pId.setBorder(new LineBorder(new Color(53, 101, 77),1));
        // Text for Player count and designs
        JTextField players = new JTextField();
        players.setText("Total Players: XX");
        players.setEditable(false);
        players.setForeground(Color.white);
        players.setBackground(new Color(53, 101, 77));
        players.setPreferredSize(new Dimension(180, 20));
        players.setHorizontalAlignment(JTextField.CENTER);
        players.setBorder(new LineBorder(new Color(53, 101, 77),1));
        // Text for Port ID and designs

        // Panel for player info
        panel3 = new JPanel();
        panel3.setBackground(new Color(53, 101, 77));
        // Layout for top bar that contains player info
        GridLayout topLayout = new GridLayout(1,3);
        panel3.setLayout(topLayout);
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
        // Text for Player name and designs
        name = new JTextField();
        name.setEditable(false);
        name.setForeground(Color.white);
        name.setBackground(new Color(53, 101, 77));
        name.setPreferredSize(new Dimension(180, 50));
        name.setHorizontalAlignment(JTextField.CENTER);
        name.setBorder(new LineBorder(new Color(53, 101, 77),1));
        // Text for Turn number and designs
        turn = new JTextField();
        turn.setEditable(false);
        turn.setForeground(Color.white);
        turn.setBackground(new Color(53, 101, 77));
        turn.setPreferredSize(new Dimension(180, 50));
        turn.setHorizontalAlignment(JTextField.CENTER);
        turn.setBorder(new LineBorder(new Color(53, 101, 77),1));
        // Text for Score and designs
        score = new JTextField();
        score.setEditable(false);
        score.setForeground(Color.white);
        score.setBackground(new Color(53, 101, 77));
        score.setPreferredSize(new Dimension(180, 50));
        score.setHorizontalAlignment(JTextField.CENTER);
        score.setBorder(new LineBorder(new Color(53, 101, 77),1));

        panel3.add(name);
        panel3.add(turn);
        panel3.add(score);

        portId = new JTextField();
        portId.setEditable(false);
        portId.setForeground(Color.white);
        portId.setBackground(new Color(53, 101, 77));
        portId.setPreferredSize(new Dimension(180, 20));
        portId.setHorizontalAlignment(JTextField.CENTER);
        portId.setBorder(new LineBorder(new Color(53, 101, 77),1));
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


        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              listener.pickCard(0);

              button1.setEnabled(false);
              button2.setEnabled(false);
              button3.setEnabled(false);
              button4.setEnabled(false);
              shuffle.setEnabled(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	listener.pickCard(1);

              button1.setEnabled(false);
              button2.setEnabled(false);
              button3.setEnabled(false);
              button4.setEnabled(false);
              shuffle.setEnabled(false);
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.pickCard(2);

              button1.setEnabled(false);
              button2.setEnabled(false);
              button3.setEnabled(false);
              button4.setEnabled(false);
              shuffle.setEnabled(false);
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.pickCard(3);

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
            	listener.shuffle();
            }
        });
        // Press enter for win
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              // This print will be the enter signal from client to server
            	listener.pressedEnter();
            	System.out.println(listener.getPlayerName() + " has pressed Enter!!!");
              	//enter.setBackground(new Color(200, 168, 1));
              	enter.setBackground(new Color(184, 0, 20));
              	enter.setEnabled(false);
              	shuffle.setEnabled(false);
              	exit.setEnabled(true);
            }
        });
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
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              // Game stops cuz someone left; Print placeholder atm
              System.out.println("WARNING: "+ listener.getPlayerName() +" has left the game!");
              listener.exit(true);
            }
        });
	}


//Functions
    public void pressEnter() {
      button1.setEnabled(false);
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      enter.setBackground(new Color(0, 171, 102));
      enter.setEnabled(true);
      exit.setEnabled(false);
      shuffle.setEnabled(false);
    }

	public void setInitialValues(String playerName, String portNumber) {
        name.setText("Name: "+ listener.getPlayerName());
        turn.setText("Turn: XX");
        score.setText("Score: XX");
        portId.setText("Port: " + portNumber);
	}

	public void updateCards(String card1, String card2, String card3, String card4) {
      ImageIcon newImg1 = new ImageIcon(((new ImageIcon(IMG_FOLDER_CARDS+card1+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH));
      ImageIcon newImg2 = new ImageIcon(((new ImageIcon(IMG_FOLDER_CARDS+card2+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH));
      ImageIcon newImg3 = new ImageIcon(((new ImageIcon(IMG_FOLDER_CARDS+card3+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH));
      ImageIcon newImg4 = new ImageIcon(((new ImageIcon(IMG_FOLDER_CARDS+card4+".png")).getImage()).getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH));
      button1.setIcon(newImg1);
      button2.setIcon(newImg2);
      button3.setIcon(newImg3);
      button4.setIcon(newImg4);
      button1.setEnabled(true);
      button2.setEnabled(true);
      button3.setEnabled(true);
      button4.setEnabled(true);
      if (!(card1.equals("BJ")) && !(card2.equals("BJ")) && !(card3.equals("BJ")) && !(card4.equals("BJ"))) shuffle.setEnabled(true);
      frame.repaint();
	}

	@Override
	public void run() {
		login.setVisible(false);
		frame.setVisible(true);
	}

	public void returnToLogin() {
		button1.setEnabled(false);
	    button2.setEnabled(false);
	    button3.setEnabled(false);
	    button4.setEnabled(false);
		frame.setVisible(false);
		login.setVisible(true);
	}

	public void updateUserId(String id) {
		pId.setText("Player ID: " + id);
	}

	public void showPopup(String wl) {
        listener.exit(false);
		(wl.equals("WI")? win : lost).setVisible(true);
	}
}
