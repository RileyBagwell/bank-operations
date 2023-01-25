import java.util.*;

public class Customer extends BankOperations{
  Scanner keyboard = new Scanner(System.in); // For user input
  private static int totalCustomerAccounts = 0;
  private int customerAccountNum;
  private String name;
  private int numAccounts;
  private CheckingAccount[] checkingAccounts = new CheckingAccount[5];

  // Customer constructor
  Customer(int n, String str){
    totalCustomerAccounts++;
    customerAccountNum = totalCustomerAccounts + 1000000;
    name = str;
    numAccounts = n;
    System.out.println("Customer ID: " + customerAccountNum);
  }

  // Accessors
  public static int getTotalCustomerAccounts(){
    return totalCustomerAccounts;
  }
  public int getNumAccounts(){
    return numAccounts;
  }
  public String getName(){
    return name;
  }
  public int getID(){
    return customerAccountNum;
  }
  
  // Mutator for checkingAccounts
  public void setCheckingAccounts(int index, CheckingAccount tempAcc){
    checkingAccounts[index] = tempAcc;
  }
}