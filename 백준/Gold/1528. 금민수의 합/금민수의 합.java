import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static String solution(int N) {

        if (N == 0) return "0";
        if (N < 0) return "-1";

        var nums = new ArrayList<Integer>();

        find(nums, 0, 0);

        nums.sort(Integer::compareTo);

        var visited = new boolean[N+1];

        var q = new LinkedList<Node>();

        q.add(new Node(0, ""));

        while (!q.isEmpty()) {
            var now = q.remove();

            for (int num : nums) {
                if (num+now.x > N) continue;
                if (visited[num+now.x]) continue;
                visited[num+now.x] = true;
                if (now.x+num == N) return (now.result+ " " + num).substring(1);
                q.add(new Node(now.x+num, now.result+ " " + num));
            }
        }

        return "-1";
    }

    static void find(List<Integer> nums, int now, int cnt) {
        if (cnt == 7) return;
        var v1 = now*10+4;
        var v2 = now*10+7;
        nums.add(v1);
        nums.add(v2);
        find(nums, v1, cnt+1);
        find(nums, v2, cnt+1);
    }

    static class Node {
        int x;
        String result;

        public Node(int x, String result) {
            this.x = x;
            this.result = result;
        }
    }
}