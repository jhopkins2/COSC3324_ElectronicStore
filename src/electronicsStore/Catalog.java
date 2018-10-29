package electronicsStore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Catalog class uses the Category class to create a data structure for the categories of the store
 * 
 */

public class Catalog
{
    private ArrayList<Category> categories = new ArrayList(); //new ArrayList object of category type created
    
    public Category getCategory(int num){//accessor method of obtaining the categories for the ArrayList
        num = num - 1; //decrement the parameter value by one
        
        if ((num < 0) || (num > this.categories.size())) //validate the parameter
        {
            return null;//if one condition is true return nothing
        }
        
        return this.categories.get(num); //returns category if condition is met
    }
    //accessor function takes user input as a parameter. A specific category will be found based on the parameter provided
    public Category getCategory(String name){
        for(Category current: this.categories) //loop through the categories array list
        {
            if (current.getName().equalsIgnoreCase(name) || current.getPath().equalsIgnoreCase(name))//compare the parameters name in the array list to find category
            {
                return current;//return category if found
            }
        }
        
        return null; //return nothing if not found
    }
    //function will return the contents of the catergories array list
    public ArrayList<Category> getCategories(){
        return (ArrayList<Category>) this.categories.clone(); //return a copy of arraylist
    }
    //this function displayss the list of categories to user
    public void showCategories(boolean showItems){ //the parameter is used to determined to show the categories item list or not  
        if (showItems)//if true display categories and items
        {
            for(Category Current: this.categories)//loop through the array list
            {
                System.out.format(" ----%s-----\n", Current.getName()); //prints categories to user
                
                System.out.format("%-40s  %-15s  %-10s  %s\n", "Item: ", "Price: ", "Quanitity:", "Availability:"); //formated output
                Current.showItems();

                System.out.print("\n\n");
            }
        }
        else
        {
            for(Category Current: this.categories)
            {
                System.out.format("%s\n", Current.getName());
            }
        }
    }
    public void showCategories(){
        showCategories(true);
    }
    //function inputs category with user entered string parameter
    public Category inputCategory(String prompt){
        Category inputCat = null;
        String cat;
        while(inputCat == null)
        {
            cat = FileUtility.stringInput(prompt);
            inputCat = this.getCategory(cat);
        }
        
        return inputCat;
    }
    public Category inputCategory(){ //prompts user for input
        return inputCategory("* Enter the category for the item you are looking for: ");
    }
    public Item inputItem(Category cat){
        Item inputItem = null; 
        String item;
        while(inputItem == null) //loops while inputItem equals null
        {
            item = FileUtility.stringInput("\nEnter the item name: "); //prompts user to enter item variable input
            
            inputItem = cat.getItem(item); //assigns user input to Item inputItem variable
        }
        
        return inputItem; //returns Item name as inputName variable
    }
    private void addCategory(Category cat){
        if (cat != null) //validation parameter does not equal null
        {
            this.categories.add(cat); //adds validated parameter to categories arrayList object
        }
    }
    //function populates arraylist with "categories.txt" 
    public void populate() throws FileNotFoundException, IOException {
        ArrayList<String> categoryStrings = FileUtility.retrieveContent(".\\txtFiles\\categories.txt"); //fills categoryStrings arrayList
        
        for(String current: categoryStrings)
        {
            String fpath = FileUtility.nameToFile(current, false);
            
            Category cat = new Category(current, fpath);
            
            this.addCategory(cat);
        }
    }
    //function prints items to the user from selected category in parameter
    public void showItems(Category cat){ 
        for(Item Current: cat.getItems()) //loops for each item in category
        {
            System.out.format("%s\n", Current.getName()); //formated output
        }
    }
}