package online.week3;

import java.io.*;


/**
 * Created by leyla on 01/10/15.
 */
public class BinarySearch {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static int rank(int key, int[] array) {
        return rank(key, array, 0, array.length - 1);
    }

    public static int rank(int key, int[] array, int low, int high) {
        if (low > high) {
            return -1;
        }
        int middle = low + (high - low) / 2;
        if (key < array[middle]) {
            return rank(key, array, low, middle - 1);
        } else if (key > array[middle]) {
            return rank(key, array, middle + 1, high);
        }
        else {
            return middle + 1;
        }
    }

    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = nextInt();
        }

        int k = nextInt();
        int[] keys = new int[k];

        for (int i = 0; i < k; i++) {
            keys[i] = nextInt();
        }

        for (int i = 0; i < k; i++) {
            int key = keys[i];
            int result = rank(key, array);
            out.print(result + " ");
        }

        out.close();
    }
}
