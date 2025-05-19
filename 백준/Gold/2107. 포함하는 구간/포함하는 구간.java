import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
누적합아닌가?
트리맵으로 그냥 +1 -1 이렇게 하자

아아...
하나가 다른 하나를 얼마나 가지고 있는지 확인하는 느낌인가

시작점 기준 정렬하고
최대 지점까지되는곳 까지 찾아서 그냥 최댓값 찾아야하나?
2.5만이라 6억정도 될텐데..?
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Range> ranges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int s = Integer.parseInt(input.nextToken());
            int e = Integer.parseInt(input.nextToken());

            ranges.add(new Range(s, e));
        }

        System.out.println(solution(N, ranges));
    }

    static int solution(int N, List<Range> ranges) {
        int result = 0;

        ranges.sort(Comparator.comparingInt(Range::getS));

        for (int i = 0; i < N; i++) {
            int now = 0;
            for (int j = i + 1; j < N; j++) {
                if (ranges.get(i).e < ranges.get(j).s) break;
                if (ranges.get(i).isIn(ranges.get(j))) now++;
            }
            result = Math.max(result, now);
        }

        return result;
    }

    static class Range {
        int s;
        int e;

        public Range(int s, int e) {
            this.s = s;
            this.e = e;
        }

        boolean isIn(Range r) {
            return r.e < e;
        }

        public int getS() {
            return s;
        }
    }
}