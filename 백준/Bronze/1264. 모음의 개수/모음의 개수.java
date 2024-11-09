import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var in = bf.readLine();
        List<Integer> a = List.of((int)'a', (int)'A', (int)'e', (int)'E', (int)'i', (int)'I', (int)'o', (int)'O', (int)'u', (int)'U');
        while (!in.equals("#")) {
            System.out.println(in.chars().filter(a::contains).count());
            in = bf.readLine();
        }
    }
}