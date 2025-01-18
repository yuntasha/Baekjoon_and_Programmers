import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
악 어려워
배낭 문제같이 진입해보자
일단 순서대로 정렬해주고
2차원 배열로 걔네들이 서로 같이 둘 수 있는지 확인
DP[어떤 책까지 가능한지][현재 책꽂이에 있는 책의 개수][마지막 책의 번호] = 최대 개수

*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        double v = Double.parseDouble(st.nextToken());
        double m = Double.parseDouble(st.nextToken());

        List<Bunker> bunkers = new ArrayList<>();

        String input;

        bunkers.add(new Bunker(bf.readLine()));
        Bunker end = new Bunker(bf.readLine());

        while ((input = bf.readLine()) != null && !input.isEmpty()) {
            bunkers.add(new Bunker(input));
        }

        bunkers.add(end);

        System.out.println(solution(v, m, bunkers));
    }

    static String solution(double v, double m, List<Bunker> bunkers) {
        double MAX_MOVE = v * m * 60;

        boolean[][] isPossible = new boolean[bunkers.size()][bunkers.size()];

        for (int i = 0; i < bunkers.size(); i++) {
            for (int j = i + 1; j < bunkers.size(); j++) {
                isPossible[i][j] = isPossible[j][i] = canRun(bunkers.get(i), bunkers.get(j), MAX_MOVE);
            }
        }

        int[] visited = new int[bunkers.size()];

        visited[0] = 1;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int i = 0; i < bunkers.size(); i++) {
                if (!isPossible[now][i]) continue;
                if (visited[i] > 0) continue;
                visited[i] = visited[now] + 1;
                q.add(i);
            }
        }

        if (visited[visited.length-1] > 0) {
            return isSuccess(visited[visited.length-1]-2);
        }
        return isFail();
    }

    static boolean canRun(Bunker b1, Bunker b2, double MAX_MOVE) {
        return MAX_MOVE * MAX_MOVE >= (b1.x - b2.x) * (b1.x - b2.x) + (b1.y - b2.y) * (b1.y - b2.y);
    }

    static String isSuccess(int N) {
        return String.format("Yes, visiting %d other holes.", N);
    }

    static String isFail() {
        return "No.";
    }

    static class Bunker {
        double x;
        double y;

        Bunker(String input) {
            String[] s = input.split(" ");
            this.x = Double.parseDouble(s[0]);
            this.y = Double.parseDouble(s[1]);
        }
    }
}