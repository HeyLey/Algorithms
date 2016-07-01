package part1.week5;

/**
 * Created by leyla on 11/10/15.
 */

import java.io.*;

public class C {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());
        String path = in.readLine();
        int[] grass = new int[n];
        int[] hops = new int[n];
        int[] prev = new int[n];

        for (int i = 1; i < n; i++) {
            char next = path.charAt(i);
            if (next == 'w') {
                hops[i] = 1000;
                continue;
            }
            hops[i] = 1000;

            for (int j : new int[]{2, 3, 6}) {
                int t = i - j;
                if (t < 0) {
                    continue;
                }
                if (hops[t] + 1 < hops[i] || (hops[t] + 1 == hops[i] && grass[t] > grass[i])) {
                    hops[i] = hops[t] + 1;
                    grass[i] = grass[t];
                    prev[i] = t;
                }
            }
            if (next == '"') {
                grass[i] += 1;
            }
        }

        if (hops[n - 1] < 1000) {
            out.println(hops[n - 1] + " " + grass[n - 1]);
            int[] p = new int[n];
            int i = 0;
            int pos = n - 1;
            while (pos > 0) {
                p[i] = pos;
                i++;
                pos = prev[pos];
            }
            for (int j = i; j >= 0; j--) {
                out.print((p[j] + 1) + " ");
            }
            out.println();
        } else {
            out.println("-1");
        }
        out.close();
    }
}

