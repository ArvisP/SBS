import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;


public class BillingInfoPanel extends JPanel {

    private final JLabel cardNumberLabel;
    private final JLabel cardCsvLabel;
    private final JLabel expdateLabel;
    private final JLabel nameOnCardLabel;
    private final JLabel fname;
    private final JLabel lname;


    //-----labels with actual user info----------
   /* private JLabel nameOnCard;
    private JLabel cardNumber; //MAke so that only last 4 digits show
    private final JLabel csv = new JLabel("***");
    private JLabel address;
    private JLabel state;
    private JLabel city;
    private JLabel zip;
    private JLabel cardExpDate; */


    private JLabel cardInfo;
    private JLabel prompt;


    //---------------other stuff----------------- SORT THIS OUT
    private String expDate;
    private GridBagLayout layout = new GridBagLayout();
    private String[] states;
    private String[] month;
    private String[] year;
    private String[] day;
    private JButton editPaymentMethod;


//------Fields for the user to fill out-----------


    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField cardName;
    private JTextField cardNum;
    private JTextField csvNum;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField zipField;
    private JComboBox monthBox;
    private JComboBox dayBox;
    private JComboBox yearBox;
    private JComboBox statesBox;












    public BillingInfoPanel(Connect conn, Person user, mainGUI frame){


        JDialog message = new JDialog();


        setVisible(true);
        setLayout(layout);


        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 50;

        c.insets = new Insets(10, 10, 10, 20);
        c.ipady = 20;



        month = new String[12];
        day = new String[31];
        year = new String[100];



        setMonth();
        setDay();
        setYear();


        monthBox = new JComboBox(month);
        dayBox = new JComboBox(day);
        yearBox = new JComboBox(year);


        prompt = new JLabel("Add your billing information");

        conn.setBillingInfo(user);

        c.fill = GridBagConstraints.HORIZONTAL;

       /* if(!user.getType().equals("V") && !user.getC_name().isEmpty()) {
            Border b;
            c.ipadx = 10;
            c.weightx = 0;

            c.insets = new Insets(30, 10, 10, 10);
            c.anchor = GridBagConstraints.NORTHEAST;
            b = BorderFactory.createLineBorder(Color.black);
            if(!user.getC_number().isEmpty()) {
                cardInfo = new JLabel("Currently using card: " + user.getC_number());
                cardInfo.setFont(new Font("Currier", Font.BOLD, 15));
                cardInfo.setBorder(b);
                add(cardInfo, c);
                prompt.setText("Edit your billing information");

            }
            c.gridy++;

        } */



        prompt = new JLabel("Enter your billing information");



        c.insets = new Insets(5, 10, 5, 20);

        prompt.setFont(new Font("Serif", Font.PLAIN, 20));
        add(prompt, c);
        c.gridy++;


        fname = new JLabel("First Name");
        fname.setFont(new Font("Serif", Font.PLAIN, 19));
        fname.setVisible(false);
        add(fname, c);

        c.gridy++;

        lname = new JLabel("Last Name");
        lname.setFont(new Font("Serif", Font.PLAIN, 19));
        lname.setVisible(false);
        add(lname, c);
        c.gridy++;


        nameOnCardLabel = new JLabel("Name on Card");
        nameOnCardLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        nameOnCardLabel.setVisible(true);
        add(nameOnCardLabel, c);

        c.gridy++;

        cardNumberLabel = new JLabel("Card number");
        cardNumberLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        cardNumberLabel.setVisible(true);
        add(cardNumberLabel,c);
        c.gridy++;

        cardCsvLabel = new JLabel("CSV");
        cardCsvLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        cardCsvLabel.setVisible(true);
        add(cardCsvLabel, c);
        c.gridy++;


        expdateLabel = new JLabel("Card expires on: ");
        expdateLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        expdateLabel.setVisible(true);
        add(expdateLabel, c);


        //if(user.getType())
        c.gridy++;
/*
        addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        addressLabel.setVisible(false);
        add(addressLabel, c);
        c.gridy++;

        stateLabel = new JLabel("State");
        stateLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        stateLabel.setVisible(false);
        add(stateLabel, c);
        c.gridy++;


        cityLabel = new JLabel("City");
        cityLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        cityLabel.setVisible(false);
        add(cityLabel, c);
        c.gridy++;

        zipLabel = new JLabel("Zip Code");
        zipLabel.setFont(new Font("Serif", Font.PLAIN, 19));
        zipLabel.setVisible(false);
        add(zipLabel, c);
        c.gridy++;

*/
        c.insets = new Insets(15, 10, 10, 20);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 200;
        /*nameOnCard = new JLabel(user.getNameOnCard()); //later extract from user
        nameOnCard.setVisible(true);
        add(nameOnCard, c); */

        c.insets = new Insets(5, 10, 5, 20);
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;





        c.gridy = 1;


        c.ipadx = 200;

        c.gridx = 1;


        firstNameField = new JTextField();
        firstNameField.setVisible(false);
        add(firstNameField, c);
        c.gridy++;

        lastNameField = new JTextField();
        lastNameField.setVisible(false);
        add(lastNameField, c);
        c.gridy++;

        cardName = new JTextField();
        cardName.setVisible(true);
        c.ipadx = 50;
        add(cardName, c);
        c.gridy++;




       /* cardNumber = new JLabel(user.getCardNum()); //get from user. only 4 last digits
        cardNumber.setVisible(true);
        c.ipadx = 50;
        add(cardNumber, c); */
        cardNum = new JTextField(); //get from user
        cardNum.setVisible(true);
        c.ipadx = 200;
        add(cardNum, c);
        c.gridy++;



        csvNum = new JTextField(); //don't add anything
        /*csv.setVisible(true);
        c.ipadx = 200;
        add(csv, c); */
        csvNum.setVisible(true);
        c.ipadx = 50;
        add(csvNum, c);
        c.gridy++;

       /* c.weightx = 1;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL; */

       /* cardExpDate = new JLabel(user.getCardExpDate()); //get card expdate from user
        cardExpDate.setVisible(true);
        add(cardExpDate, c); */



        c.gridwidth = 1;

        c.insets = new Insets(10, 10, 0, 0);
        add(monthBox, c);
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx++;
        add(dayBox, c);
        c.gridx++;

        c.insets = new Insets(20, 0, 10, 20);

        add(yearBox, c);
        c.gridx = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 20);

