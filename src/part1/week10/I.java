package part1.week10;

import java.io.*;

public class I {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int from = toNumber(in.readLine());
        int to = toNumber(in.readLine());


        boolean[] visited = new boolean[64];
        int[] prev = new int[64];

        IntList l = new IntList(64);
        IntList next = new IntList(64);
        l.add(to);
        visited[to] = true;
        while (l.size > 0) {
            for (int j = 0; j < l.size; j++) {
                int u = l.data[j];
                int x = u % 8;
                int y = u / 8;
                add(x - 1, y - 2, visited, prev, u, next);
                add(x - 1, y + 2, visited, prev, u, next);
                add(x + 1, y - 2, visited, prev, u, next);
                add(x + 1, y + 2, visited, prev, u, next);
                add(x - 2, y - 1, visited, prev, u, next);
                add(x - 2, y + 1, visited, prev, u, next);
                add(x + 2, y - 1, visited, prev, u, next);
                add(x + 2, y + 1, visited, prev, u, next);
            }
            IntList tmp = l;
            l = next;
            next = tmp;
            next.size = 0;
        }

        int p = from;

        while (p != to) {
            System.out.println(toStr(p));
            p = prev[p];
        }
        System.out.println(toStr(to));

    }

    private static String toStr(int u) {
        int x = u % 8;
        int y = u / 8;
        return new String(new char[]{(char)('a' + x),  (char)('1' + y)});
    }

    private static void add(int x, int y, boolean[] visited, int[] prev, int u, IntList next) {
        if (x < 0 || x >= 8) {
            return;
        }
        if (y < 0 || y >= 8) {
            return;
        }
        int i = toIndex(x, y);
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        next.add(i);
        prev[i] = u;
    }

    private static int toNumber(String s) {
        int x = s.charAt(0) - 'a';
        int y = s.charAt(1) - '1';
        return toIndex(x, y);
    }

    private static int toIndex(int x, int y) {
        return x + y * 8;
    }

    static class IntList {
        int[] data;
        int size;

        IntList(int n) {
            data = new int[n];
            size = 0;
        }

        public void add(int v) {
            data[size] = v;
            size++;
        }
    }

}