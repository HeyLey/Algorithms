package part1.week6;

import java.io.*;

public class H {
    static StreamTokenizer in;

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

        int a[][] = new int[n][n];
        int best[][] = new int[n][524288];
        int prev[][] = new int[n][524288];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = nextInt();
            }
        }

        int toVisit = 0;

        for (int i = 0; i < n; i++) {
            toVisit |= 1 << i;
        }

        int bestCost = Integer.MAX_VALUE;
        int bestEnd = 0;

        for (int i = 0; i < n; i++) {
            int t = toVisit & ~(1 << i);
            int cost = bestPath(a, i, t, n, best, prev);
            if (cost < bestCost) {
                bestCost = cost;
                bestEnd = i;
            }
        }
        System.out.println(bestCost);

        int[] path = new int[n];
        path[n - 1] = bestEnd;

        int p = bestEnd;
        int t = toVisit & ~(1 << p);

        for (int i = n - 2; i >= 0; i--) {
            int next = prev[p][t] - 1;
            path[i] = next;
            p = next;
            t = t & ~(1 << p);
        }
        for (int i = 0; i < n; i++) {
            System.out.print((path[i] + 1)  + " ");
        }
        System.out.println();
    }

    private static int bestPath(int[][] a, int end, int toVisit, int n, int[][] best, int[][] prev) {
        if (toVisit == 0) {
            return 0;
        }
        if (prev[end][toVisit] > 0) {
            return best[end][toVisit];
        }
        int bestCost = Integer.MAX_VALUE;
        int bestPrev = 0;
        for (int i = 0; i < n; i++) {
            if ((toVisit & (1 << i)) != 0) {
                int t = toVisit & ~(1 << i);
                int cost = bestPath(a, i, t, n, best, prev) + a[i][end];
                if (cost < bestCost) {
                    bestCost = cost;
                    bestPrev = i;
                }
            }
        }

        prev[end][toVisit] = bestPrev + 1;
        best[end][toVisit] = bestCost;
        return bestCost;
    }
}