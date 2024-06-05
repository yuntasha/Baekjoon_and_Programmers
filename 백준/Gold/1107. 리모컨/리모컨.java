import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class Main {

    public static void main(String[] args) throws IOException {
        char[] result;
        int ans;

        Set<String> s = new HashSet<>();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String sss = bufferedReader.readLine().strip();
        result = sss.strip().toCharArray();
        ans = Integer.parseInt(sss);

        int diff = ans-100>0?ans-100:100-ans;
        int parseInt = Integer.parseInt(bufferedReader.readLine());
        if (parseInt==0){
            System.out.println(Math.min(sss.length(), diff));
            return;
        }

        if (parseInt==10){
            System.out.println(diff);
            return;
        }
        String[] nums = bufferedReader.readLine().split(" ");
        for (String now: nums){
            s.add(now);
        }

        for (int i=0; i<1000000; i++){
            int d = ans<i?i-ans:ans-i;
            boolean flag = true;
            for (String n:s.toArray(String[]::new)){
                if (String.valueOf(i).contains(n)){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (diff > String.valueOf(i).length() + d) {
                    diff = String.valueOf(i).length() + d;
                }
            }
        }

        System.out.println(diff);

    }
}