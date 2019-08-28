import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sale {
    private int sale_id;
    private Person seller; // This will be the seller's username.
    private String title;
    private double price;
    private String category;
    private String buyer = ""; // This will be the buyer's username. When created the default value will be n/a
    private String expdate; // The format will be MM/dd/yyyy
    private boolean sold;
    private String dop; // Date of purchase.
    private int duration; // Duration will be given in days
    private String description; //ADDED
    private String place;
    private double startprice; // Will be the same as askprice if isAuction == 1
    private double highbid; // Highest bid made ADDED
    private String bidder;
    private String listdate;
    private boolean auction;
    private String[] keywords;
    private String image;
    private String brand;
    private String model;
    private String color;
    private boolean reviewed;

    // Parameters: Seller's username, title, price, category, expiration, duration, brand, model, color
    public Sale(int id, Person sel, String t, String desc, double p, String art, String exp, int d, String brand, String model, String color){
        setSaleID(id);
        setSeller(sel);
        setTitle(t);
        setPrice(p);
        setCategory(art);
        setExpDate(exp);
        setSold(false);
        setDuration(d);
        setListDate(todaysDate());
        setDescription(desc);
        setBrand(brand);
        setModel(model);
        setColor(color);
        setDOP("n/a"); // NOT PURCHASED YET
        setBuyer("n/a"); //INITIALLY A SALE IS NOT SOLD, SO -- NO BUYER
        setHighBid(0); //no bid -- 0
        setBidder("n/a"); //no bidder - n/a
        setDOP("n/a");

    }

    // Will verify if item is sold.
    public boolean isSold(){
        return this.sold;
    }

    // -- Get methods --
    public int getSaleID(){
        return this.sale_id;
    }

    public Person getSeller(){
        return this.seller;
    }

    public String getTitle(){
        return this.title;
    }

    public double getPrice(){
        return this.price;
    }

    public String getCategory(){
        return this.category;
    }

    public String getBuyer(){
        return this.buyer;
    }

    public String getExpDate(){
        return this.expdate;
    }

    public String getDOP(){
        return this.dop;
    }

    public int getDuration(){
        return this.duration;
    }

    public String getDescription(){
        return this.description;
    }

    public String getPlace(){
        return this.place;
    }

    public double getStartPrice(){
        return this.startprice;
    }

    public double getHighBid(){
        return this.highbid;
    }

    public String getBidder(){
        return this.bidder;
    }

    public String getListDate(){
        return this.listdate;
    }

    public boolean isAuction(){
        return this.auction;
    }

    public String getBrand() { return this.brand; }

    public String getColor() { return this.color; }

    public String getModel() { return this.model; }


    // -- Set methods --
    public void setSaleID(int id){
        this.sale_id = id;
    }

    public void setSeller(Person sel){
        this.seller = sel;
    }

    public void setTitle(String t){
        this.title = t;
    }

    public void setPrice(double p){
        this.price = p;
    }

    public void setCategory(String art){
        this.category = art;
    }

    public void setBuyer(String b){
        if (this.buyer.isEmpty() || this.buyer.equals("n/a"))
            this.buyer = b;

        else
            this.buyer = buyer + "," + b;
    }

    public void setSold(boolean state){
        this.sold = state;
    }

    public void setExpDate(String exp){
        this.expdate = exp;
    }

    public void setDOP(String date){
        this.dop = date;
    }

    public void setDuration(int days){
        this.duration = days;
    }

    public void setDescription(String d){
        this.description = d;
    }

    public void setPlace(){
        this.place = seller.getCity() + ", " + seller.getState();
    }

    public void setStartPrice(double price){
        this.startprice = price;
    }

    public void setHighBid(double bid){
        this.highbid = bid;
    }

    public void setBidder(String bidder){
        this.bidder = bidder;
    }

    public void setListDate(String ld){
        this.listdate = ld;
    }

    public void setIsAuction(boolean s){
        this.auction = s;
    }

    public void setBrand(String b) { this.brand = b; }

    public void setColor(String c) { this.color = c; }

    public void setModel(String m) { this.model = m; }

    public void setFinalBuyer(String username){ this.buyer = username; }


    //--------Additional methods-------------------------------------


    public String todaysDate() {

        LocalDate date = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");

        String dateStr = date.format(formatter);

        return dateStr;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isReviewed() {
        if (reviewed)
            return true;
        else return false;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }
}