/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Login {
    
    public Login() {
    }
    
     public static void signUp() {
        try{
            Scanner a = new Scanner(System.in);
       
            PrintWriter output= new PrintWriter(new FileOutputStream(("user.txt"),true));
            System.out.print("Username = ");
            String username = a.nextLine();
            System.out.print("Password = ");
            String password=a.nextLine();
            output.println(username);
            output.println(password);
      
            output.close();
        }catch (IOException e){
          System.out.println("File cannot read");
        }
    }
     public static void signIn() throws Exception {
        try{
        Scanner a = new Scanner(new FileInputStream("user.txt"));
        Scanner input = new Scanner (System.in);
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        int count = 0; 
        
        while(a.hasNextLine()){
            if (a.nextLine().equals(username)){
                if (a.nextLine().equals(password)){
                        System.out.println("Welcome, "+ username);
                        User.userPage();
                }else{
                    System.out.println("Wrong username or password");   
                }
                count++;
            }
        }
        if (count==0){
            System.out.println("Wrong username or password");
        }
            
        a.close();
            
        }catch (IOException e) {
            System.out.println("Cannot read from the file");
        }
    }
    
}
