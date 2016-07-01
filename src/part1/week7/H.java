package part1.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String text = in.readLine();

        int fee = 0;
        int balance = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                balance += 1;
            } else {
                if (balance == 0) {
                    fee++;
                } else {
                    balance--;
                }
            }
        }
        System.out.println(balance + fee);
    }
}