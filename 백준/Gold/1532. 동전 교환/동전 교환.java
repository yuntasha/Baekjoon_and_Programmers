import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var money = new int[3];

        var st = new StringTokenizer(bf.readLine());

        for (int i=0; i<3; i++) {
            money[i] += Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());

        for (int i=0; i<3; i++) {
            money[i] -= Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(money));
    }

    static int solution(int[] money) {
        var result = 0;
        while (money[0] < 0) {
            money[0]++;
            money[1] -= 11;
            result++;
        }

        while (money[2] < 0) {
            money[1]--;
            money[2] += 9;
            result++;
        }

        while (money[1] < 0 && money[0] > 0) {
            money[1]+=9;
            money[0]--;
            result++;
        }

        while (money[1] < 0 && money[2] > 10) {
            money[1]++;
            money[2]-=11;
            result++;
        }

        for (int i=0; i<3; i++) {
            if (money[i]<0) return -1;
        }

        return result;
    }
}