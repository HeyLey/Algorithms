package part1.week6;

import java.io.*;

public class A {
    static StreamTokenizer in;

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int t[] = new int[130];

        int sum = 0;
        for (int i = 0; i < t.length; i++) {
            sum += (i + 1) * (i + 2) / 2;
            t[i] = sum;
        }

        int n = nextInt();

        int[] din = new int[300001];
        din[0] = 0;
        int lastIndex = 0;

        for (int k = 0; k < n; k++) {
            int s = nextInt();
            for (int i = lastIndex + 1; i <= s; i++) {
                din[i] = Integer.MAX_VALUE;
                for (int h : t) {
                    int prev = i - h;
                    if (prev < 0) {
                        break;
                    }
                    if (din[i] > din[prev] + 1) {
                        din[i] = din[prev] + 1;
                    }
                }
                lastIndex = i;
            }
            System.out.println(din[s]);
        }
    }
}
