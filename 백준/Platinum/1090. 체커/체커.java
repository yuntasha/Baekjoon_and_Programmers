import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Integer> xArr = new ArrayList<>();
        List<Integer> yArr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            xArr.add(Integer.parseInt(input.nextToken()));
            yArr.add(Integer.parseInt(input.nextToken()));
        }

        System.out.println(solution(N, xArr, yArr));
    }

    public static String solution(int N, List<Integer> xArr, List<Integer> yArr) {
        int[] result = new int[N];

        Arrays.fill(result, Integer.MAX_VALUE);

        for (int x : xArr) {
            for (int y : yArr) {
                List<Integer> dis = new ArrayList<>();

                for (int i = 0; i < N; i++) {
                    dis.add(Math.abs(xArr.get(i) - x) + Math.abs(yArr.get(i) - y));
                }

                dis.sort(Comparator.naturalOrder());

                int now = 0;
                for (int i = 0; i < N; i++) {
                    now += dis.get(i);
                    result[i] = Math.min(result[i], now);
                }
            }
        }

        return Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }
}