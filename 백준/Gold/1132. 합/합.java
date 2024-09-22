import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int exist = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = new char[N][];

        for (int i=0; i<N; i++) arr[i] = bf.readLine().strip().toCharArray();

        System.out.println(solution(N, arr));
    }

    static long solution(int N, char[][] arr) {
        var l = new ArrayList<AlNum>(N);

        for (int i=0; i<10; i++) {
            l.add(new AlNum(i, 0L));
        }

        for (char[] num: arr) {
            for (int i=0; i<num.length; i++) {
                l.get(num[i]-'A').add((long)Math.pow(10, num.length-i-1));
            }
        }

        var result = 0L;

        l.sort(Comparator.comparingLong(AlNum::getValue));

        var isZero = false;

        for (int i=0; i<10; i++) {
            if (isZero) {
                result += l.get(i).value * (long)(i);
            }
            var nowZero = false;
            for (int n=0; n<N; n++) {
                if (arr[n][0]-'A'==l.get(i).alpha) nowZero=true;
            }
            if (!nowZero) isZero=true;

            if (!isZero) {
                result += l.get(i).value * (long)(i+1);
            }
        }

        return result;
    }

    static class AlNum{
        int alpha;
        long value;

        AlNum (int alpha, long value){
            this.alpha = alpha;
            this.value = value;
        }

        void add(long n) {
            value+=n;
        }

        public long getValue() {
            return value;
        }
    }
}