package part1.week3;

/**
 * Created by leyla on 02/10/15.
 */
import java.io.*;
import java.util.Arrays;

public class E {
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

        int s = nextInt();
        int[] a = readArray();
        int[] b = readArray();
        int[] c = readArray();


        int[] bSorted = Arrays.copyOf(b, b.length);
        int[] cSorted = Arrays.copyOf(c, c.length);


        Arrays.sort(bSorted);
        Arrays.sort(cSorted);

        int bans = Integer.MAX_VALUE;
        int cans = Integer.MAX_VALUE;

        for (int i = 0; i < a.length; i++) {
            int s1 = s - a[i];
            int bi = 0;
            int ci = cSorted.length - 1;

            while (bi < b.length && ci >= 0) {
                if (bSorted[bi] + cSorted[ci] == s1) {
                    int bvar = 0;
                    for (int j = 0; j < b.length; j++) {
                        if (b[j] == bSorted[bi]) {
                            bvar = j;
                            break;
                        }
                    }
                    if (bvar < bans) {
                        bans = bvar;
                        for (int j = 0; j < c.length; j++) {
                            if (c[j] == cSorted[ci]) {
                                cans = j;
                                break;
                            }
                        }
                    }
                    bi++;
                    ci--;
                } else if (bSorted[bi] + cSorted[ci] < s1) {
                    bi++;
                } else {
                    ci--;
                }
            }
            if (bans < Integer.MAX_VALUE) {
                System.out.println(i + " " + bans + " " + cans);
                return;
            }
        }

        System.out.println(-1);
    }

    private static int[] readArray() throws IOException {
        int n = nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }
}