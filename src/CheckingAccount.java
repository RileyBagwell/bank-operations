public class CheckingAccount{
  private static int totalCheckingAccounts = 0;
  private int checkingAccountNum;
  private double balance;

  // CheckingAccount constructor
  CheckingAccount(double n){
    totalCheckingAccounts++;
    balance = n;
    checkingAccountNum = totalCheckingAccounts + 5000000;
    System.out.println("Account ID: " + checkingAccountNum);
  }

  // Deposits money from account and returns the new balance
  public double deposit(double num){
    balance += num;
    return balance;
  }

  // Withdraws money from account and returns the new balance. Returns -1 if withdraw is unsuccessful
  public double withdraw(double num){
    if((balance - num) < 0)
      return -1;
    balance -= num;
    return balance;
  }

  // Accessor method
  public int getID(){
    return checkingAccountNum;
  }
  
  public static int getTotalCheckingAccounts(){
    return totalCheckingAccounts;
  }
}