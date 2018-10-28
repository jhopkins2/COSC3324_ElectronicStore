package electronicsStore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Catalog class utilizes the Category class to create a list in which
 * is used to access, modify, display and instantiate Category objects
 * 
 */

public class Catalog
{
    private ArrayList<Category> categories = new ArrayList(); //new ArrayList object created
    
    public Category getCategory(int num){
        num = num - 1; //decrements parameter value
        
        if ((num < 0) || (num > this.categories.size())) //validate function parameter
        {
            return null;
        }
        
        return this.categories.get(num); //returns category
    }
    //this function take user input as a parameter and uses it to select a category
    public Category getCategory(String name){
        for(Category current: this.categories) //loop until categories array list ends
        {
            if (current.getName().equalsIgnoreCase(name) || current.getPath().equalsIgnoreCase(name)) //validates parameter input disregarding letter cases
            {
                return current;
            }
        }
        
        return null;
    }
    //function returns array list categories
    public ArrayList<Category> getCategories(){
        return (ArrayList<Category>) this.categories.clone(); //returns copy of arraylist categories
    }
    //this function outputs the list of categories to user
    public void showCategories(boolean showItems){   
        if (showItems)
        {
            for(Category Current: this.categories)
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