import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var loads = new LinkedList<Load>();
        for (int i=0; i<N-1; i++) {
            var now = bufferedReader.readLine().split("");
            for (int j=i+1; j<N; j++) {
                if (now[j].equals("N")) continue;
                loads.add(new Load(i, j));
            }
        }
        System.out.println(solution(N, M, loads));
    }

    static String solution(int N, int M, LinkedList<Load> loads){
        var result = new int[N];
        var group = IntStream.range(0, N).toArray();

        if (N-1>M) return "-1";

        var cnt = 0;
        var q = new LinkedList<Load>();

        while (cnt<N-1 && !loads.isEmpty()) {
            var load = loads.remove();
            var x = findGroup(load.x, group);
            var y = findGroup(load.y, group);
            if (x == y) {
                q.add(load);
            } else {
                result[load.x]++;
                result[load.y]++;
                group[Math.max(x,y)] = Math.min(x,y);
                cnt++;
            }
        }

        if (cnt!=N-1) return "-1";

        while (cnt<M && !q.isEmpty()) {
            var now = q.remove();
            result[now.x]++;
            result[now.y]++;
            cnt++;
        }

        while (cnt<M && !loads.isEmpty()) {
            var now = loads.remove();
            result[now.x]++;
            result[now.y]++;
            cnt++;
        }

        return cnt==M?Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")):"-1";
    }

    static int findGroup(int n, int[] group) {
        if (n!=group[n]) group[n] = findGroup(group[n], group);
        return group[n];
    }

    static class Load {
        int x;
        int y;

        Load(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}