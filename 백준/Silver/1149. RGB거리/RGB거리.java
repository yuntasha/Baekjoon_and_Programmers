import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(bufferedReader.readLine());

        var colorValue = new int[N][3];

        for (int i=0; i<N; i++){
            var nowValue = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<3; j++){
                if (i==0){
                    colorValue[i][j] = nowValue[j];
                    continue;
                }
                for (int k=0; k<3; k++){
                    if (j!=k) {
                        if (colorValue[i][j]==0) {
                            colorValue[i][j] = nowValue[j] + colorValue[i-1][k];
                        } else {
                            colorValue[i][j] = Math.min(colorValue[i][j], nowValue[j] + colorValue[i-1][k]);
                        }
                    }
                }
            }
        }

        System.out.println(Math.min(Math.min(colorValue[N-1][0], colorValue[N-1][1]), colorValue[N-1][2]));
    }
}