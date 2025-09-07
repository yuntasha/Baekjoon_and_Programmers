/*
음 정렬하고 그냥 위치 맞춰서 옮기면 안되나
배열 2개로 입력받고
그리고 Map을 통해서 이전 위치 받아내고
정렬된 위치를 받아서 정렬될 인덱스로 그냥 계속 넘겨버려

아 위치 계속 옮겨지니까
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];
        int[] sArr = new int[N];

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) arr[i] = sArr[i] = Integer.parseInt(input.nextToken());

        System.out.print(solution(N, arr, sArr));
    }

    public static int solution(int N, int[] arr, int[] sArr) {
        Arrays.sort(sArr);

        HashMap<Integer, Integer> vToI = new HashMap<>();

        for (int i = 0; i < N; i++) {
            vToI.put(sArr[i], i);
        }

        int result = 0;
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            result += find(i, arr, vToI, visited) - 1;
        }

        return result;
    }

    public static int find(int n, int[] arr, Map<Integer, Integer> vToI, boolean[] visited) {
        if (visited[n]) return 0;
        visited[n] = true;
        return find(vToI.get(arr[n]), arr, vToI, visited) + 1;
    }
}