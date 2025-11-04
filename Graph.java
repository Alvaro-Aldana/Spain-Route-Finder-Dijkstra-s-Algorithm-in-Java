

public class Graph<E>{
    private MyHashMap<E,MyHashMap<E,Integer>> graph;
    private Dijsktra<E> dijsktra;
    private int size;

    public Graph(){
        graph = new MyHashMap<E,MyHashMap<E,Integer>>();
        size=0;
        dijsktra = new Dijsktra<E>(graph);
    }
    
    public String toString() {
        String s="";
        
        for (int i=0; i<graph.toList().size(); i++) { 
            E key = graph.toList().get(i);
            s+=key+"->";
            for (int a=0; a<graph.get(key).toList().size(); a++) { 
                E key2 = graph.get(key).toList().get(a);
               int weight=graph.get(key).get(key2);
               String character=""+key2;
               s+=character+":"+weight+", ";
            }
            s+="\n";
        }
        return s;
    }

    public DLList<E> toList() {
        return graph.keySet().toDLList();
    }
    public DLList<E> toListConnections(E key) {
        return graph.get(key).keySet().toDLList();
    }
    public void add(E data){
        graph.put(data,new MyHashMap<E,Integer>());
        //System.out.println("PRINT NULL: "+graph.put(data,new MyHashMap<E,Integer>()));
        size++;

    }
    
    public void addEdge(E data1, E data2, int num){
        graph.get(data1).put(data2,num);
        graph.get(data2).put(data1,num);
 
    }
    public void remove(E data) {
        // HashSet<E> kids = graph.get(data);
        for (int i=0; i<graph.get(data).toList().size(); i++) { 
            E key = graph.get(data).toList().get(i);
            graph.get(key).remove(data);
        }
        graph.remove(data);
        size--;
    }
    // remove by ID
    public void remove2(E root,E data) {
        // HashSet<E> kids = graph.get(data);
        E f=friendSearch(root,data);
        //remove(f.getID());
        for (int i=0; i<graph.get(f).toList().size(); i++) { 
            E key = graph.get(f).toList().get(i);
            graph.get(key).remove(f);
        }
        graph.remove(f);
    }
    public int size(){
        return size;
    }

    public int adjacentConnection(E node1, E node2) {
        return graph.get(node1).get(node2);
        // for (E key : graph.get(node1).keySet()) {
        //     if (node2.equals(key)) {
        //         return graph.get(node1).get(node2);
        //     }
        // }
    }
    public E searchByName(String name) {
        for (int i=0; i<graph.toList().size(); i++) { 
            E key = graph.toList().get(i);
            String str = (String)(((Location)(key)).getABV());
            if (name.equals(str)) {
                return key;
            }
        }
        return null;
    }
    public E friendSearch(E startData, E searchData){
        MyHashSet<E> toVisit=new MyHashSet<E>();
        MyHashSet<E> toVisit2=new MyHashSet<E>();
        MyHashSet<E> visited=new MyHashSet<E>();

        toVisit.add(startData);
        while (visited.size()<graph.size()) {
            for (int i=0; i<toVisit.toDLList().size(); i++) { 
                E key = toVisit.toDLList().get(i);


                if(key.toString().equals(searchData.toString())) {
                    return key;
                }
                else{
                    visited.add(key);   
                }
            }
            // going to update the toVisit list
            toVisit2 = new MyHashSet<E>();
            for (int i=0; i<toVisit.toDLList().size(); i++) { 
                E key = toVisit.toDLList().get(i);
                for (int a=0; a<graph.get(key).toList().size(); a++) { 
                    E key2 = graph.get(key).toList().get(a);
                    if (visited.contains(key2)==false) {
                        toVisit2.add(key2);
                    }
                }
            }
            //
        
            toVisit=toVisit2;
        }
        return searchData;
    }
    public boolean breadthSearch(E startData, E searchData){
        MyHashSet<E> toVisit=new MyHashSet<E>();
        MyHashSet<E> toVisit2=new MyHashSet<E>();
        MyHashSet<E> visited=new MyHashSet<E>();

        toVisit.add(startData);
        while (visited.size()<graph.size()) {
            for (int i=0; i<toVisit.toDLList().size(); i++) { 
                E key = toVisit.toDLList().get(i);

                if(key.toString().equals(searchData.toString())) {
                    return true;
                }
                else{
                    System.out.println(key);
                    visited.add(key);
                }
            }
            // going to update the toVisit list
            toVisit2 = new MyHashSet<E>();
            for (int i=0; i<toVisit.toDLList().size(); i++) { 
                E key = toVisit.toDLList().get(i);
                for (int a=0; a<graph.get(key).toList().size(); a++) { 
                    E key2 = graph.get(key).toList().get(a);
                    if (visited.contains(key2)==false) {
                        toVisit2.add(key2);
                    }
                }
            }
            //
        
            toVisit=toVisit2;
        }
        return false;
    }

    // public boolean depthFirstSearch(E startData, E searchData){
    //      HashSet<E> toVisit=new HashSet<E>();
    //     HashSet<E> toVisit2=new HashSet<E>();
    //     HashSet<E> visited=new HashSet<E>();

    //     toVisit.add(startData);
    //     while (visited.size()<graph.size()) {
    //         for (E key : toVisit) {

    //             if(key.toString().equals(searchData.toString())) {
    //                 //System.out.println(key.toString());
    //                 return true;
    //             }
    //             else{
    //                 System.out.println(key);
    //                 visited.add(key);
    //             }
    //         }
    //         // going to update the toVisit list
    //         // toVisit2 = new HashSet<E>();
    //         for (E key : toVisit) {
    //              for (E key2 : graph.get(key)) {
    //                  if (visited.contains(key2)==false) {
    //                     depthFirstSearch(startData,key2);
    //                      //toVisit2.add(key2);
    //                  }
    //              }
    //         }
    //         //
        
    //         //toVisit=toVisit2;
    //     }
    //     return false;
    // }

    public int shortestPath(E start, E finish) {
        int num = dijsktra.shortestPath(start,finish);
        System.out.println("shortest path worked");
        dijsktra.printTable();
        return num;
    }
    public DLList<E> getDirections(E start, E finish) {
        return dijsktra.getDirections(start,finish);
    }
    public String getDirections_toString(E start, E finish) {
        return dijsktra.getDirections_toString(start,finish);
    }
}



