package part1.week9;

/*
Дан неориентированный граф. Требуется найти все мосты в нем.
Формат входных данных
Первая строка входного файла содержит два натуральных числа n и m —
количество вершин и ребер графа соответственно (n <= 20 000, m <= 200 000).
Следующие m строк содержат описание ребер по одному на строке.
Ребро номер i описывается двумя натуральными числами b_i, e_i -
номерами концов ребра (1 <=  b_i, e_i <= n).
Формат выходных данных
Первая строка выходного файла должна содержать одно натуральное число b —
количество мостов в заданном графе.
На следующей строке выведите n целых чисел — номера ребер,
которые являются мостами, в возрастающем порядке.
Ребра нумеруются с единицы в том порядке, в котором они заданы во входном файле.
*/
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class B {

    static int timer = 0;
    static boolean[] visited;
    static int tin[];
    static int fup[];
    static boolean bridges[];

    public static void main(String[] args) throws IOException {
        ReadMy in = new ReadMy(System.in);
        PrintWriter out = new PrintWriter(System.out);


        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        int[] xor = new int[m];

        for (int k = 0; k < m; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;

            xor[k] = i ^ j;
            list.get(j).add(k);
            list.get(i).add(k);
        }


        visited = new boolean[n];
        tin = new int[n];
        fup = new int[n];
        bridges = new boolean[m];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, list, xor);
            }
        }

        int bridgesNumber = 0;

        for (int i = 0; i < m; i++) {
            if (bridges[i]) {
                bridgesNumber++;
            }
        }

        out.println(bridgesNumber);

        for (int i = 0; i < m; i++) {
            if (bridges[i]) {
                out.println(i+1);
            }
        }
        out.close();
    }

    static  void dfs(int v, int p, List<List<Integer>> g, int[] xor) {

        visited[v] = true;
        tin[v] = fup[v] = timer++;
        for (int i = 0; i<g.get(v).size(); i++) {
            int edge = g.get(v).get(i);
            int to = v ^ xor[edge];
            if (to == p)  continue;
            if (visited[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v, g, xor);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    bridges[edge] = true;
                }
            }
        }
    }


    static class ReadMy {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        ReadMy(InputStream in) {
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