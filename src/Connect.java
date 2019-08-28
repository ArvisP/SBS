import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


public class Connect {
    private static HSSFWorkbook xlWBook;
    private static HSSFSheet xlSheet;
    private static HSSFRow xlRow;
    private static String fileName = "SBS_db.xls";

    public Connect() {
        refresh();
    }

    public void refresh(){
        try {
            FileInputStream xlFile = new FileInputStream(fileName);
            xlWBook = new HSSFWorkbook(xlFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Cannot find the Database.");
        }
    }

    public Person login(String username, String password) {
        Person user;
        String rowUser, rowPass;
        int i, rows;
        boolean valid = false;
        xlSheet = xlWBook.getSheet("Person");
        rows = xlSheet.getPhysicalNumberOfRows();

        for (i = 1; i < rows; i++) {
            xlRow = xlSheet.getRow(i);
            rowUser = xlRow.getCell(0).getStringCellValue();
            rowPass = xlRow.getCell(6).getStringCellValue();

            if (username.equals(rowUser) && password.equals(rowPass)) {
                valid = true;
                break;
            }
        }
        if(valid) user = getPerson(username);
        else user = new Person("a", "a", "00/00/0000", "a", "a", "a", "a", "a", "a", "a", 0);
        return user;
    }

    public void update(Person user) {
        xlSheet = xlWBook.getSheet("Person");
        String username = new String();
        String password = new String();
        String fname = new String();
        String lname = new String();
        String dob = new String();
        String email = new String();
        String address = new String();
        String city = new String();
        String state = new String();
        int zipcode;
        int i, rows;
        rows = xlSheet.getPhysicalNumberOfRows();
        for (i = 1; i < rows; i++) {
            xlRow = xlSheet.getRow(i);
            username = xlRow.getCell(0).getStringCellValue();
            // Finds the matching username to be updated.
            if (username.equals(user.getUsername())) {
                password = xlRow.getCell(6).getStringCellValue();
                fname = xlRow.getCell(1).getStringCellValue();
                lname = xlRow.getCell(2).getStringCellValue();
                dob = xlRow.getCell(3).getStringCellValue();
                email = xlRow.getCell(7).getStringCellValue();
                address = xlRow.getCell(8).getStringCellValue();
                city = xlRow.getCell(9).getStringCellValue();
                state = xlRow.getCell(10).getStringCellValue();
                zipcode = (int) (xlRow.getCell(11).getNumericCellValue());
                // Update the DB
                if (!user.getPassword().equals(password))
                    xlRow.getCell(6).setCellValue(user.getPassword());
                if (!user.getfName().equals(fname))
                    xlRow.getCell(1).setCellValue(user.getfName());
                if (!user.getlName().equals(lname))
                    xlRow.getCell(2).setCellValue(user.getlName());
                if (!user.getDOB().equals(dob)) {
                    xlRow.getCell(3).setCellValue(user.getDOB());
                    xlRow.getCell(4).setCellValue(user.getAge());
                }
                if (!user.getEmail().equals(email))
                    xlRow.getCell(7).setCellValue(user.getEmail());
                if (!user.getAddress().equals(address))
                    xlRow.getCell(8).setCellValue(user.getAddress());
                if (!user.getCity().equals(city))
                    xlRow.getCell(9).setCellValue(user.getCity());
                // System.out.println(xlRow.getCell(9).getStringCellValue());
                if (!user.getState().equals(state))
                    xlRow.getCell(10).setCellValue(user.getState());
                if (user.getZip() != zipcode)
                    xlRow.getCell(11).setCellValue(user.getZip());
                if (user.getFlags() != xlRow.getCell(14).getNumericCellValue())
                    xlRow.getCell(14).setCellValue(user.getFlags());
                if (user.getC_number() != xlRow.getCell(17).getNumericCellValue())
                    xlRow.getCell(17).setCellValue(user.getC_number());
                if (!user.getC_name().equals(xlRow.getCell(18).getStringCellValue()))
                    xlRow.getCell(18).setCellValue(user.getC_name());
                if (user.getCsv() != xlRow.getCell(19).getNumericCellValue())
                    xlRow.getCell(19).setCellValue(user.getCsv());
                if (user.getC_expdate().equals(xlRow.getCell(20).getStringCellValue()))
                    xlRow.getCell(20).setCellValue(user.getC_expdate());
                if (user.getN_ratings() != xlRow.getCell(21).getNumericCellValue())
                    xlRow.getCell(21).setCellValue(user.getN_ratings());
                if (user.getN_lowestRatings() != xlRow.getCell(22).getNumericCellValue())
                    xlRow.getCell(22).setCellValue(user.getN_lowestRatings());
               //System.out.println(xlRow.getCell(22).getNumericCellValue());
                if (user.getTimes() != xlRow.getCell(23).getNumericCellValue())
                    xlRow.getCell(23).setCellValue(user.getTimes());
                if (user.getS_transactions() != xlRow.getCell(25).getNumericCellValue())
                    xlRow.getCell(25).setCellValue(user.getS_transactions());
                if (user.get_BadTransactions() != xlRow.getCell(26).getNumericCellValue())
                    xlRow.getCell(26).setCellValue(user.get_BadTransactions());
                if (user.getComplaintsGiven() != xlRow.getCell(27).getNumericCellValue())
                    xlRow.getCell(27).setCellValue(user.getComplaintsGiven());
                if (user.getComplaintsReceived() != xlRow.getCell(28).getNumericCellValue())
                    xlRow.getCell(28).setCellValue(user.getComplaintsReceived());
                if (user.getRatingsGiven() != xlRow.getCell(29).getNumericCellValue())
                    xlRow.getCell(29).setCellValue(user.getRatingsGiven());
                if (user.getN_ratingsGiven() != xlRow.getCell(30).getNumericCellValue())
                    xlRow.getCell(30).setCellValue(user.getN_ratingsGiven());



                output();
            }
        }

    }


    public void addSaleRow(Person user, Sale sale) {

        int count;

        xlSheet = xlWBook.getSheet("Sale");
        count = xlSheet.getPhysicalNumberOfRows();  //index of the next empty row where one can insert a new item
        xlRow = xlSheet.createRow(count);
        int saleID = count;

        sale.setSaleID(saleID);


        //update Sale sheet

        Cell cell_0 = xlRow.createCell(0);
        Cell cell_1 = xlRow.createCell(1);
        Cell cell_2 = xlRow.createCell(2);
        Cell cell_3 = xlRow.createCell(3);
        Cell cell_4 = xlRow.createCell(4);
        Cell cell_5 = xlRow.createCell(5);
        Cell cell_6 = xlRow.createCell(6);
        Cell cell_7 = xlRow.createCell(7);
        Cell cell_8 = xlRow.createCell(8);
        Cell cell_9 = xlRow.createCell(9);
        Cell cell_10 = xlRow.createCell(10);
        Cell cell_11 = xlRow.createCell(11);
        Cell cell_12 = xlRow.createCell(12);
        Cell cell_13 = xlRow.createCell(13);
        Cell cell_14 = xlRow.createCell(14);
        Cell cell_15 = xlRow.createCell(15);
        Cell cell_16 = xlRow.createCell(16);
        Cell cell_17 = xlRow.createCell(17);
        Cell cell_18 = xlRow.createCell(18);
        Cell cell_19 = xlRow.createCell(19);
        Cell cell_20 = xlRow.createCell(20);
        Cell cell_21 = xlRow.createCell(21);
        Cell cell_22 = xlRow.createCell(22);


        cell_0.setCellValue(saleID);
        cell_1.setCellValue(user.getUsername());
        cell_2.setCellValue(sale.getTitle());
        cell_3.setCellValue(sale.getPrice());
        cell_4.setCellValue(sale.getCategory());
        cell_5.setCellValue(sale.getBuyer());
        cell_6.setCellValue(sale.getExpDate());
        cell_7.setCellValue(sale.isSold());
        cell_8.setCellValue(sale.getDOP());
        cell_9.setCellValue(sale.getDuration());
        cell_10.setCellValue(sale.getDescription());
        cell_11.setCellValue(sale.getPlace());
        cell_12.setCellValue(sale.getPrice());
        cell_13.setCellValue(sale.getHighBid());
        cell_14.setCellValue(sale.getBidder());
        cell_15.setCellValue(sale.getListDate());
        cell_16.setCellValue(sale.isAuction());
        // cell_17.setCellValue(keywords);----------------NEED TO PASS KEYWORDS!!!!
        cell_18.setCellValue("n/a"); //add picture
        cell_19.setCellValue(sale.getBrand());
        cell_20.setCellValue(sale.getModel());
        cell_21.setCellValue(sale.getColor());
        cell_22.setCellValue("false");

        output();


    }


    public boolean verify(String username){
        xlSheet = xlWBook.getSheet("Person");
        int rows = xlSheet.getPhysicalNumberOfRows();
        int i;
        for(i=1;i<rows;++i){
            xlSheet = xlWBook.getSheet("Person");
            xlRow = xlSheet.getRow(i);
            if(username.equals(xlRow.getCell(0).getStringCellValue())){
                if(xlRow.getCell(15).getStringCellValue().equals("true")){
                    return true;
                }
                break;
            }
        }
        return false;
    }



    public boolean verifyUser(String username){
        xlSheet = xlWBook.getSheet("Person");
        int rows = xlSheet.getPhysicalNumberOfRows();
        int i;
        for(i=0;i<rows;++i){
            xlRow = xlSheet.getRow(i);
            if(username.equals(xlRow.getCell(0).getStringCellValue()))
                return true;
        }
        return false;
    }


    public void register(Person user) {
        xlSheet = xlWBook.getSheet("Person");
        int rows = xlSheet.getPhysicalNumberOfRows();
        xlRow = xlSheet.createRow(rows);
        Cell usern = xlRow.createCell(0);
        Cell fname = xlRow.createCell(1);
        Cell lname = xlRow.createCell(2);
        Cell dob = xlRow.createCell(3);
        Cell age = xlRow.createCell(4);
        Cell type = xlRow.createCell(5);
        Cell pw = xlRow.createCell(6);
        Cell email = xlRow.createCell(7);
        Cell address = xlRow.createCell(8);
        Cell city = xlRow.createCell(9);
        Cell state = xlRow.createCell(10);
        Cell zip = xlRow.createCell(11);
        Cell amount = xlRow.createCell(12);
        Cell rating = xlRow.createCell(13);
        Cell flags = xlRow.createCell(14);
        Cell active = xlRow.createCell(15);
        Cell VIP = xlRow.createCell(16);
        Cell c_number = xlRow.createCell(17);
        Cell c_name = xlRow.createCell(18);
        Cell csv = xlRow.createCell(19);
        Cell c_expdate = xlRow.createCell(20);
        Cell n_ratings = xlRow.createCell(21);
        Cell lowest_rating = xlRow.createCell(22);
        Cell times = xlRow.createCell(23);
        Cell lowest_rater = xlRow.createCell(24);
        Cell s_transaction = xlRow.createCell(25);
        Cell highest_rating = xlRow.createCell(26);
        Cell n_complaint = xlRow.createCell(27);
        Cell n_received = xlRow.createCell(28);
        Cell avg = xlRow.createCell(29);
        Cell ratings_given = xlRow.createCell(30);
        // ----------------------------------------
        usern.setCellValue(user.getUsername());
        fname.setCellValue(user.getfName());
        lname.setCellValue(user.getlName());
        dob.setCellValue(user.getDOB());
        age.setCellValue(user.getAge());
        type.setCellValue(user.getType());
        pw.setCellValue(user.getPassword());
        email.setCellValue(user.getEmail());
        address.setCellValue(user.getAddress());
        city.setCellValue(user.getCity());
        state.setCellValue(user.getState());
        zip.setCellValue(user.getZip());
        amount.setCellValue(0);
        rating.setCellValue(0);
        flags.setCellValue(0);
        active.setCellValue("false");
        VIP.setCellValue("false");
        c_number.setCellValue(0);
        c_name.setCellValue("n/a");
        csv.setCellValue(0);
        c_expdate.setCellValue("n/a");
        n_ratings.setCellValue(0);
        lowest_rating.setCellValue(0);
        times.setCellValue(0);
        lowest_rater.setCellValue("n/a");
        s_transaction.setCellValue(0);
        highest_rating.setCellValue(0);
        n_complaint.setCellValue(0);
        n_received.setCellValue(0);
        avg.setCellValue(0);
        ratings_given.setCellValue(0);
        output();
    }

    public void output(){
        try {
            FileOutputStream output_file = new FileOutputStream(fileName);
            xlWBook.write(output_file);
            output_file.close();
        } catch(IOException e){
            System.out.println("Database not found.");
        }
    }

    public void logout() {
        try {
            xlWBook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int rsAmount(String query){
        String[] QueryWords = query.split("\\s+|,");
        String current_query;
        String current_title;
        String current_keyword;
        Cell title, keyword;
        int i, j, k;
        boolean sold;
        int result = 0;
        boolean found = false;
        xlSheet = xlWBook.getSheet("Sale");
        int rows = xlSheet.getPhysicalNumberOfRows();
        for (i = 1; i < rows; i++){
            xlSheet = xlWBook.getSheet("Sale");
            xlRow = xlSheet.getRow(i);
            sold = xlRow.getCell(7).getBooleanCellValue();
            if(sold == false){
                title = xlRow.getCell(2);
                keyword = xlRow.getCell(17);
                String[] TitleWords = title.getStringCellValue().split("\\s+|,");
                String[] KeyWords = keyword.getStringCellValue().split(",");
                for(j = 0; j<QueryWords.length; ++j){
                    current_query = QueryWords[j].toLowerCase();
                    if(valid(current_query)){
                        for (k = 0; k < TitleWords.length; ++k){
                            current_title = TitleWords[k].toLowerCase();
                            if(current_title.equals(current_query)){
                                ++result;
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            for (k = 0; k < KeyWords.length; ++k){
                                current_keyword = KeyWords[k].toLowerCase();
                                if(current_keyword.equals(current_query)){
                                    ++result;
                                    break;
                                }
                            }
                        }
                        found = false;
                    }
                }
            }
        }
        return result;
    }

    public Sale[] search(String query, int size){
        Person seller;
        Sale []result = new Sale[size];
        String[] QueryWords = query.split("\\s+|,");
        String current_query;
        String current_title;
        String current_keyword;
        Cell title, keyword;
        int i, j, k;
        boolean sold;
        int index = 0;
        boolean found = false;
        xlSheet = xlWBook.getSheet("Sale");
        int rows = xlSheet.getPhysicalNumberOfRows();
        for (i = 1; i < rows; i++){
            xlSheet = xlWBook.getSheet("Sale");
            xlRow = xlSheet.getRow(i);
            sold = xlRow.getCell(7).getBooleanCellValue();
            if(sold == false){
                title = xlRow.getCell(2);
                keyword = xlRow.getCell(17);
                String[] TitleWords = title.getStringCellValue().split("\\s+|,");
                String[] KeyWords = keyword.getStringCellValue().split(",");
                for(j = 0; j<QueryWords.length; ++j){
                    current_query = QueryWords[j].toLowerCase();
                    if(valid(current_query)){
                        for (k = 0; k < TitleWords.length; ++k){
                            current_title = TitleWords[k].toLowerCase();
                            if(current_title.equals(current_query)){
                                result[index] = getSale(i);
                                ++index;
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            for (k = 0; k < KeyWords.length; ++k){
                                current_keyword = KeyWords[k].toLowerCase();
                                if(current_keyword.equals(current_query)){
                                    result[index] = getSale(i);
                                    ++index;
                                    break;
                                }
                            }
                        }
                        found = false;
                    }
                }
            }
        }

        return result;
    }

    // Will determine if the current word being examined is valid.
    public boolean valid(String w){
        if(w.equals("") | w.equals("of") | w.equals("from") | w.equals("to") | w.equals("his") | w.equals("her") |
                w.equals("the") | w.equals("it") | w.equals("sale") | w.equals("a") | w.equals("for") | w.equals("and"))
            return false;
        else
            return true;
    }



    public Sale[] ini_rs(){
        Sale rs[] = new Sale[40];
        Sale toAdd;
        xlSheet = xlWBook.getSheet("Sale");
        int rows = xlSheet.getPhysicalNumberOfRows();

        int i;
        boolean sold;
        Cell s;
        int index = 0;
        for(i = 1; i<rows; i++){
            xlSheet = xlWBook.getSheet("Sale");
            xlRow = xlSheet.getRow(i);
            s = xlRow.getCell(7);
            // Cell 7 = isSold
            sold = s.getBooleanCellValue();
            if(sold == false){
                toAdd = getSale(i);
                rs[index] = toAdd;
                ++index;
                if(index == 40)
                    break;
            }
        }

        return rs;
    }

    public Sale getSale(int row){
        Person seller;
        Sale result;
        Cell user, t, c, e, p, d, ID, s, img, b, m, col, desc, rev, high, buy, auct;
        int id;
        String username, title, category, exp, brand, model, color, description, buyer;
        boolean isSold, isAuction, reviewed;
        double price;
        int duration;
        xlSheet = xlWBook.getSheet("Sale");
        xlRow = xlSheet.getRow(row);
        ID = xlRow.getCell(0);
        user = xlRow.getCell(1);
        t = xlRow.getCell(2);
        p = xlRow.getCell(3);
        c = xlRow.getCell(4);
        buy = xlRow.getCell(5);
        e = xlRow.getCell(6);
        s = xlRow.getCell(7);
        d = xlRow.getCell(9);
        high = xlRow.getCell(13);
        img = xlRow.getCell(18);
        b = xlRow.getCell(19);
        m = xlRow.getCell(20);
        col = xlRow.getCell(21);
        desc = xlRow.getCell(10);
        rev = xlRow.getCell(22);
        auct = xlRow.getCell(16);
        // Cell 7 = isSold
        // Parameters: Seller's username, title, price, category, expiration, duration
        id = (int)(ID.getNumericCellValue());

        username = user.getStringCellValue();

        seller = getPerson(username);

        title = t.getStringCellValue();

        price = p.getNumericCellValue();

        category = c.getStringCellValue();

        exp = e.getStringCellValue();
        duration = (int)(d.getNumericCellValue());
        brand = b.getStringCellValue();
        model = m.getStringCellValue();
        color = col.getStringCellValue();
        description = desc.getStringCellValue();
        reviewed = rev.getBooleanCellValue();
        isSold = s.getBooleanCellValue();
        buyer = buy.getStringCellValue();
        isAuction = auct.getBooleanCellValue();
        result = new Sale(id, seller, title, description, price, category, exp, duration, brand, model, color);
        result.setHighBid(high.getNumericCellValue());
        result.setBuyer(buyer);
        result.setSold(isSold);
        result.setIsAuction(isAuction);
        result.setReviewed(reviewed);
        if(img.getStringCellValue().equals("n/a"))
            result.setImage("n/a");
        else
            result.setImage(img.getStringCellValue());

        return result;
    }

    public void bought(Sale sale, Person buyer) {
        int rowSale;
        int i, rows;
        int saleID = sale.getSaleID();
        System.out.println(saleID);
        xlSheet = xlWBook.getSheet("Sale");
        rows = xlSheet.getPhysicalNumberOfRows();


        for (i = 1; i < rows; i++) {
            xlRow = xlSheet.getRow(i);
            rowSale = (int) Math.round(xlRow.getCell(0).getNumericCellValue());
            System.out.println(rowSale);
            if (saleID == rowSale) {

                xlRow.getCell(7).setCellValue(true); //is sold
                xlRow.getCell(8).setCellValue(sale.getDOP()); //to set the date of purchase
                xlRow.getCell(5).setCellValue(sale.getBuyer()); //set buyer
                System.out.println(sale.getBuyer());

                output();
                return;

            }

        }

    }


    public void updateSale(Sale sale, Person buyer){
        int rowSale;
        int i, rows;
        int saleID = sale.getSaleID();

        xlSheet = xlWBook.getSheet("Sale");
        rows = xlSheet.getPhysicalNumberOfRows();


        for (i = 1; i < rows; i++) {
            xlRow = xlSheet.getRow(i);
            rowSale = (int) Math.round(xlRow.getCell(0).getNumericCellValue());
            if (saleID == rowSale) {


                xlRow.getCell(7).setCellValue(sale.isSold()); //is sold
                xlRow.getCell(8).setCellValue(sale.getDOP()); //to set the date of purchase
                xlRow.getCell(5).setCellValue(sale.getBuyer()); //

                if(!sale.getImage().equals(xlRow.getCell(18).getStringCellValue())){

                    xlRow.getCell(18).setCellValue(sale.getImage());


                }




                output();

                return; //return after info is updated
            }

        }


    }

    public void updateAuction(Sale sale, Person bidder){
        int rowSale;
        int i, rows;
        int saleID = sale.getSaleID();

        xlSheet = xlWBook.getSheet("Sale");
        rows = xlSheet.getPhysicalNumberOfRows();


        for (i = 1; i < rows; i++) {
            xlRow = xlSheet.getRow(i);
            rowSale = (int) Math.round(xlRow.getCell(0).getNumericCellValue());
            if (saleID == rowSale) {

                xlRow.getCell(3).setCellValue(sale.getPrice());
                xlRow.getCell(13).setCellValue(sale.getHighBid());
                xlRow.getCell(14).setCellValue(bidder.getUsername());

                output();

                return; //return after info is updated
            }

        }


    }

    public void addRating(Person reviewer, Person reviewed, Sale sale, String comment){
        int rows;
        xlSheet = xlWBook.getSheet("Review");
        rows = xlSheet.getPhysicalNumberOfRows();

        xlRow = xlSheet.createRow(rows);


        Cell cell_0 = xlRow.createCell(0);
        Cell cell_1 = xlRow.createCell(1);
        Cell cell_2 = xlRow.createCell(2);
        Cell cell_3 = xlRow.createCell(3);
        Cell cell_4 = xlRow.createCell(4);
        Cell cell_5 = xlRow.createCell(5);
        Cell cell_6 = xlRow.createCell(6);



        cell_0.setCellValue(reviewed.getUsername());
        cell_1.setCellValue(reviewer.getUsername());
        cell_2.setCellValue(sale.getSaleID());
        cell_3.setCellValue(reviewed.getRating()); //JUST USER'S Rating
        cell_4.setCellValue(comment);
        cell_5.setCellValue(0);
        cell_6.setCellValue(String.valueOf(false));




    }

    public void addComplaint(Person reviewer, Person reviewed, Sale sale, String complaintText){
        int rows;
        xlSheet = xlWBook.getSheet("Review");
        rows = xlSheet.getPhysicalNumberOfRows();

        xlRow = xlSheet.createRow(rows);


        Cell cell_0 = xlRow.createCell(0);
        Cell cell_1 = xlRow.createCell(1);
        Cell cell_2 = xlRow.createCell(2);
        Cell cell_3 = xlRow.createCell(3);
        Cell cell_4 = xlRow.createCell(4);
        Cell cell_5 = xlRow.createCell(5);
        Cell cell_6 = xlRow.createCell(6);
        
        cell_0.setCellValue(reviewed.getUsername());
        cell_1.setCellValue(reviewer.getUsername());
        cell_2.setCellValue(sale.getSaleID());
        cell_3.setCellValue(0);
        cell_4.setCellValue(complaintText);
        cell_5.setCellValue(1);
        cell_6.setCellValue("false");



    }

    public void updatePaymentMethod(Person user){

        String rowUser;
        int i, rows;
        String username = user.getUsername();

        xlSheet = xlWBook.getSheet("Person");
        rows = xlSheet.getPhysicalNumberOfRows();


        for (i = 1; i < rows; i++) {
            xlRow = xlSheet.getRow(i);
            rowUser = xlRow.getCell(0).getStringCellValue();
            if (username.equals(rowUser)) {

                xlRow.getCell(17).setCellValue(user.getC_number());
                xlRow.getCell(18).setCellValue(user.getC_name());
                xlRow.getCell(19).setCellValue(user.getCsv());
                // xlRow.getCell(20).setCellValue(user.getCardAddress());
                // xlRow.getCell(21).setCellValue(user.getCardCity());
                // xlRow.getCell(22).setCellValue(user.getCardState());
                // xlRow.getCell(23).setCellValue(user.getCardZip());
                xlRow.getCell(24).setCellValue(user.getC_expdate());

                output();


            }

        }

    }
    
    public int countPrevious(Person user){
    	int amount = 0;
    	xlSheet = xlWBook.getSheet("Sale");
    	int rows = xlSheet.getPhysicalNumberOfRows();
    	int i;
    	Cell sold, buyer;
    	for(i = 1; i<rows; ++i){
    		xlSheet = xlWBook.getSheet("Sale");
    		xlRow = xlSheet.getRow(i);
    		sold = xlRow.getCell(7);
    		buyer = xlRow.getCell(5);
    		if(sold.getBooleanCellValue() && user.getUsername().equals(buyer.getStringCellValue()))
    			++amount;
    	}
    	return amount;
    }
    
    public Sale[] previousPurchases(Person user, int amount){
    	xlSheet = xlWBook.getSheet("Sale");
    	int rows = xlSheet.getPhysicalNumberOfRows();
    	Sale[] previous = new Sale[amount];
    	int index = 0;
    	int i;
    	Cell sold, buyer;
    	for(i = 1; i<rows; ++i){
    		xlSheet = xlWBook.getSheet("Sale");
    		xlRow = xlSheet.getRow(i);
    		sold = xlRow.getCell(7);
    		buyer = xlRow.getCell(5);
    		if(sold.getBooleanCellValue() && user.getUsername().equals(buyer.getStringCellValue())){
    			previous[index] = getSale(i);
    			++index;
    		}
    		if (index == amount)
    			break;
    	}
    		
    		
    	return previous;
    }

    public void getReview(int saleID, ArrayList reviewers, ArrayList comments) {

        int rows, i, saleRow;
        xlSheet = xlWBook.getSheet("Review");
        rows = xlSheet.getPhysicalNumberOfRows();

        for (i = 1; i < rows; i++) {
            xlSheet = xlWBook.getSheet("Review");
            xlRow = xlSheet.getRow(i);
            saleRow = (int) xlRow.getCell(2).getNumericCellValue();

            if (saleID == saleRow && xlRow.getCell(5).getNumericCellValue() == 0 &&
                    !xlRow.getCell(4).getStringCellValue().equals("no comment")) {

                reviewers.add(xlRow.getCell(1).getStringCellValue());
                comments.add(xlRow.getCell(4).getStringCellValue());


            }


        }

        return;
    }

    public void setBillingInfo(Person user) {

        String rowUser;
        int i, rows;
        String username = user.getUsername();

        xlSheet = xlWBook.getSheet("Person");
        rows = xlSheet.getPhysicalNumberOfRows();


        for (i = 1; i < rows; i++) {
            xlRow = xlSheet.getRow(i);
            rowUser = xlRow.getCell(0).getStringCellValue();
            if (username.equals(rowUser)) {

                user.setC_number((int)xlRow.getCell(17).getNumericCellValue());
                user.setC_name(xlRow.getCell(18).getStringCellValue());
                user.setCsv((int)xlRow.getCell(19).getNumericCellValue());
                // user.setCardAddress(xlRow.getCell(20).getStringCellValue());
                // user.setCardCity(xlRow.getCell(21).getStringCellValue());
                // user.setCardState(xlRow.getCell(22).getStringCellValue());
                //  user.setCardZip(xlRow.getCell(23).getStringCellValue());
                user.setC_expdate(xlRow.getCell(24).getStringCellValue());


            }


        }


    }
    
    public boolean didRate(int saleID, Person rater){

        int i, rows, sale;
        String reviewer;
        xlSheet = xlWBook.getSheet("Review");
        rows = xlSheet.getPhysicalNumberOfRows();

        for (i = 1; i < rows; i++) {
            xlRow = xlSheet.getRow(i);
            reviewer = xlRow.getCell(1).getStringCellValue();
            sale = (int) xlRow.getCell(2).getNumericCellValue();

            if (reviewer.equals(rater.getUsername()) && sale == saleID) {
                return true;
            }


        }

        return false;
    }

    public Person getPerson(String username){
        Person user;
        String rowUser, fname, lname, dob, address, password;
        int flags, zipcode, c_number, n_ratings, times, csv;
        double rating, amount;
        String type, email, city, state, VIP, active, c_name, c_expdate;
        int i, rows;
        xlSheet = xlWBook.getSheet("Person");
        rows = xlSheet.getPhysicalNumberOfRows();

        for (i = 1; i < rows; i++) {
            xlSheet = xlWBook.getSheet("Person");
            xlRow = xlSheet.getRow(i);
            rowUser = xlRow.getCell(0).getStringCellValue();
            if (username.equals(rowUser)) {
                password = xlRow.getCell(6).getStringCellValue();
                fname = xlRow.getCell(1).getStringCellValue();
                lname = xlRow.getCell(2).getStringCellValue();
                dob = xlRow.getCell(3).getStringCellValue();
                type = xlRow.getCell(5).getStringCellValue();
                flags = (int) (xlRow.getCell(14).getNumericCellValue());
                rating = xlRow.getCell(13).getNumericCellValue();
                amount = xlRow.getCell(12).getNumericCellValue();
                VIP = xlRow.getCell(16).getStringCellValue();
                c_number = (int)(xlRow.getCell(17).getNumericCellValue());
                c_name = xlRow.getCell(18).getStringCellValue();
                csv = (int)(xlRow.getCell(19).getNumericCellValue());
                c_expdate = xlRow.getCell(20).getStringCellValue();
                n_ratings = (int)(xlRow.getCell(21).getNumericCellValue());
                times = (int)(xlRow.getCell(23).getNumericCellValue());
                active = xlRow.getCell(15).getStringCellValue();
                email = xlRow.getCell(7).getStringCellValue();
                address = xlRow.getCell(8).getStringCellValue();
                city = xlRow.getCell(9).getStringCellValue();
                state = xlRow.getCell(10).getStringCellValue();
                zipcode = (int) (xlRow.getCell(11).getNumericCellValue());
                //	fName, lName, dob, username, type, password, email,
                user = new Person(fname, lname, dob, username, type, password, email, address, city, state, zipcode);
                // Set remaining member variables
                user.setAmount(amount);
                user.setRating(rating);
                user.setFlags(flags);
                user.setC_number(c_number);
                user.setC_name(c_name);
                user.setCsv(csv);
                user.setC_expdate(c_expdate);
                user.setN_ratings(n_ratings);
                user.setTimes(times);
                user.setN_lowestRatings((int)(xlRow.getCell(22).getNumericCellValue()));
                user.setS_transactions((int)(xlRow.getCell(25).getNumericCellValue()));
                user.set_BadTransactions((int)(xlRow.getCell(26).getNumericCellValue()));
                user.setComplaintsGiven((int)(xlRow.getCell(27).getNumericCellValue()));
                user.setComplaintsReceived((int)(xlRow.getCell(28).getNumericCellValue()));
                user.setRatingsGiven(xlRow.getCell(29).getNumericCellValue());
                user.setN_RatingsGiven((int) xlRow.getCell(30).getNumericCellValue());
                if(VIP.equals("true"))
                    user.setVIP(true);
                else
                    user.setVIP(false);
                if(active.equals("true"))
                    user.setActive(true);
                else
                    user.setActive(false);
                return user;
            }
        }
        user = new Person("a", "a", "00/00/0000", "a", "a", "a", "a", "a", "a", "a", 0);
        return user;
    }

    public void updateAmount(Person user){
        int i;
        String username;
        xlSheet = xlWBook.getSheet("Person");
        int rows = xlSheet.getPhysicalNumberOfRows();
        for(i = 0; i<rows; i++){
            xlRow = xlSheet.getRow(i);
            username = xlRow.getCell(0).getStringCellValue();
            if(username.equals(user.getUsername())){
                xlRow.getCell(12).setCellValue(user.getAmount());
                if(user.getAmount() > 5000)
                    xlRow.getCell(16).setCellValue("true");
                if((user.getAmount() < 5000) && (user.getN_ratings()<=5))
                    xlRow.getCell(16).setCellValue("false");
            }
            output();
        }
    }

    public int listingamount(Person user){
        int amount = 0;
        xlSheet = xlWBook.getSheet("Sale");
        int rows = xlSheet.getPhysicalNumberOfRows();
        int i;
        Cell seller, act;
        boolean active;
        for(i = 1; i<rows; i++){
            xlSheet = xlWBook.getSheet("Sale");
            xlRow = xlSheet.getRow(i);
            seller = xlRow.getCell(1);
            act = xlRow.getCell(7);
            active = act.getBooleanCellValue();
            if(user.getUsername().equals(seller.getStringCellValue()) && active == false){
                ++amount;
            }
        }
        return amount;
    }

    public Sale[] viewListings(Person user, int amount){
        Sale []listings = new Sale[amount];
        xlSheet = xlWBook.getSheet("Sale");
        int rows = xlSheet.getPhysicalNumberOfRows();
        int i;
        int index = 0;
        Cell seller, act;
        boolean active;
        for(i = 1; i<rows; i++){
            xlSheet = xlWBook.getSheet("Sale");
            xlRow = xlSheet.getRow(i);
            seller = xlRow.getCell(1);
            act = xlRow.getCell(7);
            active = act.getBooleanCellValue();
            if(user.getUsername().equals(seller.getStringCellValue()) && active == false){
                listings[index] = getSale(i);
                ++index;
            }
        }

        return listings;
    }

    public void setReviewed(int saleid){
        xlSheet = xlWBook.getSheet("Person");
        int rows = xlSheet.getPhysicalNumberOfRows();
        int i;
        Cell r;
        for(i = 1; i<rows; i++){
            xlSheet = xlWBook.getSheet("Sale");
            xlRow = xlSheet.getRow(i);
            r = xlRow.getCell(22);
            if((int)(xlRow.getCell(0).getNumericCellValue()) == saleid){
                r.setCellValue("true");
                break;
            }
        }
        output();
    }

    public Object[][] setLog(){
        xlSheet = xlWBook.getSheet("Log");
        int rows = xlSheet.getPhysicalNumberOfRows();
        Object[][] Data = new Object[rows][2];
        int index = 0;
        for(int i = rows-1; i > 0; i--){
            xlSheet = xlWBook.getSheet("Log");
            xlRow = xlSheet.getRow(i);
            Cell time = xlRow.getCell(0);
            Cell event = xlRow.getCell(1);
            Data[index][0] = time.getStringCellValue();
            Data[index][1] = event.getStringCellValue();
            ++index;
        }
        return Data;
    }

    public Object[][] userList(){
        xlSheet = xlWBook.getSheet("Person");
        int rows = xlSheet.getPhysicalNumberOfRows();
        // -------------------------------------------
        Object[][] users = new Object[rows][13];
        //-------------------------------------------
        Cell username, dob, age, type, email, amount, rating;
        Cell flags, l_rater, active, VIP, n_ratings, n_suspended;

        for (int i = 1; i < rows; i++){
            xlSheet = xlWBook.getSheet("Person");
            xlRow = xlSheet.getRow(i);
            username = xlRow.getCell(0);
            dob = xlRow.getCell(3);
            age = xlRow.getCell(4);
            type = xlRow.getCell(5);
            email = xlRow.getCell(7);
            amount = xlRow.getCell(12);
            rating = xlRow.getCell(13);
            flags = xlRow.getCell(14);
            active = xlRow.getCell(15);
            VIP = xlRow.getCell(16);
            n_ratings = xlRow.getCell(21);
            n_suspended = xlRow.getCell(23);
            l_rater = xlRow.getCell(24);
            users[i-1][0]= username.getStringCellValue();
            users[i-1][1]= dob.getStringCellValue();
            users[i-1][2]= (int)(age.getNumericCellValue());
            users[i-1][3]= type.getStringCellValue();
            users[i-1][4]= email.getStringCellValue();
            users[i-1][5]= "$"+Double.toString(amount.getNumericCellValue());
            users[i-1][6]= rating.getNumericCellValue();
            users[i-1][7]= (int)(flags.getNumericCellValue());
            users[i-1][8]= active.getStringCellValue();
            users[i-1][9]= VIP.getStringCellValue();
            users[i-1][10]= (int)(n_ratings.getNumericCellValue());
            users[i-1][11]= (int)(n_suspended.getNumericCellValue());
            users[i-1][12]= l_rater.getStringCellValue();

        }
        return users;
    }

    public void log(String e){
        DateFormat df = new SimpleDateFormat("MM/dd/yy 'at' HH:mm:ss");
        Date now = new Date();
        xlSheet = xlWBook.getSheet("Log");
        int rows = xlSheet.getPhysicalNumberOfRows();
        xlRow = xlSheet.createRow(rows);
        Cell time = xlRow.createCell(0);
        Cell event = xlRow.createCell(1);
        time.setCellValue((String)(df.format(now)));
        event.setCellValue(e);
        output();
    }

    public void SUTask(String username, String e){
        xlSheet = xlWBook.getSheet("SUTasks");
        int rows = xlSheet.getPhysicalNumberOfRows();
        xlRow = xlSheet.createRow(rows);
        System.out.println(rows);
        Cell User = xlRow.createCell(0);
        Cell Action = xlRow.createCell(1);
        Cell Reviewer = xlRow.createCell(2);
        Cell Reviewed = xlRow.createCell(3);
        User.setCellValue(username);
        Action.setCellValue(e);
        Reviewer.setCellValue("n/a");
        Reviewed.setCellValue("false");
        output();
    }

    public String[] displayTasks(){
        String[] tasks;
        xlSheet = xlWBook.getSheet("SUTasks");
        int rows = xlSheet.getPhysicalNumberOfRows();
        tasks = new String[rows];
        Cell User;
        Cell Action;
        Cell Reviewer;
        Cell Reviewed;
        int index = 0;
        for(int i = 1; i<rows; i++){
            xlSheet = xlWBook.getSheet("SUTasks");
            xlRow = xlSheet.getRow(i);
            User = xlRow.getCell(0);
            Action = xlRow.getCell(1);
            Reviewer = xlRow.getCell(2);
            Reviewed = xlRow.getCell(3);
            if(Reviewed.getStringCellValue().equals("false")){
                tasks[index] = User.getStringCellValue()+" | "+Action.getStringCellValue();
                index++;
            }
        }


        // ----------------------------------------------------------------------
        for(int i = 1; i<rows; i++){
            xlSheet = xlWBook.getSheet("SUTasks");
            xlRow = xlSheet.getRow(i);
            User = xlRow.getCell(0);
            Action = xlRow.getCell(1);
            Reviewer = xlRow.getCell(2);
            Reviewed = xlRow.getCell(3);
            if(Reviewed.getStringCellValue().equals("true")){
                tasks[index] = User.getStringCellValue()+" | "+Action.getStringCellValue()+
                        " -- REVIEWED BY: "+Reviewer.getStringCellValue();
                index++;
            }
        }

        return tasks;
    }



    public String[] displayComplaints(){
        xlSheet = xlWBook.getSheet("Review");
        int rows = xlSheet.getPhysicalNumberOfRows();
        int index = 0;
        Cell recipient, reviewer, saleid, rating, comment;
        for(int i = 1; i<rows; i++){
            xlSheet = xlWBook.getSheet("Review");
            xlRow = xlSheet.getRow(i);
            if((int)(xlRow.getCell(5).getNumericCellValue()) == 1 &&
                    xlRow.getCell(6).getStringCellValue().equals("false")){
                index++;
            }
        }

        String[] complaints = new String[index];
        index = 0;
        for(int i = 1; i< rows; i++){
            xlSheet = xlWBook.getSheet("Review");
            xlRow = xlSheet.getRow(i);
            recipient = xlRow.getCell(0);
            reviewer = xlRow.getCell(1);
            saleid = xlRow.getCell(2);
            rating = xlRow.getCell(3);
            comment = xlRow.getCell(4);
            if((int)(xlRow.getCell(5).getNumericCellValue()) == 1 &&
                    xlRow.getCell(6).getStringCellValue().equals("false")){
                complaints[index] = "<html>Recipient: " + recipient.getStringCellValue()+" | "+
                        "Reviewer: " + reviewer.getStringCellValue() + " | " +
                        "Sale ID: " + (int) saleid.getNumericCellValue() + " | " +
                        "Rating: " + rating.getNumericCellValue() + "<br>" +
                        "Comment: " + comment.getStringCellValue() + "</html>";
                index++;
            }
        }

        return complaints;
    }

    public void suspend(int i){
        xlSheet = xlWBook.getSheet("Person");
        xlRow = xlSheet.getRow(i);
        Cell active = xlRow.getCell(15);
        Cell times = xlRow.getCell(23);
        active.setCellValue("false");
        int t = (int)times.getNumericCellValue()+1;
        times.setCellValue(t);
        output();
    }

    public void demote(int i){
    	 xlSheet = xlWBook.getSheet("Person");
         xlRow = xlSheet.getRow(i);
         Cell active = xlRow.getCell(15);
         Cell type = xlRow.getCell(5);
         active.setCellValue("true");
         if(type.getStringCellValue().equals("U"))
             type.setCellValue("V");
         if(type.getStringCellValue().equals("SU"))
             type.setCellValue("U");
         output();
    }
    
    public void reinstate(int i){
        xlSheet = xlWBook.getSheet("Person");
        xlRow = xlSheet.getRow(i);
        Cell active = xlRow.getCell(15);
        Cell lowest = xlRow.getCell(22);
        Cell bad = xlRow.getCell(26);
        Cell complaints = xlRow.getCell(27);
        active.setCellValue("true");
        lowest.setCellValue(0);
        complaints.setCellValue(0);

        output();
    }

    public void promote(int i){
        xlSheet = xlWBook.getSheet("Person");
        xlRow = xlSheet.getRow(i);
        Cell active = xlRow.getCell(15);
        Cell type = xlRow.getCell(5);
        active.setCellValue("true");
        if(type.getStringCellValue().equals("V"))
            type.setCellValue("U");
        if(type.getStringCellValue().equals("U"))
            type.setCellValue("SU");
        output();
    }

    public void SUReviewed(int i, String user, String which){
    	if(which.equals("Task")){
	        xlSheet = xlWBook.getSheet("SUTasks");
	        int rows = xlSheet.getPhysicalNumberOfRows();
	        Cell reviewer, reviewed, action;
	        int j = -1;
	        for (int counter = 1; counter<rows; ++counter){
	            xlSheet = xlWBook.getSheet("SUTasks");
	            xlRow = xlSheet.getRow(counter);
	            reviewer = xlRow.getCell(2);
	            if(reviewer.getStringCellValue().equals("n/a"))
	                ++j;
	            if(j==i && reviewer.getStringCellValue().equals("n/a")){
	                reviewed = xlRow.getCell(3);
	                reviewer.setCellValue(user);
	                reviewed.setCellValue("true");
	                action = xlRow.getCell(1);
	                log("SU "+user+" has reviewed: "+action.getStringCellValue());
	                break;
	
	            }
	
	        }
    	}
    	else{
    		 xlSheet = xlWBook.getSheet("Review");
 	        int rows = xlSheet.getPhysicalNumberOfRows();
 	        Cell isComp, overview;
 	        int j = -1;
 	        for (int counter = 1; counter<rows; ++counter){
 	            xlSheet = xlWBook.getSheet("Review");
 	            xlRow = xlSheet.getRow(counter);
 	            isComp = xlRow.getCell(5);
 	            overview = xlRow.getCell(6);
 	            System.out.println((int)(isComp.getNumericCellValue()));
 	            if((int)(isComp.getNumericCellValue()) == 1 && overview.getStringCellValue().equals("false"))
 	                ++j;
 	            if(j==i && (int)isComp.getNumericCellValue()==1 && overview.getStringCellValue().equals("false")){
 	                overview.setCellValue("true");
 	                log("SU "+user+" has reviewed the complaint for sale "+(int)xlRow.getCell(2).getNumericCellValue());
 	                break;
 	
 	            }
 	
 	        }
    	}
        output();

    }
}