import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.Random;

public class Registration extends JFrame{
	private JPanel panel = new JPanel();
	private final JButton reg = new JButton("Register");
	private final JButton cancel = new JButton("Cancel");
	private final JComboBox stateField;
	private final JComboBox monthField;
	private final JComboBox dayField;
	private final JComboBox yearField;
	private final String[] states;
	private final String[] month;
	private final String[] day;
	private final String[] year;
	private String dob;
	private int r1;
	private int r2;
	private int op;
	private int result;
	// --------- Text Fields -------------------------
	private JTextField userField = new JTextField(25);
	private JPasswordField passField = new JPasswordField(25);
	private JPasswordField repassField = new JPasswordField(25);
	private JTextField emailField = new JTextField(25);
	private JTextField fnameField = new JTextField(25);
	private JTextField lnameField = new JTextField(25);
	private JTextField addressField = new JTextField(25);
	private JTextField cityField = new JTextField(25);
	private JTextField zipField = new JTextField(7);
	private JTextField captcha = new JTextField(7);
	// ----------- Labels ----------------------------
	private JLabel userLabel = new JLabel("Username:");
	private JLabel passLabel = new JLabel("Password:");
	private JLabel repassLabel = new JLabel("Re-enter password:");
	private JLabel emailLabel = new JLabel("Email:");
	private JLabel fnameLabel = new JLabel("First name:");
	private JLabel lnameLabel = new JLabel("Last name:");
	private JLabel dobLabel = new JLabel("Date of Birth:");
	private JLabel addressLabel = new JLabel("Address:");
	private JLabel cityLabel = new JLabel("City:");
	private JLabel stateLabel = new JLabel("State:");
	private JLabel zipLabel = new JLabel("Zipcode:");
	private JLabel welcome = new JLabel("Welcome new user!");
	private JLabel question;
	
