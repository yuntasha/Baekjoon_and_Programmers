import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
음음음
다이나믹 느낌인데
DP[현재 A길이][현재 B길이][현재 C길이][A&][B&][C&]
이렇게하면 되려나?
8 * 100 * 100 * 100 이 나오겠군
8_000_000 * 8
오 되겠는데
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(bf.readLine().toCharArray(), bf.readLine().toCharArray(), bf.readLine().toCharArray()));
    }

    static int solution(char[] S1, char[] S2, char[] S3) {
        final int L1 = S1.length;
        final int L2 = S2.length;
        final int L3 = S3.length;

        if (Math.max(L1, Math.max(L2, L3)) > Math.min(L1, Math.min(L2, L3)) * 2 + 1) return -1;

        int[][][][] DP = new int[L1 + 1][L2 + 1][L3 + 1][8];
        boolean[][][][] visited = new boolean[L1 + 1][L2 + 1][L3 + 1][8];

        visited[0][0][0][0] = true;

        for (int cnt = 0; cnt <= L1 + L2 + L3; cnt++) {
            for (int a = Math.max(cnt - L2 - L3, 0); a <= L1; a++) {
                for (int b = Math.max(cnt - a - L3, 0); b <= Math.min(L2, cnt - a); b++) {
                    int c = cnt - a - b;

                    for (int v = 0; v < 7; v++) {
                        // aa = ((v>>2) == 0)
                        // bb = (((v>>1)&1) == 0)
                        // cc = ((v&1) == 0)
                        if (!visited[a][b][c][v]) continue;
                        // 1개만 선택
                        if (a < L1 && (((v>>1)&1) == 0) && ((v&1) == 0)) {
                            DP[a+1][b][c][3] = Math.max(DP[a+1][b][c][3], DP[a][b][c][v]);
                            visited[a+1][b][c][3] = true;
                        }

                        if (b < L2 && ((v>>2) == 0) && ((v&1) == 0)) {
                            DP[a][b+1][c][5] = Math.max(DP[a][b+1][c][5], DP[a][b][c][v]);
                            visited[a][b+1][c][5] = true;
                        }

                        if (c < L3 && ((v>>2) == 0) && (((v>>1)&1) == 0)) {
                            DP[a][b][c+1][6] = Math.max(DP[a][b][c+1][6], DP[a][b][c][v]);
                            visited[a][b][c+1][6] = true;
                        }

                        // 2개만 선택
                        if (a < L1 && b < L2 && ((v&1) == 0)) {
                            DP[a+1][b+1][c][1] = Math.max(DP[a+1][b+1][c][1], DP[a][b][c][v] + getScore(S1[a], S2[b]));
                            visited[a+1][b+1][c][1] = true;
                        }
                        if (a < L1 && c < L3 && (((v>>1)&1) == 0)) {
                            DP[a+1][b][c+1][2] = Math.max(DP[a+1][b][c+1][2], DP[a][b][c][v] + getScore(S1[a], S3[c]));
                            visited[a+1][b][c+1][2] = true;
                        }
                        if (b < L2 && c < L3 && ((v>>2) == 0)) {
                            DP[a][b+1][c+1][4] = Math.max(DP[a][b+1][c+1][4], DP[a][b][c][v] + getScore(S2[b], S3[c]));
                            visited[a][b+1][c+1][4] = true;
                        }

                        // 3개 전부 선택
                        if (a < L1 && b < L2 && c < L3) {
                            DP[a+1][b+1][c+1][0] = Math.max(DP[a+1][b+1][c+1][0], DP[a][b][c][v] + getScore(S1[a], S2[b], S3[c]));
                            visited[a+1][b+1][c+1][0] = true;
                        }
                    }
                }
            }
        }

        int result = 0;

        for (int v=0; v<8; v++) {
            result = Math.max(result, DP[L1][L2][L3][v]);
        }

        return result;
    }

    static int getScore(char a, char b) {
        if (a==b) return 1;
        return 0;
    }

    static int getScore(char a, char b, char c) {
        if (a==b && b==c) return 3;
        return 0;
    }
}

