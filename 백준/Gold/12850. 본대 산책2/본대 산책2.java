import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Main {
    static final int V = 1_000_000_007;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        long[][] arr = new long[8][8];
        var N = Integer.parseInt(bufferedReader.readLine());
        link(arr, 0, 1);
        link(arr, 0, 2);
        link(arr, 1, 2);
        link(arr, 1, 3);
        link(arr, 2, 3);
        link(arr, 2, 4);
        link(arr, 3, 4);
        link(arr, 3, 5);
        link(arr, 4, 5);
        link(arr, 4, 7);
        link(arr, 5, 6);
        link(arr, 6, 7);
        System.out.println(solution(N, arr));
    }

    static void link (long[][] arr, int a, int b){
        arr[a][b] = 1L;
        arr[b][a] = 1L;
    }

    static long solution(long N, long[][] arr){
        return divide(N, arr)[0][0];
    }

    static long[][] divide(long N, long[][] arr){
        if (N==1){
            return arr;
        }
        if (N%2==0){
            var result = divide(N/2, arr);
            return sumArr(result, result);
        } else {
            return sumArr(divide(N-1, arr), arr);
        }
    }

    static long[][] sumArr(long[][] arr1, long[][] arr2) {
        var newArr = new long[8][8];
        for (int x=0; x<8; x++){
            for (int y=0; y<=x; y++){
                for (int i=0; i<8; i++){
                    newArr[x][y] += (arr1[x][i] * arr2[i][y])%V;
                    newArr[x][y]%=V;
                }
                newArr[y][x] = newArr[x][y];
            }
        }
        return newArr;
    }
}