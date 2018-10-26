package electronicsStore;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class functions to store, validate, add and print
 * electronics store administrator data
 * 
 */

public class AdminList
{
    ArrayList<Admin> adminlist = new ArrayList(); //creates new array object
    
    //parameterized function adds new administrator to adminList array
    public void add(Admin a){
        this.adminlist.add(a);
    }
    //function validates parameters for electronics store administrators
    public Admin validateAdmin(String user, String password){
        for(Admin current: this.adminlist)
        {
            if (current.validateUser(user, password)) //calls validateUser function to validate parameters
            {
                return current; //Admin variable
            }
        }
        
        return null;
    }
    //function updates "admins.txt" as the list of administrators
    public void UpdateAdminFile(){
        String contents = "";
        for(Admin current: this.adminlist)
        {
            String data = String.format("%s,%s\n", current.getUsername(), current.getPassword());
            contents = contents + data;
        }
        
        FileUtility.writeContent("admins.txt", contents, false); //updates content the text file
    }
    //function to print text file "admins.txt" into an array
    public void populate() throws IOException {
        ArrayList<String> admins = FileUtility.retrieveContent("admins.txt"); //copies text file data into new array object
        
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
            
            this.add(newAdmin);
        }
    }
     //Check if username exists within Admin List
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
    // this function shows electronics store administrator info
    public void showAdmins(){
        System.out.println();
        System.out.println("List of Registered Administrators\n");
        for(Admin Current: this.adminlist) //creates loop for each administrator
        {
            
            System.out.format("Username: %s Password: %s\n", Current.getUsername(), Current.getPassword()); //prints list of administrators.
        }
        System.out.println();
    }
}