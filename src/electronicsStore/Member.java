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
    private String phone;
    
    //default constructor
    public Member(){
        super(null, null);
        this.setFirst(null);
        this.setLast(null);
        this.setInitial('0');
        this.setEmail(null);
        this.setPoints(0);
        this.setMemID(0);
        this.setPhone(null);
        this.setPremium(false);
 
    }
    //used by method updateMembers in Memberlist class parameterized constructor
    public Member(String user, String password, String first, String last, char initial, 
            String email,String phone, int points, int MemID, boolean premium){
        super(user, password);
        this.setFirst(first);
        this.setLast(last);
        this.setInitial(initial);
        this.setEmail(email);
        this.setPoints(points);
        this.setMemID(MemID);
        this.setPhone(phone);
        this.setPremium(premium);
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
    public void setPoints(int points){
        this.points = points;
    }
    public void setMemID(int memId){ //mutator function assigns member ID with parameter
        this.memId = memId;
    }
    public void setPhone(String phone) {
    	this.phone = phone;
    }
     public void setPremium(boolean premium){ //mutator function assigns users premium status
        this.premium = premium;
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
    public int getPoints(){ //accessor function returns user's points
        return points;
    }
    public int getMemID(){
        return memId;
    }
    public String getPhone() {
    	return phone;
    }
    public boolean getPremium(){
        return premium;
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
            
            String phone= FileUtility.stringInput("Enter Phone Number: ");
            this.setPhone(phone);

            return true;
        }
        
        else
        {
            System.out.println("\n\n**That username is already taken!**\n");
            return false;
        }
    }
    //function to output information on electronic store members
    public void showMemberInfo(){
        
        System.out.println("---Member Information---");
        System.out.println("User: "+ this.getFirst()+" "+  this.getInitial()+" "+  this.getLast()+ "\nMember ID: "+ this.getMemID()+ "\nE-mail Address: "+ this.getEmail()+"\nPhone Number: "+this.getPhone()+ "\nPremium Member: "+ this.getPremium());
    }
}