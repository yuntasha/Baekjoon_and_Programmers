import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int G = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, arr, G));
    }

    static int solution(int N, int[] arr, int G) {
        Arrays.sort(arr);

        int s = 0;
        int e = arr.length - 1;
        int result = 0;

        while (s < e) {
            if (arr[s] + arr[e] == G) {
                result++;
                s++;
                e--;
            } else if (arr[s] + arr[e] < G) {
                s++;
            } else {
                e--;
            }
        }

        return result;
    }
}