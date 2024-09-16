import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int C, N;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var CN = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        C = CN[0];
        N = CN[1];

        var cities = new ArrayList<City>();

        for (int i=0; i<N; i++) {
            cities.add(new City(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }
        System.out.println(solution(cities));
    }

    static int solution(List<City> cities) {
        var dp = new int[C];
        Arrays.fill(dp, Integer.MAX_VALUE);
        var result = Integer.MAX_VALUE;
        for (City c: cities) {
            if (c.person>=C) {
                result = Math.min(result, c.cost);
                continue;
            }
            dp[c.person] = Math.min(dp[c.person], c.cost);
        }


        for (int i=1; i<C; i++) {
            if (dp[i]==Integer.MAX_VALUE) continue;
            for (City c: cities) {
                if (c.person+i >= C) {
                    result = Math.min(dp[i] + c.cost, result);
                    continue;
                }
                if (dp[c.person+i]==0) dp[c.person+i] = dp[i] + c.cost;
                else dp[c.person+i] = Math.min(dp[c.person+i], dp[i] + c.cost);
            }
        }
        return result;
    }

    static class City {
        int cost;
        int person;

        City (int[] i) {
            this.cost = i[0];
            this.person = i[1];
        }
    }
}