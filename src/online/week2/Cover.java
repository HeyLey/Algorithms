package online.week2;

/**
 * По данным n отрезкам необходимо найти множество точек минимальной мощности,
 * для которого каждый из отрезков содержит хотя бы одну из точек.

 В первой строке дано число 1 ≤ n ≤ 100 отрезков.
 Каждая из последующих n строк содержит по два числа 0 ≤ l ≤ r ≤ 10^9, задающих начало и конец отрезка.
 Выведите оптимальное число m точек и сами m точек.

 Sample Input 1:
 3
 1 3
 2 5
 3 6
 Sample Output 1:
 1
 3

 Sample Input 2:
 4
 4 7
 1 3
 2 5
 5 6
 Sample Output 2:
 2
 3 6
 */


import java.io.*;
import java.util.Arrays;

public class Cover {
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
        Segment[] s = new Segment[2 * n];
        int points[] = new int[n];
        int pointsNum = 0;

        for (int i = 0; i < n; i++) {
            s[2 * i] = new Segment(i, 0, nextInt());
            s[2 * i + 1] = new Segment(i, 1, nextInt());
        }

        Arrays.sort(s);

        boolean[] covered = new boolean[n];

        boolean[] visited = new boolean[n];

        for (int i = 0; i < 2 * n; i++) {
            int number = s[i].number;

            if (visited[number]) {
                if (!covered[number]) {
                    points[pointsNum] = s[i].pos;
                    pointsNum++;
                    for (int j = 0; j < n; j++) {
                        if (visited[j]) {
                            covered[j] = true;
                        }
                    }
                }
                visited[number] = false;
            } else {
                visited[number] = true;
            }
        }


        out.println(pointsNum);

        for (int i = 0; i < pointsNum; i++) {
            out.print(points[i] + " ");
        }

        out.close();
    }

    static class Segment implements Comparable<Segment> {

        @Override
        public int compareTo(Segment a) {
            if (pos < a.pos) {
                return -1;
            } else if (pos > a.pos) {
                return 1;
            } else {
                if (type < a.type) {
                    return -1;
                } else if (type > a.type) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        Segment(int number, int type, int pos) {
            this.number = number;
            this.type = type;
            this.pos = pos;
        }

        int number;
        int type;
        int pos;
    }
}