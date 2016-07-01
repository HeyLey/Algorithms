package online.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by leyla on 22/10/15.
 */
public class Trap {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());
        String trap = in.readLine();
        int[] max = new int[n];
        int[] prev = new int[n];

        for (int i = 1; i < n; i++) {
            int next = trap.charAt(i) - '0';
            max[i] = Integer.MIN_VALUE;

            for (int j : new int[]{1, 2}) {
                int t = i - j;
                if (t < 0) {
                    continue;
                }
                if (max[t] > max[i]) {
                    max[i] = max[t];
                    prev[i] = t;
                }
            }
                max[i] += prev[i-1];
        }

        if (max[n - 1] >= 0) {
            out.println(max[n - 1]);
            int[] p = new int[n];
            int i = 0;
            int pos = n - 1;
            while (pos > 0) {
                p[i] = pos;
                i++;
                pos = prev[pos];
            }
        }
        out.close();
    }
}
