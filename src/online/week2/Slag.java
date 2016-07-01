package online.week2;

import java.io.*;

public class Slag {
    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {

        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();

        int[] s = new int[n];
        int num = 0;

        for (int i = 1; i + i + 1 <= n; i++) {
            s[num] = i;
            num++;
            n -= i;
        }
        s[num] = n;
        num++;

        out.println(num);
        for (int i = 0; i < num; i++) {
            out.print(s[i] + " ");
        }
        out.println();

        out.close();
    }

}