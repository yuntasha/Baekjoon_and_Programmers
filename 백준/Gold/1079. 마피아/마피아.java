import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var point = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        arr = new int[N][N];

        for (int i=0; i<N; i++){
            arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        var M = Integer.parseInt(bf.readLine());
        System.out.println(solution(N, M, point));
    }

    static int solution(int N, int M, int[] point){
        return find(N, M, new boolean[N], point, N);
    }

    static int find(int N, int M, boolean[] isDead, int[] now, int remain) {
        var dead = -1;
        if (remain%2==1) {
            dead = day(N, isDead, now);
            if (dead==M) {
                isDead[dead] = false;
                return 0;
            }
            remain--;
        }
        var result = 0;
        for (int i=0; i<N; i++) {
            if (i==M || isDead[i]) continue;
            isDead[i] = true;
            for (int n=0; n<N; n++) {
                now[n] += arr[i][n];
            }
            result = Math.max(result, find(N, M, isDead, now, remain-1)+1);
            isDead[i] = false;
            for (int n=0; n<N; n++) {
                now[n] -= arr[i][n];
            }
        }
        if (dead>=0) isDead[dead] = false;
        return result;
    }

    static int day(int N, boolean[] isDead, int[] now){
        var maxValue = 0;
        var maxNum = -1;
        for (int i=0; i<N; i++) {
            if (isDead[i] || maxValue>=now[i]) continue;
            maxValue = now[i];
            maxNum = i;
        }
        if (maxNum>=0) isDead[maxNum] = true;
        return maxNum;
    }

}