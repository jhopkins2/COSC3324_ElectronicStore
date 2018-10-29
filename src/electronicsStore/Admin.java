package electronicsStore;

/**
 * The Admin class gives the ability to register new administrators to the system 
 */

public class Admin extends User
{  
	//default constructor
    public Admin(){ 
        super(null, null);
    }
    //Constructor with user and password
    public Admin(String user, String password){ 
        super(user, password);
    }
    
    
    //Register function creates new admin and writes it to data file
    public boolean register(AdminList list){ 
        String username = FileUtility.stringInput("* Enter Username: "); //user input of user and password
        
        
        if(!list.validateAdminUsername(username))//checks to see if already present in list
        {
            super.setUsername(username); 

            String password = FileUtility.stringInput("* Enter Password: ");
            super.setPassword(password);
            
            return true;
        }
        
        else//if already present in list
        {
            System.out.println("\n\n**That username is already taken!**\n");
            return false;
        }
    }
}