import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, arr));
    }

    public static int solution(int N, int[] arr) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) set.add(arr[i]);

        int result = 0;

        for (int r : set) {
            int prev = -1;
            int now = 0;
            for (int n : arr) {
                if (n == r) continue;
                if (prev != n) now = 0;
                prev = n;
                now++;
                result = Math.max(result, now);
            }
        }

        return result;
    }
}