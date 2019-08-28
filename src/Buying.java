import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Image;

import static java.lang.Boolean.TRUE;

public class Buying extends JPanel {


    private JLabel title;
    JTextField bid;
    JButton buy;
    JButton rate;
    JButton approvePurchase;
    JButton complain;
    JButton comment;
    JButton readComment;
    JLabel seller;
    JLabel price;
    JLabel selectBuyer;
    JLabel sellersRating;
    // JLabel oldPrice;
    JTextArea prodDescription1;
    JTextArea prodDescription2;
    private ImageIcon image;
    private JLabel picLabel;
    JLabel sold;
    String[] buyers;
    JComboBox buyersBox = new JComboBox();
    JButton Back;



    private final String regExpression = "[0-9]+([,.][0-9]{1,2})?";



    public Buying(Connect conn, Person user, Sale sale, int width, int height, mainGUI frame) { //   NEED sale AS THE THIRD ARGUMENT

        conn.verify(user.getUsername());
        conn.refresh();
        Back = new JButton("Back");

        setLayout(null);


        DecimalFormat form1 = new DecimalFormat("####0.00");
        String list = sale.getBuyer();
        buyers = new String[100];
        buyers = list.split(",");
        buyersBox = new JComboBox(buyers);
        buyersBox.setVisible(false);

        String highestBidder = sale.getBidder();

        for(int i = 0; i < buyers.length; i++ ){

        if(buyers[i].equals(highestBidder)){
                buyers[i] = buyers[i] + "highest bidder";
            }




        }

        image = new ImageIcon("images/" + sale.getImage());
        Image img = image.getImage();
        Image image2 = img.getScaledInstance(width/2, height/2, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(image2);
        picLabel = new JLabel(image);




        title = new JLabel();
        title.setText(sale.getTitle());
        title.setFont(new Font("Serif", Font.PLAIN, 25));


        price = new JLabel();
        price.setPreferredSize(new Dimension(100, 40));
        price.setText("Price: $" + sale.getPrice());
        price.setFont(new Font("Serif", Font.BOLD, 22));




        if(user.isVIP() && !sale.isAuction()){

            price.setText("VIP Price: $" + form1.format(sale.getPrice()* 0.9));
        }

        if(sale.isAuction()){
        	price.setText("Price: $"+sale.getHighBid());
        }


        seller = new JLabel();
        seller.setText("sold by: " +sale.getSeller().getUsername());
        seller.setFont(new Font("Helvetica", Font.PLAIN, 12));


        DecimalFormat form2 = new DecimalFormat("####0.0");
        sellersRating = new JLabel();
        sellersRating.setText("Rating: " + form2.format(sale.getSeller().getRating()) + " (Based on " +  sale.getSeller().getN_ratings() + " ratings)");
        sellersRating.setFont(new Font("Helvetica", Font.PLAIN, 12));


        readComment = new JButton("Read Comments");



        prodDescription1 = new JTextArea();
        prodDescription2 = new JTextArea();




        prodDescription1.setText("Brand: " + sale.getBrand() + " \nModel: " + sale.getModel() + " \nColor: " + sale.getColor());
        prodDescription2.setText(sale.getDescription());

        prodDescription1.setFont(new Font("Serif", Font.PLAIN, 18));
        prodDescription1.setOpaque(false);
        prodDescription1.setLineWrap(true);
        prodDescription1.setWrapStyleWord(true);
        prodDescription1.setEditable(false);

        prodDescription2.setFont(new Font("Serif", Font.PLAIN, 18));
        prodDescription2.setOpaque(false);
        prodDescription2.setLineWrap(true);
        prodDescription2.setWrapStyleWord(true);
        prodDescription2.setEditable(false);



        buy = new JButton("Buy");
        sold = new JLabel("SOLD");
        sold.setFont(new Font("Serif", Font.BOLD, 25));
        sold.setVisible(false);

        bid = new JTextField();
        bid.setVisible(false);


        comment = new JButton("Leave comment");
        comment.setVisible(false);


        if(sale.isAuction()){
            buy.setText("Bid");
            bid.setVisible(true);
        }

        if(sale.isSold()){

            buy.setVisible(false);
            sold.setVisible(true);
            bid.setVisible(false);

        }


        rate = new JButton("Rate seller");

        rate.setVisible(false);
        complain = new JButton("Complain \nto Admin");
        complain.setVisible(false);


        selectBuyer = new JLabel("SELECT BUYER");
        selectBuyer.setFont(new Font("Serif", Font.PLAIN, 20));
        selectBuyer.setVisible(false);


        approvePurchase = new JButton("Sell");
        approvePurchase.setVisible(false);



        if(sale.getBuyer().equals(user.getUsername()) && sale.isSold()) {

            rate.setText("Rate Seller");

            selectBuyer.setVisible(false);
            approvePurchase.setVisible(false);
            rate.setVisible(true);
            complain.setVisible(true);

        }





        if(user.getUsername().equals(sale.getSeller().getUsername())){

            bid.setVisible(false);
            buy.setVisible(false);
            buyersBox.setVisible(true);
            selectBuyer.setVisible(true);
            approvePurchase.setVisible(true);

            if(sale.isSold()){
                rate.setText("Rate buyer");
                rate.setVisible(true);
                buyersBox.setVisible(false);
                selectBuyer.setVisible(false);
                approvePurchase.setVisible(false);
            }

        }


        if(user.getType().equals("V") && !sale.isSold()){


            buy.setVisible(false);
            bid.setVisible(false);
            complain.setVisible(false);

            comment.setVisible(true);

        }





        setAllBounds(1, 2, width, height);

        add(title);
        add(picLabel);
        add(seller);
        add(price);
        add(prodDescription1);
        add(prodDescription2);
        add(bid);
        add(buy);
        add(rate);
        add(complain);
        add(sold);
        add(buyersBox);
        add(selectBuyer);
        add(approvePurchase);
        add(sellersRating);
        add(comment);
        add(readComment);
        add(Back);



        approvePurchase.addActionListener(e -> {

            Date date = new Date();
            String DATE_FORMAT = "MM/dd/yyyy";
            SimpleDateFormat today = new SimpleDateFormat(DATE_FORMAT);
            String todaysDate = today.format(date).toString();
            Person finalBuyer = conn.getPerson(String.valueOf(buyersBox.getSelectedItem()).toString());
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);

            if(sale.isAuction()){
            	sale.setPrice(sale.getHighBid());
            }
            
            conn.refresh();
            conn.verify(finalBuyer.getUsername());


            if(finalBuyer.getAmount() >= sale.getPrice() && !finalBuyer.isVIP()) {
                double newAmount = finalBuyer.getAmount() - sale.getPrice();
                finalBuyer.setAmount(newAmount);
                user.setAmount(user.getAmount() + sale.getPrice());

                user.setS_transactions(user.getS_transactions() + 1);
                finalBuyer.setS_transactions(user.getS_transactions() + 1);

                sale.setDOP(todaysDate);
                sale.setFinalBuyer(finalBuyer.getUsername());
                sale.setSold(TRUE);

                conn.updateAmount(finalBuyer);
                conn.updateAmount(user);
                conn.update(user);
                conn.bought(sale, finalBuyer);
            }

            else if(finalBuyer.getAmount() >= sale.getPrice()*0.9 && finalBuyer.isVIP() && !finalBuyer.getType().equals("V")) {

                double newAmount = finalBuyer.getAmount() - sale.getPrice()*0.9;
                finalBuyer.setAmount(newAmount);
                user.setAmount(user.getAmount() + sale.getPrice());

                sale.setFinalBuyer(finalBuyer.getUsername());
                sale.setDOP(todaysDate);
                sale.setSold(TRUE);


                conn.updateAmount(finalBuyer);
                conn.updateAmount(user);
                conn.update(user);
                conn.bought(sale, finalBuyer);
                conn.updateSale(sale, finalBuyer);

            }



            else if(finalBuyer.getAmount() < sale.getPrice() && !finalBuyer.isVIP()){
                JOptionPane.showMessageDialog(dialog, "This user doesn't have sufficient funds. Choose another buyer.");


            }

            else if(finalBuyer.getAmount() < sale.getPrice()*0.9 && finalBuyer.isVIP()) {
                JOptionPane.showMessageDialog(dialog, "This user doesn't have sufficient funds. Choose another buyer.");



            }


        });

        buy.addActionListener(e -> {

            Boolean isBuyer = false;
            conn.refresh();
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);


            for(int i = 0; i < buyers.length; i++){


                if(buyers[i].equals(user.getUsername())){

                    isBuyer = true;
                    break;
                }

            }


            {

                switch (buy.getText()) {


                    case "Buy":


                        if (sale.isSold()) {
                            JOptionPane.showMessageDialog(dialog, "This item has already been sold.");
                            break;
                        }

                        if (user.getAmount() >= sale.getPrice() && isBuyer==false) {

                            JOptionPane.showMessageDialog(dialog, "Order Completed");

                            sale.setBuyer(user.getUsername());
                            conn.updateSale(sale, user);


                            break;
                        }

                        else if(user.getAmount() >= sale.getPrice() && isBuyer==true){

                            JOptionPane.showMessageDialog(dialog, "You have already ordered this item");

                        }
                        else if (user.getAmount() < sale.getPrice()) {

                            JOptionPane.showMessageDialog(dialog, "Insufficient funds");

                        }


                        break;

                    case "Bid":


                        if (bid.getText().matches(regExpression)) {

                            Double newBid = Double.parseDouble(bid.getText());

                            if (user.getAmount() >= newBid) {

                                if (newBid > sale.getPrice()) {


                                    bid.setText("");

                                    sale.setPrice(newBid);
                                    sale.setHighBid(newBid);
                                    sale.setBidder(user.getUsername());

                                    conn.updateAuction(sale, user);
                                    sale.setBuyer(user.getUsername());
                                    conn.updateSale(sale, user);
                                    price.setText("Price: $" + String.valueOf(sale.getPrice()));


                                    repaint();
                                    revalidate();

                                    break;
                                }
                            } else {

                                JOptionPane.showMessageDialog(dialog, "Insufficient funds");
                            }
                        } else {

                            JOptionPane.showMessageDialog(dialog, "Invalid money format");
                        }
                }

            }
        });


