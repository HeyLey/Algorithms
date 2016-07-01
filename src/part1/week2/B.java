package part1.week2;

import java.io.*;
import java.util.Arrays;

/**
 * Юный программист Саша написал свою первую тестирующую систему.
 * Он так обрадовался тому, что она скомпилировалась,
 * что решил пригласить школьных друзей на свой собственный контест.
 * Но в конце тура выяснилось, что система не умеет сортировать команды в таблице результатов.
 * Помогите Саше реализовать эту сортировку.
 * <p/>
 * Команды упорядочиваются по правилам ACM:
 * ∙ по количеству решённых задач в порядке убывания;
 * ∙ при равенстве количества решённых задач — по штрафному времени в порядке возрастания;
 * ∙ при прочих равных — по номеру команды в порядке возрастания.
 * <p/>
 * Формат входных данных
 * Первая строка содержит натуральное число n (1 <= n <= 100 000) — количество команд,
 * участвующих в контесте. В i-й из следующих n строк записано количество решенных задач
 * S (0 <= S <= 100) и штрафное время T (0 <= T <= 100 000) команды с номером i.
 * <p/>
 * Формат выходных данных
 * В выходной файл выведите n чисел — номера команд в отсортированном порядке
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

        int n = nextInt();

        Command[] commands = new Command[n];

        for (int i = 0; i < n; i++) {
            commands[i] = new Command();
            commands[i].number = i + 1;
            commands[i].points = nextInt();
            commands[i].fine = nextInt();
        }

        Arrays.sort(commands);

        for (int i = 0; i < n; i++) {
            out.print(commands[i].number + " ");
        }

        out.close();
    }

    static class Command implements Comparable<Command> {

        @Override
        public int compareTo(Command a) {

            if (points != a.points) {

                if (points < a.points) {
                    return 1;
                } else {
                    return -1;
                }

            } else if (fine != a.fine) {

                if (fine > a.fine) {
                    return 1;
                } else {
                    return -1;
                }

            } else {

                if (number > a.number) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        int number;
        int points;
        int fine;
    }
}

