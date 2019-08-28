import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.String;

public class ProfileGUI extends JPanel {

    private String dob;
    private JLabel state;
    private GridBagLayout layout = new GridBagLayout();
    private String[] states;
    private String[] month;
    private String[] year;
    private String[] day;


    //----JLabels for user's attributes---------

    private final JLabel usernameLabel;
    private final JLabel passLabel;
    private final JLabel newPassLabel;
    private final JLabel oldPassLabel;
    private final JLabel newPassConfLabel;
    private final JLabel fnameLabel;
    private final JLabel lnameLabel;
    private final JLabel dobLabel;
    private final JLabel emailLabel;
    private final JLabel addressLabel;
    private final JLabel stateLabel;
    private final JLabel cityLabel;
    private final JLabel zipLabel;



    //----JLabels and comboBox for user's profile information---------------

    private JLabel FirstNameField;
    private JLabel LastNameField;
    private JLabel DOB;
    private JLabel emailField;
    private JLabel addressField;
    private JComboBox statesBox;
    private JLabel CityLabel;
    private JLabel ZipCodeLabel;
    private JLabel userName;
    private JLabel passwordLabel;

    //----Fields for user to fill in---------------------------------------

    private JPasswordField oldPass;
    private JPasswordField newPass;
    private JPasswordField newPassConfirm;
    private JTextField FirstNameField1;
    private JTextField LastNameField1;
    private JTextField dobTextField;
    private JTextField emailField1;
    private JTextField addressField1;
    private JTextField CityField;
    private JTextField ZipCodeField;
    private JComboBox monthBox;
    private JComboBox dayBox;
    private JComboBox yearBox;
    public JButton editPaymentMethod;


