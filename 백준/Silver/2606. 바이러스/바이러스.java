import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int M = Integer.parseInt(bufferedReader.readLine());

        int[][] link = new int[M][2];

        for (int i=0; i<M; i++){
            String inputLine = bufferedReader.readLine();
            String[] ss = inputLine.split(" ");
            for (int j=0; j<2; j++){
                link[i][j] = Integer.parseInt(ss[j])-1;
            }
        }

        Solution solution = new Solution(N, M, link);
        solution.result();
    }

    private static class Solution{

        private int N;
        private int M;
        private ArrayList[] link;

        Solution(int N, int M, int[][] cmds){
            this.N = N;
            this.M = M;

            link = new ArrayList[N];
            for (int i=0; i<N; i++) link[i] = new ArrayList<Integer>();

            for (int[] cmd:cmds) {
                link[cmd[0]].add(cmd[1]);
                link[cmd[1]].add(cmd[0]);
            }
        }

        void result(){
            LinkedList<Integer> ll = new LinkedList<>();
            Set<Integer> s = new HashSet<>();

            ll.add(0);
            s.add(0);

            while(!ll.isEmpty()){
                Integer node = ll.remove();
                for (Object o : link[node]) {
                    Integer next = (Integer) o;
                    if (!s.contains(next)){
                        s.add(next);
                        ll.add(next);
                    }
                }
            }

            System.out.println(s.size()-1);
        }
    }
}