import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var str = bufferedReader.readLine().strip().toCharArray();

        System.out.println(solution(str.length, str));
    }

    static int solution(int N, char[] str){
        var palins = new boolean[N][N];
        var q = new LinkedList<Palin>();
        var result = new int[N];
        result[0] = 1;

        for (int i=0; i<N-1; i++){
            if (i!=0) {
                if (str[i-1] == str[i+1]) q.add(new Palin(i-1, i+1));
            }
            if (str[i] == str[i+1]) q.add(new Palin(i, i+1));
        }

        while (!q.isEmpty()) {
            var now = q.remove();
            palins[now.start][now.end] = true;
            if (!(now.start == 0 || now.end == N-1)) {
                if (str[now.start-1] == str[now.end+1]) {
                    now.start--;
                    now.end++;
                    q.add(now);
                }
            }
        }

        for (int i=1; i<N; i++){
            if (palins[0][i]) result[i] = 1;
            else result[i] = result[i-1] + 1;
            for (int j=1; j<i; j++){
                if (palins[j][i]) {
                    result[i] = Math.min(result[i], result[j-1]+1);
                }
            }
        }

        return result[N-1];
    }

    static class Palin{
        int start;
        int end;


        Palin(int start, int end){
            this.start = start;
            this.end = end;
        }

        int getEnd() {
            return end;
        }
    }
}