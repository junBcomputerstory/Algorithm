import java.io.*;
import java.util.*;

public class BJ_12865 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int N =Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());
			
		int[][] ingredient = new int [N+1][2];
			
		for(int i=1; i<=N ;i++) {
			st = new StringTokenizer(br.readLine());
			ingredient[i][1] = Integer.parseInt(st.nextToken());
			ingredient[i][0] = Integer.parseInt(st.nextToken());
		}
			
		int [] dp =new int[limit+1];
			
		for(int i=1; i<=N; i++) {
			for(int j=limit; j-ingredient[i][1]>=0; j-- ) {
				dp[j] = Math.max(dp[j], dp[j - ingredient[i][1]] + ingredient[i][0]);
			}
		}
			
		System.out.println(dp[limit]);

	}

}
