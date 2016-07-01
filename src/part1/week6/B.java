package part1.week6;

/**
 * Created by leyla on 24/10/15.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class B {
    static StreamTokenizer in;

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

        int a = nextInt();
        int b = nextInt();

        long[][] din = new long[a][b];
        din[0][0] = 1;

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (i - 2 >= 0 && j - 1 >= 0) {
                    din[i][j] += din[i - 2][j - 1];
                }
                if (i - 1 >= 0 && j - 2 >= 0) {
                    din[i][j] += din[i - 1][j - 2];
                }
            }
        }
        System.out.println(din[a - 1][b - 1]);
    }
}