import java.io.*;
import java.util.*;

public class Algo2_서울_19_석홍준 {
	static int[] days = new int[] {0,31,59,90,120,151,181,212,243,273,304,334};//각월 시작점의 전날의 날짜 누적을 저장해놓음
	static int answer;
	static int start = 60;//3월 1일
	static int finish = 334;// 11월 30일
	static int[][] Projects;
	static int N;
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
			
		N = Integer.parseInt(br.readLine());
		Projects = new int[N][2];
		answer = 0;
		start = 60;
		finish = 334;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Projects[i][0] = days[Integer.parseInt(st.nextToken()) - 1] + Integer.parseInt(st.nextToken());
			Projects[i][1] = days[Integer.parseInt(st.nextToken()) - 1] + Integer.parseInt(st.nextToken()) - 1;
		}

		Arrays.sort(Projects, (o1, o2) -> {
			if (o1[0] != o2[0])
				return o1[0] - o2[0];
			return o1[1] - o2[1];
		});
		int index = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			return  Projects[o2][1]-Projects[o1][1];
		});
		

		int end = 59;
		
		
		while (index < N) {

			if (Projects[index][1] <= end) {
				index++;
				continue;
			}

			if (Projects[index][0] <= end+1) {
				
				pq.add(index);
				index++;
				continue;
			}
			if (pq.isEmpty() && end < finish) {
				answer = 0;
				break;
			}
			
			
			
			end = Projects[pq.poll()][1];
			
			answer++;
			pq.clear();
			
			if (end >= finish) 
				break;
			
		}
		if(!pq.isEmpty()) {
			end = Projects[pq.poll()][1];
			answer++;
			pq.clear();
		}
		
		if(end<finish)
			answer=0;

		System.out.println(answer);
	}

}
