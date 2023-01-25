import java.util.*;

public class BankOperations{
  // Main method
  public static void main(String[] args){
    Scanner keyboard = new Scanner(System.in); // For user input
    Customer[] customers = new Customer[5]; // Customer array, limited to 5 customers
    CheckingAccount[] allAccounts = new CheckingAccount[25]; // All checking accounts
    int numTotalAccounts = 0;
    boolean continueProgram = true; // Controls the loop
    String command; // Menu command

    // Loop through asking for user commands
    while(continueProgram){
      command = keyboard.next();
      int tempNumAccounts;
      int currentAccounts;
      int tempAccountNum;
      int indexOfAccount;
      double tempBal;
      String tempName;
      switch(command){
        case "new": // Create a new customer account
          currentAccounts = Customer.getTotalCustomerAccounts();
          tempNumAccounts = keyboard.nextInt();
          tempName = keyboard.nextLine();
          tempName = tempName.substring(1); // Cut off space at beginning
          if(tempNumAccounts > 5)
            System.out.println("MAX 5 accounts per customer!");
          Customer tempCustomer = new Customer(tempNumAccounts, tempName);
          customers[currentAccounts] = tempCustomer;
          for(int i = 0; i < tempNumAccounts; i++){ // Take input to create the checking account(s)
            tempBal = keyboard.nextDouble();
            CheckingAccount tempAcc = new CheckingAccount(tempBal);
            allAccounts[numTotalAccounts] = tempAcc;
            numTotalAccounts++;
            Customer cust = customers[currentAccounts];
            cust.setCheckingAccounts(i, tempAcc);
          }
          keyboard.nextLine(); // clear buffer
          break;

        case "add": // Add checking acounts to customer account
          currentAccounts = Customer.getTotalCustomerAccounts();
          tempNumAccounts = keyboard.nextInt();
          tempName = keyboard.nextLine();
          tempName = tempName.substring(1); // Cut off space at beginning
          indexOfAccount = searchForString(customers, tempName);
          if(indexOfAccount == -1)
            indexOfAccount = searchForString2(customers, Integer.parseInt(tempName));
          if(customers[indexOfAccount].getNumAccounts() == 5) // Check if customer has 5 accounts
            System.out.println("Error: Customer already has 5 accounts.");
          else{
            for(int i = 0; i < tempNumAccounts; i++){
              tempBal = keyboard.nextDouble();
              CheckingAccount tempAcc = new CheckingAccount(tempBal);
              allAccounts[numTotalAccounts] = tempAcc;
              numTotalAccounts++;
              Customer cust = customers[indexOfAccount];
              cust.setCheckingAccounts(cust.getNumAccounts() + i, tempAcc);
            }
            keyboard.nextLine(); // clear buffer
          }
          break;

        case "deposit": // Deposit money into checking account
          tempAccountNum = keyboard.nextInt();
          tempBal = keyboard.nextDouble();
          indexOfAccount = searchForInt(allAccounts, tempAccountNum);
          System.out.println("New balance: " + allAccounts[indexOfAccount].deposit(tempBal));
          break;

        case "withdraw": // Withdraw money from checking account
          tempAccountNum = keyboard.nextInt();
          tempBal = keyboard.nextDouble();
          indexOfAccount = searchForInt(allAccounts, tempAccountNum);
          double tempWithdraw = allAccounts[indexOfAccount].withdraw(tempBal);
          if(tempWithdraw >= 0){
            System.out.printf("New balance: %.2f", tempWithdraw);
            System.out.println("");
          }
          else
            System.out.println("Withdrawal rejected to avoid negative balance.");
          break;
        case "close": // Close the program
          continueProgram = false;
          break;
      }
    }
  }

  // Searches for a given string in an array. Returns index, or -1 if not found
  public static int searchForString(Customer[] array, String str){
    for(int i = 0; i < Customer.getTotalCustomerAccounts(); i++){
      if(array[i].getName().equals(str))
        return i;
    }
    return -1;
  }

  // Alternate search
  public static int searchForString2(Customer[] array, int num){
    for(int i = 0; i < Customer.getTotalCustomerAccounts(); i++){
      if(array[i].getID() == num)
        return i;
    }
    return -1;
  }

  // Searches for a given int in an array. Returns index, or -1 if not found
  public static int searchForInt(CheckingAccount[] array, int num){
    for(int i = 0; i < CheckingAccount.getTotalCheckingAccounts(); i++){
      if(array[i].getID() == num)
      return i;
    }
    return -1;
  }
}