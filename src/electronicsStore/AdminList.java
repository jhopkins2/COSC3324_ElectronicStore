package electronicsStore;

import java.io.IOException;
import java.util.ArrayList;

/**
 * AdminList class has the ability to retrieve data from admin file, validate an admins login, 
 * register new admins to data file, and display admin information
 * 
 */

public class AdminList
{
    ArrayList<Admin> adminlist = new ArrayList(); //Makes new adminList array
    
    //Function to add new admin to list
    public void add(Admin a){
        this.adminlist.add(a);
    }
    
    
    //Call to check if a user and password are apart of the adminlist array
    public Admin validateAdmin(String user, String password){
        for(Admin current: this.adminlist)
        {
            if (current.validateUser(user, password))
            {
                return current;
            }
        }
        
        return null;
    }
    
    
    //Function updates data in the admin file to add new admin profile
    public void UpdateAdminFile(){
        String contents = "";
        for(Admin current: this.adminlist)
        {
            String data = String.format("%s,%s\n", current.getUsername(), current.getPassword());
            contents = contents + data;
        }
        
        FileUtility.writeContent(".\\txtFiles\\admins.txt", contents, false); //writes new data to admin file
    }
    
    
    //Calls admin data file and adds all present data to the adminList array
    public void populate() throws IOException {
        ArrayList<String> admins = FileUtility.retrieveContent(".\\txtFiles\\admins.txt"); //copies data from admin file
        
        for(String Current: admins)
        {
            String[] arr = Current.split(",");
            
            if (arr.length < 2)
            {
                continue;
            }
            
            String username = arr[0];
            String password = arr[1];
            
            Admin newAdmin = new Admin(username, password);
            
            this.add(newAdmin);//calls to add to list
        }
    }
    
    
     //Call to see if a username is already present in the adminlist
    public boolean validateAdminUsername(String user){
        for(Admin current: this.adminlist)
        {
            if (current.validateUsername(user))
            {
                return true;
            }
        }
        return false;
    }
    
    
    // Displays current admin information to the screen
    public void showAdmins(){
        System.out.println();
        System.out.println("List of Registered Administrators\n");
        //for all admins in list
        for(Admin Current: this.adminlist) 
        {
            //print information
            System.out.format("Username: %s Password: %s\n", Current.getUsername(), Current.getPassword()); 
        }
        System.out.println();
    }
}