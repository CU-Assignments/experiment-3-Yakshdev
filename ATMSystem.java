import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATMSystem {
    private static final int CORRECT_PIN = 1234;
    private static double balance = 3000;

    public static void verifyPIN(int pin) throws InvalidPinException {
        if (pin != CORRECT_PIN) {
            throw new InvalidPinException("Error: Invalid PIN entered.");
        }
    }

    public static void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Error: Insufficient balance.");
        }
        balance -= amount;
        System.out.println("Withdrawal Successful! Amount Withdrawn: " + amount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();
            verifyPIN(pin);

            System.out.print("Enter Withdrawal Amount: ");
            double amount = sc.nextDouble();

            withdraw(amount);
        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter numbers only.");
        } finally {
            System.out.println("Current Balance: " + balance);
            sc.close();
        }
    }
}
