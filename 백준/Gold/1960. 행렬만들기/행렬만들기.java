/*
 * DFS로 그냥 전체 탐색
 * 2 ^ (500 * 500) ???
 * 
 * 중복 탐색은 없는데 불필요한 탐색이 많다...
 * 
 * 그거 쳐내고
 * 
 * 중복 탐색이 있을수도?
 * 가장 높은 애들이 있는 부분에 그냥 계속 넣으면 혹시 되나?
 */

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	
    	int N = Integer.parseInt(bf.readLine());
    	
    	PriorityQueue<Node> n = new PriorityQueue(Comparator.comparingInt(Node::getCount).reversed());
    	PriorityQueue<Node> m = new PriorityQueue(Comparator.comparingInt(Node::getCount).reversed());
    	
    	StringTokenizer input = new StringTokenizer(bf.readLine());
    	
    	int sum = 0;
    	
    	for (int i = 0; i < N; i++) {
    		int now = Integer.parseInt(input.nextToken());
    		sum += now;
    		n.add(new Node(i, now));
    	}
    	
    	input = new StringTokenizer(bf.readLine());
    	
    	for (int i = 0; i < N; i++) {
    		int now = Integer.parseInt(input.nextToken());
    		sum -= now;
    		m.add(new Node(i, now));
    	}
    	
    	if (sum == 0) {
    		System.out.println(solution(N, n, m));
    	} else {
    		System.out.println("-1");
    	}
    }
    
    public static String solution(int N, PriorityQueue<Node> n, PriorityQueue<Node> m) {
    	int[][] result = new int[N][N];
    	

    	while (!n.isEmpty()) {
    		Node nn = n.remove();
    		PriorityQueue<Node> next = new PriorityQueue(Comparator.comparingInt(Node::getCount).reversed());
    		
    		while (!m.isEmpty() && nn.count-- > 0 && m.peek().count > 0) {
    			Node mm = m.remove();
    			result[nn.i][mm.i] = 1;
    			mm.count--;
    			next.add(mm);
    		}
    		
    		while (!m.isEmpty()) next.add(m.remove());
    		
    		m = next;
    	}
    	
    	if (m.peek().count > 0) return "-1";
    	
    	StringBuilder sb = new StringBuilder();
		sb.append(1);
		for (int i = 0; i < N; i++) {
			sb.append("\n");
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]);
			}
		}
		
		return sb.toString();
    }
    
    static class Node {
    	int i;
    	int count;
    	
    	Node(int i, int count) {
    		this.i = i;
    		this.count = count;
    	}
    	
    	int getCount() {
    		return count;
    	}
    }
}