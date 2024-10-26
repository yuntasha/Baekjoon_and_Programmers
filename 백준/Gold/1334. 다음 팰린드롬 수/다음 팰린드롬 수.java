import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N));
    }

    static String solution(int[] N) {
        var len = N.length;
        var mid = N.length/2;

        if (len%2==0) {
            var over = true;
            var chk = true;
            for (int i=mid-1; i>=0; i--) {
                if (chk && N[i]>N[len-i-1]) {
                    over = false;
                } else if (N[i]<N[len-i-1]) {
                    chk = false;
                }
                N[len-i-1] = N[i];
            }

            if (over) {
                for (int i=mid-1; i>=0; i--) {
                    if (N[i]==9) {
                        N[i]=0;
                        N[len-i-1]=0;
                    } else {
                        N[i]++;
                        N[len-i-1]++;
                        break;
                    }
                }
                if (N[0]==0) {
                    N = new int[len+1];
                    N[0] = N[N.length-1] = 1;
                }
            }
        } else {
            var over = true;
            var chk = true;
            for (int i=mid-1; i>=0; i--) {
                if (chk && N[i]>N[len-i-1]) {
                    over = false;
                } else if (N[i]<N[len-i-1]) {
                    chk = false;
                }
                N[len-i-1] = N[i];
            }
            if (over) {
                for (int i=mid; i>=0; i--) {
                    if (N[i]==9) {
                        N[i]=0;
                        N[len-i-1]=0;
                    } else {
                        if (i!=mid) {
                            N[i]++;
                        }
                        N[len-i-1]++;
                        break;
                    }
                }
                if (N[0]==0) {
                    N = new int[len+1];
                    N[0] = N[N.length-1] = 1;
                }
            }
        }
        return Arrays.stream(N).mapToObj(String::valueOf).collect(Collectors.joining(""));
    }
}