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
    private static Scanner scan = new Scanner(System.in);
    
    public static String nameToFile(String path, boolean upper){
        path = path.replaceAll("&", ""); //replaces all "&" characters in path with " "
        path = path.replaceAll(" ", "");
        path = path.trim();
        path = path + ".txt";
        
        if (upper)
        {
            path = path.toUpperCase();
        }
        
        return path;
    }
    public static String nameToFile(String path){
        return nameToFile(path, false);
    }
    //function used as utility function retrieve content from array
    public static ArrayList<String> retrieveContent(String path) throws FileNotFoundException, IOException {
        ArrayList<String> content = new ArrayList();
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
    //function used as utility function to output content into a file
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
    //function used as utility function to update changing data
    public static void updateContent(String path, String contents){
        writeContent(path, contents, true);
    }
    
    //function used as a utility function to gain integer user input
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
    //function used as a utility function to get user string input
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