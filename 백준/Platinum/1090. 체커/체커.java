import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] xArr = new int[N];
        int[] yArr = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            xArr[i] = Integer.parseInt(input.nextToken());
            yArr[i] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, xArr, yArr));
    }

    public static String solution(int N, int[] xArr, int[] yArr) {
        int[] result = new int[N];

        Arrays.fill(result, Integer.MAX_VALUE);

        for (int x : xArr) {
            for (int y : yArr) {
                List<Integer> dis = new ArrayList<>();

                for (int i = 0; i < N; i++) {
                    dis.add(Math.abs(xArr[i] - x) + Math.abs(yArr[i] - y));
                }

                dis.sort(Comparator.naturalOrder());

                int now = 0;
                for (int i = 0; i < N; i++) {
                    now += dis.get(i);
                    result[i] = Math.min(result[i], now);
                }
            }
        }

        StringBuilder output = new StringBuilder();

        output.append(result[0]);

        for (int i = 1; i < N; i++) {
            output.append(" ").append(result[i]);
        }

        return output.toString();
    }
}