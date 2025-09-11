/*
N = 4
2번째 값을 가져와야한다
인덱스 = 1
짝수면 N / 2 - 1

N = 5
3번째 값을 가져와야 한다
인덱스 2
홀수면 N / 2
 */

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(bf.readLine());

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, arr));
    }

    public static int solution(int N, int[] arr) {
        Arrays.sort(arr);

        if (N % 2 == 1) return arr[N / 2];

        return arr[N / 2 - 1];
    }
}