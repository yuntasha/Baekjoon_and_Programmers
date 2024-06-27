import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final int MAX_VALUE = 5_000_010;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var TC = Integer.parseInt(bufferedReader.readLine());

        for (int t=0; t<TC; t++) {

            var NMX = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            final var N = NMX[0];
            final var M = NMX[1];
            final var X = NMX[2];

            var roads = new HashMap<Integer, HashMap<Integer, Integer>>();

            for (int i=0; i<M; i++){
                var cmd = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                setRoad(roads, cmd[0], cmd[1], cmd[2]);
                setRoad(roads, cmd[1], cmd[0], cmd[2]);
            }

            for (int i=0; i<X; i++){
                var cmd = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                setRoad(roads, cmd[0], cmd[1], -cmd[2]);
            }
            System.out.println(solution(N, roads)?"YES":"NO");
        }

    }

    static void setRoad(HashMap<Integer, HashMap<Integer, Integer>> roads, int s, int e, int t) {
        HashMap<Integer, Integer> now;
        s--;
        e--;
        if (roads.containsKey(s)) now = roads.get(s);
        else {
            now = new HashMap<>();
            roads.put(s, now);
        }
        now.put(e, Math.min(now.getOrDefault(e, t), t));
    }

    static boolean solution(int N, HashMap<Integer, HashMap<Integer, Integer>> roads){
        var result = new boolean[N];
        for (int i=0; i<N; i++){
            if (result[i]) continue;
            result[i] = true;
            if (bellman(N, roads, i, result)) return true;
        }
        return false;
    }

    // 돌아오는거 성공하면 true 아님 false
    static boolean bellman(int N, HashMap<Integer, HashMap<Integer, Integer>> roads, int now, boolean[] result){
        int[] times = new int[N];
        Arrays.fill(times, MAX_VALUE);
        times[now] = 0;
        int cnt = 0;
        boolean flag = true;
        while (cnt<N && flag) {
            cnt++;
            flag = false;
            for (int i: roads.keySet()){
                if (times[i]==MAX_VALUE) continue;
                for (int j: roads.get(i).keySet()) {
                    if (times[j] > roads.get(i).get(j) + times[i]) {
                        flag = true;
                        result[j] = true;
                        times[j] = roads.get(i).get(j) + times[i];
                    }
                }
            }
        }
        return flag;
    }
}