package part1.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        char[] text = in.readLine().toCharArray();

        char[] buff = new char[text.length];
        int[] position = new int[text.length];

        position[0] = -1;

        int size = 1;

        int bestStart = 0;
        int bestCount = 0;

        for (int i = 0; i < text.length; i++) {
            char ch = text[i];
            if (ch == '(' || ch == '{' || ch == '[') {
                buff[size] = ch;
                position[size] = i;
                size++;
            } else {
                if (matches(buff[size - 1], ch)) {
                    size--;
                    int count = i - position[size - 1];
                    if (bestCount < count) {
                        bestStart = position[size - 1] + 1;
                        bestCount = count;
                    }
                } else {
                    int count = i - position[size - 1] - 1;
                    if (bestCount < count) {
                        bestStart = position[size - 1] + 1;
                        bestCount = count;
                    }
                    position[0] = i;
                    size = 1;
                }
            }

        }
        System.out.println(new String(text, bestStart, bestCount));
    }

    private static boolean matches(char a, char b) {
        switch (a) {
            case '(':
                return b == ')';
            case '[':
                return b == ']';
            case '{':
                return b == '}';
        }
        return false;
    }
}