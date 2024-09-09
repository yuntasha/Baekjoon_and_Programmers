import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var cost = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var nums = new ArrayList<Num>();

        for (int i=0; i<N; i++) nums.add(new Num(i, cost[i]));

        var M = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, nums, M));
    }

    static String solution(int N, List<Num> nums, int M){

        var result = new ArrayList<Integer>();

        nums.sort(Comparator.comparingInt(Num::getCost));

        if (N==1) return "0";

        if (nums.get(0).num==0) {
            if (M<nums.get(1).cost) return "0";
            result.add(nums.get(1).num);
            M-=nums.get(1).cost;
        }

        while (nums.get(0).cost<=M) {
            M-=nums.get(0).cost;
            result.add(nums.get(0).num);
        }
        for (int i=0; i<result.size(); i++) {
            var now = 0;
            if (i==0 && nums.get(0).num==0) now++;
            for (int j=1; j<N; j++) {
                if (i==0 && j==1 && nums.get(0).num==0) continue;
                if (nums.get(j).cost-nums.get(now).cost<=M) {
                    if (nums.get(now).num<nums.get(j).num) {
                        M -= nums.get(j).cost - nums.get(now).cost;
                        result.set(i, nums.get(j).num);
                        now = j;
                    }
                } else break;
            }
        }

        return result.stream().map(String::valueOf).collect(Collectors.joining());
    }

    static class Num{
        int num;
        int cost;

        Num(int num, int cost){
            this.num = num;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }
}