
import java.io.*;
import java.util.*;

public class BJ_2239 {

	public static int[] width = new int[9];//가로줄에 대한 비트 저장공간 
	public static int[] height = new int[9];//세로줄에 대한 비트 저장공간 
	public static int[] area = new int [9];//범위에 대한 비트 저장공간 
	public static int[][] map = new int[9][9];
	public static String answer = "";//정답 스트링 
	public static int count=0;//채워야하는 빈칸 갯수 
	public static List<Point> quiz=new ArrayList<>();//빈칸 좌표 저장소 
	public static boolean flag=false;// 최적해를 찾았는지 여부 
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		
		for(int i=0;i<9;i++) {//각칸에 대해 비트마스킹 처리 
			line = br.readLine();
			for(int j=0;j<9;j++) {
				map[i][j]=line.charAt(j)-'0';
				if(map[i][j]==0) {
					quiz.add(new Point(i,j));
					count++;
					continue;
				}
				width[i]+=1<<map[i][j];
				height[j]+=1<<map[i][j];
				area[i/3*3+j/3]+=1<<map[i][j];
				
			}
		}
		dfs(0);
		System.out.println(answer);
		
		
	}
	public static void dfs(int current) {
		
		if(current==count) { //모든 빈칸을 채웠을 경우 정답 처리 후 종료 
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			answer=sb.toString();
			flag=true;
			return;
		}
		
		int i=quiz.get(current).x;
		int j=quiz.get(current).y;
		
		for (int k = 1; k <= 9; k++) {
			if ((width[i] & (1 << k)) != 0 || (height[j] & (1 << k)) != 0 || (area[i / 3 * 3 + j / 3] & (1 << k)) != 0)
				continue;
			map[i][j] = k;
			width[i] += (1 << k);
			height[j] += (1 << k);
			area[i / 3 * 3 + j / 3] += (1 << k);
			//모든 검사 배열에 현재 빈칸을 채우는 숫자를 표기 
			dfs(current + 1);
			if(flag) //정답을 찾았을 경우 종료 
				return;
			map[i][j] = 0;
			width[i] -= (1 << k);
			height[j] -= (1 << k);
			area[i / 3 * 3 + j / 3] -= (1 << k);
			//표기들을 전부 다시 지운다.
			
		}
	}
	
	public static class Point{
		int x;
		int y;
		public Point(int x,int y) {
			this.x=x;
			this.y=y;
		}
	}

	}
