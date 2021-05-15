/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babymaze;

/**
 *
 * @author dharm
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BabyMaze extends JFrame implements ActionListener, KeyListener, ChangeListener{

	
//Declaring different objects and variables that make up the program
	private JButton jBActBtn, jBRunBtn, jBResetBtn;
	
//The three main panels within the frame
	private JPanel jPMaze,jPMaze1, jPBottomPanel, jPRightPanel;
   
//Borders for the different panels or anywhere necessary
	Border lightGreyBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

//Icon declaration for different buttons
	private Icon actIcon, runIcon, pauseIcon, resetIcon;
	
//The slider for the bottom panel and labels for the slider
	private JSlider slider;
	private JLabel sliderLabel1, sliderLabel2, sliderLabel3, sliderLabel4, sliderLabel5;
        
//Digital Timer portion	
	private JLabel jLDigitalTimer;
	private JTextField timerHours, timerMinutes, timerSeconds;
	private JLabel jLTimerSeparatorOne, jLTimerSeparatorTwo;
        
//The right panel option, square, direction and score labels and text-fields
	private JLabel jLOption, jLSquare, jLDirection,jLSpeed;
	private JTextField jTFOptionText, jTFSquareText, jTFDirectionText;
	
	
//The up down left right and the empty buttons for the right panel  
	private JButton upButton, leftButton, rightButton, downButton;
	private JButton[] emptyButton = new JButton[5];
        
//The right panel direction icon set to change according to the faced direction
        private JLabel jLDirectionIcon;
        private Icon directionImageWest = new ImageIcon("images/west.jpg");
        private Icon directionImageEast = new ImageIcon("images/east.jpg");
        private Icon directionImageNorth = new ImageIcon("images/north.jpg");
        private Icon directionImageSouth = new ImageIcon("images/south.jpg");

//Buttons
	private JButton jBOption1, jBOption2, jBExit;
	
//The maze 2d array 
	private JLabel[][] jLMazeLabels= new JLabel[20][20];
	Icon babyIcon = new ImageIcon("images/baby.png");
	Icon brickIcon = new ImageIcon("images/brick.png");
        Icon whiteIcon = new ImageIcon("images/white.jpg");

        
        private Timer timerForRun = null;
//Different integers declared for the time counter values	
	private int nextSeconds = 0;
	private int currentSeconds = 0;
	private int currentMinutes = 0;
	private int nextMinutes = 0;
	private int currentHours = 0;
	private int nextHours = 0;
	
	
//The grid-bag layout constraint
	GridBagConstraints c = new GridBagConstraints();
	
//The counter used to move the baby and get its position
	private int xCounter =0, yCounter =0;
		
/*	
 * The constructorThe main frame of the application)
*/
	
	BabyMaze(){
		super ("BabyMaze - Baby Maze Application"); //Sets the title of the program
		setSize(825,585); //Sets the size of the program to 825x585
		setIconImage(new ImageIcon("images/greenfoot.png").getImage()); //Sets the dock icon
		setLayout(null); //Sets the layout of the main frame to null
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Sets default close operation as exit
		setResizable(false); //Makes the window non-resizable
		setLocationRelativeTo(null); //Brings the window to the monitor's centre
                createjPMazevertical();
		createjPBottomPanel();
		createjPRightPanelvertical();
		addKeyListener(this); //Adds keylistener for this jframe
		setFocusable(true); //Makes this frame focusable
		setVisible(true); //Makes this frame visible
	}
        
        BabyMaze(String horizontal){
		super ("BabyMaze - Baby Maze Application"); //Sets the title of the program
		setSize(825,585); //Sets the size of the program to 825x585
		setIconImage(new ImageIcon("images/greenfoot.png").getImage()); //Sets the dock icon
		setLayout(null); //Sets the layout of the main frame to null
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Sets default close operation as exit
		setResizable(false); //Makes the window non-resizable
		setLocationRelativeTo(null); //Brings the window to the monitor's centre
	        createjPMazehorizontal();
		createjPBottomPanel();
		createjPRightPanelhorizontal();
		addKeyListener(this); //Adds keylistener for this jframe
		setFocusable(true); //Makes this frame focusable
		setVisible(true); //Makes this frame visible
	}

	

/* ------------------------- THE  MAIN METHOD -------------------------*/
	public static void main(String[] args) {
		new BabyMaze();
	}

	
