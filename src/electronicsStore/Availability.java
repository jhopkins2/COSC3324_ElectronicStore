package electronicsStore;

/**
 * This enumeration is utilized to represent the availability 
 * of a given item within the Catalog
 * 
 */

public enum Availability
{
    AVAILABLE(1), UNAVAILABLE(2); //predefined enumerated constants for item availability
    
    private int status; //private variable declaration of item status
    
    Availability(int status){ //Class constructor parameter status
        this.status = status; //assign status to private variable
    }
    
    public int getStatus(){ //accessor function to return status 
        return this.status; 
    }
}