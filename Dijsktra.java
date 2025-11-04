
import java.util.Scanner;

public class Dijsktra<E> {
    Scanner sc;
    MyHashMap<E, Pair<Integer, E>> table;
    MyHashMap<E,MyHashMap<E,Integer>> graph;
    public Dijsktra(MyHashMap<E,MyHashMap<E,Integer>> graph) {
        sc = new Scanner(System.in);
        table= new MyHashMap<E, Pair<Integer, E>>();
        this.graph = graph;
    }

    public int shortestPath(E start, E finish) {
        setupDTable(start,finish);
        return table.get(finish).getKey();
    }

    private void setupDTable(E start, E finish) {
        table.clear();
        for (int i=0; i<graph.toList().size(); i++) {
            table.put(graph.toList().get(i),new Pair<Integer,E>(0,null));
        }
        System.out.println(table); 
        MyHashSet<E> visited=new MyHashSet<E>();
        MyHashSet<E> unvisited=new MyHashSet<E>();
        E current = start;
        table.put(current,new Pair<Integer,E>(0,null));
        unvisited.add(current);
        System.out.println("starting: "+current);
        while(unvisited.size()!=0){
            visited.add(current);
            
            unvisited.remove(current);
            System.out.println("current: "+current);
            DLList<E> connections = graph.get(current).toList();
            System.out.println("connections: "+connections);
            System.out.println("unvisited: "+unvisited);
            // adds unvisited connections to unvisited set
            //System.out.println("connections size: "+graph.get(current).keySet().size());
            for (int i=0; i<connections.size(); i++) { // connections of current
                E key = connections.get(i);

                if (!visited.contains(key)) {
                    unvisited.add(key); // why is it adding Madrid and not Segovia?

                    //calculate distance
            
                    // find distance
                    int distance=adjacentConnection(current,key)+weightDT(current);
                    // shortest distance
                    if (weightDT(key)==0) {
                        table.get(key).setKey(distance);
                        table.get(key).setValue(current);
                    }
                    else if (distance<weightDT(key)) {
                        table.get(key).setKey(distance);
                        table.get(key).setValue(current);
                    }
                }

                
            }
            // finding shortest path to go down next
            int lowest=1000000000;
            E lowestNode = null;
            for (int a=0; a<unvisited.toDLList().size(); a++) { // connections of current
                E key2 = unvisited.toDLList().get(a);
                if(weightDT(key2)<lowest){
                    lowest = weightDT(key2);
                    lowestNode = key2;
                    
                }
            }
            //System.out.println("lowestNode: "+lowestNode);
            current = lowestNode;
            //sc.nextLine();
        }
        
        
        
        
        
     
    }

    public void printTable() {
        System.out.println("Table:");
        System.out.println("Node:\tWeight:\tPrevNode:");
        for (int i=0; i<table.toList().size(); i++) {
            E key = table.toList().get(i);
            System.out.println(i+". "+key+"\t"+weightDT(key)+"\t"+prevNodeDT(key));
        }
            
    }
    public DLList<E> getDirections(E start, E finish) {
        DLList<E> list = new DLList<E>();
        E current = finish;
        list.add(current);
        while (current!=start) {
            // System.out.println("current: "+current);
            // System.out.println("previous: "+table.get(current));
            E previous = table.get(current).getValue();
            list.add(0,previous);
            current=previous;
        }
        return list;
    }
    public String getDirections_toString(E start, E finish) {
        E current = finish;
        String list="";
        while (current!=start) {
            // System.out.println("current: "+current);
            // System.out.println("previous: "+table.get(current));
            E previous = table.get(current).getValue();
            list="Take "+previous+" to "+current+"-"+graph.get(current).get(previous)+"\n"+list;
            current=previous;
        }
        return list;
    }

    // getPrevNodeFromDTable
    private E prevNodeDT(E current) {
        return (E)(table.get(current).getValue());
    } 
    // getShortestDistanceFromDTable
    private int weightDT(E current) {
        return (int)(table.get(current).getKey());
    } 

    public int adjacentConnection(E node1, E node2) {
        //System.out.println("node1: "+node1+"\nnode2: "+node2);
        return graph.get(node1).get(node2);
        
    }

} 
