package electronicsStore;

/**
 * The Member class is used to initialize variables of member information
 * register new members to the list by user input, add points to the profile
 * and display this information back to the user via "view membership info" option  
 */

public class Member extends User 
{   
    private String fName;
    private String lName; 
    private char mInitial;
    private String email;
    private int points;
    private int memId;
    private String phone;
    private boolean premium;
    
    //Default member constructor
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
    //Constructor used by memberList
    public Member(String user, String password, int MemID, String first, char initial, String last,  
            String email, String phone, int points,  boolean premium){
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
    
    //Set variable functions
    public void setFirst(String fName){
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
    public void setMemID(int memId){ 
        this.memId = memId;
    }
    public void setPhone(String phone) {
    	this.phone = phone;
    }
     public void setPremium(boolean premium){ 
        this.premium = premium;
    }
     
    //Get variable functions 
    public String getFirst(){
        return fName;
    }
    public String getLast(){ 
     return lName;
    }
    public char getInitial(){
        return mInitial;
    }
    public String getEmail(){
        return email;
    }
    public int getPoints(){ 
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
   
    
    //retrieves a users current point value and adds new points earned by purchases
    public void addPoints(int points){
        int currentPoints = this.getPoints();
        this.setPoints(currentPoints + points);
    }
    
    //Function used to register new members to the system and sends it to be written to the data file 
    public boolean register(MemberList list){ 
        
    	//user input of all member information
    	String username = FileUtility.stringInput("* Enter Username: ");
    	
        if(!list.validateMemberUsername(username))//checks that username is not already in the system
        {
            super.setUsername(username); 

            String password = FileUtility.stringInput("* Enter Password: ");
            super.setPassword(password);

            String firstName = FileUtility.stringInput("* Enter First Name: ");
            this.setFirst(firstName);

            String middleInitial = FileUtility.stringInput("* Enter Middle Initial: ");
            this.setInitial(middleInitial.charAt(0));

            String lastName = FileUtility.stringInput("* Enter Last Name: "); 
            this.setLast(lastName); 

            String emailAddress = FileUtility.stringInput("* Enter E-Mail: ");
            this.setEmail(emailAddress);
            
            String phone= FileUtility.stringInput("Enter Phone Number: ");
            this.setPhone(phone);

            return true;
        }
        
        else //if a username is already in the system
        {
            System.out.println("\n\n**That username is already taken!**\n");
            return false;
        }
    }
    
    //used to display user information back to the user
    public void showMemberInfo(){
        
        System.out.println("---Member Information---");
        System.out.println("User: "+ this.getFirst()+" "+  this.getInitial()+" "+  this.getLast()+ "\nMember ID: "+ this.getMemID()+ "\nE-mail Address: "+ this.getEmail()+"\nPhone Number: "+this.getPhone()+ "\nPremium Member: "+ this.getPremium());

    }
}