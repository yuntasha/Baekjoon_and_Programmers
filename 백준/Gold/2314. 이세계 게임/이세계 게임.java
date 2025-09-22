/*
4*4
상하좌우 인접 위치 바꾸기
최소로 다음 목표 위치를 정하기
그냥 다 바꿔보고 기존에 존재하면 버리고 이런 방식
 */



import java.io.*;
import java.util.*;


public class Main {

    static int L = 0;
    static int P = 1;
    static int MAX = 1 << 16;
    static int N = 4;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int start = 0;

        for (int i = 0; i < N; i++) {
            char[] input = bf.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                start |= (input[j] == 'L' ? L : P) << (i * N + j);
            }
        }

        bf.readLine();

        int end = 0;

        for (int i = 0; i < N; i++) {
            char[] input = bf.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                end |= (input[j] == 'L' ? L : P) << (i * N + j);
            }
        }


        System.out.println(solution(start, end));
    }

    public static int solution(int start, int end) {
        int[] cnt = new int[MAX];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(start);
        cnt[start] = 1;

        while(!q.isEmpty()) {
            int now = q.remove();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int d = 0; d < 4; d++) {
                        int x = i + dx[d];
                        int y = j + dy[d];

                        if (!isIn(x, y)) continue;

                        int next = change(i, j, x, y, now);

                        if (cnt[next] > 0) continue;

                        cnt[next] = cnt[now] + 1;
                        q.add(next);
                    }
                }
            }
        }

        return cnt[end] - 1;
    }

    static int change(int x1, int y1, int x2, int y2, int now) {
        int a = getV(x1, y1, now);
        int b = getV(x2, y2, now);

        if (a != b) now ^= (1 << getIdx(x1, y1)) | (1 << getIdx(x2, y2));

        return now;
    }

    static int getIdx(int x, int y) {
        return x * 4 + y;
    }

    static int getV(int x, int y, int now) {
        return (now & (1 << (x * 4 + y))) > 0 ? 1 : 0;
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
}