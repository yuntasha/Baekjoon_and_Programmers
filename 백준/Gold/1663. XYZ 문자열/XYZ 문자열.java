import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
DP로 풀자
DP[X-0, Y-1, Z-2][개수]
1. 최종 길이 -> 그냥 다 더해주면 됨
2. 해당 인덱스로 찾아들어가면 된다
3. 몇개인지 그냥 출력하면 된다.
X -> YZ
Y -> Z
Z -> X
*/

public class Main {

    static int[][] modify = {{0, 1, 1}, {0, 0, 1}, {1, 0, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(bf.readLine());
        int N = Integer.parseInt(bf.readLine());

        if (Q == 1) {
            System.out.println(solution1(Q, N));
        } else if (Q == 2) {
            long K = Long.parseLong(bf.readLine());
            System.out.println(solution2(Q, N, K));
        } else if (Q == 3) {
            String C = bf.readLine();
            System.out.println(solution3(Q, N, C));
        }


    }

    static String solution1(int Q, int N) {
        long[][] DP = new long[N][3];

        DP[0][0] = 1;

        for (int i=1; i<N; i++) {
            for (int s=0; s<3; s++) {
                for (int d=0; d<3; d++) {
                    DP[i][d] += DP[i-1][s] * modify[s][d];
                }
            }
        }


        return String.valueOf(DP[N-1][0] + DP[N-1][1] + DP[N-1][2]);
    }

    static char solution2(int Q, int N, long k) {
        long[] DPY = new long[N+3];

        DPY[0] = 1;
        DPY[1] = 1;
        DPY[2] = 1;
        DPY[3] = 1;

        for (int i=4; i<=N+2; i++) {
            DPY[i] = DPY[i-3] + DPY[i-2];
        }

        return dfs(k, N, 0, 1, DPY[N+2], DPY);
    }
    
    static char dfs(long idx, int n, int now, long start, long end, long[] DPY) {
//        System.out.println("start = " + start);
//        System.out.println("end = " + end);
        if (n==1) {
            return (char) (now+'X');
        }

        if (now==0) {
            long s = start + DPY[n - 1];

            if (s <= idx) {
                return dfs(idx, n - 1, 2, s, end, DPY);
            }
            return dfs(idx, n - 1, 1, start, s - 1, DPY);
        }
        return dfs(idx, n - 1, (now+1)%3, start, end, DPY);
    }

    static String solution3(int Q, int N, String C) {
        long[][] DP = new long[N][3];

        DP[0][0] = 1;

        for (int i=1; i<N; i++) {
            for (int s=0; s<3; s++) {
                for (int d=0; d<3; d++) {
                    DP[i][d] += DP[i-1][s] * modify[s][d];
                }
            }
        }

        return String.valueOf(C.equals("X")?DP[N-1][0]:C.equals("Y")?DP[N-1][1]:DP[N-1][2]);
    }
}