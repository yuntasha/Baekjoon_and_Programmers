import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, arr));
    }

    static long solution(int N, int M, int[] arr) {
        var result = 0L;
        var f = new ArrayList<Integer>();
        var s = new ArrayList<Integer>();
        f.add(0);
        for (int i=0; i<N/2; i++) {
            var l = f.size();
            for (int n=0; n<l; n++) {
                f.add(f.get(n)+arr[i]);
            }
        }

        s.add(0);
        for (int i=N/2; i<N; i++) {
            var l = s.size();
            for (int n=0; n<l; n++) {
                s.add(s.get(n)+arr[i]);
            }
        }

        var fhm = new HashMap<Integer, Long>();
        var shm = new HashMap<Integer, Long>();

        for (int i: f) {
            fhm.put(i, fhm.getOrDefault(i, 0L) + 1);
        }

        for (int i: s) {
            shm.put(i, shm.getOrDefault(i, 0L) + 1);
        }

        for (int i: fhm.keySet()) {
            result += fhm.get(i) * shm.getOrDefault(M-i, 0L);
        }

        if (M==0) result--;

        return result;
    }
}