        monthBox.setVisible(true);
        dayBox.setVisible(true);
        yearBox.setVisible(true);
        c.gridy++;





/*
       //address = new JLabel(user.getCardAddress());
        addressField = new JTextField();
       // address.setVisible(true);
        addressField = new JTextField();
        addressField.setVisible(false);
        //add(address, c);
        add(addressField, c);
        c.gridy++;

       // state = new JLabel(user.getCardState());
       // add(state, c);
       // state.setVisible(true);
        states = new String[50];
        setStates();
        statesBox = new JComboBox(states);
        add(statesBox, c);
        statesBox.setVisible(false);
        c.gridy++;



       // city = new JLabel(user.getCardCity());
       // city.setVisible(true);
       // add(city, c);

        cityField = new JTextField();
        cityField.setVisible(false);
        add(cityField, c);

        c.gridy++;


        //String str = String.valueOf(user.getCardZip());
        zipField = new JTextField();
        //zip = new JLabel(str);
        //zip.setVisible(true);
        // add(zip, c);

        zipField = new JTextField();
        zipField.setVisible(false);
        add(zipField, c);





        if(user.getType().equals("V")){


            fname.setVisible(true);
            lname.setVisible(true);
            addressLabel.setVisible(true);
            stateLabel.setVisible(true);
            cityLabel.setVisible(true);
            zipLabel.setVisible(true);


            addressField.setVisible(true);
            statesBox.setVisible(true);
            cityField.setVisible(true);
            zipField.setVisible(true);
            firstNameField.setVisible(true);
            lastNameField.setVisible(true);

        }



*/





        JButton cancel = new JButton("Cancel");
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10,10,10,5);
        c.gridy++;
        c.gridx = 0;
        c.ipadx = 200;
        add(cancel, c);



        JButton editPaymentMethod = new JButton("Save Changes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.insets = new Insets(10,10,10,20);
        editPaymentMethod.setVisible(true);
        add(editPaymentMethod, c);

        editPaymentMethod.addActionListener(e -> {

            boolean correct = false;
            boolean cardDate;


            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            expirationDate();

            correct = verifyFields(conn, user);
            cardDate = verifyExpDate();

           /* if(correct && cardDate && user.getType().equals("V")) {

                String visitorID;

                visitorID = conn.getVisitorID();


                user.setUsername(visitorID);
                user.setfName(firstNameField.getText());
                user.setlName(lastNameField.getText());
                user.setC_number(cardNum.getText());
                user.setC_expdate(expDate);
                user.setCsv(Integer.parseInt(csvNum.getText()));
                user.setAddress(addressField.getText());
                user.setState(statesBox.getSelectedItem().toString());
                user.setCity(cityField.getText());
                user.setZip(Integer.valueOf(zipField.getText()));
                user.setC_name(cardName.getText());


                conn.update(user);


                JOptionPane.showMessageDialog(dialog, "Order placed");

                frame.switchToHome(); //later switch back to purchase screen
            }
*/
            if(correct && cardDate && !user.getType().equals("V")){


                user.setC_name(cardName.getText());
                user.setC_number(Integer.parseInt(cardNum.getText()));
                user.setC_expdate(expDate);
                user.setCsv(Integer.parseInt(csvNum.getText()));

                conn.updatePaymentMethod(user);

                JOptionPane.showMessageDialog(dialog, "Billing Info Saved.\nNew card: " + cardNum.getText());

            }

            {



            }


        });


        cancel.addActionListener(e -> {


                frame.switchToProfile();



        });






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
            year[i] = Integer.toString(2016 + i);
    }


    public void expirationDate(){
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
        expDate = m+"/"+d+"/"+y;
    }



    public boolean verifyFields(Connect conn, Person user){

        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);



        if(!user.getType().equals("V")) {
            if (cardName.getText().isEmpty() || cardNum.getText().isEmpty() || csvNum.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields must be filled.");
                return false;
            }
        }

        else {

        }


        return true;

    }


    public boolean verifyExpDate(){

        JDialog dialog2 = new JDialog();
        dialog2.setAlwaysOnTop(true);

        Calendar today = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        String today2 = formatter.format(today.getTime());

        System.out.println(today2);


        try {
            Date date1 = formatter.parse(expDate);
            Date date2 = formatter.parse(today2);

            if(date1.before(date2)){
                JOptionPane.showMessageDialog(dialog2, "Card expired.");
                return true;
            }

            else {

                return true;
            }

        }
        catch(ParseException e){
            e.printStackTrace();
        }


        return false;

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

}
