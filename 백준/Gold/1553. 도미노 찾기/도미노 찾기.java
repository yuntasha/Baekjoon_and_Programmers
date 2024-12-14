import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[8][];

        for (int i=0; i<8; i++) {
            arr[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(arr));
    }

    static int solution(int[][] arr) {
        return dfs(arr, 0, new boolean[8][7], new boolean[8][7], 0);
    }

    static int dfs(int[][] arr, int now, boolean[][] used, boolean[][] visited, int count) {
        if (count == 28) return 1;

        int x = now/7;
        int y = now%7;

        int result = 0;

        if (visited[x][y]) return dfs(arr, now + 1, used, visited, count);

        if (x < 7) {
            if (!visited[x+1][y] && !used[Math.min(arr[x][y], arr[x+1][y])][Math.max(arr[x][y], arr[x+1][y])]) {
                used[Math.min(arr[x][y], arr[x+1][y])][Math.max(arr[x][y], arr[x+1][y])] = true;
                visited[x][y] = true;
                visited[x+1][y] = true;
                result += dfs(arr, now + 1, used, visited, count + 1);
                used[Math.min(arr[x][y], arr[x+1][y])][Math.max(arr[x][y], arr[x+1][y])] = false;
                visited[x][y] = false;
                visited[x+1][y] = false;
            }
        }

        if (y < 6) {
            if (!visited[x][y+1] && !used[Math.min(arr[x][y], arr[x][y+1])][Math.max(arr[x][y], arr[x][y+1])]) {
                used[Math.min(arr[x][y], arr[x][y+1])][Math.max(arr[x][y], arr[x][y+1])] = true;
                visited[x][y] = true;
                visited[x][y+1] = true;
                result += dfs(arr, now + 1, used, visited, count + 1);
                used[Math.min(arr[x][y], arr[x][y+1])][Math.max(arr[x][y], arr[x][y+1])] = false;
                visited[x][y] = false;
                visited[x][y+1] = false;
            }
        }

        return result;
    }
}