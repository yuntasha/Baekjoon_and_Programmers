import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
이거 DP 문제 인듯
근데 트리 DP 문제
사장이 참여했을때와 참여하지 않을때를 찾아야 한다는 것
참여 = 자식미참여의 합 + 현재 값
미참여 = max(자식 참여의 합, 자식 미참여의 합)을 각각 해줘서 더해줌
만약 자식이 없으면
참여 = 현재값
미참여 = 0

순수 DP로 만들고 저거 전체 탐색 한번 해야할 것 같음

 */

public class Main {

    static long[][] DP; // 0은 미참여 1은 참여
    static long[] arr;
    static List<Integer>[] c;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        arr = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        DP = new long[N][2];

        c = new List[N];

        for (int i=0; i<N; i++) {
            c[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i=1; i<N; i++) {
            c[Integer.parseInt(st.nextToken())-1].add(i);
        }

        findDP(0);

        System.out.println(DP[0][1] + " " + DP[0][0]);

        List<Integer> l = new ArrayList<>();

        l.add(0);

        find(0, l);

        StringJoiner sj = new StringJoiner(" ");

        l.sort(Comparator.naturalOrder());

        for (int i : l) {
            sj.add(String.valueOf(i+1));
        }

        sj.add("-1");

        System.out.println(sj);

        l.clear();

        find(0, l);

        sj = new StringJoiner(" ");

        l.sort(Comparator.naturalOrder());

        for (int i : l) {
            sj.add(String.valueOf(i+1));
        }

        sj.add("-1");

        System.out.println(sj);
    }

    public static void findDP(int now) {
        for (int i : c[now]) {
            findDP(i);
            DP[now][0] += Math.max(DP[i][0], DP[i][1]);
            DP[now][1] += DP[i][0];
        }

        DP[now][1] += arr[now];
    }

    public static void find(int now, List<Integer> comeList) {
        if (!comeList.isEmpty() && comeList.get(comeList.size()-1) == now) { // 참여했다면
            for (int p : c[now]) {
                find(p, comeList); // 다음은 미참여로
            }
        } else {
            for (int p : c[now]) {
                if (DP[p][0] < DP[p][1]) { // 자식이 참여한게 더 크다면
                    comeList.add(p); // 참여시킴
                }
                find(p, comeList);
            }
        }
    }
}
