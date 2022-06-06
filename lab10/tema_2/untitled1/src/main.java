import java.util.Random;

public class main {
    public static void main(String []args) throws InterruptedException
    {
        final int n = 30;

        Random r = new Random();

        int[] vec = new int [n];
        for (int i = 0; i < n; i++)
        {
            vec[i] = r.nextInt() % 10;
        }

        Thread t1 = new Thread(new Thread1(vec, 3));
        Thread t2 = new Thread(new Thread2(vec));
        Thread t3 = new Thread(new Thread3(vec));

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        t3.start();
        t3.join();
    }
}