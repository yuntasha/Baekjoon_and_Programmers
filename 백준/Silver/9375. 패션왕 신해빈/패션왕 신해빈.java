import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(bufferedReader.readLine());
        for (int i=0; i<N; i++){
            var M = Integer.parseInt(bufferedReader.readLine());
            var s = new Solution();
            for (int j=0; j<M; j++){
                s.addCloth(bufferedReader.readLine().strip());
            }
            System.out.println(s.getResult());
        }
    }

    private static class Solution{
        HashMap<String, Integer> hm = new HashMap<String, Integer>();


        public void addCloth(String cloth){
            var now = cloth.split(" ");
            hm.put(now[1], hm.getOrDefault(now[1], 0)+1);
        }

        public int getResult(){
            int result = 1;
            for (String s: hm.keySet()){
                result *= hm.get(s)+1;
            }
            return hm.isEmpty()?0:result-1;
        }
    }
}