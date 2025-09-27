/*
그냥 dfs로 찾는데 이전 방문 금애보다 크면 가고 아니면 가지말자
 */


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static char EMPTY = 'E';
    static char LEFICON = 'L';
    static char TROLL = 'T';

    static String YES = "Yes";
    static String NO = "No";

    static int MAX = 500;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N;

        StringBuilder output = new StringBuilder();

        while ((N = Integer.parseInt(bf.readLine())) > 0) {

            List<Room> rooms = new ArrayList<>();

            rooms.add(null);

            for (int i = 0; i < N; i++) rooms.add(new Room(bf.readLine()));

            output.append(solution(N, rooms) ? YES : NO).append("\n");
        }

        System.out.print(output);
    }

    public static boolean solution(int N, List<Room> rooms) {
        boolean[][] visited = new boolean[N + 1][MAX + 1];
        boolean result = dfs(1, 0, N, rooms, visited);
//        System.out.println(Arrays.toString(visited));
        return result;
    }

    public static boolean dfs(int n, int cost, int N, List<Room> rooms, boolean[][] visited) {
        if (!rooms.get(n).canGo(cost)) return false;
        if (n == N) return true;

        cost = rooms.get(n).calCost(cost);
        if (visited[n][cost]) return false;
        visited[n][cost] = true;

        for (int nextRoomId : rooms.get(n).nextRoom) {
            if (dfs(nextRoomId, cost, N, rooms, visited)) return true;
        }

        return false;
    }

    public static class Room {
        char type;
        int cost;
        List<Integer> nextRoom;

        Room(String s) {
            StringTokenizer input = new StringTokenizer(s);

            this.type = input.nextToken().charAt(0);
            this.cost = Integer.parseInt(input.nextToken());
            this.nextRoom = new ArrayList<>();

            int n;

            while ((n = Integer.parseInt(input.nextToken())) > 0) nextRoom.add(n);
        }

        boolean canGo(int cost) {
//            System.out.println(type + " " + cost + " " + this.cost);
//            System.out.println(type != TROLL || cost >= this.cost);
            return type != TROLL || cost >= this.cost;
        }

        int calCost(int cost) {
            if (type == LEFICON) return Math.max(cost, this.cost);
            if (type == TROLL) return cost - this.cost;
            return cost;
        }
    }
}