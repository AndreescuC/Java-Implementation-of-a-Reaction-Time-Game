import java.awt.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Third 
{
	private JFrame frame;
	private static JPanel Menu;
	private static JPanel Game1;
	private static JPanel Game2;
	private static JButton but_High;
	private static JButton but_Quit;
	private static JButton but_Start1;
	private static JButton but_Start2;
	private static JPanel pan_enq_Menu;
	private static JTextField txt_conf_Menu;
	private static JButton but_Yes;
	private static JButton but_No;
	private static JButton but_Return_main;
	private static JPanel pan_enq_Game1;
	private static JPanel pan_enq_Game2;
	private static JTextField txt_conf_Game1;
	private static JTextField txt_conf_Game2;
	private static JButton but_SaveQuit1;
	private static JButton but_notSaveQuit1;
	private static JButton but_notQuit1;
	private static JButton but_SaveQuit2;
	private static JButton but_notSaveQuit2;
	private static JButton but_notQuit2;
	private static JLabel lab_bulina;
	private static JLabel lab_bulina2;
	private static JTable tab_rezultate;
	private static JTable tab_rezultate2;
	private static JButton but_Start_game1;
	private static JButton but_Start_game2;
	private static DefaultTableModel model;
	private static DefaultTableModel model2;
	private static JLabel txt_instructions1;
	private static JLabel txt_instructions2;
	private static JButton but_Return_main2;

    public Image background;

	static Color col_Enquire = Color.DARK_GRAY;
	static Color col_Text = Color.red;
	static Color col_bg_Game2 = new Color(200,160,54);
	static Color col_bg_Game1 = new Color(239,255,99);
	static Font font1 = new Font("SansSerif", Font.BOLD, 25);
	static Font font2 = new Font("SansSerif", Font.BOLD, 18);
	static Font font3 = new Font("SansSerif", Font.BOLD, 18);
	static Font font_instructions = new Font("SansSerif", Font.BOLD, 16);	
	
	static Random seed = new Random();
	static Random seed1 = new Random();
	static boolean choice;
	static double time, reaction_time;
	static Timer timer;
	static Date startDate;
	static Date stopDate;
	static int i = 0, soon = 0;
	static ActionListener to_do;
	static ActionListener to_do2;

	public void playSound(String name) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		File f = new File(".\\.idea\\"+name);
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					Third window = new Third();
					window.frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Third() 
	{
		initialize();
	}
	private void initialize()
	{
		initComponents();
		defineEvents();
	}
		
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// Creating and initializing components
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void initComponents()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 500);
		frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = (int)screenSize.getWidth();
		double height = (int)screenSize.getHeight();
		System.out.println(width+" "+height);
		double X_tenth = width/10;
		double Y_tenth = height/10;
		frame.setSize((int)width, (int)height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
//MENU		

        File file = new File(".\\.idea\\background.png");
        try {
            background = ImageIO.read(file);
        }
        catch (IOException e)
        {

        }
        Toolkit.getDefaultToolkit().createImage(".\\.idea\\background.png");
        Menu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
            }
        };
        Menu.setLayout(null);
		frame.getContentPane().add(Menu, "name_253788289889039");
		
		but_Start1 = new JButton("Start Game1");
		but_Start1.setBounds((int)(5*X_tenth - 60),(int)( 4*Y_tenth - 25), 120, 50);		
		Menu.add(but_Start1);
		
		but_Start2 = new JButton("Start Game2");
		but_Start2.setBounds((int)(5*X_tenth- 60),(int)( 5*Y_tenth - 25), 120, 50);		
		Menu.add(but_Start2);
		
		but_High = new JButton("Hall of Fame");
		but_High.setBounds((int)(5*X_tenth - 60),(int)( 6*Y_tenth - 25), 120, 50);
		Menu.add(but_High);
		
		but_Quit = new JButton("Quit");
		but_Quit.setBounds((int)(5*X_tenth - 60),(int)( 7*Y_tenth - 25), 120, 50);
		Menu.add(but_Quit);
		
		pan_enq_Menu = new JPanel();
		pan_enq_Menu.setLayout(null);
		pan_enq_Menu.setBounds((int)(3.25*X_tenth),(int)(3.25*Y_tenth) , (int)(3.5*X_tenth),(int)( 3.5*Y_tenth));
		pan_enq_Menu.setBackground(col_Enquire);
		pan_enq_Menu.setVisible(false);
		
		txt_conf_Menu = new JTextField("Are you sure you want to quit?");
		txt_conf_Menu.setBounds((int)(0.4*X_tenth),(int)(0.6* Y_tenth), (int)(2.7*X_tenth),(int)(1* Y_tenth));
		txt_conf_Menu.setFont(font1);
		txt_conf_Menu.setBackground(col_Enquire);
		txt_conf_Menu.setBorder(null);
		txt_conf_Menu.setForeground(col_Text);
		pan_enq_Menu.add(txt_conf_Menu);
		
		but_Yes = new JButton("Get me outta here");
		but_Yes.setBounds((int)(0.4*X_tenth),(int)(2* Y_tenth),(int)(1*X_tenth),(int)(0.6* Y_tenth));
		pan_enq_Menu.add(but_Yes);
		
		but_No = new JButton("Neah");
		but_No.setBounds((int)(2.05*X_tenth),(int)(2* Y_tenth),(int)(1*X_tenth),(int)(0.6* Y_tenth));
		pan_enq_Menu.add(but_No);
		Menu.add(pan_enq_Menu);
					
