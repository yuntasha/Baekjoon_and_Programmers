import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
피보나치 트리에 각 노드가 몇개 들어있는지 구해
i번째 트리 = 1 + i-2번째 트리 + i - 1번째 트리
현재 노드 값이라면 멈춤
현재 노드 값에서 좌측 트리만큼 더한 수보다 크면 오른쪽 아니라면 왼쪽
*/

public class Main {

    static char LEFT = 'L';
    static char RIGHT = 'R';
    static char UP = 'U';

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, S, E));
    }

    static String solution(int N, int S, int E) {
        long[] size = getSize(N);

        StringBuilder result = new StringBuilder();

        StringBuilder findS = new StringBuilder();
        StringBuilder findE = new StringBuilder();

        findRoute(N, S, size, findS);
        findRoute(N, E, size, findE);

        int idx = 0;

        while (idx < findS.length() && idx < findE.length() && findS.charAt(idx) == findE.charAt(idx)) idx++;
        for (int i=0; i<findS.length() - idx; i++) result.append(UP);
        result.append(findE.substring(idx));

        return result.toString();
    }

    static void findRoute(int N, long now, long[] size, StringBuilder result) {
        if (now == 1) return;

        long leftTreeCount = N<2?0:size[N-2];

        if (now > 1 + leftTreeCount) {
            result.append(RIGHT);
            findRoute(N-1, now - 1 - leftTreeCount, size, result);
        } else {
            result.append(LEFT);
            findRoute(N-2, now - 1, size, result);
        }
    }

    static long[] getSize(int N) {
        if (N==0) return new long[]{1};

        long[] result = new long[N+1];
        result[0] = result[1] = 1;

        for (int i=2; i<=N; i++) {
            result[i] = 1 + result[i-1] + result[i-2];
        }

        return result;
    }
}