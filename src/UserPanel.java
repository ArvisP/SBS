import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.text.NumberFormat;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.BorderLayout;

public class UserPanel extends JPanel {
	private Person u;
	private int w;
	private int h;
	private JLabel amount;
	private JLabel VIP = new JLabel("VIP");
	public final JButton profile;
	public JComboBox<String> combo;
	private String[] comboOptions;
	private ImageIcon icon;
	private BorderLayout layout;
	private NumberFormat nformat = NumberFormat.getCurrencyInstance();
	
	public UserPanel(Person user, int width, int height, Connect conn){
		u = user;
		w = width;
		h = height;
		layout = new BorderLayout();
		setLayout(layout);
		nformat.setGroupingUsed(true);
		if(user.isVIP())
			amount = new JLabel("Balance: " + nformat.format(user.getAmount()) + " - VIP");
		else
			amount = new JLabel("Balance: " + nformat.format(user.getAmount()));
		amount.setToolTipText("Your money");
		amount.setBorder(new EmptyBorder(0,20,0,0));
		add(amount, BorderLayout.LINE_START);
		setComboBox(user.getType());
		combo = new JComboBox<String>(comboOptions);
		combo.setBorder(new EmptyBorder(0,50,0,50));
		add(combo, BorderLayout.CENTER);
		
		// ------------ Profile Button -------------
		icon = new ImageIcon("images/profile_icon.png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(width/15, height/2, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		profile = new JButton();
		profile.setIcon(icon);
		profile.setToolTipText("View your profile");
		add(profile, BorderLayout.LINE_END);
		// -----------------------------------------	

	}

	public void setComboBox(String type){
		if(type.equals("SU")){
			comboOptions = new String[9];
			comboOptions[0] = "-- Actions --";
			comboOptions[1] = "Buy something";
			comboOptions[2] = "Sell something";
			comboOptions[3] = "View your listings";
			comboOptions[4] = "Add money to your account";
			comboOptions[5] = "Transfer money from your account";
			comboOptions[6] = "Previous purchases";
			comboOptions[7] = "SU Options";
			comboOptions[8] = "LogOut";
		}
		else{
			if(type.equals("U")){
				comboOptions = new String[9];
				comboOptions[0] = "-- Actions --";
				comboOptions[1] = "Buy something";
				comboOptions[2] = "Sell something";
				comboOptions[3] = "View your listings";
				comboOptions[4] = "Add money to your account";
				comboOptions[5] = "Transfer money from your account";
				comboOptions[6] = "Previous purchases";
				comboOptions[7] = "Apply to Quit";
				comboOptions[8] = "LogOut";
			}
			else{
				comboOptions = new String[4];
				comboOptions[0] = "-- Actions --";
				comboOptions[1] = "Buy something";
				comboOptions[2] = "Register!";
				comboOptions[3] = "LogOut";
			}
		}
	}
	
	public String getComboBox(){
		String option = combo.getSelectedItem().toString();
		return option;
	}
	
	public void reload(Person user){
		amount.setVisible(false);
		if(user.isVIP())
			amount = new JLabel("Balance: " + nformat.format(user.getAmount()) + " - VIP");
		else
			amount = new JLabel("Balance: " + nformat.format(user.getAmount()));
		amount.setToolTipText("Your money");
		amount.setBorder(new EmptyBorder(0,20,0,0));
		add(amount, BorderLayout.LINE_START);
	}

}