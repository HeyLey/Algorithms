package part1.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
В постфиксной записи (или обратной польской записи) операция записывается после двух операндов.
Например, сумма двух чисел A и B записывается как A B +.
Запись B C + D * обозначает привычое нам (B + C) * D,
а запись A B C + D * + означает A + (B + C) * D.
Достоинство постфиксной записи в том, что она не требует скобок
и дополнительных соглашений о приоритете операторов для своего чтения.
Дано выражение в обратной польской записи. Определите его значение.
Формат входных данных:
В единственной строке записано выражение в постфиксной записи,
содержащее однозначные числа и операции +, −, *.
Строка содержит не более 100 чисел и операций.
Формат выходных данных:
Необходимо вывести значение записанного выражения.
Гарантируется, что результат выражения, а также результаты всех промежуточных вычислений
по модулю меньше 2^(31).
 */
public class B {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        String[] array = expression.split(" ");

        Staсk stack = new Staсk(100);
        int temp, e1, e2;

        for (int i = 0; i < array.length; i++) {
            String s = array[i];
            if (s.equals("+")) {
                e1 = stack.pop();
                e2 = stack.pop();
                temp = e1 + e2;
                stack.push(temp);

            } else if (s.equals("-")) {
                e1 = stack.pop();
                e2 = stack.pop();
                temp = e2 - e1;
                stack.push(temp);

            } else if (s.equals("*")) {
                e1 = stack.pop();
                e2 = stack.pop();
                temp = e2 * e1;
                stack.push(temp);
            } else {
                int num = Integer.parseInt(s);
                stack.push(num);
            }
        }

        System.out.println(stack.pop());
    }

}

class Staсk {

    int size;
    int[] data;

    Staсk(int capacity) {
        data = new int[capacity];
    }

    void push(int value) {
        data[size++] = value;
    }

    int pop() {
        return data[--size];
    }
}