/*
그럼 일단
차이를 구해
9000 * 1000
9_000_000
음 ㄱㅊ을듯?
가장 작은 것과 가장 큰 것을 하나씩 넣고 확인해보면 될듯?
가장 작은게 들어가서 나올 최소 값 찾기
 */



import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[] saja = new int[K];

        for (int i = 0; i < K; i++) saja[i] = Integer.parseInt(bf.readLine());

        int[] arr = new int[N - K];

        for (int i = 0; i < N - K; i++) arr[i] = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, K, saja, arr));
    }

    public static int solution(int N, int K, int[] saja, int[] arr) {
        int result = 0;

        for (int i = 1; i < K; i++) result += Math.abs(saja[i] - saja[i - 1]);

        Arrays.sort(arr);

        int min = Math.min(Math.abs(saja[0] - arr[0]), Math.abs(saja[K - 1] - arr[0]));

        for (int i = 1; i < K; i++) min = Math.min(min, Math.abs(saja[i - 1] - arr[0]) + Math.abs(saja[i] - arr[0]) - Math.abs(saja[i] - saja[i - 1]));

        result += min;

        min = Math.min(Math.abs(saja[0] - arr[N - K - 1]), Math.abs(saja[K - 1] - arr[N - K - 1]));

        for (int i = 1; i < K; i++) min = Math.min(min, Math.abs(saja[i - 1] - arr[N - K - 1]) + Math.abs(saja[i] - arr[N - K - 1]) - Math.abs(saja[i] - saja[i - 1]));

        result += min;

        return result;
    }
}