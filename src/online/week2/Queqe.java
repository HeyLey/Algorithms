package online.week2;

/**
 * Created by leyla on 30/09/15.
 */
import java.io.*;


public class Queqe {
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

        int[] a = new int[100001];
        int size = 0;

        for (int i = 0; i < n; i++) {
            String s = next();
            if ("Insert".equals(s)) {
                int k = nextInt();
                a[size] = k;
                down(a, size);
                size++;
            } else {
                System.out.println(a[0]);
                size--;
                if (size > 0) {
                    a[0] = a[size];
                    up(a, size);
                }
            }
        }
    }

    private static void up(int[] a, int size) {
        int k = 0;
        while (k < size) {
            int child1 = k * 2 + 1;
            if (child1 >= size) {
                break;
            }
            int child2 = k * 2 + 2;
            if (child2 == size || a[child1] > a[child2]) {
                if (a[child1] > a[k]) {
                    int tmp = a[child1];
                    a[child1] = a[k];
                    a[k] = tmp;
                    k = child1;
                } else {
                    break;
                }
            } else {
                if (a[child2] > a[k]) {
                    int tmp = a[child2];
                    a[child2] = a[k];
                    a[k] = tmp;
                    k = child2;
                } else {
                    break;
                }
            }
        }
    }

    private static void down(int[] a, int k) {
        while (k > 0) {
            int parent = (k - 1) / 2;
            if (a[parent] < a[k]) {
                int tmp = a[parent];
                a[parent] = a[k];
                a[k] = tmp;
                k = parent;
            } else {
                break;
            }
        }
    }
}