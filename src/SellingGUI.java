import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.nio.channels.FileChannel;

import java.nio.file.Files;



public class SellingGUI extends JPanel {

    private JButton addItembtn;
    GridBagLayout layout = new GridBagLayout();

    private JTextField expdateField;
    private JTextArea durationField;
    private JComboBox exp;

    public JButton goBack;
    private JTextArea title;
    private JTextArea desc;
    private JTextField price;
    private JTextArea keywords;
    private JTextArea brand;
    private JTextArea model;
    private JTextArea color;
    private JTextField imageField;
    private JComboBox dur;
    private JComboBox categoriesBox;
    private String[] days;
    private String name;


/*
    private JComboBox monthBox;
    private JComboBox dayBox;
    private JComboBox yearBox;
    private String[] month;
    private String[] year;
    private String[] day;

*/

    public SellingGUI(Connect conn, Person user) {

        setLayout(layout);
        addItembtn = new JButton("Add Item");

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridy = 0;
        c2.gridx = 0;
        c2.weighty = .1;
        c2.anchor = GridBagConstraints.NORTHWEST;
/*
        year = new String[100];
        month = new String[12];
        day = new String[31];

        setDay();
        setMonth();
        setYear();

        monthBox = new JComboBox(month);
        dayBox = new JComboBox(day);
        yearBox = new JComboBox(year);

*/


        JLabel addItem = new JLabel("Add Sale Item");
        addItem.setFont(new Font("Serif", Font.PLAIN, 24));
        add(addItem, c2);

        c2.gridy++;

        JLabel expDate = new JLabel("Listing expires in (days): ");
        expDate.setFont(new Font("Serif", Font.PLAIN, 17));

        JLabel durationLabel = new JLabel("Duration of service (days): ");
        durationLabel.setFont(new Font("Serif", Font.PLAIN, 17));

        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        JLabel brandLabel = new JLabel("Brand");
        brandLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        JLabel modelLabel = new JLabel("Model");
        modelLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        JLabel colorLabel = new JLabel("Color");
        colorLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        JLabel descLabel = new JLabel("Description");
        descLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        JLabel keywordsLabel = new JLabel("Keywords");
        keywordsLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        JLabel priceLabel = new JLabel("Price");
        JLabel picture = new JLabel("Add Image Path");
        picture.setFont(new Font("Serif", Font.PLAIN, 17));
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        add(categoryLabel, c2);
        c2.gridy++;


        add(titleLabel, c2);
        c2.gridy++;


        add(expDate, c2);
        expDate.setVisible(true);
        add(expDate, c2);
        c2.gridy++;

        add(brandLabel, c2);
        brandLabel.setVisible(true);
        add(brandLabel, c2);
        c2.gridy++;

        add(durationLabel, c2);
        durationLabel.setVisible(false);
        add(modelLabel, c2);
        c2.gridy++;

        add(colorLabel, c2);
        c2.gridy++;

        add(descLabel, c2);
        c2.gridy = (c2.gridy + 1);
        add(keywordsLabel, c2);
        c2.gridy = (c2.gridy + 1);
        add(priceLabel, c2);


        c2.gridy++;







        add(picture, c2);








        c2.gridy++;//in case more labels are added there


        c2.gridx = 1;
        c2.gridy = 1;


        days = new String[100];

        setDays(days);


        brand = new JTextArea();
        model = new JTextArea();
        color = new JTextArea();
        exp = new JComboBox(days);
        dur = new JComboBox(days);


        String[] categories = {"", "Sale Item", "Auction Item", "Service"};
        categoriesBox = new JComboBox(categories);
        categoriesBox.setPreferredSize(new Dimension(500, 30));
        categoriesBox.setSelectedIndex(0);
        add(categoriesBox, c2);
        categoriesBox.addActionListener(e -> {

            String str = (String) categoriesBox.getSelectedItem();
            if (str.equals("Service")) {


                priceLabel.setText("Price");

                color.setVisible(false);
                brand.setVisible(false);
                model.setVisible(false);
                brandLabel.setVisible(false);
                modelLabel.setVisible(false);
                colorLabel.setVisible(false);


                expDate.setVisible(true);
                durationLabel.setVisible(true);
                exp.setVisible(true);
                dur.setVisible(true);
                addItembtn.setText("Add service");


            } else if (str.equals("") || str.equals("Sale Item")) {

                priceLabel.setText("Price");

                expDate.setVisible(true);
                exp.setVisible(true);
                dur.setVisible(false);
                durationLabel.setVisible(false);
                brand.setVisible(true);
                model.setVisible(true);
                color.setVisible(true);
                brandLabel.setVisible(true);
                modelLabel.setVisible(true);
                colorLabel.setVisible(true);
                addItembtn.setText("Add item");
            } else if (str.equals("Auction Item")) {

                priceLabel.setText("Starting price");

                dur.setVisible(false);
                durationLabel.setVisible(false);

                expDate.setVisible(true);
                exp.setVisible(true);
                expDate.setVisible(true);
                brand.setVisible(true);
                model.setVisible(true);
                color.setVisible(true);
                brandLabel.setVisible(true);
                modelLabel.setVisible(true);
                colorLabel.setVisible(true);
                addItembtn.setText("Add item");


            }


            //ADD STUFF HERE. CONNECT TO THE DATABASE THE INPUT FROM THE USER
            //
        });

        c2.gridy++;

        Border b1;
        b1 = BorderFactory.createLineBorder(Color.black);

        title = new JTextArea();
        title.setPreferredSize(new Dimension(500, 30));
        title.setBorder(b1);
        add(title, c2);
        c2.gridy++;


        exp.setPreferredSize(new Dimension(500, 30));
        exp.setVisible(true);
        add(exp, c2);
        c2.gridy++;

        brand.setPreferredSize(new Dimension(500, 30));
        brand.setBorder(b1);

        add(brand, c2);
        c2.gridy++;


        dur.setPreferredSize(new Dimension(500, 30));
        dur.setBorder(b1);
        dur.setVisible(false);
        add(dur, c2);


        //Model setting
        model.setPreferredSize(new Dimension(500, 30));
        model.setBorder(b1);
        add(model, c2);
        c2.gridy++;


        //color settings
        color.setPreferredSize(new Dimension(500, 30));
        color.setBorder(b1);
        add(color, c2);
        c2.gridy++;


        desc = new JTextArea();
        desc.setPreferredSize(new Dimension(500, 100));
        desc.setBorder(b1);
        desc.setLineWrap(true);

        add(desc, c2);
        c2.gridy = c2.gridy + 1;


        keywords = new JTextArea();
        keywords.setPreferredSize(new Dimension(500, 60));
        keywords.setBorder(b1);
        keywords.setLineWrap(true);
        add(keywords, c2);
        c2.gridy = c2.gridy + 1;


        price = new JTextField();
        price.setPreferredSize(new Dimension(500, 30));
        price.setBorder(b1);
        add(price, c2);
        c2.gridy++;

        imageField = new JTextField();
        imageField.setPreferredSize(new Dimension(500, 30));
        imageField.setBorder(b1);
        add(imageField, c2);
        c2.gridy++;


        c2.anchor = GridBagConstraints.LAST_LINE_START;
        c2.gridy = 20;
        c2.gridx = 0;
      
        c2.ipadx = 200;
        c2.gridwidth = 3;

        addItembtn = new JButton("Submit");
        addItembtn.setPreferredSize(new Dimension(100, 30));
        c2.ipadx = 200;
        c2.anchor = GridBagConstraints.LAST_LINE_END;
        c2.gridwidth = 1;
        c2.gridx = c2.gridx + 1;
        add(addItembtn, c2);

      

        addItembtn.addActionListener(e -> {

            String str = categoriesBox.getSelectedItem().toString();

            //LATER display a message that Item was added

            boolean correct = verifyFields();
            int saleID;
            System.out.println(correct);
            System.out.println(str);
            //update database

            if (str.equals("Sale Item") && correct) {

                Double p = Double.parseDouble(price.getText());
                String brandName = brand.getText();
                String modelName = model.getText();
                String colorName = color.getText();
                String description = desc.getText();
                String expdate = setExpDate(exp.getSelectedItem().toString());
                Integer duration = Integer.parseInt(exp.getSelectedItem().toString());


                Sale sale = new Sale(23, user, title.getText(), description, p, "Item", expdate, duration, brandName, modelName, colorName);
                sale.setIsAuction(false);
                conn.addSaleRow(user, sale);
                saleID = sale.getSaleID();


                try {
                    copyImage(saleID);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                sale.setImage(name);

                conn.updateSale(sale, user);



            } else if (str.equals("Service") && correct) {

                Double p = Double.parseDouble(price.getText());
                String brandName = brand.getText();
                String modelName = model.getText();
                String colorName = color.getText();
                String description = desc.getText();
                String expdate = setExpDate(exp.getSelectedItem().toString());
                Integer duration = Integer.parseInt(dur.getSelectedItem().toString());

                Sale sale = new Sale(11, user, title.getText(), description, p, "Service", expdate, duration, brandName, modelName, colorName);
                sale.setIsAuction(false);
                conn.addSaleRow(user, sale);

            }

            else if(str.equals("Auction Item") && correct) {

                Double p = Double.parseDouble(price.getText());
                String brandName = brand.getText();
                String modelName = model.getText();
                String colorName = color.getText();
                String description = desc.getText();
                String expdate = setExpDate(exp.getSelectedItem().toString());
                Integer duration = Integer.parseInt(exp.getSelectedItem().toString());

                Sale sale = new Sale(11, user, title.getText(), description, p, "Item", expdate, duration, brandName, modelName, colorName);
                sale.setIsAuction(true);
                conn.addSaleRow(user, sale);
            }

        });

    }


    public String setExpDate(String expires) {

        int days = Integer.parseInt(expires);

        LocalDate date = LocalDate.now().plusDays(days);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");

        String expiresOn = date.format(formatter);

        return expiresOn;


    }


    public boolean verifyFields() {


        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        if (categoriesBox.getSelectedItem().toString().equals("Sale Item") || categoriesBox.getSelectedItem().toString().equals("Auction Item")) {

            System.out.println("item");
            if (title.getText().isEmpty() || desc.getText().isEmpty() || brand.getText().isEmpty() || color.getText().isEmpty() ||
                    model.getText().isEmpty() || exp.getSelectedItem().toString().isEmpty() ||
                    categoriesBox.getSelectedItem().toString().isEmpty() || price.getText().isEmpty()) {

                JOptionPane.showMessageDialog(dialog, "All fields must be filled.");
                return false;

            }

            else {
                return true;
            }

        }

        else if (categoriesBox.getSelectedItem().toString().equals("Service")) {

            System.out.println("HEY!!!");
            if (title.getText().isEmpty() || desc.getText().isEmpty() || exp.getSelectedItem().toString().isEmpty() ||
                    categoriesBox.getSelectedItem().toString().isEmpty() || dur.getSelectedItem().toString().isEmpty() ||
                    price.getText().isEmpty()) {

              //  System.out.println("SOmething is empty");
                JOptionPane.showMessageDialog(dialog, "All fields must be filled.");
                return false;

            }


            else {
                return true;
            }


        }


        else if(categoriesBox.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(dialog, "All fields must be filled.");
            return false;
        }

        // ADD else ifs for other cases

        else {
            return false;
        }

    }





    public void setDays(String[] days){

        //SETS THE MAXIMUM POSSIBLE VALUE FOR DURATION OF A LISTING TO 100. MAYBE INCREASE THE NUMBER
        for(int i = 0; i <100; i++){

            int count = i+1;
            String numToText = String.valueOf(count);
            days[i] = numToText;
        }
    }





    public void copyImage(int saleID) throws InterruptedException, IOException{

        String path = imageField.getText();
        String[] extension;
        extension = path.split("\\.");
        name = saleID + "." + extension[1];


        File source = new File(imageField.getText());

        File descination = new File("C:\\Users\\Me\\userProfile\\" + name);

        FileChannel input = null;
        FileChannel output = null;

        try {

            input = new FileInputStream(source).getChannel();
            output = new FileOutputStream(descination).getChannel();
            output.transferFrom(input, 0, input.size());

        } finally {

            input.close();
            output.close();

        }



    }



}