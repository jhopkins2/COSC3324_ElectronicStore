package electronicsStore;

import java.io.IOException;

/**
* The TAMUCC Electronics Store program allows users login, register, and purchase various electronics, 
* as well as allows registered administrators to view member details such as order history and register new admins to the system.
*
* @author  Jacob Hopkins, Jonie Moya, Hailey Chapman
* @version 1.0
* @since   2018-10/21
*/

//ElectronicStore class implements the corresponding classes to run the store program.
public class ElectronicsStore
{
    public static void main(String[] args) throws IOException 
    {
        System.out.println("Welcome to Another Amazon Wannabe electronics store."); //welcome message to user
        
        ElectronicsStoreSystem ES_System = new ElectronicsStoreSystem(); //new ElectronicsStoreSystem object named ES_System
        
        ES_System.AuthenticateUser(); //calls ElectronicStoreSystem function
        
    }
}