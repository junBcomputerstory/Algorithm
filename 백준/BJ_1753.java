import  java.io.*;
import java.util.*;

public class BJ_1753 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        Map<Integer,List<node>> map=new HashMap<>();

        int v=Integer.parseInt(st.nextToken());
        int line=Integer.parseInt(st.nextToken());

        int dis[] = new int[v+1];
        boolean visited[] = new boolean[v+1];

        Arrays.fill(dis,200000);
        int start=Integer.parseInt(br.readLine());
        dis[start]=0;


        for(int i=0; i<line; i++){
            st = new StringTokenizer(br.readLine());
            int from=Integer.parseInt(st.nextToken());
            node tmp = new node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            map.putIfAbsent(from,new ArrayList<>());
            map.get(from).add(tmp);
        }

        PriorityQueue<node> qu = new PriorityQueue<>();
        qu.add(new node(start,0));



        while(!qu.isEmpty()){
            node tmp = qu.poll();

            if(visited[tmp.to])
                continue;
            visited[tmp.to]=true;
            if(!map.containsKey(tmp.to))
                continue;
            for(node a:map.get(tmp.to)){
                if(dis[a.to]>dis[tmp.to]+a.weight){
                    dis[a.to]=dis[tmp.to]+a.weight;
                    qu.add(new node(a.to,dis[a.to]));

                }
            }
        }

        for(int i=1;i<=v;i++){
            if(dis[i]==200000)
                System.out.println("INF");
            else
                System.out.println(dis[i]);
        }



    }


}
class node implements Comparable<node>{

    int to;
    int weight;

    public node(int to, int weight){

        this.to=to;
        this.weight=weight;
    }
    @Override
    public int compareTo(node n){
        return this.weight-n.weight;
    }
}
