import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.GridBagLayout;

public class Login extends JFrame {
	private final JLabel username;
	private final JLabel pw;
	private final JLabel company;
	private final JTextField userField;
	private final JPasswordField passwordField;
	private final JButton login;
	private final JButton register;
	private final JButton visitor;
	private final GridBagLayout layout;
	public int width;
	public int height;
	public int x;
	public int y;
	
	public Login(Connect conn){
		super("A Social Business System");
		//-- Creates and sets text fields and labels.--
		username = new JLabel("Username", SwingConstants.CENTER);
		pw = new JLabel("Password", SwingConstants.CENTER);
		userField = new JTextField(12);
		userField.setHorizontalAlignment(JTextField.CENTER);
		passwordField = new JPasswordField(12);
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		login = new JButton("Login");
		register = new JButton("Register");
		visitor = new JButton("Continue as a visitor");
		
		// ---- Sets login button as preselected ------
		getRootPane().setDefaultButton(login);
		login.requestFocus();
		
		//-- Layout creation---------------------------
		layout = new GridBagLayout();
		findDimension();
		setBounds(x, y, width, height);
		setLayout(layout);
		// Initial Constraints
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=2;
		EmptyBorder border = new EmptyBorder(5,0,0,0);
		
		// ------- Username Label ------
		username.setBorder(border);
		add(username,c);
		
		// ------- Username Field ------
		
		c.gridy=1;
		add(userField,c);
		
		// ------- Password Label ------
		c.gridy=2;
		pw.setBorder(border);
		add(pw,c);
		
		// ----- Password Field -------
		
		c.gridy=3;
		add(passwordField,c);
		
		// ------ Login Button --------
		c.gridy=4;
		c.gridx=0;
		c.gridwidth=1;
		
		add(login,c);
		
		// ------ Register Button ---------
		c.gridx=1;
		add(register,c);
		
		// -------- Visitor Button --------
		c.gridx=0;
		c.gridy=5;
		c.gridwidth=4;
		add(visitor,c);
		
		// --------- AA Project ----------
		company = new JLabel("AA-Project", SwingConstants.CENTER);
		company.setBorder(border);
		c.gridy=6;
		add(company,c);
			
		// Login's action
		login.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						Person user;
						String u = userField.getText();
						String p = String.valueOf(passwordField.getPassword());
						if(u.equals("") || p.equals("")){
							JOptionPane.showMessageDialog(null, "Please insert both a username and a password.");
						}
						else{
							user = conn.login(u,p);
							if(!user.getUsername().equals("a")){
								if(user.isActive()){
									setVisible(false);
									dispose();
									mainGUI frame = new mainGUI(user, conn);
									frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									frame.setAlwaysOnTop(true);
									frame.setVisible(true);
									frame.setResizable(false);
								}
								else{
									if(user.getType().equals("V"))
										JOptionPane.showMessageDialog(null, "Your account application is pending SU review.");
									else if(user.getTimes()<3)
										JOptionPane.showMessageDialog(null, "Your account has been suspended.\nPlease await admin review.");
									else
										JOptionPane.showMessageDialog(null, "After 3 suspensions your account has been permanently removed.");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Incorrect username or password.");
								passwordField.setText("");
							}
						}
					}

				}
		);
		
		// Register's action
		register.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						Registration frame = new Registration(conn, x, y, width, height);
						setVisible(false);
						dispose();
						frame.setAlwaysOnTop(true);
						frame.setVisible(true);
						frame.setResizable(false);
					}
				}
		);
		
		// Continue as visitor's action
		visitor.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						setVisible(false);
						dispose();
						// User Creation
						// parameters: fName, lName, dob, username, type, password, email, adress, state, city, zipcode.
						Person visitor = new Person("visitor", "null", "00/00/0000", "visitor", "V", "null", "null", "null", "null",
								"null", 00000);
						visitor.setAmount(0);
						mainGUI frame = new mainGUI(visitor, conn);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setAlwaysOnTop(true);
						frame.setVisible(true);
						frame.setResizable(false);
					}
				}
		);	
	}
	
	public void findDimension(){
		// -- Determines dimensions of the login window --
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = 280;
		height = 240;
		x = (int)(screenSize.getWidth()*(0.43));
		y = (int)(screenSize.getHeight()*(0.35));
	}

	public static void main(String[] args){		
		Connect conn = new Connect();
		Login frame = new Login(conn);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		
	} 
}
