package part1.week4;

/**
 * Created by leyla on 04/10/15.
 */
import java.io.*;

public class D {
    static StreamTokenizer in;

    static long cur = 0;

    static int nextRand24(int a, int b) {
        cur = (cur * a + b) & 0xffffffffL;
        return (int) (cur >> 8);
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

        int n = 16777216;
        int m = nextInt();
        int q = nextInt();
        int a = nextInt();
        int b = nextInt();

        int sum[] = new int[n + 2];
        for (int i = 0; i < m; i++) {
            int add = nextRand24(a, b);
            int f = nextRand24(a, b);
            int s = nextRand24(a, b);
            int l = Math.min(f, s);
            int r = Math.max(f, s);

            sum[l + 1] += add;
            sum[r + 2] -= add;
        }

        int curSum = 0;
        int curAdd = 0;

        for (int i = 0; i < n; i++) {
            curAdd = curAdd + sum[i + 1];
            curSum = curSum + curAdd;
            sum[i + 1] = curSum;
        }

        long result = 0;

        for (int i = 0; i < q; i++) {
            int f = nextRand24(a, b);
            int s = nextRand24(a, b);
            int l = Math.min(f, s);
            int r = Math.max(f, s);

            result = norm(result + sum[r + 1] - sum[l]);
        }
        System.out.println(result);
    }

    private static long norm(long v) {
        while (v < 0) {
            v += 0x100000000L;
        }
        return v & 0xffffffffL;
    }
}