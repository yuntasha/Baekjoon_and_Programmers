import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
이리저리 바꿔보자
트리에서 그대로, 반전으로 넘김
어떻게?
t1에서 idx범위, t2에서 idx범위
각각의 최대를 넘겨줌
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(bf.readLine());

        int[] t1 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] t2 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(k, t1, t2));
    }

    static int solution(int k, int[] t1, int[] t2) {
        return find(0, t1.length-1, 0, t2.length-1, t1, t2);
    }

    static int find(int s1, int e1, int s2, int e2, int[] t1, int[] t2) {
        if (s1 == e1 && s2 == e2) {
            return (t1[s1] == t2[s2]) ? 1 : 0;
        }

        int m1 = (s1 + e1) >> 1;
        int m2 = (s2 + e2) >> 1;

        return Math.max(
                find(s1, m1, s2, m2, t1, t2) + find(m1 + 1, e1, m2 + 1, e2, t1, t2),
                find(s1, m1, m2 + 1, e2, t1, t2) + find(m1 + 1, e1, s2, m2, t1, t2)
        );
    }
}
