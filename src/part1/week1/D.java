package part1.week1;

import java.io.*;

class D {

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

        StringBuilder sb = new StringBuilder();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            int next = nextInt();
            data[i] = next;
        }

        for (int i = n - 1; i >= 0; i--) {
            sb.append(data[i]).append(" ");
        }

        out.print(sb);
        out.close();
    }
}

