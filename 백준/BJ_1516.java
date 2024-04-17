import java.io.*;
import java.util.*;
public class BJ_1516 {
    public static int[] link;
    public static int[] cost;
    public static int[] answer;
    public static Map<Integer,ArrayList<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        link = new int[N+1];
        cost = new int[N+1];
        answer = new int[N+1];

        for(int i=1;i<=N;i++){
            map.put(i,new ArrayList<>());
        }

        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i]=Integer.parseInt(st.nextToken());

            while(st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());

                if(num==-1)
                    break;
                map.get(i).add(num);
                link[i]++;
            }
            if(link[i]==0)//선행 건물이 필요없는 경우 해당 건물의 건축시간=소요건설시간
                answer[i]=cost[i];
        }
        for(int i=1;i<=N;i++){
            if(answer[i]!=0)//해당 건물의 건축시간이 이미 구해진 경우 pass
                continue;
            answer[i]=build(i);//구해지지 않은 경우 재귀
        }
        for(int i=1;i<=N;i++)
            System.out.println(answer[i]);
    }
    public static int build(int index){
        if(link[index]==0)//해당 건물의 선행 건물이 없으면 -> 가장 선행 건물이다.
            return answer[index];

        for(int a:map.get(index)){
            if(answer[a]!=0)//이 부분이 지속적으로 선행 건물을 안찍고 오게 해주는 조건문->a건물 건축 시간을 이미 구한 경우
                answer[index]=Math.max(answer[index],answer[a]);//현재 건물 선행 건물 건축시간 중 가장 긴걸 채택-> 선행건물이 여러개일 경우 가장 오래걸리는게 끝난 후에 건축이 가능하기 때문에
            else
                answer[index]=Math.max(answer[index],build(a));
        }
        answer[index]+=cost[index];//선행건물중 가장 오래걸리는 것에 + 현재 건물 건축시간

        return answer[index];
    }
}
