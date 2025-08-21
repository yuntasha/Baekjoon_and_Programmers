import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder result = new StringBuilder();
        
        boolean isBig = true;
        
        for (int i = 0; i < s.length(); i++) {
            if (isBig) {
                result.append(convertA(s.charAt(i)));
                isBig = false;
                if (s.charAt(i) == ' ') isBig = true;
            } else {
                result.append(converta(s.charAt(i)));
                if (s.charAt(i) == ' ') isBig = true;
            }
        }
        
        return result.toString();
    }
    
    public char convertA(char c) {
        if ('a' <= c && c <= 'z') return (char) (c + ('A' - 'a'));
        return c;
    }
    
    public char converta(char c) {
        if ('A' <= c && c <= 'Z') return (char) (c + ('a' - 'A'));
        return c;
    }
}