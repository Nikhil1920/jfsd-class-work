import java.util.*;

class CalculatorWithTests {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            System.out.println("Choose a action");
            System.out.println("1. Add two numbers");
            System.out.println("2. Multiply two numbers");
            System.out.println("3. Divide two numbers");
            System.out.println("4. Subtract two numbers");
            System.out.println("5. Exit");
            int choice = sc.nextInt();

            System.out.println("Enter num 1");
            int num1 = sc.nextInt();

            System.out.println("Enter num 2");
            int num2 = sc.nextInt();

            int result = 0;
            switch (choice) {
                case 1:
                    result = add(num1, num2);
                    break;

                case 2:
                    result = multiply(num1, num2);
                    break;

                case 3:
                    result = divide(num1, num2);
                    break;

                case 4:
                    result = subtract(num1, num2);
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Please Try Again");
                    break;
            }

            System.out.println("Output = " + result);

        }
        sc.close();
    }

    public static int add(int num1, int num2) {
        return num1 + num2;
    }

    public static int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public static int divide(int num1, int num2) {
        return num1 / num2;
    }

    public static int subtract(int num1, int num2) {
        return num1 - num2;
    }
}