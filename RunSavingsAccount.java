import java.util.Scanner;

class SavingsAccount {
    private double balance = 0.0;
    private static double interestRate = 0.0;

    public SavingsAccount() {
        balance = 0.0;
    }

    public static void setInterestRate(double newRate) {
        interestRate = newRate;
    }

    public static double getInterestRate() {
        return interestRate;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public double withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return amount;
        } else {
            System.out.println("Insufficient balance. Withdrawal canceled.");
            return 0;
        }
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
    }

    public static void showBalance(SavingsAccount account) {
        System.out.println("Current balance: " + account.getBalance());
    }
}

public class RunSavingsAccount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SavingsAccount savings = new SavingsAccount();

        System.out.print("Enter the annual interest rate (e.g., 0.10 for 10%): ");
        double interestRate = scanner.nextDouble();
        SavingsAccount.setInterestRate(interestRate);

        System.out.print("Enter the initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        savings.deposit(initialDeposit);

        System.out.printf("Your balance is %.2f%n", savings.getBalance());

        while (true) {
            System.out.print("Press D for another deposit, W for withdrawal, or Q to quit: ");
            char choice = scanner.next().charAt(0);

            if (choice == 'D' || choice == 'd') {
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                savings.deposit(depositAmount);
                System.out.printf("Your new balance is %.2f%n", savings.getBalance());

                if (savings.getBalance() > 1000) {
                    savings.addInterest();
                    System.out.println("Interest applied. New balance with interest: " + savings.getBalance());
                }
            } else if (choice == 'W' || choice == 'w') {
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = scanner.nextDouble();
                double withdrawn = savings.withdraw(withdrawalAmount);

                if (withdrawn > 0) {
                    System.out.printf("Withdrawn amount: %.2f%n", withdrawn);
                    System.out.printf("Your new balance is %.2f%n", savings.getBalance());
                }
            } else if (choice == 'Q' || choice == 'q') {
                break;
            } else {
                System.out.println("Invalid choice. Please enter D to deposit, W to withdraw, or Q to quit.");
            }
        }
    }
}
