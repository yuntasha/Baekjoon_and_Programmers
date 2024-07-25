import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[9][];

        for (int i=0; i<9; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(arr));
    }

    static String solution(int[][] arr){
        findfs(arr, 0);
        return Arrays.stream(arr).map(a -> Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(""))).collect(Collectors.joining("\n"));
    }

    static boolean findfs(int[][] arr, int n) {
        var s = new HashSet<Integer>(List.of(1,2,3,4,5,6,7,8,9));

        int x=-1;
        int y=-1;

        Loop: for (int i=n/9; i<9; i++){
            for (int j=0; j<9; j++){
                if (arr[i][j]==0) {
                    x = i;
                    y = j;
                    break Loop;
                }
            }
        }

        if (x==-1) return true;

        for (int i=0; i<9; i++){
            s.remove(arr[i][y]);
            s.remove(arr[x][i]);
        }

        for (int i=(x/3)*3; i/3 == x/3; i++){
            for (int j=(y/3)*3; j/3 == y/3; j++){
                s.remove((arr[i][j]));
            }
        }

        for (int k: s){
            arr[x][y] = k;
            if (findfs(arr, x*9+y+1)) return true;
        }
        arr[x][y] = 0;
        return false;
    }
}