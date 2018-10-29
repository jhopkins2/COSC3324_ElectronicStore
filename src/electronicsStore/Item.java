package electronicsStore;

/**
 * Item class is utilized to create, modify, access and 
 * display item data
 * 
 */

public class Item
{
    private String name;
    private double price; //declares private price variable
    private int quantity;
    private Availability available; 
    
    public Item(){ //default constructor
        this.setName(null);
        this.setPrice(0);
        this.setQuantity(0);
    }
    public Item(String name, double price, int quantity){ //parameterized constructor
        this.setName(name); //uses mutator function to set parameter
        this.setPrice(price);
        this.setQuantity(quantity);
    }
    
    public void setName(String name){ //mutator function for item name
        if (name != null && !name.isEmpty()) //validates setName parameter 
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
                this.setAvailability(Availability.AVAILABLE); //sets available variable
            }
            else
            {
                this.setAvailability(Availability.UNAVAILABLE); //sets available variable
            }
        }
    }
    public void setAvailability(Availability status){ //mutator function 
        this.available = status;
    }
    
    public String getName(){
        return this.name;
    }
    public double getPrice(){ //accessor function
        return this.price; //return price variable
    }
    public int getQuantity(){
        return this.quantity;
    }
    public Availability getAvailable(){ //accessor function returns available variable 
        return this.available;
    }
    public String getAvailableString(){ //accessor function returns string value 
        if (this.getAvailable() == Availability.AVAILABLE) // condition determines item availability
        {
            return "Available";
        }
        
        return "Unavailable";
    }
    
    // function increments item quantities
    public void incrementQuantity(){
        int currentQuantity = this.getQuantity();
        this.setQuantity(currentQuantity + 1); //increments quantity by using mutator function
    }
    //function decrements item quantities
    public void decrementQuantity(){
        int currentQuantity = this.getQuantity(); //sets currentQuantity
        if (currentQuantity > 0) //validates quantity is greater than 0
        {
            this.setQuantity(currentQuantity - 1); //decrements quantity
        }
    }    
    //function to return formatted item selection
    @Override
    public final String toString(){
        String text = String.format("%-40s  $%-15.2f  %-10d %-10s", 
                        this.getName(),  this.getPrice(), this.getQuantity(),  
                        this.getAvailableString());
        return text;
    }
}