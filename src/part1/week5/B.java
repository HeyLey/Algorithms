package part1.week5;

import java.io.*;

/**
 * Отряду нужно пересечь прямоугольное поле размера m × n квадратов,
 * двигаясь из левого верхнего угла в правый нижний и перемещаясь между соседними квадратами
 * только в двух направлениях — вправо и вниз.
 * Поле не очень ровное, но у отряда есть карта, на которой отмечена высота каждого квадрата.
 * Опасность перехода с квадрата высоты ℎ1 на соседний квадрат высоты ℎ2 оценивается числом |ℎ2 − ℎ1|;
 * опасность всех переходов в пути суммируется.
 * Выясните, какова минимальная опасность пути из квадрата (1, 1) в квадрат (m, n).

 Формат входных данных
 В первой строке входного файла заданы два числа m и n через пробел (1 <= m, n <= 100).
 В следующих n строках записано по m чисел в каждой;
 i-ое число j-ой из этих строк соответствует высоте квадрата (i, j).
 Все высоты — целые числа в диапазоне от 1 до 100, включительно.
 Формат выходных данных
 Выведите в выходной файл одно число — минимальную опасность пути из квадрата (1, 1) в квадрат (m, n).
 */
public class B {

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

        int m = nextInt();
        int n = nextInt();

        int[][] field = new int[n][m];
        int[][] dangers = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    if (j == 0) {
                        dangers[i][j] = 0;
                    } else {
                        dangers[i][j] = Math.abs(field[i][j] - field[i][j - 1]) + dangers[i][j-1];
                    }
                } else if (j == 0) {
                    dangers[i][j] = Math.abs(field[i][j] - field[i - 1][j]) + dangers[i - 1][j];
                } else {
                    dangers[i][j] = Math.min(Math.abs(field[i][j] - field[i - 1][j]) + dangers[i - 1][j], Math.abs(field[i][j] - field[i][j - 1]) + dangers[i][j - 1]);
                }
            }
        }

        out.println(dangers[n - 1][m - 1]);
        out.close();
    }
}
