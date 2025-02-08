import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
한 직선 위에 있는 것들을 y기준 다음 x 절대값으로 정렬 후 앞에 것과 비교
10억까지 그냥 long으로 해당 값 만들어서 맵에 옮김
그 다음에 키마다 찾고 정렬
정렬한 애들로 막히는것 조회
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        Map<Double, List<Node>> map = new TreeMap<>();



        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            Node now = new Node(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()));

            double p = now.getPoint();

            if (!map.containsKey(p)) {
                map.put(p, new ArrayList<>());
            }
            map.get(p).add(now);
        }

        System.out.println(solution(N, map).stream().map(n -> n.x + " " + n.y).collect(Collectors.joining("\n")));
    }

    static List<Node> solution(int N, Map<Double, List<Node>> map) {
        List<Node> result = new ArrayList<>();

        for (List<Node> l : map.values()) {
            l.sort(Comparator.comparingInt(Node::getAbsX)
                    .thenComparingInt(Node::getY));
            int max = -1;

            for (Node n : l) {
                if (n.z > max) {
                    max = n.z;
                } else {
                    result.add(n);
                }
            }
        }

        result.sort(Comparator.comparingInt(Node::getX)
                .thenComparingInt(Node::getY));

        return result;
    }

    static class Node {
        int x;
        int y;
        int z;

        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getAbsX() {
            return Math.abs(x);
        }

        public int getY() {
            return y;
        }

        public double getPoint() {
            return (double) x / (double) y;
        }
    }
}