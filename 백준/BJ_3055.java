
import java.io.*;
import java.util.*;

public class BJ_3055 {

	
	public static void main(String[] args)throws IOException {
    int[] dx = new int[] {1,-1,0,0};
    int[] dy = new int[] {0,0,1,-1}; //상하우좌 이동 커맨드담아놓기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		Queue<Point> survive = new LinkedList<>(); //고슴도치가 살아남기위해 가는 길의 여정을 위한 큐
		Queue<Point> flood = new LinkedList<>();//마법사가 터트린 홍수가 가는 길의 큐
		String line;
    
		for(int i=0;i<R;i++) {
			line = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=line.charAt(j);
				if(map[i][j]=='S') {
					survive.add(new Point(i,j));
					continue;
				}
				if(map[i][j]=='*') {
					flood.add(new Point(i,j));
				}
			}
		} //입력
		
		Point hog;
		Point water;
		
		if(!flood.isEmpty()) { //물이 찰 예정인 곳은 가지 못하기 때문이 -> 물을 1초 먼저 터트린다.
		
			water = flood.poll();
			for (int i = 0; i < 4; i++) {
				if (water.x + dx[i] >= 0 && water.x + dx[i] < R && water.y + dy[i] >= 0 && water.y + dy[i] < C
						&& map[water.x + dx[i]][water.y + dy[i]] != 'X'
						&& map[water.x + dx[i]][water.y + dy[i]] != 'D') {
					map[water.x + dx[i]][water.y + dy[i]] = '*';
					flood.add(new Point(water.x + dx[i], water.y + dy[i]));
				}
			}
		}
		
		int w_cnt =0;
		int time=0;
		int cnt=0;

		while(!survive.isEmpty()) { //고슴도치가 갈길이 없을때까지
			
			w_cnt = flood.size();//현재 시간때 물길 갯수
			cnt = survive.size();//현재 시간때 고슴도치가 갈수있는 길 갯수.
			
			for(int k=0;k<cnt;k++) {//물,벽 , 방문지점외에 방문 및 큐에 담기
				hog = survive.poll();
				for(int i=0;i<4;i++) {
					if(hog.x+dx[i]>=0&&hog.x+dx[i]<R&&hog.y+dy[i]>=0&&hog.y+dy[i]<C&&map[hog.x+dx[i]][hog.y+dy[i]]!='*'&&map[hog.x+dx[i]][hog.y+dy[i]]!='X'&&map[hog.x+dx[i]][hog.y+dy[i]]!='S') {
						if(map[hog.x+dx[i]][hog.y+dy[i]]=='D') { //비버집일 경우 BFS로 항상 최단시간이기때문에 바로 출력후 종료
							System.out.println(time+1);
							return;
						}
						map[hog.x+dx[i]][hog.y+dy[i]]='S';
						survive.add(new Point(hog.x+dx[i],hog.y+dy[i]));
					}
				}
			}
			
			for(int k=0;k<w_cnt;k++) { //벽, 비버집, 방문지점 외에 큐에 담기
				water = flood.poll();
				for(int i=0;i<4;i++) {
					if(water.x+dx[i]>=0&&water.x+dx[i]<R&&water.y+dy[i]>=0&&water.y+dy[i]<C&&map[water.x+dx[i]][water.y+dy[i]]!='X'&&map[water.x+dx[i]][water.y+dy[i]]!='D'&&map[water.x+dx[i]][water.y+dy[i]]!='*') {
						map[water.x+dx[i]][water.y+dy[i]]='*';
						flood.add(new Point(water.x+dx[i],water.y+dy[i]));
					}
				}
			}
			time++;//시간 추가
			
			
			
		}
		
		System.out.println("KAKTUS");
		
		

	}
	static class Point{
		int x;
		int y;
		
		public Point(int x,int y) {
			this.x=x;
			this.y=y;
			
		}
	}
}
