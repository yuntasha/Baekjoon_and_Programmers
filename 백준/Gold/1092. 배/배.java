import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());
        var cranes = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var M = Integer.parseInt(bf.readLine());
        var boxes = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, cranes, M, boxes));
    }

    static int solution(int N, int[] cranes, int M, int[] boxes) {

        var idx = new int[N];

        Arrays.sort(cranes);
        Arrays.sort(boxes);

        var now = 0;

        for (int i=0; i<M; i++) {
            while (now<N && cranes[now]<boxes[i]) {
//                System.out.println("now = " + now);
                idx[now++] = i-1;
            }

            while (now<N && i==M-1 && cranes[now] > boxes[i]) idx[now++] = i;
        }

        for (int i=0; i<N; i++){
            if (cranes[i]>=boxes[M-1]) idx[i] = M-1;
        }

//        System.out.println("Arrays.toString(cranes) = " + Arrays.toString(cranes));
//        System.out.println("Arrays.toString(boxes) = " + Arrays.toString(boxes));
//        System.out.println("Arrays.toString(idx) = " + Arrays.toString(idx));

        if (M-1>idx[N-1]) return -1;

        var result = 0;
        var cnt = 0;
        var get = IntStream.range(0, M).toArray();
        while (cnt<M) {
            result++;
            for (int i=0; i<N; i++) {
                if (idx[i]==-1) continue;
                var n = find(get, idx[i]);
                if (n==-1) continue;
                get[n] = n-1;
                cnt++;
            }
        }
        return result;
    }

    static int find(int[] get, int n){
        if (get[n]==-1) return -1;
        if (get[n]!=n) get[n] = find(get, get[n]);
        return get[n];
    }
}