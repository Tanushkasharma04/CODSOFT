import java.util.Scanner;

class Bankaccount{
    private double balance;

    public Bankaccount(double initialBalance){
        this.balance=initialBalance;
    }
    public void deposit(double amount){
        if (amount>0) {
            balance+=amount;
            System.out.println("SUccesfully deposited: $"+amount);
        }else{
            System.out.println("Invalid deposit amount.Please enter a valid amount.");
        }
    }
    public boolean withdraw(double amount){
        if (amount>0 && amount<=balance) {
            balance-=amount;

            System.out.println("Successfully withdrawn: $"+amount);
            return true;
        }else if (amount>balance) {
            System.out.println("Insufficient balance! Transaction failed.");
        }else{
            System.out.println("Invalid withdrawal amount. Please enter a valid amount.");
        }
        return false;
    }
    public double getbalance(){
        return balance;
    }
}
class ATM{
    private Bankaccount account;
    private Scanner scanner;

    public ATM(Bankaccount account){
        this.account=account;
        this.scanner=new Scanner(System.in);
    }
    public void displaymeny(){
        while(true){
            System.out.println("\n===========ATM menu===========");
            System.out.println("1.WITHDRAW");
            System.out.println("2.DEPOSIT");
            System.out.println("3.CHECK BALANCE");
            System.out.println("4.EXIT");
            System.out.println("4.CHOOSE AN OPTION");

            int choice=scanner.nextInt();
            switch (choice) {
                case 1:
                handleWithdraw();
                    break;
                    case 2:
                    handleDeposit();
                        break;
                        case 3:
                        System.out.println("CURRENT BALANCE : $"+account.getbalance());
                            break;
                            case 4:
                            System.out.println("THANK YOU FOR USING THE ATM. Goodbye!");
                            scanner.close();
                            return;
                default:
                System.out.println("INVALID OPTION.PLEASE VHOOSE A VALID OPTION.");
            }
        }
    }
    private void handleWithdraw(){
        System.out.println("ENTER AMOOUNT TO WITHDRAW: $");
        double amount=scanner.nextDouble();
        account.withdraw(amount);
    }
    private void handleDeposit(){
        System.out.println("Enter amount to deposit: $");
        double amount=scanner.nextDouble();
        account.deposit(amount);
    }
}

public class ATM_INTERFACE {
    public static void main(String[] args) {
        Bankaccount userAccount=new Bankaccount(1000);
        ATM atm=new ATM(userAccount);
        atm.displaymeny();
    }
}
