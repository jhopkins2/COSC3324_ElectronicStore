package electronicsStore;

import java.io.IOException;

/**
* TAMUCC electronics store program implements an application that allows users to 
* register and purchase electronics, as well as allows registered administrators to track the system.
*
* @author  Jacob Hopkins, Jonie Moya, Hailey Chapman
* @version 1.0
* @since   2018-10/21
*/

//Electronic class utilizes the classes within the package to run the program
public class ElectronicsStore
{
    public static void main(String[] args) throws IOException //main function
    {
        System.out.println("Welcome to the TAMUCC electronics store."); //prompts user with output text
        
        ElectronicsStoreSystem ES_System = new ElectronicsStoreSystem(); //creates new  ElectronicsStoreSystem object ES_System
        
        ES_System.AuthenticateUser(); //calls ES_System function
        
    }
}