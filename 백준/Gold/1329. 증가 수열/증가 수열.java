import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static long minValue = Long.MAX_VALUE;
    static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var input = bf.readLine();

        solution(input);

        System.out.println(result);
    }

    static void solution(String input) {
        dfs(input, 0, -1, new ArrayList<String>());
    }

    static void dfs(String input, int now, long last, List<String> list) {

        if (input.length() == now) {
            minValue = last;
            result = String.join(",", list);
            return;
        }

        for (int i=now; i<input.length(); i++) {
            var s = input.substring(now, i+1);
            var n = Long.parseLong(s);
            if (n>minValue) {
                break;
            } else if (n>last) {
                list.add(s);
                dfs(input, i+1, n, list);
                list.remove(list.size()-1);
            }
        }
    }
}