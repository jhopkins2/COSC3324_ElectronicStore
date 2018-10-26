package electronicsStore;

/**
 * Admin class inherits from User class and provides electronics store 
 * administrators the ability to register
 * 
 */

public class Admin extends User
{   
    public Admin(){ //default class constructor
        super(null, null);
    }
    public Admin(String user, String password){ //parameterized constructor
        super(user, password);
    }
    
    public boolean register(AdminList list){ //function that allows admin's to register
        String username = FileUtility.stringInput("* Enter Username: "); //prompts admin to enter user name
        if(!list.validateAdminUsername(username))
        {
            super.setUsername(username); //calls mutator function to set user name input

            String password = FileUtility.stringInput("* Enter Password: ");
            super.setPassword(password);
            
            return true;
        }
        
        else
        {
            System.out.println("\n\n**That username is already taken!**\n");
            return false;
        }
    }
}