//This method creates maze panel
	private void createjPMazevertical() {
		Container window = getContentPane();
		jPMaze = new JPanel();
		jPMaze.setBackground(Color.black);
		jPMaze.setBounds(50,50,520,425);
		jPMaze.setBorder(lightGreyBorder);
		jPMaze.setLayout(new GridBagLayout());
		createjPMazeLayoutVertical();
		window.add(jPMaze);
	}
        private void createjPMazehorizontal() {
		Container window = getContentPane();
		jPMaze1 = new JPanel();
		jPMaze1.setBackground(Color.black);
		jPMaze1.setBounds(50,50,520,425);
		jPMaze1.setBorder(lightGreyBorder);
		jPMaze1.setLayout(new GridBagLayout());
                createjPMazeLayouthorizontal();
		window.add(jPMaze1);
	}
	
//This method creates the maze panel's layout vertical
	private void createjPMazeLayoutVertical() {
		c.fill = GridBagConstraints.VERTICAL;
	
	
                //column 1
                c.gridx =0;
		c.gridy =0;
		for (int i =0; i<1; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(babyIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i =1; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 2
                c.gridx =1;
		c.gridy =0;
                for (int i =0; i<6; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                for (int i =6; i<7; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                for (int i =7; i<13; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 3
                c.gridx =2;
		c.gridy =0;
		for (int i =0; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 4
                c.gridx =3;
		c.gridy =0;
		for (int i =0; i<3; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                for (int i=3; i<4; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i=4; i<13; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 5
                c.gridx =4;
		c.gridy =0;
		for (int i =0; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                //column 6
                c.gridx =5;
		c.gridy =0;
		for (int i =0; i<5; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                 for (int i=5; i<6; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i=6; i<13; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 7
                c.gridx =6;
		c.gridy =0;
		for (int i =0; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 8
                c.gridx =7;
		c.gridy =0;
		for (int i =0; i<10; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i=10; i<11; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i=11; i<13; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                
                //column 9
                c.gridx =8;
		c.gridy =0;
		for (int i =0; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 10
                c.gridx =9;
		c.gridy =0;
		for (int i =0; i<5; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		} 
                for (int i=5; i<6; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i=6; i<13; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                
                
                
                //column 11
                c.gridx =10;
		c.gridy =0;
		for (int i =0; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 12
                c.gridx =11;
		c.gridy =0;
		for (int i =0; i<1; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i=1; i<2; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i=2; i<13; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                
                
                
                //column 13
                c.gridx =12;
		c.gridy =0;
		for (int i =0; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                //column 14
                c.gridx =13;
		c.gridy =0;
		for (int i =0; i<1; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i =1; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                
                
                
                
                //column 15
                c.gridx =14;
		c.gridy =0;
		for (int i =0; i<13; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                
                //column 16
                c.gridx =15;
		c.gridy =0;
		for (int i =0; i<12; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
                for (int i=12; i<13; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridy++;
		}
	}
 
        private void createjPMazeLayouthorizontal() {
		c.fill = GridBagConstraints.HORIZONTAL;
	
	
                //column 1
                c.gridx =0;
		c.gridy =0;
		for (int i =0; i<1; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(babyIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                for (int i =1; i<16; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                //column 2
                c.gridx =0;
		c.gridy =1;
                for (int i =0; i<6; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                for (int i =6; i<7; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                for (int i =7; i<16; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                //column 3
                c.gridx =0;
		c.gridy =2;
		for (int i =0; i<16; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                //column 4
                c.gridx =0;
		c.gridy =3;
		for (int i =0; i<3; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                for (int i=3; i<4; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                for (int i=4; i<16; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                //column 5
                c.gridx =0;
		c.gridy =4;
		for (int i =0; i<16; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                //column 6
                c.gridx =0;
		c.gridy =5;
		for (int i =0; i<5; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                 for (int i=5; i<6; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                for (int i=6; i<16; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                //column 7
                c.gridx =0;
		c.gridy =6;
		for (int i =0; i<16; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                //column 8
                c.gridx =0;
		c.gridy =7;
		for (int i =0; i<10; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                for (int i=10; i<11; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                for (int i=11; i<16; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                
                //column 9
                c.gridx =0;
		c.gridy =8;
		for (int i =0; i<16; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                //column 10
                c.gridx =0;
		c.gridy =9;
		for (int i =0; i<5; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		} 
                for (int i=5; i<6; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                for (int i=6; i<16; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                
                
                
                //column 11
                c.gridx =0;
		c.gridy =10;
		for (int i =0; i<16; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                //column 12
                c.gridx =0;
		c.gridy =11;
		for (int i =0; i<1; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                for (int i=1; i<2; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                for (int i=2; i<16; i++){
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(brickIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
                
                
                
                
                //column 13
                c.gridx =0;
		c.gridy =12;
		for (int i =0; i<16; i++) {
			jLMazeLabels[c.gridx][c.gridy] = new JLabel(whiteIcon);
			jPMaze1.add(jLMazeLabels[c.gridx][c.gridy], c);
			c.gridx++;
		}
               
	}


/*
 *  This method creates the Bottom Panel, also adds buttons and slider to the panel.
 */
	private void createjPBottomPanel() {		
		Container window = getContentPane();
		jPBottomPanel = new JPanel();

                //Icons for the buttons	
		actIcon = new ImageIcon("images/step.png");
		runIcon = new ImageIcon("images/run.png");
		pauseIcon = new ImageIcon("images/pause.png");
		resetIcon = new ImageIcon("images/reset.png");
		
                //Act button
		jBActBtn = new JButton("Act", actIcon);
		jBActBtn.setBackground(Color.white);
		jBActBtn.addActionListener(this);
		jBActBtn.setBounds(80,15, 90, 30);
		jBActBtn.addKeyListener(this);

                //Run button	
		jBRunBtn = new JButton("Run");
		jBRunBtn.setIcon(runIcon);
		jBRunBtn.addActionListener(this);
		jBRunBtn.addKeyListener(this);
		jBRunBtn.setBackground(Color.white);
		jBRunBtn.setBounds(180,15, 90, 30);	
				
                //Reset button		
		jBResetBtn = new JButton("Reset", resetIcon);
		jBResetBtn.addActionListener(this);
		jBResetBtn.setBackground(Color.white);
		jBResetBtn.setBounds(280,15, 90, 30);	
		
                //Slider
		slider = new JSlider(1, 1500);
		slider.setBounds(550,10, 200, 30);
		slider.addChangeListener(this);
		
                //Slider labels	CSS has been used for coloring the labels
		sliderLabel1 = new JLabel("<html><font style = \" color: gray; font-size: 7px; \">|</font>");
		sliderLabel2 = new JLabel("<html><font style = \" color: gray; font-size: 7px; \">|</font>");
		sliderLabel3 = new JLabel("<html><font style = \" color: gray; font-size: 7px; \">|</font>");
		sliderLabel4 = new JLabel("<html><font style = \" color: gray; font-size: 7px; \">|</font>");
		sliderLabel5 = new JLabel("<html><font style = \" color: gray; font-size: 7px; \">|</font>");
	
		sliderLabel1.setBounds(557,20, 10, 40);
		sliderLabel2.setBounds(602,20, 10, 40);
		sliderLabel3.setBounds(649,20, 10, 40);
		sliderLabel4.setBounds(695,20, 10, 40);
		sliderLabel5.setBounds(740,20, 10, 40);
        
                //Adding speed level
                jLSpeed=new JLabel("Speed:");
                jLSpeed.setBounds(500,10, 200, 30);
                        
		jPBottomPanel.add(jBActBtn);
		jPBottomPanel.add(jBRunBtn);
		jPBottomPanel.add(jBResetBtn);
		jPBottomPanel.add(slider);
		jPBottomPanel.add(sliderLabel1);
		jPBottomPanel.add(sliderLabel2);
		jPBottomPanel.add(sliderLabel3);
		jPBottomPanel.add(sliderLabel4);
		jPBottomPanel.add(sliderLabel5);
                jPBottomPanel.add(jLSpeed);
                
		jPBottomPanel.setLayout(null);
		jPBottomPanel.setBorder(lightGreyBorder);
		jPBottomPanel.setBounds(0, 499,815, 120);
		
		window.add(jPBottomPanel);

	}

/*
 * This method creates the right panel, also adding different components to it.	
 */
	
	private void createjPRightPanelvertical() {
		Container window = getContentPane();
		jPRightPanel = new JPanel();
           
		
                //Panel three the first three labels and TextFields
		jLOption = new JLabel("Option: ");
		jLSquare = new JLabel("Square: ");
		jLDirection = new JLabel("Direction: ");
	

		
		jLOption.setBounds(25,65, 70, 30);
		jLSquare.setBounds(25,105, 70, 30);
		jLDirection.setBounds(25,145, 70, 30);
	
		
		jTFOptionText = new JTextField("Vertical");
		jTFSquareText = new JTextField("0, 0");
		jTFDirectionText = new JTextField("S");
		
		jTFOptionText.setEditable(false);
		jTFSquareText.setEditable(false);
		jTFDirectionText.setEditable(false);
		
		jTFOptionText.setBackground(Color.WHITE);
		jTFSquareText.setBackground(Color.WHITE);
		jTFDirectionText.setBackground(Color.WHITE);
		
		
		jTFOptionText.setBounds(100,65, 70, 30);
		jTFSquareText.setBounds(100,105, 70, 30);
		jTFDirectionText.setBounds(100,145, 70, 30);
		
		
		jPRightPanel.add(jLOption);
		jPRightPanel.add(jLSquare);
		jPRightPanel.add(jLDirection);
		
		
		jPRightPanel.add(jTFOptionText);
		jPRightPanel.add(jTFSquareText);
		jPRightPanel.add(jTFDirectionText);
		
		
		
	//Panel Three digital timer labels and text-fields
		jLDigitalTimer = new JLabel("DIGITAL TIMER");
		jLDigitalTimer.setBounds(50,5, 150, 25);
		
		timerHours = new JTextField("00");
		timerHours.setForeground(Color.white);
		timerHours.setBorder(etchedBorder);
		timerHours.setBackground(Color.black);
		timerHours.setBounds(35,30, 35, 25);
		
		jLTimerSeparatorOne = new JLabel(":");
		jLTimerSeparatorOne.setBounds(75,30, 5, 25);
		
		timerMinutes = new JTextField("00");
		timerMinutes.setForeground(Color.white);
		timerMinutes.setBorder(etchedBorder);
		timerMinutes.setBackground(Color.black);
		timerMinutes.setBounds(85,30, 35, 25);
				
		jLTimerSeparatorTwo = new JLabel(":");
		jLTimerSeparatorTwo.setBounds(125,30, 5, 25);
		
		timerSeconds = new JTextField("00");
		timerSeconds.setForeground(Color.white);
		timerSeconds.setBorder(etchedBorder);
		timerSeconds.setBackground(Color.black);
		timerSeconds.setBounds(135,30, 35, 25);

		timerSeconds.setEditable(false);
		timerMinutes.setEditable(false);
		timerHours.setEditable(false);
		
		jPRightPanel.add(jLDigitalTimer);
		jPRightPanel.add(timerHours);
		jPRightPanel.add(timerMinutes);
		jPRightPanel.add(timerSeconds);
		jPRightPanel.add(jLTimerSeparatorOne);
		jPRightPanel.add(jLTimerSeparatorTwo);
		
	//Panel  arrow buttons
		upButton = new JButton("^");
		upButton.addActionListener(this);
		leftButton = new JButton("<");
		leftButton.addActionListener(this);
		rightButton = new JButton(">");
		rightButton.addActionListener(this);
		downButton = new JButton("v");
		downButton.addActionListener(this);
		
		upButton.setBackground(Color.white);
		leftButton.setBackground(Color.white);
		rightButton.setBackground(Color.white);
		downButton.setBackground(Color.white);
		
		upButton.setBounds(80,180, 45, 25);
		leftButton.setBounds(35, 205, 45, 25);
		rightButton.setBounds(125, 205, 45, 25);
		downButton.setBounds(80, 230, 45, 25);
	
		jPRightPanel.add(upButton);
		jPRightPanel.add(leftButton);
		jPRightPanel.add(rightButton);
		jPRightPanel.add(downButton);

	//Panel  empty buttons
		for (int i=0; i<5; i++) {
			emptyButton[i]= new JButton();
			emptyButton[i].setEnabled(false);
			jPRightPanel.add(emptyButton[i]);
					
		}
		emptyButton[0].setBounds(35, 180, 45, 25);
		emptyButton[1].setBounds(125, 180, 45, 25);
		emptyButton[2].setBounds(80, 205, 45, 25);
		emptyButton[3].setBounds(35, 230, 45, 25);
		emptyButton[4].setBounds(125,230, 45, 25);
                emptyButton[2].setIcon(babyIcon);
		emptyButton[2].setEnabled(true);
                
	//Panel Three option buttons	
		jBOption1 = new JButton("Vertical");
		jBOption1.setFont(new Font("Serif", Font.BOLD, 10));
		jBOption1.addActionListener(this);
		jBOption2 = new JButton("Horizontal");
		jBOption2.setFont(new Font("Serif", Font.BOLD, 10));
		jBOption2.addActionListener(this);
		
		jBExit = new JButton("Exit");
		jBExit.setFont(new Font("Serif", Font.BOLD, 10));
		jBExit.addActionListener(this);
		
		jBOption1.setBounds(50,370,100, 30);
                jBOption1.setBackground(Color.white);
                jBOption1.setBorderPainted(false);
		jBOption2.setBounds(50,410, 100, 30);
                jBOption2.setBackground(Color.white);
                jBOption2.setBorderPainted(false);
		jBExit.setBounds(50,450,100, 30);
		jBExit.setBackground(Color.white);
                jBExit.setBorderPainted(false);
                
		jPRightPanel.add(jBOption1);
		jPRightPanel.add(jBOption2);
		
		jPRightPanel.add(jBExit);
		
	//Panel Three direction image
		jLDirectionIcon = new JLabel(directionImageWest);
		jLDirectionIcon.setBorder(lightGreyBorder);
		jLDirectionIcon.setBounds(50,260, 100, 100);
		
		jPRightPanel.add(jLDirectionIcon);
		
		jPRightPanel.setLayout(null);
		jPRightPanel.setBorder(lightGreyBorder);
		jPRightPanel.setBounds(625, 2,190,495);
		window.add(jPRightPanel);
	}
        private void createjPRightPanelhorizontal() {
		Container window = getContentPane();
		jPRightPanel = new JPanel();
           
		
                //Panel three the first three labels and TextFields
		jLOption = new JLabel("Option: ");
		jLSquare = new JLabel("Square: ");
		jLDirection = new JLabel("Direction: ");
	

		
		jLOption.setBounds(25,65, 70, 30);
		jLSquare.setBounds(25,105, 70, 30);
		jLDirection.setBounds(25,145, 70, 30);
	
		
		jTFOptionText = new JTextField("Horizontal");
		jTFSquareText = new JTextField("0, 0");
		jTFDirectionText = new JTextField("E");
		
		jTFOptionText.setEditable(false);
		jTFSquareText.setEditable(false);
		jTFDirectionText.setEditable(false);
		
		jTFOptionText.setBackground(Color.WHITE);
		jTFSquareText.setBackground(Color.WHITE);
		jTFDirectionText.setBackground(Color.WHITE);
		
		
		jTFOptionText.setBounds(100,65, 70, 30);
		jTFSquareText.setBounds(100,105, 70, 30);
		jTFDirectionText.setBounds(100,145, 70, 30);
		
		
		jPRightPanel.add(jLOption);
		jPRightPanel.add(jLSquare);
		jPRightPanel.add(jLDirection);
		
		
		jPRightPanel.add(jTFOptionText);
		jPRightPanel.add(jTFSquareText);
		jPRightPanel.add(jTFDirectionText);
		
		
		
	//Panel Three digital timer labels and text-fields
		jLDigitalTimer = new JLabel("DIGITAL TIMER");
		jLDigitalTimer.setBounds(50,5, 150, 25);
		
		timerHours = new JTextField("00");
		timerHours.setForeground(Color.white);
		timerHours.setBorder(etchedBorder);
		timerHours.setBackground(Color.black);
		timerHours.setBounds(35,30, 35, 25);
		
		jLTimerSeparatorOne = new JLabel(":");
		jLTimerSeparatorOne.setBounds(75,30, 5, 25);
		
		timerMinutes = new JTextField("00");
		timerMinutes.setForeground(Color.white);
		timerMinutes.setBorder(etchedBorder);
		timerMinutes.setBackground(Color.black);
		timerMinutes.setBounds(85,30, 35, 25);
				
		jLTimerSeparatorTwo = new JLabel(":");
		jLTimerSeparatorTwo.setBounds(125,30, 5, 25);
		
		timerSeconds = new JTextField("00");
		timerSeconds.setForeground(Color.white);
		timerSeconds.setBorder(etchedBorder);
		timerSeconds.setBackground(Color.black);
		timerSeconds.setBounds(135,30, 35, 25);

		timerSeconds.setEditable(false);
		timerMinutes.setEditable(false);
		timerHours.setEditable(false);
		
		jPRightPanel.add(jLDigitalTimer);
		jPRightPanel.add(timerHours);
		jPRightPanel.add(timerMinutes);
		jPRightPanel.add(timerSeconds);
		jPRightPanel.add(jLTimerSeparatorOne);
		jPRightPanel.add(jLTimerSeparatorTwo);
		
	//Panel  arrow buttons
		upButton = new JButton("^");
		upButton.addActionListener(this);
		leftButton = new JButton("<");
		leftButton.addActionListener(this);
		rightButton = new JButton(">");
		rightButton.addActionListener(this);
		downButton = new JButton("v");
		downButton.addActionListener(this);
		
		upButton.setBackground(Color.white);
		leftButton.setBackground(Color.white);
		rightButton.setBackground(Color.white);
		downButton.setBackground(Color.white);
		
		upButton.setBounds(80,180, 45, 25);
		leftButton.setBounds(35, 205, 45, 25);
		rightButton.setBounds(125, 205, 45, 25);
		downButton.setBounds(80, 230, 45, 25);
	
		jPRightPanel.add(upButton);
		jPRightPanel.add(leftButton);
		jPRightPanel.add(rightButton);
		jPRightPanel.add(downButton);

	//Panel  empty buttons
		for (int i=0; i<5; i++) {
			emptyButton[i]= new JButton();
			emptyButton[i].setEnabled(false);
			jPRightPanel.add(emptyButton[i]);
					
		}
		emptyButton[0].setBounds(35, 180, 45, 25);
		emptyButton[1].setBounds(125, 180, 45, 25);
		emptyButton[2].setBounds(80, 205, 45, 25);
		emptyButton[3].setBounds(35, 230, 45, 25);
		emptyButton[4].setBounds(125,230, 45, 25);
                emptyButton[2].setIcon(babyIcon);
		emptyButton[2].setEnabled(true);
                
	//Panel Three option buttons	
		jBOption1 = new JButton("Vertical");
		jBOption1.setFont(new Font("Serif", Font.BOLD, 10));
		jBOption1.addActionListener(this);
		jBOption2 = new JButton("Horizontal");
		jBOption2.setFont(new Font("Serif", Font.BOLD, 10));
		jBOption2.addActionListener(this);
		
		jBExit = new JButton("Exit");
		jBExit.setFont(new Font("Serif", Font.BOLD, 10));
		jBExit.addActionListener(this);
		
		jBOption1.setBounds(50,370,100, 30);
                jBOption1.setBackground(Color.white);
                jBOption1.setBorderPainted(false);
		jBOption2.setBounds(50,410, 100, 30);
                jBOption2.setBackground(Color.white);
                jBOption2.setBorderPainted(false);
		jBExit.setBounds(50,450,100, 30);
		jBExit.setBackground(Color.white);
                jBExit.setBorderPainted(false);
                
		jPRightPanel.add(jBOption1);
		jPRightPanel.add(jBOption2);
		
		jPRightPanel.add(jBExit);
		
	//Panel Three direction image
		jLDirectionIcon = new JLabel(directionImageWest);
		jLDirectionIcon.setBorder(lightGreyBorder);
		jLDirectionIcon.setBounds(50,260, 100, 100);
		
		jPRightPanel.add(jLDirectionIcon);
		
		jPRightPanel.setLayout(null);
		jPRightPanel.setBorder(lightGreyBorder);
		jPRightPanel.setBounds(625, 2,190,495);
		window.add(jPRightPanel);
	}

	
        //Moving baby to the Left With the respective button or method calls
	private void moveBabyLeft() {
		if (xCounter>0) {
			JLabel jLCheck = jLMazeLabels[xCounter-1][yCounter];
			if(jLCheck != null && (jLCheck.getIcon().equals(whiteIcon))) {
				jLMazeLabels[xCounter][yCounter].setIcon(whiteIcon);
				jLMazeLabels[xCounter-1][yCounter].setIcon(babyIcon);
				xCounter--;
				jLDirectionIcon.setIcon(directionImageWest);
				jTFDirectionText.setText("W");
				jTFSquareText.setText(""+xCounter+", "+yCounter);
			}
		}
	}

       //Moving baby to the Right With the respective button or method calls
	private void moveBabyRight() {
		JLabel jLCheck =jLMazeLabels[xCounter+1][yCounter];
		if(jLCheck != null && (jLCheck.getIcon().equals(whiteIcon))) {
			jLMazeLabels[xCounter][yCounter].setIcon(whiteIcon);
			jLMazeLabels[xCounter+1][yCounter].setIcon(babyIcon);
			xCounter++;
			jLDirectionIcon.setIcon(directionImageEast);
			jTFDirectionText.setText("E");
			jTFSquareText.setText(""+xCounter+", "+yCounter);
		}
	}
	
        //Moving baby downwards With the respective button or method calls
	private void moveBabyDown() {	
		JLabel jLCheck =jLMazeLabels[xCounter][yCounter+1];
		if(jLCheck != null && (jLCheck.getIcon().equals(whiteIcon))) {
			jLMazeLabels[xCounter][yCounter].setIcon(whiteIcon);
			jLMazeLabels[xCounter][yCounter+1].setIcon(babyIcon);
			yCounter++;
			jLDirectionIcon.setIcon(directionImageSouth);
			jTFDirectionText.setText("S");
			jTFSquareText.setText(""+xCounter+", "+yCounter);
		}	
	
	}
	
        //Moving baby upwards With the respective button or method calls
	private void moveBabyUp() {
		if (yCounter>0) {
			JLabel jLCheck = jLMazeLabels[xCounter][yCounter-1];
			if(jLCheck != null && (jLCheck.getIcon().equals(whiteIcon))) {
				jLMazeLabels[xCounter][yCounter].setIcon(whiteIcon);
				jLMazeLabels[xCounter][yCounter-1].setIcon(babyIcon);
				yCounter--;
				jLDirectionIcon.setIcon(directionImageNorth);
				jTFDirectionText.setText("N");
				jTFSquareText.setText(""+xCounter+", "+yCounter);
			}
		}
	}
	

	
//Exit and restart the game	
	private void reset() {
		dispose();
    	        new BabyMaze();
	}

    //Start Digital Timer (This happens when the run button is clicked).
    private void startTimer() {
            currentSeconds = Integer.parseInt(timerSeconds.getText());
            currentMinutes = Integer.parseInt(timerMinutes.getText());
            currentHours = Integer.parseInt(timerHours.getText());

            nextSeconds = currentSeconds + 1;
            if(nextSeconds<10)timerSeconds.setText("0" + String.valueOf(nextSeconds));
            else timerSeconds.setText(String.valueOf(nextSeconds));

            if(nextSeconds>59) {
                    nextSeconds = 0;
                    timerSeconds.setText("0" + String.valueOf(nextSeconds));

                    nextMinutes = currentMinutes + 1;
                    if(nextMinutes<10)timerMinutes.setText("0" + String.valueOf(nextMinutes));
                    else timerMinutes.setText(String.valueOf(nextMinutes));
            }

            if(nextMinutes>59) {
                    nextMinutes = 0;
                    timerMinutes.setText("0" + String.valueOf(nextMinutes));

                    nextHours = currentHours + 1;
                    if(nextHours <10)timerHours .setText("0" + String.valueOf(nextHours));
                    else timerHours .setText(String.valueOf(nextHours));

            }
	}	
        //start timer
        private void run() {
		jBRunBtn.setText("Pause");
		jBRunBtn.setIcon(pauseIcon);
		
		timerForRun = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent runClick) {
				startTimer();
			}
		});
		
		
	    timerForRun.start();

	}
	
        //Pause the timer
	private void pause() {
		jBRunBtn.setText("Run");
		jBRunBtn.setIcon(runIcon);
		if (timerForRun!=null && timerForRun.isRunning())timerForRun.stop();
	}

//Different action listener events for different buttons
	public void actionPerformed(ActionEvent click){
		if (click.getActionCommand().equals("Quit")||click.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		
		if(click.getActionCommand().equals("Reset")){
			reset();
		}
		
		if(click.getActionCommand().equals("<")) {
			moveBabyLeft();
		}
		
		if(click.getActionCommand().equals(">")) {
			moveBabyRight();
		}
		
		if(click.getActionCommand().equals("v")) {
			moveBabyDown();
		}
		
		if(click.getActionCommand().equals("^")) {
			moveBabyUp();
		}
                
		if(click.getSource().equals(jBOption1)) {
                        dispose();
                        new BabyMaze();
		}
		if(click.getSource().equals(jBOption2)) {
                        dispose();
    	                new BabyMaze("horizontal");  
		}
                if (click.getActionCommand().equals("Run")) {
                        run();
		}
		if (click.getActionCommand().equals("Pause")) {
			pause();
		}

	}

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    
    }


}
