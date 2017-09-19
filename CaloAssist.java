

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class CaloAssist implements ActionListener {

	// main container
	JFrame frame;

	//Attributes for home page.
	JButton[] btns;
	JLabel[] labels;
	JPanel panel;

	//Attributes for advanced page.
	JPanel advancedPanel;
	JButton[] advBtn;
	JLabel advLabel;

	// panel for SI
	JPanel siPanel;
	JLabel[] siLabel;
	JButton[] siBtn;
	JTextField[] siTextField;

	//panel for CI
	JPanel ciPanel;
	JLabel[] ciLabel;
	JButton[] ciBtn;
	JTextField[] ciTextField;
	JComboBox<String> comboBox;
	//It is for interest scheme like yearly,half-yearly and quarterly and default for yearly scheme.
	static int scheme = 1;

	// Card for containing all the panels
	JPanel card;

	// instantiate card Layout object.
	CardLayout cardL = new CardLayout();


	public CaloAssist(String str) {

		frame = new JFrame(str);

		// panel 1
		panel = new JPanel();
		labels = new JLabel[2];
		labels[0] = new JLabel("Welcome");
		labels[0].setBackground(Color.RED);
		labels[0].setBounds(100,100,150,50);
		labels[0].setFont(new Font("ariel",Font.PLAIN,30));

		labels[1] = new JLabel("Choose Operation you need");
		labels[1].setBackground(Color.RED);
		labels[1].setBounds(50,160,250,50);
		labels[1].setFont(new Font("ariel",Font.PLAIN,20));

		panel.add(labels[0]);
		panel.add(labels[1]);

		btns = new JButton[2];

		btns[0] = new JButton("Simple");
		btns[1] = new JButton("Advanced");

		btns[0].setBounds(60,230,80,40);
		btns[1].setBounds(190,230,90,40);

		btns[0].addActionListener(this);
		btns[1].addActionListener(this);

		panel.add(btns[0]);
		panel.add(btns[1]);
		panel.setLayout(null);

		//setting advanced Panel.
		setAdvancedPanelGUI();

		//setting SI Panel
		setSIPanelGUI();

		//setting CI Panel.
		setCIPanelGUI();

		//calculator panel		
		CalPanel calC = new CalPanel();
		Button home  = new Button("Home");
	  	home.setBounds(30,0,50,30);
	  	home.addActionListener(this);
	  	calC.add(home);
	  	calC.setLayout(null);

	  	// card.
	  	card = new JPanel();
		card.setLayout(cardL);

		card.add(panel,"Home");
		card.add(calC,"Simple");
		card.add(advancedPanel,"Advanced");
		card.add(siPanel,"SI");
		card.add(ciPanel,"CI");

        frame.add(card);
		
		//setting icon to the application.
		Image icon = Toolkit.getDefaultToolkit().getImage("App_logo.png");
		frame.setIconImage(icon);

		frame.setSize(350,450);
		frame.setResizable(false);
		frame.setLocation(400,150);
		frame.setVisible(true);

		// Closing of frame.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setAdvancedPanelGUI() {
		advancedPanel = new JPanel();

		advBtn = new JButton[3];

		advBtn[0] = new JButton("Home");
		advBtn[1] = new JButton("Simple Interest");
		advBtn[2] = new JButton("Compound Interest");

		advBtn[1].setActionCommand("SI");
		advBtn[2].setActionCommand("CI");

		advBtn[0].setBounds(0,0,70,30);
		advBtn[1].setBounds(100,170,150,50);
		advBtn[2].setBounds(100,250,150,50);

		advBtn[0].addActionListener(this);
		advBtn[1].addActionListener(this);
		advBtn[2].addActionListener(this);

		advancedPanel.add(advBtn[0]);
		advancedPanel.add(advBtn[1]);
		advancedPanel.add(advBtn[2]);

		advLabel = new JLabel("Choose below to calculate");
		advLabel.setBounds(30,100,300,50);
		advLabel.setFont(new Font("ariel",Font.PLAIN,25));
		advancedPanel.add(advLabel);

		advancedPanel.setLayout(null);

	}

	public void setSIPanelGUI() {
		siPanel = new JPanel();

		//adding labels
		siLabel = new JLabel[4];

		siLabel[0] = new JLabel("Principal amount");
		siLabel[1] = new JLabel("Rate of Interest");
		siLabel[2] = new JLabel("Time period");
		siLabel[3] = new JLabel("Simple Interest");

		siLabel[0].setBounds(40,80,150,30);
		siLabel[1].setBounds(40,130,150,30);
		siLabel[2].setBounds(40,180,150,30);
		siLabel[3].setBounds(40,330,120,30);

		siLabel[0].setFont(new Font("ariel",Font.PLAIN,15));
		siLabel[1].setFont(new Font("ariel",Font.PLAIN,15));
		siLabel[2].setFont(new Font("ariel",Font.PLAIN,15));
		siLabel[3].setFont(new Font("ariel",Font.PLAIN,15));

		siPanel.add(siLabel[0]);
		siPanel.add(siLabel[1]);
		siPanel.add(siLabel[2]);
		siPanel.add(siLabel[3]);

		//adding text fields
		siTextField = new JTextField[4];

		siTextField[0] = new JTextField();
		siTextField[1] = new JTextField();
		siTextField[2] = new JTextField();
		siTextField[3] = new JTextField();

		siTextField[0].setBounds(200,80,120,30);
		siTextField[1].setBounds(200,130,120,30);
		siTextField[2].setBounds(200,180,120,30);
		siTextField[3].setBounds(170,330,150,30);


		siTextField[3].setEditable(false);
		siTextField[3].setBackground(Color.WHITE);
		siTextField[3].setHorizontalAlignment(JTextField.LEFT);

		siPanel.add(siTextField[0]);
		siPanel.add(siTextField[1]);
		siPanel.add(siTextField[2]);
		siPanel.add(siTextField[3]);

		//adding btns
		siBtn = new JButton[3];

		siBtn[0] = new JButton("Back");
		siBtn[1] = new JButton("Calculate");
		siBtn[2] = new JButton("Clear");

		siBtn[0].setBounds(0,0,70,30);
		siBtn[1].setBounds(90,250,150,40);
		siBtn[2].setBounds(250,380,70,30);

		siBtn[1].setFont(new Font("ariel",Font.PLAIN,20));

		siBtn[0].addActionListener(this);
		siBtn[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String str1, str2, str3;

				str1 = siTextField[0].getText();
				str2 = siTextField[1].getText();
				str3 = siTextField[2].getText();

				Double p, r, t;

				if( str1.equals("") ) {
					JOptionPane.showMessageDialog(frame,"Enter Principal amount","Warning",JOptionPane.WARNING_MESSAGE);
					siTextField[0].grabFocus();
					return;
				}
				try {

					p = Double.parseDouble(str1);

					if(p < 0) {
						JOptionPane.showMessageDialog(frame,"Principal amount should be positive","Warning",JOptionPane.WARNING_MESSAGE);
						siTextField[0].grabFocus();
						return;
					}
					 
				} catch(Exception excp){
					JOptionPane.showMessageDialog(frame,"Enter numeric value","Warning",JOptionPane.WARNING_MESSAGE);
					siTextField[0].grabFocus();
					return;
				}

				if( str2.equals("") ) {
					JOptionPane.showMessageDialog(frame,"Enter rate of interest","Warning",JOptionPane.WARNING_MESSAGE);
					siTextField[1].grabFocus();
					return;
				}
				try{
					 r = Double.parseDouble(str2);

					 r = r/100;

					 if(r < 0) {
						JOptionPane.showMessageDialog(frame,"Rate of interest should be positive","Warning",JOptionPane.WARNING_MESSAGE);
						siTextField[1].grabFocus();
						return;
					}
				} catch(Exception excp) {
					JOptionPane.showMessageDialog(frame,"Enter numeric value","Warning",JOptionPane.WARNING_MESSAGE);
					siTextField[1].grabFocus();
					return;
				}

				if( str3.equals("") ) {
					JOptionPane.showMessageDialog(frame,"Enter time period","Warning",JOptionPane.WARNING_MESSAGE);
					siTextField[2].grabFocus();
					return;
				}
				try {
					t = Double.parseDouble(str3);

					if(t < 0) {
						JOptionPane.showMessageDialog(frame,"Time period should be positive","Warning",JOptionPane.WARNING_MESSAGE);
						siTextField[2].grabFocus();
						return;
					}
				} catch(Exception excp) {
					JOptionPane.showMessageDialog(frame,"Enter numeric value","Warning",JOptionPane.WARNING_MESSAGE);
					siTextField[2].grabFocus();
					return;
				}

					
				Double res = (p*r*t)/100;

				String s = ""+res;

				if(s.lastIndexOf(".0") > 0){
					s = s.substring(0,s.length()-2);
				}

				siTextField[3].setText(s);
			
			}
		});

		// set clear/flush all the fields.
		siBtn[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				siTextField[0].setText("");
				siTextField[1].setText("");
				siTextField[2].setText("");
				siTextField[3].setText("");
			}
		});

		siPanel.add(siBtn[0]);
		siPanel.add(siBtn[1]);
		siPanel.add(siBtn[2]);

		siPanel.setLayout(null);

	}

	public void setCIPanelGUI() {

		
		ciPanel = new JPanel();

		// adding labels
		ciLabel = new JLabel[5];


		ciLabel[0] = new JLabel("Principal amount");
		ciLabel[1] = new JLabel("Compound Interest rate ");
		ciLabel[2] = new JLabel("Number of periods");
		ciLabel[3] = new JLabel("Compound Interest");
		//label for JComboBox
		ciLabel[4] = new JLabel("Interest is calculated");

		ciLabel[0].setBounds(20,110,170,30);
		ciLabel[1].setBounds(20,160,170,30);
		ciLabel[2].setBounds(20,210,170,30);
		ciLabel[3].setBounds(20,340,140,30);
		//setting position 
		ciLabel[4].setBounds(20,60,170,30);

		ciLabel[0].setFont(new Font("ariel",Font.PLAIN,15));
		ciLabel[1].setFont(new Font("ariel",Font.PLAIN,15));
		ciLabel[2].setFont(new Font("ariel",Font.PLAIN,15));
		ciLabel[3].setFont(new Font("ariel",Font.PLAIN,15));
		//setting text field.
		ciLabel[4].setFont(new Font("ariel",Font.PLAIN,15));



		ciPanel.add(ciLabel[0]);
		ciPanel.add(ciLabel[1]);
		ciPanel.add(ciLabel[2]);
		ciPanel.add(ciLabel[3]);
		ciPanel.add(ciLabel[4]);


		//adding Combo box
		comboBox = new JComboBox<String>();
		comboBox.addItem("Yearly");
		comboBox.addItem("Half-yearly");
		comboBox.addItem("Quarterly");

		comboBox.setBounds(220,60,100,30);

		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String str = comboBox.getItemAt(comboBox.getSelectedIndex());

				if(str.equals("Yearly")) {
					scheme = 1;
				}
				else if(str.equals("Half-yearly")){
					scheme  = 2 ;
				}
				else if(str.equals("Quarterly")){
					scheme = 4;
				}

			}
		});

		ciPanel.add(comboBox);

		//adding text fields
		ciTextField = new JTextField[4];

		ciTextField[0] = new JTextField();
		ciTextField[1] = new JTextField();
		ciTextField[2] = new JTextField();
		ciTextField[3] = new JTextField();

		ciTextField[0].setBounds(200,110,120,30);
		ciTextField[1].setBounds(200,160,120,30);
		ciTextField[2].setBounds(200,210,120,30);
		ciTextField[3].setBounds(170,340,150,30);

		
		ciTextField[3].setEditable(false);
		ciTextField[3].setBackground(Color.WHITE);
		ciTextField[3].setHorizontalAlignment(JTextField.LEFT);

		ciPanel.add(ciTextField[0]);
		ciPanel.add(ciTextField[1]);
		ciPanel.add(ciTextField[2]);
		ciPanel.add(ciTextField[3]);

		//adding btns
		ciBtn = new JButton[3];

		ciBtn[0] = new JButton("Back");
		ciBtn[1] = new JButton("Calculate");
		ciBtn[2] = new JButton("Clear");

		ciBtn[0].setBounds(0,0,70,30);
		ciBtn[1].setBounds(80,270,150,40);
		ciBtn[2].setBounds(250,380,70,30);

		ciBtn[1].setFont(new Font("ariel",Font.PLAIN,20));

		ciBtn[0].addActionListener(this);
		ciBtn[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				String str1, str2, str3;

				str1 = ciTextField[0].getText();
				str2 = ciTextField[1].getText();
				str3 = ciTextField[2].getText();

				Double p, r, t;				

				if(str1.equals("0") || str1.equals("") || str1.equals(" ")) {
					JOptionPane.showMessageDialog(frame,"Enter Principal amount","Warning",JOptionPane.WARNING_MESSAGE);
					ciTextField[0].grabFocus();
					return;

				}
				try {
					 p = Double.parseDouble(str1);

					 if(p < 0) {
						JOptionPane.showMessageDialog(frame,"Principal amount should be positive","Warning",JOptionPane.WARNING_MESSAGE);
						ciTextField[0].grabFocus();
						return;
					}
				} catch(Exception excp) {
					JOptionPane.showMessageDialog(frame,"Enter numeric value","Warning",JOptionPane.WARNING_MESSAGE);
					ciTextField[0].grabFocus();
					return;
				}

				if(str2.equals("0") || str2.equals("") || str2.equals(" ")) {
					JOptionPane.showMessageDialog(frame,"Enter rate of interest","Warning",JOptionPane.WARNING_MESSAGE);
					ciTextField[1].grabFocus();
					return;
				}
				try {
					 r = Double.parseDouble(str2);
					 r = r/(scheme*100);

					 if(r < 0) {
						JOptionPane.showMessageDialog(frame,"Rate of interest should be positive","Warning",JOptionPane.WARNING_MESSAGE);
						ciTextField[1].grabFocus();
						return;
					}
				} catch(Exception excp) {
					JOptionPane.showMessageDialog(frame,"Enter numeric value","Warning",JOptionPane.WARNING_MESSAGE);
					ciTextField[1].grabFocus();
					return;
				}

				if(str3.equals("0") || str3.equals("") || str3.equals(" ")) {
					JOptionPane.showMessageDialog(frame,"Enter number of periods","Warning",JOptionPane.WARNING_MESSAGE);
					ciTextField[2].grabFocus();
					return;
				}
				try {
					 t = Double.parseDouble(str3);
					 t = scheme*t;

					 if(t < 0) {
						JOptionPane.showMessageDialog(frame,"Number of period should be positive","Warning",JOptionPane.WARNING_MESSAGE);
						ciTextField[2].grabFocus();
						return;
					}
				} catch(Exception excp) {
					JOptionPane.showMessageDialog(frame,"Enter numeric value","Warning",JOptionPane.WARNING_MESSAGE);
					ciTextField[2].grabFocus();
					return;
				}

				
				Double res = p * ( Math.pow((1+r),t) - 1 );

				String s = ""+res;

				if(s.lastIndexOf(".0") > 0){
					s = s.substring(0,s.length()-2);
				}

				ciTextField[3].setText(s);

			}
		});

		// set clear/flush all the fields
		ciBtn[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				ciTextField[0].setText("");
				ciTextField[1].setText("");
				ciTextField[2].setText("");
				ciTextField[3].setText("");
			}
		});

		ciPanel.add(ciBtn[0]);
		ciPanel.add(ciBtn[1]);
		ciPanel.add(ciBtn[2]);

		ciPanel.setLayout(null);
	}
	
	public void actionPerformed(ActionEvent e) {

		String str = e.getActionCommand();

		if(str.equals("Simple")) { 
			cardL.show(card,"Simple");
		} 
		else if(str.equals("Advanced")) {
			cardL.show(card,"Advanced");
		}
		else if(str.equals("Home")) {
			cardL.show(card,"Home");
		}
		else if(str.equals("Back")) {
			cardL.show(card,"Advanced");
		}
		else if(str.equals("SI")) {
			cardL.show(card,"SI");
		}
		else if(str.equals("CI")) {
			cardL.show(card,"CI");
		}
	}

	
	public static void main(String[] args) {
		new CaloAssist("CaloAssist");
	}
}
