import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var bt = new StringTokenizer(bf.readLine());

        var N = Integer.parseInt(bt.nextToken());
        var M = Integer.parseInt(bt.nextToken());

        var arr = new char[N][];

        for (int n=0; n<N; n++) {
            arr[n] = bf.readLine().toCharArray();
        }

        System.out.println(solution(N, M, arr));
    }

    static String solution(int N, int M, char[][] arr) {
        int g = 0;
        var gMap = new int[N][M];
        for (int n=0; n<N; n++) {
            for (int m=0; m<M; m++) {
                if (arr[n][m] == '.' || gMap[n][m] > 0) continue;
                g++;
                var q = new LinkedList<Node>();
                q.add(new Node(n, m));

                while (!q.isEmpty()) {
                    var now = q.remove();
                    if (gMap[now.x][now.y] > 0) continue;

                    gMap[now.x][now.y] = g;

                    for (int i=0; i<4; i++) {
                        var x = now.x + dx[i];
                        var y = now.y + dy[i];

                        if (!isIn(x, y, N, M)) continue;
                        if (arr[x][y] == '.' || gMap[x][y] > 0) continue;
                        q.add(new Node(x, y));
                    }
                }
            }
        }

        var grr = IntStream.rangeClosed(0, g).toArray();


        while (fill(N, M, gMap, grr)) {
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    gMap[i][j] = findG(gMap[i][j], grr);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                sb.append(gMap[i][j]>0?'#':'.');
            }
            sb.append("\n");
        }
        return sb.substring(0, sb.length()-1);
    }

    static boolean fill(int N, int M, int[][] gMap, int[] grr) {
        var chk = false;
        for (int n=0; n<N; n++) {
            for (int m=0; m<M; m++) {
                if (gMap[n][m] == 0) continue;

                for (int dn=N-1; dn>=n; dn--) {
                    if (gMap[n][m] == gMap[dn][m]) {
                        for (int d=n; d<=dn; d++) {
                            if (gMap[d][m] != gMap[n][m] && gMap[d][m]>0) {
                                var a = findG(gMap[n][m], grr);
                                var b = findG(gMap[d][m], grr);

                                chk = true;

                                grr[Math.max(a, b)] = Math.min(a, b);
                            }

                            for (int i=0; i<4; i++) {
                                var dxx = d + dx[i];
                                var dyy = m + dy[i];

                                if (!isIn(dxx, dyy, N, M)) continue;
                                if (gMap[dxx][dyy] == 0 || gMap[dxx][dyy] == gMap[n][m]) continue;

                                chk = true;

                                var a = findG(gMap[n][m], grr);
                                var b = findG(gMap[dxx][dyy], grr);

                                grr[Math.max(a, b)] = Math.min(a, b);
                            }

                            gMap[d][m] = gMap[n][m];
                        }
                        break;
                    }
                }

                for (int dm=M-1; dm>=m; dm--) {
                    if (gMap[n][m] == gMap[n][dm]) {
                        for (int d=m; d<=dm; d++) {
                            if (gMap[n][d] != gMap[n][m] && gMap[n][d]>0) {
                                var a = findG(gMap[n][m], grr);
                                var b = findG(gMap[n][d], grr);

                                chk = true;

                                grr[Math.max(a, b)] = Math.min(a, b);
                            }

                            for (int i=0; i<4; i++) {
                                var dxx = n + dx[i];
                                var dyy = d + dy[i];

                                if (!isIn(dxx, dyy, N, M)) continue;
                                if (gMap[dxx][dyy] == 0 || gMap[dxx][dyy] == gMap[n][m]) continue;

                                var a = findG(gMap[n][m], grr);
                                var b = findG(gMap[dxx][dyy], grr);

                                chk = true;

                                grr[Math.max(a, b)] = Math.min(a, b);
                            }

                            gMap[n][d] = gMap[n][m];
                        }
                        break;
                    }
                }
            }
        }

        return chk;
    }

    static int findG(int n, int[] grr) {
        if (grr[n]!=n) grr[n] = findG(grr[n], grr);
        return grr[n];
    }

    static boolean isIn(int n, int m, int N, int M) {
        return 0<=n && n<N && 0<=m && m<M;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}