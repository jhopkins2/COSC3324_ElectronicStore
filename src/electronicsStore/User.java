package electronicsStore;

/**
 * User class is used for validating and storing the username and password of an admin or member
 */

public class User
{
    private String username;
    private String password; 
    
    //class Constructor 
    
    public User(String user, String password){
        this.setUsername(user);
        this.setPassword(password);
    }
    
    public final void setUsername(String username){ //sets current username
        this.username = username;
    }
    public final void setPassword(String password){  //sets current password
        this.password = password;
    }
    
    public String getUsername(){ //Retrieves variable
        return username;
    }
    
    public String getPassword(){ 
        return password;
    }
    
   //functions used to check the validity of users input to the data files
    public boolean validateUser(String user, String password){
        return (this.getUsername().equalsIgnoreCase(user) && this.getPassword().equals(password));
    }
    public boolean validateUsername(String username){
        return (this.getUsername().equalsIgnoreCase(username));
    }
}