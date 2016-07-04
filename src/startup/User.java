/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class User {

    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    public static void userPage() throws FileNotFoundException, Exception {
        Scanner input = new Scanner(System.in);
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Choose any option below to manage your finance: ");
        System.out.println("\t1. Asset Management");
        System.out.println("\t2. Income Management");
        System.out.println("\t3. Expenses Management");
        System.out.println("\t4. Reports");
        System.out.print("To Enter: ");
        String num = input.next();

        switch (num) {
            case "1":
                System.out.println("\t1. Add Assets");
                System.out.println("\t2. Modify Assets");
                System.out.println("\t3. Delete Assets");
                System.out.print("To Enter: ");
                String integer1 = input.next();
                Expense.displayAssets();
                switch (integer1) {
                    case "1":
                        Expense.addAssets();
                        break;
                    case "2":
                        Expense.setAssets();
                        break;
                    case "3":
                        Expense.deleteAssets();
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                break;
            case "2":
                System.out.println("\t1. Add Income");
                System.out.println("\t2. Modify Income");
                System.out.println("\t3. Delete Income");
                System.out.print("To Enter: ");
                String integer2 = input.next();
                Expense.displayIncome();
                switch (integer2) {
                    case "1":
                        Expense.addIncome();
                        break;
                    case "2":
                        Expense.setIncome();
                        break;
                    case "3":
                        Expense.deleteIncome();
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                break;
            case "3":
                System.out.println("\t1. Add Expenses");
                System.out.println("\t2. Modify Expenses");
                System.out.println("\t3. Delete Expenses");
                System.out.print("To Enter: ");
                String integer3 = input.next();
                Expense.displayExpenses();
                switch (integer3) {
                    case "1":
                        Expense.addExpenses();
                        break;
                    case "2":
                        Expense.setExpenses();
                        break;
                    case "3":
                        Expense.deleteExpenses();
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                break;
            case "4":
                System.out.println("\t1. Daily Report");
                System.out.println("\t2. Weekly Report");
                System.out.println("\t3. Monthly Report");
                System.out.print("To Enter: ");
                String integer4 = input.next();
                switch (integer4) {
                    case "1":
                        Report.computeDaily();
                        break;
                    case "2":
                        Report.computeWeekly();
                        break;
                    case "3":
                        Report.computeMonthly();
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                break;
            default:
                System.out.println("Error");
                break;
        }
    }
    
    
}
