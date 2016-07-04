/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import static startup.Expense.category;

public class Report extends User {
    protected static final String dataSeparator = ",";
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    protected static String inDate;
    protected static double totalExpense, totalIncome, totalAsset;
    protected static final String RESET = "\u001B[0m";
    protected static final String GREEN = "\u001B[32m";
    protected static final String RED = "\u001B[31m";
    protected static final String YELLOW = "\u001B[33m";

    public Report() {
        super();
        category = "";
    }

    
    public static void computeDaily(){
        Scanner input = new Scanner (System.in);
        System.out.print("Enter the date(yyyy/mm/dd): ");
        String inputDate = input.nextLine();
        List<String>expense = new ArrayList<String>();
        try{
        Scanner a = new Scanner (new FileInputStream(Expense.filename));
        for (int i=0; a.hasNextLine(); i++){  
            String s = a.nextLine();
            if (s!=null){
                String[]line = s.split(dataSeparator);
                for (String line1 : line) {
                    expense.add(line1);
                }
            }
        }    
        a.close();
        }catch(Exception e){
            System.out.println("Cannot read from the file");        
        }
        double total = 0;
        System.out.printf("%-15s%-15s%-15s\n","Date","Category","Amount");
        for (int i=2; i<expense.size();i++){
            if (inputDate.equals(expense.get(i))){
                total+=Double.parseDouble(expense.get(i-1));
                String cat=null;
                for (int j=0; j<22; j++){
                    if (expense.get(i-2).equals(Expense.expenseC[j])){
                        cat = Expense.expenseN[j];
                    }    
                }
                System.out.printf("%-15s%-15s%-15s\n",expense.get(i),cat,expense.get(i-1));
            }
        }
        
        System.out.println("--------------------------------------");
        System.out.printf("%-15s%-15s%-15s\n","","Total",total);
        
        
    }
    

    public static void computeWeekly() throws ParseException{
        List<String>expense = new ArrayList<String>();
        try{
        Scanner b = new Scanner (new FileInputStream(Expense.filename));
        for (int i=0; b.hasNextLine(); i++){  
            String s = b.nextLine();
            if (s!=null){
                String[]line = s.split(dataSeparator);
                for (String line1 : line) {
                    expense.add(line1);
                }
            }
        }    
        b.close();
        }catch(Exception e){
            System.out.println("Cannot read from the file");        
        }
        Scanner a = new Scanner (System.in);
        Calendar c = Calendar.getInstance();
        System.out.print("Enter the date (yyyy/mm/dd): ");
        String inputDate = a.nextLine();
        Date date = dateFormat.parse(inputDate);
        c.setTime(date);
        
        double total = 0;
        System.out.printf("%-15s%-15s%-15s\n","Date","Category","Amount");
        int count = 0;
        while (count<7){
            for (int i=2; i<expense.size();i+=3){
                if (dateFormat.format(c.getTime()).equals(expense.get(i))){
                    total+=Double.parseDouble(expense.get(i-1));
                    String cat=null;
                    for (int j=0; j<22; j++){
                        if (expense.get(i-2).equals(Expense.expenseC[j])){
                            cat = Expense.expenseN[j];
                        }    
                    }
                    System.out.printf("%-15s%-15s%-15s\n",expense.get(i),cat,expense.get(i-1));
                }
            }
            c.add(Calendar.DATE, 1);
            count++;
        }
        System.out.println("--------------------------------------");
        System.out.printf("%-15s%-15s%-15s\n","","Total",total);
        
    

    }
    public static void computeMonthly() throws ParseException{
        Scanner input = new Scanner (System.in);
        System.out.print("Enter the month (yyyy/mm): ");
        String inputDate = input.nextLine();
        inDate = inputDate;
        List<String>expense = new ArrayList<String>();
        List<String>asset = new ArrayList<String>();
        List<String>income = new ArrayList<String>();
        System.out.println("**Expense**");
        compute(Expense.filename,expense,Expense.expenseC,Expense.expenseN,22);
        System.out.println("**Income**");
        compute(Expense.filename2,income,Expense.incomeC,Expense.incomeN,4);
        System.out.println("**Asset**");
        compute(Expense.filename1,asset,Expense.assetC,Expense.assetN,13);
        double percentage = totalExpense/totalIncome*100;
        if (percentage>80){
            System.out.println(RED+ "YOU ARE IN THE DANGEROUS LEVEL OF SPENDING HABIT!");
            System.out.println(RED+ "NO MONEY, NO WIFE!");
        }else if (percentage>50){
            System.out.println(YELLOW+ "PLEASE CONSIDER YOUR SPENDING HABIT!");
            System.out.println(YELLOW+ "A WISE MAN ALWAYS THINK TWICE BEFORE SPENDING.");
        }else{
            System.out.println(GREEN + "YOUR SPENDING HABIT IS EXCELLENT!");   
            System.out.println(GREEN + "KEEP IT UP!");
        }
        
        if (Expense.computeTotalAsset()>60000){
            System.out.println(GREEN + "Asset indicator: BRAVO!");   
        }else if (Expense.computeTotalAsset()>45000){
            System.out.println(YELLOW+ "Asset indicator: KEEP UP THE GOOD WORK!");
        }else{
            System.out.println(RED+ "Asset indicator: HAZARDOUS!");
        }
    }
    public static void compute(File file,List b,String[]aa, String[]bb,int k) throws ParseException{
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
        Date date = format.parse(inDate);
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,1);
        try{
        Scanner a = new Scanner (new FileInputStream(file));
        for (int i=0; a.hasNextLine(); i++){  
            String s = a.nextLine();
            if (s!=null){
                String[]line = s.split(dataSeparator);
                for (String line1 : line) {
                    b.add(line1);
                }
            }
        }    
        a.close();
        }catch(Exception e){
            System.out.println("Cannot read from the file");        
        } 
        
        double total = 0;
        System.out.printf("%-15s%-20s%-15s\n","Date","Category","Amount");
        int count = 0;
        while (count<c.getActualMaximum(Calendar.DAY_OF_MONTH)){
            for (int i=2; i<b.size();i+=3){
                if (dateFormat.format(c.getTime()).equals(b.get(i))){
                    total+=Double.parseDouble((String) b.get(i-1));
                    String cat=null;
                    for (int j=0; j<k; j++){
                        if (b.get(i-2).equals(aa[j])){
                            cat = bb[j];
                        }    
                    }
                    System.out.printf("%-15s%-20s%10s\n",b.get(i),cat,b.get(i-1));
                }
            }
            c.add(Calendar.DATE, 1);
            count++;
        }
        
        System.out.println("---------------------------------------------");
        System.out.printf("%-15s%-20s%10.2f\n","","Total",total);
        System.out.println("---------------------------------------------");
        if (k==22){
            totalExpense = total;
        }else if (k==13){
            totalAsset = total;
        }else if (k==4){
            totalIncome = total;
        }
    }
}
