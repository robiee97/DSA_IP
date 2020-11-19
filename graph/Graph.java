import java.util.*;

public class Graph {
    static HashMap<Integer, ArrayList<Edge>> graph;

    public static class Edge {
        int nbr;
        int wt;

        public Edge(int nbr, int wt) {
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(HashMap<Integer, ArrayList<Edge>>g,int v1, int v2, int wt) {
        Edge e1 = new Edge(v2, wt);
        if (g.containsKey(v1)) {
            ArrayList<Edge> list = g.get(v1);
            list.add(e1);
            g.put(v1, list);
        } else {
            ArrayList<Edge> list = new ArrayList<>();
            list.add(e1);
            g.put(v1, list);
        }
        Edge e2 = new Edge(v1, wt);
        if (g.containsKey(v2)) {
            ArrayList<Edge> list = g.get(v2);
            list.add(e2);
            g.put(v2, list);
        } else {
            ArrayList<Edge> list = new ArrayList<>();
            list.add(e2);
            g.put(v2, list);
        }
    }
    public static void display(HashMap<Integer, ArrayList<Edge>>g){
        for (int v : g.keySet()) {
            System.out.print(v + "->");
            for (Edge e : g.get(v)) {
                System.out.print(e.nbr + ",");
            }
            System.out.println();
        }
    }

    public static boolean hasPath(int s, int d, boolean[] isVisited) {
        if (s == d) {
            return true;
        }
        isVisited[s] = true;
        for (Edge e : graph.get(s)) {
            if (!isVisited[e.nbr]) {
                boolean ans = hasPath(e.nbr, d, isVisited);
                if (ans) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printAllPaths(int s, int d, boolean[] isVisited, String psf) {
        if (s == d) {
            System.out.println(psf);
            return;
        }
        isVisited[s] = true;
        for (Edge e : graph.get(s)) {
            if (!isVisited[e.nbr]) {
                psf += ""+e.nbr;
                printAllPaths(e.nbr, d, isVisited, psf);
                psf = psf.substring(0, psf.length() - 1);
            }
        }
        isVisited[s] = false;
    }

    public static boolean bfs(int s, int d){
        boolean[] isVisited= new boolean[graph.size()];
        for(int i=0;i<isVisited.length;i++){
            isVisited[i]=false;
        }
        LinkedList<Integer> q= new LinkedList<>();
        q.addLast(s);
        while(q.size()>0){
            int rem= q.removeFirst();
            if(isVisited[rem]){
                continue;
            }
            isVisited[rem]=true;
            if(rem==d){
                return true;
            }
            for(Edge e : graph.get(rem)){
                if(!isVisited[e.nbr]){
                    q.addLast(e.nbr);
                }
            }
        }
        return false;
    }
    public static String getSingleConnectedComp(int s,boolean[] isVisited){
        LinkedList<Integer>q= new LinkedList<>();
        q.addLast(s);
        String comp="";
        while(q.size()>0){
            int rem=q.removeFirst();
            if(isVisited[rem]){
                continue;
            }
            isVisited[rem]=true;
            comp+=""+rem;
            for(Edge e: graph.get(rem)){
                if(!isVisited[e.nbr]){
                    q.addLast(e.nbr);
                }
            }
        }
        return comp;
    }
    public static ArrayList<String> getAllConnectedComp(){
        ArrayList<String> ans= new ArrayList<>();
        boolean[] isVisited= new boolean[graph.size()];
        for(int i=0;i<isVisited.length;i++){
            isVisited[i]=false;
        }
        for(int i=0;i<isVisited.length;i++){
            if(!isVisited[i]){
                String comp=getSingleConnectedComp(i, isVisited);
                ans.add(comp);
            }
        }
        return ans;
    }
    public static boolean isConnected(){
        boolean[] isVisited= new boolean[graph.size()];
        for(int i=0;i<isVisited.length;i++){
            isVisited[i]=false;
        }
        int c=0;
        for(int i=0;i<isVisited.length;i++){
            if(!isVisited[i]){
                getSingleConnectedComp(i, isVisited);
                c++;
                if(c==2){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isCompCyclic(int s,boolean[] isVisited){
        LinkedList<Integer> q= new LinkedList<>();
        q.addLast(s);
        while(q.size()>0){
            int rem= q.removeFirst();
            if(isVisited[rem]){
                return true;
            }
            isVisited[rem]=true;
            for(Edge e : graph.get(rem)){
                if(!isVisited[e.nbr]){
                    q.addLast(e.nbr);
                }
            }
        }
        return false;
    }
    public static boolean isCyclic(){// if graph has multiple components
        boolean[] isVisited= new boolean[graph.size()];
        for(int i=0;i<isVisited.length;i++){
            isVisited[i]=false;
        } 
        for(int i=0;i<graph.size();i++){
            if(!isVisited[i]){
                boolean ans= isCompCyclic(i, isVisited);
                if(ans)return true;
            }
        }
        return false;   
    }

    public static class Bipair{
        int v;
        int l;
    }
    public static boolean isBipartiteComp(int s, int[] isVisited){
         LinkedList<Bipair> q= new LinkedList<>();
         Bipair bp= new Bipair();
         bp.v=s;
         bp.l=1;
         q.addLast(bp);
         while(q.size()>0){
            Bipair rem= q.removeFirst();
            if(isVisited[rem.v]!=0){
                int ol= isVisited[rem.v];
                int nl= rem.l;
                if(ol%2!=nl%2){
                    return false;
                }
            }
            isVisited[rem.v]=rem.l;
            for(Edge e : graph.get(rem.v)){
                if(isVisited[e.nbr]==0){
                    Bipair np= new Bipair();
                    np.l=rem.l+1;
                    np.v=e.nbr;
                }
            }
         }
         return true;   
    }

    public static boolean isBipartite(){//if graph has multiple components
        int[] isVisited= new int[graph.size()];
        for(int i=0;i<graph.size();i++){
            if(isVisited[i]==0){
                boolean ans= isBipartiteComp(i, isVisited);
                if(ans)return true;
            }
        }
        return false;
    } 

    //djikstra
    public static class Dpair implements Comparable<Dpair>{
        int v;
        int c;
        String p;        
        @Override
        public int compareTo(Dpair o){
            return this.c-o.c; 
        }
    }
    public static void djikstras(int s){
        boolean[] isVisited= new boolean[graph.size()];
        for(int i=0;i<isVisited.length;i++){
            isVisited[i]=false;
        }
        PriorityQueue<Dpair> pq= new PriorityQueue<>();
        Dpair bp= new Dpair();
        bp.v= s;
        bp.c= 0;
        bp.p="";
        pq.add(bp);
        while(pq.size()>0){
            Dpair rem = pq.poll();
            if(isVisited[rem.v]){
                continue;
            }
            isVisited[rem.v]=true;
            System.out.println(rem.v+" via "+rem.p+" @ "+rem.c);

            for(Edge e: graph.get(rem.v)){
                if(!isVisited[e.nbr]){
                    Dpair np= new Dpair();
                    np.v=e.nbr;
                    np.c=rem.c+e.wt;
                    np.p=rem.p+e.nbr;
                    pq.add(np);
                }
            }
        }
    }

    public static class Ppair implements Comparable<Ppair>{
        int v;  
        int av;
        int c;  
        @Override 
        public int compareTo(Ppair o){  
            return this.c-o.c;
        }
    }
    public static void prims(int s){
        boolean[] isVisited= new boolean[graph.size()];
        for(int i=0;i<isVisited.length;i++){
            isVisited[i]=false;
        }
        HashMap<Integer,ArrayList<Edge>> mst= new HashMap<>();
        PriorityQueue<Ppair> pq= new PriorityQueue<>();
    
        Ppair bp= new Ppair();
        bp.v=0;
        bp.av=-1;
        bp.c=0;
        pq.add(bp);
        while(pq.size()>0){
            Ppair rem= pq.poll();
            if(isVisited[rem.v]){
                continue;
            }
            isVisited[rem.v]=true;
            if(rem.av!=-1){
                addEdge(mst,rem.v, rem.av, rem.c);
            }
            for(Edge e:graph.get(rem.v)){
                if(!isVisited[e.nbr]){
                    Ppair np= new Ppair();
                    np.v=e.nbr;
                    np.av=rem.v;
                    np.c=e.wt;
                    pq.add(np);
                }
            }
        }
        display(mst);
    }

    static HashMap<Integer, ArrayList<Edge>> dag;
    public static void addEdgeInDAG(HashMap<Integer, ArrayList<Edge>>g,int v1, int v2, int wt) {
        Edge e1 = new Edge(v2, wt);
        if (g.containsKey(v1)) {
            ArrayList<Edge> list = g.get(v1);
            list.add(e1);
            g.put(v1, list);
        } else {
            ArrayList<Edge> list = new ArrayList<>();
            list.add(e1);
            g.put(v1, list);
        }
    }
    public static void topoSortComp(boolean[] isVisited, Stack<Integer> st, int s){
        isVisited[s]=true;
        if(dag.containsKey(s)){
            for(Edge e : dag.get(s)){
                if(!isVisited[e.nbr]){
                    topoSortComp(isVisited, st, e.nbr);
                }
            }
        }
        st.push(s);
    }
    public static void topologicalSort(){
        boolean[] isVisited= new boolean[7];
        for(int i=0;i<dag.size();i++){
            isVisited[i]=false;
        }
        Stack<Integer> st= new Stack<>();
        for(int i=0;i<dag.size();i++){
            if(!isVisited[i]){
                topoSortComp(isVisited,st,i);
            }
        }
        while(st.size()>0){
            System.out.print(st.peek());
            st.pop();
        }
    }   

    public static class KEdge implements Comparable<KEdge>{
        int v1;
        int v2;
        int wt;
        public KEdge(int v1, int v2, int wt) {
            this.v1 = v1;
            this.v2 = v2;
            this.wt = wt;
        }
        @Override 
        public int compareTo(KEdge o){
            return this.wt-o.wt;
        }
    }
    public static void kruskals(){
        HashMap<Integer,ArrayList<Edge>> mst= new HashMap<>();
        int[] pa= new int[graph.size()];
        int[] ra= new int[graph.size()];
        for(int i:graph.keySet()){
            pa[i]=i;
            ra[i]=1;
        }
        PriorityQueue<KEdge> pq= new PriorityQueue<>();
        for(int i:graph.keySet()){
            for(Edge e:graph.get(i)){
                if(i<e.nbr){
                    pq.add(new KEdge(i, e.nbr, e.wt));
                }
            }
        }
        while(pq.size()>0){
            KEdge rem= pq.poll();
            int v1=rem.v1;
            int v2=rem.v2;
            int v1p=find(pa,v1);
            int v2p=find(pa,v2);
            if(v1p!=v2p){
                addEdge(mst, v1, v2, rem.wt);
                merge(pa,ra,v1p,v2p);
            }
        }
        display(mst);
    }
    public static int find(int[] pa,int v){
        if(pa[v]==v){
            return v;
        }else{
            return find(pa, pa[v]);
        }
    }
    public static void merge(int[] pa , int[] ra, int v1p, int v2p){
        if(ra[v1p]<ra[v2p]){
            pa[v1p]=v2p;
        }else if(ra[v1p]>ra[v2p]){
            pa[v2p]=v1p;
        }else{
            pa[v1p]=v2p;
            ra[v2p]++;
        }
    }
    public static void main(String[] args) {
        graph = new HashMap<>();
        // addEdge(graph,0, 1, 10);
        // addEdge(graph,1, 2, 10);
        // addEdge(graph,2, 3, 10);
        // addEdge(graph,0, 3, 40);
        // addEdge(graph,3, 4, 2);
        // addEdge(graph,4, 5, 3);
        // addEdge(graph,5, 6, 3);
        // addEdge(graph,4, 6, 8);
        // display();
        // System.out.println(hasPath(1, 4, new boolean[5]));
        // printAllPaths(1, 4, new boolean[5], "");
        // System.out.println(bfs(1, 4));
        // System.out.println(getSingleConnectedComp(0, new boolean[5]));
        // System.out.println(getAllConnectedComp());
        // System.out.println(isConnected());
        // System.out.println(isCompCyclic(0, new boolean[5]));
        // System.out.println(isCyclic());
 
        // System.out.println(isBipartiteComp(0, new int[7]));
        // System.out.println(isBipartite());
        // djikstras(0);
        // prims(0);
 
        // dag= new HashMap<>();
        // addEdgeInDAG(dag,0,1,1);
        // addEdgeInDAG(dag,1,2,1);
        // addEdgeInDAG(dag,2,3,1);
        // addEdgeInDAG(dag,0,4,1);
        // addEdgeInDAG(dag,4,3,1);
        // addEdgeInDAG(dag,5,4,1);
        // addEdgeInDAG(dag,5,6,1);
        // addEdgeInDAG(dag,6,3,1);
        // topologicalSort();

        //kruskal graph
        addEdge(graph, 0, 1, 20);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 2, 3, 20);
        addEdge(graph, 2, 5, 5);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 4, 6, 8);
        kruskals();
    }
}