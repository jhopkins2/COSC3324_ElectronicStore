package electronicsStore;

/**
 * The User class is to retrieve username and password input data from 
 * the user and store mutator, accessor and validation functions
 * 
 */

public class User
{
    private String username;
    private String password; 
    
    /**
     * functions as parameterized constructor for User Class
     * @param user
     * @param password 
     */
    public User(String user, String password){
        this.setUsername(user);
        this.setPassword(password);
    }
    
    public final void setUsername(String username){ //mutator function to set username variable
        this.username = username;
    }
    public final void setPassword(String password){  //mutator function to set password variable
        this.password = password;
    }
    
    public String getUsername(){ //accessor function to return username
        return username;
    }
    
    public String getPassword(){ //accessor function to return password
        return password;
    }
    
    /**
     * This function validates the users credentials
     * @param user
     * @param password
     * @return true or false
     */
    public boolean validateUser(String user, String password){
        return (this.getUsername().equalsIgnoreCase(user) && this.getPassword().equals(password));
    }
    public boolean validateUsername(String username){
        return (this.getUsername().equalsIgnoreCase(username));
    }
}