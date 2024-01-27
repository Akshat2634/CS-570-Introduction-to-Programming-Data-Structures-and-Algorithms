// Name - AKSHAT SAHU
// CS570 - C
import static java.lang.Math.log;

public class TimeComplexity {
    public static void method1(int n) { // O(n^2)
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Operation " + counter);
                counter++;
            }
        }
    }

    public static void method2(int n) { // O(n^3)
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.println("Operation " + counter);
                    counter++;
                }
            }
        }
    }

    public static void method3(int n) { // O(log n)
        int counter = 0;
        for (int i = 1; i < n; i = i * 2) {
            System.out.println("Operation " + counter);
            counter++;
        }
    }

    public static void method4(int n) { // O(nlog n)
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j = j * 2) {
                System.out.println("Operation " + counter);
                counter++;
            }
        }
    }

    public static void method5(int n) { // O(log log n )
        int counter = 0;
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < log(i) + 1; j++) {
                System.out.println("Operation " + counter);
                counter++;
            }
        }
    }
}
