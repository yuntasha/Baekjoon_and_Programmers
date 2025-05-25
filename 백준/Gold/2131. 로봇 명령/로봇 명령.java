import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
Forward : 로봇이 한 칸 앞으로 이동한다.
Turn Left : 로봇이 왼쪽으로 90도만큼 회전한다.
Turn Right : 로봇이 오른쪽으로 90도만큼 회전한다.
Scan : 로봇의 한 칸 앞에서 지뢰를 찾아본다.

스캔하는 위치만 같으면 된다는 얘기인 것 같은데

8
Forward
Forward
Turn Left
Forward
Scan
Turn Right
Scan
Forward

스캔 위치 찾아서 거기까지 최단으로 박는거 생각하자
어차피 대가리 돌려야해서 손해임

이거 근데 x부터 움직이냐 y부터 움직이냐가 또 문제임
현재 위치랑 아예 같으면 어떻게 처리해야하지
어디 앞뒤로 움직여야함

어차피 상하좌우니까 상하좌우로 그냥 움직여
그 다음에 방향을 고려해서 덧셈해주자

아 스캔할때 자동으로 고개가 돌아가? 개사기네..
 */

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int R = 1;
    static int L = -1;
    static String FORWARD = "Forward";
    static String SCAN = "Scan";
    static String TURN = "Turn";
    static String LEFT = "Left";
    static String RIGHT = "Right";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<String> commands = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            commands.add(bf.readLine());
        }

        System.out.println(solution(N, commands));
    }

    static int solution(int N, List<String> commands) {
        List<Scan> scans = getScanP(N, commands);

        return count(scans);
    }

    static int count(List<Scan> scans) {
        int[][] result = new int[2][4];

        Scan start = scans.get(0);

        for (int d = 0; d < 4; d++) {
            int x = start.x - dx[d];
            int y = start.y - dy[d];

            result[0][d] = getCount(0, 0, x, y) + 1;
        }


        for (int i = 1; i < scans.size(); i++) {

            for (int n = 0; n < 4; n++) {
                result[i % 2][n] = Integer.MAX_VALUE;
            }

            for (int d1 = 0; d1 < 4; d1++) {
                int x1 = scans.get(i - 1).x - dx[d1];
                int y1 = scans.get(i - 1).y - dy[d1];
                for (int d2 = 0; d2 < 4; d2++) {
                    int x2 = scans.get(i).x - dx[d2];
                    int y2 = scans.get(i).y - dy[d2];
                    result[i % 2][d2] = Math.min(result[i % 2][d2], result[(i + 1) % 2][d1] + getCount(x1, y1, x2, y2) + 1);
                }
            }
        }

        int total = Integer.MAX_VALUE;

        for (int d = 0; d < 4; d++) {
            total = Math.min(total, result[(scans.size() + 1) % 2][d]);
        }

        return total;
    }

    static int getCount(int x1, int y1, int x2, int y2) {
        return (x1 == x2 ? 0 : 1) + (y1 == y2 ? 0 : 1);
    }

    static List<Scan> getScanP(int N, List<String> commands) {
        List<Scan> result = new ArrayList<>();

        int x = 0;
        int y = 0;
        int d = 0;

        for (String cmd : commands) {
            String[] c = cmd.split(" ");

            if (c[0].equals(FORWARD)) {
                x += dx[d];
                y += dy[d];
            } else if (c[0].equals(TURN)) {
                if (c[1].equals(RIGHT)) {
                    d += R;
                    d %= 4;
                } else {
                    d += L + 4;
                    d %= 4;
                }
            } else {
                result.add(new Scan(x + dx[d], y + dy[d]));
            }
        }

        return result;
    }

    static class Position {
        int x;
        int y;
        int d;

        public Position(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static class Scan {
        int x;
        int y;

        public Scan(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Scan{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}