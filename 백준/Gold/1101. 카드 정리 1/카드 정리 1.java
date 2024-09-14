import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var arr = new int[N][M];

        for (int i=0; i<N; i++){
            arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[][] arr) {
        var isVisited = new boolean[M];
        var jocker = false;
        var result = 0;
        for (int i=0; i<N; i++) {
            var cnt = 0;
            var n = -1;
            for (int j=0; j<M; j++) {
                if (arr[i][j]==0) continue;
                cnt++;
                n=j;
            }

            if (cnt==1) { // 하나만 있어서 해당 번호 박스로 남길수도 있는 경우
                if (!isVisited[n]) { // 해당 번호의 박스가 없는 경우
                    isVisited[n] = true;
                } else {
                    if (jocker) result++; // 조커가 이미 있어 넘겨야하는 경우
                    else jocker = true; // 해당 번호의 박스가 있어 조커박스로 지정하는 경우
                }
            } else if (cnt>1) { // 옮길것이 여러가지인 경우 따라서 해당 번호 박스로 못함
                result++;
            } // 뭐 옮길게 없을 경우 지나가면 됨 ㅎ
        }

        if (!jocker && result>0) { // 조커박스가 지정되지 않으면 지정해서 해당 박스에 있는것은 옮기지 않아도됨 단 옮길 것이 아예 없는 경우 제외
            result--;
        }

        return result;
    }
}