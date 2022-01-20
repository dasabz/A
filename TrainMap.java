package goldmine.medium;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TrainMap {
    private static class Station{
        private String name;
        private List<Station> neighbours;
        public Station(String name){
            this.name = name;
            this.neighbours=new ArrayList<>(3);
        }
        String getName(){
            return name;
        }
        void addNeighbour(Station v){
            this.neighbours.add(v);
        }
        List<Station> getNeighbours(){
            return this.neighbours;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Station station = (Station) o;
            return Objects.equals(name, station.name) &&
                    Objects.equals(neighbours, station.neighbours);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, neighbours);
        }
    }
    private HashMap<String,Station> stations;
    public TrainMap(){
        this.stations=new HashMap<>();
    }
    public TrainMap addStation(String name){
        Station s= new Station(name);
        this.stations.putIfAbsent(name,s);
        return this;
    }
    public Station getStation(String name){
        return this.stations.get(name);
    }
    public TrainMap connectStations(Station fromStation,Station toStation){
        if(fromStation==null || toStation==null)
            throw new IllegalArgumentException();
        fromStation.addNeighbour(toStation);
        toStation.addNeighbour(fromStation);
        return this;
    }
    public List<Station> shortestPathBFS(String from, String to){
        Station root = this.stations.get(from);
        if(root==null)
            throw new IllegalArgumentException("Start not present");
        Station goalNode = this.stations.get(to);
        if(goalNode==null)
            throw new IllegalArgumentException("Start not present");
        LinkedList<Station> searchSpace= new LinkedList<>();
        Map<Station,Station> parentOfTheNode =new HashMap<>();
        Set<Station> visited = new HashSet<>();
        searchSpace.add(root);
        while(!searchSpace.isEmpty()){
            Station curr = searchSpace.poll();
            if(!visited.contains(curr)){
                visited.add(curr);
                if(curr.equals(goalNode))
                    break;
                else
                    curr.getNeighbours().stream().forEach(station -> {
                        parentOfTheNode.putIfAbsent(station,curr);
                        searchSpace.addLast(station);
                    });
            }
        }
        LinkedList<Station> shortestPath = new LinkedList<>();
        Station parentNode = parentOfTheNode.get(goalNode);
        shortestPath.addFirst(goalNode);
        shortestPath.addFirst(parentNode);
        while ((!parentNode.equals(root))){
            parentNode= parentOfTheNode.get(parentNode);
            shortestPath.addFirst(parentNode);
        }
        return shortestPath;
    }
    public Set<List<Station>> getAllPathsDFS(String from, String to){
        Set<List<Station>> allPaths = new HashSet<>();
        Station root = this.stations.get(from);
        Station goalNode = this.stations.get(to);
        Stack<Station> searchSpace = new Stack();
        searchSpace.push(root);
        allPathsDFSRecursive(searchSpace,goalNode,allPaths);
        return allPaths;
    }
    private void allPathsDFSRecursive(Stack<Station> searchSpace,Station goalNode,Set<List<Station>> paths){
        Station current = searchSpace.peek();
        if(current.equals(goalNode)){
            Station [] path = new Station[searchSpace.size()];
            paths.add(Arrays.asList(searchSpace.toArray(path)));
        }else{
            for(Station v: current.getNeighbours()){
                if(!searchSpace.contains(v)){
                    searchSpace.push(v);
                    allPathsDFSRecursive(searchSpace,goalNode,paths);
                }
            }
        }
        searchSpace.pop();
    }

    public List<Station> shortestPathDFS(String from, String to){
        Set<List<Station>> paths = getAllPathsDFS(from,to);
        List<Station> shortest= null;
        for(List<Station> path:paths){
            if(shortest==null || shortest.size()>path.size()){
                shortest=path;
            }
        }
        return shortest;
    }
    public static String convertPathTpString(List<Station> path){
        if(path.isEmpty())
            return "";
        return path.stream().map(Station::getName).reduce( (s1,s2)->s1+"->"+s2).get();
    }
}