        // Back Button
        Back.addActionListener(e -> {
        	frame.switchToHome();
        }
        );
        
        complain.addActionListener(e -> {



            frame.switchToComplaintScreen(conn, user, sale.getSeller(), sale);





        });

        comment.addActionListener(e -> {



            frame.switchToReview(conn, user, sale.getSeller(), sale);



        });


        rate.addActionListener(e -> {

            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            Boolean alreadyRated = conn.didRate(sale.getSaleID(), user);

            if(alreadyRated){


                JOptionPane.showMessageDialog(dialog, "Already rated");
                return;


            }


            if(sale.getSeller().getUsername().equals(user.getUsername())){
                Person finalBuyer = conn.getPerson(String.valueOf(buyersBox.getSelectedItem()).toString());
                frame.switchToReview(conn, sale.getSeller(), finalBuyer, sale);
            }




            if(sale.getBuyer().equals(user.getUsername())) {
                frame.switchToReview(conn, user, sale.getSeller(), sale);
            }


        });



        readComment.addActionListener(e -> {


            frame.switchToReadComment(conn, sale);




        });






    }



    public void setAllBounds(int x, int y, int width, int height) {
        int tmp_x;
        int tmp_y;
        int tmp_width;
        int newWidth = width*2;
        Font font = new Font("Serif", Font.BOLD, 25);
        tmp_x = (int) (width * 0.03);
        tmp_y = (int) (height * 0.02);
        // Setting all Labels

        title.setFont(font);

        title.setBounds(tmp_x, tmp_y, width / 3, height / 20);
        tmp_y += height / 30;
        seller.setBounds(tmp_x, tmp_y, width / 3, height / 20);
        tmp_x += width / 5;
        sellersRating.setBounds(tmp_x, tmp_y, width / 3, height / 20);


        tmp_y += height / 25;
        readComment.setBounds(tmp_x, tmp_y, width / 4, height / 27);


        tmp_x -= width / 5;
        tmp_y += height / 25;


        picLabel.setBounds(tmp_x, tmp_y, width / 7, height / 7);
        tmp_y += height / 20;
        tmp_x += width / 6;

        prodDescription1.setBounds(tmp_x, tmp_y, newWidth / 3, height );

        tmp_x -= width / 6;
        tmp_y += height / 9;

        prodDescription2.setBounds(tmp_x, tmp_y, newWidth / 3, height*2);


        tmp_x = (int)(width*0.82);
        tmp_y = (int)(height*0.11);
        tmp_width = (int)(width/1.5);

        tmp_x -= 60;
        price.setBounds(tmp_x, tmp_y, width / 2, height / 20);

        tmp_x += 30;

        tmp_y += height / 15;

        tmp_x += height / 30;
        sold.setBounds(tmp_x, tmp_y, newWidth / 10, height / 25);
        tmp_x -= height / 30;

        tmp_x -= height / 20;

        comment.setBounds(tmp_x+50, tmp_y, newWidth / 10, height / 25);

        tmp_x += height / 20;

        bid.setBounds(tmp_x, tmp_y, newWidth / 10, height / 25);
        tmp_y += height / 15;


        selectBuyer.setBounds(tmp_x, tmp_y, newWidth / 10, height / 25);
        buy.setBounds(tmp_x, tmp_y, newWidth / 10, height / 25);



        tmp_y += height / 15;
        buyersBox.setBounds(tmp_x, tmp_y, newWidth / 10, height / 25);
        rate.setBounds(tmp_x, tmp_y, newWidth / 10, height / 25);

        tmp_y += height / 15;
        approvePurchase.setBounds(tmp_x, tmp_y, newWidth / 10, height / 25);
        complain.setBounds(tmp_x, tmp_y, newWidth / 10, height / 25);

        tmp_y += height / 15;
        Back.setBounds(tmp_x+10, tmp_y, newWidth / 10, height / 25);



    }



}