//GAME1
		Game1 = new JPanel();
		Game1.setFocusable(true);
		Game1.setLayout(null);
		frame.getContentPane().add(Game1, "name_253792288903634");
		Game1.setFocusable(true);
		Game1.setBackground(col_bg_Game1);
		addKeyBindings_Game1(Game1);
		
		pan_enq_Game1 = new JPanel();
		pan_enq_Game1.setLayout(null);
		pan_enq_Game1.setBounds((int)(3.25*X_tenth),(int)(3.25*Y_tenth) , (int)(3.5*X_tenth),(int)( 3.5*Y_tenth));
		pan_enq_Game1.setBackground(col_Enquire);
		pan_enq_Game1.setVisible(false);
		
		txt_conf_Game1 = new JTextField("Are you sure you want to return to the main menu?");
		txt_conf_Game1.setBounds((int)(0.15*X_tenth),(int)(0.4* Y_tenth), (int)(3.35*X_tenth),(int)(1* Y_tenth));
		txt_conf_Game1.setFont(font2);
		txt_conf_Game1.setBackground(col_Enquire);
		txt_conf_Game1.setBorder(null);
		txt_conf_Game1.setForeground(col_Text);
		pan_enq_Game1.add(txt_conf_Game1);
		
		but_SaveQuit1 = new JButton("Save and Quit");
		but_SaveQuit1.setBounds((int)((0.18)*X_tenth),(int)(2* Y_tenth),(int)(0.85*X_tenth),(int)(0.5* Y_tenth));
		pan_enq_Game1.add(but_SaveQuit1);
		Game1.add(pan_enq_Game1);	
		
		but_notSaveQuit1 = new JButton("Quit without saving");
		but_notSaveQuit1.setBounds((int)((1.315)*X_tenth),(int)(2* Y_tenth),(int)(0.85*X_tenth),(int)(0.5* Y_tenth));
		pan_enq_Game1.add(but_notSaveQuit1);
		
		but_notQuit1 = new JButton("Play on");
		but_notQuit1.setBounds((int)(2.562*X_tenth),(int)(2* Y_tenth),(int)(0.85*X_tenth),(int)(0.5* Y_tenth));
		pan_enq_Game1.add(but_notQuit1);
		

		lab_bulina = new JLabel("");
		lab_bulina.setBounds((int)(3.15*X_tenth),(int)(0.5*Y_tenth) , 500,500);
		lab_bulina.setBackground(col_bg_Game1);
		lab_bulina.setOpaque(true);
		Game1.add(lab_bulina);
				
		Object[][] values = {{ "First try", "Second try", "Third try", "Fourth try", "Fifth try" }, {1, 2, 3, 4, 5}};
		model = new DefaultTableModel(values, new Object[] {1,2,3,4,5});
		tab_rezultate = new JTable(model);
		tab_rezultate.setBounds((int)(3*X_tenth),(int)(7.8*Y_tenth) , (int)(4*X_tenth),(int)( 0.66*Y_tenth));
		tab_rezultate.setRowHeight(25);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("");
		tab_rezultate.setBorder(title);
		Game1.add(tab_rezultate);
		tab_rezultate.setModel(model);	
		
		but_Start_game1 = new JButton("Start the test");
		but_Start_game1.setBounds((int)((0.9)*X_tenth),(int)(1* Y_tenth),(int)(0.85*X_tenth),(int)(0.5* Y_tenth));
		Game1.add(but_Start_game1);
		//disable Q
		
		txt_instructions1 = new JLabel("<html><center>Welcome to the visual test!<br>"
		+ "The rules are simple: <br>Use the P key for the green color<br>Use the Q key for the red color<br>Press the upper-left button when ready!<br></center></html>");
		txt_instructions1.setBackground(col_bg_Game1);
		txt_instructions1.setFont(font_instructions);
		txt_instructions1.setBounds((int)(0.2*X_tenth),(int)(2*Y_tenth) , (int)(3*X_tenth),(int)( 2*Y_tenth));
		Game1.add(txt_instructions1);
		
		but_Return_main = new JButton("<html><center>Return to the<br>Main Menu");
		but_Return_main.setBounds((int)(8*X_tenth),(int)(0.75* Y_tenth),(int)(1.2*X_tenth),(int)(1* Y_tenth));
		Game1.add(but_Return_main);
		
