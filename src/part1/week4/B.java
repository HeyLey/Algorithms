package part1.week4;

/**
 * Created by leyla on 04/10/15.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Random;


public class B {
    static StreamTokenizer in;

    static long cur = 0;

    static int nextRand24(int a, int b) {
        cur = (cur * a + b) & 0xffffffffL;
        return (int) (cur >> 8);
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

        long center = find(arr, 0, arr.length - 1, n / 2);

        long dist = 0;
        for (int i = 0; i < n; i++) {
            dist += Math.abs(arr[i] - center);
        }
        System.out.println(dist);
    }

    private static long find(long[] arr, int start, int end, int k) {
        int i = start;
        int j = end;

        long pivot = arr[start + new Random().nextInt(end - start)];

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] >= pivot) {
                j--;
            }

            if (i < j) {
                long tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        int m1 = j;


        i = j + 1;
        j = end;

        while (i <= j) {
            while (i < arr.length && arr[i] == pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }

            if (i < j) {
                long tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        int m2 = i;

        if (m1 < k && k < m2) {
            return pivot;
        } else if (k <= m1) {
            return find(arr, start, m1, k);
        } else {
            return find(arr, m2, end, k);
        }
    }
}

