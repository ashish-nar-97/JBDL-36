import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorialMT extends Thread {

    int number;

    public static void main(String[] args) {

        Integer[] numbers = {10000, 20000, 50000, 30000, 43000, 50000, 65000, 15000, 42000};
        Integer[] shortNumbers = {1, 2, 3, 4, 5, 6, 7, 8};
        List<FactorialMT> threads = new ArrayList<>();

        long start = System.currentTimeMillis();
        Arrays.stream(numbers)
                .forEach(x -> {
                    FactorialMT thread = new FactorialMT(x);
                    thread.start();
                    threads.add(thread);

                });

        threads.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // t1 = 1
        // t2 = 5
        // t3 = 3
        // t4 = 15

        long end = System.currentTimeMillis();

        System.out.println("Total Time : " + (end - start));
    }

    public FactorialMT(int number) {
        this.number = number;
    }

    @Override
    public void run() {
//        System.out.println("Inside run function : Thread = "+currentThread());
        BigInteger result = calculateFactorial(this.number);
        System.out.println("Thread : " + currentThread() + " Result : " + result);
    }

    private static BigInteger calculateFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
