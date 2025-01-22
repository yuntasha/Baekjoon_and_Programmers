import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
깡 구현, 시뮬레이션인 느낌이다
그렇게 된다면
일단 체스판에 모든 것들을 다 저장한 후
결과 visited로 공격가능한 위치를 싹다 추출하는 방법말고는 없는 듯 하다
P - 폰 -> 앞으로 한칸 if 그 양 옆에 상대 기물 있으면 돌진 가능
N - 나이트 -> 1, 2와 같은 방식으로 점프
B - 비숍 -> 대각선 움직임 상대방 기물에 막히면 거기까지, 우리 기물이면 그 전까지
R - 룩 -> 직선 움직임 상대방 기물에 막히면 거기까지, 우리 기물이면 그 전까지
Q - 퀸 -> 대각선, 직선 움직임 상대방 기물에 막히면 거기까지, 우리 기물이면 그 전까지
K - 킹 -> 대각선, 직선 한칸
 */

public class Main {

    static char EMPTY = '.';
    static int[] NIGHT_DX = {1, 1, 2, 2, -1, -1, -2, -2};
    static int[] NIGHT_DY = {2, -2, 1, -1, 2, -2, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String input;

        StringJoiner result = new StringJoiner("\n");


        while ((input = bf.readLine()) != null && !input.isEmpty()) {
            result.add(String.valueOf(solution(input.toCharArray())));
        }

        System.out.println(result);
    }

    public static int solution(char[] input) {
        char[][] board = makeChessBoard(input);
        boolean[][] visited = new boolean[8][8];

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (board[i][j] == EMPTY) continue;
                make(i, j, board, visited);
            }
        }

        int result = 0;

        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (!visited[i][j] && board[i][j] == '.') result++;
            }
        }

        return result;
    }

    public static char[][] makeChessBoard(char[] input) {
        int i = 0;
        int j = 0;
        char[][] chessBoard = new char[8][8];

        for (int n=0; n<8; n++) {
            Arrays.fill(chessBoard[n], EMPTY);
        }

        for (char c : input) {
            if ('0' <= c && c <= '9') {
                i += c & 15;
            } else if (c == '/') {
                j++;
                i = 0;
            } else {
                chessBoard[j][i] = c;
                i++;
            }
        }

        return chessBoard;
    }

    public static void make(int n, int m, char[][] board, boolean[][] visited) {
        boolean isBlack = whatTeam(board[n][m]);

        if (board[n][m] == 'P') {
//            if (isIn(n-1, m) && board[n-1][m] == '.') {
//                visited[n-1][m] = true;
//            }

            if (isIn(n-1, m-1)) {
                visited[n-1][m-1] = true;
            }

            if (isIn(n-1, m+1)) {
                visited[n-1][m+1] = true;
            }
        } else if (board[n][m] == 'p') {
//            if (isIn(n+1, m) && board[n+1][m] == '.') {
//                visited[n+1][m] = true;
//            }

            if (isIn(n+1, m-1)) {
                visited[n+1][m-1] = true;
            }

            if (isIn(n+1, m+1)) {
                visited[n+1][m+1] = true;
            }
        } else if (board[n][m] == 'N' || board[n][m] == 'n') {


            for (int d = 0; d < 8; d++) {
                int dx = n + NIGHT_DX[d];
                int dy = m + NIGHT_DY[d];

                if (isIn(dx, dy)) {
                    attack(dx, dy, board, visited, isBlack);
                }
            }

        } else if (board[n][m] == 'B' || board[n][m] == 'b') {
            check(n, m, board, visited, isBlack, 1, 1);
            check(n, m, board, visited, isBlack, 1, -1);
            check(n, m, board, visited, isBlack, -1, 1);
            check(n, m, board, visited, isBlack, -1, -1);
        } else if (board[n][m] == 'R' || board[n][m] == 'r') {
            check(n, m, board, visited, isBlack, 0, 1);
            check(n, m, board, visited, isBlack, 0, -1);
            check(n, m, board, visited, isBlack, 1, 0);
            check(n, m, board, visited, isBlack, -1, 0);
        } else if (board[n][m] == 'Q' || board[n][m] == 'q') {
            check(n, m, board, visited, isBlack, 1, 1);
            check(n, m, board, visited, isBlack, 1, -1);
            check(n, m, board, visited, isBlack, -1, 1);
            check(n, m, board, visited, isBlack, -1, -1);
            check(n, m, board, visited, isBlack, 0, 1);
            check(n, m, board, visited, isBlack, 0, -1);
            check(n, m, board, visited, isBlack, 1, 0);
            check(n, m, board, visited, isBlack, -1, 0);
        } else if (board[n][m] == 'K' || board[n][m] == 'k') {
            if (isIn(n+1, m+1)) {
                attack(n + 1, m + 1, board, visited, isBlack);
            }
            if (isIn(n+1, m)) {
                attack(n + 1, m, board, visited, isBlack);
            }
            if (isIn(n+1, m-1)) {
                attack(n + 1, m - 1, board, visited, isBlack);
            }
            if (isIn(n, m+1)) {
                attack(n, m + 1, board, visited, isBlack);
            }
            if (isIn(n, m-1)) {
                attack(n, m - 1, board, visited, isBlack);
            }
            if (isIn(n-1, m+1)) {
                attack(n - 1, m + 1, board, visited, isBlack);
            }
            if (isIn(n-1, m)) {
                attack(n - 1, m, board, visited, isBlack);
            }
            if (isIn(n-1, m-1)) {
                attack(n - 1, m - 1, board, visited, isBlack);
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    public static boolean isIn(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    public static void check(int n, int m, char[][] board, boolean[][] visited, boolean isBlack, int dx, int dy) {
        int x = n + dx;
        int y = m + dy;

        while (isIn(x, y)) {
            if (!attack(x, y, board, visited, isBlack)) {
                break;
            }
            x += dx;
            y += dy;
        }
    }

    public static boolean attack(int n, int m, char[][] board, boolean[][] visited, boolean isBlack) {
        if (board[n][m] == EMPTY) {
            visited[n][m] = true;
            return true;
        }
        if (whatTeam(board[n][m]) != isBlack) {
            visited[n][m] = true;
        }
        return false;
    }

    public static boolean whatTeam(char c) {
        return 'a' <= c && c <= 'z';
    }
}