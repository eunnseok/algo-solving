import java.util.*;
import java.io.*;

// SWEA #3289 - 서로소 집합
// Strategy: Union-find, 분리집합

public class Main {
	static int N,M;
	static int[] parent, rank;
	
	public static void makeSet() {
		parent = new int[N+1];
		rank = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}
	
	public static int find(int u) {
		if(u == parent[u]) {
			return u;
		}
		
		// parent를 찾아낸 루트로 바꾸면 find 연산 수행시 중복되는 연산을 줄여줌
		// 재귀적인 구현 덕분에 u에서 root까지 올라가는 경로상에 있는 모든 노드들에 대해서도
		// 경로 압축 최적화가 자동으로 수행된다.
		parent[u] = find(parent[u]);
		return parent[u];
	}
	
	public static void union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u==v) return;
		
		if(rank[u] > rank[v]) {
			// swap
			int tmp = u;
			u = v;
			v = tmp;
		}
		
		parent[u] = v;
		
		if(rank[u] == rank[v]) rank[v]++;
	}
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        

        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	
        	makeSet();
        	
        	for(int i=0; i<M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int cmd = Integer.parseInt(st.nextToken());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		
        		if(cmd == 0) {
        			union(a,b);
        		}else {
        			if(find(a) == find(b)) {
        				sb.append("YES").append("\n");
        			}else {
        				sb.append("NO").append("\n");
        			}
        		}
        	}

        
        
        // 최종 결과 출력
        System.out.println(sb);
    }
}