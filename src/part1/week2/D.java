package part1.week2;

/**
 Вася написал на длинной полоске бумаги большое число
 и решил похвастаться своему старшему брату Пете этим достижением.
 Но только он вышел из комнаты, чтобы позвать брата,
 как его сестра Катя вбежала в комнату и разрезала полоску бумаги на несколько частей.
 В результате на каждой части оказалось одна или несколько идущих подряд цифр.
 Теперь Вася не может вспомнить, какое именно число он написал.
 Только помнит, что оно было очень большое.
 Чтобы утешить младшего брата, Петя решил выяснить,
 какое максимальное число могло быть написано на полоске бумаги перед разрезанием.
 Помогите ему!

 Формат входных данных
 Входной файл содержит одну или более строк,
 каждая из которых содержит последовательность цифр.
 Количество строк во входном файле не превышает 100,
 каждая строка содержит от 1 до 100 цифр.
 Гарантируется, что хотя бы в одной строке первая цифра отлична от нуля.

 Формат выходных данных
 Выведите в выходной файл одну строку — максимальное число,
 которое могло быть написано на полоске перед разрезанием.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class D {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String[] strings = new String[100];

        int n = 0;

        String line = br.readLine();
        while (line != null) {
            strings[n] = line;
            line = br.readLine();
            n++;
        }

        String[] dest = new String[n];
        System.arraycopy(strings, 0, dest, 0, n);

        Arrays.sort(dest);

        List<String> parts = new ArrayList<String>();
        for (int i = n - 1; i >= 0; i--) {
            parts.add(dest[i]);
        }

        StringBuilder result = new StringBuilder();

        String best = "";

        TreeMap<String, List<String>> wave = new TreeMap<String, List<String>>();
        wave.put("", parts);

        while (wave.size() > 0) {
            Map.Entry<String, List<String>> firstEntry = wave.pollFirstEntry();
            String prefix = firstEntry.getKey();

            if (best.startsWith(prefix)) {
                result.append(prefix);
                best = best.substring(prefix.length());
                TreeMap<String, List<String>> nextWave = new TreeMap<String, List<String>>();
                for (Map.Entry<String, List<String>> entry : wave.entrySet()) {
                    if (entry.getKey().startsWith(prefix)) {
                        nextWave.put(entry.getKey().substring(prefix.length()), entry.getValue());
                    }
                }
                wave = nextWave;

                List<String> variants = firstEntry.getValue();
                for (String variant : variants) {
                    String nextPrefix = variant;
                    if (best.startsWith(nextPrefix) || nextPrefix.startsWith(best) || nextPrefix.compareTo(best) >= 0) {
                        if (!best.startsWith(nextPrefix)) {
                            best = nextPrefix;
                        }
                        if (variants.size() > 1 && !wave.containsKey(nextPrefix)) {
                            List<String> nextVariants = new ArrayList<String>(variants);
                            nextVariants.remove(variant);
                            wave.put(nextPrefix, nextVariants);
                        }
                    }
                }
            }

        }
        System.out.println(result + best);
        out.close();
    }
}