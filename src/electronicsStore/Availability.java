package electronicsStore;

/**
 * This enumeration is utilized to represent the availability 
 * of a given item within a Catalog
 * 
 */

public enum Availability
{
    AVAILABLE(1), UNAVAILABLE(2); //enum type predefined constants for book status
    
    private int status; //private variable declaration
    
    Availability(int status){ //assigns parameter to status variable
        this.status = status;
    }
    
    public int getStatus(){ //accessor function to return status 
        return this.status; 
    }
}