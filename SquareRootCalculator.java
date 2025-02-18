import java.util.Scanner;

public class SquareRootCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter a number: ");
            double num = sc.nextDouble();

            if (num < 0) {
                throw new IllegalArgumentException("Error: Cannot calculate the square root of a negative number.");
            }

            double result = Math.sqrt(num);
            System.out.println("Square root: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter a numeric value.");
        } finally {
            sc.close();
        }
    }
}
