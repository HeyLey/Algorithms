package part1.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 Зайчик прыгает по прямой просеке, для удобства разделённой на n клеток.
 Клетки пронумерованы по порядку натуральными числами от 1 до n.
 Некоторые клетки заболочены: если зайчик прыгнет на такую клетку, ему несдобровать.
 Некоторые другие клетки просеки поросли вкусной зелёной травой:
 прыгнув на такую клетку, зайчик сможет отдохнуть и подкрепиться.
 Зайчик начинает свой путь из клетки с номером 1 и хочет попасть в клетку с номером n,
 по пути ни разу не провалившись в болото и скушав как можно больше вкусной зелёной травы.
 Конструктивные особенности зайчика таковы, что из клетки с номером k
 он может прыгнуть лишь в клетки с номерами k + 1, k + 3 и k + 5.
 Выясните, какое максимальное количество клеток с травой сможет посетить зайчик на
 своём пути.
 Формат входных данных
 В первой строке входного файла задано число n — количество клеток (2 <= n <= 1000).
 Вторая строка состоит из n символов; i-ый символ соответствует i-ой клетке просеки.
 Символ ‘w’ обозначает болото, символ ‘"’ — зелёную траву,
 а символ ‘.’ соответствует клетке без каких-либо особенностей.
 Гарантируется, что первая и последняя клетки не содержат болот и травы.
 Формат выходных данных
 В первой строке выходного файла выведите одно число — максимальное количество клеток с травой,
 которые зайчик сможет посетить на своём пути.
 Если зайчику не удастся оказаться в клетке с номером n, выведите −1.
 */
public class F {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());
        String path = in.readLine();
        int[] grass = new int[n];
        int[] prev = new int[n];

        for (int i = 1; i < n; i++) {
            char next = path.charAt(i);
            if (next == 'w') {
                grass[i] = Integer.MIN_VALUE;
                continue;
            }
            grass[i] = Integer.MIN_VALUE;

            for (int j : new int[]{1, 3, 5}) {
                int t = i - j;
                if (t < 0) {
                    continue;
                }
                if (grass[t] > grass[i]) {
                    grass[i] = grass[t];
                    prev[i] = t;
                }
            }
            if (next == '"') {
                grass[i] += 1;
            }
        }

        if (grass[n - 1] >= 0) {
            out.println(grass[n - 1]);
            int[] p = new int[n];
            int i = 0;
            int pos = n - 1;
            while (pos > 0) {
                p[i] = pos;
                i++;
                pos = prev[pos];
            }
        } else {
            out.println("-1");
        }
        out.close();
    }
}
