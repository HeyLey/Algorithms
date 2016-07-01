package part1.week5;

import java.io.*;

/*
В одном из горнолыжных курортов Италии проводятся соревнования по горнолыжному спуску.
Каждому спортсмену предстоит скатиться с горы на лыжах.
На любом этапе спуска участник получает определенное число очков.
После прохождения трассы очки суммируются.
Участник, набирающий наибольшее количество очков, выигрывает.
Гора представляет собой треугольник, в качестве элементов которого выступают целые числа —
очки за прохождение этапа. На каждом уровне спортсмену предоставляется выбор —
двигаться вниз-влево или вниз-вправо. Начало спуска — в самой высокой точке горы,
конец в одной из самых низких.
1
4 3
5 6 7
8 9 0 9
Требуется найти максимальное количество очков, которое может набрать спортсмен.
Формат входных данных
Во входном файле содержится целое число n — число этапов (1 <= n <= 100),
далее n строк, каждая из которых характеризует свой уровень.
В строке с номером i содержится ровно i целых чисел:
a_1, a_2,..., a_i (−100 <= a_k <= 100, 1 <= k <= i) — количество очков в каждой из позиций.
Формат выходных данных
В результирующем файле должно находиться искомое целое число.
 */
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
        PrintWriter out = new PrintWriter(System.out);

        int n = nextInt();
        int arr[][] = new int[n][n];
        int scores[][] = new int[n][n];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int next = nextInt();
                arr[i - 1][j] = next;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    if (i - 1 == 0) {
                        scores[i - 1][j] = arr[i - 1][j];
                    } else {
                        scores[i - 1][j] = scores[i - 2][j] + arr[i - 1][j];
                    }
                } else if (j == i - 1 && i != 1) {
                    scores[i - 1][j] = scores[i - 2][j - 1] + arr[i - 1][j];
                } else {
                    scores[i - 1][j] = Math.max(scores[i - 2][j - 1], scores[i - 2][j]) + arr[i - 1][j];
                }

            }
        }

        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(scores[i][j] + " ");
            }
            System.out.println();
        }*/

        int res = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (scores[n - 1][i]  > res) {
                res = scores[n - 1][i];
            }
        }

        System.out.println(res);

    }
}