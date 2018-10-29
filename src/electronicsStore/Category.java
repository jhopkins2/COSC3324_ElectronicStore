package electronicsStore;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Category class uses Item class to create a list that hold Item objects
 * to perform operations on
 */

public class Category
{
    
    private ArrayList<Item> items = new ArrayList(); //a list to contain items
    
    private String name;//the name of the category
    private String path;//the file path to that category
    
    public Category(String name){ //this constructor will accept a name for category
        this.setName(name);
    }
    // This constructor initializes variables with parameters and stores new items into an array
    public Category(String name, String path) throws IOException { //parameter constructor
        this.setName(name); //call the name and path mutator methods
        this.setPath(path);
        
        //find the file from the provided path and store the strings into this list
        ArrayList<String> itemStrings = FileUtility.retrieveContent(this.getPath());   
        //loop through the list
        for (String current: itemStrings)
        {
            //remove the commas from the string
            String[] arr = current.split(",");
            
            if (arr.length < 3) //determine if the string contains 4 fields
            {
                continue;//if not skip the loop
            }
            //determine if any of the fields are empty.
            if ((arr[0] != null && !arr[0].isEmpty()) && (arr[1] != null && !arr[1].isEmpty()) && (arr[2] != null && !arr[2].isEmpty())) 
            {   //if not empty store the contents into new Item
                String name1 = arr[0];
                
                String priceString = arr[1].trim(); //initializes array element
                double price = Double.parseDouble(priceString);
                
                String quantityString = arr[2].trim();
                int quantity = Integer.parseInt(quantityString);
                
                Item newItem = new Item(name1, price, quantity); //create object newItem from class Item
                
                this.addItem(newItem); //call method to store object in array
            }
        }
    }
    //another constructor
    public Category(Category cat) throws IOException {
        this(cat.getName(), cat.getPath());
    }
    
    public String getName(){ //Name accessor method
        return this.name; //return name variable
    }
    public String getPath(){//Path accessor method
        return this.path;//return path variable
    }
    public Item getItem(int num){ //retrieve item from the category
        num = num - 1;//decrement num to keep with array count
        return this.items.get(num);//return the item from that num
    }
    public Item getItem(String name){//retrieve item based on name provided
        for (Item current: this.items) //loop through to find specified item
        {
            if (current.getName().equalsIgnoreCase(name)) //checks if parameter string matches current variable disregards letter casing
            {
                return current;//if found return item
            }
        }
        
        return null;//if not found return nothing
    }
    public ArrayList<Item> getItems(){ //return Item array method
        return (ArrayList<Item>) this.items.clone(); //copies from item arraylist 
    }
    
    private void setName(String name){ //name mutator method
        if (name != null && !name.isEmpty()) //parameter validation
        {
            this.name = name; //set variable if conditions are met
        }
    }
    private void setPath(String path){ //path mutator method
        if (path != null && !path.isEmpty()) //test parameter based on conditions
        {
            this.path = path;//set path if conditions are met
        }
    }
    //method adds items to arrayList items
    public void addItem(Item item) throws IOException {
        this.items.add(item);
    }
    //this method will take the categories path and write a string to the category file name
    public void updateDatabase() throws UnsupportedEncodingException, IOException {
        FileUtility.writeContent(this.getPath(), this.toFile(), false); //call FileUtility method to write to file
    }
    //this method will take the item arraylist and put it into a string and return that string
    public String toFile(){
        String data = "";
        for (Item current: this.items)//loop through arraylist
        {
            //append formatted string to data    
            data += String.format("%s,%.2f,%d\n", current.getName(), current.getPrice(), current.getQuantity());
        }
        
        return data;//return string
    }
    //this method will display the content item arraylist
    public void showItems(){
        for (Item current: this.items) //loops for each element in arrayList
        {
            System.out.println(current); //print current element
        }
    }
}