import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.String;


public class Rating extends JPanel {


    JTextField enterRating;
    Person rater;

    private GridBagLayout layout = new GridBagLayout();


    public Rating(Connect conn, Person rater, Person ratedUser, Sale sale, mainGUI frame) {

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


        c.ipady = 10;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = .1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = .3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(40,20,0,0);


        JLabel prompt = new JLabel("Enter rating (from 0 to 5)");
        add(prompt, c);
        c.gridy++;



        c.insets = new Insets(0,20,0,0);
        enterRating = new JTextField();
        enterRating.setPreferredSize(new Dimension(50,27));
        add(enterRating, c);
        c.gridx++;

        if(rater.getType().equals("V")){
            prompt.setVisible(false);
            enterRating.setVisible(false);


        }


        c.insets = new Insets(0,0,0,20);









        c.gridx = 0;
        c.ipady = 0;
        c.gridy++;
        c.insets = new Insets(20,20,0,20);





        JLabel title = new JLabel("Comment ");
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        add(title, c);

        Border b1;
        b1 = BorderFactory.createLineBorder(Color.black);

        c.gridy++;
        c.gridwidth = 5;
        c.gridheight = 1;
        c.weighty = 3;
        JTextArea reviewText = new JTextArea();
        reviewText.setPreferredSize(new Dimension(400, 300));
        reviewText.setBorder(b1);
        reviewText.setLineWrap(true);
        add(reviewText, c);


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

            boolean correct = false;
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            double ratingValue = -1;


            if(!enterRating.getText().isEmpty() && !rater.getType().equals("V")) {
                correct = checkRating();
            }
            else if(rater.getType().equals("V")){
                correct = true;
            }
            else {
                JOptionPane.showMessageDialog(dialog, "Enter rating");
            }


            if(correct){


                if(!rater.getType().equals("V")) {
                    ratingValue = Double.parseDouble(enterRating.getText());

                    System.out.println("rating value" + ratingValue);
                    ratedUser.updateRating(ratingValue);
                    ratedUser.incrementN_ratings();
                    rater.updateRatingsGiven(ratingValue);
                    rater.incrementN_ratingsGiven();

                    System.out.println("average rating given" + rater.getRatingsGiven());



                    conn.update(rater);
                    conn.update(ratedUser);

                }


                if(reviewText.getText().isEmpty() && !rater.getType().equals("V")){
                    reviewText.setText("no comment");
                }
                else if(reviewText.getText().isEmpty() && rater.getType().equals("V")){
                    JOptionPane.showMessageDialog(dialog, "Enter your comment.");


                    frame.switchToReview(conn, rater, ratedUser, sale);


                }


                String comment = reviewText.getText();


                System.out.println(rater.getN_lowestRatings());
                conn.addRating(rater, ratedUser, sale, comment);
                conn.update(rater);
                conn.update(ratedUser);
                System.out.println(rater.getN_lowestRatings());
                if(ratedUser.getN_ratings() >= 3 && ratedUser.getRating() < 2) {

                    ratedUser.setActive(false);
                    ratedUser.incrementTimes();
                    conn.update(rater);
                    conn.log(ratedUser.getUsername() + " has been suspended due to having rating less than 2.");


                }

                if(rater.getN_ratingsGiven() >= 3 && rater.getRatingsGiven() < 2 && !rater.getType().equals("V")) {


                    rater.setActive(false);
                    rater.incrementTimes();
                    ratedUser.updateRating(ratingValue);
                    conn.update(rater);
                    conn.update(ratedUser);
                    conn.log(rater.getUsername() + " has been suspended due to giving low ratings.\"");

                }

                else if(rater.getN_ratingsGiven() >= 3 && rater.getRatingsGiven() > 4) {

                    rater.setActive(false);
                    rater.incrementTimes();
                    conn.update(rater);
                    conn.update(ratedUser);
                    conn.log(rater.getUsername() + " has been suspended due to giving high ratings.\"");

                }


                if(!rater.getType().equals("V") && ratingValue == 5){
                    rater.incrementBadTransactions();
                    conn.update(rater);

                    System.out.println("number of bad transactions" + rater.get_BadTransactions());
                    if(rater.get_BadTransactions() == 5) {
                        rater.addFlag();
                        System.out.println("Number of flags " + rater.getFlags());
                        conn.update(rater);
                        conn.update(ratedUser);
                        conn.log(rater.getUsername() + " has been flagged due to giving 5 highest ratings in the last 5 transactions.\"");
                    }
                }

                if(!rater.getType().equals("V") && ratingValue == 0){

                    System.out.println("gave lowest rating");
                    rater.incrementLowestRatings();
                    conn.update(rater);
                    System.out.println(rater.getN_lowestRatings());

                    if(rater.getN_lowestRatings() == 3 && rater.getComplaintsGiven() == 3) {

                        rater.addFlag();
                        conn.update(rater);
                        conn.log(rater.getUsername() + " has been flagged due to giving 3 lowest ratings and 3 complaints.\"");


                    }




                }

                else if(!rater.getType().equals("V") && 0 < ratingValue && ratingValue < 5){
                    rater.reset_BadTransactions();
                    conn.update(rater);
                }

            }


            if(!rater.getType().equals("V")) {
                JOptionPane.showMessageDialog(dialog, "Rating added.");
            }

            else {
            JOptionPane.showMessageDialog(dialog, "Comment added.");
            }
            frame.switchToBuying();
        });



        cancelBtn.addActionListener(e -> {





            frame.switchToBuying();





        });


    }


    public boolean checkRating(){

        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);



        if(Double.parseDouble(enterRating.getText()) < 0 || Double.parseDouble(enterRating.getText()) > 5) {
            JOptionPane.showMessageDialog(dialog, "Invalid rating.");
            return false;
        }
        return true;
    }
}
