package part1.week5;
/*
Палиндромом называется строка, которая читается одинаково как слева направо,
так и справа налево. Требуется найти самый длинный палиндром 𝑃, получающийся изданной строки S
удалением любого (возможно, нулевого) количества символов.
Формат входных данных
Входной файл содержит строчку S, cостоящую из строчных латинских букв (a–z).
Длина 𝑆 не превышает 1 000 символов.
Формат выходных данных
Выходной файл должен содержать искомый палиндром. Если таких палиндромов несколько, выведите любой из них.
 */

import java.io.*;

public class K {
    static BufferedReader in;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s = in.readLine();
        String res = "";

        int j = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
                char c1 = s.charAt(i);
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    res += c1;
                    j--;
                    continue;
                } else {

                }
                if (j == i) {
                    res += c1;
                    break ;
                }
        }
        out.println(res);
        out.close();
    }

}
