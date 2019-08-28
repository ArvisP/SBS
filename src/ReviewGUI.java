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
import java.util.ArrayList;

import static com.sun.glass.ui.Cursor.setVisible;

public class ReviewGUI extends JPanel {


    private ArrayList<String> reviewers;
    private ArrayList<String> comments;
    private JLabel[] usernames;
    private JLabel[] reviews;
    private JButton back;


    //private GridBagLayout layout = new GridBagLayout();


    public ReviewGUI(Connect conn, Sale sale, int width, int height, mainGUI frame) {

        setLayout(null);
        setVisible(true);

        int tmp_x;
        int tmp_y;
        int tmp_width;
        int newWidth = width *13;
        Font font = new Font("Serif", Font.BOLD, 25);
        tmp_x = (int) (width * 0.03);
        tmp_y = (int) (height * 0.02);

        reviewers = new ArrayList<>(20);
        comments = new ArrayList<>(20);

        conn.getReview(sale.getSaleID(), reviewers, comments);
        usernames = new JLabel[comments.size()];
        reviews = new JLabel[comments.size()];

        back = new JButton("Back");
        back.setBounds(tmp_x, tmp_y, width / 6, height / 25);
        add(back);

        for(int i = 0; i < comments.size(); i++){

            usernames[i] = new JLabel(reviewers.get(i));
            reviews[i] = new JLabel(comments.get(i));
           // reviews[i].setPreferredSize(new Dimension(500, 100));

        }

        tmp_y += height / 15;
        for(int i = 0; i < comments.size(); i++) {



            usernames[i].setBounds(tmp_x, tmp_y, newWidth / 7, height / 20);
            add(usernames[i]);
            tmp_y += height / 25;

            reviews[i].setBounds(tmp_x, tmp_y, newWidth / 14, height / 10);
            add(reviews[i]);
            tmp_y += height / 10;




        }

        back.addActionListener(e -> {



            frame.switchToBuying();




        });


    }


}