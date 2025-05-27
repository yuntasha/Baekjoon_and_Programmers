import java.io.*;
import java.util.Set;

public class Main {
    // HEPC
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = Set.of("HECP", "HCEP", "CHPE", "EHPC", "EPHC", "CPHE", "PECH", "PCEH");
        System.out.println(set.contains(bf.readLine() + bf.readLine()) ? "YES" : "NO");
    }
}