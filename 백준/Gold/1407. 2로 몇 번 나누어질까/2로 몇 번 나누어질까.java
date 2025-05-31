import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그냥 깡으로 해버리면 무조건 시간초과임
그러니까 16까지 16은 1개가 나옴
2 4 6 8 10 12
절반만 사용
/2하고 그걸 전체에서 빼줌
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        long A = Long.parseLong(input.nextToken());
        long B = Long.parseLong(input.nextToken());
        System.out.println(solution(A, B));
    }

    public static long solution(long A, long B) {
        return getSum(B) - getSum(A - 1);
    }

    public static long getSum(long N) {
        long result = 0;
        long now = 0;

        while ((1L << now) <= N) {
            result += ((N >> now) - (N >> (now + 1))) * (1L << now);
            now++;
        }

        return result;
    }
}