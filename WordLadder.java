import java.io.*;
import java.util.*;

public class  WordLadder {
    ArrayList<vertex> adjList;
    int count;






    WordLadder(String file) throws IOException {
        System.out.println("WORD LADDER.");
        System.out.println("LOADINGG......");

        adjList = new ArrayList();
        count = 0;
        ReadFile(file);
        CreateEdge();
      //  AllPath();
    }

    //O(1)
    public void AddVertex(String n, String o) {
        vertex v = new vertex(n, o);
        adjList.add(v);
        count++;

    }

   // O(1)
    public void AddEdge(vertex n1, vertex n2) {
        // add an edge between two words
        int i = adjList.indexOf(n1);
        int j = adjList.indexOf(n2);

        adjList.get(i).connections.add(adjList.get(j));
        adjList.get(j).connections.add(adjList.get(i));
    }


    //O(N)
    public void ReadFile(String file) throws IOException {
        BufferedReader  bf=new BufferedReader(new FileReader(new File(file)));
        String str="";int c=0;
        while(bf.ready()){
            c++;
            String data=bf.readLine();
            if(data.isBlank())
                continue;
            String word=data.substring(0,5);
            String meaning=data.substring(7);
            AddVertex(word,meaning);

        }


    }



    //Calls AddEdge between two words which only have a single different letter
   //O(N^2)
    public void CreateEdge() {
        for (int i = 0; i < adjList.size(); i++) {
            String word = adjList.get(i).word;
            for (int j = i + 1; j < adjList.size(); j++) {
                int count = 0;
                String word2 = adjList.get(j).word;
                for (int k = 0; k < word.length(); k++) {
                    if (word.charAt(k) == word2.charAt(k))
                        count++;
                }
                if (count == 4)
                    AddEdge(adjList.get(i), adjList.get(j));

            }

        }
    }



int c=0;


    //O(N)
    public ArrayList<vertex> problem(String level) throws IOException {
        ArrayList<vertex> t=null;
        int count=0;

       while(t==null){
            int k=(int)(Math.random()*adjList.size());
            int j=(int)( Math.random()* adjList.size());
            if(k==j)
                continue;
          //  count++;


           t= GeneratePath(adjList.get(k).word,adjList.get(j).word,level);


            }
      // System.out.println("c:"+c);
       // System.out.println("count:"+ count);
        return t;
        }




    public ArrayList GeneratePath(String l1, String l2,String level) throws IOException {
        c++;
        ArrayList<vertex> t;
        int[] prev = new int[adjList.size()];
        Queue<vertex> Q = new ArrayDeque<vertex>();
        boolean[] visited = new boolean[adjList.size()];
        int i = 0;
        int d = -1;
        boolean r = false;
        String s = "";
        for (i = 0; i < adjList.size(); i++) {
            if(adjList.get(i)==null)break;
            if (adjList.get(i).word.equals(l1)) break;
        }
        if (i < adjList.size()) {
            t = new ArrayList<>();
            vertex v = adjList.get(i);
            Q.add(v);
            visited[i] = true;
            prev[i] = -1;
            while (!Q.isEmpty()) {
                v = Q.remove();
                int p = adjList.indexOf(v);
                LinkedList<vertex> l = v.connections;
                for (int j = 0; j < l.size(); j++) {
                    vertex b = l.get(j);
                    int m = adjList.indexOf(b);
                    if (!visited[m]) {
                        Q.add(b);
                        visited[m] = true;
                        prev[m] = p;
                    }
                    if (b.word.equals(l2)) {
                        r = true;
                        d = adjList.indexOf(b);
                        break;
                    }
                }
                if (r == true) break;
            }
            if (r == false) {
                return null;
            } else {
                int k = d;
                t.add(adjList.get(k));
                k = prev[k];
                while (k != -1) {
                    t.add(adjList.get(k));
                    k = prev[k];
                    if(level.equals("easy") && t.size()>4){
                       return t;
                    }
                    if(level.equals("medium") && t.size()>6 )
                        return t;
                    else if (t.size() >=10)
                       return t;


                }




            }
        } else {
            System.out.println("Source does not exist");
            return null;
        }
        return null;

    }

   //O(N^2)
   //only used when necessary ie not called always
    public String toString(){
        String s ="Printing Graph \n";
        for(int i = 0; i < adjList.size(); i++){
            s = s +" " + adjList.get(i)+" {";
            if(adjList.get(i)!=null && adjList.get(i).connections.size()!=0){
                for(int j = 0; j < adjList.get(i).connections.size(); j++){
                    vertex a = (vertex) adjList.get(i).connections.get(j);
                    s= s +" "+(a);
                }}
            s = s + "}\n";
        }
        return s;
    }


}