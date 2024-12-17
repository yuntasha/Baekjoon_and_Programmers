import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static long MIN_VALUE = -613000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] arr = new int[N][];

        for (int i = 0; i < N; i++) {
            arr[i] = bf.readLine().chars().map(Main::getC).toArray();
        }

        System.out.println(solution(N, arr));
    }

    static double solution(int N, int[][] arr) {
        int[][] count = find(N, arr);
        long[] maxV = new long[321]; // 0이면 -160, 320이면 160

        Arrays.fill(maxV, MIN_VALUE);

        int s = 160;
        int e = 160;
        maxV[160] = 0;

        for (int i=0; i<4; i++) {
            for (int j=i; j<4; j++) {
                long[] next = new long[321]; // 0이면 -160, 320이면 160
                Arrays.fill(next, MIN_VALUE);

                if (i==j) {
                    for (int r = s; r<=e; r++) { // 저번까지 가진 합
                        for (int n=1; n<=10; n++) { // 이번에 고른 값
                            next[r+n] = Math.max(next[r+n], maxV[r] + (long) n * count[i][j]);
                        }
                    }
                    s+=1;
                    e+=10;
                } else {
                    for (int r = s; r<=e; r++) { // 저번까지 가진 합
                        for (int n=-10; n<=10; n++) { // 이번에 고른 값
                            next[r+n*2] = Math.max(next[r+n*2], maxV[r] + (long) n * count[i][j]);
                        }
                    }
                    s-=20;
                    e+=20;
                }
                maxV = next;
            }
        }

        return (double) maxV[160] / (double) (N*(N-1)/2);
    }

    static int[][] find(int N, int[][] arr) {
        int[][] count = new int[4][4];
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                for (int c=0; c<arr[i].length; c++) {
                    count[arr[i][c]][arr[j][c]] = count[arr[j][c]][arr[i][c]] = count[arr[i][c]][arr[j][c]] + 1;
                }
            }
        }

        return count;
    }

    static int getC(int i) {
        if (i=='A') return 0;
        if (i=='C') return 1;
        if (i=='T') return 2;
        if (i=='G') return 3;

        throw new IllegalArgumentException();
    }

    static class Node {
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }
}