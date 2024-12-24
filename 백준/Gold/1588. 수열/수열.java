import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
시작을 1 끝을 3^N으로 만듦
(end - start + 1) / 3으로 구간의 길이를 구함
(start, start + 구간의 길이 - 1), (start + 구간의 길이, end - 구간의 길이), (end - 구간의 길이 + 1, end)
(시작, 끝) (왼쪽, 오른쪽)
전체인 경우 왼쪽 <= 시작 && 끝 <= 오른쪽 
좌측 일부 왼쪽 > 시작 && 왼쪽 <= 끝
우측 일부 오른쪽 < 끝 && 오른쪽 >= 시작
나머지는 버림

1
132
132 232 211
 */
public class Main {

    static int[][] next = {{}, {1, 3, 2}, {2, 1, 1}, {2, 3, 2}};
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int NUM = Integer.parseInt(bf.readLine());
        long LEFT = Long.parseLong(bf.readLine())+1L;
        long RIGHT = Long.parseLong(bf.readLine())+1L;
        int N = Integer.parseInt(bf.readLine());

        System.out.println(Arrays.stream(solution(NUM, LEFT, RIGHT, N)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static long[] solution(int NUM, long LEFT, long RIGHT, int N) {
        dp = findAll(N);
        long n = 1;
        for (int i=0; i<N; i++) {
            n*=3;
        }
        
        return dfs(NUM, 1, n, LEFT, RIGHT);
    }

    static long[] dfs(int num, long start, long len, long LEFT, long RIGHT) {
        if (len == 1) {
            long[] now = new long[3];
            now[num-1]++;
            return now;
        }

        len/=3;
        int n = findN(len);
        long[] result = new long[3];
        for (int i=0; i<3; i++) {
            long s = start + i*len;
            long e = start + (i+1) * len - 1;

            if (LEFT <= s && e <= RIGHT) {
                sumArr(result, dp[n][next[num][i]]);
            } else if ((LEFT <= e && s <= LEFT) || (s <= RIGHT && RIGHT <= e)) {
                sumArr(result, dfs(next[num][i], s, len, LEFT, RIGHT));
            }
        }
//        System.out.println("start = " + start);
//        System.out.println("len = " + len);
//        System.out.println("result = " + Arrays.toString(result));
        
        return result;
    }
    
    static void sumArr(long[] arr, long[] add) {
        for (int i=0; i<3; i++) {
            arr[i] += add[i];
        }
    }

    static int findN(long n) {
        long num=1;
        for (int i=0; i<22; i++) {
            if (n==num) return i;
            num*=3;
        }
        
        throw new NullPointerException();
    }

    static long[][][] findAll(int N) {
        long[][][] result = new long[N+1][4][3]; // N번 반복했을때 M을 결과
        result[0][1][0] = result[0][2][1] = result[0][3][2] = 1;

        for (int i=0; i<N; i++) {
            result[i+1][1][0] = result[i][1][0] + result[i][2][0] + result[i][3][0];
            result[i+1][1][1] = result[i][1][1] + result[i][2][1] + result[i][3][1];
            result[i+1][1][2] = result[i][1][2] + result[i][2][2] + result[i][3][2];


            result[i+1][2][0] = result[i][1][0] * 2 + result[i][2][0];
            result[i+1][2][1] = result[i][1][1] * 2 + result[i][2][1];
            result[i+1][2][2] = result[i][1][2] * 2 + result[i][2][2];


            result[i+1][3][0] = result[i][2][0] * 2 + result[i][3][0];
            result[i+1][3][1] = result[i][2][1] * 2 + result[i][3][1];
            result[i+1][3][2] = result[i][2][2] * 2 + result[i][3][2];
        }

        return result;
    }
}