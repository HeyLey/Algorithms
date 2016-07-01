package part1.week6;

import java.io.*;
import java.util.*;

public class I {
    static StreamTokenizer in;
    static int singleCost[];
    static Nabor[] nabors;

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

        int n = nextInt();
        singleCost = new int[n];

        for (int i = 0; i < n; i++) {
            singleCost[i] = nextInt();
        }

        int m = nextInt();
        nabors = new Nabor[m];

        for (int i = 0; i < m; i++) {
            int cost = nextInt();
            int sz = nextInt();
            int[] values = new int[sz];
            for (int k = 0; k < sz; k++) {
                values[k] = nextInt() - 1;
            }
            nabors[i] = new Nabor(cost, values);
        }

        int needSz = nextInt();
        int needP = 0;

        for (int i = 0; i < needSz; i++) {
            int v = nextInt();
            needP |= 1 << (v - 1);
        }

        int[] cache = new int[1 << n];
        Arrays.fill(cache, -1);
        cache[0] = 0;

        int best = getBest(needP, cache);
        System.out.println(best);
    }

    private static int getBest(int needP, int[] cache) {
        if (cache[needP] != -1) {
            return cache[needP];
        }
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < nabors.length; i++) {
            int nextp = needP;
            for (int c: nabors[i].coins) {
                nextp &= ~(1 << c);
            }
            if (nextp == needP) {
                continue;
            }
            int t = getBest(nextp, cache);
            if (best > t + nabors[i].cost) {
                best = t + nabors[i].cost;
            }
        }
        for (int i = 0; i < singleCost.length; i++) {
            int nextp = needP;

            nextp &= ~(1 << i);

            if (nextp == needP) {
                continue;
            }

            int t = getBest(nextp, cache);
            if (best > t + singleCost[i]) {
                best = t + singleCost[i];
            }
        }
        cache[needP] = best;
        return best;
    }

    static class Nabor {
        int cost;
        int[] coins;

        public Nabor(int c, int[] values) {
            cost = c;
            coins = values;
        }
    }
}