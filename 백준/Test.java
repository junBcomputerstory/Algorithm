import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0,2,3));
        pq.add(new Point(0,0,3));
        while(!pq.isEmpty()) {
            Point tmp = pq.poll();
            System.out.println(tmp.y);
        }
    }
     private static class Point implements Comparable<Point>{
        int x;
        int y;
        int time;

        public Point(int x,int y,int time){
            this.x=x;
            this.y=y;
            this.time=time;
        }

        @Override
        public int compareTo(Point o){
            if(this.x==o.x)
                return this.y-o.y;
            return this.x-o.x;

        }

    }
}

