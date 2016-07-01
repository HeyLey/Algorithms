package part1.week7;

/*
идешь по скобкам в цикле
если скобка открывающаяся кладешь её в стек
если закрывающаяся пробуешь достать из стека
если стек пустой то последовательнось неправильная
если скобка которую ты достала на соотвесвует той
что ты прочитала то последовательнось неправильная
если ты дошла до конца
проверяешь пустой ли стек
если пустой последовательность правильная
если не пустой неправильная
Например ([)]
читаем ( откраюущаяся кладем в стек
стек (
читаем [ откраюущаяся кладем в стек
стек ([
читаем ) закрывающаяся. достаем из стека [
[ и ) не подходят друг другу
значит последовательность неправильная
печатаем NO и выходим


Дана строка, состоящая из круглых, квадратных и фигурных скобок.
Нужно проверить, является ли она правильной скобочной последовательностью.
Формат входных данных
Во входном файле записана скобочная последовательность длиной не более 10 000 символов.
Формат выходных данных
Выведите YES, если скобочная последовательность является правильной, и NO в противном случае.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Stack stack = new Stack(10000);

        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                stack.push(bracket);
            } else if (bracket == ')') {
                if (stack.size == 0 || stack.pop() != '(') {
                    System.out.println("NO");
                    System.exit(0);
                }
            } else if (bracket == ']') {
                if (stack.size == 0 || stack.pop() != '[') {
                    System.out.println("NO");
                    System.exit(0);
                }
            } else if (bracket == '}') {
                if (stack.size == 0 || stack.pop() != '{') {
                    System.out.println("NO");
                    System.exit(0);
                }
            }
        }
        if (stack.size == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

class Stack {
    int size;
    char[] data;

    Stack(int capacity) {
        data = new char[capacity];
    }

    void push(char value) {
        data[size++] = value;
    }

    char pop() {
        return data[--size];
    }
}
