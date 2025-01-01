import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
수평은 n개 20개까지
수직은 m개 200개까지
전부 전체 탐색하면 될수도?
근데 수평을 브루트포스하고 수직은 이분탐색으로 찾아가는 것도 좋을 듯
수평을 브루트포스 -> 브루트포스 결과에서 이분탐색으로 넘기기 -> 이분탐색에서 수직 자르기 -> 수직 자른 것과 결과 배출
필드에 정답 만들어둬서 출력할때 사용하는게 좋아보임

 */

public class Main {

    static int[] xResult;
    static int[] yResult;

    static int result;

    public static void main(String[] args) throws IOException {

        int N = read();
        int M = read();

        int x = read();
        int y = read();

        int[][] map = new int[N+1][M+1];
        int maxV = 0;

        for (int i=0; i<=N; i++) {
            for (int j=0; j<=M; j++) {
                map[i][j] = read();
                maxV = Math.max(map[i][j], maxV);
            }
        }

        solution(N, M, x, y, map, maxV);

        System.out.println(Arrays.stream(xResult).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(Arrays.stream(yResult).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(result);
    }

    public static void solution(int N, int M, int x, int y, int[][] map, int maxV) {

        xResult = new int[x];
        yResult = new int[y];
        result = 21_210_000;

        dfs(0, new int[x], N, M, x, y, map, maxV);
    }

    // 최소값이면 그냥 true
    public static boolean dfs(int n, int[] xLine, int N, int M, int x, int y, int[][] map, int maxV) {
        if (n==x) { // 다 뽑았으면
            return find(maxV, N, M, x, y, xLine, map);
        }

        for (int i=(n==0?1:(xLine[n-1]+1)); i<=N; i++) {
            xLine[n] = i;
            if (dfs(n+1, xLine, N, M, x, y, map, maxV)) return true; // 최솟값이면 그냥 탐색 중단하고 바로 끝
        }

        return false;
    }

    // 최소값이 나오면 1로 처리해버리자
    public static boolean find(int maxV, int N, int M, int x, int y, int[] xLine, int[][] map) {
        int start = maxV; // 시작은 가장 큰 수로
        int end = result; // 나올 수 있는 가장 큰 수 21 * 101 * 10000

        List<Integer> yList = new ArrayList<>();

        if (chk(start, N, M, x, y, xLine, map, yList)) { // 만약 start 성공이면 바로 처리
            success(xLine, yList, start);
            return true;
        }

        if (!chk(end, N, M, x, y, xLine, map, yList)) return false; // 어차피 이전 결과보다 큰 결과가 나오는건 할 필요가 없음


        while (start + 1 < end) {
            int mid = (start + end) >> 1;

            if (!chk(mid, N, M, x, y, xLine, map, yList)) { // 실패라면
                start = mid;
            } else { // 성공이라면
                end = mid;
            }
        }

        chk(end, N, M, x, y, xLine, map, yList);

        success(xLine, yList, end);

        return false;
    }

    public static void success(int[] xLine, List<Integer> yLine, int maxV) { // 항상 작게 나올테니 없앰
        for (int i=0; i<xLine.length; i++) {
            xResult[i] = xLine[i];
        }

        for (int i=0; i<yLine.size(); i++) {
            yResult[i] = yLine.get(i);
        }

        result = maxV;
    }


    public static boolean chk(int maxV, int N, int M, int x, int y, int[] xLine, int[][] map, List<Integer> yLine) {
        int[] now = new int[x+1];
        yLine.clear();

        for (int j=0; j<=M; j++) {
            int idx = 0;
            boolean isOver = false;

            for (int i=0; i<=N; i++) {
                if (idx < x && xLine[idx] <= i) idx++; // 0123에서 2를 갔는데 선은 2에서 자르라고 하면 01|23인데 2번선이면 2번부터 다음 인덱스라 넘겨줌
                now[idx] += map[i][j];
                if (now[idx] > maxV) {
                    isOver = true;
                    break;
                }
            }

            idx = 0;

            if (isOver) { // 만약 내가 설정한 최댓값보다 크다면 앞에 선을 그어줌
                yLine.add(j);
                if (yLine.size() > y) return false; // y개가 넘어간다면 실패니까 false
                Arrays.fill(now, 0);
                for (int i=0; i<=N; i++) {
                    if (idx < x && xLine[idx] <= i) idx++; // 0123에서 2를 갔는데 선은 2에서 자르라고 하면 01|23인데 2번선이면 2번부터 다음 인덱스라 넘겨줌
                    now[idx] += map[i][j]; // 이번부터 시작이라 다시 처리해주기
                }
            }
        }

        return true;
    }

    private static int read() throws IOException {
        int n = 0;
        int i;
        while ((i = System.in.read()) >= '0') {
            n = (n<<3) + (n<<1) + (i&15);
        }
        return n;
    }
}