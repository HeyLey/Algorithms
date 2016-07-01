package part1.week8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class H {

    public static void main(String[] args) throws IOException {
        RdrMy in = new RdrMy(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;

            list.get(j).add(i);
        }


        int visited[] = new int[n];

        ArrayList<Integer> result = new ArrayList<>();

        for (int v = 0; v < n; v++) {
            if (visited[v] != 0) {
                continue;
            }
            int cycle = dfs(list, visited, v, result);

            if (cycle == 1) {
                System.out.println(-1);
                return;
            }
        }
        for (int i = 0; i < result.size(); i++) {
            out.print(result.get(i) + " ");
        }
        out.println();
        out.close();
    }

    public static int dfs(List<List<Integer>> list, int[] visited, int v, ArrayList<Integer> result) {
        if (visited[v] == 1) {
            return 1;
        }
        if (visited[v] == 2) {
            return 0;
        }

        visited[v] = 1;

        for (int i : list.get(v)) {
            if (dfs(list, visited, i, result) != 0) {
                return 1;
            }
        }
        visited[v] = 2;
        result.add(v + 1);
        return 0;
    }


    static class RdrMy {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        RdrMy(InputStream in) {
            this.in = new BufferedInputStream(in, bufSize);
        }

        int nextInt() throws IOException {
            int c;
            while ((c = nextChar()) <= 32)
                ;
            int x = 0, sign = 1;
            if (c == '-') {
                sign = -1;
                c = nextChar();
            }
            while (c >= '0') {
                x = x * 10 + (c - '0');
                c = nextChar();
            }
            return x * sign;
        }

        StringBuilder _buf = new StringBuilder();

        String nextWord() throws IOException {
            int c;
            _buf.setLength(0);
            while ((c = nextChar()) <= 32 && c != -1)
                ;
            if (c == -1)
                return null;
            while (c > 32) {
                _buf.append((char) c);
                c = nextChar();
            }
            return _buf.toString();
        }

        int bn = bufSize, k = bufSize;

        int nextChar() throws IOException {
            if (bn == k) {
                k = in.read(b, 0, bufSize);
                bn = 0;
            }
            return bn >= k ? -1 : b[bn++];
        }

        int nextNotSpace() throws IOException {
            int ch;
            while ((ch = nextChar()) <= 32 && ch != -1)
                ;
            return ch;
        }
    }
}