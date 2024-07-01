import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    static int[][] last = new int[][] { {0, 2}, {1, 1}, {1, 3}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());
        var arr = new int[N][N*2-1];
        draw(0, 0, N, arr);

        System.out.print(Arrays.stream(arr).map(ar -> Arrays.stream(ar).mapToObj(i -> i==0?" ":"*").collect(Collectors.joining(""))).collect(Collectors.joining("\n")));
    }

    static void draw(int x, int y, int now, int[][] arr){
        if (now == 3) {
            for (int[] n: last){
                arr[y + n[0]][x + n[1]] = 1;
            }
        } else {
            draw(x + now/2, y, now/2, arr);
            draw(x, y+ now/2, now/2, arr);
            draw(x+now, y+now/2, now/2, arr);
        }
    }
}