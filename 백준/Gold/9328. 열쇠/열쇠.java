import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NN = Integer.parseInt(bufferedReader.readLine());

        for (int n=0; n<NN; n++) {

            var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            var N = NM[0];
            var M = NM[1];

            var map = new char[N][M];
            var keys = new boolean[26];

            for (int i = 0; i < N; i++) {
                map[i] = bufferedReader.readLine().strip().toCharArray();
            }

            for (char c : bufferedReader.readLine().toCharArray()) {
                if (c == '0') break;
                keys[c - 'a'] = true;
            }

            System.out.println(solution(N, M, map, keys));
        }
    }

    static int solution(int N, int M, char[][] map, boolean[] keys){
        var moveQ = new LinkedList<Map.Entry<Integer, Integer>>();
        var doorQ = new LinkedList<Map.Entry<Integer, Integer>>();
        var result = findIn(N, M, map, keys, moveQ, doorQ);

        while (!moveQ.isEmpty()){
            result += move(N, M, map, keys, moveQ, doorQ);
            openDoor(N, M, map, keys, moveQ, doorQ);
        }
        return result;
    }

    static int findIn(int N, int M, char[][] map, boolean[] keys, LinkedList<Map.Entry<Integer, Integer>> moveQ
            , LinkedList<Map.Entry<Integer, Integer>> doorQ) {
        var find = 0;
        for (int i=0; i<N ; i++){
            find += getFind(i, 0, map, keys, moveQ, doorQ);
            find += getFind(i, M-1, map, keys, moveQ, doorQ);
        }
        for (int i=1; i<M-1 ; i++){
            find += getFind(0, i, map, keys, moveQ, doorQ);
            find += getFind(N-1, i, map, keys, moveQ, doorQ);
        }
        return find;
    }

    private static int getFind(int n, int m, char[][] map, boolean[] keys, LinkedList<Map.Entry<Integer, Integer>> moveQ, LinkedList<Map.Entry<Integer, Integer>> doorQ) {
        var find = 0;
        switch (map[n][m]){
            case '$':
                find++;
            case '.':
                moveQ.add(Map.entry(n, m));
            case '*':
                map[n][m] = '0';
            case '0':
                break;
            default:
                if (map[n][m]<'a') {
                    if (keys[map[n][m]-'A']) {
                        moveQ.add(Map.entry(n, m));
                        map[n][m] = '0';
                    } else {
                        doorQ.add(Map.entry(n, m));
                    }
                } else {
                    moveQ.add(Map.entry(n, m));
                    keys[map[n][m]-'a'] = true;
                    map[n][m] = '0';
                }
        }
        return find;
    }

    static int move(int N, int M, char[][] map, boolean[] keys, LinkedList<Map.Entry<Integer, Integer>> moveQ, LinkedList<Map.Entry<Integer, Integer>> doorQ) {
        var dx = new int[]{0, 1, 0, -1};
        var dy = new int[]{1, 0, -1, 0};

        var result = 0;

        while (!moveQ.isEmpty()) {
            var now = moveQ.remove();
            for (int i=0; i<4; i++) {
                var n = now.getKey()+dx[i];
                var m = now.getValue()+dy[i];
                if (!(0<=n && n<N && 0<=m && m<M)) continue;
                result += getFind(n, m, map, keys, moveQ, doorQ);
            }
        }
        return result;
    }

    static void openDoor(int N, int M, char[][] map, boolean[] keys, LinkedList<Map.Entry<Integer, Integer>> moveQ, LinkedList<Map.Entry<Integer, Integer>> doorQ) {
        var s = doorQ.size();

        for (int i=0; i<s; i++){
            var now = doorQ.remove();
            if (map[now.getKey()][now.getValue()] == '0') continue;
            if (keys[map[now.getKey()][now.getValue()]-'A']) {
                map[now.getKey()][now.getValue()] = '0';
                moveQ.add(now);
            } else {
                doorQ.add(now);
            }
        }
    }
}
