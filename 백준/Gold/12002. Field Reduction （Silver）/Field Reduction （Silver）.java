/*
그럼 x끼리 y끼리 정렬
그리고 가장 마지막에서 3번째까지 같은 좌표인 애들까지 쭈루룩
아니 어차피 같은 애들이면 그건 좌표가 안줄어들어 = 의미가 없는 애들
아 그냥 3개씩해서 12개 뽑아 ㅋㅋ...
같은거 3개 빼도 괜찮잖아?
음
그럼 총 12개를 뽑고
4000에 5000이면 2초가 나오는데 정렬이 있으니 거르자
맵을 사용해서 앞뒤 3개씩가져올때 그 결과를 가져오면?
상하좌우로 나눠버린다면?
아이디 번호로 가져오는건 어떤데
셋에 아이디 번호넣고 해보자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Node> list = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            list.add(new Node(i, Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, list));
    }

    public static int solution(int N, List<Node> list) {
        int[] xID = new int[N];
        int[] yID = new int[N];

        list.sort(Comparator.comparingInt(Node::getX));

        for (int i = 0; i < N; i++) xID[i] = list.get(i).id;

        list.sort(Comparator.comparingInt(Node::getY));

        for (int i = 0; i < N; i++) yID[i] = list.get(i).id;

        list.sort(Comparator.comparingInt(Node::getId));

        Set<Integer> maybeRemove = new HashSet<>();

        maybeRemove.add(xID[0]);
        maybeRemove.add(xID[1]);
        maybeRemove.add(xID[2]);

        maybeRemove.add(xID[N - 3]);
        maybeRemove.add(xID[N - 2]);
        maybeRemove.add(xID[N - 1]);

        maybeRemove.add(yID[0]);
        maybeRemove.add(yID[1]);
        maybeRemove.add(yID[2]);

        maybeRemove.add(yID[N - 3]);
        maybeRemove.add(yID[N - 2]);
        maybeRemove.add(yID[N - 1]);

        return find(0, xID, yID, list, N, maybeRemove, new HashSet<>());
    }

    static int find(int depth, int[] xID, int[] yID, List<Node> list, int N, Set<Integer> maybeRemove, Set<Integer> realRemove) {
        if (depth == 3) {
            int ex = N - 1;
            while (realRemove.contains(xID[ex])) ex--;

            int sx = 0;
            while (realRemove.contains(xID[sx])) sx++;

            int ey = N - 1;
            while (realRemove.contains(yID[ey])) ey--;

            int sy = 0;
            while (realRemove.contains(yID[sy])) sy++;

            return (list.get(xID[ex]).x - list.get(xID[sx]).x) * (list.get(yID[ey]).y - list.get(yID[sy]).y);
        }

        int result = Integer.MAX_VALUE;

        for (int id : maybeRemove) {
            if (realRemove.contains(id)) continue;
            realRemove.add(id);
            result = Math.min(result, find(depth + 1, xID, yID, list, N, maybeRemove, realRemove));
            realRemove.remove(id);
        }

        return result;
    }

    public static class Node {
        int id;
        int x;
        int y;

        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public int getId() {
            return id;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}