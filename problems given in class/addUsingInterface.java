interface add {
    public void addTwoNum(int num1, int num2);
}

class MathOperations implements add {
    public void addTwoNum(int num1, int num2) {
        int sum = num1 + num2;
        System.out.println("Sum of two numbers is " + sum);
    }
}

class Main {
    public static void main(String[] args) {
        MathOperations mathOperations = new MathOperations();
        mathOperations.addTwoNum(6, 16);
    }
}