//GAME2
		Game2 = new JPanel();
		Game2.setFocusable(true);
		Game2.setLayout(null);
		frame.getContentPane().add(Game2, "name_253792288903634");
		Game2.setFocusable(true);
		Game2.setBackground(col_bg_Game1);
		addKeyBindings_Game2(Game2);
			
		pan_enq_Game2 = new JPanel();
		pan_enq_Game2.setLayout(null);
		pan_enq_Game2.setBounds((int)(3.25*X_tenth),(int)(3.25*Y_tenth) , (int)(3.5*X_tenth),(int)( 3.5*Y_tenth));
		pan_enq_Game2.setBackground(col_Enquire);
		pan_enq_Game2.setVisible(false);
				
		txt_conf_Game2 = new JTextField("Are you sure you want to return to the main menu?");
		txt_conf_Game2.setBounds((int)(0.15*X_tenth),(int)(0.4* Y_tenth), (int)(3.35*X_tenth),(int)(1* Y_tenth));
		txt_conf_Game2.setFont(font2);
		txt_conf_Game2.setBackground(col_Enquire);
		txt_conf_Game2.setBorder(null);
		txt_conf_Game2.setForeground(col_Text);
		pan_enq_Game2.add(txt_conf_Game2);
				
		but_SaveQuit2 = new JButton("Save and Quit");
		but_SaveQuit2.setBounds((int)((0.18)*X_tenth),(int)(2* Y_tenth),(int)(0.85*X_tenth),(int)(0.5* Y_tenth));
		pan_enq_Game2.add(but_SaveQuit2);
		Game2.add(pan_enq_Game2);
				
		but_notSaveQuit2 = new JButton("Quit without saving");
		but_notSaveQuit2.setBounds((int)((1.315)*X_tenth),(int)(2* Y_tenth),(int)(0.85*X_tenth),(int)(0.5* Y_tenth));
		pan_enq_Game2.add(but_notSaveQuit2);
				
		but_notQuit2 = new JButton("Play on");
		but_notQuit2.setBounds((int)(2.562*X_tenth),(int)(2* Y_tenth),(int)(0.85*X_tenth),(int)(0.5* Y_tenth));
		pan_enq_Game2.add(but_notQuit2);

		lab_bulina2 = new JLabel("");
		lab_bulina2.setBounds((int)(3.15*X_tenth),(int)(0.5*Y_tenth) , 500,500);
		lab_bulina2.setBackground(col_bg_Game1);
		lab_bulina2.setOpaque(true);
		ImageIcon bulina_Vacuta_Porc = new ImageIcon(".\\.idea\\Animal_spot.png");
		lab_bulina2.setIcon(bulina_Vacuta_Porc);
		Game2.add(lab_bulina2);

		Object[][] values2 = {{ "First try", "Second try", "Third try", "Fourth try", "Fifth try" }, {1, 2, 3, 4, 5}};
		model2 = new DefaultTableModel(values2, new Object[] {1,2,3,4,5});
		tab_rezultate2 = new JTable(model2);
		tab_rezultate2.setBounds((int)(3*X_tenth),(int)(7.8*Y_tenth) , (int)(4*X_tenth),(int)( 0.66*Y_tenth));
		tab_rezultate2.setRowHeight(25);
		tab_rezultate2.setBorder(title);
		Game2.add(tab_rezultate2);
		tab_rezultate2.setModel(model2);
		
		but_Start_game2 = new JButton("Start the test");
		but_Start_game2.setBounds((int)((0.9)*X_tenth),(int)(1* Y_tenth),(int)(0.85*X_tenth),(int)(0.5* Y_tenth));
		Game2.add(but_Start_game2);

		txt_instructions2 = new JLabel("<html><center>Welcome to the audio test!<br>"
		+ "The rules are simple: <br>Use the Q key for the cow mating call<br>Use the P key for the pig mating call<br>Press the upper-left button when ready!<br></center></html>");
		txt_instructions2.setBackground(col_bg_Game1);
		txt_instructions2.setFont(font_instructions);
		txt_instructions2.setBounds((int)(0.2*X_tenth),(int)(2*Y_tenth) , (int)(3*X_tenth),(int)( 2*Y_tenth));
		Game2.add(txt_instructions2);
		
		but_Return_main2 = new JButton("<html><center>Return to the<br>Main Menu");
		but_Return_main2.setBounds((int)(8*X_tenth),(int)(0.75* Y_tenth),(int)(1.2*X_tenth),(int)(1* Y_tenth));
		Game2.add(but_Return_main2);		
	}
	
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// Defining events
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void defineEvents()
	{
		but_Start1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Menu.setVisible(false);
				Game1.setVisible(true);
			}
		});
		but_Start2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Menu.setVisible(false);
				Game2.setVisible(true);
			}
		});
		but_High.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Menu.setVisible(false);
				JOptionPane.showMessageDialog(null, "You need to buy  the Premium version for enableing this feature.\n Please visist http://Reac-Havoc.com","Premium required!",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		but_Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				but_Start1.setVisible(false);
				but_Start2.setVisible(false);
				but_High.setVisible(false);
				but_Quit.setVisible(false);
				pan_enq_Menu.setVisible(true);
			}
		});
		but_Yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		but_No.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				pan_enq_Menu.setVisible(false);
				but_Start1.setVisible(true);
				but_Start2.setVisible(true);
				but_High.setVisible(true);
				but_Quit.setVisible(true);
			}
		});
		but_Return_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{			
				System.out.println("in");
				Game1.setVisible(false);
				Menu.setVisible(true);
			}
		});	
		but_Return_main2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{			
				Game2.setVisible(false);
				Menu.setVisible(true);
			}
		});	
		but_SaveQuit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				//SAVE
				pan_enq_Game1.setVisible(false);
				pan_enq_Game2.setVisible(false);
				//elements.setvisible(true)
				Game1.setVisible(false);
				Game2.setVisible(false);
				Menu.setVisible(true);
			}
		});
		but_notSaveQuit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				pan_enq_Game1.setVisible(false);
				//elements.setvisible(true)
				Game1.setVisible(false);
				Game2.setVisible(false);
				Menu.setVisible(true);
			}
		});
		but_notQuit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				pan_enq_Game1.setVisible(false);
				//elements.setvisible(true)
			}
		});
		but_SaveQuit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				//SAVE
				pan_enq_Game1.setVisible(false);
				pan_enq_Game2.setVisible(false);
				//elements.setvisible(true)
				Game1.setVisible(false);
				Game2.setVisible(false);
				Menu.setVisible(true);
			}
		});
		but_notSaveQuit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				pan_enq_Game1.setVisible(false);
				//elements.setvisible(true)
				Game1.setVisible(false);
				Game2.setVisible(false);
				Menu.setVisible(true);
			}
		});
		but_notQuit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				pan_enq_Game2.setVisible(false);
				//elements.setvisible(true)
			}
		});
		to_do = new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				soon = 1;
				startDate = new Date();
				System.out.println("to_do");
				if(choice) {
					ImageIcon bulina_verde = new ImageIcon(".\\.idea\\Green_Spot.png");
					lab_bulina.setIcon(bulina_verde);
				}
				else
				{
					ImageIcon bulina_rosie = new ImageIcon(".\\.idea\\Red_Spot2.png");
					lab_bulina.setIcon(bulina_rosie);
				}
				//Game1.setBackground(col_Text);
				timer.stop();			
			}
		};

		to_do2 = new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				soon = 1;
				startDate = new Date();
				System.out.println("to_do");
				if(choice) {
					try{
						playSound("cow.wav");
						System.out.println("Vacaaaaaa!");
					}
					catch ( Exception e)
					{

					}
				}
				else
				{
					try{
						playSound("pig+6db.wav");
						System.out.println("pig");
					}
					catch ( Exception e)
					{

					}

				}
				//Game1.setBackground(col_Text);
				timer.stop();
			}
		};
		but_Start_game1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{	
				but_Start_game1.setVisible(false);
				i=0;
				System.out.println("Buton");
				time = (seed.nextDouble()*5 + 2)*1000;
				if((int)time%2==1)
					choice=true;
				else
					choice=false;
				timer = new Timer((int)time, to_do);
				timer.start();	
				
			}
		});

		but_Start_game2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				i=0;
				but_Start_game1.setVisible(false);
				time = (seed.nextDouble()*5 + 2)*1000;
				if((int)time%2==1)
					choice=true;
				else
					choice=false;
				timer = new Timer((int)time, to_do2);
				timer.start();


			}
		});

	}


	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// KeyBinding methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void addKeyBindings_Game1(JPanel jc)
	{
	    jc.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "Esc pressed");
	    jc.getActionMap().put("Esc pressed", new AbstractAction()
	    {
	        @Override
	        public void actionPerformed(ActionEvent ae)
	        {
				pan_enq_Game1.setVisible(true);
				Game2.setVisible(false);
	        }
	    });
	    jc.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "P pressed");
		jc.getActionMap().put("P pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				stopDate = new Date();
				lab_bulina.setIcon(null);
				Game1.setBackground(col_bg_Game1);
				reaction_time = stopDate.getTime() - startDate.getTime();
				model.setValueAt(reaction_time, 1, i);
				if ((soon == 0) || (!choice))
				{
					model.setValueAt("Not Valid", 1, i);
					timer.stop();
				}
				i++;
				time = (seed.nextDouble() * 5 + 2) * 1000;
				if((int)time%2==1)
					choice=true;
				else
					choice=false;
				timer = new Timer((int) time, to_do);
				timer.start();
				System.out.println(i);
				System.out.println("soon: " + soon);
				if (i == 5) {
					timer.stop();
					//enable got it
					//disable Q
				}
				System.out.println(timer.isRunning());
				soon = 0;
			}

		});
		jc.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), "Q pressed");
			jc.getActionMap().put("Q pressed", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					stopDate = new Date();
					lab_bulina.setIcon(null);
					Game1.setBackground(col_bg_Game1);
					reaction_time = stopDate.getTime() - startDate.getTime();
					model.setValueAt(reaction_time, 1, i);
					if ((soon == 0) || (choice))
					{
						model.setValueAt("Not Valid", 1, i);
						timer.stop();
					}
					i++;
					time = (seed.nextDouble() * 5 + 2) * 1000;
					if((int)time%2==1)
						choice=true;
					else
						choice=false;
					timer = new Timer((int) time, to_do);
					timer.start();
					System.out.println(i);
					System.out.println("soon: " + soon);
					if (i == 5) {
						timer.stop();
						//enable got it
						//disable Q
					}
					System.out.println(timer.isRunning());
					soon = 0;
				}
			});
	}
	public static void addKeyBindings_Game2(JPanel jc)
	{
	    jc.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "Esc pressed");
	    jc.getActionMap().put("Esc pressed", new AbstractAction()
	    {
	        @Override
	        public void actionPerformed(ActionEvent ae)
	        {
				pan_enq_Game2.setVisible(true);
				but_SaveQuit2.setVisible(true);
				but_notSaveQuit2.setVisible(true);
				but_notQuit2.setVisible(true);
				Game1.setVisible(false);
	        }
	    });
		jc.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "P pressed");
		jc.getActionMap().put("P pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				stopDate = new Date();
				lab_bulina.setIcon(null);
				Game2.setBackground(col_bg_Game1);
				reaction_time = stopDate.getTime() - startDate.getTime();
				model2.setValueAt(reaction_time, 1, i);
				if ((soon == 0) || (choice))
				{
					//Game1.setBackground(Color.YELLOW);
					model2.setValueAt("Not Valid", 1, i);
					timer.stop();
				}
				i++;
				time = (seed.nextDouble() * 5 + 2) * 1000;
				if(Math.random() < 0.5)
					choice=true;
				else
					choice=false;
				timer = new Timer((int) time, to_do2);
				timer.start();
				System.out.println(i);
				System.out.println("soon: " + soon);
				if (i == 5) {
					timer.stop();
					//enable got it
					//disable Q
				}
				System.out.println(timer.isRunning());
				soon = 0;
			}

		});
		jc.getInputMap(JLabel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), "Q pressed");
		jc.getActionMap().put("Q pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				stopDate = new Date();
				lab_bulina.setIcon(null);
				Game2.setBackground(col_bg_Game1);
				reaction_time = stopDate.getTime() - startDate.getTime();
				model2.setValueAt(reaction_time, 1, i);
				if ((soon == 0) || (!choice))
				{
					//Game1.setBackground(Color.YELLOW);
					model2.setValueAt("Not Valid", 1, i);
					timer.stop();
				}
				i++;
				time = (seed.nextDouble() * 5 + 2) * 1000;
				if(Math.random() < 0.5)
					choice=true;
				else
					choice=false;
				timer = new Timer((int) time, to_do2);
				timer.start();
				System.out.println(i);
				System.out.println("soon: " + soon);
				if (i == 5) {
					timer.stop();
					//enable got it
					//disable Q
				}
				System.out.println(timer.isRunning());
				soon = 0;
			}
		});
	}
}