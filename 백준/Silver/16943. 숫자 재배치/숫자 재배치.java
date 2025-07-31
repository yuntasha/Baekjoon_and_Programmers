import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        long A = Long.parseLong(input.nextToken());
        long B = Long.parseLong(input.nextToken());

        System.out.println(solution(A, B));
    }

    public static long solution(long A, long B) {
        int[] arr = convert(A);

        Arrays.sort(arr);

        return findC(0, 0, arr, B);
    }

    public static long findC(int sum, int bits, int[] arr, long B) {
        if (bits == ((1 << arr.length) - 1)) {
            return sum < B ? sum : -1;
        }

        long result = -1;

        for (int i = arr.length - 1; i >= 0; i--) {
            if ((bits & (1 << i)) > 0) continue;
            if (sum == 0 && arr[i] == 0) continue;
            result = Math.max(result, findC(sum * 10 + arr[i], bits | (1 << i), arr, B));
        }

        return result;
    }

    public static int[] convert(long L) {
        int n = String.valueOf(L).length();

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = (int) (L % 10);
            L /= 10;
        }

        return result;
    }

    public static long convert(int[] arr) {
        long result = 0;

        for (int i : arr) {
            result *= 10;
            result += i;
        }

        return result;
    }
}