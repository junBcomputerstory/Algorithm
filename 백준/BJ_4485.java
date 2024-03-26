

import java.io.*;
import java.util.*;

public class BJ_4485 {
	public static int[] dx = new int[] { 0, 0, 1, -1 };
	public static int[] dy = new int[] { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int count = 0;
    
		while(true) {
			int N = Integer.parseInt(br.readLine());
			count++;
      
			if(N==0) {
				System.out.println(sb);
				break;   
			}
			
			int[][] map = new int[N][N];
			int[][] cost = new int[N][N];
			StringTokenizer st;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					cost[i][j]=Integer.MAX_VALUE;
				}
			}
		
			PriorityQueue<Point> pq = new PriorityQueue<>();
			cost[0][0]=map[0][0];
			pq.add(new Point(0,0,map[0][0]));
		
			while(!pq.isEmpty()) {
				Point tmp = pq.poll();
			
				for(int i=0;i<4;i++) {
					if(tmp.x+dx[i]<0||tmp.x+dx[i]>=N||tmp.y+dy[i]<0||tmp.y+dy[i]>=N)
						continue;
					if(tmp.cost+map[tmp.x+dx[i]][tmp.y+dy[i]]<cost[tmp.x+dx[i]][tmp.y+dy[i]]) {
						cost[tmp.x+dx[i]][tmp.y+dy[i]]=tmp.cost+map[tmp.x+dx[i]][tmp.y+dy[i]];
						pq.add(new Point(tmp.x+dx[i],tmp.y+dy[i],cost[tmp.x+dx[i]][tmp.y+dy[i]]));
					}
				}
			}
			sb.append("Problem ").append(count).append(": ").append(cost[N-1][N-1]).append("\n");
		}
		
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int cost;

		public Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost-o.cost;
		}
	}

}
