import java.util.Calendar;

public class Person {
    private String fName;
    private String lName;
    private String dob; // Format will be: MM/dd/yyyy
    private int age;
    private String username;
    private String type;
    private String password;
    private String email;
    private String address;
    private String state;
    private String city;
    private int zipcode;
    private double amount;
    private double rating;
    private double ratingsGiven;
    private int flags = 0;
    private boolean active;
    private boolean vip;
    private int c_number;
    private String c_name;
    private int csv;
    private String c_expdate;
    private int n_ratings;
    private int n_ratingsGiven;
    private int times;
    private int s_transactions;
    private int complaintsGiven = 0; //INITIALLY BOTH 0
    private int complaintsReceived = 0;
    private int n_lowestRatings;
    private int n_highestRatings;
    private int bad_transactions;

    // parameters: fName, lName, dob, username, type, password, email, adress, state, city, zipcode.
    public Person(String first, String last, String db, String user,
                  String t, String pw, String mail, String add, String cit, String st, int zip){
        setfName(first);
        setlName(last);
        setDOB(db);
        setAge(db);
        setUsername(user);
        setType(t);
        setPassword(pw);
        setEmail(mail);
        setAddress(add);
        setState(st);
        setCity(cit);
        setZip(zip);
    }

    public void incrementLowestRatings() { this.n_lowestRatings++;}

    public void set_BadTransactions(int num){ bad_transactions = num; }

    public void incrementBadTransactions(){ bad_transactions++; }

    public void incrementN_ratings(){this.n_ratings++;}

    public void incrementN_ratingsGiven(){ this.n_ratingsGiven++; }

    public void reset_BadTransactions(){ bad_transactions = 0; }

    public void setN_RatingsGiven(int num) { this.n_ratingsGiven = num;}

    public void setN_lowestRatings(int rating){this.n_lowestRatings = rating;}

    public void reset_LowestRatings(){this.n_lowestRatings = 0;}

    public int getN_highestRatings(){ return this.n_highestRatings; }

    public boolean isActive(){
        return this.active;
    }

    public void addMoney(double add){
        this.amount+= add;
    }
    // -- Get methods --

    public int get_BadTransactions(){ return this.bad_transactions; }

    public String getfName(){
        return this.fName;
    }

    public String getlName(){
        return this.lName;
    }

    public String getDOB(){
        return this.dob;
    }

    public String getUsername(){
        return this.username;
    }

    public int getAge(){
        return this.age;
    }

    public String getType(){
        return this.type;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPlace(){
        return this.address;
    }

    public double getAmount(){
        return this.amount;
    }

    public double getRating(){
        return this.rating;
    }

    public double getRatingsGiven() { return this.ratingsGiven; }

    public double getFlags(){
        return this.flags;
    }

    public String getAddress(){
        return this.address;
    }

    public String getState(){
        return this.state;
    }

    public String getCity(){
        return this.city;
    }

    public int getZip(){
        return this.zipcode;
    }

    public boolean isVIP(){
        return this.vip;
    }


    // -- Set methods --

    public void setfName(String first){
        this.fName = first;
    }

    public void setlName(String last){
        this.lName = last;
    }

    public void setDOB(String db){
        this.dob = db;
    }

    public void setAge(String db){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);

        // Extracts the month/day/year from the string and stores them separately.
        String dbM = Character.toString(db.charAt(0)) + Character.toString(db.charAt(1));
        String dbD = Character.toString(db.charAt(3)) + Character.toString(db.charAt(4));
        String dbY = Character.toString(db.charAt(6)) + Character.toString(db.charAt(7)) + Character.toString(db.charAt(8)) + Character.toString(db.charAt(9));

        // Converts strings into integer.
        int dbMonth = Integer.parseInt(dbM);
        int dbDay = Integer.parseInt(dbD);
        int dbYear = Integer.parseInt(dbY);

        // Calculates age.
        this.age = year - dbYear;
        if(month < dbMonth){
            --this.age;
        }
        if((month == dbMonth) && (day < dbDay)) {
            --this.age;
        }


    }

    public void setComplaintsGiven( int compl) { this.complaintsGiven = compl; }

    public void setComplaintsReceived( int compl) { this.complaintsReceived = compl; }

    public void incrementComplaintsReceived() { this.complaintsReceived++; }

    public void incrementComplaintsGiven() { this.complaintsGiven++; }

    public void setUsername(String user){
        this.username = user;
    }

    public void setType(String t){
        this.type = t;
    }

    public void setPassword(String pw){
        this.password = pw;
    }

    public void setEmail(String mail){
        this.email = mail;
    }

    public void setPlace(String loc){
        this.address = loc;
    }

    public void setAmount(double a){
        this.amount = a;
    }

    public void setVip(boolean status){
        this.vip = status;
    }

    public void setAddress(String ad){
        this.address = ad;
    }

    public void setState(String st){
        this.state = st;
    }

    public void setCity(String cit){
        this.city = cit;
    }

    public void setZip(int zip){
        this.zipcode = zip;
    }

    public void addFlag(){
        ++this.flags;
    }

    public void setActive(boolean state){
        this.active = state;
    }

    public void setRating(double rate){
        this.rating = rate;
    }

    public void setRatingsGiven( double rating) { this.ratingsGiven = rating; }

    public void setFlags(int f){
        this.flags = f;
    }

    public void setVIP(boolean v){
        this.vip = v;
    }

    public int getC_number() {
        return c_number;
    }

    public void setC_number(int c_number) {
        this.c_number = c_number;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public int getCsv() {
        return csv;
    }

    public void setCsv(int csv) {
        this.csv = csv;
    }

    public String getC_expdate() {
        return c_expdate;
    }

    public void setC_expdate(String c_expdate) {
        this.c_expdate = c_expdate;
    }

    public int getN_ratings() {
        return n_ratings;
    }

    public void setN_ratings(int n_ratings) {
        this.n_ratings = n_ratings;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getS_transactions() {
        return s_transactions;
    }

    public void setS_transactions(int s_transactions) {
        this.s_transactions = s_transactions;
    }

    public void incrementS_transactions(){ this.s_transactions++;}

    public int getComplaintsGiven() { return complaintsGiven; }

    public int getComplaintsReceived() { return complaintsReceived; }

    public int getN_lowestRatings(){return n_lowestRatings;}

    public int getN_ratingsGiven() { return n_ratingsGiven; }


    public void incrementTimes() {
        times++;
    }

    public void updateRating(double rate){

        rating = ((rating) * (getN_ratings()) + rate) / (getN_ratings() + 1);

    }

    public void updateRatingsGiven(double rate){

        ratingsGiven = ((ratingsGiven) * (getN_ratingsGiven()) + rate) / (getN_ratingsGiven() + 1);

    }



}