import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var todo = new ArrayList<String>();
        var solved = new ArrayList<String>();

        for (int i=0; i<N; i++){
            var now = bufferedReader.readLine().strip();
            if (now.startsWith("boj.kr/") && now.substring(7).matches("[0-9]+")
                    && Integer.parseInt(now.substring(7))>0
                    && Integer.parseInt(now.substring(7))<=30000){
                solved.add(now);
            } else {
                todo.add(now);
            }
        }

        todo.sort(Comparator.comparing(String::length)
                .thenComparing(String::compareTo));
        solved.sort((a, b) -> {
            return Integer.parseInt(a.substring(7)) - Integer.parseInt(b.substring(7));
        });

        todo.forEach(System.out::println);
        solved.forEach(System.out::println);
    }
}