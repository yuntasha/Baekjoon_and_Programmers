import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

/*
악 어려워
배낭 문제같이 진입해보자
일단 순서대로 정렬해주고
2차원 배열로 걔네들이 서로 같이 둘 수 있는지 확인
DP[어떤 책까지 가능한지][현재 책꽂이에 있는 책의 개수][마지막 책의 번호] = 최대 개수

*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Book> books = new ArrayList<>();

        for (int i=0; i<N; i++) {
            books.add(new Book(bf.readLine()));
        }

        System.out.println(solution(N, books));
    }

    static String solution(int N, List<Book> books) {
        books.sort(Comparator.naturalOrder());

        int[][] DP = new int[N][11];
        int[][] log = new int[N][11];
        boolean[][] isOK = new boolean[N][N];

        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                isOK[i][j] = isOK[j][i] = itCan(books.get(i), books.get(j));
            }
        }

        DP[0][1] = books.get(0).value;


        for (int now = 1; now < N; now++) {
            for (int count = 9; count >= 0; count--) {
                for (int last = 0; last < now; last++) {
                    if (!isOK[last][now] && count > 0) continue;
                    if (DP[now][count+1] < DP[last][count] + books.get(now).value) {
                        DP[now][count+1] = DP[last][count] + books.get(now).value;
                        log[now][count+1] = last;
                    }
                }
            }
        }

        int maxV = 0;
        int maxC = 0;
        int last = 0;

        for (int i=0; i<=10; i++) {
            for (int j=0; j<N; j++) {
                if (maxV < DP[j][i]) {
                    maxV = DP[j][i];
                    maxC = i;
                    last = j;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int count=maxC; count>0; count--) {
            result.add(last);
            last = log[last][count];
        }

        StringBuilder sj = new StringBuilder();
        sj.append(maxC).append("\n");
        sj.append(maxV);
        for (int i=result.size()-1; i>=0; i--) {
            sj.append("\n").append(books.get(result.get(i)).original);
        }

        return sj.toString();
    }

    static boolean itCan(Book book1, Book book2) {
        int idx = 0;
        while (idx < book1.forSort.length() && idx < book2.forSort.length()) {
            if (book1.forSort.charAt(idx) == book2.forSort.charAt(idx)) return false;
            if (book1.forSort.charAt(idx) - 32 == book2.forSort.charAt(idx)) return false;
            if (book1.forSort.charAt(idx) == book2.forSort.charAt(idx) - 32) return false;
            idx++;
        }
        return true;
    }

    static class Book implements Comparable<Book> {
        String original;
        String forSort;
        int value;

        Book(String input) {
            int idx = 0;

            while (input.charAt(idx) != ' ') {
                idx++;
            }
            this.original = input.substring(idx+1);
            this.forSort = original.replaceAll("[^A-Za-z]", "");
            this.value = Integer.parseInt(input.substring(0, idx));
        }

        @Override
        public int compareTo(Book o) {
            return forSort.compareTo(o.forSort);
        }
    }
}