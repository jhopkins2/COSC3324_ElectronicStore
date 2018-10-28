package electronicsStore;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * ElectronicsStoreSystem class implements other classes in electronics store package
 * to create a user interface for the TAMUCC electronics store program
 * 
 */

public class ElectronicsStoreSystem 
{
    private final AdminList adminList = new AdminList(); //creates new object from the AdminList class.
    private final MemberList memberList = new MemberList();
    private Catalog itemCatalog = new Catalog();
    
    private Member currentUser = null; //declares new private variable and initializes it as empty
    private Admin currentAdmin = null;

    public ElectronicsStoreSystem() throws IOException { //Class constructor
        this.memberList.populate();
        this.adminList.populate();
        this.itemCatalog.populate();
    }
    
   /**
   * Inputs user data and authorizes them for the program.
   * @throws IOException
   */
    void AuthenticateUser() throws IOException {
        String user; //declares user input variable
        String password;

        boolean cont = true; //sets condition for switch statement

        int option;
        while(cont)
        {
            System.out.println("\n- Main Menu -");
            System.out.println("-----------------------------------------------------");
            option = FileUtility.integerInput("1. Show Catalog\n2. Login\n3. Register\n4. Exit\n"); //prompts and inputs data from user
            System.out.println("-----------------------------------------------------");
            switch(option)
            {
                case 1:
                    itemCatalog.showCategories(); //calls itemCatalog object function  displays item selection
                    break;
                case 2: 
                    user = FileUtility.stringInput("\n* Enter Member Username: "); //inputs credentials from user
                    password = FileUtility.stringInput("\n* Enter Member Password: ");

                    this.currentUser = this.memberList.validateMember(user, password); //calls function to validate user credentials
                    if (this.currentUser != null)
                    {
                        this.MemberInterface(); //redirects to different after user credentials have been validated
                    }
                    else
                    {
                        this.currentAdmin = this.adminList.validateAdmin(user, password); 
                        
                        if (this.currentAdmin != null) //validates that variable is not empty
                        {
                            this.AdminInterface(); //redirects to new function for administrator
                        }
                        else
                        {
                            System.out.println("\n\n**Those login credentials were not valid.**\n"); //prompts user to re-enter correct credentials
                        }
                    }
                    
                    break;
                case 3:
                    Member newMember = new Member(); //creates new object from Member Class to register new user
                    if (newMember.register(this.memberList))
                    {
                        int memid = this.memberList.length() + 1; 
                        newMember.setMemID(memid);

                        this.memberList.add(newMember);
                        this.memberList.updateMembers();

                        System.out.format("\n\nSuccess! %s %s %s, you are now registered!"
                                        + "\nTo login, use these credentials:\nUsername: %s\nPassword: %s\n\n\n", 
                                        newMember.getFirst(), newMember.getInitial(), newMember.getLast(), 
                                        newMember.getUsername(), newMember.getPassword());
                        
                        break;
                    }
                    else
                    { 
                        break;
                    }
                case 4:
                    cont = false;
                    break; 
                default:
                    System.out.println("**Enter a valid value! (1-4)**");
                    break;
            }
        }  
    }
    /**
     * Function design to implement an interface for an administrator user of bookstore
     * administrator user may add books, view member orders or add new administrator
     * @throws IOException 
     */
    void AdminInterface() throws IOException {
        System.out.println("\n\n\n*--Welcome Back Administrator.--*");
        boolean cont = true;
        int option;
        
        while(cont)
        {
            option = FileUtility.integerInput("\n- Admin Menu -\n1. Add New Book \n2. Read Purchase History\n3. Show Members\n4. Show Admins\n5. Add Admin\n6. Exit\n\n---Please select an option---\n"); //prompt user of switch statement options
            
            switch(option) 
            {
                case 1:
                    Item newItem = new Item(); //create new object from Item class
                    newItem.registerNewItem(); //call function from new object to add a new item
                    
                    this.itemCatalog.showCategories(false);
                    
                    Category placeCat = this.itemCatalog.inputCategory("* Enter the category in which you wish to place the item: "); //user inputs data
                    
                    if (placeCat != null)
                    {
                        placeCat.addItem(newItem); //calls function to add newItem object to placeCat arrayList
                        placeCat.updateDatabase(); //calls function to update database
                        
                         System.out.format("\n\nYou've added a new Item successfully.\nItem Information:\nName: %s\nPrice: %s\n", newItem.getName(), newItem.getPrice());
                    }
                    else
                    {
                        System.out.println("Invalid Category");
                    }
                    
                    break;
                case 2:
                    this.readPurchaseHistory();
                    break;
                case 3:
                    this.memberList.showMembers(); //calls function to print member users
                    break;
                case 4:
                    this.adminList.showAdmins(); //calls function to print admin users
                    break;
                case 5:
                    Admin newAdmin = new Admin(); //create new object from Admin Class
                    if (newAdmin.register(this.adminList)) //call function from new object to register new administrator
                    {                    
                        this.adminList.add(newAdmin); //add newAdmin to adminList array
                        this.adminList.UpdateAdminFile();

                        System.out.format("\n\nCongratulations, you've registered a new Admin successfully.\nTo login, use these credentials:\nUsername: %s\nPassword: %s\n", newAdmin.getUsername(), newAdmin.getPassword());
                        
                        break;
                    }
                    else
                    {
                        break;
                    }
                case 6:
                    cont = false;
                    break;  
                default:
                    System.out.println("**Enter a valid value! (1-6)**");
                    break;
            }
        }
    }
    /**
     * Function implements an interface for member users to sign up, login,
     * view item, view personal info, and purchase items
     * @throws IOException 
     */
    void MemberInterface() throws IOException {
        System.out.format("Hello, %s %s %s. Welcome to the TAMUCC electronics store.\n", this.currentUser.getFirst(), this.currentUser.getInitial(), this.currentUser.getLast());
        boolean cont = true;
        
        int option;

        while(cont)
        {
            option = FileUtility.integerInput("\n- Member Menu -\n1. View Catalog\n2. View Membership Info\n3. Buy Item\n4. Upgrade Membership\n5. View Points\n6. Exit\n\n---Please select an option---\n");
            switch(option)
            {
                case 1: 
                    this.itemCatalog.showCategories(); //calls function prints item catalog to user
                    break;
                case 2: 
                    this.currentUser.showMemberInfo(); //calls function to show current user information
                    break;
                case 3:
                    System.out.println("---Item Category Selection---");
                    this.itemCatalog.showCategories(false);
                    System.out.println("\n");
                    Category inputCategory = this.itemCatalog.inputCategory(); //creates new Category object
                    
                    System.out.format("%-40s  %-15s  %-15s  %-10s  %-10s\n", "Item Name: ", "Price: ", "Geek Points: ", "Quanitity: ", "Availability: "); 
                    inputCategory.showItems();
                    
                    Item inputItem = this.itemCatalog.inputItem(inputCategory);
                    
                    this.PurchaseItem(inputCategory, inputItem);
                    
                    break;
                case 4:
                    if (this.currentUser.getPremium()) //conditional statement validates user status
                    {
                        System.out.println("You are already a Geek member.");
                    }
                    else
                    {
                        String input = FileUtility.stringInput("Are you sure you want to upgrade for $2.99 a month: [y/n]"); //user inputs string variable
                        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))
                        {
                            System.out.println("You have been upgraded to 'Geek' membership, congratulations!"); 
                            this.currentUser.setPremium(true); 
                            this.memberList.updateMembers();
                        }
                        else
                        {
                            System.out.println("You have declined the 'Geek' membership offer.");
                        }
                    }
                    
                    break;
                case 5:
                    if (this.currentUser.getPremium())
                    {
                        System.out.format("You currently have %d Geek points.\n", this.currentUser.getPoints()); //prints and calculates users premium points
                    }
                    else
                    {
                        System.out.println("Upgrade to Geek membership to get points.");
                    }
                    
                    break;
                case 6:
                    cont = false;
                    break; 
                default:
                    System.out.println("**Enter a valid value! (1-6)**");
                    break;
            }
        }
    }
    /**
     * Function allows member users to purchase items
     * @param currentCategory
     * @param currentItem
     * @throws IOException 
     */
    void PurchaseItem(Category currentCategory, Item currentItem) throws IOException
    {
        if (currentCategory != null)
        {
            if (currentItem != null)
            {
                if (currentItem.getAvailable() == Availability.AVAILABLE) //determines if current item is available
                {
                     
                    if (this.currentUser.getPremium()) //determines if current user is a premium member
                    {
                        int points = (int)(currentItem.getPrice()); //calculate premium points
                        this.currentUser.addPoints(points); //add points to user's accumulated points
                        
                        this.memberList.updateMembers();
                    }
                    
                    currentItem.decrementQuantity();
                    currentCategory.updateDatabase();
                    
                    this.writePurchaseHistory(currentItem); //calls function to add item purchase to history
                    
                    System.out.format("Thank you for purchasing %s for $%.2f, %s %s. Hope you enjoy it.\n", currentItem.getName(), currentItem.getPrice(), this.currentUser.getFirst(), this.currentUser.getLast());
                }
                else
                {
                    System.out.format("The item %s is not available currently, sorry.\n", currentItem.getName());
                }
            }
            else
            {
                System.out.println("Not an item in the category.\n");
            }
        }
        else
        {
            System.out.println("Not a category.\n");
        }
    }
    /**
     * Function writes and formats user's item purchase history
     * @param purchaseItem
     */
    public void writePurchaseHistory(Item purchaseItem){
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy KK:mm:ss a").format(new Date());
        String history = String.format("%s,%s,[%s] %s - Item: %s Price: %.2f\n", this.currentUser.getMemID(), this.currentUser.getUsername(), timeStamp, this.currentUser.getUsername(), purchaseItem.getName(), purchaseItem.getPrice());
        FileUtility.writeContent("PurchaseHistory.txt", history, true);
    }
    /**
     * Function reads user's item purchase history
     * @throws IOException 
     */
    public void readPurchaseHistory() throws IOException {
        ArrayList<String> history = FileUtility.retrieveContent("purchasehistory.txt"); //creates an arrayListobject history and fills it with data from textfile
        
        this.memberList.showMembers(); //call function to list members
        
        String input = FileUtility.stringInput("Enter UserID or Username: "); //prompts admin user to enter member
        
        System.out.println("\n\n- Purchase History - ");
        
        for(String Current: history) //creates loop to print history array list
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