/*
다익스트라로 2에서의 거리를 구함
그 다음에 1에서 dfs를 통해 더 짧아지는 곳으로만 이동
그렇게 갈 수 있는 개수를 구함

10만 * log10만
그 다음에 dfs에 dp까지 섞자
각 위치에서 갈 수 있는 가짓수를 메모라이징
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Word> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            words.add(new Word(i, bf.readLine()));
        }

        System.out.println(solution(N, words));
    }

    static String solution(int N, List<Word> words) {
        words.sort(Comparator.comparing(Word::getS));

        int max = -1;
        Word a = null;
        Word b = null;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int now = words.get(i).getPrefix(words.get(j));
                if (max > now) break;
                Word t = Word.getFirst(words.get(i), words.get(j));
                Word s = Word.getSecond(words.get(i), words.get(j));
                if (max < now || (t.n < a.n || (t.n == a.n && s.n < b.n))) {
                    a = t;
                    b = s;
                    max = now;
                }
            }
        }

        return a.s + "\n" + b.s;
    }

    static class Word {
        int n;
        String s;

        public Word(int n, String s) {
            this.n = n;
            this.s = s;
        }

        public String getS() {
            return s;
        }

        public int getPrefix(Word w) {
            for (int i = 0; i < Math.min(s.length(), w.s.length()); i++) {
                if (s.charAt(i) != w.s.charAt(i)) return i;
            }
            return Math.min(s.length(), w.s.length());
        }

        public static Word getFirst(Word w1, Word w2) {
            if (w1.n < w2.n) return w1;
            return w2;
        }

        public static Word getSecond(Word w1, Word w2) {
            if (w1.n < w2.n) return w2;
            return w1;
        }
    }
}