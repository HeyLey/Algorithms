package part1.week4;

/**
 * Created by leyla on 04/10/15.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class C {
    static StreamTokenizer in;

    static long cur = 0;

    static int nextRand24(int a, int b) {
        cur = (cur * a + b) & 0xffffffffL;
        return (int) (cur >> 8);
    }

    public static long GCD(long a, long b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }


    static long nextRand32(int a, int b) {
        long av = nextRand24(a, b);
        long bv = nextRand24(a, b);
        return (av << 8) ^ bv;
    }


    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();
        int a = nextInt();
        int b = nextInt();

        long arr[] = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextRand32(a, b);
        }


        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        long g = GCD(sum, n);

        sum /= g;
        n /= g;

        System.out.println(sum + "/" + n);
    }

}
