/*
9 9 9 9
81 * 81
아니 0 - 9니까
대충 10^4면
10000이니까 1만
각 위치로 잡는게 낫겠다
아니 4장씩 9개니까
5 ^ 9
약 200만 나오네
제일 위가 숫자가 같아야한다
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static HashMap<Character, Integer> cToI = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        init();

        int[][] cards = new int[9][4];

        for (int i = 0; i < 9; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            cards[i][0] = cToI.get(input.nextToken().charAt(0));
            cards[i][1] = cToI.get(input.nextToken().charAt(0));
            cards[i][2] = cToI.get(input.nextToken().charAt(0));
            cards[i][3] = cToI.get(input.nextToken().charAt(0));
        }

        System.out.println(solution(cards));
    }

    static void init() {
        cToI.put('6', 0);
        cToI.put('7', 1);
        cToI.put('8', 2);
        cToI.put('9', 3);
        cToI.put('T', 4);
        cToI.put('J', 5);
        cToI.put('Q', 6);
        cToI.put('K', 7);
        cToI.put('A', 8);
    }

    static double solution(int[][] cards) {
        double[][][][][][][][][] dp = new double[5][5][5][5][5][5][5][5][5];
        boolean[][][][][][][][][] visited = new boolean[5][5][5][5][5][5][5][5][5];

        int[] now = new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4};

        return dfs(cards, now, 0, dp, visited);
    }

    static double dfs(int[][] cards, int[] now, int count, double[][][][][][][][][] dp, boolean[][][][][][][][][] visited) {
        if (count == 18) return 1;
        if (visited[now[0]][now[1]][now[2]][now[3]][now[4]][now[5]][now[6]][now[7]][now[8]]) return dp[now[0]][now[1]][now[2]][now[3]][now[4]][now[5]][now[6]][now[7]][now[8]];

        List<Double> percent = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (now[i] == 0) continue;
            for (int j = i + 1; j < 9; j++) {
                if (now[j] == 0) continue;
                if (cards[i][now[i] - 1] == cards[j][now[j] - 1]) {
                    now[i]--;
                    now[j]--;
                    percent.add(dfs(cards, now, count + 1, dp, visited));
                    now[i]++;
                    now[j]++;
                }
            }
        }

        visited[now[0]][now[1]][now[2]][now[3]][now[4]][now[5]][now[6]][now[7]][now[8]] = true;
        for (double p : percent) {
            dp[now[0]][now[1]][now[2]][now[3]][now[4]][now[5]][now[6]][now[7]][now[8]] += p / percent.size();
        }

        return dp[now[0]][now[1]][now[2]][now[3]][now[4]][now[5]][now[6]][now[7]][now[8]];
    }
}