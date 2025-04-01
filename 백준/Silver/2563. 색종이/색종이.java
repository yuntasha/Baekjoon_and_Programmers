import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        boolean[][] paper = new boolean[100][100];

        for (int n = 0; n < N; n++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());

            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    paper[i][j] = true;
                }
            }
        }

        int result = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (paper[i][j]) result++;
            }
        }

        System.out.println(result);
    }
}