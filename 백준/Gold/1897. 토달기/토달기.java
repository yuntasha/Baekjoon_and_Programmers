import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
닫힌 다각형 즉 한 선분 서로 공유된다는 소리이기도 하다
따라서 선분이 서로 인접하다면 서로 움직이는게 가능하다는 소리이기도 함
같은 선분을 가진다면 그것은 연결됐다는 소리
ㄷㄱㅈ
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());

        String start = input.nextToken();
        List<String> words = new ArrayList<>();
        List<List<Integer>> next = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            next.add(new ArrayList<>());
        }

        int startIdx = -1;

        for (int i = 0; i < N; i++) {
            words.add(bf.readLine());

            if (words.get(words.size() - 1).equals(start)) {
                startIdx = i;
            }

            for (int j = 0; j < i; j++) {
                if (words.get(i).length() + 1 == words.get(j).length()) {
                    if (isChange(words.get(i), words.get(j))) {
                        next.get(i).add(j);
                    }
                }

                if (words.get(i).length() == words.get(j).length() + 1) {
                    if (isChange(words.get(j), words.get(i))) {
                        next.get(j).add(i);
                    }
                }
            }
        }

        System.out.println(words.get(solution(N, startIdx, next)));
    }

    static int solution(int N, int startIdx, List<List<Integer>> next) {
        int now = startIdx;

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(now);
        boolean[] visited = new boolean[N];

        while (!q.isEmpty()) {
            now = q.remove();

            for (int n : next.get(now)) {
                if (visited[n]) continue;
                visited[n] = true;

                q.add(n);
            }
        }

        return now;
    }

    static boolean isChange(String s1, String s2) {
        int i1 = 0;
        int i2 = 0;

        while (i1 < s1.length() && i2 < s2.length()) {
            if (s1.charAt(i1) == s2.charAt(i2)) {
                i1++;
            }
            i2++;
        }

        return s1.length() == i1 && (s2.length() == i2 || s2.length() - 1 == i2);
    }
}