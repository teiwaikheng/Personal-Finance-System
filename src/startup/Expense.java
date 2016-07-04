/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Expense extends User{
    protected static String category;
    protected static double amount;
    protected static int expenseCount=0;
    protected static File filename = new File("expenses.txt");
    protected static File filename1 = new File("asset.txt");
    protected static File filename2 = new File("income.txt");
    protected static File tempFile = new File("myTempFile.txt");
    protected static final String dataSeparator = ",";
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    protected static String[]expenseC = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"};
    protected static String[]expenseN = {"Automobile","Cash","Charity", "Child care","Clothing","Credit card payment","Eating out","Education","Entertainment","Gifts","Groceries","Health & Fitness","Household","Insurance premium","Loan","Medical","Others","Pets","Tax","Transport","Travel","Utilities"};
    protected static String[]assetC = {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
    protected static String[]assetN = {"Building","Cash on deposit","Cash on hand","Corporate bond","Corporate stock","Land","Retirement account","Loans receivable","Investment","Mutual Fund","Jewellery","Automobile","Insurance"};
    protected static String[]incomeC = {"1","2","3","4"};
    protected static String[]incomeN = {"Salary","Bonus","Lottery","Others"};

    
    public Expense() {
        super();
        category = "";
        amount = 0;
    }
        
    public static void displayExpenses(){
        System.out.println("Categories: ");
        System.out.printf("\t%-30s%-30s\n","1. Automobile","12. Health & Fitness");
        System.out.printf("\t%-30s%-30s\n","2. Cash","13. Household");
        System.out.printf("\t%-30s%-30s\n","3. Charity","14. Insurance premium");
        System.out.printf("\t%-30s%-30s\n","4. Child care","15. Loan");
        System.out.printf("\t%-30s%-30s\n","5. Clothing","16. Medical");
        System.out.printf("\t%-30s%-30s\n","6. Credit card payment","17. Others");
        System.out.printf("\t%-30s%-30s\n","7. Eating out","18. Pets");
        System.out.printf("\t%-30s%-30s\n","8. Education","19. Tax");
        System.out.printf("\t%-30s%-30s\n","9. Entertainment","20. Transport");
        System.out.printf("\t%-30s%-30s\n","10. Gifts","21. Travel");
        System.out.printf("\t%-30s%-30s\n","11. Groceries","22. Utilities");
        
    }   
    public static void writeFile(File file, List c, int m, int n){
        readFile(file,c);
        while(true){
            Scanner input = new Scanner (System.in);
            System.out.print("Enter the category[Enter 'Q' to quit]: ");
            String a = input.next();
                if (a.equalsIgnoreCase("Q")){
                    break;
                }else if(Integer.parseInt(a)>=m||Integer.parseInt(a)<=n){
                    category = a; 
                }else{
                    System.out.println("This category does not exist."); 
                }
            System.out.print("Enter amount: ");
            amount = input.nextDouble();
            System.out.println("Data saved");
            int count = 0;
            Date date = new Date();
	    
	    for (int i = 0 ; i < c.size() ; i+=3) {
	    	if (c.get(i).equals(category)&&c.get(i+2).equals(dateFormat.format(date))) {
                    double total = Double.parseDouble((String) c.get(i+1))+amount;
                        c.remove(i);
                        c.remove(i);
                        c.remove(i);
                        try{
                        PrintWriter b = new PrintWriter(new FileOutputStream(tempFile, true)); 
                        Date date1 = new Date();
                        for (int j=0; j<c.size();j+=3){
                            b.println(c.get(j)+dataSeparator+c.get(j+1)+dataSeparator+c.get(j+2));
                        }
                        b.printf("%s%s%.2f%s%s\r\n",category,dataSeparator,total,dataSeparator,dateFormat.format(date1));
                        b.close();
                        }catch(Exception e){
                            System.out.println("Cannot write to the file"); 
                        }
                        deleteFile(file);
                        renameFile(file);
                        count++;
	    		break;
	    	}
	    
                
	    }
            if (count==0){
                try{
                    PrintWriter b=new PrintWriter(new FileOutputStream(file,true));
                    b.printf("%s%s%.2f%s%s\r\n",category,dataSeparator,amount,dataSeparator,dateFormat.format(date));
                    b.close();
                } catch(IOException e){
                }
            }    
            
        }        
    
        
    }
    
    public static void readFile(File b, List c){
        try{
        Scanner a = new Scanner (new FileInputStream(b));
        for (int i=0; a.hasNextLine(); i++){  
            String s = a.nextLine();
            if (s!=null){
                String[]line = s.split(dataSeparator);
                for (String line1 : line) {
                    c.add(line1);
                }
            }
        }    
        a.close();
        }catch(Exception e){
            System.out.println("Cannot read from the file");        
        }
    }
    public static void addExpenses(){
        List<String>expense = new ArrayList<String>();
        writeFile(filename,expense,1,22);    
    }
    public static void set(File a, List c) throws IOException {
        readFile(a,c);
        Scanner input = new Scanner(System.in); 
	System.out.print("ENTER CATEGORY TO MODIFY : ");
	String toSearch = input.nextLine();
        System.out.print("Enter date(yyyy/mm/dd): ");
        String indate = input.nextLine();
        int count = 0;
        int no=0;
	    for (int i = 0 ; i < c.size() ; i+=3) {
	    	if (c.get(i).equals(toSearch)&&c.get(i+2).equals(indate)) {
                    System.out.println("CATEGORY　FOUND!");
                        count++;
                        no=i;
	    		break;
	    	}
	    }
	    if (count==0) {
	    	System.out.println("CATEGORY DOESN'T EXIST!");
	    } 
            else {
	    	String inputStr=null;
		System.out.print("ENTER NEW AMOUNT (TYPE \"REMAIN\" TO KEEP THE OLD VALUE) : ");
		    inputStr=input.next();
		    if (!inputStr.equalsIgnoreCase("REMAIN")) {
                        c.remove(no);
                        c.remove(no);
                        c.remove(no);
                        try{
                        PrintWriter b = new PrintWriter(new FileOutputStream(tempFile, true)); 
                        Date date = new Date();
                        for (int i=0; i<c.size();i+=3){
                            b.println(c.get(i)+dataSeparator+c.get(i+1)+dataSeparator+c.get(i+2));
                        }
                        b.println(toSearch+dataSeparator+inputStr+dataSeparator+dateFormat.format(date));
                        b.close();
                        }catch(Exception e){
                            System.out.println("Cannot write to the file"); 
                        }
                        deleteFile(a);
                        renameFile(a);
                        System.out.println("SUCCESSFULLY MODIFIED");
                    }
                    
	    }
                    
    }
    public static void setExpenses() throws IOException{
        List<String>expense1 = new ArrayList<String>();
        set(filename,expense1);
    }
    public static boolean deleteFile(File a){
        boolean b = a.delete();
        return b;
    }
    public static boolean renameFile(File a){
        boolean b = tempFile.renameTo(a);
        return b;
    }
             
    public static void delete(File a, List c){
        readFile(a,c);
        Scanner input = new Scanner(System.in);
        System.out.print("ENTER CATEGORY TO REMOVE: ");
        String toSearch = input.nextLine();
        System.out.print("Enter date(yyyy/mm/dd): ");
        String date = input.nextLine();
        int count = 0;
        int no = 0;
        for (int i = 0 ; i < c.size() ; i+=3) {
	    	if (c.get(i).equals(toSearch)&&c.get(i+2).equals(date)) {
                    count++;
                    no=i;
                    break;
                    
	    	}
	    }
        if (count!=0){
            c.remove(no);
            c.remove(no);
            c.remove(no);
            try{
            PrintWriter b = new PrintWriter(new FileOutputStream(tempFile, true));
            for (int j=0; j<c.size();j+=3){
                b.println(c.get(j)+dataSeparator+c.get(j+1)+dataSeparator+c.get(j+2));
            }
             b.close();
            }catch(Exception e){
                System.out.println("Cannot write to the file"); 
            }
                deleteFile(a);
                renameFile(a);
            System.out.println("SUCCESSFULLY REMOVED!");
        }
        else{
        System.out.println("CATEGORY DOES NOT EXIST!");        
        }
    } 
    public static void deleteExpenses(){
        List<String>expense2 = new ArrayList<String>();
        delete(filename,expense2);
    }
    public static void displayAssets(){
        System.out.println("Categories: ");  
        System.out.println("1. Building       \t\t\t8. Loans receivable");
        System.out.println("2. Cash on deposit\t\t\t9. Investment");
        System.out.println("3. Cash on hand   \t\t\t10. Mutual fund");
        System.out.println("4. Corporate bonds\t\t\t11. Jewellery");
        System.out.println("5. Corporate stock\t\t\t12. Automobile");
        System.out.println("6. Land\t\t\t\t\t13. Insurance");
        System.out.println("7. Retirement account");
    }
    public static void addAssets(){
        List<String>asset = new ArrayList<String>();
        writeFile(filename1,asset,1,13);
    }
    public static void setAssets() throws IOException{
        List<String>asset = new ArrayList<String>();
        set(filename1,asset);
    }
    public static void deleteAssets(){
        List<String>asset = new ArrayList<String>();
        delete(filename1,asset);
    }
    public static void displayIncome(){
        System.out.println("Categories: ");  
        
        System.out.println("1. Salary");
        System.out.println("2. Bonus");
        System.out.println("3. Lottery");
        System.out.println("4. Others");
    }
    public static void addIncome(){
        List<String>income = new ArrayList<String>();
        writeFile(filename2,income,1,4);
    }
    public static void setIncome() throws IOException{
        List<String>income = new ArrayList<String>();
        set(filename2,income);
    }
    public static void deleteIncome(){
        List<String>income = new ArrayList<String>();
        delete(filename2,income);
    }

    public static double computeTotal(File a) {
        List<String>temp1 = new ArrayList<String>();
        double total = 0;
        readFile(a,temp1);
        for (int i=1; i<temp1.size();i+=3){
            total += Double.parseDouble(temp1.get(i));
        }
        return total;
    }
    
    public static double computeTotalExpense(){
        return computeTotal(filename);
    }
    public static double computeTotalIncome(){
        return computeTotal(filename2);
    }
    public static double computeTotalAsset(){
        return computeTotal(filename1);
    }
    public static double computeTotal(){
        return computeTotalExpense()+ computeTotalIncome()+ computeTotalAsset();
    }
    

}
