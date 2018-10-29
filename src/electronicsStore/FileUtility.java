package electronicsStore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The FileUtility Class contains commonly used functions throughout the TAMUCC 
 * Electronics store program that deal with File Operations
 * 
 */

public class FileUtility
{
    //declaring a scanner to read in from keyboard 
    private static Scanner scan = new Scanner(System.in);
    //this method will find take the name and turn it into a file path
    public static String nameToFile(String path, boolean upper){
        path = path.replaceAll("&", ""); //replaces all "&" characters in path with " "
        path = path.replaceAll(" ", "");
        path = path.trim();
        path = path + ".txt";
        path = ".\\txtFiles\\" + path;
        
        if (upper)
        {
            path = path.toUpperCase();
        }
        
        return path;
    }
    //no boolean parameter
    public static String nameToFile(String path){
        return nameToFile(path, false);
    }
    //method used as utility function retrieve content from a file
    public static ArrayList<String> retrieveContent(String path) throws FileNotFoundException, IOException {
        ArrayList<String> content = new ArrayList();//new string array list
        try (FileInputStream in = new FileInputStream(path))
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null)
            {
                content.add(line);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
            FileOutputStream file = new FileOutputStream(path, true);
            file.close();
            
            return null;
        }
        finally
        {
            return content;
        }
    }
    //method used as utility function to write to a file
    public static void writeContent(String path, String contents, boolean append){
        try (FileWriter fw = new FileWriter(path, append);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print(contents);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    //method used as utility function to update changing data
    public static void updateContent(String path, String contents){
        writeContent(path, contents, true);
    }
    
    //method used as a utility function to get integer user input
    public static int integerInput(String prompt){
        System.out.println(prompt);
        
        while (!scan.hasNextInt())
        {
            scan.next();
        }
        
        int input = scan.nextInt();
        scan.nextLine();
        
        return input;
    }
    //this method used as a utility function that will get a double from a user input
    public static double doubleInput(String prompt){
        System.out.println(prompt);
        
        while (!scan.hasNextDouble())
        {
            scan.next();
        }
        
        double input = scan.nextDouble();
        scan.nextLine();
        
        return input;
    }
    //method used as a utility function to get user string input
    public static String stringInput(String prompt){
        System.out.println(prompt);
        
        String input;
                
        do
        {
            input = scan.nextLine();
        } while(input.trim().equalsIgnoreCase(""));
        
        return input;
    }
}