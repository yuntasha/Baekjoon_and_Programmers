/*
정렬 기준이 좀 빡세네
그럼 그냥 일단 맵을 사용해서 총 집계를 하고 그걸 리스트에 일단 넣자
그 다음에 정렬 후 출력부에 넣는거지
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        List<Team> teamList = new ArrayList<>();
        Map<String, Team> teamMap = new HashMap<>();

        String input;

        while (!(input = bf.readLine()).equals("#")) {
            Team t = new Team(input);
            teamMap.put(input, t);
            teamList.add(t);
        }

        int round = 1;
        int count = 0;

        StringBuilder output = new StringBuilder();

        while (!(input = bf.readLine()).equals("#")) {
            int[] info = new int[4];

            StringTokenizer st = new StringTokenizer(input);

            Team a = teamMap.get(st.nextToken());
            Team b = teamMap.get(st.nextToken());

            info[0] = Integer.parseInt(st.nextToken());
            info[1] = Integer.parseInt(st.nextToken());
            info[2] = Integer.parseInt(st.nextToken());
            info[3] = Integer.parseInt(st.nextToken());

            Team.match(a, b, info);
            count++;

            if (count == teamList.size() / 2) {
                count = 0;
                teamList.sort(Comparator.naturalOrder());

                output.append("Round ").append(round).append("\n");
                for (Team t : teamList) {
                    output.append(t.toString()).append("\n");
                }

                output.append("\n");

                round++;
            }
        }

        System.out.print(output.substring(0, output.length() - 2));
    }

    public static class Team implements Comparable<Team> {
        String name;
        int teamScore;
        int score;
        int oppoScore;
        int tryCount;
        int oppoTryCount;

        public Team(String name) {
            this.name = name;
            this.teamScore = 0;
            this.score = 0;
            this.oppoScore = 0;
            this.tryCount = 0;
            this.oppoTryCount = 0;
        }

        public static void match(Team t1, Team t2, int[] info) {
            if (info[0] == info[1]) {
                t1.teamScore += 2;
                t2.teamScore += 2;
            } else if (info[0] > info[1]) {
                t1.teamScore += 4;
                if (info[0] - info[1] < 8) t2.teamScore += 1;
            } else {
                t2.teamScore += 4;
                if (info[1] - info[0] < 8) t1.teamScore += 1;
            }

            if (info[2] >= 4) t1.teamScore += 1;
            if (info[3] >= 4) t2.teamScore += 1;

            t1.score += info[0];
            t2.score += info[1];

            t1.oppoScore += info[1];
            t2.oppoScore += info[0];

            t1.tryCount += info[2];
            t2.tryCount += info[3];

            t1.oppoTryCount += info[3];
            t2.oppoTryCount += info[2];
        }

        @Override
        public int compareTo(Team o) {
            if (this.teamScore != o.teamScore) return Integer.compare(o.teamScore, this.teamScore);
            if (this.score - this.oppoScore != o.score - o.oppoScore) return Integer.compare(o.score - o.oppoScore, this.score - this.oppoScore);
            if (this.tryCount != o.tryCount) return Integer.compare(o.tryCount, this.tryCount);
            return this.name.compareToIgnoreCase(o.name);
        }

        @Override
        public String toString() {
            return String.format("%-21s%2d%4d%4d%3d%3d", this.name, this.teamScore, this.score, this.oppoScore, this.tryCount, this.oppoTryCount);
        }
    }
}