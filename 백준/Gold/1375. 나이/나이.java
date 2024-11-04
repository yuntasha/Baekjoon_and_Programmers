import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static HashMap<String, List<String>> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        for (int i=0; i<M; i++) {
            var input = bf.readLine().split(" ");
            if (!hm.containsKey(input[0])) {
                hm.put(input[0], new ArrayList<>());
            }
            hm.get(input[0]).add(input[1]);
        }

        var T = Integer.parseInt(bf.readLine());

        var result = new StringJoiner(" ");
        for (int t=0; t<T; t++) {
            var names = bf.readLine().split(" ");
            result.add(solution(names[0], names[1]));
        }

        System.out.println(result.toString());
    }

    static String solution(String name1, String name2) {
        if (name1.equals(name2)) {
            return "gg";
        }

        var b1 = find(name1, name2);
        var b2 = find(name2, name1);

        if (b1&&b2) return "gg";
        if (b1) return name1;
        if (b2) return name2;
        return "gg";
    }

    static boolean find(String name1, String name2) {
        var visited = new HashSet<String>();
        var q = new LinkedList<String>();

        visited.add(name1);
        q.add(name1);

        while(!q.isEmpty()) {
            var now = q.remove();

            if (!hm.containsKey(now)) {
                continue;
            }

            for (String s: hm.get(now)) {
                if (s.equals(name2)) return true;
                if (visited.contains(s)) continue;
                visited.add(s);
                q.add(s);
            }
        }

        return false;
    }
}