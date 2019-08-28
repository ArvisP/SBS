import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.sql.Connection;
import java.sql.*;

public class Complaint extends JPanel {


    private GridBagLayout layout = new GridBagLayout();


    public Complaint(Connect conn, Person complainer, Person violator, Sale sale, mainGUI frame) {

        setLayout(layout);
        setVisible(true);



        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weighty = 1;
        c.ipady = 30;
        c.ipadx = 85;
        c.fill = GridBagConstraints.HORIZONTAL;

        //  JButton browse = new JButton("Browse");
       /* JButton myProfile = new JButton("My Profile");
        JButton addSale = new JButton("Add Item"); //maybe change the label
        JButton complain = new JButton("Complain");
        JButton logOut = new JButton("Log Out");


        // panelComplaint.add(browse, c);
        // c.gridx = c.gridx + 1;
        add(addSale, c);
        c.gridx = c.gridx + 1;
        add(myProfile, c);
        c.gridx = c.gridx + 1;
        add(complain, c);
        c.gridx = c.gridx + 1;
        add(logOut, c);

*/
        c.ipady = 0;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = .1;
        c.gridheight = 1;
        c.weightx = .3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20,20,0,20);

        JLabel title = new JLabel("Complaint Form");
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        add(title, c);

        Border b1;
        b1 = BorderFactory.createLineBorder(Color.black);

        c.gridy++;
        c.gridwidth = 5;
        c.gridheight = 1;
        c.weighty = 3;
        JTextArea complaintText = new JTextArea();
        complaintText.setPreferredSize(new Dimension(400, 300));
        complaintText.setBorder(b1);
        complaintText.setLineWrap(true);
        add(complaintText, c);


        JButton cancelBtn = new JButton("Cancel");


        c.insets = new Insets(0,20,0,0);
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weighty = .1;

        c.ipadx = 100;
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 5;

        add(cancelBtn, c);

        JButton sendBtn = new JButton("Send");
        c.gridwidth = 2;
        c.gridheight = 2;

        c.insets = new Insets(0,0,0,20);
        c.ipadx = 100;
        c.gridx = 2;
        c.gridy = 5;

        add(sendBtn, c);

        sendBtn.addActionListener(e -> {


            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);

            if(complaintText.getText().isEmpty()){
                JOptionPane.showMessageDialog(dialog, "Fill out complaint form");
            }

            else{

                String str = complaintText.getText();


                if(violator.getS_transactions() > 0 && violator.getS_transactions() <=5) {
                    violator.setS_transactions(violator.getS_transactions() - 1);
                }
                if(complainer.getS_transactions() > 0 && complainer.getS_transactions() <=5) {
                    complainer.setS_transactions(complainer.getS_transactions() - 1);
                }
                complainer.incrementComplaintsGiven();
                violator.incrementComplaintsReceived();





                conn.addComplaint(complainer, violator, sale, str);
                conn.update(complainer);
                conn.update(violator);


                if(complainer.getN_lowestRatings() == 3 && complainer.getComplaintsGiven() == 3) {
                    complainer.setFlags(1);
                    conn.update(complainer);
                }

                if(!complainer.getType().equals("V") && complainer.getN_lowestRatings() == 3 && complainer.getComplaintsGiven() == 3) {
                    complainer.addFlag();
                    conn.update(complainer);
                }



            }

            frame.switchToBuying();


        });

        cancelBtn.addActionListener(e -> {



            frame.switchToBuying();



        });


    }
}