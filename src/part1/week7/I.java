package part1.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.DoubleUnaryOperator;

public class I {

        private String text = null;
        private int index;
        private char currentChar;

        public double calculate(final String expression) {
            text = expression;
            index = -1;
            next();
            return parseExpr();
        }

        private void expect(final char ch) {
            if (currentChar != ch) {
                throw new RuntimeException(ch + " expected");
            }
            next();
        }

        private char next() {
            if (index == text.length()) {
                currentChar = 0;
                return 0;
            }
            index++;
            dropSpace();
            if (index == text.length()) {
                currentChar = 0;
                return 0;
            } else {
                currentChar = text.charAt(index);
            }
            return currentChar;
        }

        private void dropSpace() {
            while (index < text.length() && Character.isSpaceChar(text.charAt(index))) {
                index++;
            }
        }

        public double parseExpr() {
            return parseSum();
        }

        private double parseSum() {
            double result = parseMinus();
            while (currentChar == '+' || currentChar == '-') {
                char op = currentChar;
                next();
                double next = parseMinus();
                if (op == '+') {
                    result += next;
                } else {
                    result -= next;
                }
            }
            return result;
        }

        private double parseMinus() {
            if (currentChar == '-') {
                next();
                double value = parseMinus();
                return -value;
            } else {
                return parseMull();
            }
        }

        private double parseMull() {
            double result = parseDegree();
            while (currentChar == '*' || currentChar == '/') {
                char op = currentChar;
                next();
                double next = parseDegree();
                if (op == '*') {
                    result *= next;
                } else {
                    result /= next;
                }
            }
            return result;
        }

        private double parseDegree() {
            double result = parseAtom();
            if (currentChar == '^') {
                next();
                double degree = parseDegree();
                return Math.pow(result, degree);

            }
            return result;
        }

        public double parseAtom() {
            if (currentChar == '(') {
                return parseBrackets();
            } else if (currentChar == '-') {
                return parseUnaryMinus();
            } else if (Character.isLetter(currentChar)) {
                return parseFunction();
            } else {
                return parseNumber();
            }
        }

        private double parseUnaryMinus() {
            next();
            double value = parseAtom();
            return -value;
        }

        private double parseFunction() {
            String name = parseName();
            expect('(');
            double value = parseExpr();
            expect(')');

            return Operation.valueOf(name).calc(value);
        }

        public double parseBrackets() {
            expect('(');
            double value = parseExpr();
            expect(')');
            return value;
        }

        public String parseName() {
            StringBuilder buffer = new StringBuilder();
            while (Character.isLetter(currentChar)) {
                buffer.append(currentChar);
                next();
            }
            return buffer.toString().toUpperCase();
        }

        public double parseNumber() {
            StringBuilder buffer = new StringBuilder();
            while (isNumberCh(currentChar)) {
                if (currentChar == 'E') {
                    buffer.append(currentChar);
                    next();
                }
                buffer.append(currentChar);
                next();
            }
            return Double.parseDouble(buffer.toString());
        }

        private boolean isNumberCh(final char ch) {

            return Character.isDigit(ch) || ch == '.' || ch == 'E';
        }

}

enum Operation {
    SIN(Math::sin),
    COS(Math::cos),
    ABS(Math::abs);

    private DoubleUnaryOperator function;

    Operation(final DoubleUnaryOperator function) {
        this.function = function;
    }

    double calc(final double val) {
        return function.applyAsDouble(val);
    }
}

class Main {
    public static void main(final String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        double result = new I().calculate(exp);
        System.out.println((int)result);
    }
}

