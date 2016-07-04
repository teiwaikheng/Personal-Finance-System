/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class StartUp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        
        Scanner input = new Scanner (System.in);
        String selection;
        
        System.out.println("Dear user, Welcome To SMART Finance Helper");
        System.out.println("\tAre you a SMART member? Join us now!");
        System.out.println("\t1. Sign Up");
        System.out.println("\t2. Sign In");
        System.out.print("PLEASE SELECT YOUR OPTION : "); 
        selection = input.next();
        Login  a = new Login();
        switch(selection){
            case"1": a.signUp();
                break;
            case"2": a.signIn();
                break;
            default: System.out.println("Error");   
                break;
        }
    }
    
}
