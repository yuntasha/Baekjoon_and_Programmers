import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/*
막대끼리 같은 집합으로 만들어버림
모든 색깔이 같은 집합이라면 해결
근데 만약 홀수로 되어있다면 0 또는 2개가 있어햐함 그것도 처리
그럼 Map을 통해 해당 색깔을 int로 바꾸고
각 색깔에 개수를 세어주면 될듯
 */

public class Main {

    static String FAIL = "Impossible";
    static String SUCCESS = "Possible";
    static int MAX = 500_000;
    static HashMap<String, Integer> map = new HashMap<>();
    static int[] group;
    static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        group = IntStream.range(0, MAX).toArray();
        count = new int[MAX];

        String input;
        int now = 0;

        while ((input = bf.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);

            String s = st.nextToken();
            String e = st.nextToken();

            if (!map.containsKey(s)) map.put(s, now++);
            if (!map.containsKey(e)) map.put(e, now++);

            union(map.get(s), map.get(e));

            count[map.get(s)]++;
            count[map.get(e)]++;
        }

        int odd = 0;

        for (int i = 0; i < now; i++) {
            if (find(i) != 0) {
                System.out.println(FAIL);
                return;
            }

            if ((count[i] & 1) == 1) odd++;
        }

        System.out.println(odd == 0 || odd == 2 ? SUCCESS : FAIL);
    }

    static int find(int i) {
        if (group[i] != i) group[i] = find(group[i]);
        return group[i];
    }

    static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);

        group[Math.max(fx, fy)] = Math.min(fx, fy);
    }
}