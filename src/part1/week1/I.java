package part1.week1;


/**
 * В Байтландии за всю её историю было выпущено 15000 различных почтовых марок.
 * Известный коллекционер почтовых марок планирует собрать полную коллекцию марок Байтландии.
 * Какое-то количество марок (возможно, с дубликатами) у него есть на данным момент.
 * По заданному списку марок, имеющихся в наличии, вычислить, какое минимальное
 * количество марок коллекционер должен докупить, чтобы коллекция стала полной.
 */
import java.io.*;

public class I {
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

        int[] array = new int[15000];
        int res = 0;

        for(int i = 0; i < n; i++) {
            int j = nextInt();
            if (array[j] == 0) {
                array[j]++;}
        }
        for(int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                res++;
            }
        }

        out.println(15000 - res);
        out.close();
    }
}