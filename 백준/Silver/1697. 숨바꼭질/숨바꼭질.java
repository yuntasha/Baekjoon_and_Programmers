import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = bufferedReader.readLine().split(" ");

        int N = Integer.parseInt(NM[0]);

        int M = Integer.parseInt(NM[1]);

        int[] result = new int[200001];

        Queue<Integer> q = new LinkedList<>();

        q.add(N);

        if (N==M){
            System.out.println(0);
            return;
        }

        while (!q.isEmpty()){
            Integer p = q.remove();
            int[] next = new int[]{p-1, p+1, p*2};
            for (int i: next){
                if (0<=i && i<=200000){
                    if (i==M) {
                        System.out.println(result[p]+1);
                        return;
                    }
                    if (result[i]==0 && i!=N){
                        result[i] = result[p]+1;
                        q.add(i);
                    }
                }
            }
        }
    }
}