    public ProfileGUI(Connect conn, Person user) {

       // JDialog message = new JDialog();


        states = new String[50];
        year = new String[100];
        month = new String[12];
        day = new String[31];


        setVisible(true);
        setLayout(layout);


        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 100;
        c.ipady = 20;
        c.weightx = 0.02;
        c.weighty = 0.01;
        c.insets = new Insets(35, 30, 10, 10);
        c.anchor = GridBagConstraints.NORTHWEST;
        //c.fill = GridBagConstraints.BOTH;

        setStates();
        setMonth();
        setDay();
        setYear();

        statesBox = new JComboBox(states);

        Font f = new Font("Currier", Font.PLAIN, 16);


        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        usernameLabel.setVisible(true);
        add(usernameLabel, c);
        c.gridy++;

        c.insets = new Insets(10, 30, 10, 10);

        passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        passLabel.setVisible(true);
        add(passLabel, c);
        oldPassLabel = new JLabel("Old Password");
        add(oldPassLabel, c);
        oldPassLabel.setVisible(false);
        c.gridy++;

        newPassLabel = new JLabel("New Password");
        add(newPassLabel, c);
        newPassLabel.setVisible(false);
        c.gridy++;

        newPassConfLabel = new JLabel("Confirm Password");
        add(newPassConfLabel, c);
        newPassConfLabel.setVisible(false);
        c.gridy++;


        fnameLabel = new JLabel("First Name");
        fnameLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        fnameLabel.setVisible(true);
        add(fnameLabel, c);
        c.gridy++;

        lnameLabel = new JLabel("Last Name");
        lnameLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        lnameLabel.setVisible(true);
        add(lnameLabel, c);
        c.gridy++;

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        dobLabel.setVisible(true);
        add(dobLabel, c);
        c.gridy++;


        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        emailLabel.setVisible(true);
        add(emailLabel, c);
        c.gridy++;

        addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        add(addressLabel, c);
        c.gridy++;

        stateLabel = new JLabel("State");
        stateLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        add(stateLabel, c);
        c.gridy++;


        cityLabel = new JLabel("City");
        cityLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        add(cityLabel, c);
        c.gridy++;

        zipLabel = new JLabel("Zip Code");
        zipLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        add(zipLabel, c);


        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 20;
        c.gridwidth = 5;
        c.ipadx = 250;
        c.fill = GridBagConstraints.HORIZONTAL;


        c.insets = new Insets(45, 10, 10, 10);
        userName = new JLabel(user.getUsername()); //later extract username from database
        add(userName, c);
        c.gridy++;



        c.insets = new Insets(20, 10, 10, 20);
        oldPass = new JPasswordField(50);
        newPass = new JPasswordField(50);
        newPassConfirm = new JPasswordField(50);
        FirstNameField = new JLabel(user.getfName());
        FirstNameField1 = new JTextField(50);
        LastNameField = new JLabel(user.getlName());
        LastNameField.setPreferredSize(new Dimension(300,20));
        LastNameField1 = new JTextField(15);
        DOB = new JLabel(user.getDOB());
        monthBox = new JComboBox(month);
        dayBox = new JComboBox(day);
        yearBox = new JComboBox(year);
        emailField = new JLabel(user.getEmail());
        emailField1 = new JTextField(15);
        addressField = new JLabel(user.getAddress());
        addressField1 = new JTextField(15);
        dobTextField = new JTextField(15);
        state = new JLabel(user.getState().toString());
        CityLabel = new JLabel(user.getCity());
        CityField = new JTextField(15);

        usernameLabel.setFont(f);
        LastNameField.setFont(f);
        FirstNameField.setFont(f);
        emailField.setFont(f);
        dobTextField.setFont(f);
        CityLabel.setFont(f);
        zipLabel.setFont(f);
        addressField.setFont(f);
        state.setFont(f);



        passwordLabel = new JLabel(" * * * * * ");
        add(passwordLabel, c);

        c.insets = new Insets(0, 10, 10, 20);
        add(oldPass, c);
        oldPass.setVisible(false);
        c.gridy++;
        add(newPass, c);
        c.insets = new Insets(0, 10, 10, 20);
        newPass.setVisible(false);
        c.gridy++;
        add(newPassConfirm, c);
        c.insets = new Insets(10, 10, 0, 20);
        newPassConfirm.setVisible(false);
        c.gridy++;





        c.insets = new Insets(5, 10, 10, 20);
        add(FirstNameField, c);
        add(FirstNameField1, c);
        FirstNameField.setVisible(true);
        c.insets = new Insets(0, 10, 5, 20);
        FirstNameField1.setVisible(false);
        c.gridy++;
        c.insets = new Insets(10, 10, 5, 20);
        add(LastNameField, c);
        c.insets = new Insets(10, 10, 0, 20);
        add(LastNameField1, c);
        LastNameField1.setVisible(false);



        c.gridy++;
        add(DOB, c);
        DOB.setVisible(true);

        c.gridwidth = 1;
        c.weightx = .6;
        c.insets = new Insets(10, 10, 0, 0);
        add(monthBox, c);
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx++;
        add(dayBox, c);
        c.gridx++;
        c.insets = new Insets(10, 0, 0, 20);
        add(yearBox, c);
        c.gridx = c.gridx - 2;
        c.gridwidth = 5;
        c.insets = new Insets(20, 10, 10, 20);

        monthBox.setVisible(false);
        dayBox.setVisible(false);
        yearBox.setVisible(false);

        c.gridy++;

        add(emailField, c);
        add(emailField1, c);//add new component
        emailField1.setVisible(false);
        c.gridy++;


        c.insets = new Insets(10, 10, 10, 20);
        add(addressField, c);

        add(addressField1, c);
        addressField1.setVisible(false);
        c.gridy++;


        add(state, c);
        state.setVisible(true);
        add(statesBox, c);
        statesBox.setVisible(false);
       
        c.gridy++;



        add(CityLabel, c);
        add(CityField, c);
        CityField.setVisible(false);
        c.gridy++;


        String strZip = String.valueOf(user.getZip());

        ZipCodeLabel = new JLabel(strZip);
        add(ZipCodeLabel, c);
        ZipCodeField = new JTextField(15);
        GridBagConstraints conAtZip = new GridBagConstraints();
        add(ZipCodeField, c);
        ZipCodeLabel.setVisible(true);
        ZipCodeField.setVisible(false);
        conAtZip.gridy = c.gridy;
        conAtZip.gridx = c.gridx;
        c.gridy++;

        c.gridx = c.gridx+6;
        c.gridy = c.gridy+3;


        JButton editProfile = new JButton("Edit");
        c.gridy = c.gridy + 3;
        c.ipadx = 200;
        c.gridx = 0;
        add(editProfile, c);



        editPaymentMethod = new JButton("Manage Payment Method");
        c.fill = GridBagConstraints.NONE;
        c.gridx = c.gridx+5;
        c.ipadx = 100;
        editPaymentMethod.setVisible(true);
        add(editPaymentMethod, c);



        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 100;


        editProfile.addActionListener(e -> {
            boolean correct = true; //all fields contain info that is consistent with the rules


            switch (editProfile.getText()) {
                case "Edit":


                    passLabel.setVisible(false);
                    addressField.setVisible(false);
                    FirstNameField.setVisible(false);
                    LastNameField.setVisible(false);
                    emailField.setVisible(false);
                    DOB.setVisible(false);
                    passwordLabel.setVisible(false);
                    CityLabel.setVisible(false);
                    ZipCodeLabel.setVisible(false);
                    state.setVisible(false);
                    editPaymentMethod.setVisible(false);



                    oldPassLabel.setVisible(true);
                    newPassLabel.setVisible(true);
                    newPassConfLabel.setVisible(true);
                    addressField1.setVisible(true);//add new component
                    FirstNameField1.setVisible(true);//add new component
                    LastNameField1.setVisible(true);//add new component
                    emailField1.setVisible(true);//add new component
                    monthBox.setVisible(true);
                    dayBox.setVisible(true);
                    yearBox.setVisible(true);
                    oldPass.setVisible(true);
                    newPass.setVisible(true);
                  //  newPass.setDocument(new plainDoc(12)); //limit the length of the new password
                    newPassConfirm.setVisible(true);
                 //   newPassConfirm.setDocument(new plainDoc(12));
                    CityField.setVisible(true);
                    statesBox.setVisible(true);
                    ZipCodeField.setVisible(true);

                    //  if(!user.getAddress().equals(addressField.getText())
                    addressField1.setText(addressField.getText());
                    FirstNameField1.setText(FirstNameField.getText());
                    LastNameField1.setText(LastNameField.getText());
                    emailField1.setText(emailField.getText());
                    dobTextField.setText(user.getDOB());
                    CityField.setText(CityLabel.getText());
                    ZipCodeField.setText(ZipCodeLabel.getText());



                    editProfile.setText("Save changes");
                    revalidate();
                    repaint();

                    break;
                case "Save changes":

                    dob();

                    if(addressField1.getText().equals(user.getAddress())     &&
                            emailField.getText().equals(user.getEmail())     &&
                            FirstNameField.getText().equals(user.getfName()) &&
                            LastNameField.getText().equals(user.getlName())  &&
                            dob.equals(user.getDOB())                        &&
                            state.getText().equals(user.getState())          &&
                            CityLabel.getText().equals(user.getCity())       &&
                            ZipCodeField.getText().equals(String.valueOf(user.getZip()))) {



                        oldPassLabel.setVisible(false);
                        newPassLabel.setVisible(false);
                        newPassConfLabel.setVisible(false);
                        oldPass.setVisible(false);
                        newPass.setVisible(false);
                        newPassConfirm.setVisible(false);
                        addressField1.setVisible(false);
                        FirstNameField1.setVisible(false);
                        LastNameField1.setVisible(false);
                        emailField1.setVisible(false);
                        CityField.setVisible(false);
                        ZipCodeField.setVisible(false);
                        statesBox.setVisible(false);
                        dayBox.setVisible(false);
                        monthBox.setVisible(false);
                        yearBox.setVisible(false);



                        passLabel.setVisible(true);
                        passwordLabel.setVisible(true);
                        FirstNameField.setVisible(true);
                        LastNameField.setVisible(true);
                        DOB.setVisible(true);
                        emailField.setVisible(true);
                        addressField.setVisible(true);
                        state.setVisible(true);
                        CityLabel.setVisible(true);
                        ZipCodeLabel.setVisible(true);


                        editProfile.setText("Edit");//set button text to original
                        revalidate();
                        repaint();
                        break;
                    }


                    correct = verifyFields(conn, user);

                    if (correct) {

                       // System.out.println(user.getState());
                        addressField.setText(addressField1.getText());
                        emailField.setText(emailField1.getText());
                        FirstNameField.setText(FirstNameField1.getText());
                        LastNameField.setText(LastNameField1.getText());
                        DOB.setText(dob);
                        passwordLabel.setText(" * * * * * ");
                        state.setText(statesBox.getSelectedItem().toString());
                        CityLabel.setText(CityField.getText());
                        ZipCodeLabel.setText(ZipCodeField.getText());


                        String str = statesBox.getSelectedItem().toString();
                        int zip = Integer.parseInt(ZipCodeField.getText());


                        user.setPassword(String.valueOf(newPassConfirm.getPassword()));
                        user.setfName(FirstNameField1.getText());
                        user.setlName(LastNameField1.getText());
                        user.setAddress(addressField1.getText());
                        user.setDOB(DOB.getText());
                        user.setState(str);
                        user.setCity(CityLabel.getText());
                        user.setEmail(emailField1.getText());
                        user.setZip(zip);

                        oldPass.setText("");
                        newPass.setText("");
                        newPassConfirm.setText("");


                        oldPassLabel.setVisible(false);
                        newPassLabel.setVisible(false);
                        newPassConfLabel.setVisible(false);
                        oldPass.setVisible(false);
                        newPass.setVisible(false);
                        newPassConfirm.setVisible(false);
                        addressField1.setVisible(false);
                        FirstNameField1.setVisible(false);
                        LastNameField1.setVisible(false);
                        emailField1.setVisible(false);
                        dobTextField.setVisible(false);
                        CityField.setVisible(false);
                        ZipCodeField.setVisible(false);
                        statesBox.setVisible(false);
                        dayBox.setVisible(false);
                        monthBox.setVisible(false);
                        yearBox.setVisible(false);


                        passLabel.setVisible(true);
                        passwordLabel.setVisible(true);
                        FirstNameField.setVisible(true);
                        LastNameField.setVisible(true);
                        DOB.setVisible(true);
                        emailField.setVisible(true);
                        addressField.setVisible(true);
                        state.setVisible(true);
                        CityLabel.setVisible(true);
                        ZipCodeLabel.setVisible(true);
                        editPaymentMethod.setVisible(true);

                        editProfile.setText("Edit");//set button text to original
                        revalidate();
                        repaint();

                        conn.update(user);
                        break;

                    }
                    else {

                        oldPass.setText("");
                        newPass.setText("");
                        newPassConfirm.setText("");


                    }



            }



        });


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



   public void dob(){
        String d = dayBox.getSelectedItem().toString();
        String y = yearBox.getSelectedItem().toString();
        String m = new String();
        String selMonth = monthBox.getSelectedItem().toString();
        if(selMonth.equals("January")) m = "01";
        else if(selMonth.equals("February")) m = "02";
        else if(selMonth.equals("March")) m = "03";
        else if(selMonth.equals("April")) m = "04";
        else if(selMonth.equals("May")) m = "05";
        else if(selMonth.equals("June")) m = "06";
        else if(selMonth.equals("July")) m = "07";
        else if(selMonth.equals("August")) m = "08";
        else if(selMonth.equals("September")) m = "09";
        else if(selMonth.equals("October")) m = "10";
        else if(selMonth.equals("November")) m = "11";
        else if(selMonth.equals("December")) m = "12";
        dob = m+"/"+d+"/"+y;
    }


    public boolean verifyFields(Connect conn, Person user){

       // String username = userField.getText();
        String new_password = String.valueOf(newPass.getPassword());
        String new_pass_confirm = String.valueOf(newPassConfirm.getPassword());
        String password = user.getPassword();
        String old_pass = String.valueOf(oldPass.getPassword());

        int zip = ZipCodeField.getText().length();
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);


        if(!old_pass.equals(password)){
            JOptionPane.showMessageDialog(dialog, "Wrong password");
            return false;
        }
        else if(!new_password.equals(new_pass_confirm)){
            JOptionPane.showMessageDialog(dialog, "Passwords do not match.");
            return false;
        }
        else if(password.equals("") || emailField.getText().isEmpty() || FirstNameField1.getText().isEmpty() || LastNameField1.getText().isEmpty()||
                addressField.getText().isEmpty() || CityField.getText().isEmpty() || ZipCodeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "All fields must be filled.");
            return false;
        }
        else if(!ZipCodeField.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(dialog, "The zipcode field can only contain numbers.");
            return false;
        }
        else if(zip!=5) {
            JOptionPane.showMessageDialog(dialog, "Your zipcode is not within the U.S.");
            return false;
        }
        else {

        }

        dob();
        return true;
    }
    

}
