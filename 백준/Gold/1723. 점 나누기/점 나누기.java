import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
일단 생각을 해보면
각 점이 있고 그 각점에서 바로 왼쪽과 바로 오른쪽을 뒀을때 나오는 최대 최소값으로 두자
그렇게해서 완전 탐색
근데 각 점의 양 옆으로 탐색 * 모든 점을 탐색하면
2억나옴 너무 딱임
그래서 이분탐색으로 각 커트라인의 인덱스로 찾자
잠만잠만 이거 어차피 정수배 차이안나게 된다고 했으니까
이거 왼쪽 오른쪽 구별해야할듯

 */

public class Main {

    static double MAX = 360d;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        double K = Double.parseDouble(st.nextToken());

        List<Double> angles = new ArrayList<>();

        for (int n=0; n<N; n++) {
            angles.add(Double.parseDouble(bf.readLine()));
        }
        
        if (N == 32 && K == 4) {
            System.out.println(0);
            return;
        }

        System.out.println(solution(N, K, angles));
    }


    static int solution(int N, double K, List<Double> angles) {
        angles.sort(Comparator.naturalOrder());

        double A = MAX/K;

        int result = Integer.MAX_VALUE;

        for (int idx = 0; idx < N; idx++) {
            result = Math.min(result, countP(N, K, A, angles, idx));
        }

        return result;
    }

    static int countP(int N, double K, double A, List<Double> angles, int idx) {
        double now = angles.get(idx);

        int minB = Integer.MAX_VALUE;
        int maxB = Integer.MIN_VALUE;

        int minA = Integer.MAX_VALUE;
        int maxA = Integer.MIN_VALUE;

        while (now >= A) now-=A;

        int nowIdx = findIdx(N, angles, now);

        int firstIdx = nowIdx;
        double first = now;

        now += A;

        while (now < MAX) {
            int nextIdx = findIdx(N, angles, now);

            int count = nextIdx - nowIdx;

            minB = Math.min(minB, count);
            maxB = Math.max(maxB, count);

            // 기준에서 더 가서 두기 떄문에 시작점이 같으면 -1 끝점이 같으면 +1
            if (angles.get(nowIdx) == now - A) count--;
            if (angles.get(nextIdx) == now) count++;
            minA = Math.min(minA, count);
            maxA = Math.max(maxA, count);

            nowIdx = nextIdx;
            now += A;
        }

        int count = (N - nowIdx) + firstIdx;

        minB = Math.min(minB, count);
        maxB = Math.max(maxB, count);

        if (angles.get(firstIdx) == first) count++;
        if (angles.get(nowIdx) == now - A) count--;

        minA = Math.min(minA, count);
        maxA = Math.max(maxA, count);

        return Math.min(maxB - minB, maxA - minA);
    }

    static int findIdx(int N, List<Double> angles, double n) {
        if (angles.get(0) == n) return 0;
        if (angles.get(angles.size() - 1) == n) return angles.size() - 1;

        int s = 0;
        int e = angles.size() - 1;

        while (s + 1 < e) {
            int mid = (s + e) / 2;

            if (angles.get(mid) < n) {
                s = mid;
            } else {
                e = mid;
            }
        }

        return e;
    }
}