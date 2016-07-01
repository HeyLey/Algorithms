package online.week3;

import java.io.*;

/**
 * Created by leyla on 01/10/15.
 */
public class Podschet {
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
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();
        int[] array = new int[11];

        for (int i = 0; i < n; i++) {
            int val = nextInt();
            array[val] += 1;
        }

        for (int i = 0; i < 11; i++) {
            int j = array[i];
            while(j != 0) {
                out.print(i + " ");
                j--;
            }
        }

        out.close();

    }
}
