package electronicsStore;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class utilizes the Member class to create a list in which
 * is used to access, modify, display and instantiate Member objects
 * 
 */

public class MemberList
{
    ArrayList<Member>Memberlist = new ArrayList();
    
    //writes updated list to members.txt
    public void updateMembers() throws IOException {
        String contents = "";
        
        for(Member Current: this.Memberlist)
        {
            int premium = (Current.getPremium() == true ? 1 : 0);
            
            String memberdata = String.format("%s,%s,%d,%s,%s,%s,%s,%d,%.2f,%d\n", Current.getUsername(), Current.getPassword(), Current.getMemID(), Current.getFirst(), Current.getInitial(), Current.getLast(), Current.getEmail(), Current.getPaypal(), Current.getPoints(), premium);
            
            contents = contents + memberdata;
        }
        
        FileUtility.writeContent("members.txt", contents, false);
    }
    //Check if member exists within Member List
    public Member validateMember(String user, String password){
        for(Member current: this.Memberlist)
        {
            if (current.validateUser(user, password))
            {
                return current;
            }
        }
        return null;
    }
    //Check if username exists within Member List
    public boolean validateMemberUsername(String user){
        for(Member current: this.Memberlist)
        {
            if (current.validateUsername(user))
            {
                return true;
            }
        }
        return false;
    }
    //populates memberlist array with data stored in text file
    public void populate() throws IOException {
        ArrayList<String> members = FileUtility.retrieveContent("members.txt");
        
        for(String Current: members)
        {
            
            String[] arr = Current.split(",");
            
            if (arr.length < 9)
            {
                continue;
            }
            
            String username = arr[0];
            String password = arr[1];
            int memID = Integer.parseInt(arr[2]);
            
            String fName = arr[3];
            char mInitial = arr[4].charAt(0);
            String lName = arr[5];
            String email = arr[6];
            
            int paypal = Integer.parseInt(arr[7].trim());
            
            int points = Integer.parseInt(arr[8]);
            
            int ipremium = Integer.parseInt(arr[9]);
            
            boolean premium = (ipremium == 1);
            
            Member newMember = new Member(username, password, fName, lName, mInitial, email, premium, points, memID, paypal);
            this.add(newMember);
        }
    }
    //add member object to memberlist
    public void add(Member e) throws IOException {
        this.Memberlist.add(e);
    }
    //remove member object from memberlist
    public void remove(Member e){
        this.Memberlist.remove(e);
    }
    public int length() {
        return this.Memberlist.size();
    }
    public void showMembers(){
        System.out.println();
        System.out.println("---List of Registered Members---\n");
        for(Member Current: this.Memberlist)
        {
            System.out.format("%d. %s\n", Current.getMemID(), Current.getUsername());
        }
        System.out.println();
    }
}