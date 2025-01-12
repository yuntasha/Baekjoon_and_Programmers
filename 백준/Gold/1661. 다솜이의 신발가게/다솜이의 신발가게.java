import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        List<Integer>[] list = new List[4];

        list[1] = new ArrayList<>();
        list[2] = new ArrayList<>();
        list[3] = new ArrayList<>();

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());

            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            list[p].add(c);
        }

        System.out.println(solution(N, P, list));
    }

    static double solution(int N, int P, List<Integer>[] list) {
        double result = P;
        list[1].sort(Comparator.naturalOrder());
        list[2].sort(Comparator.naturalOrder());
        list[3].sort(Comparator.naturalOrder());
        for (int a=0; a<=list[1].size(); a++) {
            for (int b=0; b<=list[2].size(); b++) {
                for (int c=0; c<=list[3].size(); c++) {
                    result = Math.min(result, find(a, b, c, P, list));
                }
            }
        }

        return result;
    }

    static double find(int a, int b, int c, int P, List<Integer>[] list) {
        double result = 0;
        result += getSum(a, list[1]) + getSum(b, list[2]) + getSum(c, list[3]);
        return result + (double) P * (Math.pow(0.99, a) * Math.pow(0.98, b) * Math.pow(0.97, c));
    }

    static double getSum(int a, List<Integer> list) {
        double result = 0;
        for (int i=0; i<a; i++) {
            result += list.get(i);
        }
        return result;
    }
}