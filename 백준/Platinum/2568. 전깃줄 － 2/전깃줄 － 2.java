import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = new ArrayList<Line>();

        for (int i=0; i<N; i++){
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr.add(new Line(now[0], now[1]));
        }



        System.out.println(solution(N, arr));
    }

    static String solution(int N, List<Line> arr){
        arr.sort(Comparator.comparing(Line::getX));
        var dp = new ArrayList<Line>();
        var dp2 = new ArrayList<Integer>();

        for (int i=0; i<N; i++){
            if (dp.isEmpty() || dp.get(dp.size()-1).y<arr.get(i).y) {
                dp.add(arr.get(i));
                dp2.add(dp.size());
            } else {
                var r = binarySearch(dp, arr.get(i).y);
                dp.set(r, arr.get(i));
                dp2.add(r+1);
            }
        }
        StringBuilder sb = new StringBuilder(arr.size() - dp.size() + "\n");
        Stack<Line> stk = new Stack<>();
        var now = dp.size();
        for (int i=arr.size()-1; i>=0; i--){
            if (dp2.get(i) == now) now--;
            else stk.push(arr.get(i));
        }
        while (!stk.isEmpty()) sb.append(stk.pop().x+"\n");
        return sb.toString();
    }

    static int binarySearch(List<Line> dp, int n) {
        int l = 0;
        int r = dp.size()-1;
        while (l<r){
            int m = (l+r)/2;
            if (dp.get(m).y == n) {
                return m;
            } else if (dp.get(m).y < n) {
                l = m+1;
            } else {
                r = m;
            }
        }
        return r;
    }



    static class Line {
        int x;
        int y;
        Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}