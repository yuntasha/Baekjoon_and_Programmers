import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var bags = new ArrayList<Integer>();
        var jews = new LinkedList<Jew>();

        for (int i=0; i<N; i++){
            var jew = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            jews.add(new Jew(jew[0], jew[1]));
        }

        for (int i=0; i<M; i++){
            bags.add(Integer.parseInt(bufferedReader.readLine()));
        }

        System.out.println(solution(N, bags, jews));
    }

    static long solution(int N, ArrayList<Integer> bags, LinkedList<Jew> jews){
        bags.sort(Comparator.naturalOrder());
        jews.sort(Comparator.comparing(Jew::getSize));
        var enable = new PriorityQueue<Jew>(Comparator.comparing(Jew::getValue).reversed());
        long result = 0;
        for (int bag: bags){
            while (!jews.isEmpty() && jews.peekFirst().size<=bag){
                enable.add(jews.remove());
            }
            if (!enable.isEmpty()) {
                result += enable.remove().value;
            }
        }
        return result;
    }

    static class Jew{
        int size;
        int value;


        Jew(int size, int value){
            this.size = size;
            this.value = value;
        }

        int getValue(){
            return this.value;
        }

        int getSize() {
            return size;
        }
    }
}