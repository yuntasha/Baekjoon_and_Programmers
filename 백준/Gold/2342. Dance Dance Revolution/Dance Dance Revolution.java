import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(arr));
    }

    static int solution(int[] arr){
        if (arr[0]==0) return 0;
        var N = arr.length;
        var dp = new int[5][5];
        dp[0][arr[0]] = 2;
        int[][] temp;
        for (int i=1; i<arr.length-1; i++){
            temp = new int[5][5];
            var now = arr[i];
            for (int x=0; x<5; x++){
                for (int y=1; y<5; y++){
                    if (dp[x][y]==0) continue;
                    var dx = dp[x][y] + getCost(x, now);
                    var dy = dp[x][y] + getCost(y, now);
                    temp[now][y] = temp[now][y]==0?dx:Math.min(temp[now][y], dx);
                    temp[x][now] = temp[x][now]==0?dy:Math.min(temp[x][now], dy);
                }
            }
            dp = temp;
//            System.out.println(Arrays.stream(dp).mapToInt(a -> Arrays.stream(a).filter(i1->i1>0).min().orElse(0)).filter(i1->i1>0).min().orElse(0));
        }
        return Arrays.stream(dp).mapToInt(a -> Arrays.stream(a).filter(i->i>0).min().orElse(0)).filter(i->i>0).min().orElse(0);
    }

    static int getCost(int prev, int next){
        if (prev==0) return 2;
        switch ((prev-next+4)%4){
            case 2: return 4;
            case 0: return 1;
            default: return 3;
        }
    }
}