import java.io.*;
import java.util.*;

public class BJ_11054 {

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] nums = new int[N];
        int [] dp = new int[N];
        int [] back_dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        //인덱스 0 부터 증가하는 수열을 구함
        for(int i=0; i<N ; i++){
            dp[i]=1;

            for(int j=0; j<i ; j++){

                if(nums[i]>nums[j] && dp[i]<dp[j]+1)
                    dp[i] = dp[j]+1;

            }
        }

        //인덱스 N-1부터 증가하는 수열을 구함
        for(int i=N-1;i>=0;i--){
            back_dp[i]=1;

            for(int j=N-1; j>i ;j--){

                if(nums[i]>nums[j] && back_dp[i]<back_dp[j]+1)
                    back_dp[i] = back_dp[j]+1;
            }
        }

        int answer=0;
        for(int i=0; i<N ; i++)
            answer= Math.max((dp[i] + back_dp[i] - 1), answer);


        System.out.println(answer);

    }

}
