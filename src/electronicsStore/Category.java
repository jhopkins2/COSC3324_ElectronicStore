package electronicsStore;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Category class utilizes the Item class to create a list in which
 * is used to access, modify, display and instantiate Item objects
 * 
 */

public class Category
{
    
    private ArrayList<Item> items = new ArrayList(); //creates arrayList object books
    
    private String name;
    private String path;
    
    public Category(String name){ //parameter constructor
        this.setName(name);
    }
    // This constructor initializes variables with parameters and stores new items into an array
    public Category(String name, String path) throws IOException { //parameter constructor
        this.setName(name); //calls mutator functions
        this.setPath(path);
        
        ArrayList<String> itemStrings = FileUtility.retrieveContent(this.getPath()); //stores path value into itemStrings array   
        
        for (String current: itemStrings)
        {
            String[] arr = current.split(",");
            
            if (arr.length < 3) //validates array length
            {
                continue;
            }
            
            if ((arr[0] != null && !arr[0].isEmpty()) && (arr[1] != null && !arr[1].isEmpty()) && (arr[2] != null && !arr[2].isEmpty())) //creates conditional statement for arr[]
            {
                String name1 = arr[0];
                
                String priceString = arr[1].trim(); //initializes array element
                double price = Double.parseDouble(priceString);
                
                String quantityString = arr[2].trim();
                int quantity = Integer.parseInt(quantityString);
                
                Item newItem = new Item(name1, price, quantity); //create object newItem from class Item
                
                this.addItem(newItem); //call function to store object in array
            }
        }
    }
    public Category(Category cat) throws IOException {
        this(cat.getName(), cat.getPath());
    }
    
    public String getName(){ //accessor function
        return this.name; //return name variable
    }
    public String getPath(){
        return this.path;
    }
    public Item getItem(int num){
        num = num - 1;
        return this.items.get(num);
    }
    public Item getItem(String name){
        for (Item current: this.items) //loops for each element in books arrayList
        {
            if (current.getName().equalsIgnoreCase(name)) //checks if parameter string matches current variable disregards letter casing
            {
                return current;
            }
        }
        
        return null;
    }
    public ArrayList<Item> getItems(){ //array function
        return (ArrayList<Item>) this.items.clone(); //copies from arrayList items
    }
    
    private void setName(String name){ //mutator function
        if (name != null && !name.isEmpty()) //parameter validation
        {
            this.name = name; //initilaze variable
        }
    }
    private void setPath(String path){ //mutator function
        if (path != null && !path.isEmpty())
        {
            this.path = path;
        }
    }

    //function adds book to arrayList items
    public void addItem(Item item) throws IOException {
        this.items.add(item);
    }
    public void updateDatabase() throws UnsupportedEncodingException, IOException {
        FileUtility.writeContent(this.getPath(), this.toFile(), false);
    }
    public String toFile(){
        String data = "";
        for (Item current: this.items)
        {
            data += String.format("%s,%.2f,%d\n", current.getName(), current.getPrice(), current.getQuantity());
        }
        
        return data;
    }
    public void showItems(){
        for (Item current: this.items) //loops for each element in arrayList
        {
            System.out.println(current); //print current element
        }
    }
}