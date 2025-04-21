/*
정확하게 다각형 선만이 존재해야 다각형으로 인정
각 선분은 양 끝점의 좌표가 주어짐
해시 맵으로 점 저장, 인덱스 부여
2차원 배열로 각 점의 연결 정리
최대 120개의 점이 있음
DFS로 탐색
    - 2개의 점만이 연결
    - 3개이상 점이 연결되서 돌아올 것

탐색 시작이 선이 2개로 이루어져 있으면
현재꺼 방문 시켜두고 왼쪽에 있는거 스타트, 오른쪽에 도착하면 완료
이러면 3개 이상되고 2개인지 체크하면 굿인데?
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        HashMap<Point, Integer> pointToInt = new HashMap<>();
        List<List<Integer>> connects = new ArrayList<>();

        int idx = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int x1 = Integer.parseInt(input.nextToken());
            int y1 = Integer.parseInt(input.nextToken());
            int x2 = Integer.parseInt(input.nextToken());
            int y2 = Integer.parseInt(input.nextToken());

            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);

            if (!pointToInt.containsKey(p1)) {
                pointToInt.put(p1, idx++);
                connects.add(new ArrayList<>());
            }

            if (!pointToInt.containsKey(p2)) {
                pointToInt.put(p2, idx++);
                connects.add(new ArrayList<>());
            }

            int i1 = pointToInt.get(p1);
            int i2 = pointToInt.get(p2);

            connects.get(i1).add(i2);
            connects.get(i2).add(i1);
        }

        System.out.println(solution(connects));
    }

    static int solution(List<List<Integer>> connects) {
        int result = 0;
        boolean[] visited = new boolean[connects.size()];

        for (int i = 0; i < connects.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if (connects.get(i).size() != 2) continue;
            if (isShape(connects.get(i).get(0), connects, visited, connects.get(i).get(1))) {
                result++;
            }
        }

        return result;
    }

    static boolean isShape(int now, List<List<Integer>> connect, boolean[] visited, int arrive) {
        if (visited[now]) return false;
        visited[now] = true;
        if (connect.get(now).size() != 2) return false;
        if (now == arrive) return true;
        return isShape(connect.get(now).get(0), connect, visited, arrive) || isShape(connect.get(now).get(1), connect, visited, arrive);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}