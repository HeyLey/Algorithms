package online.week2;

/*
Восстановите строку по её коду и беспрефиксному коду символов.
В первой строке входного файла заданы два целых числа k и l через пробел —
количество различных букв, встречающихся в строке, и размер получившейся закодированной строки, соответственно.
В следующих k строках записаны коды букв в формате "letter: code". Ни один код не является префиксом другого.
Буквы могут быть перечислены в любом порядке. В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
каждая из этих букв встречается в строке хотя бы один раз. Наконец, в последней строке записана закодированная строка.
Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная строка имеет минимальный возможный размер.
В первой строке выходного файла выведите строку s. Она должна состоять из строчных букв латинского алфавита.
Гарантируется, что длина правильного ответа не превосходит 10^4 символов.

Sample Input 1:
1 1
a: 0
0
Sample Output 1:
a

Sample Input 2:
4 14
a: 0
b: 10
c: 110
d: 111
01001100100111

Sample Output 2:
abacabad
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CodeHufInv{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] split = line.split(" ");
        int k = Integer.parseInt(split[0]);
        int l = Integer.parseInt(split[1]);

        List<Character> chars = new ArrayList<Character>();
        List<String> codes = new ArrayList<String>();

        for (int i = 0; i < k; i++) {
            line = br.readLine();
            split = line.split(" ");
            char ch = split[0].charAt(0);
            String code = split[1];
            chars.add(ch);
            codes.add(code);
        }

        line = br.readLine();

        int start = 0;
        StringBuilder result = new StringBuilder();

        while (start < line.length()) {
            for (int i = 0; i < codes.size(); i++) {
                String code = codes.get(i);
                if (line.startsWith(code, start)) {
                    start += code.length();
                    result.append(chars.get(i));
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
