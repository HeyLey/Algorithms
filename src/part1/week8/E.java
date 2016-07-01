package part1.week8;

/*
В университетском клубе любителей кошек зарегистрировано 𝑛 членов.
Естественно, что некоторые из членов клуба знакомы друг с другом.
Нужно сосчитать, сколькими способами можно выбрать из них троих,
которые могли бы свободно общаться (то есть, любые два из которых знакомы между собой).

Формат входных данных
В первой строке входного файла заданы числа n и m (1 <= n <= 1000, 1 <= m <= 30000),
где m обозначает общее число знакомств.
В последующих m строках идут пары чисел a_i b_i,
обозначающие, что a_i знаком с b_i.
Информация об одном знакомстве может быть записана несколько раз,
причем даже в разном порядке (как (x, y) так и (y, x)).
Формат выходных данных
В выходной файл необходимо вывести количество способов выбрать троих
попарно знакомых друг с другом людей из клуба.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class E {
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
        int m = nextInt();

        boolean a[][] = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            int v = nextInt() - 1;
            int u = nextInt() - 1;
            a[v][u] = true;
            a[u][v] = true;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!a[i][j]) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (!a[i][k]) {
                        continue;
                    }
                    if (!a[j][k]) {
                        continue;
                    }
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}