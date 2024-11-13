import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int FAIL = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var man = new int[N];

        for (int i=0; i<N; i++) {
            var now = bf.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                char c = now[j];
                if (c=='1') {
                    man[i] = man[i] | (1<<j);
                }
            }
        }

        System.out.println(solution(N, M, man));
    }

    static int solution(int N, int M, int[] man) {
        var woman = new int[M];

        for (int w=0; w<M; w++) {
            for (int m=0; m<N; m++) {
                if ((man[m]&(1<<w))>0) {
                    woman[w] = woman[w] | (1<<m);
                }
            }
        }

        var result = dfs(N, M, man, woman, 0, 0, 0, 0);
        if (result == FAIL) {
            return -1;
        }
        return result;
    }

    static int dfs(int N, int M, int[] man, int[] woman, int last, int manBit, int womanBit, int count) {
        if ((manBit==(1<<N)-1) && (womanBit==(1<<M)-1)) {
            return count;
        }

        if (N+M==last) {
            return FAIL;
        }
        var result = FAIL;

        for (int i=last; i<N+M; i++) {
            if (N>i) {
                if (man[i]==0) continue;
                result = Math.min(result, dfs(N, M, man, woman, i+1, manBit|(1<<i), womanBit|man[i], count+1));
            } else {
                if (woman[i-N]==0) continue;
                result = Math.min(result, dfs(N, M, man, woman, i+1, manBit|woman[i-N], womanBit|(1<<(i-N)), count+1));
            }
        }
        
        return result;
    }
}