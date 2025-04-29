/*
증가하는 가장 긴 부분수열
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) lines.add(new Line(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));

        System.out.println(solution(N, lines));
    }

    static int solution(int N, List<Line> lines) {
        List<Integer> dp = new ArrayList<>();

        lines.sort(Comparator.comparingInt(Line::getX));

        for (int i = 0; i < N; i++) {
            int idx = find(lines.get(i).y, dp);
            if (dp.size() == idx) {
                dp.add(lines.get(i).y);
            }
            dp.set(idx, Math.min(dp.get(idx), lines.get(i).y));
        }

        return N - dp.size();
    }

    static int find(int n, List<Integer> dp) {
        if (dp.isEmpty()) return 0;
        if (dp.get(0) >= n) return 0;
        if (dp.get(dp.size() - 1) < n) return dp.size();

        int s = 0;
        int e = dp.size() - 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (dp.get(m) < n) {
                s = m;
            } else {
                e = m;
            }
        }

        return e;
    }

    static class Line {
        int x;
        int y;

        public Line(int[] input) {
            this.x = input[0];
            this.y = input[1];
        }

        public int getX() {
            return x;
        }
    }
}