	public Registration(Connect conn, int x, int y, int width, int height){
		super("Social Business System");
		x*=0.8;
		y*=0.7;
		width*=2;
		height*=3;

		setBounds(x, y, width, height);
		states = new String[50];
		month = new String[12];
		day = new String[31];
		year = new String[100];
		setStates();
		setMonth();
		setDay();
		setYear();
		stateField = new JComboBox(states);
		stateField.setToolTipText("Selecting your correct city and state ensures that only services that apply to you are displayed.");
		cityField.setToolTipText("Selecting your correct city and state ensures that only services that apply to you are displayed.");
		monthField = new JComboBox(month);
		dayField = new JComboBox(day);
		yearField = new JComboBox(year);
		Container panel = getContentPane();
		panel.setLayout(null);
		generate();
		question.setVisible(true);
		setAllBounds(x,y,width,height);
		panel.add(welcome);
		panel.add(userLabel);
		panel.add(passLabel);
		panel.add(repassLabel);
		panel.add(emailLabel);
		panel.add(fnameLabel);
		panel.add(lnameLabel);
		panel.add(dobLabel);
		panel.add(addressLabel);
		panel.add(cityLabel);
		panel.add(stateLabel);
		panel.add(zipLabel);
		panel.add(question);
		panel.add(cancel);
		panel.add(reg);
		panel.add(userField);
		panel.add(passField);
		panel.add(repassField);
		panel.add(emailField);
		panel.add(fnameField);
		panel.add(lnameField);
		panel.add(monthField);
		panel.add(dayField);
		panel.add(yearField);
		panel.add(addressField);
		panel.add(cityField);
		panel.add(stateField);
		panel.add(zipField);
		panel.add(captcha);
		panel.validate();
		
		cancel.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						setVisible(false);
						dispose();
						Login frame = new Login(conn);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
						frame.setResizable(false);
					}
				}
				);
		reg.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						verifyRegistration(conn);
					}
				}
				
		);
		
	}
	
	public void setStates(){
		states[0] = "AL";
		states[1] = "AK";
		states[2] = "AZ";
		states[3] = "AR";
		states[4] = "CA";
		states[5] = "CO";
		states[6] = "CT";
		states[7] = "DE";
		states[8] = "FL";
		states[9] = "GA";
		states[10] = "HI";
		states[11] = "ID";
		states[12] = "IL";
		states[13] = "IN";
		states[14] = "IA";
		states[15] = "KS";
		states[16] = "KY";
		states[17] = "LA";
		states[18] = "ME";
		states[19] = "MD";
		states[20] = "MA";
		states[21] = "MI";
		states[22] = "MN";
		states[23] = "MS";
		states[24] = "MO";
		states[25] = "MT";
		states[26] = "NE";
		states[27] = "NV";
		states[28] = "NH";
		states[29] = "NJ";
		states[30] = "NM";
		states[31] = "NY";
		states[32] = "NC";
		states[33] = "ND";
		states[34] = "OH";
		states[35] = "OK";
		states[36] = "OR";
		states[37] = "PA";
		states[38] = "RI";
		states[39] = "SC";
		states[40] = "SD";
		states[41] = "TN";
		states[42] = "TX";
		states[43] = "UT";
		states[44] = "VT";
		states[45] = "VA";
		states[46] = "WA";
		states[47] = "WV";
		states[48] = "WI";
		states[49] = "WY";
	}
	
	public void setMonth(){
		month[0] = "January";
		month[1] = "February";
		month[2] = "March";
		month[3] = "April";
		month[4] = "May";
		month[5] = "June";
		month[6] = "July";
		month[7] = "August";
		month[8] = "September";
		month[9] = "October";
		month[10] = "November";
		month[11] = "December";
	}
	
	public void setDay(){
		int i;
		for(i=0; i < 31; ++i)
			if(i+1<10)
				day[i] = "0"+Integer.toString(i+1);
			else
				day[i] = Integer.toString(i+1);
	}
	
	public void setYear(){
		int i;
		for(i=0; i < 100; ++i)
			year[i] = Integer.toString(2015-i);
	}
	
	public void setAllBounds(int x, int y, int width, int height){
		int tmp_x;
		int tmp_y;
		int tmp_width;
		Font font = new Font("Courier",Font.BOLD,18);
		tmp_x = (int)(width*0.03);
		tmp_y = (int)(height*0.1);
		// Setting all Labels
		welcome.setFont(font);
		welcome.setBounds(width/3, 20, width/2, height/20);
		userLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		passLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		repassLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		emailLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		fnameLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		lnameLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		dobLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		addressLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		cityLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		stateLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		zipLabel.setBounds(tmp_x, tmp_y, width/3, height/20);
		tmp_y += height/15;
		question.setBounds(tmp_x, tmp_y, width/3, height/20);
		// Setting buttons
		tmp_y += height/15;
		cancel.setBounds(5, tmp_y, (width/2)-10, height/20);
		reg.setBounds((width/2)+5, tmp_y, (width/2)-15, height/20);
		// Setting all Fields
		tmp_x = (int)(width*0.3);
		tmp_y = (int)(height*0.1);
		tmp_width = (int)(width/1.5);
		userField.setBounds(tmp_x, tmp_y, tmp_width, height/20);
		tmp_y += height/15;
		passField.setBounds(tmp_x, tmp_y, tmp_width, height/20);
		tmp_y += height/15;
		repassField.setBounds(tmp_x, tmp_y, tmp_width, height/20);
		tmp_y += height/15;
		emailField.setBounds(tmp_x, tmp_y, tmp_width, height/20);
		tmp_y += height/15;
		fnameField.setBounds(tmp_x, tmp_y, tmp_width, height/20);
		tmp_y += height/15;
		lnameField.setBounds(tmp_x, tmp_y, tmp_width, height/20);
		tmp_y += height/15;
		
		monthField.setBounds(tmp_x, tmp_y, tmp_width/3, height/20);
		dayField.setBounds(tmp_x+tmp_width/3+10, tmp_y, tmp_width/5, height/20);
		yearField.setBounds(tmp_x+tmp_width/3+tmp_width/5+20, tmp_y, tmp_width/3, height/20);
		tmp_y += height/15;
		addressField.setBounds(tmp_x, tmp_y, tmp_width, height/20);
		tmp_y += height/15;
		cityField.setBounds(tmp_x, tmp_y, tmp_width, height/20);
		tmp_y += height/15;
		stateField.setBounds(tmp_x, tmp_y, tmp_width/4, height/20);
		tmp_y += height/15;
		zipField.setBounds(tmp_x, tmp_y, tmp_width/4, height/20);
		tmp_y += height/15;
		captcha.setBounds(tmp_x, tmp_y, tmp_width/4, height/20);
		
	}
	
	public void verifyRegistration(Connect conn){
		boolean exists;
		String username = userField.getText();
		String password = String.valueOf(passField.getPassword());
		String repass = String.valueOf(repassField.getPassword());
		int zip = zipField.getText().length();
		JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true); 
		exists = conn.verifyUser(username);
		if(username.length()<5){
			JOptionPane.showMessageDialog(dialog,"Username must be at least 5 characters in length.");
		}
		else if(exists)
			JOptionPane.showMessageDialog(dialog, "Username is already taken.");
		else if(!password.equals(repass)){
			JOptionPane.showMessageDialog(dialog, "Passwords do not match.");
		}
		else if(password.equals("") || emailField.getText().isEmpty() || fnameField.getText().isEmpty() || lnameField.getText().isEmpty()|| 
				addressField.getText().isEmpty() || cityField.getText().isEmpty() || zipField.getText().isEmpty())
			JOptionPane.showMessageDialog(dialog, "All fields must be filled.");
		else if(!zipField.getText().matches("[0-9]+"))
			JOptionPane.showMessageDialog(dialog, "The zipcode field can only contain numbers.");
		else if(zip!=5)
			JOptionPane.showMessageDialog(dialog, "Your zipcode is not within the U.S.");
		else if(result!=Integer.parseInt(captcha.getText())){
			JOptionPane.showMessageDialog(dialog, "Incorrect CAPTCHA.");
		}
		else {
			setupDOB();
			createUser(conn);
		}
	}
	
	public void createUser(Connect conn){
		// parameters: fName, lName, dob, username, type, password, email, address, state, city, zipcode.
		Person n_User;
		int zip = Integer.parseInt(zipField.getText());
		n_User = new Person(fnameField.getText(),lnameField.getText(),dob,userField.getText(),"V",String.valueOf(passField.getPassword()),
				emailField.getText(), addressField.getText(), cityField.getText(), stateField.getSelectedItem().toString(),  zip);
		
		conn.register(n_User);
		conn.log(n_User.getUsername()+" has applied to be a User.");
		conn.SUTask(n_User.getUsername(), n_User.getUsername() + " has applied to be a User.");
		setVisible(false);
		dispose();
		JOptionPane.showMessageDialog(null, "Your application has been filed and\nwill be reviewed by a super user.");
		conn.refresh();
		Login frame = new Login(conn);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false); 
	}
	
	public void generate(){
		Random i = new Random();
		r1 = 1 + i.nextInt(100);
		r2 = 1 + i.nextInt(100);
		op = 1 + i.nextInt(3);
		String q;
		if (op ==1){
			q = new String(r1+" + "+r2+"?");
			result = r1+r2;
		}
		
		else if(op == 2){
			q = new String(r1+" - "+r2+"?");
			result = r1-r2;
		}
		
		else {
			q = new String(r1+" * "+r2+"?");
			result = r1*r2;
		}
		
		question = new JLabel(q);
	}
	
	public void setupDOB(){
		String d = dayField.getSelectedItem().toString();
		String y = yearField.getSelectedItem().toString();
		String m = new String();
		String selMonth = monthField.getSelectedItem().toString();
		switch(selMonth){
			case "January" :
				m = "01";
				break;
			case "February" :
				m = "02";
				break;
			case "March" :
				m = "03";
				break;
			case "April" :
				m = "04";
				break;
			case "May" :
				m = "05";
				break;
			case "June" :
				m = "06";
				break;
			case "July" :
				m = "07";
				break;
			case "August" :
				m = "08";
				break;
			case "September" :
				m = "09";
				break;
			case "October" :
				m = "10";
				break;
			case "November" :
				m = "11";
				break;
			case "December" :
				m = "12";
				break;
		}
		dob = m+"/"+d+"/"+y;
	}
	
}
