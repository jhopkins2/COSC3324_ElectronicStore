package electronicsStore;

/**
 * Member class is utilized to access, modify, display
 * and instantiate Member objects
 */

public class Member extends User //declaration of member class which inherits from User class
{   
    private String fName;
    private String lName; //private variable meant to store user's last name
    private char mInitial;
    private String email;
    private boolean premium;
    private int points;
    private int memId;
    private int paypal;
    
    //default constructor
    public Member(){
        super(null, null);
        this.setFirst(null);
        this.setLast(null);
        this.setInitial('0');
        this.setEmail(null);
        this.setPremium(false);
        this.setPoints(0);
        this.setMemID(0);
        this.setPayPal(0);
    }
    //used by method updateMembers in Memberlist class parameterized constructor
    public Member(String user, String password, String first, String last, char initial, 
            String email, boolean premium, int points, int MemID, int paypal){
        super(user, password);
        this.setFirst(first);
        this.setLast(last);
        this.setInitial(initial);
        this.setEmail(email);
        this.setPremium(premium);
        this.setPoints(points);
        this.setMemID(MemID);
        this.setPayPal(paypal);
    }
    
    public void setFirst(String fName){ //mutator function to assign variable stores users first name.
        this.fName = fName;
    }
    public void setLast(String lName){
        this.lName = lName;
    }
    public void setInitial(char mInitial){
        this.mInitial = mInitial;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPremium(boolean premium){ //mutator function assigns users premium status
        this.premium = premium;
    }
    public void setPoints(int points){
        this.points = points;
    }
    public void setMemID(int memId){ //mutator function assigns member ID with parameter
        this.memId = memId;
    }
    public void setPayPal(int num){
        this.paypal = num;
    }
    
    public String getFirst(){
        return fName;
    }
    public String getLast(){ //accessor function returns users last name
     return lName;
    }
    public char getInitial(){
        return mInitial;
    }
    public String getEmail(){
        return email;
    }
    public boolean getPremium(){
        return premium;
    }
    public int getPoints(){ //accessor function returns user's points
        return points;
    }
    public int getMemID(){
        return memId;
    }
    public int getPaypal(){
        return paypal;
    }
    
    /**
     * function takes users current purchasing points accumulates and stores them.
     * @param points 
     */
    public void addPoints(int points){
        int currentPoints = this.getPoints();
        this.setPoints(currentPoints + points);
    }
    public boolean register(MemberList list){ //function declaration to register new users
        String username = FileUtility.stringInput("* Enter Username: "); //inputs new username from user
        if(!list.validateMemberUsername(username))
        {
            super.setUsername(username); //assigns user input value to variable

            String password = FileUtility.stringInput("* Enter Password: ");
            super.setPassword(password);

            String firstName = FileUtility.stringInput("* Enter First Name: ");
            this.setFirst(firstName);

            String middleInitial = FileUtility.stringInput("* Enter Middle Initial: ");
            this.setInitial(middleInitial.charAt(0));

            String lastName = FileUtility.stringInput("* Enter Last Name: "); //inputs user's last name
            this.setLast(lastName); //parameterizes user input lastName into mutator function

            String emailAddress = FileUtility.stringInput("* Enter E-Mail: ");
            this.setEmail(emailAddress);

            int paypalNumber = FileUtility.integerInput("* Enter Paypal Account Number: ");
            this.setPayPal(paypalNumber);
            
            return true;
        }
        
        else
        {
            System.out.println("\n\n**That username is already taken!**\n");
            return false;
        }
    }
    //function to output information on bookstore members
    public void showMemberInfo(){
        
        System.out.println("---Member Information---");
        System.out.format(" %-20s %s %s %s\n %-20s %d\n %-20s %s\n %-20s %d\n %-20s %b\n","User:", this.getFirst(),  this.getInitial(),  this.getLast(), "Member ID:", this.getMemID(), "E-mail Address:", this.getEmail(), "PayPal Account #:",this.getPaypal(), "Premium Member:", this.getPremium());
    }
}