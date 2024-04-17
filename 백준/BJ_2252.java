import java.io.*;
import java.util.*;

public class BJ_2252 {


    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> qu = new LinkedList<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer,List<Integer>> map = new HashMap<>();
        int[] link = new int[N+1];

        for(int i=1;i<=N;i++)
            map.put(i,new ArrayList<>());

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            link[to]++;
            map.get(from).add(to);
        }

        for(int i=1;i<=N;i++){
            if(link[i]==0)
                qu.add(i);
        }

        while(!qu.isEmpty()){
            int num = qu.poll();
            stack.add(num);

            if(map.get(num).isEmpty())
                continue;

            for(int a:map.get(num)){
                link[a]--;

                if(link[a]==0)
                    qu.add(a);
            }
        }

        while(!stack.isEmpty())
            sb.append(stack.pop()).append(" ");

        System.out.println(sb);

    }
}
