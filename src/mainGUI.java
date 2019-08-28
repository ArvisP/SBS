import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class mainGUI extends JFrame {
    private final BorderLayout layout;
    //
    private final CardLayout cardLayout;
    private final String COMPLAINT = "Complaint Panel";
    private final String CONTENT = "Content Panel";
    private final String PROFILE = "Profile Panel";
    private final String SELL    = "Selling panel";
    private final String BILLING = "BillingInfo Panel";
    private final String BUYING  = "Buying Panel";
    private final String RATING  = "Rating Panel";
    private final String REVIEW  = "Review Panel";
    private final String SUPER = "SuperUser Panel";
    private BillingInfoPanel billInfo;
    private Rating rate;
    private SellingGUI sell;
    private Complaint complaint;
    private Buying buyingGUI;
    private JPanel mainPanel;
    private ProfileGUI profile;
    private ReviewGUI review;
    private JScrollPane buy;
    private Sale sale;
    //
    private UserPanel userpanel;
    private ContentPanel content;
    private ProfileGUI profileGUI;
    private SUserGUI suGUI;
    public int width;
    public int height;
    public int x;
    public int y;

    public mainGUI(Person user, Connect conn){
        // ------ Frame setup ------
        super("A Social Business System: Hello, " + user.getfName() + ".");
        findDimension();
        setBounds(x,y,width,height);
        layout = new BorderLayout(1,0);
        setLayout(layout);
        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);
        userpanel = new UserPanel(user, width, height/10, conn);
        suGUI = new SUserGUI(conn, width, height, user);
        add(userpanel,BorderLayout.NORTH);
        content = new ContentPanel(conn, width, height,user);

        add(mainPanel, BorderLayout.CENTER);
        // Different GUI
        profileGUI = new ProfileGUI(conn, user);
        billInfo = new BillingInfoPanel(conn, user, this);
        sell = new SellingGUI(conn, user);

        mainPanel.add(sell, SELL);
        mainPanel.add(content, CONTENT);
        mainPanel.add(profileGUI, PROFILE);
        mainPanel.add(billInfo, BILLING);
        mainPanel.add(suGUI, SUPER);
        cardLayout.show(mainPanel, CONTENT);
        buyingGUI = new Buying(conn, user, content.Sales[0], width, height, this);

        userpanel.combo.addActionListener(new ActionListener () {
                                              @Override
                                              public void actionPerformed(ActionEvent e)
                                              {
                                                  String selected = userpanel.getComboBox();
                                                  conn.refresh();
                                                  if(conn.verify(user.getUsername()) || user.getType().equals("V")){
                                                      switch(selected){
                                                          case "Buy something" :
                                                              viewContent(conn, user);
                                                              break;
                                                          case "Add money to your account" :
                                                              showAmountFrame(user, conn, "add");
                                                              break;
                                                          case "Sell something" :
                                                              sellSomething(conn, user);
                                                              break;
                                                          case "View your listings" :
                                                              viewListings(conn, user);
                                                              break;
                                                          case "Transfer money from your account" :
                                                              showAmountFrame(user, conn, "sub");
                                                              break;
                                                          case "Previous purchases" :
                                                        	  showPurchases(conn, user);
                                                        	  break;
                                                          case "Register!" :
                                                              register(conn);
                                                              break;
                                                          case "Apply to Quit" :
                                                        	  quit(conn, user);
                                                              break;
                                                          case "SU Options":
                                                              suOptions(conn, user);
                                                              break;
                                                          case "LogOut" :
                                                              LogOut(conn);
                                                              break;
                                                      }
                                                  }
                                                  else{
                                                      JDialog dialog = new JDialog();
                                                      dialog.setAlwaysOnTop(true);
                                                      JOptionPane.showMessageDialog(dialog, "Your account has been suspended.\nYou will now be logged out.");
                                                      LogOut(conn);
                                                  }



                                              }
                                          }
        );


        userpanel.profile.addActionListener(new ActionListener(){

                                                @Override
                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    conn.refresh();
                                                    if(!user.getType().equals("V")){

                                                        if(conn.verify(user.getUsername())){
                                                            switchToProfile();

                                                        }
                                                        else{
                                                            JDialog dialog = new JDialog();
                                                            dialog.setAlwaysOnTop(true);
                                                            JOptionPane.showMessageDialog(dialog, "Your account has been suspended.\nYou will now be logged out.");
                                                            LogOut(conn);
                                                        }
                                                    }
                                                    else{
                                                        JDialog dialog = new JDialog();
                                                        dialog.setAlwaysOnTop(true);
                                                        JOptionPane.showMessageDialog(dialog, "You have no profile as a Visitor, please register.");
                                                    }

                                                }
                                            }

        );

        profileGUI.editPaymentMethod.addActionListener(e->{
                    cardLayout.show(mainPanel, BILLING);
                }
        );


        content.list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (!e.getValueIsAdjusting())
                {
                    if(content.model.getElementAt(0)!= "No listings match your criteria."){
                    	conn.refresh();
                    	if(conn.verify(user.getUsername()) || user.getType().equals("V")){
                    		int selected = content.list.getSelectedIndex();
                    		if(selected>=0){
		                        content.list.clearSelection();
		                        sale = content.Sales[selected];
		                        mainPanel.remove(buyingGUI);
		                        buyScreen(conn, user, sale, width, height);
		                        mainPanel.add(buyingGUI, BUYING);
		                        cardLayout.show(mainPanel, BUYING);
                    		}
                    	}
                    	else{
                    		JDialog dialog = new JDialog();
                            dialog.setAlwaysOnTop(true);
                            JOptionPane.showMessageDialog(dialog, "Your account has been suspended.\nYou will now be logged out.");
                            LogOut(conn);
                    	}
                    }
                }
            }
        });
    }

    public void quit(Connect conn, Person u){
    	conn.SUTask(u.getUsername(), u.getUsername() + " has applied to quit the system.");
    	conn.log(u.getUsername() + " has applied to quit the system.");
    	JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(dialog, "Your application to quit has been submitted.");
    }

    public void buyScreen(Connect conn, Person u, Sale sale, int width, int height){
        buyingGUI = new Buying(conn, u, sale, width, height, this);
    }


    public void showPurchases(Connect conn, Person user){
    	int amount = conn.countPrevious(user);
    	content.model.clear();
    	if(amount == 0){
    		content.model.addElement("No listings match your criteria.");
			content.setPanels();
    	}
    	else{
    		content.Sales = new Sale[amount];
    		content.Sales = conn.previousPurchases(user, amount);
    		content.displaySales(content.Sales.length);
    	}
    	cardLayout.show(mainPanel,CONTENT);
    }
    
    public void showAmountFrame(Person user, Connect conn, String add) {
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        boolean valid = true;
        NumberFormat nformat = NumberFormat.getCurrencyInstance();
        nformat.setGroupingUsed(true);
        while(valid){
            String entry = JOptionPane.showInputDialog(dialog, "Enter the amount:");
            if (entry!=null){
                if(entry.matches("[0-9]+.?[0-9]{0,2}?")){
                    double amount = Double.parseDouble(entry);
                    int r = 1;
                    if(add.equals("add"))
                        r = JOptionPane.showConfirmDialog(dialog, "Is the amount "+ nformat.format(amount) + " OK?", "Add", JOptionPane.OK_CANCEL_OPTION);
                    else{
                        r = JOptionPane.showConfirmDialog(dialog, nformat.format(amount) + " Will be transfered to your card, is this OK?", "Transfer", JOptionPane.OK_CANCEL_OPTION);
                    }
                    if (amount > user.getAmount() && !add.equals("add"))
                        JOptionPane.showMessageDialog(dialog, "Insufficient Balance");
                    else{
                        if(r == 0){
                            if(add.equals("add"))
                                user.addMoney(amount);
                            else{
                                user.addMoney(-amount);
                            }
                            userpanel.reload(user);
                            conn.updateAmount(user);
                            valid = false;
                            {
                                if((user.getAmount() > 5000 && !user.isVIP()) || ((user.getAmount() < 5000) && (user.getN_ratings()<=5))){
                                    JOptionPane.showMessageDialog(dialog, "Your VIP Status has changed.\nYou will now be logged out.");
                                    LogOut(conn);
                                }

                            }
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(dialog, "Invalid amount.");
                }
            }
            else
                valid = false;
        }


    }

    public void LogOut(Connect conn){
        setVisible(false);
        dispose();
        Login frame = new Login(conn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        conn.logout();
    }

    public void register(Connect conn){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)(screenSize.getWidth()*(0.15));
        height = (int)(screenSize.getHeight()*(0.2));
        x = (int)(screenSize.getWidth()*(0.43));
        y = (int)(screenSize.getHeight()*(0.35));
        LogOut(conn);
        conn.refresh();
        Registration frame = new Registration(conn, x, y, width, height);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void viewContent(Connect conn, Person user){
    	content.model.clear();
    	content.initialItems(conn);
    	cardLayout.show(mainPanel, CONTENT);
    }

    public void findDimension(){
        // -- Determines dimensions of the login window --
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = 750;
        height = 800;
        x = (int)(screenSize.getWidth()*(0.3));
        y = (int)(screenSize.getHeight()*(0.2));
    }

    public void viewListings(Connect conn, Person user){
        int amount = conn.listingamount(user);
        cardLayout.show(mainPanel, CONTENT);
        content.model.clear();
        content.Sales = new Sale[amount];
        content.Sales = conn.viewListings(user, amount);
        if(amount != 0){
            content.showAll();
            content.displaySales(amount);
        }
        else{
            content.model.addElement("No listings match your criteria.");
            content.setPanels();
        }

    }

    public void suOptions(Connect conn, Person user){
        suGUI = new SUserGUI(conn, width, height, user);
        cardLayout.show(mainPanel, SUPER);
    }

    public void sellSomething(Connect conn, Person user){
        sell = new SellingGUI(conn, user);
        cardLayout.show(mainPanel, SELL);
    }

    public void switchToReview(Connect conn, Person rater, Person ratedUser, Sale sale){
        rate = new Rating(conn, rater, ratedUser, sale, this);
        mainPanel.add(rate, RATING);
        cardLayout.show(mainPanel, RATING);
    }

    public void switchToProfile() {

        cardLayout.show(mainPanel, PROFILE);

    }

    public void switchToSellingScreen() {

        cardLayout.show(mainPanel, SELL);

    }

    public void switchToHome() {
        cardLayout.show(mainPanel, CONTENT);

    }

    public void switchToPaymentInfo(){
        cardLayout.show(mainPanel, BILLING);
    }

    public void switchToBuying(){
        cardLayout.show(mainPanel, BUYING);

    }

    public void switchToComplaintScreen(Connect conn, Person user, Person seller, Sale sale){
        complaint = new Complaint(conn, user, seller, sale, this);
        mainPanel.add(complaint, COMPLAINT);
        cardLayout.show(mainPanel, COMPLAINT);

    }


    public void switchToReadComment(Connect conn, Sale sale){
        review = new ReviewGUI(conn,sale,width,height,this);
        mainPanel.add(review, REVIEW);
        cardLayout.show(mainPanel, REVIEW);

    }

}
