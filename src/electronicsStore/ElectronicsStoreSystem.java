package electronicsStore;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * ElectronicsStoreSystem class uses corresponding classes to create the interface of the
 * Electronics store.
 * 
 */

public class ElectronicsStoreSystem 
{
    private final AdminList adminList = new AdminList(); //new adminList from adminList class
    private final MemberList memberList = new MemberList();//new memberList from memberList class
    private Catalog itemCatalog = new Catalog();
    
    private Member currentUser = null; //declares new private variable and initializes it as empty
    private Admin currentAdmin = null;
    
    //Class constructor
    public ElectronicsStoreSystem() throws IOException { 
        this.memberList.populate();
        this.adminList.populate();
        this.itemCatalog.populate();
    }
    
   //Authorizes users input for program 
    
    void AuthenticateUser() throws IOException {
        String user; //user input variable
        String password;

        boolean cont = true; //sets condition for switch statement

        int option;
        while(cont)
        {
            System.out.println("\n- Main Menu -");
            System.out.println("-----------------------------------------------------");
            option = FileUtility.integerInput("1. Show Catalog\n2. Login\n3. Register\n4. Exit\n"); //user input options for case switch 
            System.out.println("-----------------------------------------------------");
            switch(option)
            {
                case 1://Show catalog
                    itemCatalog.showCategories(); //calls itemCatalog object function and displays item selection
                    break;
                case 2: //Log-in
                    user = FileUtility.stringInput("\n* Enter Member Username: "); //user input for username
                    password = FileUtility.stringInput("\n* Enter Member Password: ");//input for password

                    this.currentUser = this.memberList.validateMember(user, password); //calls function to validate user credentials as present in member data files
                    if (this.currentUser != null)//if present 
                    {
                        this.MemberInterface(); //redirects to different interface after verified
                    }
                    else  //checks if user is an admin
                    {
                        this.currentAdmin = this.adminList.validateAdmin(user, password); //calls function to validate user as present in admin data files
                        
                        if (this.currentAdmin != null) //if present
                        {
                            this.AdminInterface(); //redirects to admin interface
                        }
                        else //if neither user or admin
                        {
                            System.out.println("\n\n**You have entered the wrong username or password.**\n"); //prompts user to re-enter correct log-in information
                        }
                    }
                    
                    break;
                case 3://Register new member
                    Member newMember = new Member(); //creates new object from Member Class to register new user
                    if (newMember.register(this.memberList))
                    {
                        int memid = this.memberList.length() + 1; 
                        newMember.setMemID(memid);

                        this.memberList.add(newMember); //adds new member to memberList
                        this.memberList.updateMembersFile(); //adds member to member data file

                        System.out.format("\n\nSuccess! %s %s %s, you are now registered!"
                                        + "\nTo login, use:\nUsername: %s\nPassword: %s\n\n\n", 
                                        newMember.getFirst(), newMember.getInitial(), newMember.getLast(), 
                                        newMember.getUsername(), newMember.getPassword());
                        
                        break;
                    }
                    else
                    { 
                        break;
                    }
                case 4: //exit
                    cont = false;
                    break; 
                default: //if user entered invalid input 
                    System.out.println("**Enter a valid value! (1-4)**");
                    break;
            }
        }  
    }
    /**
     * Administration Interface
     * Allows admins to add new products, read member purchase history, and add new admins to the system
     */
    void AdminInterface() throws IOException {
        System.out.println("\n\n\n*--Welcome Administrator.--*");
        boolean cont = true;
        int option;
        
        while(cont)
        {
            option = FileUtility.integerInput("\n- Admin Menu -\n1. Read Purchase History\n2. Show Members\n3. Show Admins\n4. Add Admin\n5. Log out\n\n---Please select an option---\n"); //admins menu options
            
            switch(option) 
            {
                case 1: //calls function to read a users purchase history
                    this.readPurchaseHistory();
                    break;
                case 2://calls function to show all currently registered member users
                    this.memberList.showMembers(); 
                    break;
                case 3://calls function to show all currently registered admin users
                    this.adminList.showAdmins(); 
                    break;
                case 4://to register a new admin to the system
                    Admin newAdmin = new Admin(); //create new admin object
                    if (newAdmin.register(this.adminList)) //call to register the new admin to the array
                    {                    
                        this.adminList.add(newAdmin); //adds the new admin to the adminList
                        this.adminList.UpdateAdminFile();//updates the data file of admins

                        System.out.format("\n\nSuccess! You have registered an Admin! \nTo login, use:\nUsername: %s\nPassword: %s\n", newAdmin.getUsername(), newAdmin.getPassword());
                        
                        break;
                    }
                    else
                    {
                        break;
                    }
                case 5://for logging out
                    cont = false;
                    break;  
                default://if entered an invalid input
                    System.out.println("**Enter a valid value! (1-5)**");
                    break;
            }
        }
    }
    /**
     * Member Interface displayed for successful login of user. Here they should be able to view the store items, 
     * view their user information, purchase items, upgrade their membership, view membership points, and exit the system. 
     */
    void MemberInterface() throws IOException {
        System.out.format("Hello, %s %s %s. Welcome to Another Amazon Wannabe electronics store.\n", this.currentUser.getFirst(), this.currentUser.getInitial(), this.currentUser.getLast());
        boolean cont = true;
        
        int option;

        while(cont)
        {
            option = FileUtility.integerInput("\n- Member Menu -\n1. View Catalog\n2. View Membership Info\n3. Buy Item\n4. Upgrade Membership\n5. View Points\n6. Log out\n\n---Please select an option---\n");//User menu options
            switch(option)
            {
                case 1: //to view the catalog of items offered
                    this.itemCatalog.showCategories(); //calls to show the present categories
                    break;
                case 2: //to view the current users information
                    this.currentUser.showMemberInfo(); //calls to show member information
                    break;
                case 3://to buy an item from the store
                    System.out.println("---Item Category Selection---");
                    this.itemCatalog.showCategories(false);
                    System.out.println("\n");
                    Category inputCategory = this.itemCatalog.inputCategory(); //creates new Category object
                    
                    System.out.format("%-40s  %-15s  %-15s  %-10s  %-10s\n", "Item Name: ", "Price: ", "Geek Points: ", "Quanitity: ", "Availability: "); 
                    inputCategory.showItems();
                    
                    Item inputItem = this.itemCatalog.inputItem(inputCategory);
                    
                    this.PurchaseItem(inputCategory, inputItem);
                    
                    break;
                case 4://to upgrade a membership to premium status: "Geek"
                    if (this.currentUser.getPremium()) //checks the users information to see if already a premium member
                    {
                        System.out.println("You are already a Geek member.");
                    }
                    else//if not a "geek"
                    {
                        String input = FileUtility.stringInput("Are you sure you want to upgrade to 'Geek'?: Y/N"); //asks user if they wish to upgrade
                        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))//validates user input and checks for yes response
                        {
                            System.out.println("You have been upgraded to 'Geek' membership, congratulations!"); 
                            this.currentUser.setPremium(true); //sets premium value to true 
                            this.memberList.updateMembersFile();//updates users profile in data file
                        }
                        else//if not a yes response
                        {
                            System.out.println("You have declined the 'Geek' membership offer.");
                        }
                    }
                    
                    break;
                case 5://to check points gained by user
                    if (this.currentUser.getPremium())//first validates that theyre premium members
                    {
                        System.out.format("You currently have %d Geek points.\n", this.currentUser.getPoints()); //displays amount of points in data file for user
                    }
                    else//if not a premium member
                    {
                        System.out.println("Upgrade to Geek membership to earn points!");
                    }
                    
                    break;
                case 6://to exit the Member interface
                    cont = false;
                    break; 
                default://if user enters invalid input
                    System.out.println("**Enter a valid value! (1-6)**");
                    break;
            }
        }
    }
    /**
     *Purchasing items from the store uses PurchaseItem function to produce: the receipt for user, updates user premium points,
     *updates purchase history, and updates available items
     */
    void PurchaseItem(Category currentCategory, Item currentItem) throws IOException
    {
        double tax, total, subtotal = 0.0;
        double SALES_TAX = 0.0825;
        String line = "------------------------"; 
        int points = 0;
        if (currentCategory != null)
        {
            if (currentItem != null)
            {
                if (currentItem.getAvailable() == Availability.AVAILABLE) //determines if current item is available
                {
                    System.out.println();
                    System.out.println();
                    int quantity = FileUtility.integerInput("Enter the quantity you want to purchase: ");
                    if(quantity >= 1 && quantity <= currentItem.getQuantity()){
                        subtotal = currentItem.getPrice() * (double)quantity;
                        tax = currentItem.getPrice() * SALES_TAX * (double)quantity;
                        total = subtotal + tax; 
                        if (this.currentUser.getPremium()) //determines if current user is a premium member
                        {
                            points = (int)(total); //calculate premium points
                            this.currentUser.addPoints(points); //add points to user's accumulated points
                        
                            this.memberList.updateMembersFile();
                        }
                        for(int i = 0; i < quantity; i++){
                            currentItem.decrementQuantity();
                        }                        
                        currentCategory.updateDatabase();
                    
                        this.writePurchaseHistory(currentItem, quantity); //calls function to add item purchase to history
                    
                        System.out.println(line);
                        System.out.println("Your Purchase Receipt");
                        System.out.println();
                        System.out.printf("%dx %-40s  $%-6.2f  %n", quantity, currentItem.getName(), 
                            currentItem.getPrice());
                        System.out.println();                    
                        System.out.println(line);
                        System.out.printf("Subtotal: %-25s $%.2f%n", " ", subtotal);
                        System.out.printf("Sale Tax: %-25s $%.2f%n", " ", tax);
                        System.out.printf("Total: %-28s $%.2f%n", " ", total);
                        System.out.println();
                        if(this.currentUser.getPremium()){
                            System.out.printf("Geek points earned: %-14s  %d($%d)%n", " ", points,
                                (int)total);
                        }
                        System.out.println(line);
                        System.out.println();
                        System.out.println();
                    }
                    else{
                        System.out.format("You entered an invalid amount: %d\n", quantity);
                    }
                }
                else//if selected item is not in stock
                {
                    System.out.format("The item %s is not available currently, sorry.\n", currentItem.getName());
                }
            }
            else//if an invalid item input
            {
                System.out.println("Not an item in the category.\n");
            }
        }
        else//if an invalid category input
        {
            System.out.println("Not a category.\n");
        }
    }
    /**
     * Sends new purchase information to the purchase history file
     */
    public void writePurchaseHistory(Item purchaseItem, int q){
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy KK:mm:ss a").format(new Date());
        String history = String.format("%s,%s,[%s] %s - Item: %s Price: %.2f Quantity: %d\n", this.currentUser.getMemID(), this.currentUser.getUsername(), timeStamp,
                this.currentUser.getUsername(), purchaseItem.getName(), purchaseItem.getPrice(), q);
        FileUtility.writeContent(".\\txtFiles\\PurchaseHistory.txt", history, true);
    }
    /**
     * Retrieves purchase history of a selected member and displays information. 
     * Only used by admins. 
     */
    public void readPurchaseHistory() throws IOException {
        ArrayList<String> history = FileUtility.retrieveContent(".\\txtFiles\\PurchaseHistory.txt"); //Retrieves the data from the purchase history file
        
        this.memberList.showMembers(); //calls to show all current members
        
        String input = FileUtility.stringInput("Enter UserID or Username to view their purchase history: "); //input a user to display information
        
        System.out.println("\n\n- Purchase History - ");
        
        for(String Current: history) //creates loop to print all of present users history
        {
            String[] arr = Current.split(",");
            
            if (arr.length < 3)
            {
                continue;
            }
            
            String uid = arr[0];
            String username = arr[1];
            String historyText = arr[2];
            
            if (!uid.equalsIgnoreCase(input) && !username.equalsIgnoreCase(input))
            {
                continue;
            }
            
            System.out.println(historyText);
        }
        System.out.println("--------------------");
    } 
}