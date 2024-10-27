import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());
        
        var nums = new char[N][];

        for (int i=0; i<N; i++) {
            nums[i] = bf.readLine().toCharArray();
        }

        System.out.println(solution(N, nums));
    }

    static int solution(int N, char[][] nums) {
        var list = new ArrayList<Num>();
        for (char c='A'; c<='Z'; c++) {
            list.add(new Num(c));
        }
        for (char[] num : nums) {
            var now = 1;
            for (int i=0; i<num.length; i++) {
                list.get(num[num.length-1-i]-'A').addV(now);
                now*=10;
            }
        }
        list.sort(Comparator.comparingInt(Num::getValue).reversed());
        var now = 9;
        var result = 0;
        for (int i=0; i<10; i++) {
            result += list.get(i).getValue()*now;
            now--;
        }
        return result;
    }

    static class Num {
        char c;
        int value;

        Num (char c) {
            this.c = c;
            this.value = 0;
        }

        void addV (int v) {
            this.value += v;
        }

        public int getValue() {
            return value;
        }
    }
}