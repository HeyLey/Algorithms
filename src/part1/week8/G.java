package part1.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String[] l1 = in.readLine().split(" ");

        int w = Integer.parseInt(l1[0]);
        int h = Integer.parseInt(l1[1]);
        int x1 = Integer.parseInt(l1[2]) - 1;
        int y1 = Integer.parseInt(l1[3]) - 1;
        int x2 = Integer.parseInt(l1[4]) - 1;
        int y2 = Integer.parseInt(l1[5]) - 1;

        String[] data = new String[h];

        for (int i = 0; i < h; i++) {
            data[i] = in.readLine();
        }
        int[][] time = new int[h][w];
        int[][] dirx = new int[h][w];
        int[][] diry = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                time[i][j] = -1;
            }
        }
        time[y2][x2] = 0;
        ArrayList<Integer> wave = new ArrayList<>();
        wave.add(x2 + y2 * 10000);

        while (wave.size() > 0) {
            ArrayList<Integer> next = new ArrayList<>();
            for (int l : wave) {
                int x = l % 10000;
                int y = l / 10000;

                int t = time[y][x] + 1;

                addNext(data, time, dirx, diry, next, x - 1, y, t,  1, 0);
                addNext(data, time, dirx, diry, next, x + 1, y, t, -1, 0);
                addNext(data, time, dirx, diry, next, x, y - 1, t, 0, 1);
                addNext(data, time, dirx, diry, next, x, y + 1, t, 0, -1);
            }
            wave = next;
        }

        if (time[y1][x1] == -1) {
            out.println("NO");
        } else {
            out.println("YES");

            int x = x1;
            int y = y1;
            while (time[y][x] != 0) {
                out.println((x + 1) + " " + (y + 1));
                int dx = dirx[y][x];
                int dy = diry[y][x];
                x += dx;
                y += dy;
            }
            out.println((x + 1) + " " + (y + 1));
        }
        out.close();
    }

    private static void addNext(String[] data,
                                int[][] time,
                                int[][] dirx,
                                int[][] diry,
                                ArrayList<Integer> next,
                                int x,
                                int y,
                                int t,
                                int dx,
                                int dy) {
        if (y < 0 || y >= time.length) {
            return;
        }
        if (x < 0 || x >= time[0].length) {
            return;
        }

        if (data[y].charAt(x) == '*') {
            return;
        }

        if (time[y][x] == -1) {
            time[y][x] = t;
            dirx[y][x] = dx;
            diry[y][x] = dy;
            next.add(x + y * 10000);
        }
    }
}