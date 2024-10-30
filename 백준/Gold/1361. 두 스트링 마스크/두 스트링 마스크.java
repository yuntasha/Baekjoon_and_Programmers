import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String A;
    static String B;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        A = bf.readLine();
        B = bf.readLine();

        System.out.println(solution(A, B));
    }

    static String solution(String A, String B) {
        var sb = new StringBuilder();
        if (dfs(sb, 0, 0)) {
            return sb.toString();
        } else {
            return "-1";
        }
    }

    static boolean dfs(StringBuilder sb, int aIdx, int bIdx) {

        if (aIdx == A.length() && bIdx == B.length()) {
            return true;
        } else if (aIdx == A.length()-1 && A.charAt(aIdx)=='*' && bIdx == B.length()){
            return true;
        } else if (bIdx == B.length()-1 && B.charAt(bIdx)=='*' && aIdx == A.length()){
            return true;
        } else if (bIdx == B.length()-1 && B.charAt(bIdx)=='*' && aIdx == A.length()-1 && A.charAt(aIdx)=='*'){
            return true;
        } else if (aIdx >= A.length() || bIdx >= B.length()) {
            return false;
        }

        if (A.charAt(aIdx) == '*' && B.charAt(bIdx) == '*') {
            if (dfs(sb, aIdx+1, bIdx+1)) {
                return true;
            }
            if (dfs(sb, aIdx, bIdx+1)) {
                return true;
            }
            if (dfs(sb, aIdx+1, bIdx)) {
                return true;
            }
        } else if (A.charAt(aIdx) == '*') {
            if (dfs(sb, aIdx+1, bIdx)) {
                return true;
            }
            sb.append(B.charAt(bIdx));
            if (dfs(sb, aIdx, bIdx+1)) {
                return true;
            }
            sb.deleteCharAt(sb.length()-1);
        } else if (B.charAt(bIdx) == '*') {
            if (dfs(sb, aIdx, bIdx+1)) {
                return true;
            }
            sb.append(A.charAt(aIdx));
            if (dfs(sb, aIdx+1, bIdx)) {
                return true;
            }
            sb.deleteCharAt(sb.length()-1);
        } else if (A.charAt(aIdx) == B.charAt(bIdx)) {
            sb.append(A.charAt(aIdx));
            if (dfs(sb, aIdx+1, bIdx+1)) {
                return true;
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return false;
    }
}