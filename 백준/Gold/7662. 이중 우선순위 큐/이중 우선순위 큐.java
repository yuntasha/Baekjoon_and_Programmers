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
                s.cmd(bufferedReader.readLine().strip());
            }
            System.out.println(s.toString());
        }
    }

    private static class Solution{
        TreeMap<Integer, Integer> tm = new TreeMap<>();


        public void cmd(String s){
            var now = s.split(" ");
            if (now[0].equals("D")){
                if (tm.isEmpty()) return;
                if (now[1].equals("-1")) {
                    var removeInt = tm.firstKey();
                    if (tm.get(removeInt)==1){
                        tm.remove(removeInt);
                    }
                    else{
                        tm.put(removeInt, tm.get(removeInt)-1);
                    }
                }
                else {
                    var removeInt = tm.lastKey();
                    if (tm.get(removeInt)==1){
                        tm.remove(removeInt);
                    }
                    else{
                        tm.put(removeInt, tm.get(removeInt)-1);
                    }
                }
            }
            else{
                var insertNum = Integer.parseInt(now[1]);
                tm.put(insertNum, tm.getOrDefault(insertNum, 0)+1);
            }
        }

        public String toString(){
            return tm.isEmpty()?"EMPTY":tm.lastKey()+" "+tm.firstKey();
        }
    }
}