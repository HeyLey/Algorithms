package part1.week4;

/**
 * Created by leyla on 04/10/15.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class E {
    static long inversions = 0;
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

    static int[] mergeSort(int[] m) {
        if (m.length <= 1) {
            return m;
        }
        int middle = m.length / 2;

        int[] left = new int[middle];
        int[] right = new int[m.length - middle];
        System.arraycopy(m, 0, left, 0, middle);
        System.arraycopy(m, middle, right, 0, m.length - middle);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                inversions += left.length - i;
                j++;
            }
            k++;
        }
        while (i < left.length) {
            result[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            result[k] = right[j];
            j++;
            k++;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = nextInt();
        int m = nextInt();
        int a = nextInt();
        int b = nextInt();

        int array[] = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = nextRand24(a, b) % m;
        }
        mergeSort(array);
        System.out.println(inversions);
    }
}