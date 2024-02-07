import java.io.*;


public class BJ_2023 {
	
	static StringBuilder sb=new StringBuilder();
	static int[] add=new int[] {1,3,7,9}; //두번째 자리수부터 올 수 있는 숫자 2,4,6,8은 2로 인해 불가능 5,0또한 마찬가지

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] prime=new int[] {2,3,5,7};//첫번째 자리수에 올수 있는 소수
		N-=1;//숫자를 10의 N승씩 곱해줄것이기 때문에 N 을 하나 줄여놓는다.
		
		for(int i:prime)
			buildNum(i,N,0);
		
		System.out.println(sb);
		
	}
	
	public static void buildNum(int num,int N,int sum) {
		sum+=num*Math.pow(10, N);//처음 들어오는 수의 경우 숫자*10의 N-1승 
		
		
		if(validate(sum,(int)Math.pow(10, N)))//소수검증
			return;
		
		if(N==0) {//마지막 자리수까지 들어왔고 소수검증도 통과한 경우
			sb.append(sum).append("\n");
			return;
		}
		
		
		for(int i:add) {//숫자 붙이기
			buildNum(i,N-1,sum);
		}
		
	}
	
	public static boolean validate(int num,int divide_num) {
			num/=divide_num;//곱해줬던 10의 자리수 제거
			
			
			for(int i=2;i*i<num;i++)//소수검증 
				if(num%i==0)
					return true;
		return false;
	}

}
