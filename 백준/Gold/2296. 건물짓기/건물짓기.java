/*
각 건물의 위치를 바탕으로 설계
DP로 풀면 될 것 같다

 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Building> buildings = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());

            buildings.add(new Building(x, y, v));
        }

        System.out.println(solution(N, buildings));
    }

    public static int solution(int N, List<Building> buildings) {
        int[] upDP = new int[N];
        int[] downDP = new int[N];

        buildings.sort(Comparator.comparingInt(Building::getX));

        for (int i = 0; i < N; i++) {
            upDP[i] = downDP[i] = buildings.get(i).v;

            for (int j = 0; j < i; j++) {
                if (buildings.get(j).y < buildings.get(i).y) upDP[i] = Math.max(upDP[i], upDP[j] + buildings.get(i).v);
                else downDP[i] = Math.max(downDP[i], downDP[j] + buildings.get(i).v);
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            result = Math.max(result, upDP[i]);
            result = Math.max(result, downDP[i]);
        }

        return result;
    }

    public static class Building {
        int x;
        int y;
        int v;

        public Building(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        public int getX() {
            return x;
        }
    }
}