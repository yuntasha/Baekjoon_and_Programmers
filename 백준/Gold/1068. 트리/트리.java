import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var group = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var delete = Integer.parseInt(bf.readLine());
        System.out.println(solution(N, group, delete));
    }

    static int solution(int N, int[] group, int delete){
        var isNotLeaf = new boolean[N];
        for (int i=0; i<N; i++) {
            findN(i, group, delete, isNotLeaf);
        }
        var result = 0;
        for (int i=0; i<N; i++) if (!isNotLeaf[i]) result++;
        return result;
    }

    static int findN(int n, int[] group, int delete, boolean[] isNotLeaf) {
        if (n==delete) {
            isNotLeaf[n] = true;
            return delete;
        }
        if (group[n]!=-1) {
            isNotLeaf[group[n]] = true;
            group[n] = findN(group[n], group, delete, isNotLeaf);
        }
        if (delete == group[n]) isNotLeaf[n] = true;
        return group[n];
    }
}