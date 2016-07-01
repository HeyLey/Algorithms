package part1.week3;

/**
 * Created by leyla on 02/10/15.
 */
import java.io.*;
import java.util.*;

public class G {
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
        int k = nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }


        final long[] prefix = new long[n];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            prefix[i] = sum;
        }

        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (prefix[b] > prefix[a]) {
                    return 1;
                } else if (prefix[b] < prefix[a]) {
                    return -1;
                } else {
                    return 0;

                }
            }
        });

        sum = 0;
        Map<Integer, Integer> stat = new HashMap<Integer, Integer>();

        int r = -1;

        int maxEnd = 0;

        long maxSum = Long.MIN_VALUE;
        int start = 0;
        int end = 0;

        for (int l = 0; l < a.length; l++) {
            while (stat.size() < k) {
                r++;
                if (r == a.length) {
                    break;
                }
                int element = a[r];
                if (stat.containsKey(element)) {
                    stat.put(element, stat.get(element) + 1);
                } else {
                    stat.put(element, 1);
                }
            }
            if (r == a.length) {
                break;
            }
            while (indexes[maxEnd] < r) {
                maxEnd++;
            }

            long t = prefix[indexes[maxEnd]] - sum;
            if (maxSum < t) {
                maxSum = t;
                start = l + 1;
                end = indexes[maxEnd] + 1;
            }

            int element = a[l];
            if (stat.get(element) == 1) {
                stat.remove(element);
            } else {
                stat.put(element, stat.get(element) - 1);
            }
            sum += a[l];
        }

        if (maxSum > Long.MIN_VALUE) {
            System.out.println(maxSum);
            System.out.println(start + " " + end);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }
}