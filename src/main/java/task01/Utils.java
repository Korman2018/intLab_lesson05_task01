package task01;

public class Utils {
    public static long computeFactorial(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number can't be less than zero");
        } else if (n == 0) {
            return 1;
        }

        return n * computeFactorial(n - 1);
    }

    public static String concatenateWords(String first, String second) {
        return first.concat(second);
    }
}
