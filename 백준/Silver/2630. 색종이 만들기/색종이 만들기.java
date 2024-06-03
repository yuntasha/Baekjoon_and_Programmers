import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String inputLine = bufferedReader.readLine();
        int N = Integer.parseInt(inputLine);



        int[][] graph = new int[N][N];

        for (int i=0; i<N; i++){
            inputLine = bufferedReader.readLine();
            String[] ss = inputLine.split(" ");
            for (int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(ss[j]);
            }
        }

        Solution solution = new Solution(N, graph);
        solution.result();
    }

    private static class Solution{
        int N;
        int[][] graph;
        int[] result;

        Solution(int N, int[][] graph){
            this.N = N;
            this.graph = graph;
            this.result = new int[2];
        }

        void result(){
            int chk = chk(new int[]{0, 0}, N);
            if (chk!=-1){
                result[chk]++;
            }
            System.out.println(result[0]);
            System.out.println(result[1]);
        }

        private int chk(int[] point, int len){
            if (len == 1){
                return graph[point[0]][point[1]];
            }

            int[] now = new int[4];
            int[][] nextPoint = {{point[0], point[1]},
                    {point[0] + len/2, point[1]},
                    {point[0], point[1] + len/2},
                    {point[0] + len/2, point[1] + len/2}
            };

            for (int i=0; i<4; i++){
                now[i] = chk(nextPoint[i], len/2);
            }

            if (now[0] == now[1] && now[1] == now[2] && now[2] == now[3] && now[0] != -1) return now[0]; // 같은 경우
            else{
                for (int i=0; i<4; i++){
                    if (now[i]!=-1) {
                        result[now[i]]++;
                    }
                }
                return -1;
            }
        }
    }
}