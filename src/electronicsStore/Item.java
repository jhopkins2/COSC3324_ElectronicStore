package electronicsStore;

/**
 * Item class is used to hold the item data from the item's text files 
 * 
 */

public class Item
{
    private String name;//name of the item
    private double price; //price of the item
    private int quantity; //the amount in stock of the item
    private Availability available; //if the item is in stock then it is available for purchase
    
    public Item(String name, double price, int quantity){ //parameterized constructor
        this.setName(name); //uses mutator function to set parameter
        this.setPrice(price);
        this.setQuantity(quantity);
    }
    
    public void setName(String name){ //mutator function for item name
        if (name != null && !name.isEmpty()) //validating the given parameters
        {
            this.name = name;
        }
    }
    public void setPrice(double price){ //mutator function to set item price
        if (price > 0.0) //validates parameter
        {
            this.price = price; //initializes variable
        }
    }
    public void setQuantity(int quantity){ //mutator function sets item quantity
        if (quantity >= 0) //validates quantity 
        {
            this.quantity = quantity;// assigns parameter to variable
            
            if (this.getQuantity() > 0) //validates quantity
            {
                this.setAvailability(Availability.AVAILABLE); //sets available variable based on item quantity
            }
            else
            {
                this.setAvailability(Availability.UNAVAILABLE); //sets available variable based on item quantity
            }
        }
    }
    public void setAvailability(Availability status){ // Availability mutator function 
        this.available = status;//assign status to item
    }
    
    public String getName(){//Name accessor function
        return this.name;//return name variable
    }
    public double getPrice(){ //Price accessor function
        return this.price; //return price variable
    }
    public int getQuantity(){//Quantity accessor function
        return this.quantity;//return quantity variable
    }
    public Availability getAvailable(){ //accessor function returns available variable 
        return this.available;
    }
    public String getAvailableString(){ //accessor function returns string value 
        if (this.getAvailable() == Availability.AVAILABLE) // condition determines item availability
        {
            return "Available"; //if available return available
        }
        
        return "Unavailable";
    }
    
    //function decrements item quantities
    public void decrementQuantity(){
        int currentQuantity = this.getQuantity(); //get currentQuantity
        if (currentQuantity > 0) //validate if the quantity is greater than 0
        {
            this.setQuantity(currentQuantity - 1); //decrease quantity by 1
        }
    }    
    //item toString method
    @Override
    public final String toString(){//converts the data of the item into a readable string
        String text = String.format("%-40s  $%-15.2f  %-10d %-10s", 
                        this.getName(),  this.getPrice(), this.getQuantity(),  
                        this.getAvailableString());
        return text;//return that string for display
    }
}