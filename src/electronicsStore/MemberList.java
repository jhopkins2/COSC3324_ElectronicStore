package electronicsStore;

import java.io.IOException;
import java.util.ArrayList;

/**
 * MemberList class both retrieves and sets members information to be used by the system
 * including adding new members to data files, validating member username and passwords, and displaying
 * user information.
 */

public class MemberList
{
    ArrayList<Member>Memberlist = new ArrayList();
    
   
    //creates new members information layout to be written to the member.txt files
    public void updateMembersFile()throws IOException{
        String contents = "";
        
        for(Member current: this.Memberlist)
        {
            int premium = 0;
            if(current.getPremium() == true){
                premium = 1;
            }
            else{
                premium = 0;
            }            
            String memberdata = String.format("%s,%s,%d,%s,%s,%s,%s,%s,%d,%d\n",current.getUsername(), current.getPassword(), current.getMemID(), current.getFirst(), current.getInitial(), current.getLast(), current.getEmail(), current.getPhone(),current.getPoints(), premium );
            
            contents = contents + memberdata;
        }
        
        FileUtility.writeContent(".\\txtFiles\\members.txt", contents, false);//writes content to the members file
    }
     //Checks to see if member already exists in the member list
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
    //validates if username is present in list
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
   
    //fills the arrayList with data from the members file to be used by the system
    public void populate() throws IOException {
        ArrayList<String> members = FileUtility.retrieveContent(".\\txtFiles\\members.txt");
        
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
            String phone = arr[7];
            
            
            int points = Integer.parseInt(arr[8]);
            
            int ipremium = Integer.parseInt(arr[9]);
            
            boolean premium = (ipremium == 1);
            
            Member newMember = new Member(username, password, memID, fName, mInitial, lName, email, phone, points, premium);
            this.add(newMember); //calls to add member to the member list
        }
    }
  //function to add a member to the memberlist
    public void add(Member m)throws IOException {
        this.Memberlist.add(m);
    }
    //function to delete a member from the list
    public void remove(Member e){
        this.Memberlist.remove(e);
    }
    public int length() {
        return this.Memberlist.size();
    }
    
    //displays the list of all members in the member list
    //can only be called by the admins
    public void showMembers(){
        System.out.println();
        System.out.println("---List of Registered Members---\n");
        for(Member Current: this.Memberlist)
        {
            System.out.format("%d %s\n", Current.getMemID(), Current.getUsername());
        }
        System.out.println();
    }
}